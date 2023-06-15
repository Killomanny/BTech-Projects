package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DueDateSelector {
    @FXML
    private Button back;

    @FXML
    private DatePicker duedate;

    @FXML
    private Button submit;

    private Obj_UserRequestInv tempa;

    @FXML
    public void pass(Obj_UserRequestInv tempa){

        this.tempa=tempa;
    }

    @FXML
    void backtotabel(ActionEvent event) throws IOException {
        HelloApplication.loadStage("users_requests_inv.fxml");

    }

    @FXML
    void submitit(ActionEvent event) throws IOException {
        LocalDate date = duedate.getValue();
        HelloApplication.due_date = date.toString();
        System.out.println(HelloApplication.due_date);

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Device.class).addAnnotatedClass(User.class).addAnnotatedClass(BorrowRequest.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(WorkCondn.class).addAnnotatedClass(UserHistory.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        List<BorrowRequest> temp = session.createQuery("from BorrowRequest where user_id = "+tempa.getUser_id()+" and request_date = \""+tempa.getRequest_date() + "\"" + " and device_id = " + tempa.getDev_id()).list();
        BorrowRequest instance = temp.get(0);
        instance.setApprove_status("Approved");

        session.update(instance);

        Device d1 = session.get(Device.class,tempa.getDev_id());
        d1.setStatus(session.get(DevStatus.class,2));
        session.update(d1);

        UserHistory uh = new UserHistory();
        uh.setUser_id(session.get(User.class,instance.getUser_id().getUser_id()));
        uh.setDevice_id(session.get(Device.class,instance.getDevice_id().getDev_id()));

        LocalDateTime currentLocalDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateTime = currentLocalDateTime.format(dateTimeFormatter);

        uh.setStart_date(formattedDateTime);
        System.out.println(HelloApplication.due_date);
        uh.setDue_date(HelloApplication.due_date);

        session.persist(uh);

        tx.commit();
        session.close();
        sf.close();

        HelloApplication.loadStage("users_requests_inv.fxml");
    }
}
