package br.com.CDB.BancoDigital.entity.seguro;

import java.time.LocalDate;

import br.com.CDB.BancoDigital.entity.cartao.Cartao;

public class SeguroFraude extends Seguro {

    public double taxaDeSeguro;

    public SeguroFraude(int id, String numeroAPolice, LocalDate dataContratacao, 
            double valorApolice, Cartao cartaoAssociado, double taxaDeSeguro) {
        super(id, numeroAPolice, dataContratacao, valorApolice, cartaoAssociado);
        this.taxaDeSeguro = taxaDeSeguro;
    }

    @Override
    public void contratarSeguro() {
        // Implementação simplificada para contratação do seguro contra fraude
        System.out.println("Seguro contra fraude contratado para o cartão: " + cartaoAssociado.getNumeroCartao());
        // Aqui, você pode adicionar lógica para registrar a contratação no sistema,
        // como salvar em um banco de dados ou notificar outras partes interessadas.
    }

    @Override
    public void gerarApolice() {
        // Implementação simplificada para geração de apólice de seguro contra fraude
        System.out.println("Apólice de seguro contra fraude gerada:");
        System.out.println("Número da Apólice: " + numeroAPolice);
        System.out.println("Data de Contratação: " + dataContratacao);
        System.out.println("Cartão Coberto: " + cartaoAssociado.getNumeroCartao());
        System.out.println("Valor da Apólice: " + valorApolice);
        System.out.println("Taxa de Seguro: " + taxaDeSeguro);
        // Aqui, você pode adicionar lógica para gerar um documento de apólice,
        // como criar um arquivo PDF ou enviar detalhes por e-mail ao cliente.
    }

}
