package br.com.CDB.BancoDigital.view;

import java.util.Locale;
import java.util.Scanner;

public class MenuView {

    Scanner sc;

    ClienteView clienteView = new ClienteView();
    ContaView contaView = new ContaView();

     public MenuView(ClienteView clienteView, ContaView contaView) {
        this.sc = new Scanner(System.in).useLocale(Locale.US);
        this.clienteView = clienteView;
        this.contaView = contaView;
    }

    public void iniciarMenu() {

        while(true) {
                 limparConsole();

                 System.out.println("""
                         Banco Digital CVV:
                         1 - Cliente
                         2 - Conta
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
                  
                    default:
                        System.out.println("Opção inválida!");
                }
                System.out.println("\nPressione Enter para continuar...");
                sc.nextLine();
        }

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
