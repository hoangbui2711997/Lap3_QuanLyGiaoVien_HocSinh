/**
 * Sample Skeleton for 'Detail.fxml' Controller Class
 */

package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import model.data.HocSinh;

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
    public TableView<?> tableViewHocSinh; // Value injected by FXMLLoader

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
        assert tableViewHocSinh != null : "fx:id=\"tableViewHocSinh\" was not injected: check your FXML file 'Detail.fxml'.";
        assert lbSiSo != null : "fx:id=\"lbSiSo\" was not injected: check your FXML file 'Detail.fxml'.";
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'Detail.fxml'.";
    }
}
