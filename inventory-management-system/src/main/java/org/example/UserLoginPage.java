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
import java.util.List;
import java.util.Objects;

public class UserLoginPage {
    @FXML
    private Label error;

    @FXML
    private Button exitbutton;

    @FXML
    private Button login;

    @FXML
    private Label loginman;

    @FXML
    private PasswordField password;

    @FXML
    private TextField uid;

    @FXML
    void exitapp(ActionEvent event) throws IOException {
       HelloApplication.loadStage("log_in_as.fxml");
    }

    @FXML
    void openusersloginpage(ActionEvent event) throws IOException {

        int id = Integer.parseInt(uid.getText());
        String passw = password.getText();

        HelloApplication.login_id = id;

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Person.class).addAnnotatedClass(User.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        User login_instance = new User();
        login_instance = session.get(User.class, id);

        if (login_instance != null) {
            if (Objects.equals(login_instance.getDigest(), passw)) {
                HelloApplication.loadStage("user_home_page.fxml");
            } else {
                error.setText("Password is wrong");
            }
        } else {
            error.setText("UserID not found");
        }

        tx.commit();
        session.close();
        sf.close();

    }
}
