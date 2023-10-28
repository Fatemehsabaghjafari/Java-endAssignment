package com.example.fatemesabagh697797endassignment.Controllers;
import com.example.fatemesabagh697797endassignment.Data.Database;
import com.example.fatemesabagh697797endassignment.Model.User;
import com.example.fatemesabagh697797endassignment.Model.UserRole;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import java.io.IOException;
import javafx.stage.Stage;

public class LoginController {
    Database database;
    @FXML
    private TextField usernameInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private Button loginButton;

    public void initialize() {
        database = new Database();
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String username = usernameInput.getText();
        String password = passwordInput.getText();
        User user = database.getUserByUsernameAndPassword(username, password);
        UserRole role = database.getUserRole(username);
        if (user != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Mainwindow.fxml"));
                Parent root = loader.load();
                MainWindowController mainController = loader.getController();
                mainController.setUser(user);
                mainController.setDatabase(database);
                mainController.welcomeUser(username,role);
                Stage mainStage = new Stage();
                mainStage.setTitle("Fateme's Music shop");
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm()); // Add this line
                mainStage.setScene(scene);
                mainStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

