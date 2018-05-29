/**
 * Sample Skeleton for 'Detail.fxml' Controller Class
 */

package controller;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.data.*;
import model.repository.*;

import static controller.Share.MainController.secondaryStage;
import static controller.Share.MainController.tertiary;

public class PhanCongController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    public ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    public URL location;

    @FXML // fx:id="lbGiaoVien"
    public Label lbGiaoVien; // Value injected by FXMLLoader

    @FXML // fx:id="lbLopHoc"
    public Label lbLopHoc; // Value injected by FXMLLoader

    @FXML // fx:id="lbKhoiHoc"
    public Label lbKhoiHoc; // Value injected by FXMLLoader

    @FXML // fx:id="lbMonHoc"
    public Label lbMonHoc; // Value injected by FXMLLoader

    @FXML // fx:id="btnDetailLopHoc"
    public JFXButton btnDetailLopHoc; // Value injected by FXMLLoader

    @FXML // fx:id="lbHocKy"
    public Label lbHocKy; // Value injected by FXMLLoader

    @FXML // fx:id="lbNamHoc"
    public Label lbNamHoc; // Value injected by FXMLLoader

    @FXML // fx:id="lbThoiGianBD"
    public Label lbThoiGianBD; // Value injected by FXMLLoader

    @FXML // fx:id="lbThoiGianKT"
    public Label lbThoiGianKT; // Value injected by FXMLLoader

    @FXML // fx:id="btnCancel"
    public JFXButton btnCancel; // Value injected by FXMLLoader

    @FXML // fx:id="lbMaPhanCong"
    public Label lbMaPhanCong; // Value injected by FXMLLoader

    @FXML // fx:id="sceneRoot"
    public AnchorPane sceneRoot; // Value injected by FXMLLoader

    public PhanCong phanCong;

    @FXML
    void actionBtnCancel(MouseEvent event) {
        secondaryStage.close();
    }

    private boolean isHocSinhInXepLop(HocSinh hocSinh, List<XepLop> lstXepLop) {
        for (XepLop xepLop : lstXepLop) {
            if(xepLop.getMaHS() == hocSinh.getMaHS())
                return true;
        }
        return false;
    }

    @FXML
    void actionBtnDetail(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LopHoc/Detail.fxml"));
        Parent root = loader.load();

        // Load controller of view
        LopHocController lopHocController = loader.getController();

        int maLopHoc = phanCong.getMaLH();
        LopHoc lopHoc = RepositoryLopHoc
                .getAll()
                .stream()
                .filter(e -> e.getMaLH() == phanCong.getMaLH())
                .findFirst()
                .get();

        String tenLop = lopHoc.getTenLH();

        List<XepLop> lstXepLop = RepositoryXepLop
                .getAll()
                .stream()
                .filter(e -> e.getMaLH() == maLopHoc)
                .collect(Collectors.toList());

        List<HocSinh> lstHocSinh = RepositoryHocSinh
                .getAll().stream()
                .filter(e -> isHocSinhInXepLop(e, lstXepLop))
                .collect(Collectors.toList());

        lopHocController.lstHocSinh = lstHocSinh;
        lopHocController.lbSiSo.setText(lopHocController.lbSiSo.getText() + lstHocSinh.size());
        lopHocController.lbClassName.setText(lopHocController.lbClassName.getText() + tenLop);

        if (root != null) {
            tertiary = new Stage();
            tertiary.setScene(new Scene(root, 680, 500));
            tertiary.setResizable(false);
            tertiary.showAndWait();
        }
    }

    public void init() {
        if (phanCong != null) {
            GiaoVien giaoVien =
                    RepositoryGiaoVien
                            .getAll()
                            .stream()
                            .filter(e -> e.getMaGV() == phanCong.getMaGV())
                            .findFirst()
                            .get();

            LopHoc lopHoc =
                    RepositoryLopHoc
                            .getAll()
                            .stream()
                            .filter(e -> e.getMaLH() == phanCong.getMaLH())
                            .findFirst()
                            .get();

            KhoiHoc kh =
                    RepositoryKhoiHoc
                            .getAll()
                            .stream()
                            .filter(e -> e.getMaKH() == lopHoc.getMaKH())
                            .findFirst()
                            .get();

            MonHoc mh =
                    RepositoryMonHoc
                            .getAll()
                            .stream()
                            .filter(e -> e.getMaMH() == phanCong.getMaMH())
                            .findFirst()
                            .get();

            HocKy hk =
                    RepositoryHocKy
                            .getAll()
                            .stream()
                            .filter(e -> e.getMaHK() == phanCong.getMaHK())
                            .findFirst()
                            .get();

            NamHoc namHoc =
                    RepositoryNamHoc
                            .getAll()
                            .stream()
                            .filter(e -> e.getMaNH() == lopHoc.getMaNH())
                            .findFirst()
                            .get();

            lbMaPhanCong.setText(lbMaPhanCong.getText() + phanCong.getMaPC());
            lbGiaoVien.setText(lbGiaoVien.getText() + giaoVien.getHoTen());
            lbLopHoc.setText(lbLopHoc.getText() + lopHoc.getTenLH());
            lbKhoiHoc.setText(lbKhoiHoc.getText() + kh.getTenKH());
            lbMonHoc.setText(lbMonHoc.getText() + mh.getTenMH());
            lbHocKy.setText(lbHocKy.getText() + hk.getTenHK());
            lbThoiGianBD.setText(hk.getNgayBatDau() + "/" + hk.getThangBatDau());
            lbThoiGianKT.setText(hk.getNgayKetThuc() + "/" + hk.getThangKetThuc());
            lbNamHoc.setText(namHoc.getNambatdau() + " -" + namHoc.getNamketthuc());
        }
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert lbGiaoVien != null : "fx:id=\"lbGiaoVien\" was not injected: check your FXML file 'Detail.fxml'.";
        assert lbLopHoc != null : "fx:id=\"lbLopHoc\" was not injected: check your FXML file 'Detail.fxml'.";
        assert lbKhoiHoc != null : "fx:id=\"lbKhoiHoc\" was not injected: check your FXML file 'Detail.fxml'.";
        assert lbMonHoc != null : "fx:id=\"lbMonHoc\" was not injected: check your FXML file 'Detail.fxml'.";
        assert btnDetailLopHoc != null : "fx:id=\"btnDetailLopHoc\" was not injected: check your FXML file 'Detail.fxml'.";
        assert lbHocKy != null : "fx:id=\"lbHocKy\" was not injected: check your FXML file 'Detail.fxml'.";
        assert lbNamHoc != null : "fx:id=\"lbNamHoc\" was not injected: check your FXML file 'Detail.fxml'.";
        assert lbThoiGianBD != null : "fx:id=\"lbThoiGianBD\" was not injected: check your FXML file 'Detail.fxml'.";
        assert lbThoiGianKT != null : "fx:id=\"lbThoiGianKT\" was not injected: check your FXML file 'Detail.fxml'.";
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'Detail.fxml'.";
        assert lbMaPhanCong != null : "fx:id=\"lbMaPhanCong\" was not injected: check your FXML file 'Detail.fxml'.";
    }
}
