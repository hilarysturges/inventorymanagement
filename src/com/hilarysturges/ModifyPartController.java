/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hilarysturges;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hilary
 */
public class ModifyPartController implements Initializable {

    @FXML public Button cancelButton;
    
    @FXML public RadioButton outsourcedButton;
    @FXML public RadioButton inHouseButton;
    @FXML public ToggleGroup radioButtonToggle;
    
    @FXML public Label machineAndCompLabel;
    @FXML public TextField machineAndCompTF;
    
    private Part selectedPart;
    private Outsourced selectedPartOutsourced;
    private InHouse selectedPartInHouse;
    
    @FXML public TextField idPartTF;
    @FXML public TextField nameTF;
    @FXML public TextField priceTF;
    @FXML public TextField inventoryTF;
    @FXML public TextField minTF;
    @FXML public TextField maxTF;
    
    @FXML public Button saveButton;
    
    public void saveButtonPushed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxmlGUI.fxml"));
        Parent tableViewParent = loader.load();
        Scene tableViewScene = new Scene(tableViewParent);
        FxmlGUIController controller = loader.getController();
        if (this.radioButtonToggle.getSelectedToggle().equals(this.inHouseButton)) {
            //create InHouse object
            InHouse part = new InHouse(Integer.parseInt(idPartTF.getText()),
                             nameTF.getText(),
                             Double.parseDouble(priceTF.getText()),
                             Integer.parseInt(inventoryTF.getText()),
                             Integer.parseInt(maxTF.getText()),
                             Integer.parseInt(minTF.getText()),
                             Integer.parseInt(machineAndCompTF.getText()));
            controller.addParts(part);
        } else {
            //create Outsourced object
            Outsourced part = new Outsourced(Integer.parseInt(idPartTF.getText()),
                             nameTF.getText(),
                             Double.parseDouble(priceTF.getText()),
                             Integer.parseInt(inventoryTF.getText()),
                             Integer.parseInt(maxTF.getText()),
                             Integer.parseInt(minTF.getText()),
                             machineAndCompTF.getText());
            controller.addParts(part);
        }
        
          if (Integer.parseInt(minTF.getText()) > Integer.parseInt(maxTF.getText())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.NONE);
            alert.setTitle("Minimum > Maximum");
            alert.setHeaderText("Minimum is greater than maximum");
            alert.setContentText("Data will not be added");
            alert.showAndWait();
        } else {
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        }
    }
    
    
    public void initData(Part part) {
        selectedPart = part;
        idPartTF.setText(Integer.toString(selectedPart.getId()));
        nameTF.setText(selectedPart.getName());
        priceTF.setText(Double.toString(selectedPart.getPrice()));
        inventoryTF.setText(Integer.toString(selectedPart.getStock()));
        minTF.setText(Integer.toString(selectedPart.getMin()));
        maxTF.setText(Integer.toString(selectedPart.getMax()));
        if (this.radioButtonToggle.getSelectedToggle().equals(this.inHouseButton)) {
            machineAndCompTF.setText(Integer.toString(selectedPartInHouse.getMachineId())); }
    }

    public void cancelButtonPushed(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("fxmlGUI.fxml"));        
        //alert window    
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.NONE);
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText("Cancel");
            alert.setContentText("Are you sure you want to proceed?");
            //add if statement
            
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    Scene tableViewScene = new Scene(tableViewParent);
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(tableViewScene);
                    window.show();
                }
            });
    }
    
    public void radioButtonSelected() {
        if (this.radioButtonToggle.getSelectedToggle().equals(this.inHouseButton)) {
            //make machine ID visible
            machineAndCompTF.setVisible(true);
            machineAndCompLabel.setText("Machine ID");
            machineAndCompTF.setText(Integer.toString(selectedPartInHouse.getMachineId())); }
        if (this.radioButtonToggle.getSelectedToggle().equals(this.outsourcedButton)) {
            //make company name visible
            machineAndCompTF.setVisible(true);
            machineAndCompLabel.setText("Company Name");
            machineAndCompTF.setText(selectedPartOutsourced.getCompanyName()); }
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        radioButtonToggle = new ToggleGroup();
        this.inHouseButton.setToggleGroup(radioButtonToggle);
        this.outsourcedButton.setToggleGroup(radioButtonToggle);
    }    
    
}

