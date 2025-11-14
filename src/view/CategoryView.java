package view;

import controller.CategoryController;
import model.Category;
import model.dao.DaoFactory;
import utils.Utility;

import java.util.List;
import java.util.Scanner;

public class CategoryView {
    public void crudCategory(Scanner sc) {
        CategoryController categoryController = new CategoryController(DaoFactory.createCaregoryDao());

        Utility.printSection("Find Category by ID");
        System.out.print("Enter the ID of the category you want to search for: ");
        int id = sc.nextInt();
        Category category = categoryController.findById(id);
        System.out.println(category);

        Utility.printSection("List All Products");
        List<Category> listProduct = categoryController.findAll();
        listProduct.forEach(System.out::println);

        Utility.printSection("Insert New Product");
        System.out.print("Id category: ");
        int idCategory = sc.nextInt();
        sc.nextLine();

        category = categoryController.findById(idCategory);

        if (category == null) {
            System.out.print("Name category: ");
            String nameCategory = sc.nextLine();

            category = new Category(idCategory, nameCategory, null);

            categoryController.insert(category);
            System.out.println("Category inserted successfully: " + category);
        } else {
            System.out.println("Category found: " + category);
        }

        Utility.printSection("Update Category");
        System.out.print("Enter the ID of the category to update: ");
        idCategory = sc.nextInt();
        sc.nextLine();

        category = categoryController.findById(idCategory);

        if (category != null) {
            System.out.println("Current category name: " + category.getName());

            System.out.print("Enter the new category name: ");
            String nameCategory = sc.nextLine();

            category.setName(nameCategory);
            categoryController.update(category);

            System.out.println("Category successfully updated: " + category);
        } else {
            System.out.println("Category not found.");
        }

        Utility.printSection("Delete category");
        System.out.print("Enter the ID of the category to delete: ");
        idCategory = sc.nextInt();
        categoryController.delete(idCategory);
        System.out.println("Category deleted successfully!");
    }
}
