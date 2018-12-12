/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.createInvoiceController.TITLE;
import Services.PaymentService;
import Services.PaymentServiceInterface;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.PaymentModel;
import model.PaymentModelforTable;

/**
 * FXML Controller class
 *
 * @author Shalika Ashan
 */
public class ViewInvoicesController implements Initializable {

    @FXML
    private JFXTreeTableView<PaymentModelforTable> paymentTable;
    @FXML
    private TreeTableColumn<PaymentModelforTable, String> colfname;
    @FXML
    private TreeTableColumn colpayID;
    @FXML
    private TreeTableColumn<PaymentModelforTable, String> collname;
    @FXML
    private TreeTableColumn colappointmentID;
    @FXML
    private TreeTableColumn coldate;
    @FXML
    private TreeTableColumn coltime;
    @FXML
    private TreeTableColumn coltotal;
    @FXML
    private JFXTextField txtKey;
    @FXML
    private BarChart<String, Double> barChart;
    @FXML
    private Label invoices;
    @FXML
    private Label income;
    static double sun = 0, mon = 0, tue = 0, wen = 0, thu = 0, fri = 0, sat = 0, total = 0;
    static int no = 0;

    @FXML
    private Button btnDelete;

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
    private Button btnAddPaymentUI;

    @FXML
    private Button btnViewSummary;

//    @FXML
//    private JFXToggleButton inverse;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadViews();
        setNavigationbarToolTip();
        btnDelete.setTooltip(new Tooltip("Delete selected payment"));
        report.setTooltip(new Tooltip("Genarate Report"));
        btnViewSummary.setTooltip(new Tooltip("View Payments Summary"));
        btnAddPaymentUI.setTooltip(new Tooltip("Add new Payment"));
        
    }

    public void loadViews() {

        //add values to table
        PaymentServiceInterface paymentService = new PaymentService();
        ArrayList<PaymentModel> payments = paymentService.getPaymentInfo();
        ArrayList<PaymentModelforTable> paymentsforTable = new ArrayList<>();
        for (PaymentModel temp : payments) {
            PaymentModelforTable temp1 = new PaymentModelforTable(temp);
            paymentsforTable.add(temp1);
        }

        ObservableList<PaymentModelforTable> paymentlist = FXCollections.observableArrayList(paymentsforTable);
        colpayID.setCellValueFactory(new TreeItemPropertyValueFactory<PaymentModelforTable, String>("payid"));
        collname.setCellValueFactory(new TreeItemPropertyValueFactory<PaymentModelforTable, String>("lname"));
        colfname.setCellValueFactory(new TreeItemPropertyValueFactory<PaymentModelforTable, String>("fname"));
        colappointmentID.setCellValueFactory(new TreeItemPropertyValueFactory<PaymentModelforTable, String>("aid"));
        coldate.setCellValueFactory(new TreeItemPropertyValueFactory<PaymentModelforTable, String>("payDate"));
        coltime.setCellValueFactory(new TreeItemPropertyValueFactory<PaymentModelforTable, String>("payTime"));
        coltotal.setCellValueFactory(new TreeItemPropertyValueFactory<PaymentModelforTable, String>("total"));
//        collname.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<PaymentModelforTable, String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<PaymentModelforTable, String> param) {
//                return param.getValue().getValue().lname;
//            }
//        });

        TreeItem<PaymentModelforTable> root = new RecursiveTreeItem<>(paymentlist, RecursiveTreeObject::getChildren);
        paymentTable.setRoot(root);
        paymentTable.setShowRoot(false);

        colpayID.setStyle("-fx-alignment: CENTER;");
        collname.setStyle("-fx-alignment: CENTER;");
        colfname.setStyle("-fx-alignment: CENTER;");
        colappointmentID.setStyle("-fx-alignment: CENTER;");
        coldate.setStyle("-fx-alignment: CENTER;");
        coltime.setStyle("-fx-alignment: CENTER;");
        coltotal.setStyle("-fx-alignment: CENTER;");
        searchPayment();/*filter payments*/
        //Set bar chart
        try {
            getChartInfo();
        } catch (ParseException ex) {
            Logger.getLogger(ViewInvoicesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void searchPayment() {
        txtKey.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> ov, String s, String t1) {
                paymentTable.setPredicate(new Predicate<TreeItem<PaymentModelforTable>>() {
                    @Override
                    public boolean test(TreeItem<PaymentModelforTable> payment) {
                        String fname = payment.getValue().getFname().toLowerCase();
                        String lname = payment.getValue().getLname().toLowerCase();
                        String name = fname + " " + lname;
                        Boolean found = payment.getValue().getLname().toLowerCase().contains(t1.toLowerCase())
                                || payment.getValue().getFname().toLowerCase().contains(t1.toLowerCase())
                                || name.contains(t1.toLowerCase())
                                || payment.getValue().getPayTime().contains(t1)
                                || payment.getValue().getPayid().contains(t1)
                                || payment.getValue().getPayDate().contains(t1)
                                || payment.getValue().getTotal().contains(t1);
                        return found;
                    }
                });
            }
        });
    }

