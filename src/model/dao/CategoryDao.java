package model.dao;

import model.Category;
import model.Product;

import java.util.List;

public interface CategoryDao {
    void insert(Category category);

    Category findById(int id);

    List<Category> findAll();

    void update(Category category);

    void delete(int id);
}
