package br.com.CDB.BancoDigital.Entity.seguro;


import java.time.LocalDate;
import br.com.CDB.BancoDigital.Entity.cartao.Cartao;

public abstract class Seguro {
    protected int id;
    protected String numeroAPolice;
    protected LocalDate dataContratacao;
    protected double valorApolice;
    protected Cartao cartaoAssociado;

    public Seguro(){}
    
    public Seguro(int id,String numeroAPolice, LocalDate dataContratacao,  double valorApolice, Cartao cartaoAssociado) {
        this.numeroAPolice = numeroAPolice;
        this.dataContratacao = dataContratacao;
        this.valorApolice = valorApolice;
        if(cartaoAssociado != null) {
            cartaoAssociado.adicionarSeguro(this);;
         }
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

    @Override
    public String toString() {
        String cartaoInfo = (cartaoAssociado != null) ? Integer.toString(cartaoAssociado.getNumeroCartao()) : "N/A";
        return "Seguro [id=" + id + ", numeroAPolice=" + numeroAPolice + ", dataContratacao=" + dataContratacao
                + ", valorApolice=" + valorApolice + ", cartaoAssociado=" + cartaoInfo + "]";
    }

    

}
