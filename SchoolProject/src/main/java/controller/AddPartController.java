package controller;

import com.example.schoolproject.HelloApplication;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * controller.AddPartController
 *
 * @author Peter Moffett
 */
public class AddPartController implements Initializable {
    public Label addPartTextField;
    public TextField partNameField;
    public TextField partPriceField;
    public TextField partInventoryField;
    public TextField partMaxField;
    public TextField partMinField;
    public TextField partIDField;
    public RadioButton inHouseRadioIcon;
    public RadioButton outSourcedRadioIcon;
    public TextField addPartDataField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("AddPart!");
    }

    public void onClickCancelPart(ActionEvent actionEvent) throws IOException {
        //Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainMenu.fxml")));
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainMenu.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1110, 600);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Set TextLabel to "MachineID"
     * @param actionEvent InHouse Radio Button event
     */
    public void onClickInHouseRadio(ActionEvent actionEvent) {
        addPartTextField.setText("MachineID");
    }

    /**
     * Set TextLabel to "Company Name"
     * @param actionEvent OutSourced Radio Button event
     */
    public void onClickOutSourcedRadio(ActionEvent actionEvent) {
        addPartTextField.setText("Company Name");
    }


    public void onClickSavePart(ActionEvent actionEvent) throws IOException {

        ObservableList<Part> parts = Inventory.getAllParts();
        int id = 1000+parts.size();
        partIDField = new TextField(String.valueOf(++id));


        String name = partNameField.getText();
        String price = partPriceField.getText();
        String inventory = partInventoryField.getText();
        String max = partMaxField.getText();
        String min = partMinField.getText();
        String machineID = addPartDataField.getText();

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
                if (inHouseRadioIcon.isSelected()) {
                    InHouse inHousePart = new InHouse(
                            Integer.parseInt(partIDField.getText())
                            , name
                            , Double.parseDouble(price)
                            , Integer.parseInt(inventory)
                            , Integer.parseInt(min)
                            , Integer.parseInt(max)
                            , Integer.parseInt(machineID));
                    Inventory.addPart(inHousePart);
                    passed = true;
                }
                if (outSourcedRadioIcon.isSelected()) {
                    Outsourced outsourced = new Outsourced(
                            Integer.parseInt(partIDField.getText())
                            , partNameField.getText()
                            , Double.parseDouble(partPriceField.getText())
                            , Integer.parseInt(partInventoryField.getText())
                            , Integer.parseInt(partMinField.getText())
                            , Integer.parseInt(partMaxField.getText())
                            , addPartDataField.getText());
                    Inventory.addPart(outsourced);
                    passed = true;
                }
            }
        } catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("There is an error!");
            alert.setHeaderText("Error adding part!");
            alert.setContentText("Form contains blank fields or unacceptable data.");
            alert.showAndWait();
        }

        if (passed) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainMenu.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 1110, 600);
            stage.setTitle("Main Menu");
            stage.setScene(scene);
            stage.show();
        }
    }
}