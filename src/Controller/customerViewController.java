/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.customercontroller;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.customer;
import static sun.net.www.MimeTable.loadTable;


import Utils.DBConnection;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class customerViewController implements Initializable {

     Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs =null;
    private ObservableList<customer> data;

    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private TextField add;
    @FXML
    private TextField pno;
    @FXML
    private TextField nic;
    @FXML
    private TextField age;
    @FXML
    private Button addNew;
    @FXML
    private Button updatedelete;
    @FXML
    private Button calcCust;
    @FXML
    private Button load;
    @FXML
    private Button search;
    @FXML
    private TextField searchCust;
    @FXML
    private TableView<customer> tableview;
    @FXML
    private TableColumn<customer, String> cnic;
    @FXML
    private TableColumn<customer, String> cfname;
    @FXML
    private TableColumn<customer, String> clname;
    @FXML
    private TableColumn<customer, String> cadd;
    @FXML
    private TableColumn<customer, String> cpno;
    @FXML
    private TableColumn<customer, String> cage;
    
    @FXML
    private TableView<customer> tableview1;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {

         try {
             data = FXCollections.observableArrayList();
             conn = DBConnection.getDBConnection();
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(customerViewController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(customerViewController.class.getName()).log(Level.SEVERE, null, ex);
         }
         setCellTable();
         loadTable();
    }    
    
    
    

    @FXML
    void calculateNoCustomers(ActionEvent event) {
                try{
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/customercount.fxml"));
            Parent root1 = (Parent)fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Packages");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        } 

    }

   
    private void clearTextField() {
        nic.clear();
        fname.clear();
        lname.clear();
        add.clear();
        pno.clear();
        age.clear();
    }
    @FXML
    private void search(ActionEvent event) throws SQLException {
    
            Connection conn = null;
            String search = searchCust.getText() ;
         try{
             
            String sql;
            sql = "select * from customer where NIC='"+searchCust.getText()+"'";
            rs = ps.executeQuery();
            
            while(rs.next()){
             nic.setText(rs.getString("NIC"));
             fname.setText(rs.getString("Firstname"));
             lname.setText(rs.getString("Lastname"));
             add.setText(rs.getString("Address"));
             pno.setText(rs.getString("Phonenumber"));
             age.setText(rs.getString("Age"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Unable to retrieve record "+e.getMessage());
        } 
         }
      

    @FXML
    private void updateDelete(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/customerDelete.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
    }
        
    @FXML
    private void custMain(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/customerMainPage.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
    }
    
    
    private void setCellTable(){
        cnic.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        cfname.setCellValueFactory(new PropertyValueFactory<>("Firstname"));
        clname.setCellValueFactory(new PropertyValueFactory<>("Lastname"));
        cadd.setCellValueFactory(new PropertyValueFactory<>("Address"));
        cpno.setCellValueFactory(new PropertyValueFactory<>("Phonenumber"));
        cage.setCellValueFactory(new PropertyValueFactory<>("Age"));
    }
    
    @FXML
    private void loadtable() throws SQLException, ClassNotFoundException {
        
        //data = FXCollections.observableArrayList();
        data.clear();
        try {
            conn = DBConnection.getDBConnection();
            ps = conn.prepareStatement("Select * from customer");
            rs = ps.executeQuery();
            while(rs.next()){
             data.add(new customer(rs.getString("NIC"), rs.getString("Firstname"), rs.getString("Lastname"),rs.getString("Address"), rs.getString("Phonenumber"), rs.getString("Age")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(customerDeleteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
           ps.close();
           conn.close();
       }
        setCellTable();
        tableview.setItems(data);
        clearTextField();
    }

}
        
        
    

