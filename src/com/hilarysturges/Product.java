/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hilarysturges;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Hilary
 */
public class Product {
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();;
    private int id;
    private String name;
    private double price;
    private int stock;
    private int max;
    private int min;
    
    
    public Product(int id, String name, double price, int stock, int max, int min) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.max = max;
        this.min = min;
    }
    
    public void setId(int id) {
        this.id=id;
    };
    public void setName(String name) {
        this.name=name;
    };
    public void setPrice(double price){  
        this.price=price;
    };
    public void setStock(int stock){ 
        this.stock=stock;
    };
    public void setMin(int min){  
        this.min=min;
    };
    public void setMax(int max){ 
        this.max=max;
    };
    public void setPrice(int max){  
    };
    public int getId() {
        return id;
    };
    public String getName() {
        return name;
    };
    public double getPrice() {
        return price;
    };
    public int getStock() {
        return stock;
    };
    public int getMin() {
        return min;
    };
    public int getMax() {
        return max;
    };
    public void addAssociatedPart(Part associatedPart){
        associatedParts.add(associatedPart);
    };
    public void deleteAssociatedPart(Part associatedPart) {  
        associatedParts.remove(associatedPart);
    };
    public ObservableList<Part> getAllAssociatedParts() { 
        return associatedParts;
    };
}
