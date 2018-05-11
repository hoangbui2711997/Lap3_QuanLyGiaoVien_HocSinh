package controller;

import com.jfoenix.controls.JFXButton;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.function.BiFunction;

import com.jfoenix.controls.JFXNodesList;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import javax.security.auth.callback.Callback;
import javax.xml.crypto.dsig.keyinfo.KeyValue;

public class ManageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchor;

    @FXML
    private JFXButton buttonPattern;

    @FXML
    void initialize() {
//        assert anchor != null : "fx:id=\"anchor\" was not injected: check your FXML file 'Home.fxml'.";
//        assert buttonPattern != null : "fx:id=\"buttonPattern\" was not injected: check your FXML file 'Home.fxml'.";
//        JFXButton button1 = new JFXButton("Hello");
//        button1.setStyle(buttonPattern.getStyle());
//
//        JFXButton button2 = new JFXButton("Hello");
//        button1.setStyle(buttonPattern.getStyle());
//
//        JFXButton button3 = new JFXButton("Hello");
//        button1.setStyle(buttonPattern.getStyle());
//
//        JFXButton button4 = new JFXButton("Hello");
//        button1.setStyle(buttonPattern.getStyle());
//
//        JFXButton button5 = new JFXButton("Hello");
//        button1.setStyle(buttonPattern.getStyle());
//
//        JFXNodesList nodesList0 = new JFXNodesList();
//        nodesList0.addAnimatedNode(button1);
//        nodesList0.addAnimatedNode(button2);
//        nodesList0.addAnimatedNode(button3);
//
//
//        JFXNodesList nodesList = new JFXNodesList();
//        nodesList.addAnimatedNode(button4, new BiFunction<Boolean, Duration, Collection<KeyFrame>>() {
//            @Override
//            public Collection<KeyFrame> apply(Boolean expanded, Duration duration) {
//                return new ArrayList<KeyFrame>() {
//                    {
//                        add(new KeyFrame(Duration.millis(500), new javafx.animation.KeyValue(button4.rotateProperty(),
//                                expanded ? 360 : 0, Interpolator.EASE_BOTH)));
//                    }
//                };
//            }
//        });
//
//        nodesList.addAnimatedNode(button5);
//        nodesList.addAnimatedNode(nodesList0);

        JFXButton btnOption = new JFXButton("Tùy chọn");
        btnOption.setStyle("-fx-background-color: #3b5998; -fx-background-radius: 50px; -fx-border-radius: 50px;");
        btnOption.setMinSize(110, 45);

        JFXButton btnUpdate = new JFXButton("Cập nhật");
        btnUpdate.setStyle("-fx-background-color: #00C851; -fx-background-radius: 50px; -fx-border-radius: 50px;");
        btnUpdate.setMinSize(110, 45);

        JFXButton btnAdd = new JFXButton("Thêm");
        btnAdd.setStyle("-fx-background-color: #4285F4; -fx-background-radius: 50px; -fx-border-radius: 50px;");
        btnAdd.setMinSize(110, 45);

        JFXButton btnDel = new JFXButton("Xóa");
        btnDel.setStyle("-fx-background-color: #ff4444; -fx-background-radius: 50px; -fx-border-radius: 50px;");
        btnDel.setMinSize(110, 45);
        JFXNodesList nodeList = new JFXNodesList();

        JFXNodesList jfxNodesList = new JFXNodesList();
        nodeList.addAnimatedNode(btnOption, (expanded, duration) ->
                new ArrayList<>() {
                    {
                        add(new KeyFrame(duration,
                                new javafx.animation.KeyValue(
                                        btnOption.rotateProperty(),
                                        expanded ? 360 : 0,
                                        Interpolator.EASE_BOTH
                                )));
                    }
                }
        );
        jfxNodesList.addAnimatedNode(btnAdd);
        jfxNodesList.addAnimatedNode(btnUpdate);
        jfxNodesList.addAnimatedNode(btnDel);
        jfxNodesList.setSpacing(75);
        nodeList.setSpacing(25);
        jfxNodesList.setRotate(270);

        nodeList.addAnimatedNode(jfxNodesList);
        anchor.getChildren().add(nodeList);
    }
}
