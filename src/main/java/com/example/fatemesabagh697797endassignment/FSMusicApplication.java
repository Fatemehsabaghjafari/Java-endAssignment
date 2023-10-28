package com.example.fatemesabagh697797endassignment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class FSMusicApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Fateme's music Login");
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void stop() throws Exception {
        super.stop();
    }
    public static void main(String[] args) {
        launch();
    }
}