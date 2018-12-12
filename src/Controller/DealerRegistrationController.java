/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import model.ModelTable;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import static java.lang.Boolean.TRUE;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import Utils.DBConnection;

public class DealerRegistrationController implements Initializable {

    
    @FXML
    private TextField txtcmpname;
    @FXML
    private TextField txxtaddress;
    @FXML
    private TextField txtemail;
    @FXML
    private TextField txtphno;
    @FXML
    private TextField txtname;
    @FXML
    private TextField txtphno2;
    @FXML
    private DatePicker datePick;
    @FXML
    private Button exit;
    @FXML
    private Button load;
    @FXML
    private Button submit;
    @FXML
    private Button demo;
    @FXML
    private CheckBox chkSkin;
    @FXML
    private CheckBox chkHair;
    @FXML
    private CheckBox chkEyes;
    @FXML
    private CheckBox chkFace;
    @FXML
    private CheckBox chkNails;
    @FXML
    private Label lblName;
    @FXML
    private Label lblAddress;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblProd;
    @FXML
    private Label lblPhone1;
    @FXML
    private Label lblAgtName;
    @FXML
    private Label lblPhone2;
    @FXML
    private Label lblID;

    ObservableList<String> chkList = FXCollections.observableArrayList();

    Connection con;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @FXML
    private boolean validationEmpty() {

        if (lblID.getText().isEmpty() | txtcmpname.getText().isEmpty() | txxtaddress.getText().isEmpty() | txtemail.getText().isEmpty()  | (!(chkEyes.isSelected() | chkFace.isSelected() | chkHair.isSelected() | chkNails.isSelected() | chkSkin.isSelected())) | txtphno.getText().isEmpty() | txtname.getText().isEmpty() | txtphno2.getText().isEmpty()) {

            if (txtcmpname.getText().isEmpty()) {

                lblName.setText("*Please Enter a Company Name");
            }
            if (txxtaddress.getText().isEmpty()) {

                lblAddress.setText("*Please Enter a Address");
            }
            if (txtemail.getText().isEmpty()) {

                lblEmail.setText("*Please Enter a Phone Email");
            }
            if ((!(chkEyes.isSelected() | chkFace.isSelected() | chkHair.isSelected() | chkNails.isSelected() | chkSkin.isSelected()))) {

                lblProd.setText("*Please Select A Product");
            }
            if (txtphno.getText().isEmpty()) {

                lblPhone1.setText("*Please Enter a Company Phone Number");
            }
            if (txtname.getText().isEmpty()) {

                lblAgtName.setText("*Please Enter a Agent Name");
            }
            if (txtphno2.getText().isEmpty()) {

                lblPhone2.setText("*Please Enter a Agent Phone Number");
            }

            return false;

        }

        //validate datepick
        if (datePick.getEditor().getText().isEmpty()) {
            Alert alert3 = new Alert(Alert.AlertType.WARNING);
            alert3.setTitle("Warning Dialog");
            alert3.setHeaderText(null);
            alert3.setContentText("Please enter the date");

            alert3.showAndWait();

            return false;
        }

        return true;

    }

    @FXML
    private boolean validateCheckBox() {

        chkEyes.requestFocus();
        chkFace.requestFocus();
        chkHair.requestFocus();
        chkNails.requestFocus();
        chkSkin.requestFocus();

        if (!(chkEyes.isSelected() | chkFace.isSelected() | chkHair.isSelected() | chkNails.isSelected() | chkSkin.isSelected())) {

            System.out.println("not selected");
            Alert alert3 = new Alert(Alert.AlertType.WARNING);
            alert3.setTitle("Warning Dialog");
            alert3.setHeaderText(null);
            alert3.setContentText("Please select a product!!");

            alert3.showAndWait();

            return false;

        } else {

            return true;

        }

    }

