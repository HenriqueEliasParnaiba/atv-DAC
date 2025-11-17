# Sistema de Cadastro de Livros (corrigido)

CRUD de livros com 4 camadas (interface, aplicação, domínio, infraestrutura), JPA/H2 e testes.
Este projeto usa o **parent do Spring Boot 3.3.1** para evitar conflitos com o plugin do Spring.

## Requisitos
- Java 17+
- Maven 3.9+

## Rodar
```bash
mvn spring-boot:run
```
API: `http://localhost:8080` — Console H2: `http://localhost:8080/h2` (JDBC URL: `jdbc:h2:mem:livrosdb`).

## Testes
```bash
mvn test
```
