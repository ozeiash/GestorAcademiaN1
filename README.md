# GestorAcademiaN1

# Descrição:
Este projeto consiste no desenvolvimento de uma API REST completa para o gerenciamento de uma academia, utilizando Spring Boot 3.x e Java 17+.
A API foi estruturada seguindo a Arquitetura em Camadas (Controller → Service → Repository) e implementa o gerenciamento de:
**Alunos:** Cadastro, consulta, atualização e inativação.
**Planos:** Gerenciamento completo (CRUD).
**Treinos:** Gerenciamento e associação a alunos.
**Pagamentos:** Registro de pagamentos com status automático.

## Regras de Negócio Implementadas

O foco do projeto é a aplicação de boas práticas e regras de negócio específicas:
**Um aluno** só pode estar matriculado em um plano por vez.
**O CPF do aluno** é um campo único.
**A data do pagamento** é preenchida automaticamente no registro.
**Não é possível remover treinos** que ainda estejam associados a alunos.
**A lógica de status de pagamento** define **PAGO** ou **ATRASADO** com base no valor mensal do plano do aluno.

# Instruções para Rodar o Projeto
# 1. Pré-requisitos
Certifique-se de ter instalado em sua máquina:
Java Development Kit (JDK) 17+ 
Maven (para gerenciamento de dependências e build) 
Git (para clonar o repositório)

# 2. Clonar o Repositório
git clone https://github.com/ozeiash/GestorAcademiaN1.git

# 3. Configuração do Banco de Dados
O projeto utiliza o banco de dados **H2** em modo arquivo (.file).
Nenhuma configuração adicional é estritamente necessária, mas você pode verificar o arquivo application.properties para detalhes de conexão H2.

# Execute o comando Maven para compilar o projeto e empacotá-lo:
mvn clean install

# Em seguida, execute a aplicação Spring Boot:
java -jar target/gestorAcademia.jar

# 5. Acessar a Documentação (Swagger/OpenAPI)
Com a aplicação rodando (geralmente na porta 8080), a documentação interativa da API estará acessível no navegador:
http://localhost:8080/swagger-ui.html

## 📸 Prints dos Endpoints Testados (Swagger)

### 1. Cadastro de Plano (201 Created)

Este print demonstra o sucesso do cadastro de um novo Plano (POST /api/v1/planos), retornando o código 201 e o objeto criado.
![Cadastrar Plano](screenshots/POSTCadastroPlano.png)

### 2. Cadastro de Aluno (201 Created)

Confirma o cadastro do Aluno (POST /api/v1/alunos), associando-o ao Plano criado, e retornando o código 201.
![Cadastrar Aluno](screenshots/POSTAluno.png)

### 3. Cadastro de Treino (201 Created)

Demonstra o sucesso na criação de um novo Treino (POST /api/v1/treinos), retornando o código 201.
![Cadastrar Treino](screenshots/POSTTreino.png)

### 4. Vínculo Aluno-Treino (201 Created)

Confirma a associação de um aluno a um treino (POST /api/v1/vinculos), que é um requisito fundamental da regra de negócio.
![Vincular Aluno ao Treino](screenshots/POSTVinculoTreino.png)

### 5. Registro de Pagamento (201 Created)

Demonstra o registro de um Pagamento (POST /api/v1/pagamentos), onde a data de pagamento é preenchida automaticamente e o status é calculado com base no valor do plano.
![Registrar Pagamento](screenshots/POSTPagamento.png)

### 6. Consulta de Aluno por ID (200 OK)

A consulta de um aluno específico (GET /api/v1/alunos/{id}) retorna o código 200 e o DTO do aluno encontrado.
![Consultar Aluno](screenshots/GETAluno.png)

### 7. Consulta de Plano por ID (200 OK)

A consulta de um plano específico (GET /api/v1/planos/{id}) retorna o código 200 e o DTO do plano encontrado.
![Consultar Plano](screenshots/GETPlanoByID.png)

### 8. Consulta de Pagamento por ID (200 OK)

A consulta de um registro de pagamento (GET /api/v1/pagamentos/{id}) retorna o código 200 e o DTO do pagamento.
![Consultar Pagamento](screenshots/GETPagamento.png)

### 9. Consulta de Treino por ID (200 OK)

A consulta de um treino específico (GET /api/v1/treinos/{id}) retorna o código 200 e o DTO do treino.
![Consulta de Treino](screenshots/GETTreino.png)

### 10. Atualização de Aluno (200 OK)

Demonstra a atualização de dados do aluno (PUT /api/v1/alunos/{id}), retornando 200 e o DTO atualizado.
![Atualizar aluno](screenshots/PUTAluno.png)

### 11. Atualização de Plano (200 OK)

Demonstra a atualização de dados do plano (PUT /api/v1/planos/{id}), retornando 200 e o DTO atualizado.
![Atualizar Plano](screenshots/PUTPlano.png)

### 12. Atualização de Treino (200 OK)

Demonstra a atualização de dados do treino (PUT /api/v1/treinos/{id}), retornando 200 e o DTO atualizado.
![Atualizar Treino](screenshots/PUTTreino.png)

### 13. Inativação de Aluno (204 No Content)

A inativação (PATCH /api/v1/alunos/{id}/inativar) confirma a alteração do status para ativo = false e retorna o código 204 No Content.
![Inativar Aluno](screenshots/InativarAluno.png)

### 14. Remoção de Plano (204 No Content)

A exclusão de um plano sem vínculos (DELETE /api/v1/planos/{id}) retorna o código 204 No Content, indicando sucesso na remoção.
![Excluir Plano](screenshots/DELETEPlano.png)

### 15. Remoção de Treino sem Vínculos (204 No Content)

A exclusão de um treino que não está associado a alunos (DELETE /api/v1/treinos/{id}) retorna o código 204 No Content.
![Remover Treino](screenshots/DELETETreino.png)

### 16. Tentativa de Remoção de Treino Associado (Erro de Regra de Negócio)

Este é um teste crucial que retorna um erro HTTP (ex: 400 Bad Request ou 409 Conflict) ao tentar remover um treino que possui vínculos ativos, validando a regra: "Não deve ser possível remover treinos que ainda estejam associados a alunos."
![Proteger Treino com Vinculo](screenshots/ErroExcluirTreinoVinculado.png)
