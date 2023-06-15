package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ViewBalanceAcc {
    @FXML
    private Button Back;

    @FXML
    private Label finesaccount;

    @FXML
    private Label fundsaccount;

    @FXML
    private AnchorPane fundsavailable;

    @FXML
    private Label top;

    @FXML
    void goestoaccounthome(ActionEvent event) throws IOException {
       HelloApplication.loadStage("accounts_after_login.fxml");
    }
}
