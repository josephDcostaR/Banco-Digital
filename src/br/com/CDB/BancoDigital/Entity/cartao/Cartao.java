package br.com.CDB.BancoDigital.Entity.cartao;


import br.com.CDB.BancoDigital.Entity.conta.Conta;

public abstract class Cartao {

    protected int id;
    protected int numeroCartao;
    protected String senha;
    protected boolean status;
    protected Conta conta;

    public Cartao(){}

    public Cartao(int numeroCartao, String senha, boolean status, Conta conta) {
        this.numeroCartao = numeroCartao;
        this.senha = senha;
        this.status = status;
        if(conta != null) {
           conta.adicionarCartao(this);
        }
    }
    
    public Cartao(int numeroCartao, String senha, boolean status) {
        this.numeroCartao = numeroCartao;
        this.senha = senha;
        this.status = status;
    }
    
    public abstract void alterarSenha(String novaSenha);
    public abstract void ativarDesativar();

    public int getNumeroCartao() {
        return numeroCartao;
    }
    public void setNumeroCartao(int numeroCartao) {
        this.numeroCartao = numeroCartao;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
