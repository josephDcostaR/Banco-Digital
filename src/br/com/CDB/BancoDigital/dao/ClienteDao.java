package br.com.CDB.BancoDigital.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.CDB.BancoDigital.entity.cliente.Cliente;

public class ClienteDao {

    private static ClienteDao instance;
    List<Cliente> clientes;
    private int idCounter = 1;

    private ClienteDao() {
        this.clientes = new ArrayList<>();
    }

    //Singleton
    public static ClienteDao getInstance() {
        if (instance == null) {
            instance = new ClienteDao();
        }
        return instance;
    }

    public void registrarClientes(Cliente cliente) {
        cliente.setId(idCounter++);
        clientes.add(cliente);
    }

    public Cliente encontraClientePorId(int id) {
        verificarListaClientes();
        for(Cliente c : clientes) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public Cliente listarClientes() {
        verificarListaClientes();
        for(Cliente c : clientes) {
            if (c.isAtivo()) {
                return c;
            }
        }

        return null;
    }

    
    public boolean atualizarCartao(Cliente cartaoAtualizado) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == cartaoAtualizado.getId()) {
                clientes.set(i, cartaoAtualizado);
                return true;
            }
        }
        return false;
    }

    public int getTotalClientes() {
        return clientes.size();
    }
    
    public void removerCliente(int id) {
        verificarListaClientes();
        Cliente cliente = encontraClientePorId(id);
        if (cliente != null) {
            cliente.inativar();
            System.out.println("Cliente desligado com sucesso!");        
        }
        
    }
    

    private void verificarListaClientes() {
        if (clientes.isEmpty()) {
            throw new IllegalStateException("Não há clientes cadastrados!");
        }
    }
}    
