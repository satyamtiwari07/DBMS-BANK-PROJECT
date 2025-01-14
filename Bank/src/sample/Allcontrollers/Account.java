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
import sample.AllClasses.accountclass;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Account implements Initializable {
    Stage dialogStage = new Stage();
    Scene scene;

    @FXML
    private TableColumn<accountclass, Integer> accnocolumn;

    @FXML
    private TableColumn<accountclass,String> accountstatuscolumn;

    @FXML
    private TableView<accountclass> accounttable;

    @FXML
    private Button branch_btn;

    @FXML
    private TableColumn<accountclass,Integer > branchcolumn;

    @FXML
    private Button customer_btn;

    @FXML
    private TableColumn<accountclass,Integer > customercolumn;

    @FXML
    private TableColumn<accountclass, String> datecolumn;

    @FXML
    private Button deletebutton;

    @FXML
    private TextField enteramount;

    @FXML
    private TextField enterdate;

    @FXML
    private TextField enterstatus;

    @FXML
    private TextField entertype;

    @FXML
    private TextField entryaccno;

    @FXML
    private TextField entrybranchno;

    @FXML
    private TextField entrycusno;

    @FXML
    private Button exit;

    @FXML
    private Button insertbutton;

    @FXML
    private Button loan_btn;

    @FXML
    private TableColumn<accountclass, Integer> openingcolumn;

    @FXML
    private Button transection_btn;

    @FXML
    private TableColumn<accountclass, String> typecolumn;

    @FXML
    private Button updatebutton;

    @FXML
    void selectentry(MouseEvent event) {
        accountclass items =accounttable.getSelectionModel().getSelectedItem();
        entryaccno.setText(items.getAccount_number().toString());
        entrycusno.setText(items.getCustomer_id().toString());
        entrybranchno.setText(items.getBranch_id().toString());
        enteramount.setText(items.getOpening_amount().toString());
        enterdate.setText(items.getOpening_date());
        enterstatus.setText(items.getAccount_status());
        entertype.setText(items.getAccount_type());
    }

    @FXML
    void exit_btn(ActionEvent event) {
        if(event.getSource()==exit)
        Platform.exit();
    }

    @FXML
    void loan_button(ActionEvent event) throws IOException {
        if (event.getSource() == loan_btn) {
            Node node = (Node) event.getSource();
            dialogStage = (Stage) node.getScene().getWindow();
            scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Allcontrollers/AllFxmls/loan_test.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        }
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
    private void insetbtn(ActionEvent event)  {
        if (event.getSource() == insertbutton) {
            Connection c = getConnection();
            String sql = "insert into account values(?,?,?,?,?,?,?)";
            PreparedStatement stmt;
            try {
                stmt = c.prepareStatement(sql);
                stmt.setInt(1, Integer.parseInt(entryaccno.getText()));
                stmt.setInt(2, Integer.parseInt(entrycusno.getText()));
                stmt.setInt(3, Integer.parseInt(entrybranchno.getText()));
                stmt.setInt(4, Integer.parseInt(enteramount.getText()));
                stmt.setDate(5, Date.valueOf(enterdate.getText()));
                stmt.setString(6, entertype.getText());
                stmt.setString(7, enterstatus.getText());
                stmt.execute();
                showaccountrecord();
            } catch (SQLException throwables) {
                printing(throwables.getMessage(),"Error in insert","Error");
            }

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
    private void deletebtn(ActionEvent event) throws SQLException {
        if(event.getSource()==deletebutton){
            Connection c= getConnection();
            //"delete from table name where name=__";
            String sql="delete from account where account_number=? and customer_id=? " +
                    "and  branch_id=? and opening_amount=? and opening_date=? and account_type=? and account_status=?";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(entryaccno.getText()));
            stmt.setInt(2, Integer.parseInt(entrycusno.getText()));
            stmt.setInt(3, Integer.parseInt(entrybranchno.getText()));
            stmt.setInt(4, Integer.parseInt(enteramount.getText()));
            stmt.setDate(5, Date.valueOf(enterdate.getText()));
            stmt.setString(6,entertype.getText());
            stmt.setString(7,enterstatus.getText());
            int i=stmt.executeUpdate();
            if(i>0) showaccountrecord();
            else printing("Record not Found","Not Delete","Delete");
        }

    }

    @FXML
    private void updatebtn(ActionEvent event) throws SQLException {
        if(event.getSource()==updatebutton){

            Connection c= getConnection();
            String sql="Update account set customer_id=? , " +
                    "branch_id=?,opening_amount=?" +
                    ",opening_date=?,account_type=? ,account_status=?" +
                    "where  account_number=?";

            PreparedStatement stmt=c.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(entrycusno.getText()));
            stmt.setInt(2, Integer.parseInt(entrybranchno.getText()));
            stmt.setInt(3, Integer.parseInt(enteramount.getText()));
            stmt.setDate(4, Date.valueOf(enterdate.getText()));
            stmt.setString(5,entertype.getText());
            stmt.setString(6,enterstatus.getText());
            stmt.setInt(7, Integer.parseInt(entryaccno.getText()));
            int check =stmt.executeUpdate();
//            System.out.println(check);
            if(check>0) showaccountrecord();
            else printing("Not Updated ","update","Error");

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showaccountrecord();
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

    private ObservableList<accountclass> getaccountrecord() {
        ObservableList<accountclass> recordlist= FXCollections.observableArrayList();
        Connection c=getConnection();
        String sql = "select * from bank_144.account";
        Statement st ;
        ResultSet rs ;
        try{
            st=c.createStatement();
            rs=st.executeQuery(sql);
            accountclass tr;
            while(rs.next()){
                tr= new accountclass(
                        rs.getInt("account_number"),
                        rs.getInt("customer_id"),
                        rs.getInt("branch_id"),
                        rs.getInt("opening_amount"),
                        rs.getDate("opening_date").toString(),
                        rs.getString("account_type"),
                        rs.getString("account_status")
                );

                recordlist.add(tr);
            }
        }
        catch(Exception e){
            System.out.println("Error in observable list statement sql");
        }
        return recordlist;
    }

    private void showaccountrecord() {
        ObservableList<accountclass> list= getaccountrecord();

        accnocolumn.setCellValueFactory(new PropertyValueFactory<>("account_number"));

        customercolumn.setCellValueFactory(new PropertyValueFactory<>("customer_id"));

        branchcolumn.setCellValueFactory(new PropertyValueFactory<>("branch_id"));

        openingcolumn.setCellValueFactory(new PropertyValueFactory<>("opening_amount"));

        datecolumn.setCellValueFactory(new PropertyValueFactory<>("opening_date"));

        typecolumn.setCellValueFactory(new PropertyValueFactory<>("account_type"));

        accountstatuscolumn.setCellValueFactory(new PropertyValueFactory<>("account_status"));

        accounttable.setItems(list);
    }
    //printing msg in database
    public static void printing(String infoMessage, String headerText, String title){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

}
