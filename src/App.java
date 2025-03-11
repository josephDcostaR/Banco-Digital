
import br.com.CDB.BancoDigital.view.ClienteView;
import br.com.CDB.BancoDigital.view.ContaView;
import br.com.CDB.BancoDigital.view.MenuView;

public class App {

    
    public static void main(String[] args) throws Exception {

        ClienteView clienteView = new ClienteView();
        ContaView contaView = new ContaView();
        MenuView menu = new MenuView(clienteView,contaView);

        clienteView.setMenuView(menu);
        contaView.setMenuView(menu);

        menu.iniciarMenu();

        // TODO: 
       // 1. Contruir a cartão serices, dao e view
       // 1. Contruir a seguro serices, dao e view
       // 3. Fazer testes com as entidades
       // 4. Melhorar a logica da relação entre as classes 


        //Testes:

        // List<Cliente> clientes = new ArrayList<>();
        // List<Conta> contas = new ArrayList<>();

        // // Criando clientes de teste
        // Cliente cliente1 = new Cliente("123.456.789-00", "João Silva", LocalDate.of(1990, 5, 20), "Rua A, 123", Categoria.COMUM);
        // Cliente cliente2 = new Cliente("987.654.321-00", "Maria Oliveira", LocalDate.of(1985, 10, 15), "Rua B, 456", Categoria.PREMIUM);
        // Cliente cliente3 = new Cliente("456.123.789-00", "Carlos Souza", LocalDate.of(2000, 3, 8), "Rua C, 789", Categoria.SUPER);
        
        // clientes.add(cliente1);
        // clientes.add(cliente2);
        // clientes.add(cliente3);

        // // Criando contas associadas aos clientes
        // ContaCorrente conta1 = new ContaCorrente(1, 1001, new BigDecimal("2500.00"), cliente1, 12.00);
        // ContaPoupanca conta2 = new ContaPoupanca(2, 2001, new BigDecimal("5000.00"), cliente2, 0.009);
        // ContaCorrente conta3 = new ContaCorrente(3, 1002, new BigDecimal("1500.00"), cliente3, 8.00);
        
        // contas.add(conta1);
        // contas.add(conta2);
        // contas.add(conta3);

        // // Associando contas aos clientes
        // cliente1.adicionarConta(conta1);
        // cliente2.adicionarConta(conta2);
        // cliente3.adicionarConta(conta3);


        ///-------------------------------------------------------------------------------------//
        /// 
        /// 

        
    }
}
