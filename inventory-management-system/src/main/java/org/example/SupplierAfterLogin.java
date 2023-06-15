package org.example;


import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;

import java.io.IOException;

public class SupplierAfterLogin {

    @FXML
    private Button devices;

    @FXML
    private Button history;

    @FXML
    private Button info;

    @FXML
    private Button logout;

    @FXML
    private Label maintitle;

    @FXML
    void logoutstologinpage(ActionEvent event) throws IOException {

        HelloApplication.loadStage("supplier_login_page.fxml");
    }

    @FXML
    void openalldevices(ActionEvent event) throws IOException {

        HelloApplication.loadStage("supplier_all_devices.fxml");
    }

    @FXML
    void opensinfo(ActionEvent event)  throws IOException {

        HelloApplication.loadStage("supplier_info.fxml");

    }

    @FXML
    void showshistory(ActionEvent event) throws IOException {

      HelloApplication.loadStage("supplier_sale_history.fxml");

    }

}
