package application;

import view.CategoryView;
import view.ProductView;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CategoryView categoryView = new CategoryView();
        categoryView.crudCategory(sc);
        ProductView productView = new ProductView();
        productView.crudProduct(sc);
        sc.close();
    }
}
