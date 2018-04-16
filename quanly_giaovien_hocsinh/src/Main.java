import com.jfoenix.controls.JFXDrawer;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Main extends Application implements Initializable {

    @Override
    public void start(Stage primaryStage) throws IOException {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("view/Login.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("view/Share/Composition.fxml"));

            //        leftPane =

//        topPane =
//        topPane = FXMLLoader.load(getClass().getResource("view/Share/TopPane.fxml"));

//        homePane =
//        homePane = FXMLLoader.load(getClass().getResource("view/Share/HomePane.fxml"));

//        bottomPane =
//        bottomPane = FXMLLoader.load(getClass().getResource("view/Share/BottomPane.fxml"));

//        Scene scene = new Scene(root, 800, 600);


            primaryStage.setScene(new Scene(root, 1000, 600));
            //primaryStage.setResizable(true);
            primaryStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    void initialize() throws IOException {

//        assert contentPane != null : "fx:id=\"contentPane\" was not injected: check your FXML file 'Main.fxml'.";
//        assert homePane != null : "fx:id=\"homePane\" was not injected: check your FXML file 'Main.fxml'.";
//        assert bottomPane != null : "fx:id=\"bottomPane\" was not injected: check your FXML file 'Main.fxml'.";
//        assert leftPane != null : "fx:id=\"leftPane\" was not injected: check your FXML file 'Main.fxml'.";
//        assert topPane != null : "fx:id=\"topPane\" was not injected: check your FXML file 'Main.fxml'.";


    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
