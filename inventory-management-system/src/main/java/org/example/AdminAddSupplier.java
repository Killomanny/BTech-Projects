package org.example;


import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.DatePicker;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextField;

import java.io.IOException;

public class AdminAddSupplier {

    @FXML
    private TextField Contactnumber;

    @FXML
    private DatePicker Dos;

    @FXML
    private Button add;

    @FXML
    private Button back;

    @FXML
    private TextField emailid;

    @FXML
    private TextField password;

    @FXML
    private Label showserrorifsameemailisused;

    @FXML
    private TextField sname;

    @FXML
    private TextField age;

    @FXML
    private Label toplabel;

    @FXML
    void addtothedevicadduserstabel(ActionEvent event) {

    }

    @FXML
    void backtoviewsuppliers(ActionEvent event) throws IOException {

        HelloApplication.loadStage("admin_view_suppliers.fxml");

    }

}

