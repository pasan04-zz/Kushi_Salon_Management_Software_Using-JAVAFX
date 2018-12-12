/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kushisaloon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 *
 * @author NADEESH
 */
public class MainApp extends Application {

    public static void showInformationAlertBox(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public static void showWarningAlertBox(String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    @Override
    public void start(Stage Stage)throws Exception {
        
      Parent root = FXMLLoader.load(getClass().getResource("/View/EmployeeManagement.fxml"));
        Scene scene = new Scene(root);
        
        Stage.setScene(scene);
        Stage.show();
      
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
