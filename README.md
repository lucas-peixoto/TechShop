# Documentação API - TechShop

## 1. Usuário

### 1.1 Login

| Informações            |                                      |
|------------------------|--------------------------------------|
| **recurso**            | http://localhost:8081/api/auth/login |
| **método**             | POST                                 |

**Exemplo de requisição**:
```bash
curl -X POST --location "http://localhost:8081/api/auth/login" \
    -H "Content-Type: application/json" \
    -d '{
          "email" : "admin@admin.com",
          "password" : "admin"
        }'
```

### 1.2 Cadastro de usuário administrador

| Informações            |                                               |
|------------------------|-----------------------------------------------|
| **recurso**            | http://localhost:8081/api/auth/register-admin |
| **método**             | POST                                          |

**Exemplo de requisição**:
```bash
curl -X POST --location "http://localhost:8081/api/auth/register-admin" \
  -H "Content-Type: application/json" \
  -H 'Authorization: Bearer {token}' \
  -d '{
        "name" : "",
        "email" : "",
        "password" : ""
      }'
```

### 1.3 Cadastro de usuário cliente

| Informações            |                                         |
|------------------------|-----------------------------------------|
| **recurso**            | http://localhost:8081/api/auth/register |
| **método**             | POST                                    |

**Exemplo de requisição**:
```bash
curl -X POST --location "http://localhost:8081/api/auth/register" \
    -H "Content-Type: application/json" \
    -d '{
          "name" : "",
          "email" : "",
          "password" : ""
        }'
```

## 2. Categoria  

### 2.1 Cadastro de categoria

| Informações            |                                            |
|------------------------|--------------------------------------------|
| **recurso**            | http://localhost:8081/api/admin/categories |
| **método**             | POST                                       |

**Exemplo de requisição**:
```bash
curl -X POST --location "http://localhost:8081/api/admin/categories" \
    -H "Content-Type: application/json" \
    -H 'Authorization: Bearer {token}' \
    -d '{
          "name" : "Eletrônicos",
          "description" : "Categoria de produtos eletrônicos"
        }'
```

### 2.2 Recuperar categorias

| Informações            |                                            |
|------------------------|--------------------------------------------|
| **recurso**            | http://localhost:8081/api/admin/categories |
| **método**             | GET                                        |

**Exemplo de requisição**:
```bash
curl -X GET --location "http://localhost:8081/api/admin/categories" \
     -H 'Authorization: Bearer {token}' \
```

### 2.3 Recuperar categoria por id

| Informações            |                                                 |
|------------------------|-------------------------------------------------|
| **recurso**            | http://localhost:8081/api/admin/categories/{id} |
| **método**             | GET                                             |

**Exemplo de requisição**:
```bash
curl -X GET --location "http://localhost:8081/api/admin/categories/1" \
     -H 'Authorization: Bearer {token}' \
```

### 2.4 Atualizar categoria por id

| Informações            |                                                 |
|------------------------|-------------------------------------------------|
| **recurso**            | http://localhost:8081/api/admin/categories/{id} |
| **método**             | PUT                                             |

**Exemplo de requisição**:
```bash
curl -X PUT --location "http://localhost/categories/0" \
    -H "Content-Type: application/json" \
    -H 'Authorization: Bearer {token}' \
    -d '{
          "name" : "",
          "description" : ""
        }'
```

### 2.5 Deletar categoria por id

| Informações            |                                                 |
|------------------------|-------------------------------------------------|
| **recurso**            | http://localhost:8081/api/admin/categories/{id} |
| **método**             | DELETE                                          |

**Exemplo de requisição**:
```bash
curl -X DELETE --location "http://localhost:8081/api/admin/categories/1" \
     -H 'Authorization: Bearer {token}' \
```


## 3. Produto
## 4. Buscar produtos
## 5. Carrinho
## 6. Pagamento


### 1.1 Cadastro de categoria

| Informações            |                                                |
|------------------------|------------------------------------------------|
| **recurso**            | https://cursos.alura.com.br/start/api/v1/class |
| **método**             | POST                                           |

**Exemplo de requisição**:
...

### 1.2 Cadastro de produto

| Informações            |                                                |
|------------------------|------------------------------------------------|
| **recurso**            | https://cursos.alura.com.br/start/api/v1/class |
| **método**             | POST                                           |

**Exemplo de requisição**:
...