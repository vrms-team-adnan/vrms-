package com.vrms.presentation.gui.fx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The JavaFX login screen. On successful login, opens the MainScreen.
 */
public class LoginScreen {

    private final ServiceBundle bundle;
    private final Stage stage;

    public LoginScreen(ServiceBundle bundle, Stage stage) {
        this.bundle = bundle;
        this.stage = stage;
    }

    public void show() {
        Label title = new Label("VRMS");
        title.getStyleClass().add("app-title");

        Label subtitle = new Label("Vehicle Rental Management System");
        subtitle.getStyleClass().add("app-subtitle");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginButton = new Button("Sign In");
        loginButton.getStyleClass().add("btn-primary");
        loginButton.setMaxWidth(Double.MAX_VALUE);

        VBox card = new VBox(14);
        card.setAlignment(Pos.CENTER);
        card.setMaxWidth(320);
        card.getStyleClass().add("login-card");
        card.getChildren().addAll(title, subtitle, spacer(10), usernameField, passwordField, loginButton);

        Runnable attemptLogin = () -> {
            boolean success = bundle.getAuthService().login(usernameField.getText().trim(), passwordField.getText());
            if (success) {
                new MainScreen(bundle, stage).show();
            } else {
                showError("Invalid username or password.");
                passwordField.clear();
            }
        };

        loginButton.setOnAction(e -> attemptLogin.run());
        passwordField.setOnAction(e -> attemptLogin.run());

        StackPane root = new StackPane(card);
        root.getStyleClass().add("login-container");
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 480, 420);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        stage.setTitle("VRMS - Login");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private VBox spacer(double height) {
        VBox box = new VBox();
        box.setPrefHeight(height);
        return box;
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.setHeaderText(null);
        alert.setTitle("Login Failed");
        alert.showAndWait();
    }
}