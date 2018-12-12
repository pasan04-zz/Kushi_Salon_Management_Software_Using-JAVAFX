package Controller;


import Utils.DBConnection;
import java.awt.event.MouseEvent;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.stock;
import Services.stockmainServices;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kushisaloon.MainApp;
import kushisaloon.StockFinalFinal;
import kushisaloon.stockcategoryPieChart;
import kushisaloon.stockView;

public class stockMainController implements Initializable {
    
    // Values injected by FXMLLoader
    @FXML
    private Button home;
    
    @FXML
    private Button appointment;
    
    @FXML
    private Button csd;
    
    @FXML
    private Button qwe;
    
    @FXML
    private Button eer;
    
    @FXML
    private Button wewr;

    @FXML
    private Button rre;

    @FXML
    private TextField bill;

    @FXML
    private TextField supplier;

    @FXML
    private TextField categ;

    @FXML
    private TextField name;
  
    @FXML
    private TextField qty;

    @FXML
    private TextField price;
    
    
    @FXML
    private Button save1;
    
    @FXML
    private Button stockid;
    
    @FXML
    private Button calcTot;
 
    @FXML
    private Button calcProducts;
    
    @FXML
    private DatePicker datepick;
    
    @FXML
    private ComboBox comboBox1;
     
    @FXML
    private ComboBox comboBox2;
    
    @FXML
    private Button updatedelete;
    
    @FXML
    private Button search;
    
    @FXML
    private Button stock1;
    
    @FXML
    private Button notification;
    
    @FXML
    private Label pdate;
    
        
    @FXML
    private Label pname;
    
    @FXML
    private Label pprice;
    
    @FXML
    private Label pqty;
    
    @FXML
    private Label psupplier;
    
    
    
    @FXML
    void clickAppointment(ActionEvent event) {
                try{
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/addAppointment.fxml"));
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
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/customerMainPage.fxml"));
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
    void  clickDealer2(ActionEvent event) {
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
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/EmployeeManagement.fxml"));
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
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/FXMLExpanse.fxml"));
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
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/addPackage.fxml"));
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
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/createInvoice.fxml"));
            Parent root1 = (Parent)fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Payment");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        } 

    }

    
    
    
    @FXML   //button click the update||Delete
    void updateDelete(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/stockdeleteUpdate.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root2));  
        stage.show(); 
        Stage stage1 = (Stage) updatedelete.getScene().getWindow();
        stage1.close();  
    }
    
    @FXML  //button click the view Category
    void viewCategory(ActionEvent event) throws IOException {
        
  
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/stockpieChart.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
  
    }
    
