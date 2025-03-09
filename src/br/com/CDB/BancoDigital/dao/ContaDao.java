package br.com.CDB.BancoDigital.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.CDB.BancoDigital.Entity.conta.Conta;

public class ContaDao {

    List<Conta> listaDeContas = new ArrayList<>();

    public void registrarContaCliente(Conta conta) {
        conta.setId(listaDeContas.size() + 1);
        listaDeContas.add(conta);
        // Associa a conta ao cliente (se ainda não estiver associada)
        if (conta.getClienteAssociado() != null) {
            conta.getClienteAssociado().adicionarConta(conta);
        }
    }

    public List<Conta> geContas() {
        return listaDeContas;
    }

    public Conta buscarContaPorId(int id) {
        for (Conta conta : listaDeContas) {
            if (conta.getId() == id) {
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
