package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    private static int idCounter = 0;

    public static int createPartId() {
        return idCounter++;
    }

    private static int idCounter1 = 0;

    public static int createProductId() {
        return idCounter1++;
    }

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
        for (Product product : allProducts) {
            if(product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public static ObservableList<Part> lookupPart(String name) {
        ObservableList<Part> partsMatching = FXCollections.observableArrayList();
        for (Part part : allParts) {
            if(String.valueOf(part.getName()).contains(name)) {
                partsMatching.add(part);
            }
        }
        return partsMatching;
    }

    public static ObservableList<Product> lookupProduct(String name) {
        ObservableList<Product> productsMatching = FXCollections.observableArrayList();
        for (Product product : allProducts) {
            if(String.valueOf(product.getName()).contains(name)) {
                productsMatching.add(product);
            }
        }
        return productsMatching;
    }

    public static void updatePart(int index, Part partSelected) {
        allParts.set(index, partSelected);
    }

    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    public static boolean deletePart(Part partSelected) {
        if(allParts.contains(partSelected)) {
            allParts.remove(partSelected);
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean deleteProduct(Product productSelected) {
        if(allProducts.contains(productSelected)) {
            allProducts.remove(productSelected);
            return true;
        }
        else {
            return false;
        }
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
