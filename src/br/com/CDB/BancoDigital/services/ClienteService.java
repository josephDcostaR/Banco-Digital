package br.com.CDB.BancoDigital.services;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import br.com.CDB.BancoDigital.Entity.cliente.Cliente;
import br.com.CDB.BancoDigital.Entity.cliente.Cliente.Categoria;
import br.com.CDB.BancoDigital.dao.ClienteDao;

public class ClienteService {

    ClienteDao clienteDao = ClienteDao.getInstance();
    private Scanner sc;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Construtor para inicializar o Scanner corretamente
    public ClienteService() {
        this.sc = new Scanner(System.in).useLocale(Locale.US);
    }

    private String solicitarEntrada(String mensagem) {
        System.out.println(mensagem);
        return sc.nextLine().trim();
    }

    public void cadastrarCliente() {
        try {
            System.out.println("=== Cadastro de cliente ===");

            String cpf = solicitarEntrada("Insira o CPF: ");

            String nome = solicitarEntrada("Insira o nome: ");
            if (nome.trim().isEmpty()) {
                throw new IllegalArgumentException("Nome não pode ser vazio");
            }

            String endereco = solicitarEntrada("Insira o endereço: ");

            Categoria categoria = solicitarcCategoria();

            LocalDate dataNascimento = solicitarDataNascimento();

            Cliente cliente = new Cliente(cpf, nome, dataNascimento, endereco, categoria);
            clienteDao.adicionarClientes(cliente);
            
            System.out.println("Cliente cadastrado com sucesso!");
            
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: "+ e.getMessage());
        }
        
    }

    private Categoria solicitarcCategoria() {
        System.out.println("""
            Insira a categoria do cliente:
            1 - COMUM
            2 - SUPER
            3 - PREMIUM
            """);

        try {
            int escolha = Integer.parseInt(sc.nextLine().trim());
        
            return switch (escolha) {
                case 1 ->  Categoria.COMUM;
                case 2 ->  Categoria.SUPER;
                case 3 ->  Categoria.PREMIUM;
                default -> {
                    System.out.println("Opção inválida. Definindo como COMUM por padrão.");
                    yield Categoria.COMUM;
                }
            };
            
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Definido como COMUM por padrão");
            return Categoria.COMUM;
        }
        
    }

    private LocalDate solicitarDataNascimento() {
        String dataStr = solicitarEntrada("Insira a data de nascimento (dd/MM/yyyy): ");
        try {
            LocalDate data = LocalDate.parse(dataStr, FORMATTER);
            if (data.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("Data de nascimento não pode ser futura");
            }
            return data;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de data inválido. Use dd/MM/yyyy");
        }
        
    }

    public void buscarCliente() {
        System.out.println("Qual o ID do cliente buscado: ");
        int escolhaId = sc.nextInt();
        sc.nextLine();
        clienteDao.encontraCliente(escolhaId);
    }

    public void exibirClientes() {
        clienteDao.listarClientes();
    }

    public void removerCliente() {
        System.out.println("Qual o ID do cliente buscado: ");
        int escolhaId = sc.nextInt();
        sc.nextLine();
        clienteDao.deletarCliente(escolhaId);
    }

    // Método para fechar o scanner (deve ser chamado antes de encerrar o programa)
    public void fecharScanner() {
        sc.close();
    }
}
