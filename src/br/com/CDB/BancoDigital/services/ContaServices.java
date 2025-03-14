package br.com.CDB.BancoDigital.services;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

import br.com.CDB.BancoDigital.Entity.cliente.Cliente;
import br.com.CDB.BancoDigital.Entity.conta.Conta;
import br.com.CDB.BancoDigital.Entity.conta.ContaCorrente;
import br.com.CDB.BancoDigital.Entity.conta.ContaPoupanca;
import br.com.CDB.BancoDigital.dao.ClienteDao;
import br.com.CDB.BancoDigital.dao.ContaDao;

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
        sc.nextLine();
        Cliente cliente = clienteDao.encontraCliente(idCliente);
        
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
        sc.nextLine();
        int numeroDaConta = Integer.parseInt(solicitarEntrada("Digite o número da conta: "));
        sc.nextLine();
        BigDecimal saldo = new BigDecimal(solicitarEntrada("Digite o saldo inicial: "));
        sc.nextLine();

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

    public void exibirSaldos() {
        contaDao.listarContas();
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
