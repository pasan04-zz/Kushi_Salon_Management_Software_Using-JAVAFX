/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import kushisaloon.MainApp;
import model.Employee;
import Utils.DBConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author NADEESH
 */
public class EmployeeUpdateController implements Initializable {

    
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs =null;
            //con = DBConnection.getDBConnection();
     //con = DBConnection.getDBConnection();
    ObservableList<Employee> data = FXCollections.observableArrayList();
    FilteredList<Employee> filteredData = new FilteredList<>(data, e -> true);

    //PreparedStatement preparedStatement = null;
   // ResultSet rs = null;

    @FXML
    public TextField firstNameText;
    @FXML
    private TextField lastNameText;
    @FXML
    private TextField NICText;
    @FXML
    private TextField emailText;
    @FXML
    private TextField PhoneText;
    @FXML
    private TextField addressText;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;

    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<?, ?> columnEid;
    @FXML
    private TableColumn<?, ?> columnFirstName;
    @FXML
    private TableColumn<?, ?> columnLastName;
    @FXML
    private TableColumn<?, ?> columnNIC;
    @FXML
    private TableColumn<?, ?> columnEmail;
    @FXML
    private TableColumn<?, ?> columnPhone;
    @FXML
    private TableColumn<?, ?> columnAddress;
    @FXML
    private TableColumn<?, ?> columngender;
    
   // @FXML
   // private TableColumn<?, ?> columnhireDate;
    @FXML
    private TextField searchTxt;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnReset;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    
    
    
        
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
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/DealerRegistration.fxml"));
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
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/stockMain.fxml"));
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
    public void initialize(URL url, ResourceBundle rb) {
        columnEid.setCellValueFactory(new PropertyValueFactory<>("eid"));
        columnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        columnLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        columnNIC.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        columngender.setCellValueFactory(new PropertyValueFactory<>("gender"));
       // columnhireDate.setCellValueFactory(new PropertyValueFactory<>("hireDate"));
        //columnDOB.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        try {
            loadData();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeUpdateController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeUpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void insertEmployee(ActionEvent event) throws SQLException, ClassNotFoundException {
        String gend;
        if (male.isSelected()) {
            gend = "Male";  
        }
        else{
            gend = "Female";
            
        }
     
       // if (emptyValidation() && firstNameValidate() && lastNameValidate() && nicValidate() && emailValidate() && phoneValidate() && genderValidate()) {

            String fname = firstNameText.getText();
            String lname = lastNameText.getText();
            String nic = NICText.getText();
            String email = emailText.getText();
            //String dob = DOBPick.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String phone = PhoneText.getText();
            String address = addressText.getText();
            
           // java.sql.Date today = new java.sql.Date(new java.util.Date().getTime());
            connection = DBConnection.getDBConnection();
            String query = "INSERT into Employee(FirstName,LastName,NIC,Email,Phone,Address,Gender) VALUES(?,?,?,?,?,?,?)";
            preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, fname);
                preparedStatement.setString(2, lname);
                preparedStatement.setString(3, nic);
                preparedStatement.setString(4, email);
                preparedStatement.setString(5, phone);
                preparedStatement.setString(6, address);
                preparedStatement.setString(7, gend);
  

                if (query != null) {
                    MainApp.showInformationAlertBox("New employee added successfully");
                } else {
                    MainApp.showInformationAlertBox("New employee adding unsuccessful");
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                preparedStatement.execute();
                preparedStatement.close();
            }
            firstNameText.clear();
            lastNameText.clear();
            NICText.clear();
            emailText.clear();
            PhoneText.clear();
            addressText.clear();
            male.setSelected(false);
            female.setSelected(false);

            data.clear();
            loadData();
            //showInformationAlertBox("New Employee added successfully");
       // }
    }

    public void resetButton(ActionEvent event) {
        firstNameText.clear();
        lastNameText.clear();
        emailText.clear();
        NICText.clear();
        PhoneText.clear();
        addressText.clear();
        male.setSelected(false);
        female.setSelected(false);
    }

