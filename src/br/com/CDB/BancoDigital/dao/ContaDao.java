package br.com.CDB.BancoDigital.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.CDB.BancoDigital.entity.conta.Conta;

public class ContaDao {

    private static ContaDao instance;  // Instância única da classe
    private List<Conta> listaDeContas; 
    private int idCounter = 1;  

    // Construtor privado impede a criação direta da classe
    private ContaDao() {
        this.listaDeContas = new ArrayList<>();
    }

    // Método para obter a instância única
    public static ContaDao getInstance() {
        if (instance == null) {
            instance = new ContaDao();
        }
        return instance;
    }

    public void registrarContaCliente(Conta conta) {
        conta.setIdCliente(idCounter++);
        
        if (listaDeContas.size() < 2) {
            listaDeContas.add(conta);
        } else {
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

    public Conta listarTodasAsContas() {
        verificarContas();
        for(Conta c : listaDeContas){
            return c;
        }
        return null;
    }

    public boolean atualizarConta(Conta contaAtualizada) {
        for (int i = 0; i < listaDeContas.size(); i++) {
            if (listaDeContas.get(i).getIdCliente() == contaAtualizada.getIdCliente()) {
                listaDeContas.set(i, contaAtualizada);
                return true;
            }
        }
        return false;
    }
    
    public void removerConta(int id) {
        verificarContas();
        Conta conta = buscarContaPorId(id);
        if (conta != null) {
            conta.inativar();
            System.out.println("conta desligada com sucesso!");
        }
    }

    public void revelarSaldoDaConta() {
        if (listaDeContas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
        } else {
            for (Conta conta : listaDeContas) {
                conta.exibirSaldo();
            }
        }
    }

    private void verificarContas() {
        if (listaDeContas.isEmpty()) {
            throw new IllegalStateException("Não há contas cadastrados!");
        }
    }
}
