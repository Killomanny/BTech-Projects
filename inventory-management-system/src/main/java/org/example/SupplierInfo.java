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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SupplierInfo implements Initializable {

    @FXML
    private Button back;

    @FXML
    private Label contactno;

    @FXML
    private Label dos;

    @FXML
    private Label emailid;

    @FXML
    private Label name;

    @FXML
    private Label sid;

    @FXML
    private Label supplierinfo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Device.class).addAnnotatedClass(BorrowDev.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(Manager.class).addAnnotatedClass(Person.class).addAnnotatedClass(Branch.class).addAnnotatedClass(WorkCondn.class).addAnnotatedClass(Supplier.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Supplier temp = session.get(Supplier.class,HelloApplication.login_id);

        name.setText(temp.getSup_name());
        sid.setText(Integer.toString(temp.getSup_id()));
        emailid.setText(temp.getEmail_id());
        contactno.setText(temp.getContact());

        tx.commit();
        session.close();
        sf.close();
    }

    @FXML
    void backtorespectiveafterlogin(ActionEvent event) throws IOException {

         HelloApplication.loadStage("supplier_after_login.fxml");

    }

}