package com.example.schoolproject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Product;

import java.io.IOException;

/*
To improve the functionality of this project I would like to connect this code to a database so the information may be more streamlined
logical error that I encountered was the inability to have the min be greater than the max in any screen within the GUI.
Run Time Error can be found in MainMenuController passing "null" value.
 */

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 500);
        stage.setTitle("Inventory Management System");
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

        InHouse inHousePart3 = new InHouse(3,"Headset",99.99,10,1,15, 193);
        Inventory.addPart(inHousePart3);

        Product outsourcedPart1  = new Product(1,"GPU", 599.99,10,1,112);
        Inventory.addProduct(outsourcedPart1);

        Product outsourcedPart2 = new Product(2,"PSU", 199.99,10,1,112);
        Inventory.addProduct(outsourcedPart2);

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