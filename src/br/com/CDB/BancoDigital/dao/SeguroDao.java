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

    public List<Seguro> listarSeguros() {
        if (listaDeSeguros.isEmpty()) {
            System.out.println("Nenhum seguro cadastrado no momento.");
            return new ArrayList<>(); // Retorna uma lista vazia ao invés de lançar exceção
        }
    
        List<Seguro> segurosAtivos = new ArrayList<>();
        for (Seguro c : listaDeSeguros) {
            if (c.isAtivo()) {
                segurosAtivos.add(c);
            }
        }
    
        return segurosAtivos;
    }


    // Método para buscar um seguro pelo ID
    public Seguro buscarSeguroPorId(int id) {
        if (listaDeSeguros.isEmpty()) {
            System.out.println("Nenhum seguro cadastrado no momento.");
            return null;
        }
    
        for (Seguro s : listaDeSeguros) {
            if (s.getId() == id) {
                return s;
            }
        }
    
        System.out.println("Seguro com ID " + id + " não encontrado.");
        return null;
    }

     public boolean atualizarSeguro(Seguro seguroAtualizado) {
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
       Seguro seguro = buscarSeguroPorId(id);
    
        if (seguro == null) {
            System.out.println("Não foi possível remover. Seguro não encontrado.");
            return;
        }
    
        seguro.inativar();
        System.out.println("Seguro desligado com sucesso!");  
    
    }

       
    

 

}
