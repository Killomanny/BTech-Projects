package org.example;


import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.ComboBox;
        import javafx.scene.control.DatePicker;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class AdminAddManager {

    @FXML
    private TextField Contactnumber;



    @FXML
    private Button add;

    @FXML
    private Button back;

    @FXML
    private ComboBox<?> combobox;

    @FXML
    private TextField emailid;

    @FXML
    private TextField mname;

    @FXML
    private TextField password;

    @FXML
    private TextField age;

    @FXML
    private DatePicker Dob;


    @FXML
    private Label errorshow;

    @FXML
    private Label toplabel;

    @FXML
    void addtothedevicadduserstabel(ActionEvent event) throws IOException {

        String name =mname.getText();
        String Password = password.getText();
        int Age=Integer.parseInt(age.getText());
        String email = emailid.getText();
        String contact = Contactnumber.getText();
        LocalDate date = Dob.getValue();
        String DOB = date.toString();
        String branch = (String)combobox.getValue();
        String[] names = name.split(" ");

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Manager.class).addAnnotatedClass(Branch.class).addAnnotatedClass(User.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(WorkCondn.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Branch b;
        Manager add = new Manager();
        if(Objects.equals(branch,"Inventory")) {
            b = session.get(Branch.class,1);
            if(b.getManager_id() == null) {
                add.setAge(Age);
                add.setEmail_id(email);
                add.setContact_no(contact);
                add.setFirst_name(names[0]);
                add.setLast_name(names[1]);
                add.setDob(DOB);
                add.setDigest(Password);

                session.persist(add);
                tx.commit();

                List<Manager> temp = session.createQuery("from Manager").list();
                b.setManager_id(session.get(Manager.class,temp.get(temp.size() - 1).getManager_id()));

                session.update(b);

                tx.commit();
                session.close();
                sf.close();

                HelloApplication.loadStage("admin_view_managers.fxml");
            } else {
                errorshow.setText("Inventory manager already present");
            }
        } else if(Objects.equals(branch,"Accounts")) {
            b = session.get(Branch.class,2);
            if(b.getManager_id() == null) {
                add.setAge(Age);
                add.setEmail_id(email);
                add.setContact_no(contact);
                add.setFirst_name(names[0]);
                add.setLast_name(names[1]);
                add.setDob(DOB);
                add.setDigest(Password);

                session.persist(add);
                tx.commit();

                List<Manager> temp = session.createQuery("from Manager").list();
                b.setManager_id(session.get(Manager.class,temp.get(temp.size() - 1).getManager_id()));

                session.update(b);

                tx.commit();
                session.close();
                sf.close();

                HelloApplication.loadStage("admin_view_managers.fxml");
            } else {
                errorshow.setText("Accounts manager already present");
            }
        }





    }

    @FXML
    void backtoviewusers(ActionEvent event) throws IOException {

        HelloApplication.loadStage("admin_view_managers.fxml");

    }

}

