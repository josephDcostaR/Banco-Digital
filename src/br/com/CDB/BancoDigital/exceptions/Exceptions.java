package br.com.CDB.BancoDigital.exceptions;

public class Exceptions {

    public void isStringEmptyField(String item) {
        if (item.trim().isEmpty()) {
            throw new IllegalArgumentException("campo não pode ser vazio");
        }
    }

    public void isNumberEmptyField(){
        System.out.println("não implementada");
    }
}
