# 🧱 product-manager-JDBC

## 📝 Descrição
Projeto simples em **Java** usando **JDBC** para conectar ao **MySQL**.  
O sistema faz o **cadastro de produtos e categorias**, permitindo inserir, listar, atualizar e excluir registros.  
Serve como prática dos principais conceitos de JDBC, DAO e relacionamento entre tabelas.

---

## 🚀 Tecnologias
- Java
- MySQL
- JDBC
- DAO Pattern

---

## ⚙️ Funcionalidades

### Categorias
- Inserir categoria
- Listar categorias

### Produtos
- Inserir produto (com categoria)
- Atualizar preço ou quantidade
- Listar produtos (com nome da categoria)
- Buscar produtos por categoria
- Excluir produto

---

## 🗄️ Script SQL

```sql
CREATE DATABASE product_db;
USE product_db;

CREATE TABLE category (
    Id INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(60) NOT NULL
);

CREATE TABLE product (
    Id INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(100) NOT NULL,
    Price DOUBLE NOT NULL,
    Quantity INT NOT NULL,
    CategoryId INT,
    FOREIGN KEY (CategoryId) REFERENCES category(Id)
);

INSERT INTO category (Name)
VALUES ('Informática'), ('Vestuário'), ('Alimentos');

INSERT INTO product (Name, Price, Quantity, CategoryId)
VALUES 
('Mouse Sem Fio', 80.00, 20, 1),
('Camisa Polo', 120.00, 30, 2),
('Café Expresso', 25.00, 50, 3);
```

---

## 📂 Estrutura

```
src/
 ├── application/Program.java
 ├── db/DB.java
 ├── db/DbException.java
 ├── model/entities/Product.java
 ├── model/entities/Category.java
 ├── model/dao/ProductDao.java
 ├── model/dao/CategoryDao.java
 └── model/dao/impl/
      ├── ProductDaoJDBC.java
      └── CategoryDaoJDBC.java
```

---

## 🧠 Conceitos praticados
- Conexão JDBC
- CRUD
- DAO pattern
- Relacionamento 1:N (categoria e produtos)
- PreparedStatement e ResultSet

---

## 🖥️ Como executar
1. Clone o projeto
2. Crie o banco de dados com o script acima
3. Configure `DB.java` com seu usuário e senha do MySQL
4. Execute `Program.java`

---

## 👤 Autor
**Cledson**  
Projeto criado para praticar **JDBC com Java** (2025).  
