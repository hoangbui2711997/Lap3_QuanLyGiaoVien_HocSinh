package controller;

import controller.Share.MainController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Layout;
import model.data.GiaoVien;
import model.database.DB_Connection;
import model.repository.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainApp extends Application implements Initializable {

    private static Layout layout;
    private static Stage primaryStage = new Stage();

    @Override
    public void start(Stage primaryStageX) throws IOException, InterruptedException {
        Runnable runnable = new Runnable() {
            public void run() {
                DB_Connection.getConnection();
//                System.out.println(RepositoryKhoiHoc.getAll().size());
//                System.out.println(RepositoryNamHoc.getAll().size());
//                System.out.println(RepositoryHocKy.getAll().size());
//                System.out.println(RepositoryHocSinh.getAll().size());
//                System.out.println(RepositoryMonHoc.getAll().size());
//                System.out.println(RepositoryGiaoVien.getAll().size());
//                System.out.println(RepositoryLopHoc.getAll().size());
//                System.out.println(RepositoryXepLop.getAll().size());
//                System.out.println(RepositoryPhanCong.getAll().size());
//                System.out.println(RepositoryDiem.getAll().size());
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        layout = Layout.LAYOUT_MAIN;
        System.out.println(this.getClass().getName());
        System.out.println(this.getClass());
        makeForm(Layout.LAYOUT_LOGIN, null);
    }

    public static void makeForm(Layout layoutMain, GiaoVien admin) throws IOException {
        Thread thread1 = new Thread(() -> {
            layout = layoutMain;
            if (layout.equals(Layout.LAYOUT_LOGIN)) {
                try {
                    Parent root = getLogin();
                    Platform.runLater(() -> {
                        primaryStage.setScene(new Scene(root, 1000, 600));
//        primaryStage.setFullScreen(true);
                        primaryStage.setResizable(true);
                        primaryStage.show();
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (layout.equals(Layout.LAYOUT_MAIN)) {

                    RepositoryKhoiHoc.getAll();
                    RepositoryNamHoc.getAll();
                    RepositoryHocKy.getAll();
                    RepositoryHocSinh.getAll();
                    RepositoryMonHoc.getAll();
                    RepositoryGiaoVien.getAll();
                    RepositoryLopHoc.getAll();
                    RepositoryXepLop.getAll();
                    RepositoryPhanCong.getAll();
                    RepositoryDiem.getAll();

                Platform.runLater(() -> {
                    try {
                        FXMLLoader main = new FXMLLoader(MainApp.class.getResource("/view/Main.fxml"));
                        Parent root = main.load();

                        MainController mainController = main.getController();

                        mainController.admin = admin;
                        mainController.init();

                        primaryStage.setScene(new Scene(root, 1000, 600));
//        primaryStage.setFullScreen(true);
                        primaryStage.setResizable(true);
                        primaryStage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        });
        thread1.start();
    }


    /**
     * @throws IOException
     */
    private static Parent getLogin() throws IOException {
        return FXMLLoader.load(MainApp.class.getResource("/view/login/Login.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("view/share/Composition.fxml"));

        //        leftPane =

//        topPane =
//        topPane = FXMLLoader.load(getClass().getResource("view/share/TopPane.fxml"));

//        homePane =
//        homePane = FXMLLoader.load(getClass().getResource("view/share/HomePane.fxml"));

//        bottomPane =
//        bottomPane = FXMLLoader.load(getClass().getResource("view/share/BottomPane.fxml"));

//        Scene scene = new Scene(root, 800, 600);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
