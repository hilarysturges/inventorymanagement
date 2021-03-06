/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hilarysturges;

import java.io.IOException;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hilary
 */
public class FxmlGUIController implements Initializable {

    @FXML public TableView<Part> tableViewPart;
    @FXML public TableColumn<Part, Integer> idPartColumn;
    @FXML public TableColumn<Part, String> namePartColumn;
    @FXML public TableColumn<Part, Integer> inventoryPartColumn;
    @FXML public TableColumn pricePerUnitPartColumn;
    
    @FXML public TableView<Product> tableViewProduct;
    @FXML public TableColumn<Product, Integer> idProductColumn;
    @FXML public TableColumn<Product, String> nameProductColumn;
    @FXML public TableColumn<Product, Integer> inventoryProductColumn;
    @FXML public TableColumn pricePerUnitProductColumn;
    
    @FXML public Button addPartButton;
    @FXML public Button modifyPartButton;
    @FXML public Button deletePartButton;
    
    @FXML public Button addProductButton;
    @FXML public Button modifyProductButton;
    @FXML public Button deleteProductButton;
    
    @FXML public Button exitButton;
    
    @FXML public TextField searchBarParts;
    @FXML public Button searchPartsButton;
    @FXML public TextField searchBarProducts;
    @FXML public Button searchProductsButton;
    
    static int counterParts;
    static int counterProducts;
    
