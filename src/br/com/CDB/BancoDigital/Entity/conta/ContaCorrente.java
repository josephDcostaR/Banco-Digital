package br.com.CDB.BancoDigital.entity.conta;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import br.com.CDB.BancoDigital.entity.cliente.Cliente;

public class ContaCorrente extends Conta {

    public double taxaDeManutencao;

    public ContaCorrente(int Id,int numeroDaConta, BigDecimal saldo, Cliente clienteAssociado,double taxaDeManutencao) {
        super(Id, numeroDaConta, saldo, clienteAssociado);
        this.taxaDeManutencao = taxaDeManutencao;
        
    }

    @Override
    public void exibirSaldo() {
    DecimalFormat df = new DecimalFormat("#,##0.00");
    System.out.println("Número da conta corrente: " + getNumeroDaConta() + 
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
        BigDecimal taxa = BigDecimal.valueOf(taxaDeManutencao);
    
        if (saldoAtual.compareTo(taxa) >= 0) {
            setSaldo(saldoAtual.subtract(taxa));
            System.out.printf("Taxa de manutenção de R$%.2f descontada. Novo saldo: R$%.2f%n",
                              taxaDeManutencao, getSaldo());
        } else {
            System.out.println("Saldo insuficiente para descontar a taxa de manutenção.");
        }
    }
    

    public double getTaxaDeManutencao() {
        return taxaDeManutencao;
    }

    public void setTaxaDeManutencao(double taxaDeManutencao) {
        this.taxaDeManutencao = taxaDeManutencao;
    }

    
  

    

}
