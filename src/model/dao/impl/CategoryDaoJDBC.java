package model.dao.impl;

import model.Category;
import model.dao.CategoryDao;

import java.sql.Connection;
import java.util.List;

public class CategoryDaoJDBC implements CategoryDao {
    private Connection connection;

    public CategoryDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Category category) {
        
    }

    @Override
    public Category findById(int id) {
        return null;
    }

    @Override
    public List<Category> findAll() {
        return List.of();
    }

    @Override
    public void update(Category category) {

    }

    @Override
    public void delete(int id) {

    }
}
