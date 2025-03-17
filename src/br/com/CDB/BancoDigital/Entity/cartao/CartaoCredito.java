package br.com.CDB.BancoDigital.entity.cartao;


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
        this.ativo = !this.ativo;
        String status = this.ativo ? "Ativado" : "Desativado";
        System.out.println("O cartão de crédito foi " + status + ".");
    }

    @Override
    public void efetuarPagamento(double valor) {
        if (valor <= limiteCredito) {
            System.out.println("Pagamento efetuado !");
        } else {
            System.out.println("pagamento Recusado, vlor acima do limite");
        }
    }
    

}
