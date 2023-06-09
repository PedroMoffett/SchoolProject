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
import java.util.ResourceBundle;

/**
 * controller.ModifyPartController
 * @author Peter Moffett
 */
public class ModifyPartController implements Initializable {
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

    private Part selectedPart;

    /**
     * Initialize Controller
     * Populate text fields with selectedpart data
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Modify Part!");
        selectedPart =  MainMenuController.partmodify();

        if (selectedPart instanceof InHouse) {
            inHouseRadioIcon.setSelected(true);
            addPartTextField.setText("MachineID");
            addPartDataField.setText(String.valueOf(((InHouse) selectedPart).getMachineId()));
        }

        if (selectedPart instanceof Outsourced){
            outSourcedRadioIcon.setSelected(true);
            addPartTextField.setText("Company Name");
            addPartDataField.setText(((Outsourced) selectedPart).getCompanyName());
        }

        partIDField.setText(String.valueOf(selectedPart.getId()));
        partNameField.setText(selectedPart.getName());
        partInventoryField.setText(String.valueOf(selectedPart.getStock()));
        partPriceField.setText(String.valueOf(selectedPart.getPrice()));
        partMaxField.setText(String.valueOf(selectedPart.getMax()));
        partMinField.setText(String.valueOf(selectedPart.getMin()));

    }

    /**
     * ModifyPart closes, and application returns to MainMenu
     * @param actionEvent
     * @throws IOException
     */
    public void onClickCancelPart(ActionEvent actionEvent) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainMenu.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1110, 600);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * set TextLabel to "MachineID"
     * @param actionEvent Inhouse Radio Button
     */
    public void onClickInHouseRadio(ActionEvent actionEvent) {
        addPartTextField.setText("MachineID");
    }

    /**
     * set Text Label to "Company Name"
     * @param actionEvent outsourced Radio Button
     */
    public void onClickOutSourcedRadio(ActionEvent actionEvent) {
        addPartTextField.setText("Company Name");
    }

    /**
     * Part modified and returns to Main Menu after validating and logic are checked
     * @param actionEvent Save button action event
     * @throws IOException
     */
    public void onClickSavePart(ActionEvent actionEvent) throws IOException {
        ObservableList<Part> parts = Inventory.getAllParts();
        int id = selectedPart.getId();
        String name = partNameField.getText();
        Double price = Double.parseDouble(partPriceField.getText());
        int inventory = Integer.parseInt(partInventoryField.getText());
        int max = Integer.parseInt(partMaxField.getText());
        int min = Integer.parseInt(partMinField.getText());
        int machineID;
        String CompanyName;

        boolean passed = false;
        try {

            boolean isinventoryvalid = true;
            boolean isminvalid = true;

            if(min < 0 || max < 0 || inventory < 0 || price < 0){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("There is an error!");
                alert.setHeaderText("Form contains unacceptable data!");
                alert.setContentText("Negative number is invalid!");
                alert.showAndWait();
            }

            if (min > 0 && max > 0 && inventory > 0 && price > 0) {
                if (min >= max || min <= 0) {
                    isminvalid = false;
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("There is an error!");
                    alert.setHeaderText("Form contains unacceptable data!");
                    alert.setContentText("Min cannot be greater than Max.");
                    alert.showAndWait();
                } else if (inventory > max || inventory < min) {
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
                //machineID = Integer.parseInt(addPartDataField.getText());
                if (inHouseRadioIcon.isSelected()) {
                    machineID = Integer.parseInt(addPartDataField.getText());
                    InHouse inHousePart = new InHouse(
                            id
                            , name
                            , price
                            , inventory
                            , min
                            , max
                            , machineID);
                    Inventory.addPart(inHousePart);
                    passed = true;
                }
                if (outSourcedRadioIcon.isSelected()) {
                    CompanyName = addPartDataField.getText();
                    Outsourced outsourced = new Outsourced(
                            id
                            ,name
                            ,price
                            ,inventory
                            ,min
                            ,max
                            ,CompanyName);
                    Inventory.addPart(outsourced);
                    passed = true;
                }
            }
        } catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("There is an error!");
            alert.setHeaderText("There was an error adding part!");
            alert.setContentText("Form contains blank fields or unacceptable data.");
            alert.showAndWait();
        }

        if (passed) {
            Inventory.deletePart(selectedPart);
            //Parent root = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainMenu.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 1110, 600);
            stage.setTitle("Main Menu");
            stage.setScene(scene);
            stage.show();
        }
    }
}