package model.dao;

import model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findAll();
}
