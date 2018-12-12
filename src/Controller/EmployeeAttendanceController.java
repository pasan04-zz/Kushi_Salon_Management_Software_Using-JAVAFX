/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class EmployeeAttendanceController implements Initializable {
    

    ObservableList<Employee> data = FXCollections.observableArrayList();
    FilteredList<Employee> filteredData = new FilteredList<>(data, e -> true);
    
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;

    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<?, ?> columnFirstName;
    @FXML
    private TableColumn<?, ?> columnLastName;
    @FXML
    private TableColumn<?, ?> columnEid; 
    @FXML
    private TextField searchText;
    @FXML
    private RadioButton present;
    @FXML
    private RadioButton absent;
    
    
    
            
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
    void clickDealer2(ActionEvent event) {
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
    


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        columnEid.setCellValueFactory(new PropertyValueFactory<>("eid"));
        columnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        columnLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
       
        try {
            loadData();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadData() throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getDBConnection();
        String query = "SELECT * FROM Employee";
        try {
            
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                data.add(new Employee(rs.getString("EID"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("NIC"), rs.getString("Email"), rs.getString("Address"), rs.getString("Phone"),rs.getString("Gender")));
                employeeTable.setItems(data);
            }
            preparedStatement.close();
            rs.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    //pasan have commented these line in order to run the program
    @FXML
    public void searchEmp() {
        searchText.textProperty().addListener((observableValue, oldValue, newValue) -> {
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
    //pasan have commented these line in order to run the program
    
    @FXML
    private void save(ActionEvent event) throws SQLException, ClassNotFoundException {

        String pres;
        if (present.isSelected()) {
            pres = "present";
        } else {
            pres = "absent";
        }
        Connection connection = DBConnection.getDBConnection();
        String query = "INSERT into Attendance VALUES(?,?)";
        preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employeeTable.getSelectionModel().getSelectedItem().getEid());
            preparedStatement.setString(2, pres);
            preparedStatement.execute();

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

}
