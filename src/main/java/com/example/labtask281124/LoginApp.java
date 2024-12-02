package com.example.labtask281124;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Label userNameLabel = new Label("Username: ");
        TextField userNameTextField = new TextField();
        Label passwordLabel = new Label("Password: ");
        PasswordField passwordTextField = new PasswordField();
        Button loginButton = new Button("Login");
        Button exitButton = new Button("Exit");
        Label notificationLabel = new Label();

        ImageView imageView = new ImageView("login.jpg");
        imageView.setFitWidth(300);
        imageView.setFitHeight(300);

        VBox formLayout = new VBox(10);
        formLayout.getChildren().addAll(
                new HBox(10,userNameLabel,userNameTextField),
                new HBox(10,passwordLabel,passwordTextField),
                new HBox(10,loginButton,exitButton)
        );

        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(imageView,formLayout);
        mainLayout.setStyle("-fx-padding: 10;-fx-alignment: center;");

        Scene scene = new Scene(mainLayout);
        stage.sizeToScene(); // Resizes to fit the layout

        stage.setScene(scene);
        stage.setTitle("Login Application");
        stage.show();

        loginButton.setOnAction(event -> {
            String username = userNameTextField.getText();
            String password = passwordTextField.getText();
            if (validateUser(username, password)) {
                Stage newWindow = new Stage();
                VBox newWindowLayout = new VBox(new Label("Welcome "+username+" !"));
                newWindowLayout.setStyle("-fx-padding: 20;-fx-alignment: center;");
                newWindow.setScene(new Scene(newWindowLayout,200,100));
                newWindow.setTitle("Welcome");
                newWindow.show();
            }else{
                notificationLabel.setText("Invalid Username or Password");
                notificationLabel.setStyle("-fx-text-fill: red;");
            }

        });
    }

    private boolean validateUser(String username, String password) {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/users.txt")))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String [] parts = line.split(":");
                if (parts.length==2 && parts[0].equals(username) && parts[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
        }
        return false;
    }


    public static void main(String[] args) {
        launch(args);
    }

}
