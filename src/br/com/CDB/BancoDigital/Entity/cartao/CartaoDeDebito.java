package br.com.CDB.BancoDigital.Entity.cartao;

import java.math.BigDecimal;

public class CartaoDeDebito extends Cartao {

    public BigDecimal taxaDeManutencao;

    public CartaoDeDebito(int numeroCartao, String senha, boolean status, BigDecimal taxaDeManutencao) {
            super(numeroCartao, senha, status);
            this.taxaDeManutencao = taxaDeManutencao;
        }
    
        

    @Override
    public void alterarSenha() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'alterarSenha'");
    }

    @Override
    public void ativarDesativar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ativarDesativar'");
    }

}
