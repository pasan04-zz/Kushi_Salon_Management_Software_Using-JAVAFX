/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.management.Query;  
//Edited by pasan in order to run the program. Should check later
/*import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRDesignViewer;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.log4j.BasicConfigurator;*/
import Controller.DealerRegistrationController;
import model.ModelTable;
import Utils.DBConnection;

public class DealerViewController implements Initializable {

    @FXML
    private Button exit_view;

    @FXML
    private MenuBar menuBar2;
    @FXML
    private TableView<ModelTable> table;
    @FXML
    private TableColumn<ModelTable, String> col_id;
    @FXML
    private TableColumn<ModelTable, String> col_cmp_name;
    @FXML
    private TableColumn<ModelTable, String> col_add;
    @FXML
    private TableColumn<ModelTable, String> col_email;
    @FXML
    private TableColumn<ModelTable, String> col_product;
    @FXML
    private TableColumn<ModelTable, String> col_phno;
    @FXML
    private TableColumn<ModelTable, String> col_agt_name;
    @FXML
    private TableColumn<ModelTable, String> col_agt_phno;
    @FXML
    private TableColumn<ModelTable, String> col_date;
    @FXML
    private Label lblID;
    @FXML
    private TextField txtCmp_name;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtCmp_Email;
    @FXML
    private TextField txtProduct;
    @FXML
    private TextField txt_Ph_no;
    @FXML
    private TextField txt_Agt_Name;
    @FXML
    private TextField txt_Agt_Ph_No;
    @FXML
    private TextField txt_Date;
    @FXML
    private Button btn_update;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_report;
    @FXML
    private TextField txtSearch;

    //connection objects
    Connection con;
    ResultSet rs = null;
    PreparedStatement pst = null;

    //ObserverList
    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
    // ObservableList<Combo> oblist1 = FXCollections.observableArrayList();
    FilteredList<ModelTable> filtlist = new FilteredList(oblist, e -> true);

    

