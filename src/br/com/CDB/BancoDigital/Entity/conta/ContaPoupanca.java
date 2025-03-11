package br.com.CDB.BancoDigital.Entity.conta;

import java.math.BigDecimal;
import java.math.MathContext;

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
    public void transferirDinheiro(Conta destino, BigDecimal valor) {
       if (saldo.compareTo(valor) >= 0) {
        saldo = saldo.subtract(valor);
        destino.saldo = destino.saldo.add(valor);
        System.out.println("Transferência de R$ " + valor + " realizada.");  
       } else {
        System.out.println("Saldo insuficiente.");
    }
    }
    

    @Override
    public void calcularTaxaOuRendimento() {
        BigDecimal saldoAtual = getSaldo();
        BigDecimal taxaAnual = BigDecimal.valueOf(getTaxaDeRendimentoAnual());

        // Calcula a taxa mensal corretamente
        BigDecimal taxaMensal = taxaAnual.divide(BigDecimal.valueOf(12), MathContext.DECIMAL128);
        BigDecimal rendimento = saldoAtual.multiply(taxaMensal);
        
        // Atualiza o saldo com o rendimento
        setSaldo(saldoAtual.add(rendimento));

        System.out.printf("Rendimento de %.2f%% aplicado. Novo saldo: %.2f%n",
                        getTaxaDeRendimentoAnual(), getSaldo());
    }

    public double getTaxaDeRendimentoAnual() {
        return taxaDeRendimentoAnual;
    }

    public void setTaxaDeRendimentoAnual(double taxaDeRendimentoAnual) {
        this.taxaDeRendimentoAnual = taxaDeRendimentoAnual;
    }

    
}
