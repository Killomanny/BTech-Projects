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

public class AdminLogin {

        @FXML
        private TextField adminid;

        @FXML
        private Button back;

        @FXML
        private Label errorshow;

        @FXML
        private Button login;

        @FXML
        private PasswordField password;

        @FXML
        private Label toplabel;

        @FXML
        void backtoall(ActionEvent event) throws IOException {

            HelloApplication.loadStage("log_in_as.fxml");
        }

        @FXML
        void login(ActionEvent event) throws IOException {
                int userid=Integer.parseInt(adminid.getText());
                String Password = password.getText();

                HelloApplication.login_id = userid;

                Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Person.class).addAnnotatedClass(Manager.class).addAnnotatedClass(Branch.class).addAnnotatedClass(Admin.class);
                SessionFactory sf = con.buildSessionFactory();
                Session session = sf.openSession();
                Transaction tx = session.beginTransaction();

                Admin login_instance = new Admin();
                login_instance = session.get(Admin.class, userid);

                if(login_instance != null) {
                        if (Objects.equals(login_instance.getDigest(), Password)) {
                                HelloApplication.loadStage("admin_home_page.fxml");
                        } else {
                                errorshow.setText("Password is wrong");
                        }
                } else {
                        errorshow.setText("UserID not found");
                }

                tx.commit();
                session.close();
                sf.close();


        }

    }


