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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import kushisaloon.StockFinalFinal;

/**
 * FXML Controller class
 *
 * @author Pasan
 */
public class stockSummaryOfProductsController implements Initializable {



    @FXML
    private Text products;

    @FXML
    private Text price;
    
    @FXML
    private Button ok;
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            calculateNoOfProducts();
            calculateTotal();
        } catch (IOException ex) {
            Logger.getLogger(stockSummaryOfProductsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(stockSummaryOfProductsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    
    void calculateNoOfProducts( ) throws IOException, SQLException{
        
        try {
            Connection con = null;
            PreparedStatement preparedStatement = null;
            ResultSet rs =null;
            con = DBConnection.getDBConnection();
            String query = "select qty from stock"; 
            preparedStatement = con.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            int total = 0;
            while(rs.next()){

                int x = rs.getInt("qty");
                total = total+x;              
                
            }   
            
            products.setText(String.valueOf(total));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(stockSummaryOfProductsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    } 
    void calculateTotal() throws IOException{
        
            try {

            Connection con = null;
            PreparedStatement preparedStatement = null;
            ResultSet rs =null;
            con = DBConnection.getDBConnection();
            String query = "select price,qty from stock"; 
            preparedStatement = con.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            double multi=0;
            double total = 0;
            while(rs.next()){
                double x = rs.getInt("price");
                int y = rs.getInt("qty");
                multi = x*y;
                total = total+multi;
            }

            preparedStatement.close();
            price.setText(String.valueOf(total));
            
            
        } catch (SQLException ex) {
            Logger.getLogger(stockMainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StockFinalFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
        @FXML
    void clickok(ActionEvent event) throws IOException {
        
       /*FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/stockMain.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();*/
        Stage stage = (Stage) ok.getScene().getWindow();
    // do what you have to do
        stage.close();

    }

  

}
