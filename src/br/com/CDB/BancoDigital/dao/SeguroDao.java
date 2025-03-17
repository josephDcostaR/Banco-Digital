package br.com.CDB.BancoDigital.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.CDB.BancoDigital.entity.seguro.Seguro;

public class SeguroDao {
    private static SeguroDao instance = new SeguroDao();
    private List<Seguro> listaDeSeguros;
    private int idCounter = 1;

    private SeguroDao() {
        this.listaDeSeguros = new ArrayList<>();
    }

    public static SeguroDao getInstance() {
        if (instance == null) {
            instance = new SeguroDao();
        }
        return instance;
    }

    public void registrarSeguroCartao(Seguro seguro) {
        seguro.setId(idCounter++);
        if (seguro.getCartaoAssociado()  != null) {
            seguro.getCartaoAssociado().adicionarSeguro(seguro);
        }
        listaDeSeguros.add(seguro);
        System.out.println("Seguro registrado com sucesso! ID: " + seguro.getId());

    }

    // Método para listar todos os seguros
    public List<Seguro> getTotalSeguros() {
        return listaDeSeguros;
    }

    public Seguro listarSeguros() {
        verificarListaSeguros();
        for(Seguro s : listaDeSeguros) {
            if (s.isAtivo()) {
                return s;
            }
        }

        return null;
    }


    // Método para buscar um seguro pelo ID
    public Seguro buscarSeguroPorId(int id) {
        for (Seguro seguro : listaDeSeguros) {
            if (seguro.getId() == id && seguro.isAtivo()) {
                return seguro;
            }
        }
        return null; // Retorna null caso não encontre o seguro
    }

     public boolean atualizarCartao(Seguro seguroAtualizado) {
        for (int i = 0; i < listaDeSeguros.size(); i++) {
            if (listaDeSeguros.get(i).getId() == seguroAtualizado.getId()) {
                listaDeSeguros.set(i, seguroAtualizado);
                return true;
            }
        }
        return false;
    }

    // Método para remover um seguro pelo ID
    public void removerSeguroPorId(int id) {
        verificarListaSeguros();
        Seguro seguro = buscarSeguroPorId(id);
        if (seguro != null) {
            seguro.inativar();
            System.out.println("Seguro desligado com sucesso!");
        }
    
    }

       
    

    private void verificarListaSeguros() {
        if (listaDeSeguros.isEmpty()) {
            throw new IllegalStateException("Não há seguros cadastrados!");
        }
    }

}
