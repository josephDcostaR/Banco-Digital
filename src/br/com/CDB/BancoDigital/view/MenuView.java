package br.com.CDB.BancoDigital.view;

import java.util.Locale;
import java.util.Scanner;

public class MenuView {

    Scanner sc;

    ClienteView clienteView = new ClienteView();
    ContaView contaView = new ContaView();
    CartaoView cartaoView = new CartaoView();
    SeguroView seguroView = new SeguroView();

     public MenuView(ClienteView clienteView, ContaView contaView, CartaoView cartaoView, SeguroView seguroView) {
        this.sc = new Scanner(System.in).useLocale(Locale.US);
        this.clienteView = clienteView;
        this.contaView = contaView;
        this.cartaoView = cartaoView;
        this.seguroView = seguroView;
    }

    public void iniciarMenu() {

        while(true) {
                 limparConsole();

                 System.out.println("""
                         Banco Digital CVV:
                         1 - Acessar Menu Cliente
                         2 - Acessar Menu Conta
                         3 - Acessar Menu Cartão
                         4 - Acessar Menu Cartão
                         5 - Sair
                         """);
                 
                 System.out.print("Escolha uma opção: ");
                 int escolha = sc.nextInt();
                 System.out.println();
                 sc.nextLine();

                 switch (escolha) {
                    case 1:
                        clienteView.iniciarCliente();
                        break;
                    case 2:
                        contaView.iniciarConta();
                        break;
                    case 3:
                        cartaoView.iniciarCartao();
                        break;
                    case 4:
                        seguroView.iniciarSeguro();
                        break;
                    case 5:
                        sairSistema();
                        break;
                  
                    default:
                        System.out.println("Opção inválida!");
                }
                System.out.println("\nPressione Enter para continuar...");
                sc.nextLine();
        }

    }

    private void sairSistema(){
        System.out.println("Encerrando ssitema...");
        System.exit(0);
    }

    private void limparConsole() {
        try{
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            }else {
                System.out.println("\\u001b[H\\u001b[2J");
                System.out.flush();
            }

        } catch (Exception e) {
            System.out.println("Erro ao limpar o console!");
        }
    }

}
