package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ManagerInfo implements Initializable {

    @FXML
    private Button back;

    @FXML
    private Label contactno;

    @FXML
    private Label managerid;

    @FXML
    private Label emailid;

    @FXML
    private Label managerinfo;

    @FXML
    private Label name;

    @FXML
    private Label type;

    private int tempa;

    @Override
     public void initialize(URL url, ResourceBundle resourceBundle) {

        this.tempa = HelloApplication.login_id;

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Device.class).addAnnotatedClass(BorrowDev.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(Manager.class).addAnnotatedClass(Person.class).addAnnotatedClass(Branch.class).addAnnotatedClass(WorkCondn.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Manager m1 = session.get(Manager.class,tempa);
        List<Branch> b = session.createQuery("from Branch where manager_id = " + m1.getManager_id()).list();
        Branch b1 = b.get(0);


        contactno.setText(m1.getContact_no());
        managerid.setText(Integer.toString(m1.getManager_id()));
        emailid.setText(m1.getEmail_id());
        name.setText(m1.getFirst_name()+" "+m1.getLast_name());
        type.setText(b1.getBranch_name());

        tx.commit();
        session.close();
        sf.close();

    }

    @FXML
    void backtorespectiveafterlogin(ActionEvent event) throws IOException {
        if(HelloApplication.login_id == 1) {
            HelloApplication.loadStage("inv_manager_home_page.fxml");
        } else if(HelloApplication.login_id == 2) {
            HelloApplication.loadStage("accounts_after_login.fxml");
        }
    }
}
