Testes API Serverest
Este projeto contém testes automatizados para a API Serverest, implementados em Java utilizando JUnit 5, RestAssured e Allure para relatórios.
📋 Sobre o Projeto
O projeto realiza testes de API para operações CRUD (Create, Read, Update, Delete) de usuários na API Serverest, incluindo:

✅ Cadastro de usuário
✅ Busca de usuário por ID
✅ Exclusão de usuário
✅ Relatórios detalhados com Allure

🛠️ Tecnologias Utilizadas

Java 21 - Linguagem de programação
JUnit 5 - Framework de testes
RestAssured - Biblioteca para testes de API REST
Allure - Framework para relatórios de teste
Jackson - Manipulação de JSON
Maven - Gerenciamento de dependências e build

📁 Estrutura do Projeto
src/
├── main/java/
│   ├── core/
│   │   └── BaseTest.java
│   └── utils/
│       ├── AuthHelper.java
│       └── JsonUtils.java
├── test/java/
│   └── tests/
│       └── UsuariosTest.java
└── test/resources/
    └── CadastrarUsuario.json
⚙️ Pré-requisitos

Java 21 ou superior
Maven 3.6 ou superior
API Serverest disponível e acessível

🚀 Como Executar
Instalação das Dependências
bashmvn clean install
Executar os Testes
bash# Executar todos os testes
mvn test

# Executar teste específico
mvn test -Dtest=UsuariosTest
Gerar Relatório Allure
bash# Gerar resultados
mvn clean test

# Gerar e servir relatório
mvn allure:serve
📊 Relatórios
O projeto utiliza o Allure para gerar relatórios detalhados dos testes, incluindo:

Status dos testes executados
Tempo de execução
Logs de requisições e respostas
Anexos com dados das requisições
Histórico de execuções

Visualizar Relatórios
Após executar mvn allure:serve, o relatório será aberto automaticamente no navegador padrão.
🧪 Casos de Teste
UsuariosTest
A classe UsuariosTest contém os seguintes cenários de teste:

testCadastrarUsuario

Cadastra um novo usuário na API
Valida status code 201
Armazena ID do usuário para testes subsequentes


testBuscarUsuarioCriado

Busca o usuário criado anteriormente pelo ID
Valida status code 200


testExcluirUsuarioCriado

Exclui o usuário criado anteriormente
Valida status code 200
