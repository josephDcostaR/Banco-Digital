package br.com.CDB.BancoDigital.view;

import java.util.Locale;
import java.util.Scanner;

import br.com.CDB.BancoDigital.services.ContaServices;

public class ContaView {

    private ContaServices contaServices;
    private MenuView menuView;
    private Scanner sc;

      public ContaView() {
        this.sc = new Scanner(System.in).useLocale(Locale.US);
        this.contaServices = new ContaServices();
    }

    

    public void setMenuView(MenuView menuView) {
        this.menuView = menuView;
    }



    public void menuConta() {
        System.out.println("""
                === Menu da conta ===
                1 - Cadastrar Conta
                2 - Exibir Saldos
                3 - Simular Mes
                4 - volvar ao menu.
                """);
    }

    public void iniciarConta() {

        while(true) {
                 limparConsole();
                 menuConta();
                 System.out.print("Escolha uma opção: ");
                 int escolha = sc.nextInt();
                 System.out.println();
                 sc.nextLine();

                 switch (escolha) {
                    case 1:
                        contaServices.cadastrarConta();
                        break;
                    case 2:
                        contaServices.exibirSaldos();
                        break;
                    case 3:
                        contaServices.simularMes();
                        break;
                    case 4:
                        menuView.iniciarMenu();
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
