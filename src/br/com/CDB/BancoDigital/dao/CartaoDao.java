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
        if (listaDeCartoes.isEmpty()) {
            System.out.println("Nenhum cartao cadastrado no momento.");
            return null;
        }
    
        for (Cartao c : listaDeCartoes) {
            if (c.getId() == id) {
                return c;
            }
        }
    
        System.out.println("Cartao com ID " + id + " não encontrado.");
        return null;
    }

    public List<Cartao> listarCartoes() {
        if (listaDeCartoes.isEmpty()) {
            System.out.println("Nenhum cartao cadastrado no momento.");
            return new ArrayList<>(); // Retorna uma lista vazia ao invés de lançar exceção
        }
    
        List<Cartao> cartoesAtivos = new ArrayList<>();
        for (Cartao c : listaDeCartoes) {
            if (c.isAtivo()) {
                cartoesAtivos.add(c);
            }
        }
    
        return cartoesAtivos;
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
        Cartao cartao = buscarCartaoPorId(id);
    
        if (cartao == null) {
            System.out.println("Não foi possível remover. Cartao não encontrado.");
            return;
        }
    
        cartao.inativar();
        System.out.println("Cartao desligado com sucesso!");
       
    }

  
}
