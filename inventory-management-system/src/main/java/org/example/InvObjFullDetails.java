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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class InvObjFullDetails {

    @FXML
    private Label CompanyTayg;

    @FXML
    private Label Cost;

    @FXML
    private Label PType;

    @FXML
    private Label ProductID;

    @FXML
    private Label Status;

    @FXML
    private Label Productmodel;

    @FXML
    private Button backtoalldevices;

    @FXML
    private Label fulldetails;

    @FXML
    void openalldevices(ActionEvent event) throws IOException {
        HelloApplication.loadStage("all_devices_inv.fxml");
    }

    public void pass(All_Devices all_devices){

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Device.class).addAnnotatedClass(BorrowDev.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(WorkCondn.class).addAnnotatedClass(UserHistory.class).addAnnotatedClass(User.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Device d1 = session.get(Device.class,all_devices.getDev_id());

        tx.commit();
        session.close();
        sf.close();

        System.out.println(d1.getDev_id());



        this.ProductID.setText(Integer.toString(d1.getDev_id()));
        this.Status.setText(d1.getStatus().getStatus());
        this.PType.setText(d1.type);
        this.Cost.setText("0");
        this.CompanyTayg.setText(d1.getBrand());
        this.Productmodel.setText(d1.getModel());



    }


}