    @FXML  //button click summmary
    void calculateNoOfProducts(ActionEvent event) throws IOException{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/stocksummaryOfProducts.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));  
            stage.show();  
    }
       
    @FXML  //button click stock view
    void stockView(ActionEvent event) throws IOException {
        
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/stockView.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
        Stage stage1 = (Stage) stock1.getScene().getWindow();
        stage1.close();
    }

            
    @FXML  //button click notification 
    void notificationAction(ActionEvent event) throws IOException {
        
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/stocknotifications.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();


    }
    
    @FXML   //To enter the values to the database 
    private void handleButtonAction(ActionEvent event) {  
        
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(""); 
            
            
        if (emptyValidation() && quantityValidate() && priceValidate() && quantityValidate()&& productNameValidate() ) { 
            
         
            int billid= Integer.parseInt(bill.getText());
            String date = ((TextField)datepick.getEditor()).getText(); 
            String supname = (String)comboBox1.getValue();
            String proname = name.getText();
            String catname =(String) comboBox2.getValue();
            int quantity=Integer.parseInt(qty.getText());
            double proprice = Double.parseDouble(price.getText());
       

            stock st1  = new stock(billid,date,supname,proname,catname,quantity,proprice);
            st1.setDate(date);
            st1.setCateg(catname);
            st1.setName(proname);
            st1.setSupplier(supname);
            st1.setQty(quantity);
            st1.setPrice(proprice);

            stockmainServices stockdao = new stockmainServices();
            String userregistered = stockdao.registerStock(st1);
            if(userregistered.equals("SUCCESS")){
                alert.setContentText("Data Succesfully entered !");
                alert.showAndWait();
                System.out.println("succesfully entered the data");
                datepick.getEditor().setText("");
                 try {

                    Connection con = null;
                    PreparedStatement preparedStatement = null;
                    ResultSet rs =null;
                    con = DBConnection.getDBConnection();
                    String query = "select bill from stock"; 
                    preparedStatement = con.prepareStatement(query);
                    rs = preparedStatement.executeQuery(); 
                    int i=0;   
            
                    while(rs.next()){
                    i= Integer.parseInt(rs.getString("bill"));
                 }
                bill.setText(String.valueOf(i+1));
                rs.close();
                preparedStatement.close();
                qty.setText("");
                price.setText("");
                pdate.setText("");
                pname.setText("");
                pqty.setText("");
                psupplier.setText("");
                pprice.setText("");
            
                } catch (SQLException ex) {
                    Logger.getLogger(stockMainController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(StockFinalFinal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                    JOptionPane.showMessageDialog(null, "Error! Data not inserted!");
                    System.out.println("Not successfully entered the data");  
            }
        }
        
    }

    // Auto complete the stockID of the entering data
    public void fillBill(){  
        try {

            Connection con = null;
            PreparedStatement preparedStatement = null;
            ResultSet rs =null;
            con = DBConnection.getDBConnection();
            String query = "select bill from stock"; 
            preparedStatement = con.prepareStatement(query);
            rs = preparedStatement.executeQuery(); 
            int i=0;   
            
            while(rs.next()){

               i= Integer.parseInt(rs.getString("bill"));
               
            }
            bill.setText(String.valueOf(i+1));
            rs.close();
            preparedStatement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(stockMainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StockFinalFinal.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    
    //fill the supplier comboBox in javaFX tables
    ObservableList<String> options = FXCollections.observableArrayList();
    public void fillComboBox(){
        try {

            Connection con = null;
            PreparedStatement preparedStatement = null;
            ResultSet rs =null;
            con = DBConnection.getDBConnection();
            String query = "select agt_name from dealer"; 
            preparedStatement = con.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                options.add(rs.getString("agt_name"));
            }
            comboBox1.setItems(options);
            rs.close();
            preparedStatement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(stockMainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StockFinalFinal.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    //Initialize the values in the stock class
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
            
            stock s12 = new stock();
            ObservableList<String> opt = FXCollections.observableArrayList("Hair Products","Shampoo","Conditioner","Jewellery","No Category");
            comboBox2.setItems(opt);
            fillComboBox();
            fillBill();     
            
    }  
    
    
 //validate the fields   
    public boolean emptyValidation() {
        if (name.getText().isEmpty() || bill.getText().isEmpty() || qty.getText().isEmpty() || (datepick.getEditor()).getText().trim().isEmpty() ) { //|| comboBox2.getEditor().getText().isEmpty()
            MainApp.showWarningAlertBox("These fields can't be empty");
            return false;
        }
        return true;
    }



    
 //RIGHT
    public boolean productNameValidate() {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(name.getText());

        if (m.find() && m.group().equals(name.getText())) {
            MainApp.showWarningAlertBox("Invalid Product Name");
            return false;

        } else {
            return true;
        }
    }


    
    
//Paritially Right
   public boolean categoryValidate() {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(comboBox2.getEditor().getText());

        if (m.find() && m.group().equals(comboBox2.getEditor().getText())) {
            return true;
        } else {
            MainApp.showWarningAlertBox("Please enter category!!!");
            return false;
        }
    }
   
    
//RIGHT
    public boolean priceValidate() {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(bill.getText());

        if (m.find() && m.group().equals(bill.getText())) {
            return true;
        } else {
            MainApp.showWarningAlertBox("Incorrect price!");
            return false;
        }
    }
    
    
 //RIGHT   
    public boolean quantityValidate() {
        
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(qty.getText());

        if (m.find() && m.group().equals(qty.getText())) {
            return true;
        } else {
            MainApp.showWarningAlertBox("Incorrect quantity !");
            return false;
        }
    }

}
