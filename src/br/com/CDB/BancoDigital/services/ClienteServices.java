package br.com.CDB.BancoDigital.services;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import br.com.CDB.BancoDigital.Entity.cliente.CategoriaCliente;
import br.com.CDB.BancoDigital.Entity.cliente.Cliente;
import br.com.CDB.BancoDigital.dao.ClienteDao;
import br.com.CDB.BancoDigital.exceptions.Exceptions;
import br.com.CDB.BancoDigital.validations.Validations;

public class ClienteServices {

    ClienteDao clienteDao = ClienteDao.getInstance();
    private Scanner sc;
    private Exceptions exceptions = new Exceptions();
    private Validations validations = new Validations();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Construtor para inicializar o Scanner corretamente
    public ClienteServices() {
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
            exceptions.isStringEmptyField(cpf);

            if (validations.validarCPF(cpf)) {
                System.out.println("CPF válido!");
            }else {
                System.out.println("CPF inválido !");
                return;
            }

            String nome = solicitarEntrada("Insira o nome: ");
            exceptions.isStringEmptyField(nome);
         
            String endereco = solicitarEntrada("Insira o endereço: ");
            exceptions.isStringEmptyField(endereco);

            CategoriaCliente categoria = solicitarcCategoria();

            LocalDate dataNascimento = solicitarDataNascimento();
            if (validations.validarIdade(dataNascimento)) {
                System.out.println("Cadastro aprovado !");
            }else {
                System.out.println("Cadastro reprovado, cliente menor de idade !");
                return;
            }
           

            Cliente cliente = new Cliente(cpf, nome, dataNascimento, endereco, categoria);
            clienteDao.adicionarClientes(cliente);
            System.out.println("Cliente cadastrado com sucesso!");
            
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: "+ e.getMessage());
        }
        
    }

    private CategoriaCliente solicitarcCategoria() {
        System.out.println("""
            Insira a categoria do cliente:
            1 - COMUM
            2 - SUPER
            3 - PREMIUM
            """);

        try {
            int escolha = Integer.parseInt(sc.nextLine().trim());
        
            return switch (escolha) {
                case 1 ->  CategoriaCliente.COMUM;
                case 2 ->  CategoriaCliente.SUPER;
                case 3 ->  CategoriaCliente.PREMIUM;
                default -> {
                    System.out.println("Opção inválida. Definindo como COMUM por padrão.");
                    yield CategoriaCliente.COMUM;
                }
            };
            
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Definido como COMUM por padrão");
            return CategoriaCliente.COMUM;
        }
        
    }

    private LocalDate solicitarDataNascimento() {
        String dataStr = solicitarEntrada("Insira a data de nascimento (dd/MM/yyyy): ");
        exceptions.isStringEmptyField(dataStr);
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
