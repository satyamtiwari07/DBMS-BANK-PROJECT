package sample.Allcontrollers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.AllClasses.loanclass;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Loan implements Initializable {
    Stage dialogStage = new Stage();
    Scene scene;

    @FXML
    private TableColumn<loanclass, Integer> loanamount;

    @FXML
    private TableColumn<loanclass, Integer> loanbranchid;

    @FXML
    private TableColumn<loanclass, Integer> loancustomerid;

    @FXML
    private Button loandelete;

    @FXML
    private TextField loanentryamount;

    @FXML
    private TextField loanentrybranchid;

    @FXML
    private TextField loanentrycustomerid;

    @FXML
    private Button loaninsert;

    @FXML
    private TableView<loanclass> loantable;

    @FXML
    private Button loanupdate;

    @FXML // fx:id="account_btn"
    private Button account_btn; // Value injected by FXMLLoader

    @FXML // fx:id="branch_btn"
    private Button branch_btn; // Value injected by FXMLLoader

    @FXML // fx:id="customer_btn"
    private Button customer_btn; // Value injected by FXMLLoader

    @FXML // fx:id="exit"
    private Button exit; // Value injected by FXMLLoader

    @FXML // fx:id="transection_btn"
    private Button transection_btn; // Value injected by FXMLLoader



    @FXML
    void account_button(ActionEvent event) throws IOException {
        if(event.getSource()==account_btn) {
            Node node = (Node) event.getSource();
            dialogStage = (Stage) node.getScene().getWindow();
            scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Allcontrollers/AllFxmls/account_test.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        }
    }

    @FXML
    void branch_button(ActionEvent event) throws IOException {
        if(event.getSource()==branch_btn) {
            Node node = (Node) event.getSource();
            dialogStage = (Stage) node.getScene().getWindow();
            scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Allcontrollers/AllFxmls/branch.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        }

    }

    @FXML
    void customer_button(ActionEvent event) throws IOException {
        if(event.getSource()==customer_btn){
            Node node = (Node) event.getSource();
            dialogStage = (Stage) node.getScene().getWindow();
            scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Allcontrollers/AllFxmls/customer.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        }

    }

    @FXML
    void exit_btn(ActionEvent event) {
        if(event.getSource()==exit)
             Platform.exit();
    }

    @FXML
    void transection_button(ActionEvent event) throws IOException {
        if (event.getSource() == transection_btn) {
            Node node = (Node) event.getSource();
            dialogStage = (Stage) node.getScene().getWindow();
            scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Allcontrollers/AllFxmls/transaction_test.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        }
    }

    @FXML
    void deleterecord(ActionEvent event) throws SQLException {
        if(event.getSource()==loandelete){
            deletingrecord();
        }
    }
    @FXML
    void handlemouseclick(MouseEvent event) {
        loanclass items =loantable.getSelectionModel().getSelectedItem();
        loanentryamount.setText(String.valueOf(items.getAmount()));
        loanentrybranchid.setText(String.valueOf(items.getBranch_id()));
        loanentrycustomerid.setText(String.valueOf(items.getCustomer_id()));
    }

    @FXML
    void insertrecord(ActionEvent event) {
        if(event.getSource()==loaninsert){
            insertingrecord();
        }
    }

    @FXML
    void updaterecord(ActionEvent event) throws SQLException {
        if(event.getSource()==loanupdate){
            updatingrecord();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showloanrecord();
    }

    public Connection getConnection(){
        Connection c;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            c= DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_144","root","Satyam@07");
            System.out.println("Connected to the database successfully " + c.getCatalog());
            return c;
        }
        catch(Exception e){
            System.out.println("error in connection part in testing load class");
            return null;
        }
    }

    private ObservableList<loanclass> gettestingrecord() {
        ObservableList<loanclass> recordlist= FXCollections.observableArrayList();
        Connection c=getConnection();
        String sql = "select * from bank_144.loan";
        Statement st ;
        ResultSet rs ;
        try{
            st=c.createStatement();
            rs=st.executeQuery(sql);
            loanclass loans;
            while(rs.next()){
                loans=new loanclass(rs.getInt("customer_id"),rs.getInt("branch_id"),rs.getInt("amount"));
                recordlist.add(loans);
            }
        }
        catch(Exception e){
            System.out.println("Error in observable list statement sql");
        }

        return recordlist;
    }

    public void showloanrecord() {
        ObservableList<loanclass> list= gettestingrecord();
        loancustomerid.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        loanbranchid.setCellValueFactory(new PropertyValueFactory<>("branch_id"));
        loanamount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        loantable.setItems(list);

    }

    private void insertingrecord(){
        Connection c=getConnection();
        String sql= "insert into loan values  (?, ?, ?)";
        PreparedStatement pstmt ;
        try {
            pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(loanentrycustomerid.getText()));
            pstmt.setInt(2, Integer.parseInt(loanentrybranchid.getText()));
            pstmt.setInt(3, Integer.parseInt(loanentryamount.getText()));
            pstmt.execute();
           showloanrecord();
        } catch (SQLException throwables) {
            printing(throwables.getMessage(),"Error in insert","Error");
        }
    }

    private void updatingrecord() throws SQLException {

        Connection c= getConnection();
        String sql="Update loan set amount=? where customer_id=? and branch_id=?";
        PreparedStatement stmt=c.prepareStatement(sql);
        stmt.setInt(1, Integer.parseInt(loanentryamount.getText()));
        stmt.setInt(2, Integer.parseInt(loanentrycustomerid.getText()));
        stmt.setInt(3, Integer.parseInt(loanentrybranchid.getText()));
        int check =stmt.executeUpdate();
//        System.out.println(check);
        if(check>0) showloanrecord();
        else printing("Not Updated ","update","Error");

    }

    private void deletingrecord() throws SQLException {

        Connection c= getConnection();
        //"delete from table name where name=__";
        String sql="delete from loan where  customer_id=? and branch_id =? and amount =? ";
        PreparedStatement stmt = c.prepareStatement(sql);
        stmt.setInt(1, Integer.parseInt(loanentrycustomerid.getText()));
        stmt.setInt(2, Integer.parseInt(loanentrybranchid.getText()));
        stmt.setInt(3, Integer.parseInt(loanentryamount.getText()));
        int i=stmt.executeUpdate();
        if(i>0) showloanrecord();
        else printing("Record not Found","Not Delete","Delete");

    }

    public static void printing(String infoMessage, String headerText, String title){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
}
