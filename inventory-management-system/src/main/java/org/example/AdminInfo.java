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

public class AdminInfo implements Initializable {

    @FXML
    private Label adminid;

    @FXML
    private Label admininfo;

    @FXML
    private Button back;

    @FXML
    private Label contactno;

    @FXML
    private Label emailid;

    @FXML
    private Label name;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Person.class).addAnnotatedClass(Manager.class).addAnnotatedClass(Branch.class).addAnnotatedClass(Admin.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Admin info = session.get(Admin.class,HelloApplication.login_id);

        adminid.setText(Integer.toString(info.getAdmin_id()));
        contactno.setText(info.getContact_no());
        emailid.setText(info.getEmail_id());
        name.setText(info.getFirst_name()+" "+info.getLast_name());

    }

    @FXML
    void backtoadminhome(ActionEvent event) throws IOException {

     HelloApplication.loadStage("admin_home_page.fxml");

    }

}

