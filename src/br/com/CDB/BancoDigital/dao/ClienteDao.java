package br.com.CDB.BancoDigital.dao;

import java.util.ArrayList;
import java.util.List;
import br.com.CDB.BancoDigital.Entity.cliente.Cliente;

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

    public void adicionarClientes(Cliente cliente) {
        cliente.setId(idCounter++);
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
            System.out.println(c); 
        }
    }

    public int getTotalClientes() {
        return clientes.size();
    }
    
    public void deletarCliente(int id) {
        verificarListaClientes();
        clientes.removeIf(c -> c.getId() == id); 
    }
    

    private void verificarListaClientes() {
        if (clientes.isEmpty()) {
            throw new IllegalStateException("Não há clientes cadastrados!");
        }
    }
}    
