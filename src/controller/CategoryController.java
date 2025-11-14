package controller;

import model.Category;
import model.Product;
import model.dao.CategoryDao;
import model.dao.ProductDao;

import java.util.List;

public class CategoryController {
    private CategoryDao categoryDao;

    public CategoryController(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    public Category findById(int id) {
        return categoryDao.findById(id);
    }

    public void insert(Category category) {
        categoryDao.insert(category);
    }

    public void update(Category category) {
        categoryDao.update(category);
    }

    public void delete(int id) {
        categoryDao.delete(id);
    }
}
