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
                === Menu da Conta ===
                1 - Cadastrar Conta
                2 - Buscar Conta
                3 - Exibir Contas
                4 - Atualizar Conta
                5 - Deletar Cliente
                6 - Exibir Saldos
                7 - Simular Mes
                8 - volvar ao menu
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
                        contaServices.buscarConta();
                        break;
                    case 3:
                        contaServices.exibirContas();
                        break;
                    case 4:
                        contaServices.atualizarConta();
                        break;
                    case 5:
                        contaServices.deletarConta();
                        break;
                    case 6:
                        contaServices.exibirSaldos();
                        break;
                    case 7:
                        contaServices.simularMes();
                        break;
                    case 8:
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
