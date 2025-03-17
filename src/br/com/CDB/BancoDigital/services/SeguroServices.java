package br.com.CDB.BancoDigital.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;

import br.com.CDB.BancoDigital.dao.CartaoDao;
import br.com.CDB.BancoDigital.dao.SeguroDao;
import br.com.CDB.BancoDigital.entity.cartao.Cartao;
import br.com.CDB.BancoDigital.entity.cliente.CategoriaCliente;
import br.com.CDB.BancoDigital.entity.seguro.Seguro;
import br.com.CDB.BancoDigital.entity.seguro.SeguroFraude;
import br.com.CDB.BancoDigital.entity.seguro.SeguroViagem;
import br.com.CDB.BancoDigital.exceptions.Exceptions;

public class SeguroServices {
    private SeguroDao seguroDao = SeguroDao.getInstance();
    private CartaoDao cartaoDao = CartaoDao.getInstance();
    private Exceptions exceptions = new Exceptions();
    private Scanner sc;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public SeguroServices() {
        this.sc  = new Scanner(System.in).useLocale(Locale.US);
    }

    // Método para solicitar entrada do usuário
    private String solicitarEntrada(String mensagem) {
        System.out.println(mensagem);
        return sc.nextLine().trim();
    }

    // Método para solicitar e validar a data de contratação do seguro
    private LocalDate solicitarDataContratacao() {
        String dataStr = solicitarEntrada("Insira a data de contratação (dd/MM/yyyy): ");
        exceptions.isStringEmptyField(dataStr);
        try {
            LocalDate data = LocalDate.parse(dataStr, FORMATTER);
            if (data.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("Data de contratação não pode ser futura.");
            }
            return data;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de data inválido. Use dd/MM/yyyy");
        }
    }

    // Método principal para registrar um seguro
    public void registrarSeguro() {
        // Verifica se há algum cartão cadastrado
        if (cartaoDao.getTotalCartao() == 0) {
            System.out.println("Nenhum cartão cadastrado. Não é possível registrar um seguro.");
            return;
        }

        // Solicita o ID do cartão
        int idCartao = Integer.parseInt(solicitarEntrada("Digite o ID do cartão associado: "));
        Cartao cartao = cartaoDao.buscarCartaoPorId(idCartao);
        if (cartao == null) {
            System.out.println("Erro: Cartão não encontrado.");
            return;
        }

        // Solicita informações comuns para o seguro
        String numeroApolice = solicitarEntrada("Digite o número da apólice: ");
        exceptions.isStringEmptyField(numeroApolice);
        LocalDate dataContratacao = solicitarDataContratacao();
        double valorApolice = Double.parseDouble(solicitarEntrada("Digite o valor da apólice: "));

        // Solicita o tipo de seguro a ser contratado
        System.out.println("""
                Insira o tipo de seguro:
                1 - Seguro Fraude (Cobertura automática com valor base de R$ 5.000,00)
                2 - Seguro Viagem (Grátis para clientes PREMIUM; R$ 50,00/mês para COMUM e SUPER)
                """);
        int tipoSeguro = Integer.parseInt(solicitarEntrada("Escolha: "));

        Seguro seguro = null;
        switch (tipoSeguro) {
            case 1:
                // Seguro Fraude: a taxa pode ser definida como, por exemplo, 0 ou algum valor fixo (aqui usamos 0 para indicar cobertura automática)
                seguro = new SeguroFraude(0, numeroApolice, dataContratacao, valorApolice, cartao, 0.0);
                break;
            case 2:
                // Seguro Viagem: verifica a categoria do cliente para definir a taxa
                System.out.println("Digite a categoria do cliente:");
                System.out.println("1 - COMUM");
                System.out.println("2 - SUPER");
                System.out.println("3 - PREMIUM");
                int escolha = Integer.parseInt(solicitarEntrada("Escolha: "));
                CategoriaCliente categoria;
                switch (escolha) {
                    case 1: categoria = CategoriaCliente.COMUM; break;
                    case 2: categoria = CategoriaCliente.SUPER; break;
                    case 3: categoria = CategoriaCliente.PREMIUM; break;
                    default: 
                        System.out.println("Opção inválida. Definindo como COMUM por padrão.");
                        categoria = CategoriaCliente.COMUM; 
                }
                double taxaDeSeguroMensal = (categoria == CategoriaCliente.PREMIUM) ? 0.0 : 50.0;
                seguro = new SeguroViagem(0, numeroApolice, dataContratacao, valorApolice, cartao, taxaDeSeguroMensal);
                break;
            default:
                System.out.println("Tipo de seguro inválido.");
                return;
        }

        // Registra o seguro no DAO (o método registra e associa o seguro ao cartão)
        seguroDao.registrarSeguroCartao(seguro);
        System.out.println("Seguro registrado com sucesso!");
    }

    // Método para listar todos os seguros
    public void listarSeguros() {
      System.out.println(seguroDao.listarSeguros());
    }
    
