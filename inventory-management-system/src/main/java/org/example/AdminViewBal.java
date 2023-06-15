package org.example;



import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextField;

import java.io.IOException;

public class AdminViewBal {

    @FXML
    private Button addbalance;

    @FXML
    private TextField addingbalance;

    @FXML
    private Button back;

    @FXML
    private Label currentbalance;

    @FXML
    void addsbalance(ActionEvent event) {

    }

    @FXML
    void backtoadminhome(ActionEvent event) throws IOException {
        HelloApplication.loadStage("admin_home_page.fxml");

    }

}
