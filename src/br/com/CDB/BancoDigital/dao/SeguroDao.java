package br.com.CDB.BancoDigital.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.CDB.BancoDigital.Entity.seguro.Seguro;

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
    public List<Seguro> listarSeguros() {
        return listaDeSeguros;
    }

    // Método para buscar um seguro pelo ID
    public Seguro buscarSeguroPorId(int id) {
        for (Seguro seguro : listaDeSeguros) {
            if (seguro.getId() == id) {
                return seguro;
            }
        }
        return null; // Retorna null caso não encontre o seguro
    }

    // Método para remover um seguro pelo ID
    public boolean removerSeguroPorId(int id) {
        Seguro seguro = buscarSeguroPorId(id);
        if (seguro != null) {
            listaDeSeguros.remove(seguro);
            System.out.println("Seguro removido com sucesso! ID: " + id);
            return true;
        }
        return false;
    }



}
