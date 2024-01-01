package com.btl_tkxdpm;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SwitchScreener {
    public static final Stage primaryStage = new Stage();


    public static void switchScreen(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(SwitchScreener.class.getResource(fxmlFileName));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
