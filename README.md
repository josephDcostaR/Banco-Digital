## 📌 Projeto Banco Digital  

Este projeto é uma simulação de um banco digital desenvolvida em **Java Vanilla**, utilizando a arquitetura **MVC (Model-View-Controller)**.  
O objetivo é aplicar conceitos de **orientação a objetos**, **persistência de dados** e **boas práticas de programação**.  

---

### 🚀 Funcionalidades  

✅ **Cadastro e gerenciamento de clientes**  
✅ **Abertura, consulta, atualização e exclusão de contas bancárias**  
✅ **Emissão e administração de cartões**  
✅ **Gerenciamento de seguros vinculados às contas**  
✅ **Realização de pagamentos e simulação de saldo**  
✅ **Interface em linha de comando (CLI) para navegação no sistema**  

---

### 🛠️ Tecnologias Utilizadas  

- **Java 21**  
- **Paradigma de Orientação a Objetos (POO)**  
- **Arquitetura MVC**  
- **Persistência de dados (ArrayList, podendo futuramente ser substituído por banco de dados)**  
- **Scanner para entrada de dados no CLI**  

---

### 📂 Estrutura do Projeto  

```bash
📦 banco-digital  
┣ 📂 src  
┃ ┣ 📂 entity       # Classes que representam as entidades do sistema (Cliente, Conta, Cartão, Seguro, etc.)  
┃ ┣ 📂 dao          # Responsável pela persistência de dados usando ArrayList  
┃ ┣ 📂 view         # Interface do usuário (menus e interações no CLI)  
┃ ┣ 📂 exceptions   # Gerenciamento de exceções e erros  
┃ ┣ 📂 validations  # Classes de validação de dados e regras de negócio  
┃ ┗ 📂 services     # Lógica de negócios e manipulação de dados  
┣ 📄 README.md      # Documentação do projeto  
```

---

### 🔧 Como Executar
O projeto é executado via linha de comando (CLI) e fornece um menu interativo para o usuário navegar entre as opções.

### 📌 Estrutura do Menu Principal:
- 1️⃣ Cliente → Gerencia as informações do cliente (cadastro, consulta, atualização e remoção).
- 2️⃣ Conta → Gerencia operações bancárias (criação de conta, consulta de saldo, atualização e exclusão).
- 3️⃣ Cartão → Emissão e administração de cartões (ativação, alteração de senha, pagamentos).
- 4️⃣ Seguro → Contratação e gerenciamento de seguros vinculados às contas.
- 5️⃣ Sair → Encerra o sistema.

---

1️⃣ **Clone o repositório**  
```bash
git clone https://github.com/seu-usuario/banco-digital.git
cd banco-digital
```  

2️⃣ **Compile o projeto**  
```bash
javac -d bin src/**/*.java  
```  

3️⃣ **Execute o programa**  
```bash
java -cp bin Main
```  

---

### 📝 Melhorias Futuras  

🔹 Persistência de dados utilizando **banco de dados (MySQL/PostgreSQL)**  
🔹 Implementação de **interface gráfica (JavaFX ou Spring Boot com frontend)**  
🔹 Melhor tratamento de erros e exceções  
🔹 Testes unitários com **JUnit**  

---

## 📌 Autor  

👨‍💻 **Joseph da Costa Ribeiro**  
🔗 [LinkedIn](https://www.linkedin.com/in/josephcostaribeiro/) | 📧 [jojojosephdcostaribeiro@gmail.com](mailto:jojojosephdcostaribeiro@gmail.com)

