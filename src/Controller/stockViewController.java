/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Utils.DBConnection;
import java.beans.Statement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.stock;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.log4j.BasicConfigurator;
import kushisaloon.StockFinalFinal;

public class stockViewController implements Initializable{

    @FXML
    private TextField search_txt;
    
    @FXML
    private TableView<stock> tableView;

    @FXML
    private TableColumn<stock, String> name12;

    @FXML
    private TableColumn<stock, String> categ12;

    @FXML
    private TableColumn<stock, Double> price12;
    
    @FXML
    private TableColumn<stock, Integer> qty12;

    @FXML
    private TableColumn<stock, Integer> bill12;

    @FXML
    private Button notification;
        
    @FXML
    private Button calcProducts;
    
    @FXML
    private Button addNew;
    
    @FXML
    private Button additem;
    
    @FXML
    private Button report;
    
    @FXML
    private Button updatedelete;

    
   @FXML   //when button click Report, this will generate the report
    void createReport(ActionEvent event) throws IOException,SQLException,FileNotFoundException,JRException,IOException{
        
        BasicConfigurator.configure();
        
        String path = "src\\views\\Reports\\stockReport.jrxml";
        InputStream input = new FileInputStream(new File(path));
        
        JRDesignQuery jrQuery = new JRDesignQuery();
        String query = "select * from stock";
        jrQuery.setText(query);
        
        JasperDesign jasperDesign = JRXmlLoader.load(input);
       
        jasperDesign.setQuery(jrQuery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint;
        try {
            jasperPrint = JasperFillManager.fillReport(jasperReport,null,DBConnection.getDBConnection());
            JasperViewer viewer = new JasperViewer(jasperPrint,false);
            viewer.setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(stockViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
 
    
    @FXML //button click notification
    void notificationAction(ActionEvent event) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/stocknotifications.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
    }
    

    @FXML  //button click summary
    void calculateNoOfProducts(ActionEvent event) throws IOException{
        
        
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/stocksummaryOfProducts.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));  
            stage.show();
    
        
    }

    @FXML           //Button click add new Items
    void additems(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/stockMain.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show(); 
        Stage stage1 = (Stage) additem.getScene().getWindow();
        stage1.close();
    }
  
    @FXML             //Button click to enter the view category page 
    void viewCategory(ActionEvent event) throws IOException {
        
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/stockpieChart.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
    

    }
    @FXML         //Button click update|delete
    void updateDelete(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/stockdeleteUpdate.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root2));  
        stage.show(); 
        Stage stage1 = (Stage) updatedelete.getScene().getWindow();
        stage1.close();
    }

    
    @FXML
    void stockmain(ActionEvent event) throws IOException {
        
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/stockMain.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();

    }
    //fill the table values
    ObservableList<stock>filltable = FXCollections.observableArrayList();   
    public void fillTableValues(){

        try { 
            Connection con = null;
            PreparedStatement preparedStatement = null;
            ResultSet rs =null;
            con = DBConnection.getDBConnection();
            stock st1 = new stock();
            String query = "select * from stock"; 
            preparedStatement = con.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while(rs.next()){   

              stock st2= new stock();
              st2.setBill(rs.getInt("bill"));
              st2.setName(rs.getString("name"));
              st2.setCateg(rs.getString("categ"));  
              st2.setQty(rs.getInt("qty"));
              st2.setPrice(rs.getDouble("price"));
              
              filltable.add(st2);
            }
            tableView.setItems(filltable);
        } catch (SQLException ex) {
            
            Logger.getLogger(stockMainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            
            Logger.getLogger(StockFinalFinal.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }  
    

    @FXML            //Search the values in the table
    private void onSearch(ActionEvent event) {
            
        
            Connection con = null;
            PreparedStatement preparedStatement = null;
            ResultSet rs =null;
            if(search_txt.getText().equals("")){    
            fillTableValues();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Search Box Cannot be empty!!");
            alert.showAndWait();
            alert.setTitle("Error!");
            alert.setHeaderText("");
                
            }else{
                try {
                 filltable.clear();
                con = DBConnection.getDBConnection();
 
                String searchquery = "SELECT * from stock where bill LIKE '%"+search_txt.getText()+"%'";
                preparedStatement = con.prepareStatement(searchquery);
                rs = preparedStatement.executeQuery();
                if(rs.next()){
                    filltable.add(new stock(rs.getInt(2),rs.getString(4)," "+rs.getString(5),rs.getInt(6),rs.getDouble(7)));
                
                }
                tableView.setItems(filltable);   
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(stockMainController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(stockMainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override  //Initialize the table values in the beginning of the value
    public void initialize(URL url, ResourceBundle rb) {

        bill12.setCellValueFactory(new PropertyValueFactory<>("bill"));
        name12.setCellValueFactory(new PropertyValueFactory<>("name"));
        categ12.setCellValueFactory(new PropertyValueFactory<>("categ"));
        qty12.setCellValueFactory(new PropertyValueFactory<>("qty")); 
        price12.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        fillTableValues();
        
    
    }
            
    

}
