package br.com.CDB.BancoDigital.view;

import java.util.Locale;
import java.util.Scanner;

import br.com.CDB.BancoDigital.services.ClienteServices;

public class ClienteView {

    private ClienteServices clienteServices;
    private MenuView menuView;
    private Scanner sc;

    public ClienteView() {
        this.sc = new Scanner(System.in).useLocale(Locale.US);
        this.clienteServices = new ClienteServices();
    }

    public void setMenuView(MenuView menuView) {
        this.menuView = menuView;
    }


    public void menuCliente() {
        System.out.println("""
                 === Menu do Cliente ===
                 1 - Cadastrar Cliente
                 2 - Buscar Cliente por Id
                 3 - Exibir todos os clientes
                 4 - Atualizar Cartao
                 5 - Remover um Cliente
                 6 - Voltar para Menu
                 """);
    }

    public void iniciarCliente() {

        while(true) {
                 limparConsole();
                 menuCliente();
                 System.out.print("Escolha uma opção: ");
                 int escolha = sc.nextInt();
                 System.out.println();
                 sc.nextLine();

                 switch (escolha) {
                    case 1:
                        clienteServices.cadastrarCliente();
                        break;
                    case 2:
                        clienteServices.buscarCliente();
                        break;
                    case 3:
                        clienteServices.exibirClientes();
                        break;
                    case 4:
                        clienteServices.atualizarCliente();
                        break;
                    case 5:
                        clienteServices.deletarCliente();
                        break;
                    case 6:
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
