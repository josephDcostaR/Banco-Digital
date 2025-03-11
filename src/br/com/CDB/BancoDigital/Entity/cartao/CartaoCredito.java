package br.com.CDB.BancoDigital.Entity.cartao;


public class CartaoCredito extends Cartao {

    public double limiteCredito;

    public CartaoCredito(int numeroCartao, String senha, boolean status, double limiteCredito) {
        super(numeroCartao, senha, status);
        this.limiteCredito = limiteCredito;
    }
    

    @Override
    public void alterarSenha(String novaSenha) {
       this.senha = novaSenha;
       System.out.println("Senha alterada com sucesso.");
    }

    @Override
    public void ativarDesativar() {
        this.status = !this.status;
        String status = this.status ? "Ativado" : "Desativado";
        System.out.println("O cartão de crédito foi " + status + ".");
    }

}
