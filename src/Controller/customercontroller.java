/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Utils.DBConnection;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.customer;
import Services.customerDB;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class customercontroller implements Initializable {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    private ObservableList<customercontroller> data;

    @FXML
    private Button home;
    @FXML
    private Button appointment;
    @FXML
    private Button csd;
    @FXML
    private Button qwe;
    @FXML
    private Button btn_save;
    @FXML
    private Button eer;
    @FXML
    private Button wewr;
    @FXML
    private Button rre;
    @FXML
    private Button updatedelete;
    @FXML
    private Button view;
 
    
    @FXML
    private TextField nic;
    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private TextField address;
    @FXML
    private TextField age;
    @FXML
    private TextField phonenumber;

   
       

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        data = FXCollections.observableArrayList();
    } 

        
    @FXML
    void clickAppointment(ActionEvent event) {
                try{
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource(""));
            Parent root1 = (Parent)fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Appointment");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        } 
        
        

    }

    @FXML
    void clickCustomer(ActionEvent event) {
                try{
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource(""));
            Parent root1 = (Parent)fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Customer");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        } 
        

    }

    @FXML
    void clickDealer(ActionEvent event) {
                try{
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource(""));
            Parent root1 = (Parent)fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        } 
        
        

    }
        
    

    @FXML
    void clickEmployee(ActionEvent event) {
                try{
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource(""));
            Parent root1 = (Parent)fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Employee details");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        } 
        
        

    }

    @FXML
    void clickFinance(ActionEvent event) {
                try{
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource(""));
            Parent root1 = (Parent)fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Finance");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        } 
        
        

    }

    @FXML
    void clickStock(ActionEvent event) {
                try{
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource(" "));
            Parent root1 = (Parent)fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Kushi Saloon");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        } 

    }

    @FXML
    void clickPackages(ActionEvent event) {
                try{
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource(""));
            Parent root1 = (Parent)fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Packages");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        } 

    }

    @FXML
    void clickPayment(ActionEvent event) {
                try{
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/"));
            Parent root1 = (Parent)fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Payment");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        } 

    }
    
    
    @FXML
        private void updateDelete(ActionEvent event) throws IOException {
            
            try{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/customerDelete.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
        }catch(IOException e){
            e.printStackTrace();            
        }
    }
    
     @FXML  //when button click view Customer
     private void ViewCustomer(ActionEvent event) throws IOException {
         
         try{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/customerView.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));  
        stage.show();
    }catch(IOException e){
        e.printStackTrace();
    }}
    

    @FXML
    private void save(ActionEvent event) throws SQLException, ClassNotFoundException {
    
        
        String NIC = nic.getText();
        String firstname = fname.getText();
        String lastname = lname.getText();
        String address1 = address.getText();
        String phonenumber1 = phonenumber.getText();
        String age1 = age.getText();
        
        customer c1 = new customer(NIC, firstname,lastname, address1, phonenumber1,age1) ;
        c1.setNIC(NIC);
        c1.setFirstname(firstname);
        c1.setLastname(lastname);
        c1.setAddress(address1);
        c1.setPhoneNumber(phonenumber1);
        c1.setAge(age1);
        
        customerDB ct1 = new customerDB();
        String customerregistered = ct1.registerCustomer(c1);
         
        if(NIC.isEmpty() || (NIC == "")){
       JOptionPane.showMessageDialog(null, "Enter NIC number");}
        
        if(firstname.isEmpty() || (firstname == "")){
       JOptionPane.showMessageDialog(null, "Enter first name");}
                 
       if(lastname.isEmpty() || (lastname == "")){
       JOptionPane.showMessageDialog(null, "Enter last name");}
       
       if(address1.isEmpty() || (address1 == "")){
       JOptionPane.showMessageDialog(null, "Enter address");}
       
       if(phonenumber1.isEmpty() || (phonenumber1 == "")){
       JOptionPane.showMessageDialog(null, "Enter Phone number");}
       
       if(age1.isEmpty() || (age1 == "")){
       JOptionPane.showMessageDialog(null, "Enter Age");}
       
      if(NIC.isEmpty() ||firstname.isEmpty() || lastname.isEmpty() || address1.isEmpty() || phonenumber1.isEmpty() || age1.isEmpty()){ 
          JOptionPane.showMessageDialog(null, "Not successfully entered the data");
          }
        
       else if(!ct1.equals("SUCCESS")){
                nic.setText("");
                fname.setText("");
                lname.setText("");
                address.setText("");
                phonenumber.setText("");
                age.setText("");  
         
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Successful !");
                alert.setHeaderText("");
                alert.setContentText("Data Succesfully entered !");
                alert.showAndWait();
                System.out.println("succesfully entered the data");
               
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("");
                alert.setContentText("Err !");
                System.out.println("Not successfully entered the data"); 
       }
       }

    @FXML
    private void updateDelete(MouseEvent event) {
    }
}      

        

