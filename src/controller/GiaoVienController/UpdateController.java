package controller.GiaoVienController;

import com.jfoenix.controls.*;
import controller.Share.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import model.data.GiaoVien;
import model.database.InsertDB;

import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateController {
    public static List<GiaoVien> giaoViens;

    @FXML
    public JFXComboBox jfxComboboxSex;

    public static List<GiaoVien> getGiaoViens() {
        return giaoViens;
    }

    static {
        try {
            giaoViens = GiaoVien.Search.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton btnSubmit;

    @FXML
    private JFXButton btnCancel;

    @FXML
    public JFXTextField MaGV;

    @FXML
    private JFXTextField gioiTinh;

    @FXML
    public JFXTextField diaChi;

    @FXML
    public JFXTextField dienThoai;

    @FXML
    public JFXTextField CMND;

    @FXML
    public JFXTextField hoTen;

    @FXML
    public JFXComboBox jfxComboboxRole;

    @FXML
    public JFXDatePicker ngaySinh;

    @FXML
    public JFXPasswordField matKhau;

    @FXML
    private AnchorPane rootPane;

    public GiaoVien oldGiaoVien;

    @FXML
    void btnCancelAction(ActionEvent event) throws SQLException {
        MainController.secondaryStage.close();
    }

    @FXML
    void btnSubmitAction(ActionEvent event) throws SQLException {
//        if ("".equals(jfxComboboxSex.getValue().toString()) ||
//                "".equals(hoTen.getText()) || "".equals(diaChi.getText()) ||
//                "".equals(dienThoai.getText()) || "".equals(CMND.getText()) ||
//                "".equals(matKhau.getText()) || "".equals(ngaySinh.getValue().toString()) ||
//                "".equals(jfxComboboxRole.getValue().toString())) {
//            new Alert(Alert.AlertType.ERROR, "Chưa nhập đủ các trường", ButtonType.OK).showAndWait();
//        } else {
            GiaoVien giaoVien = GiaoVien.getInstance(Integer.parseInt(MaGV.getText()),
                    jfxComboboxSex.getValue() == null ? oldGiaoVien.getGioiTinh() : jfxComboboxSex.getValue().toString(),
                    hoTen.getText(),
                    diaChi.getText(),
                    ngaySinh.getValue().toString(),
                    dienThoai.getText(),
                    CMND.getText(),
                    matKhau.getText(),
                    jfxComboboxRole.getValue() == null ? oldGiaoVien.getRole() : Integer.parseInt(jfxComboboxRole.getValue().toString()));

            // Sửa giáo viên
            GiaoVien.Update.whereId(MaGV.getText() + "", giaoVien);


            new Alert(Alert.AlertType.INFORMATION, "Sửa thành công", ButtonType.OK).showAndWait();
            MainController.secondaryStage.close();
//        }
    }

    @FXML
    void initialize() throws SQLException {
        assert btnSubmit != null : "fx:id=\"btnSubmit\" was not injected: check your FXML file 'Add.fxml'.";
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'Add.fxml'.";
        assert MaGV != null : "fx:id=\"MaGV\" was not injected: check your FXML file 'Add.fxml'.";
        assert gioiTinh != null : "fx:id=\"gioiTinh\" was not injected: check your FXML file 'Add.fxml'.";
        assert diaChi != null : "fx:id=\"diaChi\" was not injected: check your FXML file 'Add.fxml'.";
        assert dienThoai != null : "fx:id=\"dienThoai\" was not injected: check your FXML file 'Add.fxml'.";
        assert CMND != null : "fx:id=\"CMND\" was not injected: check your FXML file 'Add.fxml'.";
        assert ngaySinh != null : "fx:id=\"ngaySinh\" was not injected: check your FXML file 'Add.fxml'.";
        assert matKhau != null : "fx:id=\"matKhau\" was not injected: check your FXML file 'Add.fxml'.";

        List list = new ArrayList();
        list.add("1"); // 1 la muc cao nhat
        list.add("2");
        jfxComboboxRole.getItems().addAll(list);

        List list1 = new ArrayList();
        list1.add("Nam");
        list1.add("Nu");
        jfxComboboxSex.getItems().addAll(list1);
    }
}
