package br.com.CDB.BancoDigital.services;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import br.com.CDB.BancoDigital.dao.CartaoDao;
import br.com.CDB.BancoDigital.dao.ContaDao;
import br.com.CDB.BancoDigital.entity.cartao.Cartao;
import br.com.CDB.BancoDigital.entity.cartao.CartaoCredito;
import br.com.CDB.BancoDigital.entity.cartao.CartaoDebito;
import br.com.CDB.BancoDigital.entity.conta.Conta;
import br.com.CDB.BancoDigital.exceptions.Exceptions;

public class CartaoServices {

    private CartaoDao cartaoDao = CartaoDao.getInstance();
    private Exceptions exceptions = new Exceptions();
    private ContaDao contaDao;
    private Scanner sc;

    public CartaoServices() {
        this.contaDao = ContaDao.getInstance();  // Obtendo a instância única
        this.sc = new Scanner(System.in).useLocale(Locale.US);
    }

    // Método para solicitar entrada do usuário
    private String solicitarEntrada(String mensagem) {
        System.out.println(mensagem);
        return sc.nextLine().trim();
    }

    public void registrarCartao() {   
        if(contaDao.getTotalContas()== 0) {
            System.out.println("Nenhum conta cadastrado. Não é possível abrir uma conta.");
            return;
        }

        int idConta= Integer.parseInt(solicitarEntrada("Digite o ID da conta associada: "));
        Conta conta = contaDao.buscarContaPorId(idConta);
        
        if (conta == null) {
            System.out.println("Erro: Conta não encontrada.");
            return;
        }
        
        if (conta.getCartoes().size() >= 2) { // Agora verificamos direto na conta
            System.out.println("Erro: A conta já possui o número máximo de cartões.");
            return;
        }
    

        int numeroDoCartao = Integer.parseInt(solicitarEntrada("Digite o numero do cartão: "));
        String senha = solicitarEntrada("Cadatre a senha do cartão: ");
        exceptions.isStringEmptyField(senha);

        System.out.println("""
            Cartão:
            1 - Ativo
            2 - Desativado 
            """);
        int status = Integer.parseInt(solicitarEntrada("Qual o Status do cartão"));
        boolean cartaoStatus = (status == 1);

        System.out.println("""
            Tipo de Cartão:
            1 - Cartão de Crédito
            2 - Cartão de Débito 
            """);
        int tipoCartao = Integer.parseInt(solicitarEntrada("Insira: "));

        Cartao cartao;
            
        if (tipoCartao == 1) {
            double limiteCredito = switch (conta.getClienteAssociado().getCategoria()) { 
                case COMUM -> 1.000;
                case SUPER -> 5.000;
                case PREMIUM -> 10.000;
            };
            cartao = new CartaoCredito(numeroDoCartao, senha, cartaoStatus, limiteCredito);
            } else if (tipoCartao == 2) {
                double limiteDiario = Double.parseDouble(solicitarEntrada("Qual o limite do cartão hoje:  "));
                cartao = new CartaoDebito(numeroDoCartao, senha, cartaoStatus, limiteDiario);
            } else {
                System.out.println("Tipo de cartão inválido.");
                return;
            }

        conta.adicionarCartao(cartao);
        cartaoDao.registrarCartaoCliente(cartao);
        System.out.println("Cartão registrado com sucesso");
    }

    // Buscar um cartão pelo ID
    public void buscarCartao() {
        int idCartao = Integer.parseInt(solicitarEntrada("Digite o ID do cartao: "));
        System.out.println(cartaoDao.buscarCartaoPorId(idCartao));

    }

    // Listar todos os cartões
    public void exibirCartoes() {
        try {
            List<Cartao> clientes = cartaoDao.listarCartoes();
            System.out.println(clientes);
        } catch (IllegalStateException e) {
            System.out.println("Nenhum cartao foi cadastrado ainda. Cadastre um cartao antes de tentar visualizar a lista.");
        }
    }

