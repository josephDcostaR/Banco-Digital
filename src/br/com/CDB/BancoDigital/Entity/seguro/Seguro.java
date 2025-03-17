package br.com.CDB.BancoDigital.entity.seguro;


import java.time.LocalDate;

import br.com.CDB.BancoDigital.entity.cartao.Cartao;

public abstract class Seguro {
    protected int id;
    protected String numeroAPolice;
    protected LocalDate dataContratacao;
    protected double valorApolice;
    protected Cartao cartaoAssociado;
    protected boolean ativo;

    public Seguro(){
        this.ativo = true;
    }
    
    public Seguro(int id,String numeroAPolice, LocalDate dataContratacao,  double valorApolice, Cartao cartaoAssociado) {
        this.numeroAPolice = numeroAPolice;
        this.dataContratacao = dataContratacao;
        this.valorApolice = valorApolice;
        if(cartaoAssociado != null) {
            cartaoAssociado.adicionarSeguro(this);
        }
    }


    public void inativar() {
        this.ativo = false;
    }

    public abstract void contratarSeguro();
    public abstract void gerarApolice();    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cartao getCartaoAssociado() {
        return cartaoAssociado;
    }

    public void setCartaoAssociado(Cartao cartaoAssociado) {
        this.cartaoAssociado = cartaoAssociado;
    }

    public String getNumeroAPolice() {
        return numeroAPolice;
    }

    public void setNumeroAPolice(String numeroAPolice) {
        this.numeroAPolice = numeroAPolice;
    }


    public LocalDate getDataContratacao() {
        return dataContratacao;
    }


    public void setDataContratacao(LocalDate dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public double getValorApolice() {
        return valorApolice;
    }


    public void setValorApolice(double valorApolice) {
        this.valorApolice = valorApolice;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        String cartaoInfo = (cartaoAssociado != null) ? Integer.toString(cartaoAssociado.getNumeroCartao()) : "N/A";
        return "Seguro [id=" + id + ", numeroAPolice=" + numeroAPolice + ", dataContratacao=" + dataContratacao
                + ", valorApolice=" + valorApolice + ", Numero do Cartao=" + cartaoInfo + "]";
    }

}
