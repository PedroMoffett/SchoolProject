package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * model.product
 * @author Peter Moffett
 */
public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * Constructor for the new instance of product
     * @param id of the product
     * @param name of the product
     * @param price of the Product
     * @param stock Inventory level of product
     * @param min value of the product
     * @param max value of the product
     */
    public Product (int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Get list of associatedparts for product
     * @return parts list
     */
    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }

    /**
     * @param associatedParts to set
     */
    public void setAssociatedParts(ObservableList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return min
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Add associatedparts list for product
     * @param part to add
     */
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }

    /**
     * Delete associatedparts from Product
     * @param selectedAssociated part to delete
     * @return Boolean indication for associated part
     */
    public boolean deleteAssociatedPart(Part selectedAssociated){
        return true;
    }

    /**
     * Get list of associatedparts for product
     * @return list of associatedParts
     */
    public ObservableList<Part> getAllAssociatedPart(){
        return associatedParts;
    }
}