# TechShop
TechShop é uma API para gerenciamento de uma loja virtual, com funcionalidades para administradores, clientes e carrinho de compras.
O Sistema tem como objetivo simular um e-commerce, onde é possível realizar a compra de produtos, adicionar produtos ao carrinho, realizar o pagamento e gerenciar produtos e categorias.

Para administradores:

Gerenciamento de usuários:
- Cadastro de administradores e clientes.
- Autenticação com token.

Gerenciamento de categorias:
- Cadastro, atualização, exclusão e recuperação de categorias de produtos.

Gerenciamento de produtos:
- Cadastro, atualização, exclusão e recuperação de produtos.
- Associação de produtos a categorias.

Para clientes:

Pesquisa de produtos:
- Listagem de produtos com paginação.
- Busca por produtos por nome ou categoria.
- Recuperação de detalhes de um produto.

Carrinho de compras:
- Adição, remoção e listagem de produtos no carrinho.
- Cálculo do valor total do carrinho.

Pagamento:
- Realização de pagamentos com diferentes métodos.
- Consulta de pagamentos realizados.

Recursos da API:

Autenticação:
- Utilize o token de autenticação para acessar recursos protegidos.

Pagamentos:
- Integração com diferentes métodos de pagamento.
- Erros:
Respostas padronizadas para erros com códigos e mensagens descritivas.

# Tecnologias Utilizadas
- Java 21
- Spring Boot
- Spring Framework
  - Spring Data JPA
  - Spring Security
  - Spring Cloud
      - Gateway
      - Eureka
      - OpenFeign
- Swagger
- JWT
- Hibernate
- MySQL
- Docker

# Documentação API - TechShop

## 1. Usuário

### 1.1 Login

| Informações |                                      |
|-------------|--------------------------------------|
| **recurso** | http://localhost:8081/api/auth/login |
| **método**  | POST                                 |

**Exemplo de requisição**:

```bash
curl -v -X POST --location "http://localhost:8081/api/auth/login" \
     -H "Content-Type: application/json" \
     -d '{
           "email" : "admin@admin.com",
           "password" : "admin"
         }'
```

### 1.2 Cadastro de usuário administrador

| Informações |                                               |
|-------------|-----------------------------------------------|
| **recurso** | http://localhost:8081/api/auth/register-admin |
| **método**  | POST                                          |
Obs: Para cadastrar um usuário administrador, é necessário estar autenticado como administrador.

**Exemplo de requisição**:

```bash
curl -v -X POST --location "http://localhost:8081/api/auth/register-admin" \
     -H "Content-Type: application/json" \
     -H 'Authorization: Bearer {token}' \
     -d '{
           "name" : "usuarioAdmin",
           "email" : "usuarioAdmin@email.com",
           "password" : "usuarioAdmin"
         }'
```

### 1.3 Cadastro de usuário cliente

| Informações |                                         |
|-------------|-----------------------------------------|
| **recurso** | http://localhost:8081/api/auth/register |
| **método**  | POST                                    |

**Exemplo de requisição**:

```bash
curl -v -X POST --location "http://localhost:8081/api/auth/register" \
     -H "Content-Type: application/json" \
     -d '{
           "name" : "usuarioCliente",
           "email" : "usuarioCliente@email.com",
           "password" : "usuarioCliente"
         }'
```

## 2. Categoria

### 2.1 Cadastro de categoria

| Informações |                                            |
|-------------|--------------------------------------------|
| **recurso** | http://localhost:8081/api/admin/categories |
| **método**  | POST                                       |

**Exemplo de requisição**:

```bash
curl -v -X POST --location "http://localhost:8081/api/admin/categories" \
     -H "Content-Type: application/json" \
     -H 'Authorization: Bearer {token}' \
     -d '{
           "name" : "Eletrônicos",
           "description" : "Categoria de produtos eletrônicos"
         }'
```

### 2.2 Recuperar categorias

| Informações |                                            |
|-------------|--------------------------------------------|
| **recurso** | http://localhost:8081/api/admin/categories |
| **método**  | GET                                        |

**Exemplo de requisição**:

```bash
curl -v -X GET --location "http://localhost:8081/api/admin/categories" \
     -H 'Authorization: Bearer {token}'
```

### 2.3 Recuperar categoria por id

