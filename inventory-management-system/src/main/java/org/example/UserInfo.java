package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.net.URL;

import java.io.IOException;
import java.util.ResourceBundle;

public class UserInfo implements Initializable {

    @FXML
    private Button back;

    @FXML
    private Label contactno;

    @FXML
    private Label dob;

    @FXML
    private Label emailid;

    @FXML
    private Label name;

    @FXML
    private Label uid;

    @FXML
    private Label userinfo;

    @FXML
    void backtorespectiveafterlogin(ActionEvent event) throws IOException {
       HelloApplication.loadStage("user_home_page.fxml");
    }

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle){

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Person.class).addAnnotatedClass(User.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        User temp = session.get(User.class,HelloApplication.login_id);

        uid.setText(Integer.toString(temp.getUser_id()));
        name.setText(temp.getFirst_name()+" "+temp.getLast_name());
        emailid.setText(temp.getEmail_id());
        dob.setText(temp.getDob());
        contactno.setText(temp.getContact_no());

        tx.commit();
        session.close();
        sf.close();

    }
}
