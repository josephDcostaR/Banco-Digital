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

    public Cliente(String CPF, String nome, LocalDate dataDeNascimento, String endereco, Categoria categoria, Conta conta) {
        this.CPF = CPF;
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.endereco = endereco;
        this.categoria = categoria;
        this.contas = new ArrayList<>();
    }

    public Cliente(String CPF, String nome, LocalDate dataDeNascimento, String endereco, Categoria categoria) {
        this.CPF = CPF;
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.endereco = endereco;
        this.categoria = categoria;
    }

    public void adicionarConta(Conta conta) {
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

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    @Override
    public String toString() {
        return "Cliente [Id=" + Id + ", CPF=" + CPF + ", nome=" + nome + ", dataDeNascimento=" + dataDeNascimento
                + ", endereco=" + endereco + ", categoria=" + categoria + ", contas=" + contas + "]";
    }

   

    
    

}
