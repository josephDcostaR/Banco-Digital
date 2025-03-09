package br.com.CDB.BancoDigital.Entity.seguro;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.CDB.BancoDigital.Entity.cartao.Cartao;

public class Seguro {
    private String numeroAPolice;
    private LocalDateTime dataContratacao;
    private Cartao cartaoCoberto;
    private BigDecimal valorApolice;


    public Seguro(String numeroAPolice, LocalDateTime dataContratacao, Cartao cartaoCoberto, BigDecimal valorApolice) {
        this.numeroAPolice = numeroAPolice;
        this.dataContratacao = dataContratacao;
        this.cartaoCoberto = cartaoCoberto;
        this.valorApolice = valorApolice;
    }


    public String getNumeroAPolice() {
        return numeroAPolice;
    }


    public void setNumeroAPolice(String numeroAPolice) {
        this.numeroAPolice = numeroAPolice;
    }


    public LocalDateTime getDataContratacao() {
        return dataContratacao;
    }


    public void setDataContratacao(LocalDateTime dataContratacao) {
        this.dataContratacao = dataContratacao;
    }


    public Cartao getCartaoCoberto() {
        return cartaoCoberto;
    }


    public void setCartaoCoberto(Cartao cartaoCoberto) {
        this.cartaoCoberto = cartaoCoberto;
    }


    public BigDecimal getValorApolice() {
        return valorApolice;
    }


    public void setValorApolice(BigDecimal valorApolice) {
        this.valorApolice = valorApolice;
    }

    

    

}
