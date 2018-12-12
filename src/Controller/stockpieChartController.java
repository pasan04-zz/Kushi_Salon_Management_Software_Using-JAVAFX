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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.stock;
import kushisaloon.StockFinalFinal;


public class stockpieChartController implements Initializable{
    
    @FXML
    private Pane view;
    


    @FXML
    private Text shampoo;//

    @FXML
    private Text conditioner;//

    @FXML
    private Text jewellery;//

    @FXML
    private Text noCategory; //
    
    @FXML
    private Text hairProduct;
    

    
    @FXML
    private Button ok;
        
    //Initialize the values
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            calculateTotal();
        } catch (IOException ex) {
            Logger.getLogger(stockpieChartController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(stockMainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StockFinalFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        hairProduct.setText(String.valueOf(j1));
        shampoo.setText(String.valueOf(j2));
        conditioner.setText(String.valueOf(j3));
        jewellery.setText(String.valueOf(j4));
        noCategory.setText(String.valueOf(j5));
        
        
        view.getChildren().clear();
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
        list.add(new PieChart.Data("Jewellery ",j1));
        list.add(new PieChart.Data("Shampoo ",j2));
        list.add(new PieChart.Data("Conditioners ",j3));
        list.add(new PieChart.Data("Hair Products ",j4));
        list.add(new PieChart.Data("No Category ",j5));
        PieChart category = new PieChart(list);
        category.setTitle("Category Availablity");
        view.getChildren().add(category);

    }

    @FXML
    void clickok(ActionEvent event) throws IOException {
        
        Stage stage = (Stage) ok.getScene().getWindow();
        stage.close();

    }
    
}