    public void loadData() throws ClassNotFoundException, SQLException {
        connection = DBConnection.getDBConnection();
        String query = "SELECT * FROM Employee";
        try {

            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                data.add(new Employee(rs.getString("eid"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("NIC"), rs.getString("email"), rs.getString("Phone"), rs.getString("address"),rs.getString("gender")));
                employeeTable.setItems(data);
            }
            preparedStatement.close();
            rs.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    static String tempEid;

    @FXML
    public void showOnClick() throws ClassNotFoundException {
        try {
            Employee emp = (Employee) employeeTable.getSelectionModel().getSelectedItem();
            connection = DBConnection.getDBConnection();
            String query = "SELECT * FROM Employee";
            preparedStatement = connection.prepareStatement(query);
            tempEid = emp.getEid();
            rs = preparedStatement.executeQuery();
            while(rs.next()){

            firstNameText.setText(emp.getFirstName());
            lastNameText.setText(emp.getLastName());
            NICText.setText(emp.getNIC());
            emailText.setText(emp.getEmail());
            PhoneText.setText(emp.getPhone());
            addressText.setText(emp.getAddress());
            
            if(null != rs.getString("gender")) switch (rs.getString("gender")){
                case "Male":
                    male.setSelected(true);
                    break;
                case "Female":
                    female.setSelected(true);
                    break;
                default:
                    male.setSelected(false);
                    female.setSelected(false);
                    break;
            }else{
                male.setSelected(false);
                female.setSelected(false);
            }
            }
            preparedStatement.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @FXML
    public void updateButton(ActionEvent event) throws ClassNotFoundException, SQLException {
       
        String name = null;
        connection = DBConnection.getDBConnection();
        String query = "Update Employee set FirstName=?,LastName=?,NIC=?,Email=?,Phone=?,Address=? WHERE EID='" + tempEid + "'";
        try {
            Employee emp = (Employee) employeeTable.getSelectionModel().getSelectedItem();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstNameText.getText());
            preparedStatement.setString(2, lastNameText.getText());
            preparedStatement.setString(3, NICText.getText());
            preparedStatement.setString(4, emailText.getText());
            preparedStatement.setString(5, PhoneText.getText());
            preparedStatement.setString(6, addressText.getText());
            
            name = emp.getFirstName();
                
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            System.err.println(e);
        }
        data.clear();
        loadData();
         MainApp.showInformationAlertBox("Employee '" + name + "' Updated Successfully");
    }

    @FXML
    public void searchEmp() {
        searchTxt.textProperty().addListener((observableValue, oldValue, newValue) -> {
            filteredData.setPredicate((Predicate<? super Employee>) emp -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (emp.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (emp.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Employee> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(employeeTable.comparatorProperty());
        employeeTable.setItems(sortedData);

    }

    @FXML
    public void deleteEmp() throws ClassNotFoundException, SQLException {

        String name = null;
        connection = DBConnection.getDBConnection();
        try {
            Employee emp = (Employee) employeeTable.getSelectionModel().getSelectedItem();
            String query = "DELETE FROM Employee WHERE EID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, emp.getEid());
            name = emp.getFirstName();
            preparedStatement.executeUpdate();
            preparedStatement.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
        data.clear();
        loadData();
        MainApp.showInformationAlertBox("Employee '" + name + "' Deleted Successfully");

    }

    public boolean emptyValidation() {
        if (firstNameText.getText().isEmpty() || lastNameText.getText().isEmpty() || NICText.getText().isEmpty() || emailText.getText().isEmpty() || PhoneText.getText().isEmpty() || addressText.getText().isEmpty()) {
            MainApp.showWarningAlertBox("These fields can't be empty");
            return false;
        }
        return true;
    }

    public boolean firstNameValidate() {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(firstNameText.getText());

        if (m.find() && m.group().equals(firstNameText.getText())) {
            MainApp.showWarningAlertBox("Invalid Name");
            return false;

        } else {
            return true;
        }
    }

    public boolean lastNameValidate() {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(lastNameText.getText());

        if (m.find() && m.group().equals(lastNameText.getText())) {
            MainApp.showWarningAlertBox("Invalid Name");
            return false;

        } else {
            return true;
        }
    }

    public boolean nicValidate() {
        Pattern p = Pattern.compile("[0-9]{12}");
        Matcher m = p.matcher(NICText.getText());

        if (m.find() && m.group().equals(NICText.getText())) {
            return true;
        } else {
            MainApp.showWarningAlertBox("Invalid NIC");
            return false;
        }
    }

    public boolean emailValidate() {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(emailText.getText());

        if (m.find() && m.group().equals(emailText.getText())) {
            return true;
        } else {
            MainApp.showWarningAlertBox("Invalid Email");
            return false;
        }
    }

    public boolean phoneValidate() {
        Pattern p = Pattern.compile("[0-9]{10}");
        Matcher m = p.matcher(PhoneText.getText());

        if (m.find() && m.group().equals(PhoneText.getText())) {
            return true;
        } else {
            MainApp.showWarningAlertBox("Incorrect Phone number");
            return false;
        }
    }
  
    public boolean genderValidate() {
        if (male.isSelected() || female.isSelected()) {
            return true;
        }
        MainApp.showWarningAlertBox("Please Select the Gender");
        return false;
    }

}
