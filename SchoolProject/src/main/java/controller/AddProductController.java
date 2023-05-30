package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * controller.AddProductController
 * @author Peter Moffett
 */
public class AddProductController implements Initializable {
    public TableView allTable;
    public TableColumn allPartIDCol;
    public TableColumn allNameCol;
    public TableColumn allInventoryCol;
    public TableColumn allPriceCol;
    public TableView fewTable;
    public TableColumn fewPartIDCol;
    public TableColumn fewNameCol;
    public TableColumn fewInventoryCol;
    public TableColumn fewPriceCol;
    public Button addAssociatedPartB;
    public Button deleteAssociatedPartB;
    public TextField productIDField;
    public TextField productMinField;
    public TextField productNameField;
    public TextField productInventoryField;
    public TextField productPriceField;
    public TextField productMaxField;
    public TextField searchPartString;

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * Initialize the Controller and populate the Tables views
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("AddProduct!");

        allTable.setItems(Inventory.getAllParts());
        allPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        allNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        allInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        allPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));


        fewTable.setItems(associatedParts);
        fewPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        fewNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        fewInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        fewPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));


    }

    /**
     * add selectedparts to the associatedpart table
     * if part was not selected the Warning Dialog box popped up.
     * @param actionEvent Add button action event
     */
    public void addAssociatedPart(ActionEvent actionEvent) {
        System.out.println("On Add Button!");
        Part selectedItem = (Part) allTable.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("There is an error!");
            alert.setHeaderText("Please select an item to add!");
            alert.setContentText("Part wasn't selected");
            alert.showAndWait();
        }
        else {
            associatedParts.add(selectedItem);
            fewTable.setItems(associatedParts);
        }
    }

    /**
     * remove selectedparts from the associatedTable
     * if part was not selected the Warning Dialog box popped up
     * @param actionEvent Remove AssociatedPart Button action event
     */
    public void deleteAssociatedPart(ActionEvent actionEvent) {
        System.out.println("On Remove Button!");
        Part selectedItem = (Part) fewTable.getSelectionModel().getSelectedItem();

        if (selectedItem == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("There is an error!");
            alert.setHeaderText("Select an item to remove!");
            alert.setContentText("Part was not selected.  Please select a Part.");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("This is an alert!");
            alert.setHeaderText("Selected part will be deleted!");
            alert.setContentText("Are you positive you want to delete this selected part?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                associatedParts.remove(selectedItem);
                fewTable.setItems(associatedParts);
            }
        }
    }

    /**
     * AddProduct closes, and application returns to MainMenu
     * @param actionEvent Cancel Button action
     * @throws IOException
     */
    public void onClickCancelProduct(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1110, 600);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Add new product and Return to Main Menu after validating and logic are checked
     * @param actionEvent Save button action event
     * @throws IOException
     */
    public void onClickSaveProduct(ActionEvent actionEvent) throws IOException{
        ObservableList<Product> products = Inventory.getAllProducts();
        int id = 7000+products.size();
        productIDField = new TextField(String.valueOf(++id));

        String name = productNameField.getText();
        String price = productPriceField.getText();
        String inventory = productInventoryField.getText();
        String max = productMaxField.getText();
        String min = productMinField.getText();

        boolean passed = false;
        try {

            boolean isinventoryvalid = true;
            boolean isminvalid = true;

            if(Integer.parseInt(min) < 0 || Integer.parseInt(max) < 0 || Integer.parseInt(inventory) < 0 || Double.parseDouble(price) < 0){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("There is an error!");
                alert.setHeaderText("Form contains unacceptable data!");
                alert.setContentText("Negative number is invalid!");
                alert.showAndWait();
            }

            if (Integer.parseInt(min) > 0 && Integer.parseInt(max) > 0 && Integer.parseInt(inventory) > 0 && Double.parseDouble(price) > 0) {
                if (Integer.parseInt(min) >= Integer.parseInt(max) || Integer.parseInt(min) <= 0) {
                    isminvalid = false;
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("There is an error!");
                    alert.setHeaderText("Form contains unacceptable data!");
                    alert.setContentText("Min cannot be greater than Max.");
                    alert.showAndWait();
                } else if (Integer.parseInt(inventory) > Integer.parseInt(max) || Integer.parseInt(inventory) < Integer.parseInt(min)) {
                    isinventoryvalid = false;
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("There is an error!");
                    alert.setHeaderText("Form contains unacceptable data!");
                    alert.setContentText("Inventory must fall between Min and Max.");
                    alert.showAndWait();
                }
            } else {
                isminvalid = false;
                isinventoryvalid = false;
            }

            if (isminvalid && isinventoryvalid) {
                Product newProduct = new Product(
                        Integer.parseInt(productIDField.getText())
                        , name
                        , Double.parseDouble(price)
                        , Integer.parseInt(inventory)
                        , Integer.parseInt(min)
                        , Integer.parseInt(max));

                for (Part part : associatedParts) {
                    newProduct.addAssociatedPart(part);
                }

                Inventory.addProduct(newProduct);
                passed = true;
            }

        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("There is an error!");
            alert.setHeaderText("There was an error adding product!");
            alert.setContentText("Form contains blank fields or unacceptable data.");
            alert.showAndWait();
        }

        if (passed) {
            Parent root = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1110, 600);
            stage.setTitle("Main Menu");
            stage.setScene(scene);
            stage.show();
        }

    }

    /**
     * search based on value in the searchbox text
     * @param keyEvent
     */
    public void onSearchPart(KeyEvent keyEvent) {
        String q = searchPartString.getText().toLowerCase();

        ObservableList<Part> parts = Inventory.lookupPart(q);

        try {
            if (parts.size() == 0){
                int id = Integer.parseInt(q);
                Part search = Inventory.lookupPart(id);
                if(search != null)
                    parts.add(search);
            }
        } catch (NumberFormatException e){

        }

        allTable.setItems(parts);
    }
}