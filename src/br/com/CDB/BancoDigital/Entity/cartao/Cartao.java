package br.com.CDB.BancoDigital.Entity.cartao;

public abstract class Cartao {

    private int numeroCartao;
    private String senha;
    private boolean status;

    public Cartao(int numeroCartao, String senha, boolean status) {
        this.numeroCartao = numeroCartao;
        this.senha = senha;
        this.status = status;
    }
    
    public abstract void alterarSenha();
    public abstract void ativarDesativar();


    public int getNumeroCartao() {
        return numeroCartao;
    }
    public void setNumeroCartao(int numeroCartao) {
        this.numeroCartao = numeroCartao;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    


}
