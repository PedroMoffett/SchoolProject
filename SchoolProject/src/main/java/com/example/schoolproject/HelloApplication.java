package com.example.schoolproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Product;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 500);
        stage.setTitle("Inventory");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Test Data
     */
    private static void addTestData(){
        InHouse inHousePart1 = new InHouse(1,"Mouse",49.99,10,1,12,117);
        Inventory.addPart(inHousePart1);

        InHouse inHousePart2 = new InHouse(2,"Keyboard",59.99,10,1,12,102);
        Inventory.addPart(inHousePart2);

        Product inHousePart3 = new Product(3,"Headset",99.99,10,1,15);
        Inventory.addProduct(inHousePart3);

        Outsourced outsourcedPart1 = new Outsourced(1,"GPU", 599.99,10,1,112,"Best Buy");
        Inventory.addPart(outsourcedPart1);

        Outsourced outsourcedPart2 = new Outsourced(2,"PSU", 199.99,10,1,112,"Fry's Electronics");
        Inventory.addPart(outsourcedPart2);

        Product outsourcedPart3 = new Product(3,"CPU", 439.99,10,1,15);
        Inventory.addProduct(outsourcedPart3);

    }

    /**
     * launching the application
     */
    public static void main(String[] args){
        addTestData();
        launch(args);
    }
}