package controller.Share;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Composition_ implements Initializable {

    @FXML
    public JFXButton btnRootNodeList;

    @FXML
    public JFXButton btnUpdatetNodeList;

    @FXML
    public JFXButton btnAddNodeList;

    @FXML
    public JFXButton btnDelNodeList;

    @FXML
    void click(ActionEvent event) {
        Platform.runLater(() -> {
            System.out.println(btnUpdatetNodeList.getStyle());
            System.out.println(btnAddNodeList.getStyle());
            System.out.println(btnRootNodeList.getStyle());
            System.out.println(btnDelNodeList.getStyle());
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
