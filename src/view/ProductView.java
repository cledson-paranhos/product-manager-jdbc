package view;

import controller.ProductController;
import model.Category;
import model.Product;
import model.dao.DaoFactory;
import utils.Utility;

import java.util.List;
import java.util.Scanner;

public class ProductView {

    public void crudProduct(Scanner sc) {
        ProductController productController = new ProductController(DaoFactory.createProductDao());

        Utility.printSection("Find Product by ID");
        System.out.print("Enter the ID of the product you want to search for: ");
        int id = sc.nextInt();
        Product product = productController.findById(id);
        System.out.println(product);

        Utility.printSection("List All Products");
        List<Product> listProduct = productController.findAll();
        listProduct.forEach(System.out::println);

        Utility.printSection("Insert New Product");
        System.out.print("Id category: ");
        int idCategory = sc.nextInt();
        Category category = new Category(idCategory, "", null);

        sc.nextLine();

        System.out.print("Name product: ");
        String nameProduct = sc.nextLine();

        System.out.print("Price product: ");
        double priceProduct = sc.nextDouble();

        System.out.print("Quantity product: ");
        double quantityProduct = sc.nextDouble();

        product = new Product(null, nameProduct, priceProduct, quantityProduct, category);
        productController.insert(product);
        System.out.println("Product inserted successfully: " + product);

        Utility.printSection("Update Product");
        System.out.print("Enter new price: ");
        priceProduct = sc.nextDouble();
        product.setPrice(priceProduct);
        productController.update(product);
        System.out.println("Product updated: " + product);

        Utility.printSection("Delete Product");
        System.out.print("Enter the ID of the product to delete: ");
        id = sc.nextInt();
        productController.delete(id);
        System.out.println("Product deleted successfully!");
    }
}