    @FXML
    void clickAppointment(ActionEvent event) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource(" "));
            Parent root1 = (Parent) fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Appointment");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void clickCustomer(ActionEvent event) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/"));
            Parent root1 = (Parent) fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Customer");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void clickDealer(ActionEvent event) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource(" "));
            Parent root1 = (Parent) fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void clickEmployee(ActionEvent event) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/EmployeeManagement.fxml"));
            Parent root1 = (Parent) fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Employee details");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void clickFinance(ActionEvent event) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource(""));
            Parent root1 = (Parent) fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Finance");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void clickStock(ActionEvent event) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/stockMain.fxml"));
            Parent root1 = (Parent) fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Kushi Saloon");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void clickPackages(ActionEvent event) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource(""));
            Parent root1 = (Parent) fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Packages");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void clickPayment(ActionEvent event) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/"));
            Parent root1 = (Parent) fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Payment");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // clear the warning after the txtbox is filled
    @FXML
    private void labelNull() {

        if (!txtcmpname.getText().isEmpty()) {

            lblName.setText(null);
        }
        if (!txxtaddress.getText().isEmpty()) {

            lblAddress.setText(null);
        }
        if (!txtemail.getText().isEmpty()) {

            lblEmail.setText(null);
        }
        if (chkEyes.isSelected() | chkFace.isSelected() | chkHair.isSelected() | chkNails.isSelected() | chkSkin.isSelected()) {

            lblProd.setText(null);
        }       
        if (!txtphno.getText().isEmpty()) {

            lblPhone1.setText(null);
        }
        if (!txtname.getText().isEmpty()) {

            lblAgtName.setText(null);
        }
        if (!txtphno2.getText().isEmpty()) {

            lblPhone2.setText(null);
        }

    }

    @FXML
    private boolean CmpNameValidation() {

        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(txtcmpname.getText());

        if (m.find() && m.group().equals(txtcmpname.getText())) {

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

    //Agent name Validation
    @FXML
    private boolean AgtNameValidation() {

        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(txtname.getText());

        if (m.find() && m.group().equals(txtname.getText())) {

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
        Matcher m = p.matcher(txtphno.getText());
        Matcher m1 = p.matcher(txtphno2.getText());

        if (m.find() && m.group().equals(txtphno.getText()) && m1.find() && m.group().equals(txtphno.getText())) {

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

    //Validate Phone Number
    @FXML
    private boolean validatePhNumber() {

        Pattern p = Pattern.compile("(011|071|072|075|076|077|078)?[0-9][0-9]{6}");
        Matcher m = p.matcher(txtphno.getText());
        Matcher m1 = p.matcher(txtphno2.getText());

        if (m.find() && m.group().equals(txtphno.getText()) && m1.find() && m.group().equals(txtphno.getText())) {

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
        Matcher m1 = p1.matcher(txtemail.getText());

        if (m1.find() && m1.group().equals(txtemail.getText())) {

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

    @FXML
    private void showAlert1() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Dealer is Registered.. Sending Email!!!");

        alert.showAndWait();

    }

    //alert is not sucess
    @FXML
    private void showAlert2() {

        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("Information Dialog");
        alert1.setHeaderText(null);
        alert1.setContentText("Dealer is Not Registered!!");

        alert1.showAndWait();

    }

    @FXML
    private void showAlert3() {

        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("Information Dialog");
        alert1.setHeaderText(null);
        alert1.setContentText("Email was sent!!!!");

        alert1.showAndWait();

    }

    //CheckBox Methods
    @FXML
    private void CheckBoxSkin(ActionEvent event) {
        //chkSkin = new CheckBox("Skin");
        chkList.add("Skin");
    }

    @FXML
    private void CheckBoxHair(ActionEvent event) {
        // chkHair = new CheckBox("Hair");
        chkList.add("Hair");
    }

    @FXML
    private void CheckBoxEyes(ActionEvent event) {
        //chkEyes = new CheckBox("Eyes");
        chkList.add("Eyes");
    }

    @FXML
    private void CheckBoxFace(ActionEvent event) {
        //chkFace = new CheckBox("Face");
        chkList.add("Face");
    }

    @FXML
    private void CheckBoxNails(ActionEvent event) {
        //chkNails = new CheckBox("Nails");
        chkList.add("Nails");
    }

    //tooltip Method
    @FXML
    public void tooltips() {

        final Tooltip date = new Tooltip();
        date.setText("Date");
        datePick.setTooltip(date);

        final Tooltip id = new Tooltip();
        id.setText("Dealer ID");
        lblID.setTooltip(id);

        final Tooltip cmpName = new Tooltip();
        cmpName.setText("Company Name");
        txtcmpname.setTooltip(cmpName);

        final Tooltip cmpAdd = new Tooltip();
        cmpAdd.setText("Company Address");
        txxtaddress.setTooltip(cmpAdd);

        final Tooltip email = new Tooltip();
        email.setText("Company Email");
        txtemail.setTooltip(email);

        final Tooltip cmpPhNo = new Tooltip();
        cmpPhNo.setText("Company Phone Number");
        txtphno.setTooltip(cmpPhNo);

        final Tooltip agtName = new Tooltip();
        agtName.setText("Agent Name");
        txtname.setTooltip(agtName);

        final Tooltip agtPhNo = new Tooltip();
        agtPhNo.setText("Agent Phone Number");
        txtphno2.setTooltip(agtPhNo);

        final Tooltip viewBtn = new Tooltip();
        viewBtn.setText("View Dealers");
        load.setTooltip(viewBtn);

        final Tooltip submitBtn = new Tooltip();
        submitBtn.setText("Submit the form");
        submit.setTooltip(submitBtn);

        final Tooltip exitBtn = new Tooltip();
        exitBtn.setText("Exit");
        exit.setTooltip(exitBtn);
        
        final Tooltip demoBtn = new Tooltip();
        demoBtn.setText("Demo Values");
        demo.setTooltip(demoBtn);

        final Tooltip prdType = new Tooltip();
        prdType.setText("Check one or more");
        chkSkin.setTooltip(prdType);
        chkHair.setTooltip(prdType);
        chkEyes.setTooltip(prdType);
        chkFace.setTooltip(prdType);
        chkNails.setTooltip(prdType);

    }

    //Submit Button action method
    @FXML
    private void submit_button_Action(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {

        con = DBConnection.getDBConnection();
        try {

            if (validationEmpty() && validateNumber() && EmailValidation() && CmpNameValidation() && validatePhNumber() && AgtNameValidation() && validateCheckBox()) {

                LocalDate localDate = datePick.getValue();
                localDate.toString();

                String id = lblID.getText();
                String cmp_name = txtcmpname.getText();
                String address = txxtaddress.getText();
                String email = txtemail.getText();
                String product = chkList.toString();
                String cmp_phno = txtphno.getText();
                String name = txtname.getText();
                String agt_phno = txtphno2.getText();

                Statement statement = con.createStatement();

                int query = statement.executeUpdate("INSERT INTO dealer (dealerID, cmp_name, cmp_address, cmp_email, prod_type, cmp_phno, agt_name, agt_phno, date)"
                        + "VALUES ('" + id + "','" + cmp_name + "','" + address + "','" + email + "','" + product + "','" + cmp_phno + "','" + name + "','" + agt_phno + "','" + localDate + "')");

                if (query > 0) {

                    showAlert1();
                    System.out.println("User Registered!");

                } else {

                    showAlert2();
                    System.out.println("User not Registered!");
                }
                //Edited by pasan in order to run the program. Should check later
                // sendEmail();
                showAlert3();
                clearFields();
                LastDealerId();
                datePick.setValue(LocalDate.now());

            }

        } catch (SQLException ex) {
            Logger.getLogger(DealerRegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.close();

        }

    }

    //load action button
    @FXML
    private void load_button_Action(ActionEvent event) {
        try {
            Parent root;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DealerView.fxml"));
            root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Dealer Details");
            stage.show();

        } catch (Exception e) {
            System.out.println(e);
            System.out.println();
            System.out.println("Can't Load the new Window!");
        }

    }

    //Exit button action
    @FXML
    private void exit_button_Action(ActionEvent event) {

        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

    //Demo values
    @FXML
    private void demo_button_Action(ActionEvent event) {

        txtcmpname.setText("Dreamron Lanka (Pvt) Ltd.");
        txxtaddress.setText("112, Sunethra Devi Road, Kohuwala, Nugegoda");
        txtemail.setText("dinukakulatunga@gmail.com");
        txtphno.setText("0112853804");
        txtname.setText("Mr. Samantha");
        txtphno2.setText("0714240755");

    }

    //calculate last Dealer Id
    @FXML
    public void LastDealerId() throws ClassNotFoundException {

        try {
            con = DBConnection.getDBConnection();

            String query = "select max(dealerID) as dealerID from dealer";

            pst = con.prepareStatement(query);

            try {
                rs = con.createStatement().executeQuery(query);
                System.out.println("Query executed!");

            } catch (Exception e) {
                System.out.println("Queary not executed!!");
            }

            while (rs.next()) {

                lblID.setText(String.valueOf(rs.getInt("dealerID") + 1));

            }

            con.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(DealerViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
//Edited by pasan in order to run the program. Should check later

    //send Email after registration
    /* @FXML
    private void sendEmail() {

        // Recipient's email ID needs to be mentioned.   
        String to = txtemail.getText();

        // Sender's email ID needs to be mentioned
        String from = "kushib96@gmail.com";
        final String username = "kushib96@gmail.com";//enter the email
        final String password = "kushi12345";//Password

        //Your mail host
        String host = "smtp.gmail.com";

        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "25");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Register Dealer");

            // Now set the actual message
            message.setText("You have been registered successfully!!!");

            // Send message
            Transport.send(message);

            System.out.println("Email sent successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }*/
    //clear Fileds
    public void clearFields() {

        chkSkin.setSelected(false);
        chkHair.setSelected(false);
        chkNails.setSelected(false);
        chkEyes.setSelected(false);
        chkFace.setSelected(false);
        chkList.clear();
        lblID.setText(null);
        txtcmpname.setText(null);
        txxtaddress.setText(null);
        txtemail.setText(null);
        txtphno.setText(null);
        txtname.setText(null);
        txtphno2.setText(null);
        // datePick.getEditor().clear();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            LastDealerId();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DealerRegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        datePick.setValue(LocalDate.now());
        tooltips();

    }

}
