package br.com.CDB.BancoDigital.entity.conta;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.CDB.BancoDigital.entity.cartao.Cartao;
import br.com.CDB.BancoDigital.entity.cliente.Cliente;

public abstract class Conta {
    protected int IdCliente;
    protected int numeroDaConta;
    protected BigDecimal saldo;
    protected Cliente clienteAssociado;
    protected List<Cartao> cartoes;
    protected boolean ativa;

    public Conta(){
        this.ativa = true;
    }

    public Conta(int IdCliente, int numeroDaConta, BigDecimal saldo, Cliente clienteAssociado) {
        this.IdCliente = IdCliente;
        this.numeroDaConta = numeroDaConta;
        this.saldo = saldo;
        this.clienteAssociado = clienteAssociado;
        this.cartoes = new ArrayList<>();
    }

    //Cria a relaçao Conta 1.1 Cliente
    public void associarAoCliente(Cliente cliente) {
        this.clienteAssociado = cliente;
        //Evitaando duplicatas
        if (!cliente.getContas().contains(this)) {
            cliente.adicionarConta(this);
        }
    }

    //Cria a relaçao Conta 1.* Cartao
    public void adicionarCartao(Cartao cartao) {
        if (cartao != null && !cartoes.contains(cartao)) {
            cartoes.add(cartao);
        }
    }

    //Desliga a conta e os cartões relacionados a ela
    public void inativar() {
        this.ativa = false;
        for(Cartao cartao : cartoes){
            cartao.inativar();
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
