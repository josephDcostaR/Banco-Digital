import java.util.Scanner;

import br.com.CDB.BancoDigital.view.ClienteView;
import br.com.CDB.BancoDigital.view.ContaView;

public class App {
    public static void main(String[] args) throws Exception {
        ClienteView clienteView = new ClienteView();
        ContaView contaView = new ContaView();

        Scanner sc = new Scanner(System.in);

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

    private static void limparConsole() {
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
