package br.com.CDB.BancoDigital.view;

import java.util.Locale;
import java.util.Scanner;

import br.com.CDB.BancoDigital.services.CartaoServices;

public class CartaoView {

    private CartaoServices cartaoServices;
    private MenuView menuView;
    private Scanner sc;

    public CartaoView() {
        this.sc = new Scanner(System.in).useLocale(Locale.US);
        this.cartaoServices = new CartaoServices();
    }

    public void setMenuView(MenuView menuView) {
        this.menuView = menuView;
    }

    public void menuCartao() {
        System.out.println("""
                 === Menu do Cartao ===
                 1 - Cadastrar Cartao
                 2 - Alterar Senha
                 3 - Ativar Cartao
                 4 - Buscar Cartao por Id
                 5 - Exibir todos os Cartoes
                 6 - Editar Cartao
                 7 - Efetuar Pagamento
                 8 - Remover um Cartao
                 9 - Voltar para Menu
                 """);
    }

    public void iniciarCartao() {

        while(true) {
                 limparConsole();
                 menuCartao();
                 System.out.print("Escolha uma opção: ");
                 int escolha = sc.nextInt();
                 System.out.println();
                 sc.nextLine();

                 switch (escolha) {
                    case 1:
                        cartaoServices.registrarCartao();
                        break;
                    case 2:
                        cartaoServices.alterarSenhaCartao();
                        break;
                    case 3:
                        cartaoServices.ativarDesativarCartao();
                        break;
                    case 4:
                        cartaoServices.buscarCartao();
                        break;
                    case 5:
                        cartaoServices.exibirCartoes();
                        break;
                    case 6:
                        cartaoServices.atualizarCartao();
                        break;
                    case 7:
                        cartaoServices.efetuadoPagamento();;
                        break;
                    case 8:
                        cartaoServices.removerCartao();
                        break;
                    case 9:
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

