package com.btl_tkxdpm;

import com.btl_tkxdpm.home.HomeController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        SwitchScreener.switchScreen("/com/btl_tkxdpm/manHinhChinh.fxml");
        SwitchScreener.primaryStage.setTitle("FXML Example");
        SwitchScreener.primaryStage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
