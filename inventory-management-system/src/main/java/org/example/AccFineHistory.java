package org.example;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AccFineHistory {
    @FXML
    private Button Back;

    @FXML
    private TableColumn<?, ?> ammount;

    @FXML
    private Button clear;

    @FXML
    private TableColumn<?, ?> date;

    @FXML
    private TableView<?> fineaccountstable;

    @FXML
    private TableColumn<?, ?> pid;

    @FXML
    private Button search;

    @FXML
    private TableColumn<?, ?> sid;

    @FXML
    private TableColumn<?, ?> tid;

    @FXML
    private Label toplabel;

    @FXML
    private TextField typesid;

    @FXML
    void gotoacchome(ActionEvent event) throws IOException {
        HelloApplication.loadStage("accounts_after_login.fxml");
    }

    @FXML
    void opensallrelatedtosearch(ActionEvent event) {

    }

    @FXML
    void refreshingthesamepage(ActionEvent event) {

    }

    @FXML
    void searches(ActionEvent event) {

    }
}
