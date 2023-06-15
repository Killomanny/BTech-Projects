package org.example;



import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;

import java.io.IOException;

public class AdminHomePage {

    @FXML
    private Button back;

    @FXML
    private Button funds;

    @FXML
    private Button managers;

    @FXML
    private Button suppliers;

    @FXML
    private Button users;

    @FXML
    private Button admininfo;

    @FXML
    void addfunds(ActionEvent event) throws IOException  {

        HelloApplication.loadStage("admin_view_bal.fxml");
    }

    @FXML
    void opensinfo(ActionEvent event) throws IOException {

        HelloApplication.loadStage("admin_info.fxml");

    }


    @FXML
    void backtologinadmin(ActionEvent event) throws IOException {
        HelloApplication.loadStage("admin_login.fxml");

    }

    @FXML
    void viewmanagers(ActionEvent event) throws IOException {
        HelloApplication.loadStage("admin_view_managers.fxml");

    }

    @FXML
    void viewsuppliers(ActionEvent event)throws IOException {
        HelloApplication.loadStage("admin_view_suppliers.fxml");

    }

    @FXML
    void viewusers(ActionEvent event)throws IOException {
        HelloApplication.loadStage("admin_view_users.fxml");

    }

}