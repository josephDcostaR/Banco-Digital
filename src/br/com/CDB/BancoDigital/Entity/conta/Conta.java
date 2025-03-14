package br.com.CDB.BancoDigital.Entity.conta;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.CDB.BancoDigital.Entity.cartao.Cartao;
import br.com.CDB.BancoDigital.Entity.cliente.Cliente;

public abstract class Conta {

    protected int IdCliente;
    protected int numeroDaConta;
    protected BigDecimal saldo;
    protected Cliente clienteAssociado;
    protected List<Cartao> cartoes;

    public Conta(){}

    public Conta(int IdCliente, int numeroDaConta, BigDecimal saldo, Cliente clienteAssociado) {
        this.IdCliente = IdCliente;
        this.numeroDaConta = numeroDaConta;
        this.saldo = saldo;
        this.clienteAssociado = clienteAssociado;
        this.cartoes = new ArrayList<>();
    }

    public void associarAoCliente(Cliente cliente) {
        this.clienteAssociado = cliente;
        //Evitaando duplicatas
        if (!cliente.getContas().contains(this)) {
            cliente.adicionarConta(this);
        }
    }

    public void adicionarCartao(Cartao cartao) {
        if (cartao != null && !cartoes.contains(cartao)) {
            cartoes.add(cartao);
        }
    }

    public abstract void calcularTaxaOuRendimento();
    public abstract void exibirSaldo();
    public abstract void transferirDinheiro(Conta destino, BigDecimal valor);

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
    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int idCliente) {
        IdCliente = idCliente;
    }

    public List<Cartao> getCartoes() {
        return cartoes;
    }

    public void setCartoes(List<Cartao> cartoes) {
        this.cartoes = cartoes;
    }

    

    @Override
    public String toString() {
        String clienteInfo = (clienteAssociado != null) ? clienteAssociado.getNome() : "N/A";
        return "Conta [Id=" + IdCliente + ", numeroDaConta=" + numeroDaConta + ", saldo=" + saldo + ", clienteAssociado=" + clienteInfo + "]";
    }

   
}
