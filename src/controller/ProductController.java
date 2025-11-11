package controller;

import model.Product;
import model.dao.ProductDao;

import java.util.List;

public class ProductController {
    private ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> findAll() {
        return productDao.findAll();
    }

    public Product findById(int id) {
        return productDao.findById(id);
    }

    public void insert(Product product) {
        productDao.insert(product);
    }

    public void update(Product product) {
        productDao.update(product);
    }

    public void delete(int id) {
        productDao.delete(id);
    }
}
