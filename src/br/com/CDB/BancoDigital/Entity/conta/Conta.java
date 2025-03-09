package br.com.CDB.BancoDigital.Entity.conta;

import java.math.BigDecimal;

import br.com.CDB.BancoDigital.Entity.cliente.Cliente;

public abstract class Conta {

    private int Id;
    private int numeroDaConta;
    private BigDecimal saldo;
    private Cliente clienteAssociado;

    public Conta(int Id, int numeroDaConta, BigDecimal saldo, Cliente clienteAssociado) {
        this.Id = Id;
        this.numeroDaConta = numeroDaConta;
        this.saldo = saldo;
        this.clienteAssociado = clienteAssociado;
    }

    public void associarAoCliente(Cliente cliente) {
        this.clienteAssociado = cliente;
        cliente.adicionarConta(this);
    }

    public abstract void calcularTaxaOuRendimento();
    public abstract void exibirSaldo();
    public abstract void transferirDinheiro();

    public int getNumeroDaConta() {
        return numeroDaConta;
    }


    public void setNumeroDaConta(int numeroDaConta) {
        this.numeroDaConta = numeroDaConta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }


    public void setSaldo(BigDecimal novoSaldo) {
        this.saldo = novoSaldo;
    }


    public Cliente getClienteAssociado() {
        return clienteAssociado;
    }


    public void setClienteAssociado(Cliente clienteAssociado) {
        this.clienteAssociado = clienteAssociado;
    }


    public int getId() {
        return Id;
    }


    public void setId(int id) {
        Id = id;
    }

    
}
