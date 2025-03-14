package br.com.CDB.BancoDigital.dao;

import java.util.ArrayList;
import java.util.List;
import br.com.CDB.BancoDigital.Entity.cartao.Cartao;

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
            if (cartao.getId() == id) {
                return cartao;
            }
        }
        return null;
    }

    public void listarCartoes() {
        for (Cartao c : listaDeCartoes) {
            System.out.println(c);
        }
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
