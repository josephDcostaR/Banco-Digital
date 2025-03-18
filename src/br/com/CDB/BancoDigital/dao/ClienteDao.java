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
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado no momento.");
            return null;
        }
    
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                return c;
            }
        }
    
        System.out.println("Cliente com ID " + id + " não encontrado.");
        return null;
    }

    public List<Cliente> listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado no momento.");
            return new ArrayList<>(); // Retorna uma lista vazia ao invés de lançar exceção
        }
    
        List<Cliente> clientesAtivos = new ArrayList<>();
        for (Cliente c : clientes) {
            if (c.isAtivo()) {
                clientesAtivos.add(c);
            }
        }
    
        return clientesAtivos;
    }

    
    public boolean atualizarCliente(Cliente cartaoAtualizado) {
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
        Cliente cliente = encontraClientePorId(id);
    
        if (cliente == null) {
            System.out.println("Não foi possível remover. Cliente não encontrado.");
            return;
        }
    
        cliente.inativar();
        System.out.println("Cliente desligado com sucesso!");        
    }
    
    

}    
