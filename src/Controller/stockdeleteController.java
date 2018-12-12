/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.EmployeeUpdateController.tempEid;
import Utils.DBConnection;
import java.awt.event.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import kushisaloon.MainApp;
import model.stock;
import kushisaloon.StockFinalFinal;
import model.Employee;
import model.customer;

public class stockdeleteController implements Initializable{
    
    @FXML
    private TextField billID;
    
    @FXML
    private TextField pname;

    @FXML
    private TextField categ;

    @FXML
    private TextField price;
 
    @FXML
    private TextField qty;
    
    @FXML
    private Button update;
    
    @FXML
    private Button delete;
    

    @FXML
    private TableView<stock> tableView12;
    
    @FXML
    private TableColumn<stock, Integer> col_bill;

    @FXML
    private TableColumn<stock, String> col_name;

    @FXML
    private TableColumn<stock, String> col_categ;

    @FXML
    private TableColumn<stock, Double> col_price;
    
    @FXML
    private TableColumn<stock, Integer> col_qty;
    
    @FXML
    private Button addNew;
    
    @FXML
    private Button notification;
    
    @FXML
    private Button additem;
    
    @FXML
    private TableColumn<stock, Button> action;
    ObservableList<stock> filltable;//FXCollections.observableArrayList();
    
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs =null;
    
   ObservableList<stock> data = FXCollections.observableArrayList();
   
    @FXML
    void addItems(ActionEvent event) throws IOException{
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/stockMain.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
        Stage stage1 = (Stage) additem.getScene().getWindow();
        stage1.close();
        
        
    }
    
    @FXML
    void calculateNoOfProducts(ActionEvent event) throws IOException{
        
        
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/stocksummaryOfProducts.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));  
            stage.show();
    
        
    }
        
    @FXML
    void viewCategory(ActionEvent event) throws IOException {
        
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/stockpieChart.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();

    }  
   @FXML
    private void onUpdate(ActionEvent event) { 
            

    }
     @FXML
    void notificationAction(ActionEvent event) throws IOException {
        
        
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/stocknotifications.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();


    }
    
     public void loadData() throws ClassNotFoundException, SQLException {
        con= DBConnection.getDBConnection();
        String query = "select * from stock";
        try {

            preparedStatement = con.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                
                data.add(new stock(rs.getInt("bill"), rs.getString("name"), rs.getString("categ"), rs.getInt("qty"), rs.getDouble("price")));
                tableView12.setItems(data);
            }
            preparedStatement.close();
            rs.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    

     //delete the selected products
    @FXML 
    private void deleteProduct(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        
     
       // String a = cage.getText().toString();
        
        ObservableList<stock> abc, def ;
        abc = tableView12.getItems();
        def = tableView12.getSelectionModel().getSelectedItems();
        
        def.forEach(abc::remove);
        
         String delete = "Delete from stock where bill = ?" ;
          preparedStatement = con.prepareStatement(delete);
          int bill = Integer.parseInt(billID.getText());
         try{
        PreparedStatement ps = DBConnection.getDBConnection().prepareStatement(delete);
      
        preparedStatement.setDouble(1, bill);
        int i= preparedStatement.executeUpdate();
        
        
        
       tableView12.getItems().removeAll(tableView12.getSelectionModel().getSelectedItem());
        
        } catch (SQLException ex) {
            Logger.getLogger(customerDeleteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(customerDeleteController.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
         try{
             rs.close();
             preparedStatement.close();
                }catch(Exception e){
                }
         }
        data.clear();
        loadData();
        MainApp.showInformationAlertBox("stock '" + bill + "' Deleted Successfully");
        
    } 
    @FXML
    public void updateProduct(ActionEvent event) throws ClassNotFoundException, SQLException {
       

        con = DBConnection.getDBConnection();
        int bill  = Integer.valueOf(billID.getText());
        String query = "update stock set name = ?,categ = ?,qty =?, price = ? where bill = ?" + bill+ "'";
        try {
                    stock st1 = (stock) tableView12.getSelectionModel().getSelectedItem();
                    
                    String name1 = pname.getText();                  
                    String category = categ.getText();
                    int qty12= Integer.valueOf(qty.getText());
                    double price1 = Double.valueOf(price.getText());

                    
                    preparedStatement = con.prepareStatement(query);
                    preparedStatement.setString(1,name1);
                    preparedStatement.setString(2,category);
                    preparedStatement.setInt(3,qty12);
                    preparedStatement.setDouble(4, price1);
                    preparedStatement.setDouble(5, bill);
                        
                    int i = preparedStatement.executeUpdate();
            

                
            preparedStatement.execute();
            preparedStatement.close();


        } catch (SQLException e) {
            System.err.println(e);
        }
        //int bill  = Integer.valueOf(billID.getText());
        data.clear();
        loadData();
        MainApp.showInformationAlertBox("Stock '" + bill + "' Updated Successfully");
    }
    
    
    //Insert the values to the table
    ObservableList<stock>filltable12 = FXCollections.observableArrayList();
    public void fillTableValues(){

        try { 
            Connection con = null;
            PreparedStatement preparedStatement = null;
            ResultSet rs =null;
            con = DBConnection.getDBConnection();
 
            String query = "select *from stock"; 
            preparedStatement = con.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while(rs.next()){   
              stock st2= new stock();
              st2.setBill(rs.getInt("bill"));
              st2.setName(rs.getString("name"));
              st2.setCateg(rs.getString("categ"));  
              st2.setQty(rs.getInt("qty"));
              st2.setPrice(rs.getDouble("price"));
              
              filltable12.add(st2);
            }
             tableView12.setItems(filltable12);
        } catch (SQLException ex) {
            
            Logger.getLogger(stockMainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            
            Logger.getLogger(StockFinalFinal.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }  
  
    
   private void setCellValueFromTableTextField (){
     
        tableView12.setOnMouseClicked(e->{ 
                stock st1 = tableView12.getItems().get(tableView12.getSelectionModel().getSelectedIndex());
                billID.setText(Integer.toString(st1.getBill()));
                pname.setText(st1.getName());
                categ.setText(st1.getCateg());
                String tot = new Double(st1.getPrice()).toString();
                price.setText(tot);
                qty.setText(Integer.toString(st1.getQty()));

        });     
    }
 
    private void clearTextField(){
       billID.clear();
       pname.clear();
       categ.clear();
       price.clear();
       qty.clear();
 
    }
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        filltable = FXCollections.observableArrayList();
        col_bill.setCellValueFactory(new PropertyValueFactory<>("bill"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_categ.setCellValueFactory(new PropertyValueFactory<>("categ"));
        col_qty.setCellValueFactory(new PropertyValueFactory<>("qty")); 
        col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
 
        try {
            loadData();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(stockdeleteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(stockdeleteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setCellValueFromTableTextField ();
    }  
    


}
