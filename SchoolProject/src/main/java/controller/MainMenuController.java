package controller;

import com.example.schoolproject.HelloApplication;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * controller.MainMenuController

 * @author Peter Moffett
 */
public class MainMenuController implements Initializable {
    public Button addPartB;
    public Button addProductB;
    public TableView partTable;
    public TableColumn partID;
    public TableColumn partName;
    public TableColumn partInventory;
    public TableColumn partPrice;
    public TableView productTable;
    public TableColumn productID;
    public TableColumn productName;
    public TableColumn productInventory;
    public TableColumn productPrice;
    public TextField searchPartString;
    public TextField searchProductString;

    private static Part selectedpart;
    private static Product selectedproduct;

    /**
     * get selectedpart to modify
     * @return selectedpart
     */
    public static Part partmodify() {

        return selectedpart;
    }

    /**
     * get selectedproduct to modify
     * @return selectedproduct
     */
    public static Product productmodify() {

        return selectedproduct;
    }

    /**
     * Initialize controller and populate tables
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Welcome to the main menu!");

        partTable.setItems(Inventory.getAllParts());
        partID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        productTable.setItems(Inventory.getAllProducts());
        productID.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /**
     * Load AddPartController
     * @param actionEvent Add Action button
     * @throws IOException
     */
    public void onClickAddPart(ActionEvent actionEvent) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("addPart.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddPart.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(),800,600);
        stage.setTitle("Add Part");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Load AddProductController
     * @param actionEvent Add Action Button
     * @throws IOException
     */
    public void onClickAddProduct(ActionEvent actionEvent) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddProduct.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(),920,670);
        stage.setTitle("Add Product");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Exit Program
     * @param actionEvent Exit button event
     */
    public void onClickExit(ActionEvent actionEvent) {

        System.exit(0);
    }

    /**
     * search based on value in searchbox textfield
     * @param actionEvent*/

    public void onSearchPart(ActionEvent actionEvent) {

        if (!searchPartString.getText().trim().isEmpty()) {
            try {
                int id = Integer.parseInt(searchPartString.getText());
                for (Part q : Inventory.getAllParts()) {
                    if (q.getId() == id) {
                        partTable.getSelectionModel().select(q);
                    }
                }
            } catch (NumberFormatException e) {
                String Name = (searchPartString.getText());
                for (Part q : Inventory.getAllParts()) {
                    if (q.getName().equals(Name)) {
                        partTable.getSelectionModel().select(q);
                    }
                }
            }
        }
    }

    /**
     * search based on value in searchbox textfield
     * @param actionEvent
     */
    public void onSearchProduct(ActionEvent actionEvent) {
        if (!searchProductString.getText().trim().isEmpty()) {
            try {
                int id = Integer.parseInt(searchProductString.getText());
                for (Product q : Inventory.getAllProducts()) {
                    if (q.getId() == id) {
                        productTable.getSelectionModel().select(q);
                    }
                }
            } catch (NumberFormatException e) {
                String Name = (searchProductString.getText());
                for (Product q : Inventory.getAllProducts()) {
                    if (q.getName().equals(Name)) {
                        productTable.getSelectionModel().select(q);
                    }
                }
            }
        }
    }

    /**
     * Delete selectedpart
     * if part wasn't selected, the Warning Dialog box will pop up
     * Return to MainMenu
     * @param actionEvent Delete action event button
     * @throws IOException
     */
    public void onDeletePart(ActionEvent actionEvent) throws IOException {
        boolean deleted = false;
        Part selectedItem = (Part) partTable.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("This is an error!");
            alert.setHeaderText("Select item to delete!");
            alert.setContentText("Part wasn't selected.  Please select.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("This is an alert");
            alert.setHeaderText("Selected part to be deleted!");
            alert.setContentText("Are you positive you want to delete selected part?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                Inventory.deletePart(selectedItem);
                deleted = true;
                if (deleted) {
                    //Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainMenu.fxml"));
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    Scene scene = new Scene(fxmlLoader.load(), 1110, 600);
                    stage.setTitle("Main Menu");
                    stage.setScene(scene);
                    stage.show();
                }
            } else {
               // Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainMenu.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(fxmlLoader.load(), 1110, 600);
                stage.setTitle("Main Menu");
                stage.setScene(scene);
                stage.show();
            }
        }

    }

    /**
     * Delete selected Product
     * if product wasn't selected, Warning Dialog Box pops up
     * Return to MainMenu
     * @param actionEvent Delete Button action
     * @throws IOException
     */
    public void onDeleteProduct(ActionEvent actionEvent) throws IOException {
        boolean deleted = false;
        Product selectedItem = (Product) productTable.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("This is an error!");
            alert.setHeaderText("Select item to delete!");
            alert.setContentText("Product wasn't selected.  Please select.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("This is an alert");
            alert.setHeaderText("Selected product to be deleted!");
            alert.setContentText("Are you positive you want to delete selected product?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                ObservableList<Part> associatedParts = selectedItem.getAllAssociatedPart();

                if (associatedParts.size() >= 1) {
                    alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("This is an error!");
                    alert.setHeaderText("Product has associated part(s)");
                    alert.setContentText("All associated parts to be removed prior to deletion");
                    alert.showAndWait();
                }
                else {
                    Inventory.deleteProduct(selectedItem);
                    deleted = true;
                    if (deleted) {
                        //Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainMenu.fxml"));
                        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        Scene scene = new Scene(fxmlLoader.load(), 1110, 600);
                        stage.setTitle("Main Menu");
                        stage.setScene(scene);
                        stage.show();
                    }
                }
            } else {
                //Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainMenu.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(fxmlLoader.load(), 1110, 600);
                stage.setTitle("Main Menu");
                stage.setScene(scene);
                stage.show();
            }
        }

    }

    /**
     * if part was selected, Load the ModifyPartController
     * if part wasn't selected Warning Dialog Box pops up
     * @param actionEvent Modify Button Action event
     * @throws IOException
     */
    public void onClickModifyPart(ActionEvent actionEvent) throws IOException {

        selectedpart = (Part) partTable.getSelectionModel().getSelectedItem();

        /*
         * Run Time Error.
         * Error was corrected by preventing User from passing "null" value.
         */
        if (selectedpart == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("This is an error!");
            alert.setHeaderText("Select item to modify!");
            alert.setContentText("Part wasn't selected.  Please select a part.");
            alert.showAndWait();
        }

        if (selectedpart != null) {
            //Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ModifyPart.fxml")));
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ModifyPart.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 920, 670);
            stage.setTitle("Modify Part Screen");
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * if product was selected, Load the ModifyProductController
     * if product wasn't selected, Warning Dialog Box pops up
     * @param actionEvent Modify Button action event
     * @throws IOException
     */
    public void onClickModifyProduct(ActionEvent actionEvent) throws IOException {

        selectedproduct = (Product) productTable.getSelectionModel().getSelectedItem();

        /*
         * Run Time Error.
         * Error was corrected by preventing User from passing "null" value.
         */
        if (selectedproduct == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("This is an error!");
            alert.setHeaderText("Select item to modify!");
            alert.setContentText("Product wasn't selected.  Please select a product.");
            alert.showAndWait();
        }

        if (selectedproduct != null) {
            //Parent root = FXMLLoader.load(getClass().getResource("ModifyProduct.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ModifyProduct.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 920, 670);
            stage.setTitle("Modify Product");
            stage.setScene(scene);
            stage.show();
        }
    }

}