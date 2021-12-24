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
import sample.AllClasses.branchclass;
import sample.AllClasses.tranctionclass;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Branch implements Initializable {
    Stage dialogStage = new Stage();
    Scene scene;

    @FXML
    private Button accountbtn;

    @FXML
    private TextField bidentry;

    @FXML
    private TableColumn<branchclass, String> branchcitycolumn;

    @FXML
    private TextField branchcityentry;

    @FXML
    private TextField branchnameentry;

    @FXML
    private TableColumn<branchclass, Integer> branchidcolumn;

    @FXML
    private TableColumn<branchclass, String> branchnamecolumn;

    @FXML
    private TableView<branchclass> branchtable;

    @FXML
    private Button customerbtn;

    @FXML
    private Button deletebtn;

    @FXML
    private Button exit;

    @FXML
    private Button insertbtn;

    @FXML
    private Button loanbtn;

    @FXML
    private Button tranasctionbtn;

    @FXML
    private Button updatebtn;

    @FXML
    void selectentry(MouseEvent event) {
        branchclass items =branchtable.getSelectionModel().getSelectedItem();
        bidentry.setText(items.getBranch_id().toString());
        branchnameentry.setText(items.getBranch_name());
        branchcityentry.setText(items.getBranch_city());
    }

    @FXML
    void accountbutton(ActionEvent event) throws IOException {
        if(event.getSource()==accountbtn){
            Node node = (Node) event.getSource();
            dialogStage = (Stage) node.getScene().getWindow();
            scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Allcontrollers/AllFxmls/account_test.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        }

    }

    @FXML
    void customerbutton(ActionEvent event) throws IOException {
        if(event.getSource()==customerbtn){
            Node node = (Node) event.getSource();
            dialogStage = (Stage) node.getScene().getWindow();
            scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Allcontrollers/AllFxmls/customer.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        }
    }

    @FXML
    void deletebutton(ActionEvent event) {

    }

    @FXML
    void exitbutton(ActionEvent event) {
        if(event.getSource()==exit)
           Platform.exit();
    }

    @FXML
    void insertbutton(ActionEvent event) {
        if(event.getSource()==insertbtn){
            Connection c = getConnection();
            String sql = "insert into branch values  (?, ?, ?)";
            PreparedStatement pstmt ;
            try {
                pstmt = c.prepareStatement(sql);
                pstmt.setInt(1, Integer.parseInt(bidentry.getText()));
                pstmt.setString(2, branchnameentry.getText());
                pstmt.setString(3, branchcityentry.getText());
                pstmt.execute();
                showbranchrecord();

            } catch (SQLException throwables) {
                printing(throwables.getMessage(), "Error in insert", "Error");
            }
        }
    }

    @FXML
    void loanbutton(ActionEvent event) throws IOException {
        if(event.getSource()==loanbtn){
            Node node = (Node) event.getSource();
            dialogStage = (Stage) node.getScene().getWindow();
            scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Allcontrollers/AllFxmls/loan_test.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        }
    }

    @FXML
    void transactionbutton(ActionEvent event) throws IOException {
        if(event.getSource()==tranasctionbtn){
            Node node = (Node) event.getSource();
            dialogStage = (Stage) node.getScene().getWindow();
            scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Allcontrollers/AllFxmls/transaction_test.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        }

    }

    @FXML
    void updatebutton(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showbranchrecord();
    }
    public Connection getConnection(){
        Connection c;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            c= DriverManager.getConnection("jdbc:mysql://117.236.190.213/bank_144","bank_144","bank_144");
            System.out.println("Connected to the database successfully " + c.getCatalog());
            return c;
        }
        catch(Exception e){
            System.out.println("error in connection part in testing loginpage class");
            return null;
        }
    }

    private ObservableList<branchclass> getbranchrecord() {
        ObservableList<branchclass> recordlist= FXCollections.observableArrayList();
        Connection c=getConnection();
        String sql = "select * from bank_144.branch";
        Statement st ;
        ResultSet rs ;
        try{
            st=c.createStatement();
            rs=st.executeQuery(sql);
            branchclass tr;
            while(rs.next()){
                tr= new branchclass(rs.getInt("branch_id"),
                        rs.getString("branch_name"),
                        rs.getString("branch_city")
                );
                recordlist.add(tr);
            }
        }
        catch(Exception e){
            System.out.println("Error in observable list statement sql");
        }

        return recordlist;
    }

    private void showbranchrecord() {
        ObservableList<branchclass> list=getbranchrecord();

        branchidcolumn.setCellValueFactory(new PropertyValueFactory<>("branch_id"));

        branchnamecolumn.setCellValueFactory(new PropertyValueFactory<>("branch_name"));

        branchcitycolumn.setCellValueFactory(new PropertyValueFactory<>("branch_city"));


        branchtable.setItems(list);

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
