package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part part) {
        allParts.add(part);
    }

    public static void addProduct(Product product) {
        allProducts.add(product);
    }

    public static Part lookupPart(int id) {
        for(Part part : allParts) {
            if(part.getId() == id)
                return part;
        }
        return null;
    }

    public static Product lookupProduct(int id) {
        for(Product product : allProducts) {
            if(product.getId() == id)
                return product;
        }
        return null;
    }

    public static ObservableList<Part> lookupPart(String name) {
        ObservableList<Part> partsMatching = FXCollections.observableArrayList();
        for(Part part : allParts) {
            if(part.getName().equals(name))
                partsMatching.add(part);
        }
        return partsMatching;
    }

    public static ObservableList<Product> lookupProduct(String name) {
        ObservableList<Product> productsMatching = FXCollections.observableArrayList();
        for(Product product : allProducts) {
            if(product.getName().equals(name))
                productsMatching.add(product);
        }
        return productsMatching;
    }

    public static void updatePart(int index, Part selectedPart) {

    }

    public static void updateProduct(int index, Product selectedProduct) {

    }

    public static boolean deletePart(Part selectedPart) {
        return true;
    }

    public static boolean deleteProduct(Product selectedProduct) {
        return true;
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
