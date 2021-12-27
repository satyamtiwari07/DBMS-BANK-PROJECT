package sample.Allcontrollers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.AllClasses.*;


import java.io.IOException;
import java.sql.*;

public class query {
    Stage dialogStage = new Stage();
    Scene scene;

    @FXML // fx:id="Q1"
    private Button Q1; // Value injected by FXMLLoader

    @FXML // fx:id="Q2"
    private Button Q2; // Value injected by FXMLLoader

    @FXML // fx:id="Q3"
    private Button Q3; // Value injected by FXMLLoader

    @FXML // fx:id="Q4"
    private Button Q4; // Value injected by FXMLLoader

    @FXML // fx:id="Q5"
    private Button Q5; // Value injected by FXMLLoader

    @FXML // fx:id="Query1table"
    private TableView<queryone> Query1table; // Value injected by FXMLLoader

    @FXML // fx:id="Query2table"
    private TableView<querytwo> Query2table; // Value injected by FXMLLoader

    @FXML // fx:id="Query3table"
    private TableView<querythree> Query3table; // Value injected by FXMLLoader

    @FXML // fx:id="Query4table"
    private TableView<queryfour> Query4table; // Value injected by FXMLLoader

    @FXML // fx:id="Query5table"
    private TableView<queryfive> Query5table; // Value injected by FXMLLoader

    @FXML // fx:id="accnocol3"
    private TableColumn<querythree, Integer> accnocol3; // Value injected by FXMLLoader

    @FXML // fx:id="accountnocol2"
    private TableColumn<querytwo, Integer> accountnocol2; // Value injected by FXMLLoader

    @FXML // fx:id="amountcol5"
    private TableColumn<queryfive, Integer> amountcol5; // Value injected by FXMLLoader

    @FXML // fx:id="branchbutton"
    private Button branchbutton; // Value injected by FXMLLoader

    @FXML // fx:id="branchcitycol4"
    private TableColumn<queryfour, String> branchcitycol4; // Value injected by FXMLLoader

    @FXML // fx:id="branchidcol5"
    private TableColumn<queryfive, String> branchidcol5; // Value injected by FXMLLoader

    @FXML // fx:id="cidcol2"
    private TableColumn<querytwo, Integer> cidcol2; // Value injected by FXMLLoader

    @FXML // fx:id="cidcol5"
    private TableColumn<queryfive, Integer> cidcol5; // Value injected by FXMLLoader

    @FXML // fx:id="citycol3"
    private TableColumn<querythree, String> citycol3; // Value injected by FXMLLoader

    @FXML // fx:id="countcol4"
    private TableColumn<queryfour, Integer> countcol4; // Value injected by FXMLLoader

    @FXML // fx:id="customerbutton"
    private Button customerbutton; // Value injected by FXMLLoader

    @FXML // fx:id="customercolumn1"
    private TableColumn<queryone, Integer> customercolumn1; // Value injected by FXMLLoader

    @FXML // fx:id="dobcol1"
    private TableColumn<queryone, String> dobcol1; // Value injected by FXMLLoader

    @FXML // fx:id="exitbutton"
    private Button exitbutton; // Value injected by FXMLLoader

    @FXML // fx:id="fnamecol2"
    private TableColumn<querytwo, String> fnamecol2; // Value injected by FXMLLoader

    @FXML // fx:id="fnamecol3"
    private TableColumn<querythree, String> fnamecol3; // Value injected by FXMLLoader

    @FXML // fx:id="fnamecol5"
    private TableColumn<queryfive, String> fnamecol5; // Value injected by FXMLLoader

    @FXML // fx:id="fnamecolumn1"
    private TableColumn<queryone, String> fnamecolumn1; // Value injected by FXMLLoader

    @FXML // fx:id="lnamecol1"
    private TableColumn<queryone, String> lnamecol1; // Value injected by FXMLLoader

    @FXML // fx:id="loanbutton"
    private Button loanbutton; // Value injected by FXMLLoader

