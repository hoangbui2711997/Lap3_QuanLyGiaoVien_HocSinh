package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSpinner;
import controller.Share.MainController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.ThongKe;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ThongKeController {

    @FXML
    private JFXComboBox jfxcbbSLKhongDat;

    @FXML
    private JFXComboBox jfxcbbSLTrungBinh;

    @FXML
    private JFXComboBox jfxcbbSLKha;

    @FXML
    private JFXComboBox jfxcbbSLGioi;

    @FXML
    private Label lbKhongDat;

    @FXML
    private Label lbTrungBinh;

    @FXML
    private Label lbKha;

    @FXML
    private Label lbGioi;

    @FXML
    private JFXComboBox jfxcbbSLNhapHocNam;

    @FXML
    private JFXComboBox jfxcbbSLTotNghiepNam;

    @FXML
    private JFXComboBox jfxcbbSLGVBatDauDayNam;

    @FXML
    private JFXComboBox jfxcbbSLGVNghiDayNam;

    @FXML
    private Label lbSLNhapHocNam;

    @FXML
    private Label lbSoLuongTotNghiepNam;

    @FXML
    private Label lbGiaoVienBatDauGiangDayNam;

    @FXML
    private Label lbGiaoVienNghiDayNam;

    @FXML
    private JFXButton btnClose;

    @FXML
    private JFXSpinner spKhongDat;

    @FXML
    private JFXSpinner spTrungBinh;

    @FXML
    private JFXSpinner spKha;

    @FXML
    private JFXSpinner spGioi;

    @FXML
    private JFXSpinner spHSNhapHoc;

    @FXML
    private JFXSpinner spHSTotNghiep;

    @FXML
    private JFXSpinner spGVGDBatDau;

    @FXML
    private JFXSpinner spGVGDKetThuc;

    @FXML
    void initialize() {
        assert jfxcbbSLKhongDat != null : "fx:id=\"jfxcbbSLKhongDat\" was not injected: check your FXML file 'ThongKe.fxml'.";
        assert jfxcbbSLTrungBinh != null : "fx:id=\"jfxcbbSLTrungBinh\" was not injected: check your FXML file 'ThongKe.fxml'.";
        assert jfxcbbSLKha != null : "fx:id=\"jfxcbbSLKha\" was not injected: check your FXML file 'ThongKe.fxml'.";
        assert jfxcbbSLGioi != null : "fx:id=\"jfxcbbSLGioi\" was not injected: check your FXML file 'ThongKe.fxml'.";
        assert lbKhongDat != null : "fx:id=\"lbKhongDat\" was not injected: check your FXML file 'ThongKe.fxml'.";
        assert lbTrungBinh != null : "fx:id=\"lbTrungBinh\" was not injected: check your FXML file 'ThongKe.fxml'.";
        assert lbKha != null : "fx:id=\"lbKha\" was not injected: check your FXML file 'ThongKe.fxml'.";
        assert lbGioi != null : "fx:id=\"lbGioi\" was not injected: check your FXML file 'ThongKe.fxml'.";
        assert jfxcbbSLNhapHocNam != null : "fx:id=\"jfxcbbSLNhapHocNam\" was not injected: check your FXML file 'ThongKe.fxml'.";
        assert jfxcbbSLTotNghiepNam != null : "fx:id=\"jfxcbbSLTotNghiepNam\" was not injected: check your FXML file 'ThongKe.fxml'.";
        assert jfxcbbSLGVBatDauDayNam != null : "fx:id=\"jfxcbbSLGVBatDauDayNam\" was not injected: check your FXML file 'ThongKe.fxml'.";
        assert jfxcbbSLGVNghiDayNam != null : "fx:id=\"jfxcbbSLGVNghiDayNam\" was not injected: check your FXML file 'ThongKe.fxml'.";
        assert lbSLNhapHocNam != null : "fx:id=\"lbSLNhapHocNam\" was not injected: check your FXML file 'ThongKe.fxml'.";
        assert lbSoLuongTotNghiepNam != null : "fx:id=\"lbSoLuongTotNghiepNam\" was not injected: check your FXML file 'ThongKe.fxml'.";
        assert lbGiaoVienBatDauGiangDayNam != null : "fx:id=\"lbGiiaoVienBatDauGiangDayNam\" was not injected: check your FXML file 'ThongKe.fxml'.";
        assert lbGiaoVienNghiDayNam != null : "fx:id=\"lbGiaoVienNghiDayNam\" was not injected: check your FXML file 'ThongKe.fxml'.";
        assert btnClose != null : "fx:id=\"btnClose\" was not injected: check your FXML file 'ThongKe.fxml'.";

        btnClose.setOnAction(e -> MainController.secondaryStage.close());
        List<String> stringList = new ArrayList<>();
        for (int j = LocalDate.now().getYear(); j >= 1970; j--) {
            stringList.add(j + "");
        }

        //setup value off list cbb
        setupListCBB(stringList);
        // -------------------------

        jfxcbbSLKhongDat.setOnAction(e -> {
            String year = (String) jfxcbbSLKhongDat.getValue();
            int iYear = Integer.parseInt(year);
            lbKhongDat.setVisible(false);
            spKhongDat.setVisible(true);
            Thread thread1 = new Thread(() -> {
                try {
                    int soLuongHocSinhChuaDatNam = ThongKe.laySoLuongHocSinhChuaDatNam(iYear);
                    int soLuongHocSinh = ThongKe.laySoLuongHocNam(iYear);
                    String strChuaDat = soLuongHocSinhChuaDatNam + "/" + soLuongHocSinh + " học sinh";

                    Platform.runLater(() -> {
                        lbKhongDat.setText(strChuaDat);
                        lbKhongDat.setVisible(true);
                        spKhongDat.setVisible(false);
                    });
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            });
            thread1.start();
        });

        jfxcbbSLKha.setOnAction(e -> {
            String year = (String) jfxcbbSLKha.getValue();
            int iYear = Integer.parseInt(year);
            lbKha.setVisible(false);
            spKha.setVisible(true);
            Thread thread1 = new Thread(() -> {
                try {
                    int laySoLuongHocKhaDatNam = ThongKe.laySoLuongHocKhaDatNam(iYear);
                    int soLuongHocSinh = ThongKe.laySoLuongHocNam(iYear);
                    String strKha = laySoLuongHocKhaDatNam + "/" + soLuongHocSinh + " học sinh";

                    Platform.runLater(() -> {
                        lbKha.setText(strKha);
                        lbKha.setVisible(true);
                        spKha.setVisible(false);
                    });
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            });
            thread1.start();
        });

        jfxcbbSLTrungBinh.setOnAction(e -> {
            String year = (String) jfxcbbSLTrungBinh.getValue();
            int iYear = Integer.parseInt(year);
            lbTrungBinh.setVisible(false);
            spTrungBinh.setVisible(true);
            Thread thread1 = new Thread(() -> {
                try {
                    int laySoLuongHocTrungBinhNam = ThongKe.laySoLuongHocTrungBinhNam(iYear);
                    int soLuongHocSinh = ThongKe.laySoLuongHocNam(iYear);
                    String strTrungBinh = laySoLuongHocTrungBinhNam + "/" + soLuongHocSinh + " học sinh";

                    Platform.runLater(() -> {
                        lbTrungBinh.setText(strTrungBinh);
                        lbTrungBinh.setVisible(true);
                        spTrungBinh.setVisible(false);
                    });
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            });

            thread1.start();
        });

        jfxcbbSLGioi.setOnAction(e -> {
            String year = (String) jfxcbbSLGioi.getValue();
            int iYear = Integer.parseInt(year);
            lbGioi.setVisible(false);
            spGioi.setVisible(true);

            Thread thread1 = new Thread(() -> {
                try {
                    int laySoLuongHocSinhGioiNam = ThongKe.laySoLuongHocSinhGioiNam(iYear);
                    int soLuongHocSinh = ThongKe.laySoLuongHocNam(iYear);
                    String strGioi = laySoLuongHocSinhGioiNam + "/" + soLuongHocSinh + " học sinh";

                    Platform.runLater(() -> {
                        lbGioi.setText(strGioi);
                        lbGioi.setVisible(true);
                        spGioi.setVisible(false);
                    });
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            });

            thread1.start();
        });

        jfxcbbSLGVBatDauDayNam.setOnAction(e -> {
            String year = (String) jfxcbbSLGVBatDauDayNam.getValue();
            int iYear = Integer.parseInt(year);
            lbGiaoVienBatDauGiangDayNam.setVisible(false);
            spGVGDBatDau.setVisible(true);

            Thread thread1 = new Thread(() -> {
                try {
                    int laySoLuongGVBatDauDayNam = ThongKe.laySoLuongGVBatDauDayNam(iYear);
                    int laySoLuongGVNam = ThongKe.laySoLuongGVNam(iYear);
                    String strBatDauDay = laySoLuongGVBatDauDayNam + "/" + laySoLuongGVNam + " giáo viên";

                    Platform.runLater(() -> {
                        lbGiaoVienBatDauGiangDayNam.setText(strBatDauDay);
                        lbGiaoVienBatDauGiangDayNam.setVisible(true);
                        spGVGDBatDau.setVisible(false);
                    });
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            });

            thread1.start();
        });

        jfxcbbSLGVNghiDayNam.setOnAction(e -> {
            String year = (String) jfxcbbSLGVNghiDayNam.getValue();
            int iYear = Integer.parseInt(year);
            lbGiaoVienNghiDayNam.setVisible(false);
            spGVGDKetThuc.setVisible(true);

            Thread thread1 = new Thread(() -> {
                try {
                    int laySoLuongGVNghiDayNam = ThongKe.laySoLuongGVKetThucDayNam(iYear);
                    int laySoLuongGVNam = ThongKe.laySoLuongGVNam(iYear);
                    String strNghiDay = laySoLuongGVNghiDayNam + "/" + laySoLuongGVNam + " giáo viên";

                    Platform.runLater(() -> {
                        lbGiaoVienNghiDayNam.setText(strNghiDay);
                        lbGiaoVienNghiDayNam.setVisible(true);
                        spGVGDKetThuc.setVisible(false);
                    });
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            });

            thread1.start();
        });

        jfxcbbSLNhapHocNam.setOnAction(e -> {
            String year = (String) jfxcbbSLNhapHocNam.getValue();
            int iYear = Integer.parseInt(year);
            lbSLNhapHocNam.setVisible(false);
            spHSNhapHoc.setVisible(true);

            Thread thread1 = new Thread(() -> {
                try {
                    int laySoLuongHSNhapHocNam = ThongKe.laySoLuongHSNhapHocNam(iYear);
                    int laySoLuongHSNam = ThongKe.laySoLuongHSNam(iYear);
                    String strNhapHoc = laySoLuongHSNhapHocNam + "/" + laySoLuongHSNam + " học sinh";

                    Platform.runLater(() -> {
                        lbSLNhapHocNam.setText(strNhapHoc);
                        lbSLNhapHocNam.setVisible(true);
                        spHSNhapHoc.setVisible(false);
                    });
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            });

            thread1.start();
        });

        jfxcbbSLTotNghiepNam.setOnAction(e -> {
            String year = (String) jfxcbbSLGVNghiDayNam.getValue();
            int iYear = Integer.parseInt(year);
            lbSoLuongTotNghiepNam.setVisible(false);
            spHSTotNghiep.setVisible(true);

            Thread thread1 = new Thread(() -> {
                try {
                    int laySoLuongHSTotNghiepNam = ThongKe.laySoLuongHSTotNghiepNam(iYear);
                    int laySoLuongHSNam = ThongKe.laySoLuongHSNam(iYear);
                    String strTotNghiep = laySoLuongHSTotNghiepNam + "/" + laySoLuongHSNam + " học sinh";

                    Platform.runLater(() -> {
                        lbSoLuongTotNghiepNam.setText(strTotNghiep);
                        lbSoLuongTotNghiepNam.setVisible(true);
                        spHSTotNghiep.setVisible(false);
                    });
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            });

            thread1.start();
        });
    }

    private void setupListCBB(List<String> stringList) {
        jfxcbbSLKhongDat.getItems().addAll(stringList);
        jfxcbbSLKha.getItems().addAll(stringList);
        jfxcbbSLGioi.getItems().addAll(stringList);
        jfxcbbSLTrungBinh.getItems().addAll(stringList);
        jfxcbbSLNhapHocNam.getItems().addAll(stringList);
        jfxcbbSLGVBatDauDayNam.getItems().addAll(stringList);
        stringList.remove(stringList.size() - 1);
        stringList.remove(stringList.size() - 1);
        stringList.remove(stringList.size() - 1);
        stringList.remove(stringList.size() - 1);
        jfxcbbSLTotNghiepNam.getItems().addAll(stringList);
        stringList.remove(stringList.size() - 1);
        stringList.remove(stringList.size() - 1);
        stringList.remove(stringList.size() - 1);
        stringList.remove(stringList.size() - 1);
        stringList.remove(stringList.size() - 1);
        stringList.remove(stringList.size() - 1);
        jfxcbbSLGVNghiDayNam.getItems().addAll(stringList);
    }
}
