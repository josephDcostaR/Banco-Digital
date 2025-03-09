package br.com.CDB.BancoDigital.Entity.conta;

import java.math.BigDecimal;

import br.com.CDB.BancoDigital.Entity.cliente.Cliente;

public class ContaCorrente extends Conta {

    public double taxaDeManutencao;

    public ContaCorrente(int Id,int numeroDaConta, BigDecimal saldo, Cliente clienteAssociado,double taxaDeManutencao) {
        super(Id, numeroDaConta, saldo, clienteAssociado);
        this.taxaDeManutencao = taxaDeManutencao;
        
    }

    @Override
    public void exibirSaldo() {
        System.out.println("Saldo da Conta Corrente " + getNumeroDaConta() + ": R$  " + getSaldo());
    }

    @Override
    public void transferirDinheiro() {
        System.out.println("Transferência via Pix ainda não implementada.");
    }

    @Override
    public void calcularTaxaOuRendimento() {
        BigDecimal saldoAtual = getSaldo();
        BigDecimal taxa = BigDecimal.valueOf(taxaDeManutencao);
        BigDecimal novoSaldo = saldoAtual.subtract(taxa);
        setSaldo(novoSaldo);
        System.out.println("Taxa de manutenção de " + taxaDeManutencao + "descontada. Novo saldo: " + novoSaldo);
    }

    public double getTaxaDeManutencao() {
        return taxaDeManutencao;
    }

    public void setTaxaDeManutencao(double taxaDeManutencao) {
        this.taxaDeManutencao = taxaDeManutencao;
    }

  

    

}