    static ObservableList<Part> partslist = FXCollections.observableArrayList();
    static ObservableList<Product> productslist = FXCollections.observableArrayList();

    
    public void exitSession(ActionEvent event)  throws IOException {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
    
    public static void addParts(Part part) {
        partslist.add(part);
        //tableViewPart.getItems().add(part);
    }
    
    public void addProducts(Product product) {
        productslist.add(product);
        //tableViewProduct.getItems().add(product);
    }
    
    public void deletePart(Part part) {
        ObservableList<Part> selectedRows, allParts;
        allParts = tableViewPart.getItems();
        selectedRows = tableViewPart.getSelectionModel().getSelectedItems();
        for (Part part1 : selectedRows) {
            allParts.remove(part);
        }
        
    }
    
    public void deleteChangedPart (Part part) {
        partslist.remove(part);
    }
    
    public void deletePartButtonPushed() {
        ObservableList<Part> selectedRows, allParts;
        allParts = tableViewPart.getItems();
        selectedRows = tableViewPart.getSelectionModel().getSelectedItems();
        //alert window    
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.NONE);
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText("Delete data");
            alert.setContentText("Are you sure you want to proceed?");
            //add if statement
            
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    for (Part part : selectedRows) {
                        System.out.println(selectedRows);
                    allParts.remove(part);
                    }
                    
                }
            });
    }
    
    
    public void deleteProductButtonPushed() {
        ObservableList<Product> selectedRows, allProducts;
        allProducts = tableViewProduct.getItems();
        selectedRows = tableViewProduct.getSelectionModel().getSelectedItems();
        //alert window    
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.NONE);
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText("Delete data");
            alert.setContentText("Are you sure you want to proceed?");
            //add if statement
            
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    for (Product product : selectedRows) {
                    allProducts.remove(product);
                    }
                }
            });
    }
    
    public static ObservableList<Part> getParts() {
        if (counterParts == 0) {
        partslist.add(new Outsourced(1,"Part 1",5.00,5,10,1,"Company ABC"));
        partslist.add(new InHouse(2,"Part 2",10.00,10,20,5,123));
        partslist.add(new Outsourced(3,"Part 3",15.00,12,15,2,"Company DEF"));
        counterParts++; }
        return partslist;
    }
    
    public ObservableList<Product> getProducts() {
        if (counterProducts == 0) {
        productslist.add(new Product(1,"Product 1",5.00,5,10,1));
        productslist.add(new Product(2,"Product 2",10.00,10,20,5));
        productslist.add(new Product(3,"Product 3",15.00,12,15,2));
        counterProducts++; }
        return productslist;
    }
    
    public void enableModifyAndDeletePart() {
        this.modifyPartButton.setDisable(false);
        this.deletePartButton.setDisable(false);
    }
    
    public void enableModifyAndDeleteProduct() {
        this.modifyProductButton.setDisable(false);
        this.deleteProductButton.setDisable(false);
    }
    
    public void addPartButtonPushed(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("addPart.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    
    public void addProductButtonPushed(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("addProduct.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    
    public void modifyPartButtonPushed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("modifyPart.fxml"));
        Parent tableViewParent = loader.load();
        Scene tableViewScene = new Scene(tableViewParent);
        ModifyPartController controller = loader.getController();
        controller.initData(tableViewPart.getSelectionModel().getSelectedItem());
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        //controller.deletedAndModified(tableViewPart.getSelectionModel().getSelectedItem());
        //partslist.remove(tableViewPart.getSelectionModel().getSelectedItem());
    }
    
    public void modifyProductButtonPushed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("modifyProduct.fxml"));
        Parent tableViewParent = loader.load();
        Scene tableViewScene = new Scene(tableViewParent);
        ModifyProductController controller = loader.getController();
        controller.initData(tableViewProduct.getSelectionModel().getSelectedItem());
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        //controller.deletedAndModified(tableViewProduct.getSelectionModel().getSelectedItem());
        //productslist.remove(tableViewProduct.getSelectionModel().getSelectedItem());
    }
    
    public void searchPartsButtonPushed() {
        //parts search feature
        FilteredList<Part> filteredParts = new FilteredList<>(partslist, p -> true);
        searchBarParts.textProperty().addListener((observable, oldValue, newValue)-> {
            filteredParts.setPredicate(part -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (part.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Part> sortedParts = new SortedList<>(filteredParts);
        sortedParts.comparatorProperty().bind(tableViewPart.comparatorProperty());
        tableViewPart.setItems(sortedParts);
    }
    
    public void searchProductsButtonPushed() {
        //products search feature
        FilteredList<Product> filteredProducts = new FilteredList<>(getProducts(), p -> true);
        searchBarProducts.textProperty().addListener((observable, oldValue, newValue)-> {
            filteredProducts.setPredicate(product -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (product.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Product> sortedProducts = new SortedList<>(filteredProducts);
        sortedProducts.comparatorProperty().bind(tableViewProduct.comparatorProperty());
        tableViewProduct.setItems(sortedProducts);
    }
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //parts
        idPartColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        namePartColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getName()));
        inventoryPartColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());
        pricePerUnitPartColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
            pricePerUnitPartColumn.setCellFactory(new Callback<TableColumn, TableCell>() {
            public TableCell call(TableColumn p) {
                TableCell cell = new TableCell<Part, Double>() {
                    @Override
                    public void updateItem(Double item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty ? null : getString());
                        setGraphic(null);
                    }

                    private String getString() {
                        String ret = "$";
                        if (getItem() != null) {
                            String gi = getItem().toString();
                            NumberFormat df = DecimalFormat.getInstance();
                            df.setMinimumFractionDigits(2);
                            df.setRoundingMode(RoundingMode.DOWN);

                            ret += df.format(Double.parseDouble(gi));
                        } else {
                            ret = "$0.00";
                        }
                        return ret;
                    }
                };
                return cell;
            }
        });
        
        tableViewPart.setItems(getParts());
        //tableViewPart.getItems().add(AddPartController.createList());
        //idPartColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        namePartColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        //tableViewPart.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.modifyPartButton.setDisable(true);
        this.deletePartButton.setDisable(true);
        
        //products
        idProductColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        nameProductColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        inventoryProductColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        pricePerUnitProductColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
            pricePerUnitProductColumn.setCellFactory(new Callback<TableColumn, TableCell>() {
            public TableCell call(TableColumn p) {
                TableCell cell = new TableCell<Part, Double>() {
                    @Override
                    public void updateItem(Double item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty ? null : getString());
                        setGraphic(null);
                    }

                    private String getString() {
                        String ret = "$";
                        if (getItem() != null) {
                            String gi = getItem().toString();
                            NumberFormat df = DecimalFormat.getInstance();
                            df.setMinimumFractionDigits(2);
                            df.setRoundingMode(RoundingMode.DOWN);

                            ret += df.format(Double.parseDouble(gi));
                        } else {
                            ret = "$0.00";
                        }
                        return ret;
                    }
                };
                return cell;
            }
        });
            
        tableViewProduct.setItems(getProducts());
        //tableViewProduct.setEditable(true);
        //idProductColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameProductColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tableViewProduct.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.modifyProductButton.setDisable(true);
        this.deleteProductButton.setDisable(true);
        
        
        
        
    }    
    
}
