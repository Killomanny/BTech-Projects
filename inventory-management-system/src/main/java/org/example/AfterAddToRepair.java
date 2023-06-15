package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.net.URL;

import java.io.IOException;
import java.util.Objects;
import java.util.ResourceBundle;


public class AfterAddToRepair implements Initializable {

    @FXML
    private Label LABELTOP;

    @FXML
    private TextField costinrupes;

    @FXML
    private Label nop;

    @FXML
    private Label pid;

    @FXML
    private Button backtotabel;

    @FXML
    private Button reqforrep;

    private RepairRequestDev temp;

    public void pass(RepairRequestDev temp){
        this.temp=temp;
         pid.setText(Integer.toString(temp.getDev_id()));
         nop.setText(temp.getBrand()+" "+temp.getModel());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){


    }

    @FXML
    void gotoinrepair(ActionEvent event) throws IOException {

       int cost = Integer.parseInt(costinrupes.getText());

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Device.class).addAnnotatedClass(All_Devices.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(WorkCondn.class).addAnnotatedClass(FundRequest.class).addAnnotatedClass(SupplierDevice.class).addAnnotatedClass(Supplier.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        FundRequest fundreq = new FundRequest();
        fundreq.setCost(cost);
        fundreq.setDevice_id(session.get(Device.class,temp.getDev_id()));
        fundreq.setPurpose("Repair");

        session.persist(fundreq);

        tx.commit();
        session.close();
        sf.close();

        HelloApplication.loadStage("selecting_repair_inv.fxml");

    }

    @FXML
    void openstheprevioustabel(ActionEvent event) throws IOException {

         HelloApplication.loadStage("selecting_repair_inv.fxml");
    }


}
