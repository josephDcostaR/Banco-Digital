package br.com.CDB.BancoDigital.Entity.cartao;

import java.util.List;

import br.com.CDB.BancoDigital.Entity.conta.Conta;
import br.com.CDB.BancoDigital.Entity.seguro.Seguro;;

public abstract class Cartao {

    protected int id;
    protected int numeroCartao;
    protected String senha;
    protected boolean status;
    protected Conta conta;
    protected List<Seguro> seguros;

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
    public abstract void efetuarPagamento(double valor);

    public void adicionarSeguro(Seguro seguro) {
        if (seguros != null && !seguros.contains(seguro)) {
            seguros.add(seguro);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public List<Seguro> getSeguros() {
        return seguros;
    }

    public void setSeguros(List<Seguro> seguros) {
        this.seguros = seguros;
    }

    @Override
    public String toString() {
        String conatInfo = (conta != null) ? Integer.toString(conta.getNumeroDaConta()) : "N/A";
        return "Cartao [id=" + id + ", numeroCartao=" + numeroCartao + ", senha=" + senha + ", status=" + status
                + ", Numero da conta=" + conatInfo + "]";
    }

    

}
