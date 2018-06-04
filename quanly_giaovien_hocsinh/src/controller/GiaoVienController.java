package controller;

import com.jfoenix.controls.*;
import controller.Share.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import model.data.GiaoVien;
import model.database.InsertDB;
import model.repository.RepositoryGiaoVien;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GiaoVienController {

    @FXML
    AnchorPane mainPane;

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

    @FXML
    public Label lbGiaoVien;

    @FXML
    private ImageView imgGiaoVien;

    @FXML
    private JFXButton btnDetailImg;

    @FXML
    ImageView imgView;

    public GiaoVien oldGiaoVien;

    public GiaoVien newGiaoVien;

    /**
     * Báo xem action sẽ là action nào
     */
    public static String action;

    @FXML
    void btnCancelAction(ActionEvent event) throws SQLException {

    }

    void handleAdd(ActionEvent event) throws SQLException {
        // Thêm giáo viên
        newGiaoVien = getGiaoVienFromForm();
        RepositoryGiaoVien.add(newGiaoVien);
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
                Integer.parseInt(jfxComboboxRole.getValue().toString()),
                LocalDate.now().toString(),
                null,
                imgGiaoVien.getImage().getUrl()
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

        MaGV.setText(InsertDB.getInstance().initInsert("giaovien") + "");
        ngaySinh.setValue(LocalDate.now());

        btnCancel.setOnAction(e -> {
            MainController.secondaryStage.close();
        });
    }

    public void init() {
        if(oldGiaoVien != null) {
            int i = 0;

            // update gia tri ra combobox
            i = getPositionValue(jfxComboboxSex, oldGiaoVien.getGioiTinh() + "");

            jfxComboboxSex.getSelectionModel().select(
                    i
            );
            // ---------------------------


            // update gia tri ra combobox
            i = getPositionValue(jfxComboboxRole, oldGiaoVien.getRole() + "");

            jfxComboboxRole.getSelectionModel().select(
                    i
            );
            // ----------------------------

            hoTen.setText(oldGiaoVien.getHoTen());
            MaGV.setText(oldGiaoVien.getMaGV() + "");
            ngaySinh.setValue(LocalDate.parse(oldGiaoVien.getNgaySinh()));
            CMND.setText(oldGiaoVien.getCMND());
            dienThoai.setText(oldGiaoVien.getDienThoai());
            diaChi.setText(oldGiaoVien.getDiaChi());
            matKhau.setText(oldGiaoVien.getMatkhau());
            lbGiaoVien.setText("Giáo Viên");
            System.out.println(oldGiaoVien.getAnhGiaoVien());
            if(!oldGiaoVien.getAnhGiaoVien().equals("Chưa có ảnh")) {
                Image img = new Image(oldGiaoVien.getAnhGiaoVien());

//        System.out.println(img.getUrl());

                imgGiaoVien.setImage(img);
//        System.out.println(imgGiaoVien.getImage().getUrl());
                imgGiaoVien.setFitHeight(315);
                imgGiaoVien.setFitWidth(450);
            } else {

            }
        }
    }

    /**
     * Kiểm tra xem nhập có hợp lệ không
     *
     * @return
     */
    private boolean checkIsEmpty() {
        if ("".equals(jfxComboboxSex.getValue().toString()) ||
                "".equals(hoTen.getText().trim()) || "".equals(diaChi.getText().trim()) ||
                "".equals(dienThoai.getText().trim()) || "".equals(CMND.getText().trim()) ||
                "".equals(matKhau.getText().trim()) || "".equals(ngaySinh.getValue().toString()) ||
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
//        giaovien.Update.whereId(MaGV.getText() + "", giaoVien);
        RepositoryGiaoVien.edit(giaoVien);

        newGiaoVien = giaoVien;
        new Alert(Alert.AlertType.INFORMATION, "Sửa thành công", ButtonType.OK).showAndWait();
        MainController.secondaryStage.close();
    }

    /**
     * Lấy vị trí của giá trị trong comboBox
     *
     * @param jfxCombobox comboBox
     * @param value       giá trị muốn lấy vị trí
     * @return
     */
    private int getPositionValue(JFXComboBox<String> jfxCombobox, String value) {
        // update gia tri ra combobox
        for (int i = 0; i < jfxCombobox.getItems().size(); i++) {
//            System.out.println(jfxCombobox.getItems().get(i));
            if (jfxCombobox.getItems().get(i).toString().trim()
                    .equals(
                            value.trim())
                    ) {
                return i;
            }
        }
        return -1;
    }

    @FXML
    void getImage(MouseEvent event) throws IOException {
        try {
            FileChooser fileChooser = new FileChooser();
            File fileImage = fileChooser.showOpenDialog(MainController.secondaryStage);

            String getImgDir;
            getImgDir = this.getClass().getResource("/images").toString().replace("/", "\\").substring(6);
//        System.out.println(getImgDir);
            File fileDir = new File(getImgDir);
            if (!fileDir.exists()) {
                fileDir.mkdir();
            }

            InputStream inputStream = Files.newInputStream(fileImage.toPath());

//        System.out.println(getImgDir);
            String imgPath = getImgDir + "\\" + fileImage.getName();
            File file = new File(imgPath);
//        System.out.println(file.getAbsolutePath());

            if(!file.exists()) {
                file.createNewFile();
            }
//        System.out.println(this.getClass().getResource("/images"));
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(inputStream.readAllBytes());

            Image img = new Image(String
                    .valueOf(this.getClass().getResource("/images")) + "\\" + file.getName());

//        System.out.println(img.getUrl());

            imgGiaoVien.setImage(img);
//        System.out.println(imgGiaoVien.getImage().getUrl());
            imgGiaoVien.setFitHeight(315);
            imgGiaoVien.setFitWidth(550);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void actionBtnLookImg(ActionEvent e) throws IOException {
//        System.load((
//                this.getClass()
//                        .getResource("/images") + "/" +
//                        "tuoi-tre-dang-gia-bao-nhieu-u547-d20161012-t113832-888179.u3059.d20170616.t095744.390222.jpg")
//                .substring(6).replace("/", "\\"));
//        Process process = new ProcessBuilder("C:/Users/hoang/TTN/Lap3_QuanLyGiaoVien_HocSinh/out/production/quanly_giaovien_hocsinh/images/tuoi-tre-dang-gia-bao-nhieu-u547-d20161012-t113832-888179.u3059.d20170616.t095744.390222.jpg").start();

//        Runtime runtime = Runtime.getRuntime();
//        Process process = runtime.exec("C:/Users/hoang/TTN/Lap3_QuanLyGiaoVien_HocSinh/out/production/quanly_giaovien_hocsinh/images/tuoi-tre-dang-gia-bao-nhieu-u547-d20161012-t113832-888179.u3059.d20170616.t095744.390222.jpg");
    }
    }
