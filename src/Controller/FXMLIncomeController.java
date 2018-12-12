/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Utils.DBConnection;
import model.List;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author MHL1
 */
public class FXMLIncomeController implements Initializable {
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection con = null;
    ObservableList<List> data;
    
    @FXML
    private Button home;
    @FXML
    private Button appointment;
    @FXML
    private Button customer;
    @FXML
    private Button packages;
    @FXML
    private Button payment;
    @FXML
    private Button employee;
    @FXML
    private Button suplier;
    @FXML
    private Button finance;
    @FXML
    private Button Button1;
    @FXML
    private Button Button2;
    @FXML
    private Button Button3;
    @FXML
    private Button Button4;
    @FXML
    private Button Button5;
    @FXML
    private Button Button6;
    @FXML
    private Button Button7;
    @FXML
    private TextField app;
    @FXML
    private TextField sale;
    @FXML
    private TextField tIncome;
    @FXML
    private Button Button8;
    @FXML
    private Button Button9;
    @FXML
    private TableView<List> table;
    @FXML
    private TableColumn<List, String> appi;
    @FXML
    private TableColumn<List, String> salei;
    @FXML
    private TableColumn<List, String> tot;
        //for navigation bar

    @FXML
    private Button btnStockUI;

    @FXML
    private Button btnAppointmentUI;

    @FXML
    private Button btnCustomerUI;

    @FXML
    private Button btnPackageUI;

    @FXML
    private Button btnPaymentUI;

    @FXML
    private Button btnEmployeeUI;

    @FXML
    private Button btnSuppllierUI;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setNavigationbarToolTip();
    }   
    @FXML
    private void incomeAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/FXMLIncome.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));          

        stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void PTAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/FXMLPT.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();

        stage.setScene(new Scene(root1));  
        stage.show();
    }

    @FXML
    private void bankAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/FXMLbankAccounts.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  

        stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void expanseAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/FXMLExpanse.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  

        stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void reportAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/FXMLreport.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1)); 
 
        stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
    }


    @FXML
    private void salseAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        con = DBConnection.getDBConnection();
             if(con != null){
              String sql ="Select sum(salIncome) from salincom";
              try{
              ps = con.prepareStatement(sql);
              rs = ps.executeQuery();
              if(rs.next()){
                  String sum1 = rs.getString("sum(salIncome)");
                  sale.setText(sum1);
              }

            }catch(Exception e){     

                JOptionPane.showMessageDialog(null, e);

            }finally{
                  ps.close();
                  rs.close();
                  con.close();
            }
         }
    }
    
    @FXML
    private void appAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        con = DBConnection.getDBConnection();
        if(con != null){
         String sql ="Select sum(appIncome) from appincom";
         try{
         ps = con.prepareStatement(sql);
         rs = ps.executeQuery();
         if(rs.next()){
             String sum2 = rs.getString("sum(appIncome)");
             app.setText(sum2);
         }
       }catch(Exception e){     

           JOptionPane.showMessageDialog(null, e);

       } finally{
             ps.close();
            rs.close();
            con.close();
         }
     }   
    }
    @FXML
    private void calAction(ActionEvent event) throws SQLException {
        int one = Integer.parseInt(app.getText());
         int two = Integer.parseInt(sale.getText());
         
         
         String answer = String.valueOf(one+two);
         tIncome.setText(answer);
         
    }
    @FXML
    private void loadTAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        data = FXCollections.observableArrayList();
        data.clear();
        try {
            con = DBConnection.getDBConnection();
            ps = con.prepareStatement("Select * from totalIncome");
            rs = ps.executeQuery();
            while(rs.next()){
             data.add(new List(rs.getString(1), rs.getString(2), rs.getString(3)));
             
             appi.setCellValueFactory(new PropertyValueFactory<>("app"));
             salei.setCellValueFactory(new PropertyValueFactory<>("sale"));
             tot.setCellValueFactory(new PropertyValueFactory<>("tIncome"));
            
            table.setItems(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLIncomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
           ps.close();
            rs.close();
            con.close();
       }
        clearTextField();
        
    }
    private void clearTextField() {
        app.clear();
        sale.clear();
        tIncome.clear();
        
    }
        //for navigation bar

    @FXML
    public void viewAppointmentUIb(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/addAppointment.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(createInvoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void viewCustomerUIb(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/customerMainPage.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(createInvoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void viewEmployeeUIb(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/EmployeeManagement.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(createInvoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void clickFinance(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/FXMLExpanse.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(createInvoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void viewPackageUIb(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/addPackage.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(createInvoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void viewPaymentUIb(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/createInvoice.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(createInvoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void viewStockUIb(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/stockMain.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(createInvoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void viewSupplierUIb(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DealerRegistration.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(createInvoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setNavigationbarToolTip() {
        btnAppointmentUI.setTooltip(new Tooltip("Appointment Management System"));
        btnCustomerUI.setTooltip(new Tooltip("Customer Management System"));
        btnEmployeeUI.setTooltip(new Tooltip("Employee Management System"));
        btnStockUI.setTooltip(new Tooltip("Stock Management System"));
        btnPackageUI.setTooltip(new Tooltip("Package Management System"));
        btnPaymentUI.setTooltip(new Tooltip("Payment Management System"));
        btnSuppllierUI.setTooltip(new Tooltip("Supplier Management System"));
        finance.setTooltip(new Tooltip("Finance Management System"));

    }
    
}
