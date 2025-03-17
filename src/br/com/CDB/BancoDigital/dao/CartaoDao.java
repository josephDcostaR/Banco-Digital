package br.com.CDB.BancoDigital.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.CDB.BancoDigital.entity.cartao.Cartao;

public class CartaoDao {

    private static CartaoDao instance = new CartaoDao(); // Inicialização antecipada
    private List<Cartao> listaDeCartoes;
    private int idCounter = 1;

    private CartaoDao() {
        this.listaDeCartoes = new ArrayList<>();
    }

    public static CartaoDao getInstance() {
        if (instance == null) {
            instance = new CartaoDao();
        }
        return instance;
    }

    public void registrarCartaoCliente(Cartao cartao) {
        cartao.setId(idCounter++);
        if (cartao.getConta() != null) {
            cartao.getConta().adicionarCartao(cartao);
        }
        listaDeCartoes.add(cartao);
        System.out.println("Cartão registrado com sucesso! ID: " + cartao.getId());
    }

    public Cartao buscarCartaoPorId(int id) {
        for (Cartao cartao : listaDeCartoes) {
            if (cartao.getId() == id && cartao.isAtivo()) {
                return cartao;
            }
        }
        return null;
    }

    public Cartao listarCartoes() {
        for (Cartao c : listaDeCartoes) {
            if (c.isAtivo()) {
                return c;
            }
        }
        return null;
    }

    public int getTotalCartao() {
        return listaDeCartoes.size();
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

    public void removerCartao(int id) {
        verificarListaClientes();
        Cartao cartao = buscarCartaoPorId(id);
        if (cartao != null) {
            cartao.inativar();
            System.out.println("Cartão desligado com sucesso!");
        }
       
    }

    private void verificarListaClientes() {
        if (listaDeCartoes.isEmpty()) {
            throw new IllegalStateException("Não há clientes cadastrados!");
        }
    }
}
