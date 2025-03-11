package br.com.CDB.BancoDigital.Entity.cliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.CDB.BancoDigital.Entity.conta.Conta;

public class Cliente {
    private int Id;
    private String CPF;
    private String nome;
    private LocalDate dataDeNascimento;
    private String endereco;
    private Categoria categoria;
    private List<Conta> contas;

    public enum Categoria {
        COMUM,SUPER,PREMIUM
    }

    public Cliente(){
        this.contas = new ArrayList<>();
    }

    // Construtor que recebe também uma conta (usa o construtor padrão para inicializar 'contas')
    public Cliente(String CPF, String nome, LocalDate dataDeNascimento, String endereco, Categoria categoria, Conta conta) {
        this();
        this.CPF = CPF;
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.endereco = endereco;
        this.categoria = categoria;
        if(conta != null) {
            this.adicionarConta(conta);
        }
    }

    public Cliente(String CPF, String nome, LocalDate dataDeNascimento, String endereco, Categoria categoria) {
        this(); // chama o construtor padrão para inicializar a lista
        this.CPF = CPF;
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.endereco = endereco;
        this.categoria = categoria;
    }

    public void adicionarConta(Conta conta) {
        if (this.contas == null) {
            this.contas = new ArrayList<>();
        }
        this.contas.add(conta);
        conta.setClienteAssociado(this);
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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
        + ", endereco=" + endereco + ", categoria=" + categoria + ", contas={" + contasInfo.toString() + "}]";
    }
}
