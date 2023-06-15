package org.example;


import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.control.PasswordField;
        import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Objects;

public class SupplierLoginPage {

    @FXML
    private Button back;

    @FXML
    private Label error;

    @FXML
    private Button login;

    @FXML
    private Label loginman;

    @FXML
    private PasswordField password;

    @FXML
    private TextField sid;

    @FXML
    void backtoallcatgs(ActionEvent event) throws IOException  {
      HelloApplication.loadStage("log_in_as.fxml");
    }

    @FXML
    void openssuppliersafterlogin(ActionEvent event) throws IOException {
        int userid = Integer.parseInt(sid.getText());
        String Password = password.getText();
        HelloApplication.login_id = userid;

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Person.class).addAnnotatedClass(Manager.class).addAnnotatedClass(Branch.class).addAnnotatedClass(Supplier.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Supplier instance = session.get(Supplier.class,userid);
        if(instance == null) {
            //show error id not found
        } else {
            if(Objects.equals(instance.getDigest(), Password)) {
                HelloApplication.loadStage("supplier_after_login.fxml");
            } else {
                // show error wrong password
                error.setText("Wrong Password");
            }
        }


    }

}
