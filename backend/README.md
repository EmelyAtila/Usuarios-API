# Usuarios API

![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.3-brightgreen?style=flat-square&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue?style=flat-square&logo=postgresql)
![Maven](https://img.shields.io/badge/Maven-3.9-red?style=flat-square&logo=apachemaven)

API RESTful para gerenciamento de usuários de um sistema de vendas online. Oferece CRUD completo, paginação, filtros dinâmicos e documentação interativa via Swagger.

---

## Tecnologias

- **Java 21**
- **Spring Boot 4.0.3**
- **Spring Data JPA + Hibernate**
- **PostgreSQL 16** (via Docker)
- **Lombok**
- **Bean Validation**
- **SpringDoc OpenAPI 3.0.2** (Swagger UI)
- **specification-arg-resolver 3.1.0**
- **Log4j2**

---

## Pré-requisitos

- Java 21+
- Maven 3.9+
- Docker e Docker Compose

---

## Como rodar

**1. Clone o repositório**
```bash
git clone https://github.com/EmelyAtila/Usuarios-API.git
cd Usuarios-API/backend
```

**2. Suba o banco de dados**
```bash
docker-compose up -d
```

**3. Crie a tabela no banco**
```sql
CREATE TYPE tipo_usuario_enum AS ENUM
  ('PROPRIETARIO', 'VENDEDOR', 'GERENTE', 'CAIXA', 'CONTADOR', 'ESTOQUISTA');

CREATE TYPE status_usuario_enum AS ENUM ('ATIVO', 'BLOQUEADO');

CREATE TABLE usuarios (
    id               BIGSERIAL PRIMARY KEY,
    usuario          VARCHAR(50)  NOT NULL UNIQUE,
    email            VARCHAR(255) NOT NULL UNIQUE,
    senha            VARCHAR(255) NOT NULL,
    nome_completo    VARCHAR(150),
    status_usuario   status_usuario_enum NOT NULL DEFAULT 'ATIVO',
    tipo_usuario     tipo_usuario_enum   NOT NULL,
    imagem_url       TEXT,
    data_criacao     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
```

**4. Configure o `application.properties`**
```properties
spring.datasource.url=jdbc:postgresql://localhost:5433/usuarios_db
spring.datasource.username=postgres
spring.datasource.password=postgres123
server.port=8000
```

**5. Rode a aplicação**
```bash
mvn spring-boot:run
```

A API estará disponível em `http://localhost:8000`

---

## Documentação

Acesse o Swagger UI em:
```
http://localhost:8000/swagger-ui/index.html
```

---

## Endpoints

### Autenticação

| Método | Endpoint | Descrição | Status |
|--------|----------|-----------|--------|
| `POST` | `/autenticacao/registro` | Cadastrar novo usuário | `201` / `409` |

**Exemplo de requisição:**
```json
{
  "usuario": "joao123",
  "email": "joao@email.com",
  "senha": "Senha@123",
  "nomeCompleto": "João Silva",
  "tipoUsuario": "VENDEDOR"
}
```

---

### Usuários

| Método | Endpoint | Descrição | Status |
|--------|----------|-----------|--------|
| `GET` | `/usuarios` | Listar usuários (paginado + filtros) | `200` |
| `GET` | `/usuarios/{id}` | Buscar usuário por ID | `200` / `404` |
| `PUT` | `/usuarios/{id}/usuario` | Atualizar dados do usuário | `200` / `404` |
| `PUT` | `/usuarios/{id}/imagem` | Atualizar imagem do usuário | `200` / `404` |
| `PUT` | `/usuarios/{id}/senha` | Atualizar senha do usuário | `200` / `404` |
| `DELETE` | `/usuarios/{id}` | Deletar usuário | `200` / `404` |

---

## Paginação

```bash
# Página padrão (0, tamanho 10)
GET /usuarios

# Especificando página e tamanho
GET /usuarios?page=0&size=5
```

**Resposta:**
```json
{
  "content": [...],
  "page": {
    "size": 5,
    "number": 0,
    "totalElements": 4,
    "totalPages": 1
  }
}
```

---

## Filtros Dinâmicos

| Parâmetro | Tipo de busca | Exemplo |
|-----------|--------------|---------|
| `tipoUsuario` | Exata | `?tipoUsuario=GERENTE` |
| `statusUsuario` | Exata | `?statusUsuario=ATIVO` |
| `email` | Parcial | `?email=gmail` |
| `usuario` | Parcial | `?usuario=joao` |
| `nomeCompleto` | Parcial (case insensitive) | `?nomeCompleto=silva` |

**Combinando filtros e paginação:**
```bash
GET /usuarios?tipoUsuario=GERENTE&statusUsuario=ATIVO&page=0&size=5
```

---

## Tipos de Usuário

```
PROPRIETARIO | VENDEDOR | GERENTE | CAIXA | CONTADOR | ESTOQUISTA
```

## Status do Usuário

```
ATIVO | BLOQUEADO
```

---

## Regras de Senha

A senha deve conter entre **5 e 20 caracteres** e incluir:

- Pelo menos 1 letra maiúscula
- Pelo menos 1 letra minúscula
- Pelo menos 1 número
- Pelo menos 1 caractere especial `(!@#&...)`

---

## Tratamento de Erros

```json
{
  "codigoErro": 400,
  "mensagemErro": "Erro de validação",
  "detalhesErro": {
    "senha": "A senha deve ter entre 5 e 20 caracteres, com maiúscula, minúscula, número e caractere especial",
    "email": "deve ser um endereço de e-mail bem formado"
  }
}
```

| Código | Descrição |
|--------|-----------|
| `400` | Erro de validação nos campos |
| `404` | Recurso não encontrado |
| `409` | Conflito — usuário ou email já cadastrado |
| `500` | Erro interno do servidor |

---

## Estrutura do Projeto

```
src/main/java/br/com/emelyatila/backend/
├── config/
│   ├── ResolverConfig.java         # Paginação, CORS e Specification
│   ├── SpringDoc.java              # Configuração do Swagger
│   └── RequestLoggingFiltroConfig.java  # Configuração de logs
├── controller/
│   ├── UsuarioController.java
│   └── AutenticacaoUsuarioController.java
├── dto/
│   └── UsuarioDTO.java
├── exceptions/
│   ├── GlobalExceptionHandler.java
│   ├── NotFoundException.java
│   └── ErroRecordResponse.java
├── model/
│   ├── Usuario.java
│   ├── TipoUsuario.java
│   └── StatusUsuario.java
├── repository/
│   └── UsuarioRepository.java
├── service/
│   ├── UsuarioService.java
│   └── impl/UsuarioServiceImpl.java
├── specifications/
│   └── SpecificationsTemplate.java
└── validates/
    ├── SenhaConstraint.java
    └── SenhaConstraintImpl.java
```