| Informações |                                                 |
|-------------|-------------------------------------------------|
| **recurso** | http://localhost:8081/api/admin/categories/{id} |
| **método**  | GET                                             |

**Exemplo de requisição**:

```bash
curl -v -X GET --location "http://localhost:8081/api/admin/categories/1" \
     -H 'Authorization: Bearer {token}'
```

### 2.4 Atualizar categoria por id

| Informações |                                                 |
|-------------|-------------------------------------------------|
| **recurso** | http://localhost:8081/api/admin/categories/{id} |
| **método**  | PUT                                             |

**Exemplo de requisição**:

```bash
curl -v -X PUT --location "http://localhost:8081/api/admin/categories/1" \
    -H "Content-Type: application/json" \
    -H 'Authorization: Bearer {token}' \
    -d '{
          "name" : "Eletro",
          "description" : "Categoria de produtos eletro legais"
        }'
```

### 2.5 Deletar categoria por id

| Informações |                                                 |
|-------------|-------------------------------------------------|
| **recurso** | http://localhost:8081/api/admin/categories/{id} |
| **método**  | DELETE                                          |

**Exemplo de requisição**:

```bash
curl -v -X DELETE --location "http://localhost:8081/api/admin/categories/1" \
     -H 'Authorization: Bearer {token}'
```

## 3. Produto

### 3.1 Cadastro de produto

| Informações |                                          |
|-------------|------------------------------------------|
| **recurso** | http://localhost:8081/api/admin/products |
| **método**  | POST                                     |

**Exemplo de requisição**:

```bash
curl -v -X POST --location "http://localhost:8081/api/admin/products" \
     -H "Content-Type: application/json" \
     -H 'Authorization: Bearer {token}' \
     -d '{
           "name" : "Celular",
           "description" : "Celular top",
           "price" : 15000.0,
           "inventory" : 3,
           "categoriesIds" : [ 1 ]
         }'
```

### 3.2 Recuperar produtos

| Informações |                                          |
|-------------|------------------------------------------|
| **recurso** | http://localhost:8081/api/admin/products |
| **método**  | GET                                      |

**Exemplo de requisição**:

```bash
curl -v -X GET --location "http://localhost:8081/api/admin/products"
```

### 3.3 Recuperar produto por id

| Informações |                                               |
|-------------|-----------------------------------------------|
| **recurso** | http://localhost:8081/api/admin/products/{id} |
| **método**  | GET                                           |

**Exemplo de requisição**:

```bash
curl -v -X GET --location "http://localhost:8081/api/admin/products/1"
```

### 3.4 Atualizar produto por id

| Informações |                                               |
|-------------|-----------------------------------------------|
| **recurso** | http://localhost:8081/api/admin/products/{id} |
| **método**  | PUT                                           |

**Exemplo de requisição**:

```bash
curl -v -X PUT --location "http://localhost:8081/api/admin/products/1" \
    -H "Content-Type: application/json" \
    -H 'Authorization: Bearer {token}' \
    -d '{
          "name" : "Celular Top",
          "description" : "Celular top top",
          "price" : 16000.0,
          "categoriesIds" : [ 2 ]
        }'
```

### 3.5 Deletar produto por id

| Informações |                                               |
|-------------|-----------------------------------------------|
| **recurso** | http://localhost:8081/api/admin/products/{id} |
| **método**  | DELETE                                        |

**Exemplo de requisição**:

```bash
curl -v -X DELETE --location "http://localhost:8081/api/admin/products/1" \
     -H 'Authorization: Bearer {token}'
```

### 3.6 Adicionar estoque

| Informações |                                                         |
|-------------|---------------------------------------------------------|
| **recurso** | http://localhost:8081/api/admin/inventory/1/increment/5 |
| **método**  | POST                                                    |

**Exemplo de requisição**:

```bash
curl -v -X PUT --location "http://localhost:8081/api/admin/inventory/1/increment/5" \
     -H 'Authorization: Bearer {token}'
```

### 3.7 Remover estoque

| Informações |                                                         |
|-------------|---------------------------------------------------------|
| **recurso** | http://localhost:8081/api/admin/inventory/1/decrement/1 |
| **método**  | POST                                                    |

**Exemplo de requisição**:

```bash
curl -v -X PUT --location "http://localhost:8081/api/admin/inventory/1/decrement/3" \
     -H 'Authorization: Bearer {token}'
```

