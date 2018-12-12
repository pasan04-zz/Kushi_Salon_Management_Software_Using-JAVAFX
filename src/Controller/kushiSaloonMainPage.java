/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Utils.DBConnection;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 *
 * @author Pasan
 */
public class kushiSaloonMainPage implements Initializable {
    
    @FXML
    private Label label;
   
    @FXML
    private Button appoinment;

    @FXML
    private Button customer;

    @FXML
    private Button packages;

    @FXML
    private Button stock;

    @FXML
    private Button payment;

    @FXML
    private Button employee;

    @FXML
    private Button finance;
    
    
    
    @FXML
    private Circle circle1;

    @FXML
    private Circle circle2;

    @FXML
    private Circle circle3;
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
            setRotate(circle1,true,340,10);
            setRotate(circle2,true,160,20);
            setRotate(circle3,true,145,24);
            

    }   
    

    @FXML
    void  clickSupplier(ActionEvent event) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DealerRegistration.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();

    }



    @FXML
    void clickAppointment(ActionEvent event) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/addAppointment.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();

    }

    @FXML
    void clickCustomer(ActionEvent event) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/customerMainPage.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();

    }

    @FXML
    void clickEmployee(ActionEvent event) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/EmployeeManagement.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();

    }

    @FXML
    void clickFinance(ActionEvent event) throws IOException {
        
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/FXMLExpanse.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();

    }

    @FXML
    void clickPackages(ActionEvent event) throws IOException {
        
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/addPackage.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();

    }

    @FXML
    void clickPayment(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/createInvoice.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1)); 
        stage.show();

    }

    @FXML
    void clickStock(ActionEvent event) throws IOException {
                    
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/stockMain.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
        // TODO

    }
    int rotate =0;
    private void setRotate(Circle c,boolean reverse,int angle,int duration){


      RotateTransition rotateTransition = new RotateTransition(Duration.seconds(duration),c);

       
       rotateTransition.setAutoReverse(reverse);
       rotateTransition.setByAngle(angle);
       rotateTransition.setDelay(Duration.seconds(0));
       rotateTransition.setRate(3);
       rotateTransition.setCycleCount(18);
       rotateTransition.play();
}

}



    

