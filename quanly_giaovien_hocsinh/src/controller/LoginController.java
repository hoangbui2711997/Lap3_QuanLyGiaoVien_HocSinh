package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Layout;
import model.data.GiaoVien;
import model.database.SearchDB;
import model.repository.RepositoryGiaoVien;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    void actionLogin(ActionEvent event) throws IOException, SQLException {
//        System.out.println(RepositoryGiaoVien
//                .getAll()
//                .stream().anyMatch(
//                        e -> e.getCMND()
//                                .equals(txtCMND.getText()))
//        );

        Thread thread1 = new Thread(() -> {
            SearchDB searchDB = SearchDB.getQueryDB();
            ResultSet ketQua = null;

            try {
                ketQua = searchDB.searchCommand("SELECT * FROM giaovien as gv WHERE gv.CMND = '" + txtCMND.getText()
                        + "' and matkhau='" + txtPassword.getText()+ "'");


                ketQua.next();

                GiaoVien gv = searchDB.getGV(ketQua);

            Platform.runLater(() -> {
                if(gv != null) {
                    try {
                        MainApp.makeForm(Layout.LAYOUT_MAIN, gv);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        thread1.start();



//        if(RepositoryGiaoVien
//                .getAll()
//                .stream().anyMatch(
//                        e -> e.getCMND()
//                        .equals(txtCMND.getText()))
//                ) {
//
//            MainApp.makeForm(Layout.LAYOUT_MAIN,
//                    RepositoryGiaoVien.getAll()
//                    .stream()
//                    .filter(e -> e.getCMND().equals(txtCMND.getText()))
//                    .findFirst()
//                    .get());
//        }
    }
}
