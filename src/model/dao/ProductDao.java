package model.dao;

import model.Product;

import java.util.List;

public interface ProductDao {
    void insert(Product product);

    Product findById(int id);

    List<Product> findAll();

    void update(Product product);

    void delete(int id);
}
