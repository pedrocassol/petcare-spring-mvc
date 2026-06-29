# PetCare

Sistema web para gerenciamento de clínicas veterinárias desenvolvido como projeto da disciplina de Programação Orientada a Objetos para Web.

## Sobre o Projeto

O PetCare tem como objetivo auxiliar no gerenciamento de informações relacionadas a proprietários, pets e consultas, permitindo o cadastro, a visualização, a edição e a exclusão dos registros de forma simples e organizada.

Esta versão foi desenvolvida utilizando Spring Boot e a arquitetura MVC (Model-View-Controller), mantendo separadas a interface, as regras de negócio e o acesso ao banco de dados.

## Funcionalidades

* Cadastro e autenticação de usuários
* Login e logout com controle de sessão
* Dashboard com resumo dos registros
* Cadastro, listagem, edição e exclusão de proprietários
* Cadastro, listagem, edição e exclusão de pets
* Cadastro, listagem, edição e exclusão de consultas
* Relatório de consultas por período
* Cálculo do valor estimado total das consultas
* Validação dos formulários
* Integração com banco de dados PostgreSQL

## Tecnologias Utilizadas

* Java 21
* Spring Boot
* Spring MVC
* JSP
* JSTL
* JDBC
* PostgreSQL
* Maven
* Apache Tomcat incorporado
* Bootstrap 5
* HTML5
* CSS3

## Estrutura do Projeto

```text
src/main/
├── java/br/com/petcare/
│   ├── controller/
│   ├── service/
│   ├── dao/
│   └── model/
├── resources/
│   └── application.properties
└── webapp/
    ├── css/
    ├── images/
    └── WEB-INF/
        └── pages/
```

## Banco de Dados

O sistema utiliza o PostgreSQL para armazenar os dados.

As principais tabelas utilizadas são:

* Usuário
* Proprietário
* Pet
* Consulta

Configuração padrão da conexão:

```text
Banco: petCare
Endereço: localhost
Porta: 5432
Usuário: postgres
Senha: 1234
```

## Como Executar

1. Clonar o repositório:

```bash
git clone https://github.com/pedrocassol/petcare-spring-mvc.git
```

2. Criar o banco de dados `petCare` no PostgreSQL e suas tabelas.

3. Confirmar as credenciais na classe `ConexaoDB`. Por padrão, o projeto utiliza o usuário `postgres` e a senha `1234`.

4. Abrir o projeto na IDE utilizando o Java 21.

5. Executar o projeto pelo PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

6. Acessar o sistema em [http://localhost:8080](http://localhost:8080).

---

Projeto desenvolvido para a disciplina de Programação Orientada a Objetos para Web.
