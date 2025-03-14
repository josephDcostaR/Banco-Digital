package br.com.CDB.BancoDigital.view;

import java.util.Locale;
import java.util.Scanner;

import br.com.CDB.BancoDigital.services.CartaoServices;
import br.com.CDB.BancoDigital.services.SeguroServices;

public class SeguroView {

     private SeguroServices seguroServices;
    private MenuView menuView;
    private Scanner sc;

    public SeguroView() {
        this.sc = new Scanner(System.in).useLocale(Locale.US);
        this.seguroServices = new SeguroServices();
    }

    public void setMenuView(MenuView menuView) {
        this.menuView = menuView;
    }


    public void menuSeguro() {
        System.out.println("""
                 Menu do cliente:
                 1 - Cadastrar Seguro
                 2 - Contratar Seguro
                 2 - Gerar Apolice
                 3 - Buscar Seguro
                 4 - Exibir Seguros
                 5 - Remover Seguro
                 6 - Voltar para Menu
                 """);
    }

    public void iniciarSeguro() {

        while(true) {
                 limparConsole();
                 menuSeguro();
                 System.out.print("Escolha uma opção: ");
                 int escolha = sc.nextInt();
                 System.out.println();
                 sc.nextLine();

                 switch (escolha) {
                    case 1:
                        seguroServices.registrarSeguro();
                        break;
                    case 2:
                        seguroServices.contratarSeguro();
                        break;
                    case 3:
                        seguroServices.gerarApolice();
                        break;
                    case 4:
                        seguroServices.buscarSeguro();
                        break;
                    case 5:
                        seguroServices.listarSeguros();
                        break;
                    case 6:
                        seguroServices.removerSeguro();
                        break;
                    case 7:
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
