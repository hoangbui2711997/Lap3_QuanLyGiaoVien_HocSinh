package controller.Share;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.data.*;
import model.database.DB_Connection;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.zip.GZIPInputStream;

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

    private static Object key = new Object();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // Khởi tạo --
//            fitSizeMethod(treeTableView, 0.0);
            // Khoi tao ket noi
            DB_Connection.getConnection();


            setSelectCombobox();


            // Để mặc định tàng hình
            drawLeft.setVisible(true);
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Share/LeftPane.fxml"));
            drawLeft.setSidePane(anchorPane);

            JFXHamburger hamburger = new JFXHamburger();
            hamburger.setCursor(Cursor.HAND);

            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
            // setRate equal -1 is not active
            transition.setRate(-1);

            // Xử lý action handleActionHamburger
            handleActionHamburger(hamburger, transition);


//            AnchorPane top = FXMLLoader.load(getClass().getResource("../../view/Share/TopPane.fxml"));
            AnchorPane top = new AnchorPane();

            AnchorPane bottom = FXMLLoader.load(getClass().getResource("/view/Share/BottomPane.fxml"));
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


            // Action handle here
            jfxCombobox.setOnAction(e -> {
                try {
                    handleActionJFXComboboxSwitchTable();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            });
        } catch (Exception e) {
            System.out.println(e.getCause());
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

        // Tạo nodeList chứa các node
        JFXNodesList nodeList = new JFXNodesList();

        // Tạo subList
        JFXNodesList jfxNodesList = new JFXNodesList();
        // Add và set hiệu ứng animation
//            nodeList.addAnimatedNode(btnOption, (expanded, duration) ->
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
        nodeList.addAnimatedNode(btnHome);

        // Add button

        jfxNodesList.addAnimatedNode(btnOption, (expanded, duration) -> {
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
        jfxNodesList.addAnimatedNode(btnAdd);
        jfxNodesList.addAnimatedNode(btnUpdate);
        jfxNodesList.addAnimatedNode(btnDel);

        // Đặt khoảng cách giữa 2 button
        jfxNodesList.setSpacing(85);

        // Độ quay của jfxNodeList
        jfxNodesList.setRotate(270);
        nodeList.setRotate(270);
        nodeList.setSpacing(85);

        // jfxNodeList là con của thằng NodeList
        nodeList.addAnimatedNode(jfxNodesList);
//            AnchorPane.setLeftAnchor();
        containerNav.getChildren().add(nodeList);
        containerNav.setPadding(new Insets(8, 0, 8, 15));

        JFXToolbar toolbar = new JFXToolbar();
        toolbar.setCenter(containerNav);
        toolbar.setRight(hamburger);


        toolbar.setPadding(new Insets(15, 10, 15, 5));
        toolbar.setStyle("-fx-border-style: solid; -fx-border-radius: 5px;");


        // --------------- handle action btn
        btnAdd.setOnAction(event -> {
            try {
                actionBtn_Add();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        fitSizeMethod(toolbar, 15.0);
        return toolbar;
    }

    public static Object lock = new Object();

    public static Stage secondaryStage;

    private void actionBtn_Add() throws IOException, InterruptedException {
        if ("Giáo viên".equals(jfxCombobox.getValue())) {
            Parent root = FXMLLoader.load(getClass().getResource("/view/GiaoVien/Add.fxml"));
            secondaryStage = new Stage();
            secondaryStage.setScene(new Scene(root, 600, 549));
            secondaryStage.setResizable(false);
            secondaryStage.showAndWait();
            secondaryStage.close();
        }
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

    private void setSelectCombobox() {
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
    }

    private String getName(String name) {

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

    private void handleActionJFXComboboxSwitchTable() throws SQLException {
        treeTableView.getColumns().clear();
        Field[] fields;
        JFXTreeTableColumn[] jfxTreeTableColumns = null;

        // bảng chọn
        String tableSelected = jfxCombobox.getValue();
        if ("Giáo viên".equals(tableSelected)) {
            fields = GiaoVien.class.getDeclaredFields();
            // 2 truong khong su dung
            String[] strFields = getAllColumnOfObject(fields);
            // Tạo đồng bộ cho column (Theo tên trường và tên bảng)
            jfxTreeTableColumns = getJfxTreeTableColumns(strFields, GiaoVien.class.getSimpleName());


            List<GiaoVien> list = GiaoVien.Search.getAll();
            if(list == null) {
                list = new ArrayList<>();
            }

            ObservableList<GiaoVien> data = FXCollections.observableArrayList(
                    list
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


            List<XepLop> list = XepLop.Search.getAll();
            if(list == null) {
                list = new ArrayList<>();
            }

            ObservableList<XepLop> data = FXCollections.observableArrayList(
                    list
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


            List<Diem> list = Diem.Search.getAll();
            if(list == null) {
                list = new ArrayList<>();
            }

            ObservableList<Diem> data = FXCollections.observableArrayList(
                    list
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


            List<HocKy> list = HocKy.Search.getAll();
            if(list == null) {
                list = new ArrayList<>();
            }

            ObservableList<HocKy> data = FXCollections.observableArrayList(
                    list
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


            List<HocSinh> list = HocSinh.Search.getAll();
            if(list == null) {
                list = new ArrayList<>();
            }

            ObservableList<HocSinh> data = FXCollections.observableArrayList(
                    list
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


            List<KhoiHoc> list = KhoiHoc.Search.getAll();
            if(list == null) {
                list = new ArrayList<>();
            }

            ObservableList<KhoiHoc> data = FXCollections.observableArrayList(
                    list
            );

//            TreeItem<KhoiHoc> root = new RecursiveTreeItem(data);

//            TreeItem<KhoiHoc> treeItem = new RecursiveTreeItem(data, null,RecursiveTreeItem::getChildren);
            TreeItem<KhoiHoc> root = new RecursiveTreeItem<KhoiHoc>(data, RecursiveTreeObject::getChildren);
            treeTableView.setRoot(root);
        } else if ("Lớp học".equals(tableSelected)) {
            fields = LopHoc.class.getDeclaredFields();
            // 2 truong khong xu dung
            String[] strFields = getAllColumnOfObject(fields);
            // Tạo đồng bộ cho column (Theo tên trường và tên bảng)
            jfxTreeTableColumns = getJfxTreeTableColumns(strFields, LopHoc.class.getSimpleName());


            List<LopHoc> list = LopHoc.Search.getAll();
            if(list == null) {
                list = new ArrayList<>();
            }

            if(list == null) {
                list = new ArrayList<>();
            }

            ObservableList<LopHoc> data = FXCollections.observableArrayList(
                    list
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


            List<MonHoc> list = MonHoc.Search.getAll();
            if(list == null) {
                list = new ArrayList<>();
            }

            ObservableList<MonHoc> data = FXCollections.observableArrayList(
                    list
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


            List<NamHoc> list = NamHoc.Search.getAll();
            if(list == null) {
                list = new ArrayList<>();
            }

            ObservableList<NamHoc> data = FXCollections.observableArrayList(
                    list
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


            List<PhanCong> list = PhanCong.Search.getAll();
            if(list == null) {
                list = new ArrayList<>();
            }

            ObservableList<PhanCong> data = FXCollections.observableArrayList(
                    list
            );

//            TreeItem<PhanCong> root = new RecursiveTreeItem(data);

//            TreeItem<PhanCong> treeItem = new RecursiveTreeItem(data, null,RecursiveTreeItem::getChildren);
            TreeItem<PhanCong> root = new RecursiveTreeItem<PhanCong>(data, RecursiveTreeObject::getChildren);
            treeTableView.setRoot(root);
        }

        treeTableView.getColumns().addAll(jfxTreeTableColumns);
    }

    private String[] getAllColumnOfObject(Field[] fields) {
        String[] strFields = new String[fields.length - 2];
        int i = 0;
        for (Field field : fields) {
            String name = getName(field.toString());
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

//                System.out.println(treeTableView.getWidth());

                jfxTreeTableColumns[j].setPrefWidth(treeTableView.getWidth() / jfxTreeTableColumns.length);
                jfxTreeTableColumns[j].setMaxWidth(200.0);
                jfxTreeTableColumns[j].setCellValueFactory(
                        new TreeItemPropertyValueFactory<GiaoVien, String>(strFields[j])
                );
            }
        } else if (object.equals("XepLop")) {
            for (int j = 0; j < jfxTreeTableColumns.length; j++) {
                jfxTreeTableColumns[j] = new JFXTreeTableColumn(strFields[j]);

                jfxTreeTableColumns[j].setPrefWidth(treeTableView.getWidth() / jfxTreeTableColumns.length);
                jfxTreeTableColumns[j].setMaxWidth(200.0);
                jfxTreeTableColumns[j].setCellValueFactory(
                        new TreeItemPropertyValueFactory<XepLop, String>(strFields[j])
                );
            }
        } else if (object.equals("Diem")) {
            for (int j = 0; j < jfxTreeTableColumns.length; j++) {
                jfxTreeTableColumns[j] = new JFXTreeTableColumn(strFields[j]);

                jfxTreeTableColumns[j].setPrefWidth(treeTableView.getWidth() / jfxTreeTableColumns.length);
                jfxTreeTableColumns[j].setMaxWidth(200.0);
                jfxTreeTableColumns[j].setCellValueFactory(
                        new TreeItemPropertyValueFactory<Diem, String>(strFields[j])
                );
            }
        } else if (object.equals("HocKy")) {
            for (int j = 0; j < jfxTreeTableColumns.length; j++) {
                jfxTreeTableColumns[j] = new JFXTreeTableColumn(strFields[j]);

                jfxTreeTableColumns[j].setPrefWidth(treeTableView.getWidth() / jfxTreeTableColumns.length);
                jfxTreeTableColumns[j].setMaxWidth(200.0);
                jfxTreeTableColumns[j].setCellValueFactory(
                        new TreeItemPropertyValueFactory<HocKy, String>(strFields[j])
                );
            }
        } else if (object.equals("HocSinh")) {
            for (int j = 0; j < jfxTreeTableColumns.length; j++) {
                jfxTreeTableColumns[j] = new JFXTreeTableColumn(strFields[j]);

                jfxTreeTableColumns[j].setPrefWidth(treeTableView.getWidth() / jfxTreeTableColumns.length);
                jfxTreeTableColumns[j].setMaxWidth(200.0);
                jfxTreeTableColumns[j].setCellValueFactory(
                        new TreeItemPropertyValueFactory<HocSinh, String>(strFields[j])
                );
            }
        } else if (object.equals("KhoiHoc")) {
            for (int j = 0; j < jfxTreeTableColumns.length; j++) {
                jfxTreeTableColumns[j] = new JFXTreeTableColumn(strFields[j]);

                jfxTreeTableColumns[j].setPrefWidth(treeTableView.getWidth() / jfxTreeTableColumns.length);
                jfxTreeTableColumns[j].setMaxWidth(200.0);
                jfxTreeTableColumns[j].setCellValueFactory(
                        new TreeItemPropertyValueFactory<KhoiHoc, String>(strFields[j])
                );
            }
        } else if (object.equals("LopHoc")) {
            for (int j = 0; j < jfxTreeTableColumns.length; j++) {
                jfxTreeTableColumns[j] = new JFXTreeTableColumn(strFields[j]);

                jfxTreeTableColumns[j].setPrefWidth(treeTableView.getWidth() / jfxTreeTableColumns.length);
                jfxTreeTableColumns[j].setMaxWidth(200.0);
                jfxTreeTableColumns[j].setCellValueFactory(
                        new TreeItemPropertyValueFactory<LopHoc, String>(strFields[j])
                );
            }
        } else if (object.equals("MonHoc")) {
            for (int j = 0; j < jfxTreeTableColumns.length; j++) {
                jfxTreeTableColumns[j] = new JFXTreeTableColumn(strFields[j]);

                jfxTreeTableColumns[j].setPrefWidth(treeTableView.getWidth() / jfxTreeTableColumns.length);
                jfxTreeTableColumns[j].setMaxWidth(200.0);
                jfxTreeTableColumns[j].setCellValueFactory(
                        new TreeItemPropertyValueFactory<MonHoc, String>(strFields[j])
                );
            }
        } else if (object.equals("NamHoc")) {
            for (int j = 0; j < jfxTreeTableColumns.length; j++) {
                jfxTreeTableColumns[j] = new JFXTreeTableColumn(strFields[j]);

                jfxTreeTableColumns[j].setPrefWidth(treeTableView.getWidth() / jfxTreeTableColumns.length);
                jfxTreeTableColumns[j].setMaxWidth(200.0);
                jfxTreeTableColumns[j].setCellValueFactory(
                        new TreeItemPropertyValueFactory<NamHoc, String>(strFields[j])
                );
            }
        } else if (object.equals("PhanCong")) {
            for (int j = 0; j < jfxTreeTableColumns.length; j++) {
                jfxTreeTableColumns[j] = new JFXTreeTableColumn(strFields[j]);

                jfxTreeTableColumns[j].setPrefWidth(treeTableView.getWidth() / jfxTreeTableColumns.length);
                jfxTreeTableColumns[j].setMaxWidth(200.0);
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

            if (drawLeft.isShowing()) {
                AnchorPane.setLeftAnchor(paneMain, 200.0);
                AnchorPane.setLeftAnchor(drawBottom, 200.0);
                AnchorPane.setLeftAnchor(paneTop, 200.0);

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
                AnchorPane.setLeftAnchor(paneMain, 0.0);
                AnchorPane.setLeftAnchor(drawBottom, 0.0);
                AnchorPane.setLeftAnchor(paneTop, 0.0);
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
}
