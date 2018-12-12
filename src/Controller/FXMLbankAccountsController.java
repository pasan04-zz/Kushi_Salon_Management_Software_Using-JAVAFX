/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import model.List3;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author MHL1
 */
public class FXMLbankAccountsController implements Initializable {
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection con = null;
    ObservableList<List3> data;
    
    
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
    private Button Button8;
    @FXML
    private TextField BN;
    @FXML
    private TableView<List3> table;
    @FXML
    private TableColumn<List3, String> ba;
    @FXML
    private TableColumn<List3, String> br;
    @FXML
    private TableColumn<List3, String> acn;
    @FXML
    private TableColumn<List3, String> ach;
    @FXML
    private TextField BR;
    @FXML
    private TextField ACN;
    @FXML
    private TextField ACH;

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
            ((Node) (event.getSource())).getScene().getWindow().hide();
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
    private void addAccAction(ActionEvent event) throws SQLException {
        String BankName = BN.getText();
        String Branch = BR.getText();
        String AccountNu = ACN.getText();
        String AccountHol = ACH.getText();
        
        String query = "Insert into BankAcc(BankName, Branch, AccountNu, AccountHol) values(?,?,?,?)";
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(query);
            
            ps.setString(1, BankName);
            ps.setString(2, Branch);
            ps.setString(3, AccountNu);
            ps.setString(4, AccountHol);
            
            ps.execute(query);
        }catch(SQLException e){
            System.out.println(e);
            
        }
        

       }

    @FXML
    private void tranAction(ActionEvent event) {
    }

    @FXML
    private void loadTable(ActionEvent event) {
        data = FXCollections.observableArrayList();
        data.clear();
        String sql = "SELECT * from BankAcc";
        
        try{
            ps = con.prepareStatement(sql);
            while(rs.next()){
                data.add(new List3(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
                table.setItems(data);
            }
            ps.close();
            rs.close();
            con.close();
        }catch(Exception e){
            
        }
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
