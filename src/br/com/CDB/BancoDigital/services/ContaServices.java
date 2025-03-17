package br.com.CDB.BancoDigital.services;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

import br.com.CDB.BancoDigital.dao.ClienteDao;
import br.com.CDB.BancoDigital.dao.ContaDao;
import br.com.CDB.BancoDigital.entity.cliente.Cliente;
import br.com.CDB.BancoDigital.entity.conta.Conta;
import br.com.CDB.BancoDigital.entity.conta.ContaCorrente;
import br.com.CDB.BancoDigital.entity.conta.ContaPoupanca;

public class ContaServices {
    ContaDao contaDao = ContaDao.getInstance();
    ClienteDao clienteDao = ClienteDao.getInstance();
    private Scanner sc;

    public ContaServices() {
        this.sc = new Scanner(System.in).useLocale(Locale.US);
    }

    private String solicitarEntrada(String mensagem) {
        System.out.println(mensagem);
        return sc.nextLine().trim();
    }

    public void cadastrarConta() {
        System.out.println("=== Cadastro de Conta ===");

        //Verificar se há clientes cadastrados
        if(clienteDao.getTotalClientes() == 0) {
            System.out.println("Nenhum cliente cadastrado. Não é possível abrir uma conta.");
            return;
        }

        int idCliente = Integer.parseInt(solicitarEntrada("Digite o ID do cliente associado: "));
        Cliente cliente = clienteDao.encontraClientePorId(idCliente);
        
        if(cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.println("""
                Tipo de conta:
                1 - Conta Corrente
                2 - Conta Poupança 
                """);
        
        int tipoConta = Integer.parseInt(solicitarEntrada("Insira: "));
        int numeroDaConta = Integer.parseInt(solicitarEntrada("Digite o número da conta: "));
        BigDecimal saldo = new BigDecimal(solicitarEntrada("Digite o saldo inicial: "));

        Conta conta;
        if (tipoConta == 1) {
            double taxaDeManutencao = switch (cliente.getCategoria()) {
                case COMUM -> 12.00;
                case SUPER -> 8.00;
                case PREMIUM -> 0.00;
            };
            conta = new ContaCorrente(idCliente, numeroDaConta, saldo, cliente, taxaDeManutencao);
        } else if(tipoConta == 2) {
            double taxaDeRendimentoAnual = switch (cliente.getCategoria()) {
                case COMUM -> 0.005;  // 0,5%
                case SUPER -> 0.007;  // 0,7%
                case PREMIUM -> 0.009; // 0,9%
            };
            conta = new ContaPoupanca(idCliente, numeroDaConta, saldo, cliente, taxaDeRendimentoAnual);
        } else {
            System.out.println("Tipo de conta inválido.");
            return;
        }
        contaDao.registrarContaCliente(conta);
        System.out.println("Conta cadastrada com sucesso!");
    }

    public void buscarConta() {
        int escolhaId = Integer.parseInt(solicitarEntrada("Qual o ID da conta buscada: "));
        System.out.println(contaDao.buscarContaPorId(escolhaId));
    }

    public void exibirContas() {
        System.out.println(contaDao.listarTodasAsContas());
    }

     // Método para atualizar uma conta
     public void atualizarConta() {
        int idConta;
        try {
            idConta = Integer.parseInt(solicitarEntrada("Digite o ID da conta que deseja atualizar: "));
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }
        
        Conta conta = contaDao.buscarContaPorId(idConta);
        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }
        
        System.out.println("O que deseja atualizar?");
        System.out.println("1 - Número da conta");
        System.out.println("2 - Saldo");
        System.out.println("3 - Cancelar");
        
        int opcao;
        try {
            opcao = Integer.parseInt(solicitarEntrada("Escolha uma opção: "));
        } catch (NumberFormatException e) {
            System.out.println("Opção inválida.");
            return;
        }
        
        switch (opcao) {
            case 1:
                try {
                    int novoNumero = Integer.parseInt(solicitarEntrada("Digite o novo número da conta: "));
                    conta.setNumeroDaConta(novoNumero);
                } catch (NumberFormatException e) {
                    System.out.println("Número de conta inválido.");
                    return;
                }
                break;
            case 2:
                try {
                    BigDecimal novoSaldo = new BigDecimal(solicitarEntrada("Digite o novo saldo da conta: "));
                    conta.setSaldo(novoSaldo);
                } catch (NumberFormatException e) {
                    System.out.println("Saldo inválido.");
                    return;
                }
                break;
            case 3:
                System.out.println("Operação cancelada.");
                return;
            default:
                System.out.println("Opção inválida.");
                return;
        }
        
        boolean atualizado = contaDao.atualizarConta(conta);
        if (atualizado) {
            System.out.println("Conta atualizada com sucesso!");
        } else {
            System.out.println("Falha ao atualizar a conta.");
        }
    }


    
    public void deletarConta() {
        int escolhaId = Integer.parseInt(solicitarEntrada("Qual o ID da conta buscada: "));
        clienteDao.removerCliente(escolhaId);
        System.out.println("Conta desliga com sucesso!");
    }

    public void exibirSaldos() {
        contaDao.revelarSaldoDaConta();
    }

    public void simularMes() {
        System.out.println("Simulando passagem de um mês...");
        for(Conta conta : contaDao.getContas() ) 
        conta.calcularTaxaOuRendimento();
    }

    public void fecharScanner() {
        sc.close();
    }


}