    @FXML // fx:id="occupationcol3"
    private TableColumn<querythree, String> occupationcol3; // Value injected by FXMLLoader

    @FXML // fx:id="tranbutton"
    private Button tranbutton; // Value injected by FXMLLoader

    @FXML
    private Button Accountbutton;

    @FXML
    void branchbtn(ActionEvent event) throws IOException {
        if(event.getSource()==branchbutton) {
            Node node = (Node) event.getSource();
            dialogStage = (Stage) node.getScene().getWindow();
            scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Allcontrollers/AllFxmls/branch.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        }
    }

    @FXML
    void Accountbtn(ActionEvent event) throws IOException {
        if(event.getSource()==Accountbutton){
            Node node = (Node) event.getSource();
            dialogStage = (Stage) node.getScene().getWindow();
            scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Allcontrollers/AllFxmls/account_test.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        }
    }

    @FXML
    void customerbtn(ActionEvent event) throws IOException {
        if(event.getSource()==customerbutton){
            Node node = (Node) event.getSource();
            dialogStage = (Stage) node.getScene().getWindow();
            scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Allcontrollers/AllFxmls/customer.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        }
    }

    @FXML
    void exitbtn(ActionEvent event) {
        if(event.getSource()==exitbutton) {
            Platform.exit();
        }
    }

    @FXML
    void loanbtn(ActionEvent event) throws IOException {
        if(event.getSource()==loanbutton) {
            Node node = (Node) event.getSource();
            dialogStage = (Stage) node.getScene().getWindow();
            scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Allcontrollers/AllFxmls/loan_test.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        }
    }

    @FXML
    void tranbtn(ActionEvent event) throws IOException {
        if (event.getSource() == tranbutton) {
            Node node = (Node) event.getSource();
            dialogStage = (Stage) node.getScene().getWindow();
            scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Allcontrollers/AllFxmls/transaction_test.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        }
    }

    @FXML
    void query1(ActionEvent event) {
        if (event.getSource() == Q1) {
            ObservableList<queryone> list = getqueryonerecord();
            customercolumn1.setCellValueFactory(new PropertyValueFactory<>("customer_idcol1"));
            fnamecolumn1.setCellValueFactory(new PropertyValueFactory<>("fnamecol1"));
            lnamecol1.setCellValueFactory(new PropertyValueFactory<>("lnamecol1"));
            dobcol1.setCellValueFactory(new PropertyValueFactory<>("dobcol1"));
            Query1table.setItems(list);
        }
    }