    // Atualizar um cartão
    public void atualizarCartao() {
        int idCartao = Integer.parseInt(solicitarEntrada("Digite o ID do cartão que deseja atualizar: "));
        Cartao cartao = cartaoDao.buscarCartaoPorId(idCartao);
    
        if (cartao == null) {
            System.out.println("Erro: Cartão não encontrado.");
            return;
        }
    
        System.out.println("O que deseja atualizar?");
        System.out.println("1 - Número do cartão");
        System.out.println("2 - Senha");
        System.out.println("3 - Status do cartão (Ativar/Desativar)");
        System.out.println("4 - Tipo do Cartão (Somente se necessário)");
        System.out.println("5 - Cancelar");
    
        int escolha = Integer.parseInt(solicitarEntrada("Escolha uma opção: "));
        
        switch (escolha) {
            case 1:
                int novoNumero = Integer.parseInt(solicitarEntrada("Digite o novo número do cartão: "));
                cartao.setNumeroCartao(novoNumero);
                break;
    
            case 2:
                String novaSenha = solicitarEntrada("Digite a nova senha do cartão: ");
                exceptions.isStringEmptyField(novaSenha);
                cartao.setSenha(novaSenha);
                break;
    
            case 3:
                System.out.println("1 - Ativar\n2 - Desativar");
                int status = Integer.parseInt(solicitarEntrada("Escolha: "));
                cartao.setAtivo(status == 1);
                break;
    
            case 4:
                if (cartao instanceof CartaoCredito) {
                    System.out.println("Alteração de tipo de cartão não é permitida diretamente.");
                    return;
                }
                System.out.println("1 - Cartão de Crédito\n2 - Cartão de Débito");
                int novoTipo = Integer.parseInt(solicitarEntrada("Escolha: "));
                
                Cartao novoCartao;
                if (novoTipo == 1) {
                    double novoLimite = Double.parseDouble(solicitarEntrada("Digite o novo limite de crédito: "));
                    novoCartao = new CartaoCredito(cartao.getNumeroCartao(), cartao.getSenha(), cartao.isAtivo(), novoLimite);
                } else {
                    double novoLimiteDiario = Double.parseDouble(solicitarEntrada("Digite o novo limite diário: "));
                    novoCartao = new CartaoDebito(cartao.getNumeroCartao(), cartao.getSenha(), cartao.isAtivo(), novoLimiteDiario);
                }
                
                novoCartao.setId(cartao.getId());
                cartaoDao.atualizarCartao(novoCartao);
                System.out.println("Cartão atualizado com sucesso!");
                return;
    
            case 5:
                System.out.println("Operação cancelada.");
                return;
    
            default:
                System.out.println("Opção inválida.");
                return;
        }
    
        cartaoDao.atualizarCartao(cartao);
        System.out.println("Cartão atualizado com sucesso!");
    }
    
    // Remover um cartão pelo ID
    public void removerCartao() {
        int escolhaId = Integer.parseInt(solicitarEntrada("Qual o ID do cartao buscado: "));
        System.out.println("Tem certeza que deseja desligar esse cartao? (S/N)");
        String confirmacao = sc.nextLine().trim().toUpperCase();
    
        if (confirmacao.equals("S")) {
            cartaoDao.removerCartao(escolhaId);;
            System.out.println("Cliente desligado com sucesso!");
        } else {
            System.out.println("Operação cancelada.");
        }
    }

    public void efetuadoPagamento() {
        double valorPagamento = Double.parseDouble(solicitarEntrada("Qual o valor do pagamento: "));
        
        int idCartao = Integer.parseInt(solicitarEntrada("Digite o ID do cartão: "));
        
        Cartao cartao = cartaoDao.buscarCartaoPorId(idCartao);
        
        if (cartao == null) {
            System.out.println("Erro: Cartão não encontrado.");
            return;
        }
    
        cartao.efetuarPagamento(valorPagamento);
    }
    
    //-------------------------------

    // Método para alterar a senha do cartão
    public void alterarSenhaCartao() {
        try {
            int idCartao = Integer.parseInt(solicitarEntrada("Digite o ID do cartão para alterar a senha: "));
            Cartao cartao = cartaoDao.buscarCartaoPorId(idCartao);
    
            if (cartao == null) {
                System.out.println("Cartão não encontrado.");
                return;
            }
    
            String novaSenha = solicitarEntrada("Digite a nova senha: ");
            exceptions.isStringEmptyField(novaSenha);
            cartao.alterarSenha(novaSenha); // Passando a senha como parâmetro
            cartaoDao.atualizarCartao(cartao);
            System.out.println("Senha alterada com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("Erro: ID inválido. Digite um número válido.");
        }
    }
    

    // Método para ativar ou desativar um cartão
    public void ativarDesativarCartao() {
        try {
            int idCartao = Integer.parseInt(solicitarEntrada("Digite o ID do cartão para ativar/desativar: "));
            Cartao cartao = cartaoDao.buscarCartaoPorId(idCartao);
    
            if (cartao == null) {
                System.out.println("Cartão não encontrado.");
                return;
            }
    
            cartao.ativarDesativar();
            cartaoDao.atualizarCartao(cartao);
            System.out.println("Status do cartão alterado com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("Erro: ID inválido. Digite um número válido.");
        }
    }
 }
