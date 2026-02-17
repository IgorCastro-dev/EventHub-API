# EventHub API

API REST desenvolvida para simular um sistema de gerenciamento de eventos e venda de ingressos, aplicando boas práticas de desenvolvimento backend com Spring Boot.

Projeto desenvolvido com foco em boas práticas de arquitetura em camadas, regras de negócio, tratamento de exceções e testes unitários.

---

## Tecnologias Utilizadas

- Java 21
- Spring Boot 3
- Spring Data JPA
- PostgreSQL
- Flyway
- Docker
- JUnit 5 + Mockito

---

## Arquitetura

O projeto segue arquitetura em camadas:

controller → service → repository → entity

- **Controller**: Responsável pelos endpoints HTTP.
- **Service**: Contém as regras de negócio.
- **Repository**: Acesso ao banco via Spring Data JPA.
- **DTOs**: Usados para entrada e saída de dados.
- **Exception Handler**: Tratamento global de erros da API.

---

## Funcionalidades

### Eventos
- Criar evento
- Listar eventos
- Buscar evento por ID
- Atualizar evento
- Deletar evento

### Ingressos
- Comprar ingresso
- Validação de capacidade disponível
- Histórico de ingressos por participante

---

## Regras de Negócio

- Não é permitido criar eventos com nome vazio.
- Não é permitido criar eventos com data no passado.
- Não é possível comprar ingresso se o evento estiver lotado.
- A capacidade do evento é decrementada após cada compra.

---

## Banco de Dados

Foi escolhido **PostgreSQL** por ser:

- Robusto
- Amplamente usado no mercado
- Excelente integração com Spring Boot
- Ideal para projetos reais

---

## Controle de Versão do Banco

O projeto utiliza **Flyway** para versionamento do banco de dados.

Motivos da escolha:
- Controle de histórico das alterações no banco
- Reprodutibilidade do ambiente
- Padrão amplamente utilizado em produção

As migrations ficam em: src/main/resources/db/migration

## Padrão de Respostas HTTP

A API utiliza os seguintes status codes:

- 200 OK
- 201 Created
- 204 No Content
- 400 Bad Request
- 404 Not Found
- 409 Conflict

### Uso de DTOs
As entidades não são expostas diretamente na API.
DTOs são utilizados para:
- Evitar vazamento de estrutura interna
- Controlar dados de entrada e saída
- Facilitar evolução futura da API

### Uso de @Transactional
A compra de ingressos é transacional para garantir consistência na atualização da capacidade e criação do ingresso.

## Melhorias Futuras

- Implementar paginação nos endpoints
- Adicionar autenticação com Spring Security
- Implementar controle de concorrência para evitar overbooking
- Adicionar documentação com Swagger/OpenAPI


---

## Como rodar o projeto

### 1 Clonar o repositório
```bash
git clone https://github.com/IgorCastro-dev/EventHub-API.git
cd eventhubapi
```

### 2 Subir o banco com Docker
```bash
docker compose up -d
```

### 3 Rodar a aplicação
```bash
./mvnw spring-boot:run
```
Ao iniciar a aplicação, o Flyway executa automaticamente as migrations e cria as tabelas no banco.

### 4 Rodar os endpoints de teste
Agora para testar é só rodar os endpoints de teste que estão nos arquivos 
EventoRequest.http e IngressoRequest.http dentro da pasta http-request na raíz do projeto.