    // Método para buscar um seguro pelo ID
    public void buscarSeguro() {
        int id;
        try {
            id = Integer.parseInt(solicitarEntrada("Digite o ID do seguro: "));
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }
        Seguro seguro = seguroDao.buscarSeguroPorId(id);
        if (seguro == null) {
            System.out.println("Seguro não encontrado.");
        } else {
            System.out.println("Seguro encontrado: ");
            System.out.println(seguro);
        }
    }

    public void atualizarSeguro() {
        int id;
        try {
            id = Integer.parseInt(solicitarEntrada("Digite o ID do seguro que deseja atualizar: "));
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }
        
        Seguro seguro = seguroDao.buscarSeguroPorId(id);
        if (seguro == null) {
            System.out.println("Seguro não encontrado.");
            return;
        }
        
        System.out.println("O que deseja atualizar?");
        System.out.println("1 - Número da Apólice");
        System.out.println("2 - Data de Contratação");
        System.out.println("3 - Valor da Apólice");
        
        // Se for seguro de fraude ou viagem, permite atualizar a taxa
        if (seguro instanceof SeguroFraude) {
            System.out.println("4 - Taxa de Seguro (Fraude)");
        } else if (seguro instanceof SeguroViagem) {
            System.out.println("4 - Taxa de Seguro Mensal (Viagem)");
        }
        System.out.println("5 - Cancelar");
        
        int escolha;
        try {
            escolha = Integer.parseInt(solicitarEntrada("Escolha uma opção: "));
        } catch (NumberFormatException e) {
            System.out.println("Opção inválida.");
            return;
        }
        
        switch (escolha) {
            case 1:
                String novoNumeroApolice = solicitarEntrada("Digite o novo número da apólice: ");
                seguro.setNumeroAPolice(novoNumeroApolice);
                break;
            case 2:
                LocalDate novaData;
                try {
                    novaData = LocalDate.parse(solicitarEntrada("Digite a nova data de contratação (dd/MM/yyyy): "), FORMATTER);
                } catch (DateTimeParseException e) {
                    System.out.println("Data inválida.");
                    return;
                }
                seguro.setDataContratacao(novaData);
                break;
            case 3:
                double novoValor;
                try {
                    novoValor = Double.parseDouble(solicitarEntrada("Digite o novo valor da apólice: "));
                } catch (NumberFormatException e) {
                    System.out.println("Valor inválido.");
                    return;
                }
                seguro.setValorApolice(novoValor);
                break;
            case 4:
                if (seguro instanceof SeguroFraude) {
                    double novaTaxaFraude;
                    try {
                        novaTaxaFraude = Double.parseDouble(solicitarEntrada("Digite a nova taxa de seguro fraude: "));
                    } catch (NumberFormatException e) {
                        System.out.println("Valor inválido.");
                        return;
                    }
                    ((SeguroFraude) seguro).taxaDeSeguro = novaTaxaFraude;
                } else if (seguro instanceof SeguroViagem) {
                    double novaTaxaViagem;
                    try {
                        novaTaxaViagem = Double.parseDouble(solicitarEntrada("Digite a nova taxa de seguro viagem (mensal): "));
                    } catch (NumberFormatException e) {
                        System.out.println("Valor inválido.");
                        return;
                    }
                    ((SeguroViagem) seguro).taxaDeSeguroMensal = novaTaxaViagem;
                } else {
                    System.out.println("Tipo de seguro não suportado para atualização da taxa.");
                    return;
                }
                break;
            case 5:
                System.out.println("Operação cancelada.");
                return;
            default:
                System.out.println("Opção inválida.");
                return;
        }
        
        // Atualiza o seguro no DAO
        boolean atualizado = seguroDao.atualizarCartao(seguro);
        if (atualizado) {
            System.out.println("Seguro atualizado com sucesso!");
        } else {
            System.out.println("Falha na atualização do seguro.");
        }
    }

    // Método para remover um seguro pelo ID
    public void removerSeguro() {
        int id;
        try {
            id = Integer.parseInt(solicitarEntrada("Digite o ID do seguro a ser removido: "));
            seguroDao.removerSeguroPorId(id);
            System.out.println("Seguro Desligado");
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }
    }

    public void contratarSeguro() {
        int id;
        try {
            id = Integer.parseInt(solicitarEntrada("Digite o ID do seguro para contratar: "));
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }
        
        Seguro seguro = seguroDao.buscarSeguroPorId(id);
        if (seguro == null) {
            System.out.println("Seguro não encontrado.");
            return;
        }
        
        // Agora chama o método contratarSeguro() no seguro encontrado
        seguro.contratarSeguro();
    }
    
    public void gerarApolice() {
        int id;
        try {
            id = Integer.parseInt(solicitarEntrada("Digite o ID do seguro para gerar a apólice: "));
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }
        
        Seguro seguro = seguroDao.buscarSeguroPorId(id);
        if (seguro == null) {
            System.out.println("Seguro não encontrado.");
            return;
        }
        
        // Chama o método gerarApolice() no seguro encontrado
        seguro.gerarApolice();
    }
    
}
