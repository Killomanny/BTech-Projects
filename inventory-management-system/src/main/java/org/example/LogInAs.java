package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInAs {
    @FXML
    private Button manager;

    @FXML
    private Label toplabel;

    @FXML
    private Button user;

    @FXML
    private Button exit;

    @FXML
    private Button admin;

    @FXML
    private Button supplier;

    @FXML
    void loginasmanager(ActionEvent event) throws IOException {
        HelloApplication.loadStage("manager_login_page.fxml");
    }

    @FXML
    void loginasuser(ActionEvent event) throws IOException{
        HelloApplication.loadStage("user_login_page.fxml");
    }


    @FXML
    void opensadmin(ActionEvent event) throws IOException{
        HelloApplication.loadStage("admin_login.fxml");

    }

    @FXML
    void openssupplierlopgin(ActionEvent event) throws IOException {
        HelloApplication.loadStage("supplier_login_page.fxml");

    }

    @FXML
    void exittheapp(ActionEvent event) throws IOException {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }


}
