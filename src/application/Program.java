package application;

import view.ProductView;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductView productView = new ProductView();
        productView.crudProduct(sc);
        sc.close();
    }
}
