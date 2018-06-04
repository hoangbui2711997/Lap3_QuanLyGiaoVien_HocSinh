//package controller.share;
//
//import com.jfoenix.animation.JFXNodesAnimation;
//import com.jfoenix.controls.JFXButton;
//import com.jfoenix.controls.JFXHamburger;
//import com.jfoenix.controls.JFXNodesList;
//import com.jfoenix.controls.JFXToolbar;
//import javafx.animation.Interpolator;
//import javafx.animation.KeyFrame;
//import javafx.animation.KeyValue;
//import javafx.application.Platform;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.print.PageLayout;
//import javafx.scene.layout.VBox;
//import javafx.util.Duration;
//
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.ResourceBundle;
//import java.util.function.BiFunction;
//
//public class ShareViewController {
//
//    @FXML
//    private JFXToolbar toolbar;
//
//    @FXML
//    private JFXHamburger hamburger;
//
//    @FXML
//    private VBox vBox;
//
//    @FXML
//    private JFXNodesList nodesList;
//
//    @FXML
//    private JFXButton btnRootNodeList;
//
//    @FXML
//    private JFXButton btnUpdatetNodeList;
//
//    @FXML
//    private JFXButton btnAddNodeList;
//
//    @FXML
//    private JFXButton btnDelNodeList;
//
//    @FXML
//    void toggleOption(ActionEvent event) {
//
////        System.out.println(this.nodesList == null ? "Null" : "Not Null");
////        boolean toggle = this.nodesList.isExpanded();
////        Platform.runLater(() -> {
////            if(this.nodesList != null) {
////                System.out.println(nodesList.isExpanded());
////                this.nodesList.animateList(!toggle);
////            }
////        });
//    }
//
////    @FXML
////    private JFXToolbar toolbar;
////
////    @FXML
////    private JFXHamburger hamburger;
////
////    @FXML
////    private JFXNodesList nodesList;
////
////    @FXML
////    private VBox vBox;
////
////    @FXML
////    public void initialize(URL location, ResourceBundle resources) {
//////        nodeList.addAnimatedNode(btnRootNodeList);
//////        JFXNodesList nodeList1 = new JFXNodesList();
//////        nodeList1.addAnimatedNode(btnAddNodeList);
//////        nodeList1.addAnimatedNode(btnUpdatetNodeList);
//////        nodeList1.addAnimatedNode(btnDelNodeList);
//////
//////        nodeList.addAnimatedNode(nodeList1);
////
////
////        JFXButton btnOption = new JFXButton("Tùy chọn");
////        btnOption.setStyle("-fx-background-color: #3b5998; -fx-background-radius: 50px; -fx-border-radius: 50px;");
////        btnOption.setMinSize(110, 45);
////
////        JFXButton btnUpdate = new JFXButton("Cập nhật");
////        btnUpdate.setStyle("-fx-background-color: #00C851; -fx-background-radius: 50px; -fx-border-radius: 50px;");
////        btnUpdate.setMinSize(110, 45);
////
////        JFXButton btnAdd = new JFXButton("Thêm");
////        btnAdd.setStyle("-fx-background-color: #4285F4; -fx-background-radius: 50px; -fx-border-radius: 50px;");
////        btnAdd.setMinSize(110, 45);
////
////        JFXButton btnDel = new JFXButton("Xóa");
////        btnDel.setStyle("-fx-background-color: #ff4444; -fx-background-radius: 50px; -fx-border-radius: 50px;");
////        btnDel.setMinSize(110, 45);
////
//////        nodeList.addAnimatedNode(btnOption, (new BiFunction<Boolean, Duration, Collection<KeyFrame>>() {
//////            @Override
//////            public Collection<KeyFrame> apply(Boolean aBoolean, Duration duration) {
//////                return new ArrayList<>() {
//////                    @Override
//////                    public boolean add(KeyFrame keyFrame) {
//////                        return super.add(
//////                                new KeyFrame(
//////                                        duration,
//////                                        new KeyValue(
//////                                                btnOption.rotateProperty(),
//////                                                aBoolean ? 360 : 0,
//////                                                Interpolator.EASE_BOTH)
//////                                ));
//////                    }
//////                };
//////            }
//////        }));
////
//////        JFXNodesList nodeList = new JFXNodesList();
//////        nodeList.addAnimatedNode(btnOption, (expanded, duration) ->
//////            new ArrayList<>() {
//////                {
//////                    add(new KeyFrame(duration,
//////                            new KeyValue(
//////                                    btnOption.rotateProperty(),
//////                                    expanded ? 360 : 0,
//////                                    Interpolator.EASE_BOTH
//////                                    )));
//////                }
//////            }
//////        );
////
////        nodesList.addAnimatedNode(btnOption);
//////        JFXNodesList jfxNodesList = new JFXNodesList();
////        nodesList.addAnimatedNode(btnAdd);
////        nodesList.addAnimatedNode(btnUpdate);
////        nodesList.addAnimatedNode(btnDel);
////
//////        nodesList.addAnimatedNode(jfxNodesList);
//////        nodesList.setSpacing(75);
//////        vBox.getChildren().add(nodesList);
//////        nodeList.addAnimatedNode(nodeList1, (aBoolean, duration) -> {
//////            return new ArrayList<>() {
//////                {
////////                    add(new KeyFrame(Duration.millis(500), new KeyValue(aBoolean, Interpolator.EASE_BOTH)));
//////                }
//////            };
//////        });
////    }
//}

package controller.Share;

import com.jfoenix.controls.JFXButton;
import controller.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Layout;
import model.data.GiaoVien;
import model.repository.GlobalRepository;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ShareViewController implements Initializable {

    @FXML
    public Label lbNameUser;

    @FXML
    public JFXButton btnUpdateDB;

    @FXML
    public JFXButton btnExit;

    @FXML
    public Label lbRole;

    @FXML
    public JFXButton btnThongTin;

    @FXML
    public ImageView imgUser;

    public GiaoVien admin;

    @FXML
    void actionBtnExit(ActionEvent event) throws IOException {
        MainApp.makeForm(Layout.LAYOUT_LOGIN, null);
    }

    public void synDatabase() throws SQLException {
        GlobalRepository.synToDataBase();
    }


    @FXML
    void actionBtnInfoAdmin(ActionEvent event) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void init() {
        lbRole.setText("Role: " + (admin.getRole() == 1 ? "Admin" : "Client"));
        lbNameUser.setText(admin.getHoTen());
    }

    public void actionThongKe(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/ThongKe.fxml"));

        MainController.secondaryStage = new Stage();
        MainController.secondaryStage.setScene(new Scene(root, 800, 735));
        MainController.secondaryStage.showAndWait();
    }
}
