/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.concurrent.atomic.AtomicInteger;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

/**
 *
 * @author Pasan
 */
public class stock {
    
     private String date;
     private int bill;
     private String supplierName;
     private String category;
     private String name;
     private int qty;
     private double price123; 
     private Button button;
     
     
     
    public stock(){
        
        
    }
    public stock(int bill,String name,String category,int qty, double price1) {  //String category, String name, 
        
        
        this.bill = bill;
        this.name = name;
        this.category = category;
        this.qty = qty;
        this.price123 = price1;
           
    }
    public stock(int bill,String da,String sn,String ca,String na,int q,double p){
        
        
       this.date = da;
       this.bill = bill;
       this.supplierName = sn;
       this.name = na;
       this.category = ca;
       this.qty = q;
       this.price123 = p;   
    }


    //
    public String getDate(){
        return this.date;
    }
    public void setDate(String da){
        
        this.date = da;
    }
    
    
    //
    public int getBill(){
        return bill;
    }
    public void setBill(int b){
        this.bill = b;
    }
    
    //
    public String getSupplier(){
        
        return supplierName;
    }
    public void setSupplier(String sn){
        this.supplierName = sn;
    }
    
    //
    public String getCateg(){

        return category;
    }
    
    public void setCateg(String category){
        this.category = category;
    }
    //
    public String getName(){
        
        return name;
    }
    public void setName(String name1){
        this.name = name1;
    }

    public int getQty(){
        return qty;
    }
    
    public void setQty(int qty){
        this.qty = qty;
        
    }
    
    public double getPrice(){
        return price123;
        
        
    }
    public void setPrice(double price123){
        this.price123 = price123;
        
        
    }  
    public void setButton(Button button){
        
        this.button = button;
    }
    public Button getButton(){
        
        return button;
    }
}