//ObservableList<PieChart.Data> pielist = FXCollections.observableArrayList();
    public void getChartInfo() throws ParseException {
        PaymentServiceInterface paymentService = new PaymentService();
        ArrayList<PaymentModel> payments = paymentService.getPaymentInfo();
        String sdate;
        Date date = new Date();
        int dayweek;
        Calendar calendar = Calendar.getInstance();
        int i = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        sun = 0;
        mon = 0;
        tue = 0;
        wen = 0;
        thu = 0;
        fri = 0;
        sat = 0;
        total = 0;
        for (PaymentModel payment : payments) {
            sdate = payment.getPayDate();
            date = dateFormat.parse(sdate);
            calendar.setTime(date);
            dayweek = calendar.get(Calendar.DAY_OF_WEEK);
            switch (dayweek) {
                case 1:
                    sun += payment.getTotal();
                    break;
                case 2:
                    mon += payment.getTotal();
                    break;
                case 3:
                    tue += payment.getTotal();
                    break;
                case 4:
                    wen += payment.getTotal();
                    break;
                case 5:
                    thu += payment.getTotal();
                    break;
                case 6:
                    fri += payment.getTotal();
                    break;
                case 7:
                    sat += payment.getTotal();
                    break;
            }
        }
        no =0;
        no =payments.size();
        total = sun + mon + tue + wen + thu + fri + sat;
        invoices.setText("No.of Invoice: " + no);
        income.setText("Total Income: " + total);
        //load barchart
        try {
            XYChart.Series<String, Double> sereis = new XYChart.Series<>();
            sereis.getData().add(new XYChart.Data<>("Sunday", sun));
            sereis.getData().add(new XYChart.Data<>("Monday", mon));
            sereis.getData().add(new XYChart.Data<>("Tuesday", tue));
            sereis.getData().add(new XYChart.Data<>("Wednesday", wen));
            sereis.getData().add(new XYChart.Data<>("Thursday", thu));
            sereis.getData().add(new XYChart.Data<>("Friday", fri));
            sereis.getData().add(new XYChart.Data<>("Saturday", sat));
            barChart.getData().clear();
            barChart.getData().add(sereis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private Button report;

    @FXML
    public void showPopUp(ActionEvent event) throws IOException {

        AnchorPane root = FXMLLoader.<AnchorPane>load(getClass().getResource("/view/ReportGenaratePopUp.fxml"));
        Stage stage = new Stage();
//            stage.setAlwaysOnTop(true);
        Scene scene = new Scene(root);
        //        stage.resizableProperty().setValue(Boolean.FALSE);//disable maximize btn
        //stage.initStyle(StageStyle.UTILITY);//disable mini,max,e
        stage.initStyle(StageStyle.UNDECORATED);//hide all button
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(report.getScene().getWindow());
        stage.showAndWait();
    }

    @FXML
    public void deletePayment(ActionEvent event) {
        if (paymentTable.getSelectionModel().getSelectedItem() != null) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Confirmation");
            dialog.setHeaderText("Do you realy want to delete this payment?");
            dialog.setContentText("Please enter CONFIRM to delete this result:");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                if (result.get().equals("CONFIRM")) {
                    PaymentServiceInterface paymentService = new PaymentService();
                    int res = paymentService.deletePaymentByID(Integer.parseInt(paymentTable.getSelectionModel().getSelectedItem().getValue().getPayid()));
                    if (res > 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Successfully Deleted");
                        alert.setHeaderText("");
                        alert.setContentText("Selected result is deleted");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("");
                        alert.setContentText("Cannot Deleted");
                        alert.showAndWait();
                    }
                }
                loadViews();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("");
            alert.setContentText("Please Select a ROW form table view");
            alert.showAndWait();

        }
//        pielist.clear();
    }

    @FXML
    public void viewAddPaymentUI(ActionEvent event) {
           
        try {
             AnchorPane root = FXMLLoader.<AnchorPane>load(getClass().getResource("/view/createInvoice.fxml"));
                        Stage stage = new Stage();
            //    stage.setAlwaysOnTop(true);
            Scene scene = new Scene(root);
            //        stage.resizableProperty().setValue(Boolean.FALSE);//disable maximize btn
            //stage.initStyle(StageStyle.UTILITY);//disable mini,max,e
            //stage.initStyle(StageStyle.UNDECORATED);//hide all button
            stage.setScene(scene);
            stage.setTitle("Kushi Bridal and Beauty Salon");
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(ViewInvoicesController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }

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
            Logger.getLogger(ViewInvoicesController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ViewInvoicesController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void setNavigationbarToolTip(){
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
    public void viewSummary(ActionEvent event) throws IOException {
                AnchorPane root = FXMLLoader.<AnchorPane>load(getClass().getResource("/view/popUPSummary.fxml"));
        Stage stage = new Stage();
//            stage.setAlwaysOnTop(true);
        Scene scene = new Scene(root);
        //        stage.resizableProperty().setValue(Boolean.FALSE);//disable maximize btn
        stage.initStyle(StageStyle.UTILITY);//disable mini,max,e
        //stage.initStyle(StageStyle.UNDECORATED);//hide all button
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(report.getScene().getWindow());
        stage.showAndWait();
    }
}
