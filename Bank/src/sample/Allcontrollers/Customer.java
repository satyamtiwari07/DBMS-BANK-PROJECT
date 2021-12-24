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
import sample.AllClasses.customerclass;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Customer implements Initializable {

    Stage dialogStage = new Stage();
    Scene scene;

    @FXML // fx:id="accountbtn"
    private Button accountbtn; // Value injected by FXMLLoader

    @FXML // fx:id="cidcolumn"
    private TableColumn<customerclass, Integer> cidcolumn; // Value injected by FXMLLoader

    @FXML // fx:id="banktable"
    private TableView<customerclass> banktable; // Value injected by FXMLLoader

    @FXML // fx:id="branchbtn"
    private Button branchbtn; // Value injected by FXMLLoader

    @FXML // fx:id="cidentry"
    private TextField cidentry; // Value injected by FXMLLoader

    @FXML // fx:id="cityentry"
    private TextField cityentry; // Value injected by FXMLLoader

    @FXML // fx:id="citycolumn"
    private TableColumn<customerclass, String> citycolumn; // Value injected by FXMLLoader

    @FXML // fx:id="dobcolumn"
    private TableColumn<customerclass, String> dobcolumn; // Value injected by FXMLLoader

    @FXML // fx:id="firstcolumn"
    private TableColumn<customerclass, String> firstcolumn; // Value injected by FXMLLoader

    @FXML // fx:id="deletedtn"
    private Button deletedtn; // Value injected by FXMLLoader

    @FXML // fx:id="dob"
    private TextField dob; // Value injected by FXMLLoader

    @FXML // fx:id="exitbtn"
    private Button exitbtn; // Value injected by FXMLLoader

    @FXML // fx:id="firstentry"
    private TextField firstentry; // Value injected by FXMLLoader

    @FXML // fx:id="insertbtn"
    private Button insertbtn; // Value injected by FXMLLoader

    @FXML // fx:id="lastentry"
    private TextField lastentry; // Value injected by FXMLLoader

    @FXML // fx:id="loanbtn"
    private Button loanbtn; // Value injected by FXMLLoader

    @FXML // fx:id="mobilentery"
    private TextField mobilentery; // Value injected by FXMLLoader

    @FXML // fx:id="lastcolumn"
    private TableColumn<customerclass, String> lastcolumn; // Value injected by FXMLLoader

    @FXML // fx:id="occupationentry"
    private TextField occupationentry; // Value injected by FXMLLoader

    @FXML // fx:id="mobilecolumn"
    private TableColumn<customerclass, Long> mobilecolumn; // Value injected by FXMLLoader

    @FXML // fx:id="occupationcolumn"
    private TableColumn<customerclass, String> occupationcolumn; // Value injected by FXMLLoader

    @FXML // fx:id="transactionbtn"
    private Button transactionbtn; // Value injected by FXMLLoader

    @FXML // fx:id="updatebtn"
    private Button updatebtn; // Value injected by FXMLLoader

    @FXML
    void selectentry(MouseEvent event) {
        customerclass items =banktable.getSelectionModel().getSelectedItem();
        cidentry.setText(items.getCustomer_id().toString());
        firstentry.setText(items.getFname());
        lastentry.setText(items.getLname());
        cityentry.setText(items.getCity());
        mobilentery.setText(items.getMobileno().toString());
        occupationentry.setText(items.getOccupation());
        dob.setText(items.getDob());
    }

    @FXML
    void accountbtn(ActionEvent event) throws IOException {
        if(event.getSource()==accountbtn) {
            Node node = (Node) event.getSource();
            dialogStage = (Stage) node.getScene().getWindow();
            scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Allcontrollers/AllFxmls/account_test.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        }
    }

    @FXML
    void branchbutton(ActionEvent event) throws IOException {
        if(event.getSource()==branchbtn) {
            Node node = (Node) event.getSource();
            dialogStage = (Stage) node.getScene().getWindow();
            scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Allcontrollers/AllFxmls/branch.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        }

    }

    @FXML
    void deletebutton(ActionEvent event) {

    }

    @FXML
    void exitbutton(ActionEvent event) {
        if(event.getSource()==exitbtn) Platform.exit();
    }

    @FXML
    void insertbutton(ActionEvent event) {
        if(event.getSource()==insertbtn){
            Connection c = getConnection();
            String sql = "insert into customer values  (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt;
            try {
                pstmt = c.prepareStatement(sql);
                pstmt.setInt(1, Integer.parseInt(cidentry.getText()));
                pstmt.setString(2, firstentry.getText());
                pstmt.setString(3, lastentry.getText());
                pstmt.setString(4, cityentry.getText());
                pstmt.setString(5, occupationentry.getText());
                pstmt.setDate(6, Date.valueOf(dob.getText()));
                pstmt.setLong(7, Integer.parseInt(mobilentery.getText()));
                pstmt.execute();
                showcustomerrecord();

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
        if(event.getSource()==transactionbtn){
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
        showcustomerrecord();
    }

    private void showcustomerrecord() {
        ObservableList<customerclass> list= getcustomerrecord();

        cidcolumn.setCellValueFactory(new PropertyValueFactory<>("customer_id"));

        firstcolumn.setCellValueFactory(new PropertyValueFactory<>("fname"));

        lastcolumn.setCellValueFactory(new PropertyValueFactory<>("lname"));

        citycolumn.setCellValueFactory(new PropertyValueFactory<>("city"));

        occupationcolumn.setCellValueFactory(new PropertyValueFactory<>("occupation"));

        dobcolumn.setCellValueFactory(new PropertyValueFactory<>("dob"));

        mobilecolumn.setCellValueFactory(new PropertyValueFactory<>("mobileno"));

        banktable.setItems(list);

    }
    private ObservableList<customerclass> getcustomerrecord() {
        ObservableList<customerclass> recordlist= FXCollections.observableArrayList();
        Connection c=getConnection();
        String sql = "select * from bank_144.customer";
        Statement st ;
        ResultSet rs ;
        try{
            st=c.createStatement();
            rs=st.executeQuery(sql);
            customerclass tr;
            while(rs.next()){
                tr= new customerclass(rs.getInt("customer_id"),
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("city"),
                        rs.getString("occupation"),
                        rs.getDate("dob").toString(),
                        rs.getLong("mobileno")
                );

                recordlist.add(tr);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        return recordlist;
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
            System.out.println("error in connection part in testing customer class");
            return null;
        }
    }

    public static void printing(String infoMessage, String headerText, String title){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

}
