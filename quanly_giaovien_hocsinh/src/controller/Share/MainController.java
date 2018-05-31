package controller.Share;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import controller.GiaoVienController;
import controller.PhanCongController;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;
import model.data.*;
import model.repository.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

//import org.junit.jupiter.api.Test;

public class MainController implements Initializable {

    @FXML
    private AnchorPane primaryPane;

    @FXML
    private AnchorPane paneBottom;

    @FXML
    private JFXDrawer drawBottom;

    @FXML
    private JFXTreeTableView treeTableView;

    @FXML
    private JFXComboBox<String> jfxCombobox;

    @FXML
    private AnchorPane paneMid;

    @FXML
    private AnchorPane paneMain;

    @FXML
    private JFXDrawer drawLeft;

    @FXML
    private AnchorPane paneTop;

    @FXML
    private JFXDrawer drawTop;

    @FXML
    private JFXDrawer drawMain;

    public GiaoVien admin;

    private JFXButton btnShowDetailPhanCong;
    private JFXNodesList jfxNodeListRoot;
    private JFXNodesList jfxNodeListControl;
    public static Stage secondaryStage;
    public static Stage tertiary;

    public void init() throws IOException {
        // Để mặc định tàng hình
        drawLeft.setVisible(true);

        FXMLLoader leftLoader = new FXMLLoader(getClass().getResource("/view/Share/LeftPane.fxml"));
        AnchorPane anchorPane= leftLoader.load();

        ShareViewController leftPaneLoaderController = leftLoader.getController();

        leftPaneLoaderController.admin = admin;

        leftPaneLoaderController.imgUser.setImage(new Image(admin.getAnhGiaoVien()));

        leftPaneLoaderController.btnUpdateDB.setOnAction(e -> {
            try {
                leftPaneLoaderController.synDatabase();
                handleActionJFXComboboxSwitchTable();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        leftPaneLoaderController.btnThongTin.setOnAction(e -> {
            try {
                makeSecondaryStage(updateGiaoVien(leftPaneLoaderController.admin));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        leftPaneLoaderController.init();

//            leftPaneLoaderController.init();

        drawLeft.setSidePane(anchorPane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // Khởi tạo --
//            fitSizeMethod(treeTableView, 0.0);
            // Khoi tao ket noi

            // add item to comboBox select list
            setSelectCombobox();

            JFXHamburger hamburger = new JFXHamburger();
            hamburger.setCursor(Cursor.HAND);

            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
            // setRate equal -1 is not active
            transition.setRate(-1);

            // Xử lý action handleActionHamburger
            handleActionHamburger(hamburger, transition);


//            AnchorPane top = FXMLLoader.load(getClass().getResource("../../view/Share/TopPane.fxml"));
            AnchorPane top = new AnchorPane();

//            AnchorPane bottom = FXMLLoader.load(getClass().getResource("/view/Share/BottomPane.fxml"));
//            drawLeft.setSidePane(left);
//            left.prefHeightProperty().bind(paneLeft.heightProperty());
//            left.prefWidthProperty().bind(paneLeft.widthProperty());

            // Lay toolbar
            JFXToolbar toolbar = getJfxToolbar(hamburger);

            // Thêm toolbar cho AnchorPane
            top.getChildren().add(toolbar);

//            paneLeft.getChildren().add(left);
//            AnchorPane.setBottomAnchor(left, 0.0);
//            AnchorPane.setRightAnchor(left, 0.0);
//            AnchorPane.setTopAnchor(left, 0.0);
//            AnchorPane.setLeftAnchor(left, 0.0);

//            paneBottom.getChildren().add(bottom);
//            fitSizeMethod(bottom, 0.0);

            paneTop.getChildren().add(top);
            fitSizeMethod(top, 0.0);

        } catch (Exception e) {
//            System.out.println(e.getCause());
        }


    }

    private JFXToolbar getJfxToolbar(JFXHamburger hamburger) {
        // Chứa Nav chức năng -
        VBox containerNav = new VBox();

        JFXButton btnHome = new JFXButton("Home");
        btnHome.setStyle("-fx-background-color: #000000; -fx-background-radius: 50px; -fx-border-radius: 50px;");
        setStyleButton(btnHome);

        JFXButton btnOption = new JFXButton("Tùy chọn");
        btnOption.setStyle("-fx-background-color: #3b5998; -fx-background-radius: 50px; -fx-border-radius: 50px;");
        setStyleButton(btnOption);

        JFXButton btnUpdate = new JFXButton("Cập nhật");
        btnUpdate.setStyle("-fx-background-color: #00C851; -fx-background-radius: 50px; -fx-border-radius: 50px;");
        setStyleButton(btnUpdate);

        JFXButton btnAdd = new JFXButton("Thêm");
        btnAdd.setStyle("-fx-background-color: #4285F4; -fx-background-radius: 50px; -fx-border-radius: 50px;");
        setStyleButton(btnAdd);


        JFXButton btnDel = new JFXButton("Xóa");
        btnDel.setStyle("-fx-background-color: #ff4444; -fx-background-radius: 50px; -fx-border-radius: 50px;");
        setStyleButton(btnDel);

        btnShowDetailPhanCong = new JFXButton("Chi tiết");
        btnShowDetailPhanCong.setStyle("-fx-background-color: #ea4335; -fx-background-radius: 50px; -fx-border-radius: 50px;");
        setStyleButton(btnShowDetailPhanCong);

        // Tạo jfxNodeListRoot chứa các node
        jfxNodeListRoot = new JFXNodesList();

        // Tạo subList
        jfxNodeListControl = new JFXNodesList();
        // Add và set hiệu ứng animation
//            jfxNodeListRoot.addAnimatedNode(btnOption, (expanded, duration) ->
//                    new ArrayList<>() {
//                        {
//                            add(new KeyFrame(duration,
//                                    new javafx.animation.KeyValue(
//                                            btnOption.rotateProperty(),
//                                            expanded ? 360 : 0,
//                                            Interpolator.EASE_BOTH
//                                    )));
//                        }
//                    }
//            );
        jfxNodeListRoot.addAnimatedNode(btnHome);

        // Add button
        jfxNodeListControl.addAnimatedNode(btnOption, (expanded, duration) -> {
            return new ArrayList<>() {
                {
                    add(new KeyFrame(duration,
                            new KeyValue(
                                    btnOption.rotateProperty(),
                                    expanded ? 90 : -270,
                                    Interpolator.EASE_BOTH
                            )));
                }
            };
        });
        jfxNodeListControl.addAnimatedNode(btnAdd);
        jfxNodeListControl.addAnimatedNode(btnUpdate);
        jfxNodeListControl.addAnimatedNode(btnShowDetailPhanCong);
        jfxNodeListControl.addAnimatedNode(btnDel);
        btnShowDetailPhanCong.setVisible(false);
        btnShowDetailPhanCong.setDisable(true);
        // Đặt khoảng cách giữa 2 button
        jfxNodeListControl.setSpacing(85);

        // Độ quay của jfxNodeList
        jfxNodeListControl.setRotate(270);
        jfxNodeListRoot.setRotate(270);
        jfxNodeListRoot.setSpacing(85);

        // jfxNodeList là con của thằng NodeList
        jfxNodeListRoot.addAnimatedNode(jfxNodeListControl);
//            AnchorPane.setLeftAnchor();
        containerNav.getChildren().add(jfxNodeListRoot);
        containerNav.setPadding(new Insets(8, 0, 8, 15));

        JFXToolbar toolbar = new JFXToolbar();
        toolbar.setCenter(containerNav);
        toolbar.setRight(hamburger);


        toolbar.setPadding(new Insets(15, 10, 15, 5));
//        toolbar.setStyle("-fx-border-style: solid; -fx-border-radius: 5px;");


        // --------------- handle action btn
        // Add action
        btnAdd.setOnAction(event -> {
            try {
                actionBtn_Add();
                treeTableView.refresh();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Delete action
        btnDel.setOnAction(event -> {
//            System.out.println("I'm here");
            actionBtn_Del();
            treeTableView.refresh();
        });

        // Update action
        btnUpdate.setOnAction(e -> {
            try {
//                handleActionJFXComboboxSwitchTable();
                actionBtn_Update();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        // Show Detail PhanCong action
        btnShowDetailPhanCong.setOnAction(e -> {
            try {
                actionBtn_DetailPhanCong();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        // controller action
        // expand control
        treeTableView.setOnMouseClicked(e -> {
            if (!jfxNodeListControl.isExpanded()) {
                jfxNodeListControl.animateList();
            }
        });

        fitSizeMethod(toolbar, 15.0);
        return toolbar;
    }

    private void actionBtn_Del() {

        TreeItem item = (TreeItem) treeTableView.getSelectionModel().getSelectedItem();
//        System.out.println(item.toString());
        if ("Giáo viên"
                .equals(jfxCombobox.getValue())) {
////            System.out.println("I'm here");
////            System.out.println(item.getValue());
////            System.out.println(item);
            GiaoVien giaoVien = (GiaoVien) item.getValue();
            if (RepositoryGiaoVien.
                    del(giaoVien)) {
//                treeTableView.getColumns().remove(item);

////                System.out.println(RepositoryGiaoVien.getAll().size());
            } else { // Nếu xóa không thành công?
                new Alert(Alert.AlertType.ERROR, "Xóa không thành công", ButtonType.OK).showAndWait();
                return;
            }
        } else if ("Điểm".equals(jfxCombobox.getValue())) {
            Diem diem = (Diem) item.getValue();
            if (RepositoryDiem.
                    del(diem)) {
            } else {
                new Alert(Alert.AlertType.ERROR, "Xóa không thành công", ButtonType.OK).showAndWait();
                return;
            }
        } else if ("Học Sinh".equals(jfxCombobox.getValue())) {
            HocSinh hocSinh = (HocSinh) item.getValue();
            if (RepositoryHocSinh.del(hocSinh)) {
            } else {
                new Alert(Alert.AlertType.ERROR, "Xóa không thành công", ButtonType.OK).showAndWait();
                return;
            }
        } else if ("Môn Học".equals(jfxCombobox.getValue())) {
            MonHoc monHoc = (MonHoc) item.getValue();
            if (RepositoryMonHoc.del(monHoc)) {
            } else {
                new Alert(Alert.AlertType.ERROR, "Xóa không thành công", ButtonType.OK).showAndWait();
                return;
            }
        } else if ("Lớp Học".equals(jfxCombobox.getValue())) {
            LopHoc lopHoc = (LopHoc) item.getValue();
            if (RepositoryLopHoc.del(lopHoc)) {
            } else {
                new Alert(Alert.AlertType.ERROR, "Xóa không thành công", ButtonType.OK).showAndWait();
                return;
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Xóa không thành công", ButtonType.OK).showAndWait();
            return;
        }

        treeTableView.refresh();
        new Alert(Alert.AlertType.INFORMATION, "Xóa thành công", ButtonType.OK).showAndWait();
    }

//    public static Object lock = new Object();

    private void actionBtn_Add() throws IOException, InterruptedException {
        GiaoVienController.action = "Add";
        Parent root = null;
        if ("Giáo viên".equals(jfxCombobox.getValue())) {
            root = FXMLLoader.load(getClass().getResource("/view/GiaoVien/Add_Update.fxml"));
        }

        makeSecondaryStage(root);
    }

    private void actionBtn_Update() throws IOException, InterruptedException, SQLException {
        GiaoVienController.action = "Update";
        Parent root = null;
        if ("Giáo viên".equals(jfxCombobox.getValue())) {
            int pos = treeTableView.getSelectionModel().getSelectedIndex();
            GiaoVien gv = RepositoryGiaoVien.getAll().get(pos);
            root = updateGiaoVien(gv);
        }

        makeSecondaryStage(root);

        try {
            handleActionJFXComboboxSwitchTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void makeSecondaryStage(Parent root) {
        if (root != null) {
            secondaryStage = new Stage();
            secondaryStage.setScene(new Scene(root, 800, 754));
            secondaryStage.setResizable(false);
            secondaryStage.showAndWait();
            secondaryStage.close();
        }
    }

    private void actionBtn_DetailPhanCong() throws IOException {
//        GiaoVienController.action = "Add";
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PhanCong/Detail.fxml"));
        root = loader.load();

        TreeItem<PhanCong> phanCongTreeItem = (TreeItem<PhanCong>) treeTableView.getSelectionModel().getSelectedItem();
        PhanCong pc = phanCongTreeItem.getValue();

////        System.out.println(phanCongTreeItem.toString());

        PhanCongController phanCongController = loader.getController();
        phanCongController.phanCong = pc;
        phanCongController.init();

//        phanCongController.lbGiaoVien = ;

        if (root != null) {
            secondaryStage = new Stage();
            secondaryStage.setScene(new Scene(root, 800, 600));
            secondaryStage.setResizable(false);
            secondaryStage.showAndWait();
            secondaryStage.close();
        }
    }

    /**
     * Xử lý update Giáo viên
     *
     * @return
     * @throws IOException
     */
    private Parent updateGiaoVien(GiaoVien oldGiaoVien) throws IOException {
        GiaoVienController.action = "Update";

        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/GiaoVien/Add_Update.fxml"));
        root = loader.load();

        GiaoVienController updateController = loader.getController();
        updateController.oldGiaoVien = oldGiaoVien;
        updateController.init();

        return root;
    }

    /**
     * @param node fitSize of anchorPane truyền vào
     */
    private void fitSizeMethod(Node node, double value) {
        AnchorPane.setBottomAnchor(node, value);
        AnchorPane.setRightAnchor(node, value);
        AnchorPane.setTopAnchor(node, value);
        AnchorPane.setLeftAnchor(node, value);
    }

    private void setSelectCombobox() throws SQLException {
        List items = new ArrayList();
        items.add("Điểm");
        items.add("Giáo viên");
        items.add("Học kỳ");
        items.add("Học sinh");
        items.add("Khối học");
        items.add("Lớp học");
        items.add("Môn học");
        items.add("Năm học");
        items.add("Phân công");
        items.add("Xếp lớp");

        jfxCombobox.getItems().addAll(items);
        jfxCombobox.setValue("Giáo viên");
        handleActionJFXComboboxSwitchTable();

        //        jfxCombobox.setValue("Giáo viên");
//        handleActionJFXComboboxSwitchTable();
    }

    //Of field
    private String getNameOfField(String name) {

        StringBuffer stringBuffer = new StringBuffer("");
        for (int i = name.length() - 1; i >= 0; i--) {
            if (name.charAt(i) == ' ' || name.charAt(i) == '.')
                break;
            stringBuffer.append(name.charAt(i));
        }

        String newName = stringBuffer.reverse().toString();
        if ("searchDB".equals(newName) || "statement".equals(newName)) {
            return null;
        }
        return newName;
    }

    private void expandNodeList(JFXNodesList root) {
//        if (root != null) {
//            if (!root.isExpanded()) {
//                // make is expand
//                root.animateList();
//            }
//            for (Node node : root.getChildren()) {
//                if (node instanceof JFXNodesList) {
//                    expandNodeList((JFXNodesList) node);
//                }
//            }
//        }

        if (root != null) {
            if (!root.isExpanded()) {
                // make is expand
                root.animateList();
                ;
            }
        }
    }

    @FXML
    private void handleActionJFXComboboxSwitchTable() throws SQLException {
        expandNodeList(jfxNodeListRoot);

        treeTableView.getColumns().clear();
        Field[] fields;
        // Mang cac truong trong jfxTreeTableView
        JFXTreeTableColumn[] jfxTreeTableColumns = null;

        // bảng chọn
        String tableSelected = jfxCombobox.getValue();
        if ("Giáo viên".equals(tableSelected)) {
            fields = GiaoVien.class.getDeclaredFields();
            // 2 truong khong su dung
            String[] strFields = getAllColumnOfObject(fields);
            // Tạo đồng bộ cho column (Theo tên trường và tên bảng)
            jfxTreeTableColumns = getJfxTreeTableColumns(strFields, GiaoVien.class.getSimpleName());

            ObservableList<GiaoVien> data = FXCollections.observableArrayList(
                    RepositoryGiaoVien.getAll()
            );

//            TreeItem<GiaoVien> root = new RecursiveTreeItem(data);

//            TreeItem<GiaoVien> treeItem = new RecursiveTreeItem(data, null,RecursiveTreeItem::getChildren);
            TreeItem<GiaoVien> root = new RecursiveTreeItem<GiaoVien>(data, RecursiveTreeObject::getChildren);
            treeTableView.setRoot(root);
            treeTableView.setShowRoot(false);
        } else if ("Xếp lớp".equals(tableSelected)) {
            fields = XepLop.class.getDeclaredFields();
            // 2 truong khong xu dung
            String[] strFields = getAllColumnOfObject(fields);
            // Tạo đồng bộ cho column (Theo tên trường và tên bảng)
            jfxTreeTableColumns = getJfxTreeTableColumns(strFields, XepLop.class.getSimpleName());


            ObservableList<XepLop> data = FXCollections.observableArrayList(
                    RepositoryXepLop.getAll()
            );

//            TreeItem<XepLop> root = new RecursiveTreeItem(data);

//            TreeItem<XepLop> treeItem = new RecursiveTreeItem(data, null,RecursiveTreeItem::getChildren);
            TreeItem<XepLop> root = new RecursiveTreeItem<XepLop>(data, RecursiveTreeObject::getChildren);
            treeTableView.setRoot(root);
            treeTableView.setShowRoot(false);
        } else if ("Điểm".equals(tableSelected)) {
            fields = Diem.class.getDeclaredFields();
            // 2 truong khong xu dung
            String[] strFields = getAllColumnOfObject(fields);
            // Tạo đồng bộ cho column (Theo tên trường và tên bảng)
            jfxTreeTableColumns = getJfxTreeTableColumns(strFields, Diem.class.getSimpleName());

            ObservableList<Diem> data = FXCollections.observableArrayList(
                    RepositoryDiem.getAll()
            );

//            TreeItem<Diem> root = new RecursiveTreeItem(data);

//            TreeItem<Diem> treeItem = new RecursiveTreeItem(data, null,RecursiveTreeItem::getChildren);
            TreeItem<Diem> root = new RecursiveTreeItem<Diem>(data, RecursiveTreeObject::getChildren);
            treeTableView.setRoot(root);
        } else if ("Học kỳ".equals(tableSelected)) {
            fields = HocKy.class.getDeclaredFields();
            // 2 truong khong xu dung
            String[] strFields = getAllColumnOfObject(fields);
            // Tạo đồng bộ cho column (Theo tên trường và tên bảng)
            jfxTreeTableColumns = getJfxTreeTableColumns(strFields, HocKy.class.getSimpleName());

            ObservableList<HocKy> data = FXCollections.observableArrayList(
                    RepositoryHocKy.getAll()
            );

//            TreeItem<HocKy> root = new RecursiveTreeItem(data);

//            TreeItem<HocKy> treeItem = new RecursiveTreeItem(data, null,RecursiveTreeItem::getChildren);
            TreeItem<HocKy> root = new RecursiveTreeItem<HocKy>(data, RecursiveTreeObject::getChildren);
            treeTableView.setRoot(root);
        } else if ("Học sinh".equals(tableSelected)) {
            fields = HocSinh.class.getDeclaredFields();
            // 2 truong khong xu dung
            String[] strFields = getAllColumnOfObject(fields);
            // Tạo đồng bộ cho column (Theo tên trường và tên bảng)
            jfxTreeTableColumns = getJfxTreeTableColumns(strFields, HocSinh.class.getSimpleName());

            ObservableList<HocSinh> data = FXCollections.observableArrayList(
                    RepositoryHocSinh.getAll()
            );

//            TreeItem<HocSinh> root = new RecursiveTreeItem(data);

//            TreeItem<HocSinh> treeItem = new RecursiveTreeItem(data, null,RecursiveTreeItem::getChildren);
            TreeItem<HocSinh> root = new RecursiveTreeItem<HocSinh>(data, RecursiveTreeObject::getChildren);
            treeTableView.setRoot(root);
        } else if ("Khối học".equals(tableSelected)) {
            fields = KhoiHoc.class.getDeclaredFields();
            // 2 truong khong xu dung
            String[] strFields = getAllColumnOfObject(fields);
            // Tạo đồng bộ cho column (Theo tên trường và tên bảng)
            jfxTreeTableColumns = getJfxTreeTableColumns(strFields, KhoiHoc.class.getSimpleName());

            ObservableList<KhoiHoc> data = FXCollections.observableArrayList(
                    RepositoryKhoiHoc.getAll()
            );

//            TreeItem<KhoiHoc> root = new RecursiveTreeItem(data);

//            TreeItem<KhoiHoc> treeItem = new RecursiveTreeItem(data, null,RecursiveTreeItem::getChildren);
            TreeItem<KhoiHoc> root = new RecursiveTreeItem<KhoiHoc>(data, param -> param.getChildren());
            treeTableView.setRoot(root);
        } else if ("Lớp học".equals(tableSelected)) {
            fields = LopHoc.class.getDeclaredFields();
            // 2 truong khong xu dung
            String[] strFields = getAllColumnOfObject(fields);
            // Tạo đồng bộ cho column (Theo tên trường và tên bảng)
            jfxTreeTableColumns = getJfxTreeTableColumns(strFields, LopHoc.class.getSimpleName());

            ObservableList<LopHoc> data = FXCollections.observableArrayList(
                    RepositoryLopHoc.getAll()
            );

//            TreeItem<LopHoc> root = new RecursiveTreeItem(data);

//            TreeItem<LopHoc> treeItem = new RecursiveTreeItem(data, null,RecursiveTreeItem::getChildren);
            TreeItem<LopHoc> root = new RecursiveTreeItem<LopHoc>(data, RecursiveTreeObject::getChildren);
            treeTableView.setRoot(root);
        } else if ("Môn học".equals(tableSelected)) {
            fields = MonHoc.class.getDeclaredFields();
            // 2 truong khong xu dung
            String[] strFields = getAllColumnOfObject(fields);
            // Tạo đồng bộ cho column (Theo tên trường và tên bảng)
            jfxTreeTableColumns = getJfxTreeTableColumns(strFields, MonHoc.class.getSimpleName());

            ObservableList<MonHoc> data = FXCollections.observableArrayList(
                    RepositoryMonHoc.getAll()
            );

//            TreeItem<MonHoc> root = new RecursiveTreeItem(data);

//            TreeItem<MonHoc> treeItem = new RecursiveTreeItem(data, null,RecursiveTreeItem::getChildren);
            TreeItem<MonHoc> root = new RecursiveTreeItem<MonHoc>(data, RecursiveTreeObject::getChildren);
            treeTableView.setRoot(root);
        } else if ("Năm học".equals(tableSelected)) {
            fields = NamHoc.class.getDeclaredFields();
            // 2 truong khong xu dung
            String[] strFields = getAllColumnOfObject(fields);
            // Tạo đồng bộ cho column (Theo tên trường và tên bảng)
            jfxTreeTableColumns = getJfxTreeTableColumns(strFields, NamHoc.class.getSimpleName());

            ObservableList<NamHoc> data = FXCollections.observableArrayList(
                    RepositoryNamHoc.getAll()
            );

//            TreeItem<NamHoc> root = new RecursiveTreeItem(data);

//            TreeItem<NamHoc> treeItem = new RecursiveTreeItem(data, null,RecursiveTreeItem::getChildren);
            TreeItem<NamHoc> root = new RecursiveTreeItem<NamHoc>(data, RecursiveTreeObject::getChildren);
            treeTableView.setRoot(root);
        } else if ("Phân công".equals(tableSelected)) {
            fields = PhanCong.class.getDeclaredFields();
            // 2 truong khong xu dung
            String[] strFields = getAllColumnOfObject(fields);
            // Tạo đồng bộ cho column (Theo tên trường và tên bảng)
            jfxTreeTableColumns = getJfxTreeTableColumns(strFields, PhanCong.class.getSimpleName());

//            System.out.println(RepositoryPhanCong.getAll());

            ObservableList<PhanCong> data = FXCollections.observableArrayList(
                    RepositoryPhanCong.getAll()
            );

//            TreeItem<PhanCong> root = new RecursiveTreeItem(data);

//            TreeItem<PhanCong> treeItem = new RecursiveTreeItem(data, null,RecursiveTreeItem::getChildren);
            TreeItem<PhanCong> root = new RecursiveTreeItem<PhanCong>(data, RecursiveTreeObject::getChildren);
            btnShowDetailPhanCong.setDisable(false);
            treeTableView.setRoot(root);
        }

        treeTableView.getColumns().addAll(jfxTreeTableColumns);
    }

    private String[] getAllColumnOfObject(Field[] fields) {
        String[] strFields = new String[fields.length - 2];
        int i = 0;
        for (Field field : fields) {
            String name = getNameOfField(field.toString());

            if (name != null) {
                strFields[i] = name;
                i++;
            }
        }
        return strFields;
    }

    private JFXTreeTableColumn[] getJfxTreeTableColumns(String[] strFields, String object) {
        JFXTreeTableColumn[] jfxTreeTableColumns = new JFXTreeTableColumn[strFields.length];

        if (object.equals("GiaoVien")) {
            for (int j = 0; j < jfxTreeTableColumns.length; j++) {
                jfxTreeTableColumns[j] = new JFXTreeTableColumn(strFields[j]);

                setStyleColumn(jfxTreeTableColumns[j], GiaoVien.class.getSimpleName());
////                System.out.println(treeTableView.getWidth());
                if (jfxTreeTableColumns[j].getText() != null) {
                    if (jfxTreeTableColumns[j].getText().equals("maGV")) {
                        jfxTreeTableColumns[j].setText("Mã Giáo Viên");
                    } else if (jfxTreeTableColumns[j].getText().equals("gioiTinh")) {
                        jfxTreeTableColumns[j].setText("Giới Tính");
                    } else if (jfxTreeTableColumns[j].getText().equals("hoTen")) {
                        jfxTreeTableColumns[j].setText("Họ Tên");
                    } else if (jfxTreeTableColumns[j].getText().equals("diaChi")) {
                        jfxTreeTableColumns[j].setText("Địa Chỉ");
                    } else if (jfxTreeTableColumns[j].getText().equals("ngaySinh")) {
                        jfxTreeTableColumns[j].setText("Ngày Sinh");
                    } else if (jfxTreeTableColumns[j].getText().equals("dienThoai")) {
                        jfxTreeTableColumns[j].setText("Điện Thoại");
                    } else if (jfxTreeTableColumns[j].getText().equals("matkhau")) {
                        jfxTreeTableColumns[j].setText("Mật Khẩu");
                    } else if (jfxTreeTableColumns[j].getText().equals("role")) {
                        jfxTreeTableColumns[j].setText("Vai Trò");
                    }

                    jfxTreeTableColumns[j].setPrefWidth(treeTableView.getWidth() / jfxTreeTableColumns.length);
                    jfxTreeTableColumns[j].setMaxWidth(200.0);
                    jfxTreeTableColumns[j].setMinWidth(150.0);

                    jfxTreeTableColumns[j].setCellValueFactory(
                            new TreeItemPropertyValueFactory<GiaoVien, String>(strFields[j])
                    );
                }
            }
        } else if (object.equals("XepLop")) {
            for (int j = 0; j < jfxTreeTableColumns.length; j++) {
                jfxTreeTableColumns[j] = new JFXTreeTableColumn(strFields[j]);
                setStyleColumn(jfxTreeTableColumns[j], XepLop.class.getSimpleName());

                if (jfxTreeTableColumns[j].getText() != null) {

                    if (jfxTreeTableColumns[j].getText().equals("maHS")) {
                        jfxTreeTableColumns[j].setText("Mã Học Sinh");
                    } else if (jfxTreeTableColumns[j].getText().equals("maLH")) {
                        jfxTreeTableColumns[j].setText("Mã Lớp Học");
                    }

                    jfxTreeTableColumns[j].setPrefWidth(treeTableView.getWidth() / jfxTreeTableColumns.length);
                    jfxTreeTableColumns[j].setMaxWidth(200.0);
                    jfxTreeTableColumns[j].setMinWidth(150.0);
                    jfxTreeTableColumns[j].setCellValueFactory(
                            new TreeItemPropertyValueFactory<XepLop, String>(strFields[j])
                    );

                }
            }
        } else if (object.equals("Diem")) {
            for (int j = 0; j < jfxTreeTableColumns.length; j++) {
                jfxTreeTableColumns[j] = new JFXTreeTableColumn(strFields[j]);
                setStyleColumn(jfxTreeTableColumns[j], Diem.class.getSimpleName());

                if (jfxTreeTableColumns[j].getText().equals("maHS")) {
                    jfxTreeTableColumns[j].setText("Mã Học Sinh");
                } else if (jfxTreeTableColumns[j].getText().equals("diemHS1")) {
                    jfxTreeTableColumns[j].setText("Điểm Hệ Só 1");
                } else if (jfxTreeTableColumns[j].getText().equals("diemHS2")) {
                    jfxTreeTableColumns[j].setText("Điểm Hệ Só 2");
                } else if (jfxTreeTableColumns[j].getText().equals("diemHS3")) {
                    jfxTreeTableColumns[j].setText("Điểm Hệ Só 3");
                } else if (jfxTreeTableColumns[j].getText().equals("maPC")) {
                    jfxTreeTableColumns[j].setText("Mã Phân Công");
                }

                jfxTreeTableColumns[j].setPrefWidth(treeTableView.getWidth() / jfxTreeTableColumns.length);
                jfxTreeTableColumns[j].setMaxWidth(200.0);
                jfxTreeTableColumns[j].setMinWidth(150.0);
                jfxTreeTableColumns[j].setCellValueFactory(
                        new TreeItemPropertyValueFactory<Diem, String>(strFields[j])
                );
            }
        } else if (object.equals("HocKy")) {
            for (int j = 0; j < jfxTreeTableColumns.length; j++) {
                jfxTreeTableColumns[j] = new JFXTreeTableColumn(strFields[j]);
                setStyleColumn(jfxTreeTableColumns[j], HocKy.class.getSimpleName());

                if (jfxTreeTableColumns[j].getText().equals("maHK")) {
                    jfxTreeTableColumns[j].setText("Mã Học Kỳ");
                } else if (jfxTreeTableColumns[j].getText().equals("tenHK")) {
                    jfxTreeTableColumns[j].setText("Tên Học Kỳ");
                } else if (jfxTreeTableColumns[j].getText().equals("thangBatDau")) {
                    jfxTreeTableColumns[j].setText("Tháng Bắt Đầu");
                } else if (jfxTreeTableColumns[j].getText().equals("ngayBatDau")) {
                    jfxTreeTableColumns[j].setText("Ngày Bắt Đầu");
                } else if (jfxTreeTableColumns[j].getText().equals("thangKetThuc")) {
                    jfxTreeTableColumns[j].setText("Tháng Kết Thúc");
                } else if (jfxTreeTableColumns[j].getText().equals("ngayKetThuc")) {
                    jfxTreeTableColumns[j].setText("Ngày Kết Thúc");
                }

                jfxTreeTableColumns[j].setPrefWidth(treeTableView.getWidth() / jfxTreeTableColumns.length);
                jfxTreeTableColumns[j].setMaxWidth(200.0);
                jfxTreeTableColumns[j].setMinWidth(150.0);
                jfxTreeTableColumns[j].setCellValueFactory(
                        new TreeItemPropertyValueFactory<HocKy, String>(strFields[j])
                );
            }
        } else if (object.equals("HocSinh")) {
            for (int j = 0; j < jfxTreeTableColumns.length; j++) {
                jfxTreeTableColumns[j] = new JFXTreeTableColumn(strFields[j]);
                setStyleColumn(jfxTreeTableColumns[j], HocSinh.class.getSimpleName());

                if (jfxTreeTableColumns[j].getText().equals("maHS")) {
                    jfxTreeTableColumns[j].setText("Mã Học Sinh");
                } else if (jfxTreeTableColumns[j].getText().equals("gioiTinh")) {
                    jfxTreeTableColumns[j].setText("Giới Tính");
                } else if (jfxTreeTableColumns[j].getText().equals("hoTen")) {
                    jfxTreeTableColumns[j].setText("Họ Tên");
                } else if (jfxTreeTableColumns[j].getText().equals("ngaySinh")) {
                    jfxTreeTableColumns[j].setText("Ngày Sinh");
                } else if (jfxTreeTableColumns[j].getText().equals("diaChi")) {
                    jfxTreeTableColumns[j].setText("Địa Chỉ");
                } else if (jfxTreeTableColumns[j].getText().equals("dienThoai")) {
                    jfxTreeTableColumns[j].setText("Điện Thoại");
                }

                jfxTreeTableColumns[j].setPrefWidth(treeTableView.getWidth() / jfxTreeTableColumns.length);
                jfxTreeTableColumns[j].setMaxWidth(200.0);
                jfxTreeTableColumns[j].setMinWidth(150.0);
                jfxTreeTableColumns[j].setCellValueFactory(
                        new TreeItemPropertyValueFactory<HocSinh, String>(strFields[j])
                );
            }
        } else if (object.equals("KhoiHoc")) {
            for (int j = 0; j < jfxTreeTableColumns.length; j++) {
                jfxTreeTableColumns[j] = new JFXTreeTableColumn(strFields[j]);
                setStyleColumn(jfxTreeTableColumns[j], KhoiHoc.class.getSimpleName());

                if (jfxTreeTableColumns[j].getText().equals("maKH")) {
                    jfxTreeTableColumns[j].setText("Mã Khối Học");
                } else if (jfxTreeTableColumns[j].getText().equals("tenKH")) {
                    jfxTreeTableColumns[j].setText("Tên Khối Học");
                }

                jfxTreeTableColumns[j].setPrefWidth(treeTableView.getWidth() / jfxTreeTableColumns.length);
                jfxTreeTableColumns[j].setMaxWidth(200.0);
                jfxTreeTableColumns[j].setMinWidth(150.0);
                jfxTreeTableColumns[j].setCellValueFactory(
                        new TreeItemPropertyValueFactory<KhoiHoc, String>(strFields[j])
                );
            }
        } else if (object.equals("LopHoc")) {
            for (int j = 0; j < jfxTreeTableColumns.length; j++) {
                jfxTreeTableColumns[j] = new JFXTreeTableColumn(strFields[j]);
                setStyleColumn(jfxTreeTableColumns[j], LopHoc.class.getSimpleName());

                if (jfxTreeTableColumns[j].getText().equals("maLH")) {
                    jfxTreeTableColumns[j].setText("Mã Lớp Học");
                } else if (jfxTreeTableColumns[j].getText().equals("maNH")) {
                    jfxTreeTableColumns[j].setText("Mã Năm Học");
                } else if (jfxTreeTableColumns[j].getText().equals("maGV")) {
                    jfxTreeTableColumns[j].setText("Mã Giáo Viên");
                } else if (jfxTreeTableColumns[j].getText().equals("maKH")) {
                    jfxTreeTableColumns[j].setText("Mã Khối Học");
                } else if (jfxTreeTableColumns[j].getText().equals("tenLH")) {
                    jfxTreeTableColumns[j].setText("Tên Lớp Học");
                }


                jfxTreeTableColumns[j].setPrefWidth(treeTableView.getWidth() / jfxTreeTableColumns.length);
                jfxTreeTableColumns[j].setMaxWidth(200.0);
                jfxTreeTableColumns[j].setMinWidth(150.0);
                jfxTreeTableColumns[j].setCellValueFactory(
                        new TreeItemPropertyValueFactory<LopHoc, String>(strFields[j])
                );
            }
        } else if (object.equals("MonHoc")) {
            for (int j = 0; j < jfxTreeTableColumns.length; j++) {
                jfxTreeTableColumns[j] = new JFXTreeTableColumn(strFields[j]);
                setStyleColumn(jfxTreeTableColumns[j], MonHoc.class.getSimpleName());

                if (jfxTreeTableColumns[j].getText().equals("maMH")) {
                    jfxTreeTableColumns[j].setText("Mã Môn Học");
                } else if (jfxTreeTableColumns[j].getText().equals("tenMH")) {
                    jfxTreeTableColumns[j].setText("Tên Môn Học");
                }

                jfxTreeTableColumns[j].setPrefWidth(treeTableView.getWidth() / jfxTreeTableColumns.length);
                jfxTreeTableColumns[j].setMaxWidth(200.0);
                jfxTreeTableColumns[j].setMinWidth(150.0);
                jfxTreeTableColumns[j].setCellValueFactory(
                        new TreeItemPropertyValueFactory<MonHoc, String>(strFields[j])
                );
            }
        } else if (object.equals("NamHoc")) {
            for (int j = 0; j < jfxTreeTableColumns.length; j++) {
                jfxTreeTableColumns[j] = new JFXTreeTableColumn(strFields[j]);
                setStyleColumn(jfxTreeTableColumns[j], NamHoc.class.getSimpleName());

                if (jfxTreeTableColumns[j].getText().equals("maMH")) {
                    jfxTreeTableColumns[j].setText("Mã Môn Học");
                } else if (jfxTreeTableColumns[j].getText().equals("Nambatdau")) {
                    jfxTreeTableColumns[j].setText("Năm Bắt Đầu");
                } else if (jfxTreeTableColumns[j].getText().equals("Namketthuc")) {
                    jfxTreeTableColumns[j].setText("Năm Kết Thúc");
                } else if (jfxTreeTableColumns[j].getText().equals("tenNH")) {
                    jfxTreeTableColumns[j].setText("Tên Năm Học");
                }


                jfxTreeTableColumns[j].setPrefWidth(treeTableView.getWidth() / jfxTreeTableColumns.length);
                jfxTreeTableColumns[j].setMaxWidth(200.0);
                jfxTreeTableColumns[j].setMinWidth(150.0);
                jfxTreeTableColumns[j].setCellValueFactory(
                        new TreeItemPropertyValueFactory<NamHoc, String>(strFields[j])
                );
            }
        } else if (object.equals("PhanCong")) {
            for (int j = 0; j < jfxTreeTableColumns.length; j++) {
                jfxTreeTableColumns[j] = new JFXTreeTableColumn(strFields[j]);
                setStyleColumn(jfxTreeTableColumns[j], PhanCong.class.getSimpleName());

                if (jfxTreeTableColumns[j].getText().equals("maPC")) {
                    jfxTreeTableColumns[j].setText("Mã Phân Công");
                } else if (jfxTreeTableColumns[j].getText().equals("maMH")) {
                    jfxTreeTableColumns[j].setText("Mã Môn Học");
                } else if (jfxTreeTableColumns[j].getText().equals("maGV")) {
                    jfxTreeTableColumns[j].setText("Mã Giáo Viên");
                } else if (jfxTreeTableColumns[j].getText().equals("maLH")) {
                    jfxTreeTableColumns[j].setText("Mã Lớp Học");
                } else if (jfxTreeTableColumns[j].getText().equals("maHK")) {
                    jfxTreeTableColumns[j].setText("Mã Học Kỳ");
                }

                jfxTreeTableColumns[j].setPrefWidth(treeTableView.getWidth() / jfxTreeTableColumns.length);
                jfxTreeTableColumns[j].setMaxWidth(200.0);
                jfxTreeTableColumns[j].setMinWidth(150.0);
                jfxTreeTableColumns[j].setCellValueFactory(
                        new TreeItemPropertyValueFactory<PhanCong, String>(strFields[j])
                );
            }
        }

        return jfxTreeTableColumns;
    }

    private void handleActionHamburger(JFXHamburger hamburger, HamburgerBackArrowBasicTransition transition) {
        // e is event pass through action parameter
        hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            drawLeft.toggle();

            TranslateTransition aniMain = new TranslateTransition(new Duration(350), paneMain);
            TranslateTransition aniTop = new TranslateTransition(new Duration(350), paneTop);
            TranslateTransition aniBottom = new TranslateTransition(new Duration(350), paneBottom);
            TranslateTransition aniHamburger = new TranslateTransition(new Duration(350), hamburger);
            TranslateTransition aniJfxCombobox = new TranslateTransition(new Duration(350), jfxCombobox);

            if (drawLeft.isOpening()) {
                aniMain.setToX(200);
                aniTop.setToX(200);
                aniBottom.setToX(200);
                aniHamburger.setToX(-200);
                aniJfxCombobox.setToX(-200);

//                AnchorPane.setLeftAnchor(paneMain, 200.0);
//                AnchorPane.setLeftAnchor(drawBottom, 200.0);
//                AnchorPane.setLeftAnchor(paneTop, 200.0);

                aniMain.play();
                aniTop.play();
                aniBottom.play();
                aniHamburger.play();
                aniJfxCombobox.play();
//                    drawBottom.setPadding(new Insets(0, 0, 0, 230));

//                    drawLeft.setStyle("-fx-background-color: black;");
//                    new Thread(() -> {
//                        Platform.runLater(() -> {
//                            try {
//                                wait(500);
//                            } catch (InterruptedException e1) {
//                                e1.printStackTrace();
//                            }
//                            drawLeft.setVisible(!drawLeft.isVisible());
//                        });
//                    }).start();
            } else {
                aniMain.setToX(0);
                aniTop.setToX(0);
                aniBottom.setToX(0);
                aniHamburger.setToX(0);
                aniJfxCombobox.setToX(0);
//                AnchorPane.setLeftAnchor(paneMain, 0.0);
//                AnchorPane.setLeftAnchor(drawBottom, 0.0);
//                AnchorPane.setLeftAnchor(paneTop, 0.0);

                aniMain.play();
                aniTop.play();
                aniBottom.play();
                aniHamburger.play();
                aniJfxCombobox.play();

//                    drawBottom.setPadding(new Insets(0, 0, 0, 30));
//                    drawLeft.setStyle("-fx-background-color: inherit;");
////                    drawLeft.setVisible(!drawLeft.isVisible());
            }

            transition.setRate(transition.getRate() * -1);

            // start animation in current position
            transition.play();
        });
    }

    private void setStyleButton(JFXButton btnOption) {
        btnOption.setMinSize(110, 40);
        btnOption.setFont(new Font("Times New Roman", 20));
        btnOption.setTextFill(Color.WHITE);
    }

    private void setStyleColumn(JFXTreeTableColumn jfxTreeTableColumn, String type) {
//        System.out.println(jfxTreeTableColumn);

        if (jfxTreeTableColumn.getText().startsWith("ma") && !jfxTreeTableColumn.getText().equals("matkhau")) {
            jfxTreeTableColumn.setStyle("-fx-alignment: CENTER; -fx-font-weight: 900; -fx-background-color  : #92959a63");

            return;
        } else if ("GiaoVien".equals(type)) {
            for (Field field : GiaoVien.class.getDeclaredFields()) {
                if (jfxTreeTableColumn.getText().equals(field.getName())) {
//                    System.out.println(field.getType().getSimpleName());
//                    System.out.println(field.getName());
                    if (field.getType().getSimpleName().equals("SimpleStringProperty")) {
                        jfxTreeTableColumn.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
                    } else if (field.getType().getSimpleName().equals("SimpleIntegerProperty")) {
//                TextFieldTreeTableCell<GiaoVien, Integer> textFieldTreeTableCell = new TextFieldTreeTableCell();
                        jfxTreeTableColumn.setCellFactory(TextFieldTreeTableCell
                                .forTreeTableColumn(new StringConverter<Integer>() {
                                    @Override
                                    public String toString(Integer object) {
                                        return object + "";
                                    }

                                    @Override
                                    public Integer fromString(String string) {
                                        return Integer.parseInt(string);
                                    }
                                }));
                    }
                    break;
                }
            }
        }

//        jfxTreeTableColumn.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        jfxTreeTableColumn.setStyle("-fx-alignment: CENTER");
        jfxTreeTableColumn.setEditable(true);
    }
}

