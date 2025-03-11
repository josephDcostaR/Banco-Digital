package br.com.CDB.BancoDigital.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.CDB.BancoDigital.Entity.conta.Conta;

public class ContaDao {

    List<Conta> listaDeContas = new ArrayList<>();
    private int idCounter = 1;

    public void registrarContaCliente(Conta conta) {
        conta.setIdCliente(idCounter++);

        if(listaDeContas.size() < 2) {
            listaDeContas.add(conta);
        }else {
            System.out.println("O cliente já possui o número máximo de contas permitidas.");
        }
 
        // Associa a conta ao cliente (se ainda não estiver associada)
        if (conta.getClienteAssociado() != null) {
            conta.getClienteAssociado().adicionarConta(conta);
        }
    }

    public int getTotalContas() {
        return listaDeContas.size();
    }
    

    public List<Conta> getContas() {
        return listaDeContas;
    }

    public Conta buscarContaPorId(int id) {
        for (Conta conta : listaDeContas) {
            if (conta.getIdCliente() == id) {
                return conta;
            }
        }
        return null;
    }

    public void listarContas() {
        if (listaDeContas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
        } else {
            for(Conta conta : listaDeContas) {
                conta.exibirSaldo();
            }
        }
    }

}
