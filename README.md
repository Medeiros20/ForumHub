# ForumHub - Challenge Alura 🚀
<p align="center">
<img width="461" height="95" alt="image" src="https://github.com/user-attachments/assets/aa70ea6f-5792-4860-a34d-5d365efc6d2f" />
</p>

A Voll.med é uma API REST desenvolvida para a gestão de uma clínica médica, permitindo o gerenciamento de médicos, pacientes e o agendamento de consultas. O projeto foca em boas práticas de desenvolvimento, segurança com JWT e organização de banco de dados com migrações.

## 🛠️ Funcionalidades
- CRUD de Tópicos:** Gerenciamento completo (Criar, Listar, Atualizar e Deletar).
- Autenticação JWT:** Proteção de endpoints utilizando Spring Security e Tokens JWT.
- Validações de Negócio:** Impede a criação de tópicos duplicados (mesmo título e mensagem).
- Persistência de Dados:** Banco de dados relacional com integridade garantida pelo Spring Data JPA.
- Versionamento de Banco:** Uso do Flyway para controle de migrations.

## 🛠️ Tecnologias Utilizadas
- Java 17
- Spring Boot 3
- Spring Data JPA (Persistência de dados)
- Spring Security (Autenticação e Autorização)
- Auth0 JWT (Token de segurança)
- MySQL (Banco de dados relacional)
- Flyway (Gerenciamento de migrações de banco de dados)
- Lombok (Produtividade e redução de código boilerplate)
- Maven (Gerenciador de dependências)
- insomnia

### 🔐 Autenticação e visualização
- Login: Aberto ao público. Retorna um Token JWT para acessar as rotas protegidas.
- Listagem: Listagem paginada e ordenada de um topico ou de todos os topico permitido ao publico.

### 📝 Topicos
- Cadastro: Requer autorização.
- Atualização: Edição de dados específicos requer autorização.
- Exclusão: Exclusão lógica do registro no banco de dados requer autorização (No caso desse projeto a exclusão é permanente no banco de dados até o momento).

## 🚀 Como Executar o Projeto
### Clone o repositório:
  ```
  git clone https://github.com/Medeiros20/ForumHub.git
  ```
### Configure o Banco de Dados:
- Certifique-se de ter o MySQL instalado.
- Crie um database chamado `forumhub`.
- No arquivo `src/main/resources/application.properties`, ajuste as credenciais:
```
  spring.datasource.url=jdbc:mysql://localhost:3306/forumhub
  spring.datasource.username=${seu_usuario}
  spring.datasource.password=${sua_senha}
  api.security.token.secret=${JWT_SECRET:12345678}
```
## Endipoints principais
| Método | Endpoint | Descrição | Requer Token? |
| :--- | :--- | :--- | :--- |
`POST`| `/login` | Autentica usuário e gera token JWT | **Não**.
`POST`| `/topicos` | Cria um novo tópico | **Sim**.
`GET` | `/topicos` | Lista todos os tópicos | **Não**
`GET` | `/topicos/{id}` | Detalha um tópico específico | **Não**
`PUT` | `/topicos/{id}` | Atualiza dados de um tópico | **Sim**
`DELETE` | `/topicos/{id}` | Remove um tópico permanentemente | **Não**

