package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginController {
    @FXML
    private JFXTextField txttk;

    @FXML
    private JFXTextField txtmk;

    @FXML
    private JFXButton btndn;

    @FXML
    private Label lbltk;

    public LoginController (){

    }
    @FXML void Login(ActionEvent event){
        if(txtmk.getText().equals("Username") && txtmk.getText().equals("123456") ){
            lbltk.setText("Đăng Nhập Thành Công");
        }else {
            lbltk.setText("Tài Khoản Hoặc Mật Khâu Không Đúng");
        }
    }
}