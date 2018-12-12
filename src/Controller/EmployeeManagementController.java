/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EmployeeManagementController implements Initializable {
    
    @FXML
    private Button attenDet;

    @FXML
    private Button salaryDetails;

    @FXML
    private Button update;
    
   
    
    public void addNewEmp(ActionEvent event){
        try{
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/AddNewEmployee.fxml"));
            Parent root1 = (Parent)fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Add new employee");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        } 
    }
    
    public void removeEmp(ActionEvent event){
        try{
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/EmployeeDetails.fxml"));
            Parent root1 = (Parent)fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Remove employee");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        } 
    }
    
    public void updateEmployeePro(ActionEvent event){
        try{
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/EmployeeUpdate.fxml"));
            Parent root1 = (Parent)fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Employee details");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        } 
    }
     public void EmployeeAttendance(ActionEvent event){
        try{
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/EmployeeAttendanceDetails.fxml"));
            Parent root1 = (Parent)fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Employee details");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        } 
    }
     
    public void clickSalaryAction(ActionEvent event){
        
        

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }        
}
