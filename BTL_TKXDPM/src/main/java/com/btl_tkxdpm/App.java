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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/btl_tkxdpm/manHinhChinh.fxml"));
        Parent root = loader.load();

        // Get the controller from the loader
        HomeController controller = loader.getController();

        stage.setTitle("FXML Example");
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
