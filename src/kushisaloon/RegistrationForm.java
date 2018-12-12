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
import javafx.stage.Stage;


public class RegistrationForm extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views2/FXMLDealerRegistration.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("RegistrationCascadeStyleSheet.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Dealer Registration");

        
        stage.show(); 
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
