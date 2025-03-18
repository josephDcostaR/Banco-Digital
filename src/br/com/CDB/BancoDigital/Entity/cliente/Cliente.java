package br.com.CDB.BancoDigital.entity.cliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.CDB.BancoDigital.entity.conta.Conta;

public class Cliente {
    private int Id;
    private String CPF;
    private String nome;
    private LocalDate dataDeNascimento;
    private String endereco;
    private CategoriaCliente categoriaCliente;
    private List<Conta> contas;
    private boolean ativo;

    public Cliente(){
        this.contas = new ArrayList<>();
    }

    // Construtor que recebe também uma conta (usa o construtor padrão para inicializar 'contas')
    public Cliente(String CPF, String nome, LocalDate dataDeNascimento, String endereco, CategoriaCliente categoriaCliente,  Conta conta) {
        this();
        this.CPF = CPF;
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.endereco = endereco;
        this.categoriaCliente = categoriaCliente;
        
        if(conta != null) {
            this.adicionarConta(conta);
        }
   
    }

    public Cliente(String CPF, String nome, LocalDate dataDeNascimento, String endereco, CategoriaCliente categoriaCliente,  boolean ativo) {
            this(); // chama o construtor padrão para inicializar a lista
            this.CPF = CPF;
            this.nome = nome;
            this.dataDeNascimento = dataDeNascimento;
            this.endereco = endereco;
            this.categoriaCliente = categoriaCliente;
            this.ativo = ativo;
    }

    //Cria a relação Cliente 1.* Contas
    public void adicionarConta(Conta conta) {
        if (this.contas == null) {
            this.contas = new ArrayList<>();
        }
        this.contas.add(conta);
        conta.setClienteAssociado(this);
    }

    //Desliga o cliente e as contas relacionadas a ele
    public void inativar() {
        this.ativo = false;
        for(Conta conta : contas) {
            conta.inativar();
        }
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id){
        this.Id = Id;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public CategoriaCliente getCategoria() {
        return categoriaCliente;
    }

    public void setCategoria(CategoriaCliente categoriaCliente) {
        this.categoriaCliente = categoriaCliente;
    }
    
    public List<Conta> getContas() {
        return contas;
    }
    
    @Override
    public String toString() {
        StringBuilder contasInfo = new StringBuilder();
        for(Conta conta : contas) {
            contasInfo.append(conta.getNumeroDaConta()).append(", ");
        }
        // Remove a última vírgula, se houver
        if (contasInfo.length() > 0) {
            contasInfo.setLength(contasInfo.length() - 2);            
        }
        return "Cliente [Id=" + Id + ", CPF=" + CPF + ", nome=" + nome + ", dataDeNascimento=" + dataDeNascimento
        + ", endereco=" + endereco + ", categoria=" + categoriaCliente + ", Numero das Contas{" + contasInfo.toString() + "}]";
    }
}
