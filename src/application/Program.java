package application;

import model.Product;
import model.dao.DaoFactory;
import model.dao.ProductDao;

import java.util.List;

public class Program {
    public static void main(String[] args) {
        ProductDao productDao = DaoFactory.createProductDao();

        System.out.println("FindAll Product");
        List<Product> listProduct = productDao.findAll();
        listProduct.forEach(System.out::println);
    }
}
