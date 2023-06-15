package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.hibernate.engine.query.spi.ParamLocationRecognizer;

import java.io.IOException;

public class AccountsAfterLogin {

    @FXML
    private Button Logout;

    @FXML
    private Button fines;

    @FXML
    private Button fundsaskedbyinv;

    @FXML
    private Button info;

    @FXML
    private Label label;

    @FXML
    private Button transactionshistory;

    @FXML
    private Button viewbalance;


    @FXML
    void openbalance(ActionEvent event) throws IOException {

        HelloApplication.loadStage("view_balance_acc.fxml");
    }

    @FXML
    void openfinee(ActionEvent event) throws IOException {
        HelloApplication.loadStage("acc_fine_history.fxml");
    }

    @FXML
    void openforapprove(ActionEvent event) throws IOException {

        HelloApplication.loadStage("fund_req_from_inv_manager.fxml");
    }

    @FXML
    void openinfo(ActionEvent event) throws IOException {
        HelloApplication.loadStage("manager_info.fxml");
    }

    @FXML
    void viewtransactionshistory(ActionEvent event) throws IOException {

        HelloApplication.loadStage("acc_purchase_repair_history.fxml");
    }

    @FXML
    void gotohomeinv(ActionEvent event) throws IOException{
      HelloApplication.loadStage("manager_login_page.fxml");
    }

}

