package br.com.CDB.BancoDigital.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.CDB.BancoDigital.Entity.cliente.Cliente;

public class ClienteDao {

    List<Cliente> clientes = new ArrayList<>();

    public void adicionarClientes(Cliente cliente) {
        cliente.setId(clientes.size() + 1);
        clientes.add(cliente);
    }

    public Cliente encontraCliente(int id) {
        for(Cliente c : clientes) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public void listarClientes() {
        for(Cliente c : clientes) {
            System.out.println(c); // Exibe todos os clientes
        }
    }
    

    public void deletarCliente(int id) {
        verificarListaClientes();
        clientes.removeIf(c -> c.getId() == id); // Remove sem erro de concorrência
    }
    

    private void verificarListaClientes() {
        if (clientes.isEmpty()) {
            throw new IllegalStateException("Não há clientes cadastrados!");
        }
    }
}    
