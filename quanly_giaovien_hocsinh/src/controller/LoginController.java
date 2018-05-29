package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import controller.Share.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Layout;
import model.repository.RepositoryGiaoVien;

import java.io.IOException;

public class LoginController {

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtCMND;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private Label lbltk;

    @FXML
    private JFXButton btnExit;

    @FXML
    void actionExit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void actionLogin(ActionEvent event) throws IOException {
        System.out.println(RepositoryGiaoVien
                .getAll()
                .stream().anyMatch(
                        e -> e.getCMND()
                                .equals(txtCMND.getText()))
        );

        if(RepositoryGiaoVien
                .getAll()
                .stream().anyMatch(
                        e -> e.getCMND()
                        .equals(txtCMND.getText()))
                ) {

            MainApp.makeForm(Layout.LAYOUT_MAIN);
        }
    }
}
