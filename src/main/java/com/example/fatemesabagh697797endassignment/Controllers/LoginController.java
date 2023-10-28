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
    User user;

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
        user = database.getUserByUsernameAndPassword(username, password);
        UserRole role = database.getUserRole(username);
        if (user != null) {
            navigateToMainWindow(username, role);
        }
    }

    private void navigateToMainWindow(String username, UserRole role) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fatemesabagh697797endassignment/MainWindow.fxml"));
            Parent root = loader.load();
            MainWindowController mainController = loader.getController();
            mainController.setUser(user); // Set user here
            mainController.setDatabase(database);
            mainController.welcomeUser(username, role);
            Stage mainStage = new Stage();
            mainStage.setTitle("Fateme's Music shop");
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

