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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import kushisaloon.StockFinalFinal;
//import stockfinalfinal.StockFinalFinal;

/**
 * FXML Controller class
 *
 * @author Pasan
 */
public class stockNotificationsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button ok;
    
    @FXML
    private Text note1;
    
    @FXML
    private Text note2;
        
    @FXML
    private Text note3;
            
    @FXML
    private Text note4;
    
    @FXML
    private Text note5;
            
    @FXML
    private Text note6;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            calculateTotal();
        } catch (IOException ex) {
            Logger.getLogger(stockNotificationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    } 
    
    
    int j1=0;
    int j2 =0;
    int j3 =0;
    int j4 =0;
    int j5 =0;
    public void calculateTotal()throws IOException{
        

            int bill12 =0;
            String categ12 = null;
            String name12;
            int qty12 =0;
            Double price12 =0.0 ;
        try { 
            Connection con = null;
            PreparedStatement preparedStatement = null;
            ResultSet rs =null;
            con = DBConnection.getDBConnection();
            String query = "select * from stock"; 
            preparedStatement = con.prepareStatement(query);
            rs = preparedStatement.executeQuery();

        while(rs.next()){
              bill12 = rs.getInt("bill");
              categ12 = rs.getString("categ");
              name12 = rs.getString("name");
              qty12 = rs.getInt("qty");
              price12 = rs.getDouble("price");
              
              
             // "Hair_Products","Shampoo","Conditioner","Jewellery","No Category"
              if("Jewellery".equals(categ12)){  
                  j1 = j1+qty12;
              }
              if("Shampoo".equals(categ12)){
                      
                  j2 = j2+qty12;
              }
              if("Conditioner".equals(categ12)){
                  
                  j3 = j3+qty12;
              }
              if("Hair Products".equals(categ12)){ 
                  j4 = j4+qty12;
              }
              if("No Category".equals(categ12)){ 
                  j5 = j5+qty12;
              }
                   
        }
        if(j1 <= 10){
            
            note1.setText("* Jewellery Product is nearly over, Please re-stock it. ");
        }
        if(j2 <= 10){
            
            note2.setText("* shampoo  is nearly over, Please re-stock it. ");
        }
        if(j3 <= 10){
            
            note3.setText("* Conditioners are nearly over, Please re-stock it. ");
        }
        if(j4 <= 10){
            
            note4.setText("* Hair Products are nearly over, Please re-stock it. ");
        }
        if(j5 <= 10){
            
            note5.setText("* No category is nearly over, Please re-stock it. ");
        }
        if(j1> 10 && j2 >10 && j3>10 && j4>10 && j5>10){
            
            note6.setText("* Sorry,no any notifications yet!! ");
        }
        
            
        } catch (SQLException ex) {
            Logger.getLogger(stockMainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StockFinalFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

    }
        @FXML
    void actionOk(ActionEvent event) throws IOException {
        
 
        Stage stage = (Stage) ok.getScene().getWindow();
        stage.close();

    }
    
    
    
}
