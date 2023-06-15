package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class UserHomePage {
    @FXML
    private Button history;

    @FXML
    private Button info;

    @FXML
    private Button logout;

    @FXML
    private Button newdevice;

    @FXML
    private Label toppart;

    @FXML
    void gotohomepage(ActionEvent event) throws IOException {
        HelloApplication.loadStage("user_login_page.fxml");
    }

    @FXML
    void openhistory(ActionEvent event) throws IOException {
        HelloApplication.loadStage("user_usage_history.fxml");
    }

    @FXML
    void openinfo(ActionEvent event) throws IOException {
        HelloApplication.loadStage("user_info.fxml");
    }

    @FXML
    void takeadevice(ActionEvent event) throws IOException {
      HelloApplication.loadStage("user_borrow_new_device.fxml");
    }
}
