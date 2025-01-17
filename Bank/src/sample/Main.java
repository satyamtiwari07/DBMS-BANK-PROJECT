package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//cd C:\Program Files\MySQL\MySQL Server 8.0\bin
// mysql -u bank_144 -p -h 117.236.190.213
//c= DriverManager.getConnection("jdbc:mysql://117.236.190.213/bank_144","bank_144","bank_144");

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Allcontrollers/AllFxmls/loginpage_test.fxml"));
        primaryStage.setTitle("Bank Database Management");
        primaryStage.setScene(new Scene(root, 600, 455));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
