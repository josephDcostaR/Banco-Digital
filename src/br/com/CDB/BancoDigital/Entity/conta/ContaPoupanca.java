package br.com.CDB.BancoDigital.entity.conta;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;

import br.com.CDB.BancoDigital.entity.cliente.Cliente;

public class ContaPoupanca extends Conta {

    public double taxaDeRendimentoAnual;

    public ContaPoupanca(int Id, int numeroDaConta, BigDecimal saldo, Cliente clienteAssociado, double taxaDeRendimentoAnual) {
        super(Id, numeroDaConta, saldo, clienteAssociado);
        this.taxaDeRendimentoAnual = taxaDeRendimentoAnual;
    }

    @Override
    public void exibirSaldo() {
    DecimalFormat df = new DecimalFormat("#,##0.00");
    System.out.println("Número da conta poupanca: " + getNumeroDaConta() + 
                       ", Saldo da conta: R$ " + df.format(getSaldo()));
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

        BigDecimal taxaMensal = taxaAnual.divide(BigDecimal.valueOf(12), MathContext.DECIMAL128);
        BigDecimal rendimento = saldoAtual.multiply(taxaMensal);
        
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
