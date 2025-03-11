package br.com.CDB.BancoDigital.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.CDB.BancoDigital.Entity.cartao.Cartao;

public class CartaoDao {

    List<Cartao> listaDeCartoes = new ArrayList<>();
    private int idCounter = 1; 

     public void registrarCartaoCliente(Cartao cartao) {
        cartao.setId(idCounter++);
        
        if (cartao.getConta() != null) {
            cartao.getConta().adicionarCartao(cartao);
        }
        
        listaDeCartoes.add(cartao);
        System.out.println("Cartão registrado com sucesso! ID: " + cartao.getId());
    }

    public Cartao buscarCartaoPorId(int id) {
        for(Cartao cartao : listaDeCartoes) {
            if (cartao.getId() == id) {
                return cartao;
            }
        }
        return null;
    }

     public void listarCartoes() {
        for(Cartao c : listaDeCartoes) {
            System.out.println(c); // Exibe todos os clientes
        }
    }

    public boolean atualizarCartao(Cartao cartaoAtualizado) {
        for (int i = 0; i < listaDeCartoes.size(); i++) {
            if (listaDeCartoes.get(i).getId() == cartaoAtualizado.getId()) {
                listaDeCartoes.set(i, cartaoAtualizado);
                return true;
            }
        }
        return false;
    }

    public boolean removerCartao(int id) {
        verificarListaClientes();
        return listaDeCartoes.removeIf(cartao -> cartao.getId() == id);
    }

    private void verificarListaClientes() {
        if (listaDeCartoes.isEmpty()) {
            throw new IllegalStateException("Não há clientes cadastrados!");
        }
    }


}