    //Sorted search bar
    @FXML
    private void Search(KeyEvent event) {

        txtSearch.textProperty().addListener(((observable, oldValue, newValue) -> {

            filtlist.setPredicate((Predicate<? super ModelTable>) m1 -> {
                if (newValue == null || newValue.isEmpty()) {

                    return true;
                }
                if (m1.getDealerId().contains(newValue)) {

                    return true;
                }
                if (m1.getCmp_name().contains(newValue)) {

                    return true;
                }
                if (m1.getDate().contains(newValue)) {

                    return true;
                }
                if (m1.getProd_type().contains(newValue)) {

                    return true;
                }

                return false;

            });

            SortedList<ModelTable> sort = new SortedList(filtlist);
            sort.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sort);

        }));

    }

 
    //validate Empty
    @FXML
    private boolean validationEmpty() {

        if (lblID.getText().isEmpty() | txtCmp_name.getText().isEmpty() | txtAddress.getText().isEmpty() | txtCmp_Email.getText().isEmpty() | txtProduct.getText().isEmpty() | txt_Ph_no.getText().isEmpty() | txt_Agt_Name.getText().isEmpty() | txt_Agt_Ph_No.getText().isEmpty()) {

            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Warning Dialog");
            alert2.setHeaderText(null);
            alert2.setContentText("Please select a dealer");

            alert2.showAndWait();

            return false;

        }
        if (txt_Date.getText().isEmpty()) {
            Alert alert3 = new Alert(Alert.AlertType.WARNING);
            alert3.setTitle("Warning Dialog");
            alert3.setHeaderText(null);
            alert3.setContentText("Please enter the date");

            alert3.showAndWait();

            return false;
        }
        return true;
    }

    //Comapany Name Validation
    @FXML
    private boolean CmpNameValidation() {

        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(txtCmp_name.getText());

        if (m.find() && m.group().equals(txtCmp_name.getText())) {

            Alert alert5 = new Alert(Alert.AlertType.WARNING);
            alert5.setTitle("Warning Dialog");
            alert5.setHeaderText(null);
            alert5.setContentText("Please enter valid Name!");

            alert5.showAndWait();
            return false;
        } else {

            return true;

        }
    }

    //Agent name validation
    @FXML
    private boolean AgtNameValidation() {

        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(txt_Agt_Name.getText());

        if (m.find() && m.group().equals(txt_Agt_Name.getText())) {

            Alert alert5 = new Alert(Alert.AlertType.WARNING);
            alert5.setTitle("Warning Dialog");
            alert5.setHeaderText(null);
            alert5.setContentText("Please enter valid Name!");

            alert5.showAndWait();
            return false;
        } else {

            return true;

        }
    }

    //number Validation
    @FXML
    private boolean validateNumber() {

        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(txt_Ph_no.getText());
        Matcher m1 = p.matcher(txt_Agt_Ph_No.getText());

        if (m.find() && m.group().equals(txt_Ph_no.getText()) && m1.find() && m.group().equals(txt_Ph_no.getText())) {

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

    //Email Validation
    @FXML
    private boolean EmailValidation() {

        Pattern p1 = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m1 = p1.matcher(txtCmp_Email.getText());

        if (m1.find() && m1.group().equals(txtCmp_Email.getText())) {

            return true;

        } else {

            Alert alert5 = new Alert(Alert.AlertType.WARNING);
            alert5.setTitle("Warning Dialog");
            alert5.setHeaderText(null);
            alert5.setContentText("Please enter valid Email!");

            alert5.showAndWait();

            return false;

        }

    }


    //alert if Updated
    @FXML
    private void showAlert1() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Dealer is Updated!!!");

        alert.showAndWait();

    }

    //alert if not Updated
    @FXML
    private void showAlert2() {

        Alert alert1 = new Alert(Alert.AlertType.WARNING);
        alert1.setTitle("Information Dialog");
        alert1.setHeaderText(null);
        alert1.setContentText("Dealer is Not Updated!!");

        alert1.showAndWait();

    }

    //Alert if deleted
    @FXML
    private void showAlert3() {

        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("Information Dialog");
        alert1.setHeaderText(null);
        alert1.setContentText("Dealer is Deleted!!");

        alert1.showAndWait();

    }

    //Alert if not deleted
    @FXML
    private void showAlert4() {

        Alert alert1 = new Alert(Alert.AlertType.WARNING);
        alert1.setTitle("Information Dialog");
        alert1.setHeaderText(null);
        alert1.setContentText("Dealer is Not Deleted!!");

        alert1.showAndWait();

    }

    //tooltips
    public void Ttips() {

        final Tooltip search = new Tooltip();
        search.setText("Enter a product type or a date");
        txtSearch.setTooltip(search);

        final Tooltip id = new Tooltip();
        id.setText("Dealer ID");
        lblID.setTooltip(id);

        final Tooltip cmpName = new Tooltip();
        cmpName.setText("Company Name");
        txtCmp_name.setTooltip(cmpName);

        final Tooltip cmpAdd = new Tooltip();
        cmpAdd.setText("Company Address");
        txtAddress.setTooltip(cmpAdd);

        final Tooltip email = new Tooltip();
        email.setText("Company Email");
        txtCmp_Email.setTooltip(email);

        final Tooltip prdType = new Tooltip();
        prdType.setText("Product Type");
        txtProduct.setTooltip(prdType);

        final Tooltip cmpPhNo = new Tooltip();
        cmpPhNo.setText("Company Phone Number");
        txt_Ph_no.setTooltip(cmpPhNo);

        final Tooltip agtName = new Tooltip();
        agtName.setText("Agent Name");
        txt_Agt_Name.setTooltip(agtName);

        final Tooltip agtPhNo = new Tooltip();
        agtPhNo.setText("Agent Phone Number");
        txt_Agt_Ph_No.setTooltip(agtPhNo);

        final Tooltip date = new Tooltip();
        date.setText("Date");
        txt_Date.setTooltip(date);

        final Tooltip updateBtn = new Tooltip();
        updateBtn.setText("Update Dealer");
        btn_update.setTooltip(updateBtn);

        final Tooltip deleteBtn = new Tooltip();
        deleteBtn.setText("Delete Dealer");
        btn_delete.setTooltip(deleteBtn);

        final Tooltip exitBtn = new Tooltip();
        exitBtn.setText("Exit");
        exit_view.setTooltip(exitBtn);

        final Tooltip reportBtn = new Tooltip();
        reportBtn.setText("Generate Report");
        btn_report.setTooltip(reportBtn);

        final Tooltip tableView = new Tooltip();
        tableView.setText("Dealer Table");
        table.setTooltip(tableView);

    }

    //Start
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Ttips();
        try {

            con = DBConnection.getDBConnection();

            try {

                String query = "select * from dealer";

                rs = con.createStatement().executeQuery(query);

                while (rs.next()) {

                    oblist.add(new ModelTable(rs.getString("dealerId"), rs.getString("cmp_name"), rs.getString("cmp_address"), rs.getString("cmp_email"), rs.getString("prod_type"), rs.getString("cmp_phno"), rs.getString("agt_name"), rs.getString("agt_phno"), rs.getString("date")));
                }

            } catch (Exception e) {

            }

            col_id.setCellValueFactory(new PropertyValueFactory<>("dealerId"));
            col_cmp_name.setCellValueFactory(new PropertyValueFactory<>("cmp_name"));
            col_add.setCellValueFactory(new PropertyValueFactory<>("cmp_address"));
            col_email.setCellValueFactory(new PropertyValueFactory<>("cmp_email"));
            col_product.setCellValueFactory(new PropertyValueFactory<>("prod_type"));
            col_phno.setCellValueFactory(new PropertyValueFactory<>("cmp_phno"));
            col_agt_name.setCellValueFactory(new PropertyValueFactory<>("agt_name"));
            col_agt_phno.setCellValueFactory(new PropertyValueFactory<>("agt_phno"));
            col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

            table.setItems(oblist);

            con.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(DealerViewController.class.getName()).log(Level.SEVERE, null, ex);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DealerViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        getValueToText();

    }

    // Update Button Action
    @FXML
    private void update_button_Action(ActionEvent event) throws SQLException, ClassNotFoundException {

        con = DBConnection.getDBConnection();

        try {
            if (validationEmpty() && validateNumber() && EmailValidation() && CmpNameValidation() && AgtNameValidation()) {

                //String id1 = txtID.getText();
                String query = "UPDATE dealer SET dealerID = ?, cmp_name = ?, cmp_address = ?, cmp_email = ?, prod_type = ?, cmp_phno = ? , agt_name = ?, agt_phno = ? , date = ? where dealerID = ?";

                pst = con.prepareStatement(query);

                pst.setString(1, lblID.getText());
                pst.setString(2, txtCmp_name.getText());
                pst.setString(3, txtAddress.getText());
                pst.setString(4, txtCmp_Email.getText());
                pst.setString(5, txtProduct.getText());
                pst.setString(6, txt_Ph_no.getText());
                pst.setString(7, txt_Agt_Name.getText());
                pst.setString(8, txt_Agt_Ph_No.getText());
                pst.setString(9, txt_Date.getText());
                pst.setString(10, lblID.getText());

                pst.execute();
                // pst.executeQuery();

                if (query != null) {
                    showAlert1();
                    System.out.println("User Updated!");
                } else {
                    showAlert2();
                    System.out.println("User not Updated!");
                }
                clearFields();
                refreshTable();

            }

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Query is not executed!");
        } finally {
            con.close();
            pst.close();

        }

    }

//Delete Button Action
    public void delete_Button_Action(ActionEvent event) throws SQLException, ClassNotFoundException {

       con = DBConnection.getDBConnection();

        try {

            if (validationEmpty()) {

                String query = "delete from dealer where dealerId = ?";

                pst = con.prepareStatement(query);
                pst.setString(1, lblID.getText());

                pst.executeUpdate();

                if (query != null) {

                    showAlert3();
                } else {

                    showAlert4();
                }
                clearFields();
                refreshTable();

            }

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("not exec");
        } finally {

            con.close();
            pst.close();
        }
    }

    //Exit Button Action
    @FXML
    private void Exit_button_action(ActionEvent event) {

        Stage stage = (Stage) exit_view.getScene().getWindow();
        stage.close();
        
        
    }
    
    @FXML
    private void  Report_button_action(ActionEvent event) {

  
        
    }
    
   

    //Report button Action
   /* @FXML
    private void Report_button_action(ActionEvent event) throws FileNotFoundException, JRException, SQLException {


        Pattern p = Pattern.compile("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$");
        Matcher m = p.matcher(txtSearch.getText());

        Pattern p1 = Pattern.compile("[0-9]+");
        Matcher m1 = p1.matcher(txtSearch.getText());

        if (m.find() && m.group().equals(txtSearch.getText())) {

            try {
                con = DBConnection.getInstence().getConnection();
                String date = txtSearch.getText();

                String query = "select * from dealer where date ='" + date + "'";

                //Alert
                Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                al.setTitle("Information Dialog");
                al.setHeaderText(null);
                al.setContentText("Generating Report...Please wait!!");

                al.showAndWait();

                BasicConfigurator.configure();
                String path = "C:\\Users\\A C E R\\Documents\\NetBeansProjects\\RegistrationForm\\RegistrationForm\\src\\dealerview\\DealerNew.jrxml";
                InputStream input = new FileInputStream(new File(path));

                JRDesignQuery jrquery = new JRDesignQuery();
                jrquery.setText(query);
                JasperDesign jasperDesign = JRXmlLoader.load(input);
                jasperDesign.setQuery(jrquery);

                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, con);

                JasperViewer viewer = new JasperViewer(jasperPrint, false);
                viewer.setVisible(true);

            } catch (Exception e) {
                System.out.println("Error Loading Jasper Report");
                System.out.println(e);

            } finally {
                con.close();
            }

        } else if (txtSearch.getText().isEmpty()) {

            try {

                con = DBConnection.getInstence().getConnection();
                String query = "select * from dealer";

                //Alert
                Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                al.setTitle("Information Dialog");
                al.setHeaderText(null);
                al.setContentText("Generating Report...Please wait!!");

                al.showAndWait();

                BasicConfigurator.configure();
                String path = "C:\\Users\\A C E R\\Documents\\NetBeansProjects\\RegistrationForm\\RegistrationForm\\src\\dealerview\\DealerNew.jrxml";
                InputStream input = new FileInputStream(new File(path));

                JRDesignQuery jrquery = new JRDesignQuery();
                jrquery.setText(query);
                JasperDesign jasperDesign = JRXmlLoader.load(input);
                jasperDesign.setQuery(jrquery);

                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, con);

                JasperViewer viewer = new JasperViewer(jasperPrint, false);
                viewer.setVisible(true);

            } catch (Exception e) {
                System.out.println("Error Loading Jasper Report");
                System.out.println(e);
            } finally {
                con.close();
            }

        } else if (m1.find() && m1.group().equals(txtSearch.getText())) {

            System.out.println("No type!");
        } else {

            try {

                con = DBConnection.getInstence().getConnection();
                String type = txtSearch.getText();

                String query = "select * from dealer where prod_type ='" + type + "'";

                //Alert
                Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                al.setTitle("Information Dialog");
                al.setHeaderText(null);
                al.setContentText("Generating Report...Please wait!!");

                al.showAndWait();

                BasicConfigurator.configure();
                String path = "C:\\Users\\A C E R\\Documents\\NetBeansProjects\\RegistrationForm\\RegistrationForm\\src\\dealerview\\DealerNew.jrxml";
                InputStream input = new FileInputStream(new File(path));

                JRDesignQuery jrquery = new JRDesignQuery();
                jrquery.setText(query);
                JasperDesign jasperDesign = JRXmlLoader.load(input);
                jasperDesign.setQuery(jrquery);

                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, con);

                JasperViewer viewer = new JasperViewer(jasperPrint, false);
                viewer.setVisible(true);

            } catch (Exception e) {
                System.out.println("Error Loading Jasper Report");
                System.out.println(e);
            } finally {
                con.close();
            }

        }

    }*/

    //Clear the fields 
    public void clearFields() {

        lblID.setText(null);
        txtCmp_name.setText(null);
        txtAddress.setText(null);
        txtCmp_Email.setText(null);
        txtProduct.setText(null);
        txt_Ph_no.setText(null);
        txt_Agt_Name.setText(null);
        txt_Agt_Ph_No.setText(null);
        txt_Date.setText(null);
    }

    //Refresh the table 
    private void refreshTable() {

        oblist.clear();

        try {
            con = DBConnection.getDBConnection();

            String query = "select * from dealer";

            pst = con.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {

                oblist.add(new ModelTable(rs.getString("dealerId"), rs.getString("cmp_name"), rs.getString("cmp_address"), rs.getString("cmp_email"), rs.getString("prod_type"), rs.getString("cmp_phno"), rs.getString("agt_name"), rs.getString("agt_phno"), rs.getString("date")));

                table.setItems(oblist);
            }

            pst.close();
            rs.close();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    //get Cell value to textFields
    private void getValueToText() {

        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                ModelTable m1 = table.getItems().get(table.getSelectionModel().getSelectedIndex());

                lblID.setText(m1.getDealerId());
                txtCmp_name.setText(m1.getCmp_name());
                txtAddress.setText(m1.getCmp_address());
                txtCmp_Email.setText(m1.getCmp_email());
                txtProduct.setText(m1.getProd_type());
                txt_Ph_no.setText(m1.getCmp_phno());
                txt_Agt_Name.setText(m1.getAgt_name());
                txt_Agt_Ph_No.setText(m1.getAgt_phno());
                txt_Date.setText(m1.getDate());

            }

        });

    }
}
