package br.com.CDB.BancoDigital.Entity.cartao;



public class CartaoDebito extends Cartao {

    public double limiteDiario;

    public CartaoDebito(int numeroCartao, String senha, boolean status, double limiteDiario) {
            super(numeroCartao, senha, status);
            this.limiteDiario = limiteDiario;
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
