package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * model.inventory
 * @author Peter Moffett
 */
public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part newPart){
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }

    /**
     * search by partID
     *
     */
    public static Part lookupPart(int partId){
        ObservableList<Part> parts = Inventory.getAllParts();

        for (Part search: parts){
            if(search.getId() == partId){
                return search;
            }
        }

        return null;
    }

    /**
     * search by productID
     */
    public static Product lookupProduct(int productId){
        ObservableList<Product> products = Inventory.getAllProducts();

        for (Product search: products){
            if(search.getId() == productId){
                return search;
            }
        }

        return null;
    }

    /**
     * search by partName
     */
    public static ObservableList<Part> lookupPart(String partName){

        ObservableList<Part> found = FXCollections.observableArrayList();

        for (Part search: allParts){
            if(search.getName().toLowerCase().contains(partName)){
                found.add(search);
            }
        }

        return found;
    }

    /**
     * search by productName
     */
    public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> found = FXCollections.observableArrayList();
        ObservableList<Product> products = Inventory.getAllProducts();

        for (Product search: products){
            if(search.getName().toLowerCase().contains(productName)){
                found.add(search);
            }
        }

        return found;
    }

    public static void updatePart(int index, Part selectedPart){

    }

    public static void updateProduct(int index, Product selectedProduct){

    }

    /**
     * Remove selected Part from the part list
     */
    public static boolean deletePart(Part selectedPart){
        return allParts.remove(selectedPart);
    }

    /**
     * Remove selected Product from the product list
     */
    public static boolean deleteProduct(Product selectedProduct){
        return allProducts.remove(selectedProduct);
    }

    public static int getID() {
        int newId = 0;
        ObservableList<Part> allParts = Inventory.getAllParts();

        for (Part part : allParts) {
            if (part.getId() > newId) {
                newId = part.getId();
            }
        }
        return ++newId;
    }
    public static int getProductID() {
        int newProductId = 0;
        ObservableList<Product> allProducts = Inventory.getAllProducts();

        for (Product product : allProducts) {
            if (product.getId() > newProductId) {
                newProductId = product.getId();
            }
        }
        return ++newProductId;
    }
}