    private ObservableList<queryone>getqueryonerecord(){
        Connection c=getConnection();
        ObservableList<queryone> recordlist= FXCollections.observableArrayList();
        try {
            CallableStatement stmt=c.prepareCall("{call QUERY1() }");
            boolean result=stmt.execute();
            if(result){
                ResultSet rs=stmt.getResultSet();
                queryone tr;
                while(rs.next()){
                    tr= new queryone(rs.getInt("CUSTOMER_ID"),rs.getString("fname"),rs.getString("lname"),
                            rs.getDate("dob").toString());
                    recordlist.add(tr);
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Error in store procedure");
        }
        return recordlist;
    }

    @FXML
    void query2(ActionEvent event) {
        if (event.getSource() == Q2) {
            ObservableList<querytwo> list = getquerytworecord();
            cidcol2.setCellValueFactory(new PropertyValueFactory<>("getCustomer_idcol2"));
            fnamecol2.setCellValueFactory(new PropertyValueFactory<>("fnamecol2"));
            accountnocol2.setCellValueFactory(new PropertyValueFactory<>("accountcol2"));
            Query2table.setItems(list);
        }

    }

    private ObservableList<querytwo> getquerytworecord() {
        Connection c=getConnection();
        ObservableList<querytwo> recordlist= FXCollections.observableArrayList();
        try {
            CallableStatement stmt=c.prepareCall("{call QUERY2() }");
            boolean result=stmt.execute();
            if(result){
                ResultSet rs=stmt.getResultSet();
                querytwo tr;
                while(rs.next()){
                    tr= new querytwo(rs.getInt("CUSTOMER_ID"),rs.getString("fname"),rs.getInt("account_number"));
                    recordlist.add(tr);
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Error in store procedure");
        }
        return recordlist;
    }

    @FXML
    void query3(ActionEvent event) {
        if (event.getSource() == Q3) {
            ObservableList<querythree> list = getquerythreerecord();
            fnamecol3.setCellValueFactory(new PropertyValueFactory<>("fnamecol3"));
            citycol3.setCellValueFactory(new PropertyValueFactory<>("citycol3"));
            accnocol3.setCellValueFactory(new PropertyValueFactory<>("account_nocol3"));
            occupationcol3.setCellValueFactory(new PropertyValueFactory<>("occupationcol3"));
            Query3table.setItems(list);
        }

    }

    private ObservableList<querythree> getquerythreerecord() {
        Connection c=getConnection();
        ObservableList<querythree> recordlist= FXCollections.observableArrayList();
        try {
            CallableStatement stmt=c.prepareCall("{call QUERY3() }");
            boolean result=stmt.execute();
            if(result){
                ResultSet rs=stmt.getResultSet();
                querythree tr;
                while(rs.next()){
                    tr= new querythree(rs.getString("fname"),rs.getString("city"),rs.getInt("account_number")
                            ,rs.getString("occupation"));
                    recordlist.add(tr);
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Error in store procedure");
        }
        return recordlist;
    }

    @FXML
    void query4(ActionEvent event) {
        if(event.getSource()==Q4){
            ObservableList<queryfour> list = getqueryfourrecord();
            branchcitycol4.setCellValueFactory(new PropertyValueFactory<>("branchcitycol4"));
            countcol4.setCellValueFactory(new PropertyValueFactory<>("countbranchcol4"));
            Query4table.setItems(list);
        }

    }

    private ObservableList<queryfour> getqueryfourrecord() {
        Connection c=getConnection();
        ObservableList<queryfour> recordlist= FXCollections.observableArrayList();
        try {
            CallableStatement stmt=c.prepareCall("{call QUERY4() }");
            boolean result=stmt.execute();
            if(result){
                ResultSet rs=stmt.getResultSet();
                queryfour tr;
                while(rs.next()){
                    tr= new queryfour(rs.getString("branch_city"),rs.getInt("Count_Branch"));
                    recordlist.add(tr);
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Error in store procedure");
        }
        return recordlist;
    }

    @FXML
    void query5(ActionEvent event) {
        if (event.getSource() == Q5) {
            ObservableList<queryfive> list = getqueryfiverecord();
            cidcol5.setCellValueFactory(new PropertyValueFactory<>("customer_idcol5"));
            fnamecol5.setCellValueFactory(new PropertyValueFactory<>("fnamecol5"));
            branchidcol5.setCellValueFactory(new PropertyValueFactory<>("branch_idcol5"));
            amountcol5.setCellValueFactory(new PropertyValueFactory<>("amountcol5"));
            Query5table.setItems(list);
        }

    }

    private ObservableList<queryfive> getqueryfiverecord() {
        Connection c=getConnection();
        ObservableList<queryfive> recordlist= FXCollections.observableArrayList();
        try {
            CallableStatement stmt=c.prepareCall("{call QUERY5() }");
            boolean result=stmt.execute();
            if(result){
                ResultSet rs=stmt.getResultSet();
                queryfive tr;
                while(rs.next()){
                    tr= new queryfive(rs.getInt("customer_id"),rs.getString("fname"),rs.getInt("branch_id"),rs.getInt("amount"));
                    recordlist.add(tr);
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Error in store procedure");
        }
        return recordlist;
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
            System.out.println("error in connection part in testing loginpage class");
            return null;
        }
    }

}
