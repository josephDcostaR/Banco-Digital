package br.com.CDB.BancoDigital.entity.cartao;

import java.util.List;

import br.com.CDB.BancoDigital.entity.conta.Conta;
import br.com.CDB.BancoDigital.entity.seguro.Seguro;;

public abstract class Cartao {

    protected int id;
    protected int numeroCartao;
    protected String senha;
    protected boolean ativo;
    protected Conta conta;
    protected List<Seguro> seguros;

    public Cartao(int numeroCartao, String senha,  Conta conta, boolean ativo) {
            this.numeroCartao = numeroCartao;
            this.senha = senha;
            this.ativo = ativo;
            if(conta != null) {
            conta.adicionarCartao(this);
            }
    }
    
    public Cartao(int numeroCartao, String senha, boolean ativo) {
            this.numeroCartao = numeroCartao;
            this.senha = senha;
            this.ativo = ativo;
    }
    
    public abstract void alterarSenha(String novaSenha);
    public abstract void ativarDesativar();
    public abstract void efetuarPagamento(double valor);

    //Cria a relaçao Cartao 1.* Seguros
    public void adicionarSeguro(Seguro seguro) {
        if (seguros != null && !seguros.contains(seguro)) {
            seguros.add(seguro);
        }
    }

    public void inativarSeguros() {
        this.ativo = false;
        for(Seguro seguro : seguros){
            seguro.inativar();
        }
    }

    public void inativar() {
        this.ativo = false;
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
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
        return "Cartao [id=" + id + ", numeroCartao=" + numeroCartao + ", senha=" + senha + ", status=" + ativo
                + ", Numero da conta=" + conatInfo + "]";
    }

    

}
