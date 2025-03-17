
import br.com.CDB.BancoDigital.view.CartaoView;
import br.com.CDB.BancoDigital.view.ClienteView;
import br.com.CDB.BancoDigital.view.ContaView;
import br.com.CDB.BancoDigital.view.MenuView;
import br.com.CDB.BancoDigital.view.SeguroView;

public class App {
    public static void main(String[] args) throws Exception {

        ClienteView clienteView = new ClienteView();
        ContaView contaView = new ContaView();
        CartaoView cartaoView = new CartaoView();
        SeguroView seguroView = new SeguroView();
        MenuView menu = new MenuView(clienteView,contaView, cartaoView, seguroView);

        clienteView.setMenuView(menu);
        contaView.setMenuView(menu);
        cartaoView.setMenuView(menu);
        seguroView.setMenuView(menu);

        menu.iniciarMenu();

    }
}
