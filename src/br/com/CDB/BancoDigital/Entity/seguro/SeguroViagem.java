package br.com.CDB.BancoDigital.Entity.seguro;

import java.time.LocalDate;


import br.com.CDB.BancoDigital.Entity.cartao.Cartao;

public class SeguroViagem extends Seguro {

    public double taxaDeSeguroMensal;

    public SeguroViagem(int id, String numeroAPolice, LocalDate dataContratacao, 
        double valorApolice, Cartao cartaoAssociado, double taxaDeSeguroMensal) {
        super(id, numeroAPolice, dataContratacao,  valorApolice, cartaoAssociado);
        this.taxaDeSeguroMensal = taxaDeSeguroMensal;
    }

    
    @Override
    public void contratarSeguro() {
        // Implementação simplificada para contratação do seguro viagem
        System.out.println("Seguro viagem contratado para o cartão: " + cartaoAssociado.getNumeroCartao());
        // Aqui, você pode adicionar lógica para registrar a contratação no sistema,
        // como salvar em um banco de dados ou notificar outras partes interessadas.
    }

    @Override
    public void gerarApolice() {
        // Implementação simplificada para geração de apólice de seguro viagem
        System.out.println("Apólice de seguro viagem gerada:");
        System.out.println("Número da Apólice: " + numeroAPolice);
        System.out.println("Data de Contratação: " + dataContratacao);
        System.out.println("Cartão Coberto: " + cartaoAssociado.getNumeroCartao());
        System.out.println("Valor da Apólice: " + valorApolice);
        System.out.println("Taxa de Seguro Mensal: " + taxaDeSeguroMensal);
        // Aqui, você pode adicionar lógica para gerar um documento de apólice,
        // como criar um arquivo PDF ou enviar detalhes por e-mail ao cliente.
    }


}
