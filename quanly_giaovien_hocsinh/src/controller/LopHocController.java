/**
 * Sample Skeleton for 'Detail.fxml' Controller Class
 */

package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controller.Share.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import model.data.HocSinh;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static controller.Share.MainController.tertiary;

public class LopHocController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    public ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    public URL location;

    @FXML
    public Label lbClassName;

    @FXML // fx:id="tableViewHocSinh"
    public JFXTreeTableView jfxTTVHocSinh; // Value injected by FXMLLoader

    @FXML // fx:id="lbSiSo"
    public Label lbSiSo; // Value injected by FXMLLoader

    @FXML // fx:id="btnCancel"
    public JFXButton btnCancel; // Value injected by FXMLLoader

    public List<HocSinh> lstHocSinh;

    @FXML
    void actionBtnCancel(MouseEvent event) {
        tertiary.close();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert jfxTTVHocSinh != null : "fx:id=\"jfxTTVHocSinh\" was not injected: check your FXML file 'Detail.fxml'.";
        assert lbSiSo != null : "fx:id=\"lbSiSo\" was not injected: check your FXML file 'Detail.fxml'.";
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'Detail.fxml'.";
    }

    public void init() {
        Field[] fields = HocSinh.class.getDeclaredFields();
        JFXTreeTableColumn[] jfxTreeTableColumns;
        MainController mainController = new MainController();
        // 2 truong khong su dung
        String[] strFields = mainController.getAllColumnOfObject(fields);

        // Tạo đồng bộ cho column (Theo tên trường và tên bảng)
        jfxTreeTableColumns = mainController.getJfxTreeTableColumns(strFields, HocSinh.class.getSimpleName(), jfxTTVHocSinh.getWidth());

        ObservableList<HocSinh> data = FXCollections.observableArrayList(
                lstHocSinh
        );

//            TreeItem<giaovien> root = new RecursiveTreeItem(data);

//            TreeItem<giaovien> treeItem = new RecursiveTreeItem(data, null,RecursiveTreeItem::getChildren);
        jfxTTVHocSinh.getColumns().addAll(jfxTreeTableColumns);
        TreeItem<HocSinh> treeHocSinh = new RecursiveTreeItem<HocSinh>(data, RecursiveTreeObject::getChildren);
        jfxTTVHocSinh.setRoot(treeHocSinh);
        jfxTTVHocSinh.setShowRoot(false);
        lbSiSo.setText(lbSiSo.getText() + lstHocSinh.size());
    }
}
