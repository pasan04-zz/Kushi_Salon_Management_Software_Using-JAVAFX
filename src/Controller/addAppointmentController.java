/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.createInvoiceController.TITLE;
import Services.AppointmentServices;
import Utils.DBConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEvent;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.log4j.BasicConfigurator;
import model.Appointment;
import model.employeeSearch;
import model.packageSearch;
import model.serviceSearch;

/**
 *
 * @author Ushadi
 */
public class addAppointmentController implements Initializable {
//    private Stage primaryStage = SalonManagementSystem.primaryStage;
    
    @FXML
    private ComboBox<employeeSearch> searchEmployee;

    private List<employeeSearch> search = new ArrayList<>();

    private ObservableList<employeeSearch> obsearch;

    //package
    @FXML
    private ComboBox<packageSearch> searchPackage;

    private List<packageSearch> search1 = new ArrayList<>();
    private ObservableList<packageSearch> obsearch1;

    //service
    @FXML
    private ComboBox<serviceSearch> service;
    private List<serviceSearch> search2 = new ArrayList<>();
    private ObservableList<serviceSearch> obsearch2;

    //textfield1
    @FXML
    private TextField apid;
    @FXML
    private TextField cusID;

    @FXML
    private TextField cusName;

    @FXML
    private TextField cusAdd;

    @FXML
    private TextField telNum;

    @FXML
    private TextField tot;

    @FXML
    private Button submit;

    @FXML
    private DatePicker date;
    @FXML
    private TextField time;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
            //for navigation bar

    @FXML
    private Button btnStockUI;

    @FXML
    private Button btnAppointmentUI;

    @FXML
    private Button btnCustomerUI;

    @FXML
    private Button btnPackageUI;

    @FXML
    private Button btnPaymentUI;

    @FXML
    private Button btnEmployeeUI;

    @FXML
    private Button btnSuppllierUI;

    @FXML
    private Button finance;
   

