# atv-DAC

# 📚 Sistema de Cadastro de Livros

Aplicação desenvolvida em **Java 17 + Spring Boot 3**, utilizando **JPA/Hibernate**, **Banco H2** e **JUnit/Mockito**.  
O sistema permite gerenciar um catálogo de livros com operações **CRUD completas**, seguindo arquitetura em **4 camadas**.

---

## ⚙️ Tecnologias

- Java 17+
- Spring Boot 3.x
- Spring Web
- Spring Data JPA
- Hibernate
- H2 Database (memória)
- Maven
- JUnit 5 + Mockito

---

# 🧱 Arquitetura do Projeto

src/main/java/com/example/livros
├── interfaceapi/ # Controllers, DTOs, ExceptionHandler
├── application/ # Services, regras de controle, transações
├── domain/ # Entidades e contratos de repositório
└── infrastructure/ # Implementações JPA (EntityManager)

yaml
Copiar código

A aplicação segue fielmente o padrão de camadas exigido:  
**Interface → Aplicação → Domínio → Infraestrutura**

---

# 🚀 Como Rodar o Projeto

## 1️⃣ Pré-requisitos

Verifique se possui Java e Maven instalados:

```bash

java -version
mvn -version

Rodar o servidor
mvn clean
mvn spring-boot:run


Acessando o Banco H2

Acesse o console do banco:

http://localhost:8080/h2

Credenciais:

# 🗄️ Acessando o Banco H2

Acesse o console do banco no navegador:

👉 **http://localhost:8080/h2**

### 🔐 Credenciais de acesso:

JDBC URL: jdbc:h2:mem:livrosdb
Usuário: sa
Senha:
> ⚠️ A senha deve ficar **em branco**.

---

# 🧪 Rodando os Testes

mvn test

# 🗄️ Exemplos de Povoamento do Banco (SQL)

Abaixo estão exemplos completos para **adicionar**, **listar**, **modificar** e **remover** livros diretamente no console do H2.



## ➕ Adicionar Livros (INSERT)

```sql
INSERT INTO LIVRO (TITULO, AUTOR, ISBN, ANO_PUBLICACAO, QUANTIDADE_ESTOQUE)
VALUES 
('Clean Code', 'Robert C. Martin', '9780132350884', 2008, 5);

INSERT INTO LIVRO (TITULO, AUTOR, ISBN, ANO_PUBLICACAO, QUANTIDADE_ESTOQUE)
VALUES 
('Domain-Driven Design', 'Eric Evans', '9780321125217', 2003, 3);

INSERT INTO LIVRO (TITULO, AUTOR, ISBN, ANO_PUBLICACAO, QUANTIDADE_ESTOQUE)
VALUES 
('Clean Architecture', 'Robert C. Martin', '9780134494166', 2017, 4);



Listar Todos os Livros (SELECT)
SELECT * FROM LIVRO;



Modificar um Livro (UPDATE)
UPDATE LIVRO
SET 
  TITULO = 'Clean Code - Revisado',
  ANO_PUBLICACAO = 2010,
  QUANTIDADE_ESTOQUE = 8
WHERE ID = 1;



Modificar título, ano e estoque do livro com ID 1:
UPDATE LIVRO
SET 
  TITULO = 'Clean Code - Revisado',
  ANO_PUBLICACAO = 2010,
  QUANTIDADE_ESTOQUE = 8
WHERE ID = 1;



❌ Deletar um Livro (DELETE)

Remover o livro com ID 2:

DELETE FROM LIVRO WHERE ID = 2;
