package br.com.CDB.BancoDigital.services;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;

import br.com.CDB.BancoDigital.dao.ClienteDao;
import br.com.CDB.BancoDigital.entity.cliente.CategoriaCliente;
import br.com.CDB.BancoDigital.entity.cliente.Cliente;
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

            String cpf = solicitarEntrada("Insira o CPF (somente numeros 11 digitos): ");
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
           
            Cliente cliente = new Cliente(cpf, nome, dataNascimento, endereco, categoria,true);
            clienteDao.registrarClientes(cliente);
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
            int escolha = Integer.parseInt(solicitarEntrada("Digite: "));
        
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
        try {
            int escolhaId = Integer.parseInt(solicitarEntrada("Qual o ID do cliente buscado: "));
            Cliente cliente = clienteDao.encontraClientePorId(escolhaId);
            
            if (cliente != null) {
                System.out.println("Cliente encontrado: " + cliente);
            } else {
                System.out.println("Cliente não encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: O ID deve ser um número inteiro válido.");
        }
    }
    

    // Atualizar um cartão
    public void atualizarCliente() {
        int idCartao = Integer.parseInt(solicitarEntrada("Digite o ID do cliente que deseja atualizar: "));
        Cliente cliente = clienteDao.encontraClientePorId(idCartao);
    
        if (cliente == null) {
            System.out.println("Erro: Cartão não encontrado.");
            return;
        }
    
        System.out.println("""
            O que deseja atualizar?
            1 - CPF
            2 - Nome
            3 - Data de Nascimento
            4 - Endereco
            5 - Categoria
            6 - Cancelar
            """);
       
    
        int escolha = Integer.parseInt(solicitarEntrada("Escolha uma opção: "));
        
        switch (escolha) {
            case 1:
                String cpf = solicitarEntrada("Insira o CPF (somente numeros 11 digitos): ");
                exceptions.isStringEmptyField(cpf);

                if (validations.validarCPF(cpf)) {
                    System.out.println("CPF válido!");
                }else {
                    System.out.println("CPF inválido !");
                    return;
                }
        
                cliente.setCPF(cpf);
                break;
    
            case 2:
                String nome = solicitarEntrada("Insira o nome: ");
                exceptions.isStringEmptyField(nome);
                cliente.setNome(nome);
                break;
    
            case 3:

                String endereco = solicitarEntrada("Insira o endereço: ");
                exceptions.isStringEmptyField(endereco);
                cliente.setEndereco(endereco);
                break;
    
            case 4:
                
                CategoriaCliente categoria = solicitarcCategoria();
                cliente.setCategoria(categoria);
                return;
    
            case 5:
                System.out.println("Operação cancelada.");
                return;
    
            default:
                System.out.println("Opção inválida.");
                return;
        }
    
        clienteDao.atualizarCliente(cliente);
        System.out.println("Cartão atualizado com sucesso!");
    }

    public void exibirClientes() {
        try {
            List<Cliente> clientes = clienteDao.listarClientes();
            System.out.println(clientes);
        } catch (IllegalStateException e) {
            System.out.println("Nenhum cliente foi cadastrado ainda. Cadastre um cliente antes de tentar visualizar a lista.");
        }
    }
    

    public void deletarCliente() {
        int escolhaId = Integer.parseInt(solicitarEntrada("Qual o ID do cliente buscado: "));
        System.out.println("Tem certeza que deseja desligar esse cliente? (S/N)");
        String confirmacao = sc.nextLine().trim().toUpperCase();
    
        if (confirmacao.equals("S")) {
            clienteDao.removerCliente(escolhaId);
            System.out.println("Cliente desligado com sucesso!");
        } else {
            System.out.println("Operação cancelada.");
        }
    }
    

    // Método para fechar o scanner (deve ser chamado antes de encerrar o programa)
    public void fecharScanner() {
        sc.close();
    }
}