    @FXML
    public void insertDetails(ActionEvent event) throws Exception {
        if (validationEmpty() && validateNumber() && validateCustId()) {
            int CustomerID = Integer.parseInt(cusID.getText());
            String CustomerName = cusName.getText();
            String CustomerAddress = cusAdd.getText();
            String Telephone = telNum.getText();
            double total = Double.parseDouble(tot.getText());
            String appTime = time.getText();
            LocalDate localDate = date.getValue();
            String appservice;
            String apppackage;
            String emp;

            if (service.getSelectionModel().isEmpty()) {
                appservice = service.getPromptText();
            } else {
                appservice = service.getSelectionModel().selectedItemProperty().getValue().toString();
            }
            if (searchPackage.getSelectionModel().isEmpty()) {
                apppackage = searchPackage.getPromptText();
            } else {
                apppackage = searchPackage.getSelectionModel().selectedItemProperty().getValue().toString();
            }
            if (searchEmployee.getSelectionModel().isEmpty()) {
                emp = searchEmployee.getPromptText();
            } else {
                emp = searchEmployee.getSelectionModel().selectedItemProperty().getValue().toString();
            }

            Appointment appointment = new Appointment();
            appointment.setTime(appTime);
            appointment.setDate(localDate.toString());
            appointment.setCustomerName(CustomerName);
            appointment.setCustomerAddress(CustomerAddress);
            appointment.setTelephone(Telephone);
            appointment.setCustomerID(CustomerID);
            appointment.setTotal(total);
            appointment.setEmployeeName(emp);
            appointment.setPackages(apppackage);
            appointment.setService(appservice);
            AppointmentServices appointmentServices = new AppointmentServices();

            int result = appointmentServices.insertQuery(appointment);
            if (result > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Successfully added to database");
                alert.showAndWait();
                clean();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Cannot added to database");
                alert.showAndWait();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clean();
        empSearch();
        packSearch();
        servicesSearch();
        setNavigationbarToolTip();

        // force the appointment number field to be numeric only
        apid.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    apid.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
    @FXML 
    void newCust(ActionEvent event)throws IOException{
        AnchorPane root = FXMLLoader.<AnchorPane>load(getClass().getResource("/view/appointmentView.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/salonmanagementsystem/appointmentView.fxml"));
//        Parent root1 = (Parent)fxmlLoader.load();
//        //Stage stage = new Stage();
//        primaryStage.setScene(new Scene(root1));
//        primaryStage.show();
        
    }

    @FXML
    public void submit(ActionEvent event) throws Exception {
        int CustomerID = Integer.parseInt(cusID.getText());
        System.out.println(CustomerID);
    }

    public void empSearch() {
                try{
            Connection connection = DBConnection.getDBConnection();
            String query = "SELECT EID,FirstName,LastName FROM Employee";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                
        employeeSearch employeeSearch1 = new employeeSearch(resultSet.getInt("EID"), resultSet.getString("FirstName")+" "+ resultSet.getString("LastName"));
                search.add(employeeSearch1);
                
            }
            preparedStatement.close();
            resultSet.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(addAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(addAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        obsearch = FXCollections.observableArrayList(search);

        searchEmployee.setItems(obsearch);
    }

    public void packSearch() {
//        packageSearch packageSearch1 = new packageSearch(1, "Simple pack");
//        packageSearch packageSearch2 = new packageSearch(2, "Dark Pack");
//        packageSearch packageSearch3 = new packageSearch(3, "Bridal Pack");

        try{
            Connection connection = DBConnection.getDBConnection();
            String query = "SELECT * FROM package_table";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                packageSearch packageSearch1 = new packageSearch(resultSet.getInt("PackageId"),resultSet.getString("packageName"),resultSet.getDouble("PackagePrice"));
                search1.add(packageSearch1);
                
            }
            preparedStatement.close();
            resultSet.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(addAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(addAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        search1.add(packageSearch1);
//        search1.add(packageSearch2);
//        search1.add(packageSearch3);
//        search1.add(packageSearch1);
//        search1.add(packageSearch2);
//        search1.add(packageSearch3);

        obsearch1 = FXCollections.observableArrayList(search1);

        searchPackage.setItems(obsearch1);
    }

    public void servicesSearch() {
        
        try{
            Connection connection = DBConnection.getDBConnection();
            String query = "SELECT * FROM service_table";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                serviceSearch serviceSearch1 = new serviceSearch(resultSet.getInt("ServiceId"),resultSet.getString("ServiceName"),resultSet.getDouble("ServicePrice"));
                search2.add(serviceSearch1);
                
            }
            preparedStatement.close();
            resultSet.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(addAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(addAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        obsearch2 = FXCollections.observableArrayList(search2);

        service.setItems(obsearch2);

    }
    @FXML
    public void onClickService(ActionEvent event){
        double sprice;
        if(service.getSelectionModel().isEmpty())
            sprice = 0;
        else
            sprice = service.getSelectionModel().getSelectedItem().getPrice();
        double pprice;
        if(searchPackage.getSelectionModel().isEmpty())
            pprice = 0;
        else
            pprice = searchPackage.getSelectionModel().getSelectedItem().getPrice();
        tot.setText(String.valueOf(sprice+pprice));
    }
    public void search(ActionEvent event) {
        String keyword = apid.getText();

        int appid = Integer.parseInt(keyword);
        AppointmentServices appointmentServices = new AppointmentServices();
        Appointment appointment = appointmentServices.getAppointmentByID(appid);

        if (appointment != null) {
            if (appointment.getCustomerID() != 0) {
                cusName.setText(appointment.getCustomerName());
                time.setText(appointment.getTime());
                cusID.setText(String.valueOf(appointment.getCustomerID()));
                cusAdd.setText(appointment.getCustomerAddress());
                telNum.setText(appointment.getTelephone());
                tot.setText(String.valueOf(appointment.getTotal()));

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(appointment.getDate(), dtf);

                date.setValue(localDate);
                searchPackage.promptTextProperty().set(appointment.getPackages());
                service.promptTextProperty().set(appointment.getService());
                searchEmployee.promptTextProperty().set(appointment.getEmployeeName());
                btnUpdate.setVisible(true);
                submit.setVisible(false);
                btnDelete.setVisible(true);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Appointment was not found");
                alert.showAndWait();
                clean();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Appointment was not found");
            alert.showAndWait();
            clean();
        }

    }

    public void clean() {
        setAppointmentID();
        cusID.clear();
        cusName.clear();
        cusAdd.clear();
        telNum.clear();
        time.clear();
        apid.clear();
        tot.clear();
        searchPackage.getSelectionModel().clearSelection();
        service.getSelectionModel().clearSelection();
        searchEmployee.promptTextProperty().set("Employee");
        LocalDate localDate = LocalDate.now();
        date.setValue(localDate);
        btnUpdate.setVisible(false);
        submit.setVisible(true);
        btnDelete.setVisible(false);
    }

    @FXML
    public void update(ActionEvent actionEvent) throws Exception {
        if (validationEmpty() && validateCustId() && validateNumber()) {
            int CustomerID = Integer.parseInt(cusID.getText());
            int appid = Integer.parseInt(apid.getText());
            String CustomerName = cusName.getText();
            String CustomerAddress = cusAdd.getText();
            String Telephone = telNum.getText();
            double total = Double.parseDouble(tot.getText());
            String appTime = time.getText();
            LocalDate localDate = date.getValue();
            String appservice;
            String apppackage;
            String emp;

            if (service.getSelectionModel().isEmpty()) {
                appservice = service.getPromptText();
            } else {
                appservice = service.getSelectionModel().selectedItemProperty().getValue().toString();
            }
            if (searchPackage.getSelectionModel().isEmpty()) {
                apppackage = searchPackage.getPromptText();
            } else {
                apppackage = searchPackage.getSelectionModel().selectedItemProperty().getValue().toString();
            }
            if (searchEmployee.getSelectionModel().isEmpty()) {
                emp = searchEmployee.getPromptText();
            } else {
                emp = searchEmployee.getSelectionModel().selectedItemProperty().getValue().toString();
            }

            Appointment appointment = new Appointment();
            appointment.setAppointmentId(appid);
            appointment.setTime(appTime);
            appointment.setDate(localDate.toString());
            appointment.setCustomerName(CustomerName);
            appointment.setCustomerAddress(CustomerAddress);
            appointment.setTelephone(Telephone);
            appointment.setCustomerID(CustomerID);
            appointment.setTotal(total);
            appointment.setEmployeeName(emp);
            appointment.setPackages(apppackage);
            appointment.setService(appservice);
            AppointmentServices appointmentServices = new AppointmentServices();

            int result = appointmentServices.updateQuery(appointment);
            if (result > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Changed");
                alert.showAndWait();
                clean();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Cannot added to database");
                alert.showAndWait();
            }
        }
    }

    @FXML
    public void delete(ActionEvent actionEvent) {
        int appid = Integer.parseInt(apid.getText());
        AppointmentServices appointmentServices = new AppointmentServices();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Do you want to delete this appointment");

        Optional<ButtonType> res = alert.showAndWait();

        if (res.orElse(ButtonType.OK) == ButtonType.OK) {
            int result = appointmentServices.delete(appid);
            if (result > 0) {
                clean();
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Success Dialog");
                alert1.setHeaderText(null);
                alert1.setContentText("Successfully deleted");
                alert1.showAndWait();
                clean();
            } else {
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Success Dialog");
                alert1.setHeaderText(null);
                alert1.setContentText("Cannot be delete");
                alert1.showAndWait();
            }
        }

    }
    
    
    //validation empty

    private boolean validationEmpty() {
        if (cusID.getText().isEmpty() | cusName.getText().isEmpty() | cusAdd.getText().isEmpty() | telNum.getText().isEmpty() | tot.getText().isEmpty() | time.getText().isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Warning Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Please fill all the details");

            alert1.showAndWait();
            return false;
        }
        if (date.getEditor().getText().isEmpty()) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Warning Dialog");
            alert2.setHeaderText(null);
            alert2.setContentText("Please enter the date");

            alert2.showAndWait();

            return false;
        }
        return true;
    }
    //Number validations

    private boolean validateCustId() {

        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(cusID.getText());

        if (m.find() && m.group().equals(cusID.getText())) {
            return true;

        } else {

            Alert alert4 = new Alert(Alert.AlertType.WARNING);
            alert4.setTitle("Warning Dialog");
            alert4.setHeaderText(null);
            alert4.setContentText("Please enter valid Customer ID!");

            alert4.showAndWait();

            return false;

        }

    }

    private boolean validateNumber() {

        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(telNum.getText());

        if (m.find() && m.group().equals(telNum.getText())) {
            return true;

        } else {

            Alert alert4 = new Alert(Alert.AlertType.WARNING);
            alert4.setTitle("Warning Dialog");
            alert4.setHeaderText(null);
            alert4.setContentText("Please enter valid phone number!");

            alert4.showAndWait();

            return false;

        }

    }

  /*   @FXML
    public void newCust(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Register a customer");
        alert.setHeaderText(null);
        alert.setContentText("Do you want to register this customer ?");
        alert.showAndWait();
    }*/
    
    
    public void setAppointmentID() {
        AppointmentServices appointmentServices = new AppointmentServices();
        int id = appointmentServices.getAppointmentNumber();
        apid.setPromptText(String.valueOf(id));

    }
    //for navigation bar
    @FXML
    public void viewAppointmentUIb(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.<AnchorPane>load(getClass().getResource("/view/addAppointment.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            //    stage.setAlwaysOnTop(true);
            //        stage.resizableProperty().setValue(Boolean.FALSE);//disable maximize btn
            //stage.initStyle(StageStyle.UTILITY);//disable mini,max,e
            //stage.initStyle(StageStyle.UNDECORATED);//hide all button
            stage.setTitle(TITLE);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(createInvoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void viewCustomerUIb(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.<AnchorPane>load(getClass().getResource("/view/customerMainPage.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            //    stage.setAlwaysOnTop(true);
            //        stage.resizableProperty().setValue(Boolean.FALSE);//disable maximize btn
            //stage.initStyle(StageStyle.UTILITY);//disable mini,max,e
            //stage.initStyle(StageStyle.UNDECORATED);//hide all button
            stage.setTitle(TITLE);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(createInvoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void viewEmployeeUIb(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.<AnchorPane>load(getClass().getResource("/view/EmployeeManagement.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            //    stage.setAlwaysOnTop(true);
            //        stage.resizableProperty().setValue(Boolean.FALSE);//disable maximize btn
            //stage.initStyle(StageStyle.UTILITY);//disable mini,max,e
            //stage.initStyle(StageStyle.UNDECORATED);//hide all button
            stage.setTitle(TITLE);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(createInvoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void clickFinance(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/FXMLExpanse.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle(TITLE);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(createInvoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void viewPackageUIb(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.<AnchorPane>load(getClass().getResource("/view/addPackage.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            //    stage.setAlwaysOnTop(true);
            //        stage.resizableProperty().setValue(Boolean.FALSE);//disable maximize btn
            //stage.initStyle(StageStyle.UTILITY);//disable mini,max,e
            //stage.initStyle(StageStyle.UNDECORATED);//hide all button
            stage.setTitle(TITLE);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(createInvoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void viewPaymentUIb(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.<AnchorPane>load(getClass().getResource("/view/createInvoice.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            //    stage.setAlwaysOnTop(true);
            //        stage.resizableProperty().setValue(Boolean.FALSE);//disable maximize btn
            //stage.initStyle(StageStyle.UTILITY);//disable mini,max,e
            //stage.initStyle(StageStyle.UNDECORATED);//hide all button
            stage.setTitle(TITLE);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(createInvoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void viewStockUIb(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.<AnchorPane>load(getClass().getResource("/view/stockMain.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            //    stage.setAlwaysOnTop(true);
            //        stage.resizableProperty().setValue(Boolean.FALSE);//disable maximize btn
            //stage.initStyle(StageStyle.UTILITY);//disable mini,max,e
            //stage.initStyle(StageStyle.UNDECORATED);//hide all button
            stage.setTitle(TITLE);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(createInvoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void viewSupplierUIb(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.<AnchorPane>load(getClass().getResource("/view/DealerRegistration.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            //    stage.setAlwaysOnTop(true);
            //        stage.resizableProperty().setValue(Boolean.FALSE);//disable maximize btn
            //stage.initStyle(StageStyle.UTILITY);//disable mini,max,e
            //stage.initStyle(StageStyle.UNDECORATED);//hide all button
            stage.setTitle(TITLE);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(createInvoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setNavigationbarToolTip() {
        btnAppointmentUI.setTooltip(new Tooltip("Appointment Management System"));
        btnCustomerUI.setTooltip(new Tooltip("Customer Management System"));
        btnEmployeeUI.setTooltip(new Tooltip("Employee Management System"));
        btnStockUI.setTooltip(new Tooltip("Stock Management System"));
        btnPackageUI.setTooltip(new Tooltip("Package Management System"));
        btnPaymentUI.setTooltip(new Tooltip("Payment Management System"));
        btnSuppllierUI.setTooltip(new Tooltip("Supplier Management System"));
        finance.setTooltip(new Tooltip("Finance Management System"));

    }
    
   @FXML
   public  void cId(ActionEvent event) {
     
       String keyword = cusID.getText();
        AppointmentServices appointmentServices = new AppointmentServices();

        try{
            Connection connection = DBConnection.getDBConnection();
            String query = "select * from customer where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if(Integer.parseInt(keyword) <100)
                keyword = "00"+keyword;
            preparedStatement.setString(1, keyword);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()){
                cusAdd.setText(resultSet.getString("address"));
                telNum.setText(resultSet.getString("phonenumber"));
                cusName.setText(resultSet.getString("firstname") + " " +resultSet.getString("lastname"));
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Customer ID  was not found");
                alert.showAndWait();
                clean();
            }
            

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }catch(NumberFormatException|NullPointerException e){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Invalid Customer ID");
                alert.showAndWait();
                clean();
            }

    }

    
}
