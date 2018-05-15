package controller;

import com.jfoenix.controls.*;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.Share.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import model.data.GiaoVien;
import model.database.InsertDB;
import model.repository.RepositoryGiaoVien;

public class GiaoVienController {

    @FXML
    public JFXComboBox jfxComboboxSex;

    @FXML
    public ResourceBundle resources;

    @FXML
    public URL location;

    @FXML
    public JFXButton btnSubmit;

    @FXML
    public JFXButton btnCancel;

    @FXML
    public JFXTextField MaGV;

    @FXML
    public JFXTextField gioiTinh;

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
    public AnchorPane rootPane;

    public GiaoVien oldGiaoVien;

    /**
     * Báo xem action sẽ là action nào
     */
    public static String action;

    @FXML
    void btnCancelAction(ActionEvent event) throws SQLException {

    }

    void handleAdd(ActionEvent event) throws SQLException {

        GiaoVien giaoVien = getGiaoVienFromForm();
        // Thêm giáo viên
        RepositoryGiaoVien.add(giaoVien);
        new Alert(Alert.AlertType.INFORMATION, "Thêm thành công", ButtonType.OK).showAndWait();

    }

    private GiaoVien getGiaoVienFromForm() {
        return GiaoVien.getInstance(Integer.parseInt(MaGV.getText()),
                    jfxComboboxSex.getValue().toString(),
                    hoTen.getText(),
                    diaChi.getText(),
                    ngaySinh.getValue().toString(),
                    dienThoai.getText(),
                    CMND.getText(),
                    matKhau.getText(),
                    Integer.parseInt(jfxComboboxRole.getValue().toString())
            );
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
        jfxComboboxRole.getSelectionModel().selectLast();

        List list1 = new ArrayList();
        list1.add("Nam");
        list1.add("Nữ");
        jfxComboboxSex.getItems().addAll(list1);
        jfxComboboxSex.getSelectionModel().selectFirst();

        MaGV.setText(InsertDB.getInstance().initInsert("GiaoVien") + "");
        ngaySinh.setValue(LocalDate.now());

        btnCancel.setOnAction(e -> {
            MainController.secondaryStage.close();
        });
    }

    /**
     * Kiểm tra xem nhập có hợp lệ không
     *
     * @return
     */
    private boolean checkIsEmpty() {
        if ("".equals(jfxComboboxSex.getValue().toString()) ||
                "".equals(hoTen.getText()) || "".equals(diaChi.getText()) ||
                "".equals(dienThoai.getText()) || "".equals(CMND.getText()) ||
                "".equals(matKhau.getText()) || "".equals(ngaySinh.getValue().toString()) ||
                "".equals(jfxComboboxRole.getValue().toString())) {
            new Alert(Alert.AlertType.ERROR, "Chưa nhập đủ các trường", ButtonType.OK).showAndWait();
            return true;
        } else {
            return false;
        }
    }

    public void btnSubmitAction(ActionEvent actionEvent) throws SQLException {
        if (!checkIsEmpty()) {
            if (action.equals("Add")) {
                handleAdd(actionEvent);
            } else if(action.equals("Update")){
                handleUpdate(actionEvent);
            }
        }
        MainController.secondaryStage.close();
    }

    private void handleUpdate(ActionEvent actionEvent) throws SQLException {
        // Truyền giá trị update vào 1 đối tượng xong update
        GiaoVien giaoVien = getGiaoVienFromForm();

        // Sửa giáo viên
//        GiaoVien.Update.whereId(MaGV.getText() + "", giaoVien);
        RepositoryGiaoVien.edit(giaoVien);


        new Alert(Alert.AlertType.INFORMATION, "Sửa thành công", ButtonType.OK).showAndWait();
        MainController.secondaryStage.close();
    }


}
