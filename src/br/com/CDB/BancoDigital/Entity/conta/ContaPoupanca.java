package br.com.CDB.BancoDigital.Entity.conta;

import java.math.BigDecimal;

import br.com.CDB.BancoDigital.Entity.cliente.Cliente;

public class ContaPoupanca extends Conta {

    public double taxaDeRendimentoAnual;

    public ContaPoupanca(int Id, int numeroDaConta, BigDecimal saldo, Cliente clienteAssociado, double taxaDeRendimentoAnual) {
        super(Id, numeroDaConta, saldo, clienteAssociado);
        this.taxaDeRendimentoAnual = taxaDeRendimentoAnual;
    }

    @Override
    public void exibirSaldo() {
        System.out.println("Saldo da Conta Poupança " + getNumeroDaConta() + ": R$" + getSaldo());
    }

    @Override
    public void transferirDinheiro() {
        System.out.println("Transferência via Pix ainda não implementada.");
    }

    @Override
    public void calcularTaxaOuRendimento() {
        BigDecimal saldoAtual = getSaldo(); // Mantém como BigDecimal
        BigDecimal taxaAnual = BigDecimal.valueOf(getTaxaDeRendimentoAnual()); // Converte double para BigDecimal
        @SuppressWarnings("deprecation")
        BigDecimal taxaMensal = taxaAnual.divide(BigDecimal.valueOf(12), 4, BigDecimal.ROUND_HALF_UP); // Taxa mensal
        BigDecimal rendimento = saldoAtual.multiply(taxaMensal); // Calcula rendimento
        BigDecimal novoSaldo = saldoAtual.add(rendimento); // Novo saldo
        setSaldo(novoSaldo); // Atualiza o saldo
        System.out.println("Rendimento de " + taxaDeRendimentoAnual + "descontada. Novo saldo: " + novoSaldo);
    }

    public double getTaxaDeRendimentoAnual() {
        return taxaDeRendimentoAnual;
    }

    public void setTaxaDeRendimentoAnual(double taxaDeRendimentoAnual) {
        this.taxaDeRendimentoAnual = taxaDeRendimentoAnual;
    }
}