## 4. Buscar produtos

### 4.1 Listar produtos

| Informações |                                         |
|-------------|-----------------------------------------|
| **recurso** | http://localhost:8081/api/shop/products |
| **método**  | GET                                     |

**Exemplo de requisição**:

```bash
curl -v -X GET --location "http://localhost:8081/api/shop/products"
```

### 4.2 Recuperar produto por id

| Informações |                                              |
|-------------|----------------------------------------------|
| **recurso** | http://localhost:8081/api/shop/products/{id} |
| **método**  | GET                                          |

**Exemplo de requisição**:

```bash
curl -v -X GET --location "http://localhost:8081/api/shop/products/1"
```

### 4.3 Listar produtos por categoria

| Informações |                                                               |
|-------------|---------------------------------------------------------------|
| **recurso** | http://localhost:8081/api/shop/products/category/{categoryId} |
| **método**  | GET                                                           |

**Exemplo de requisição**:

```bash
curl -v -X GET --location "http://localhost:8081/api/shop/products/category/1"
```

## 5. Carrinho

### 5.1 Adicionar produto ao carrinho

| Informações |                                         |
|-------------|-----------------------------------------|
| **recurso** | http://localhost:8081/api/shop/cart/add |
| **método**  | POST                                    |

**Exemplo de requisição**:

```bash
curl -v -X POST --location "http://localhost:8081/api/shop/cart/add" \
     -H "Content-Type: application/json" \
     -H 'Authorization: Bearer {token}' \
     -d '[
          {
            "productId" : 1,
            "quantity" : 2
          }
     ]'
```

### 5.2 Remover produto do carrinho

| Informações |                                            |
|-------------|--------------------------------------------|
| **recurso** | http://localhost:8081/api/shop/cart/remove |
| **método**  | POST                                       |

**Exemplo de requisição**:

```bash
curl -v -X POST --location "http://localhost:8081/api/shop/cart/remove" \
     -H "Content-Type: application/json" \
     -H 'Authorization: Bearer {token}' \
     -d '[
          {
            "productId" : 1,
            "quantity" : 1
          }
     ]'
```

### 5.3 Remover todos os produtos do carrinho

| Informações |                                                |
|-------------|------------------------------------------------|
| **recurso** | http://localhost:8081/api/shop/cart/remove/all |
| **método**  | POST                                           |

**Exemplo de requisição**:

```bash
curl -v -X POST --location "http://localhost/payment" \
     -H "Content-Type: application/json" \
     -H 'Authorization: Bearer {token}' \
     -d '{
           "cartId" : 0,
           "amount" : { },
           "paymentMethod" : ""
         }'
```

### 5.4 Listar produtos do carrinho

| Informações |                                     |
|-------------|-------------------------------------|
| **recurso** | http://localhost:8081/api/shop/cart |
| **método**  | GET                                 |

**Exemplo de requisição**:

```bash
curl -v -X GET --location "http://localhost:8081/api/shop/cart" \
     -H 'Authorization: Bearer {token}'
```

### 5.5 Valor total do carrinho

| Informações |                                           |
|-------------|-------------------------------------------|
| **recurso** | http://localhost:8081/api/shop/cart/total |
| **método**  | GET                                       |

**Exemplo de requisição**:

```bash
curl -v -X GET --location "http://localhost:8081/api/shop/cart/total" \
     -H 'Authorization: Bearer {token}'
```

## 6. Pagamento

### 6.1 Realizar pagamento

| Informações |                                           |
|-------------|-------------------------------------------|
| **recurso** | http://localhost:8081/api/payment/payment |
| **método**  | POST                                      |

**Exemplo de requisição**:

```bash
curl -v -X POST --location "http://localhost:8081/api/payment/payment" \
     -H "Content-Type: application/json" \
     -H 'Authorization: Bearer {token}' \
     -d '{
           "cartId" : 1,
           "amount" : 3000.0,
           "paymentMethod" : "PIX"
     }'
```

### 6.2 Recuperar pagamento por id

| Informações |                                                |
|-------------|------------------------------------------------|
| **recurso** | http://localhost:8081/api/payment/payment/{id} |
| **método**  | GET                                            |

**Exemplo de requisição**:

```bash
curl -v -X GET --location "http://localhost:8081/api/payment/payment/1" \
     -H 'Authorization: Bearer {token}'
```
