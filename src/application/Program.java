package application;

import controller.ProductController;
import model.Category;
import model.Product;
import model.dao.DaoFactory;
import model.dao.ProductDao;

import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ProductController productController = new ProductController(DaoFactory.createProductDao());

        System.out.print("Enter the ID of the product you want to search for: ");
        int id = sc.nextInt();

        Product product = productController.findById(id);
        System.out.println(product);

        System.out.println();

        System.out.println("\n=== LIST ALL PRODUCTS ===");
        System.out.print("Search for all products ");
        List<Product> listProduct = productController.findAll();
        listProduct.forEach(System.out::println);

        System.out.println();

        System.out.println("\n=== INSERT NEW PRODUCT ===");
        System.out.print("Id category: ");
        int idCategory = sc.nextInt();

        Category category = new Category(idCategory, "", null);

        sc.nextLine();

        System.out.print("Name product: ");
        String nameProduct = sc.nextLine();

        System.out.print("Price product: ");
        double priceProduct = sc.nextDouble();

        System.out.print("quantity product: ");
        double quantityProduct = sc.nextDouble();

        product = new Product(null, nameProduct, priceProduct, quantityProduct, category);
        productController.insert(product);

        System.out.println("Product inserted successfully: " + product);

        System.out.println();

        System.out.println("\n=== UPDATE PRODUCT ===");
        System.out.println("Update Product");
        product = productController.findById(4);
        product.setPrice(30.0);
        productController.update(product);
        System.out.println(product);

        System.out.println("\n=== DELETE PRODUCT ===");
        System.out.println("Delete Product");
        productController.delete(5);
    }
}
