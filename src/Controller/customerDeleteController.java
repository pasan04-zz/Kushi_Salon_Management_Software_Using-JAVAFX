/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.EmployeeUpdateController.tempEid;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.customer;
import Utils.DBConnection;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import kushisaloon.MainApp;
import model.Employee;
import model.stock;


/**
 * FXML Controller class
 *
 * @author HP
 */
public class customerDeleteController implements Initializable {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    //private ObservableList<customer> data;
    ObservableList<customer> data = FXCollections.observableArrayList();
    
    @FXML
    private Button addCust;
    @FXML
    private Button calcCust;
    
    

    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private TextField add;
    @FXML
    private TextField pno;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private TextField nic;
    @FXML
    private TextField age;
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
    private Button load;
    @FXML
    private TableView<customer> tableview;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         setCellValueFromTableTextField ();
       
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

    /*UPDATE CUSTOMERS*/
  /* @FXML
    private void updateCustomer(ActionEvent event) throws SQLException, ClassNotFoundException {
     
        Connection conn = null;
     
     String sql = "Update customer set nic = ?, Firstname = ?, Lastname = ?, Address = ?, Phonenumber = ?, Age = ?";
        try {
            
             ps = conn.prepareStatement(sql);
          
            ps.setString(1, cnic.getText());
            ps.setString(2, cfname.getText());
            ps.setString(3, clname.getText());
            ps.setString(4, cadd.getText());
            ps.setString(5, cpno.getText());
            ps.setString(6, cage.getText());
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Customer'"+fname.getText()+"'Updated Successfully!!");
            loadTbl();
            
        }catch(Exception e){
            
            System.out.println(e);
           
        }finally{
            conn.close();
        }
    }*/

    private void clearTextField() {
        nic.clear();
        fname.clear();
        lname.clear();
        add.clear();
        pno.clear();
        age.clear();
    }

    @FXML
    public void showOnClick() throws SQLException{
        
        try{
            
            customer custom  = (customer)tableview.getSelectionModel().getSelectedItem();
            String sql = "select * from customer";
            ps = conn.prepareStatement(sql);
           
            
            nic.setText(custom.getNIC());
            fname.setText(custom.getFirstName());
            lname.setText(custom.getLastname());
            add.setText(custom.getAddress());
            pno.setText(custom.getPhoneNumber());
            age.setText(custom.getAge());
            
            ps.close();
            rs.close();
            
        }catch(SQLException e){
            System.out.println(e);
        }  
       
    }   
    
       private void setCellValueFromTableTextField (){
     
            tableview.setOnMouseClicked(e->{ 
                customer st1 = tableview.getItems().get(tableview.getSelectionModel().getSelectedIndex());
                nic.setText(st1.getNIC());
                fname.setText(st1.getFirstName());
                lname.setText(st1.getLastname());
                add.setText(st1.getAddress());


        });     
    }
    
   /* @FXML
    private void updateCustomer(ActionEvent event) throws IOException{
        
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs =null;
        try {
            con = DBConnection.getDBConnection();
                    String sql = "update customer set nic= ?,firstname = ?,lastname =?, address = ?,phonenumber = ?,age =?  where id = ?";
                    
                    String nic12 = nic.getText();
                    String fname12= fname.getText();
                    String lname12 = lname.getText();
                    String add12= add.getText();
                    String pno12 = pno.getText();
                    String age12= age.getText();
                    
                    preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setString(1,nic12);
                    preparedStatement.setString(2,fname12);
                    preparedStatement.setString(3,lname12);
                    preparedStatement.setString(4, add12);
                    preparedStatement.setString(5, age12);
                        
                    int i = preparedStatement.executeUpdate();
                    
                    if( i== 1){
                        
                       Alert alert = new Alert(Alert.AlertType.ERROR);
                       alert.setTitle("Error!");
                       alert.setHeaderText("");
                       alert.setContentText("Updated Succesfully!");
                       alert.showAndWait();
                       clearTextField();
                       
                    }
                    else{
                       Alert alert = new Alert(Alert.AlertType.ERROR);
                       alert.setTitle("Error!");
                       alert.setHeaderText("");
                       alert.setContentText("Oop!,There went something wrong!!");
                    }
                    
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(stockdeleteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(stockdeleteController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/customerDelete.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
        Stage stage1 = (Stage) update.getScene().getWindow();
        stage.close();
        
    }*/
   
    
    @FXML
    void addCustomer(ActionEvent event) throws IOException{
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/customerMainPage.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
    }

    
 
    @FXML
    private void calculateNoCustomers(ActionEvent event) {
        
        try{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Customercount.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));  
        stage.show();
    }catch(IOException e){
        e.printStackTrace();
    }
        
    }   
    
    private void setTableValue(){
    
        cnic.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        cfname.setCellValueFactory(new PropertyValueFactory<>("Firstname"));
        clname.setCellValueFactory(new PropertyValueFactory<>("Lastname"));
        cadd.setCellValueFactory(new PropertyValueFactory<>("Address"));
        cpno.setCellValueFactory(new PropertyValueFactory<>("Phonenumber"));
        cage.setCellValueFactory(new PropertyValueFactory<>("Age"));

        
    }
    //pasan Updated
    Connection con = null;
    PreparedStatement preparedStatement = null;
    public void loadData() throws ClassNotFoundException, SQLException {
        con= DBConnection.getDBConnection();
        String query = "select * from customer";
        try {

            preparedStatement = con.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                
                data.add(new customer(rs.getString("nic"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("address"), rs.getString("phonenumber"),rs.getString("age")));
                tableview.setItems(data);
            }
            preparedStatement.close();
            rs.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    @FXML 
    private void deleteCustomer(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        String name = null;
        con = DBConnection.getDBConnection();
        try {
            customer c1 = (customer) tableview.getSelectionModel().getSelectedItem();
            String query = "DELETE FROM customer WHERE nic = ?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, c1.getNIC());
            name = c1.getFirstName();
            preparedStatement.executeUpdate();
            preparedStatement.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
        data.clear();
        loadData();
        MainApp.showInformationAlertBox("Customer '" + name + "' Deleted Successfully");
        
    }
    
    
    @FXML
    public void updateCustomer(ActionEvent event) throws ClassNotFoundException, SQLException {
       
        String name = null;
        con = DBConnection.getDBConnection();
        customer emp = (customer) tableview.getSelectionModel().getSelectedItem();
        String query = "Update customer set firstname=?,lastname=?,address=?,phonenumber=?,age=? WHERE nic= ?";
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, emp.getNIC());
        try {
            //customer c1 = (customer) tableview.getSelectionModel().getSelectedItem();
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, nic.getText());
            preparedStatement.setString(2, fname.getText());
            preparedStatement.setString(3, lname.getText());
            preparedStatement.setString(4, add.getText());
            preparedStatement.setString(5, pno.getText());
            preparedStatement.setString(6, age.getText());
            
            name = emp.getFirstName();
                
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            System.err.println(e);
        }
        data.clear();
        loadData();
        MainApp.showInformationAlertBox("customer '" + name + "' Updated Successfully");
    }


    @FXML
    private void loadTbl() throws SQLException, ClassNotFoundException {
        
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
        setTableValue();
        tableview.setItems(data);
        clearTextField();
    }

  
}
