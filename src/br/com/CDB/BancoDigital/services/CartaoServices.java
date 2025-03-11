package br.com.CDB.BancoDigital.services;

import java.util.List;
import java.util.Scanner;

import br.com.CDB.BancoDigital.Entity.cartao.Cartao;
import br.com.CDB.BancoDigital.dao.CartaoDao;
import br.com.CDB.BancoDigital.dao.ContaDao;

public class CartaoServices {

    CartaoDao cartaoDao = new CartaoDao();
    ContaDao contaDao = new ContaDao();

    Scanner sc;

    public CartaoServices() {
        this.contaDao = new ContaDao();
        this.sc = new Scanner(System.in);
    }



    private String solicitarEntrada(String mensagem) {
        System.out.println(mensagem);
        return sc.nextLine().trim();
    }

    // protected int id;
    // protected int numeroCartao;
    // protected String senha;
    // protected boolean status;
    // protected Conta conta;

    // Registra um cartão após validar regras de negócio
    public void registrarCartao(Cartao cartao) {
        
        if(contaDao.getTotalContas()== 0) {
            System.out.println("Nenhum conta cadastrado. Não é possível abrir uma conta.");
            return;
        }

        if (cartao.getConta() != null && cartao.getConta().getCartoes().size() >= 2) {
            System.out.println("Erro: A conta já possui o número máximo de cartões.");
            return;
        }
        cartaoDao.registrarCartaoCliente(cartao);
    }

    // Buscar um cartão pelo ID
    public Cartao buscarCartao(int id) {
        return cartaoDao.buscarCartaoPorId(id);
    }

    // Listar todos os cartões
    public List<Cartao> listarCartoes() {
        return cartaoDao.listarCartoes();
    }

    // Atualizar um cartão
    public boolean atualizarCartao(Cartao cartao) {
        return cartaoDao.atualizarCartao(cartao);
    }

    // Remover um cartão pelo ID
    public boolean removerCartao(int id) {
        return cartaoDao.removerCartao(id);
    }

}
