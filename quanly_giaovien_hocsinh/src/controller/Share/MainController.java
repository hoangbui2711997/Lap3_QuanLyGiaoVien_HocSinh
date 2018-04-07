package controller.Share;

import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private AnchorPane primaryPane;

    @FXML
    private AnchorPane paneBottom;

    @FXML
    private JFXDrawer drawBottom;

    @FXML
    private JFXComboBox<?> jfxCombobox;

    @FXML
    private AnchorPane paneMid;

    @FXML
    private JFXDrawer drawMain;

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

            paneBottom.getChildren().add(bottom);
            fitSizeMethod(bottom, 0.0);

            paneTop.getChildren().add(top);
            fitSizeMethod(top, 0.0);

        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }

    private JFXToolbar getJfxToolbar(JFXHamburger hamburger) {
        // Chứa Nav chức năng -
        VBox containerNav = new VBox();

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
        nodeList.addAnimatedNode(btnOption);

        // Add button
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


        fitSizeMethod(toolbar, 15.0);
        return toolbar;
    }

    /**
     *
     * @param pane fitSize of anchorPane truyền vào
     */
    private void fitSizeMethod(Pane pane, double value) {
        AnchorPane.setBottomAnchor(pane, value);
        AnchorPane.setRightAnchor(pane, value);
        AnchorPane.setTopAnchor(pane, value);
        AnchorPane.setLeftAnchor(pane, value);
    }

    private void handleActionHamburger(JFXHamburger hamburger, HamburgerBackArrowBasicTransition transition) {
        // e is event pass through action parameter
        hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            drawLeft.toggle();

            if(drawLeft.isShowing()) {
                AnchorPane.setLeftAnchor(drawMain, 200.0);
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
                AnchorPane.setLeftAnchor(drawMain, 0.0);
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
