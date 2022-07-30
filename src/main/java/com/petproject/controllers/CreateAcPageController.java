package com.petproject.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.petproject.User;
import com.petproject.database.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CreateAcPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ButtonSignUp;

    @FXML
    private DatePicker dateOfBir;

    @FXML
    private TextField lastnamef;

    @FXML
    private TextField loginf;

    @FXML
    private TextField firstnamef;

    @FXML
    private PasswordField passwordf;

    @FXML
    private TextField phone;


    @FXML
    void initialize() {
        ButtonSignUp.setOnAction(event -> {

            try {
                signUpNewUser();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            OpenSignIn("/com/petproject/openPage.fxml");

        });
    }

    public void OpenSignIn(String window){
        ButtonSignUp.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        Label label = new Label("My label");

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    private void signUpNewUser() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();

        String firstName= firstnamef.getText();
        String lastName= lastnamef.getText();
        String password=passwordf.getText();
        String login=loginf.getText();
        int balance=100;
        int bet=0;
        User user = new User(firstName, lastName, login, password);
        try {
            dbHandler.signUpUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
