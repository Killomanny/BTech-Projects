package org.example;






import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.DatePicker;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class AdminAddUsers {

    @FXML
    private TextField age;

    @FXML
    private TextField Contactnumber;

    @FXML
    private Button add;

    @FXML
    private Button back;

    @FXML
    private DatePicker dob;

    @FXML
    private TextField modemailidl;

    @FXML
    private TextField password;

    @FXML
    private Label showserrorifsameemailisused;

    @FXML
    private Label toplabel;

    @FXML
    private TextField uname;

    @FXML
    void addtothedevicadduserstabel(ActionEvent event) throws  IOException {

        String contact = Contactnumber.getText();
        String mail = modemailidl.getText();
        String Password = password.getText();
        String name = uname.getText();
        LocalDate date = dob.getValue();
        int Age = Integer.parseInt(age.getText());
        String date_of_birth = date.toString();

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(UserHistory.class).addAnnotatedClass(Device.class).addAnnotatedClass(User.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(WorkCondn.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        User add = new User();
        String[] names = name.split(" ");
        add.setFirst_name(names[0]);
        add.setLast_name(names[1]);
        add.setDigest(Password);
        add.setEmail_id(mail);
        add.setAge(Age);
        add.setContact_no(contact);
        add.setDob(date_of_birth);

        session.persist(add);

        tx.commit();
        session.close();
        sf.close();

        HelloApplication.loadStage("admin_view_users.fxml");
    }

    @FXML
    void backtoviewusers(ActionEvent event) throws IOException {
        HelloApplication.loadStage("admin_view_users.fxml");

    }

}
