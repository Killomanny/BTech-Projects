package org.example;

import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import  java.net.URL;


import java.io.IOException;
import java.util.ResourceBundle;

public class SetQuantity {

    @FXML
    private Button add;

    @FXML
    private Button back;

    @FXML
    private Label brand;

    @FXML
    private Label costper;

    @FXML
    private Label model;

    @FXML
    private Label ptype;

    @FXML
    private TextField quantity;

    @FXML
    private Label toplabel;

    public SupplierDevice supplierDevice;

    public void pass(SupplierDevice supplierDevice){
        System.out.println("pass function is called now");
        System.out.println(supplierDevice);
        this.supplierDevice=supplierDevice;
        brand.setText(supplierDevice.getBrand());
        model.setText(supplierDevice.getModel());
        ptype.setText(supplierDevice.getType());
        costper.setText(Integer.toString(supplierDevice.getCost()));
    }

//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//    }

    @FXML
    void backtonewpurchase(ActionEvent event) throws IOException {

         HelloApplication.loadStage("create_purchase_request.fxml");

    }

    @FXML
    void purchasing (ActionEvent event) throws IOException {
        int quantit = Integer.parseInt(quantity.getText());

        if(quantit > supplierDevice.getQuantity()) {
            //only these many are available
            System.out.println("Not enough quantity");
        } else {
            Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Device.class).addAnnotatedClass(SupplierDevice.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(WorkCondn.class).addAnnotatedClass(FundRequest.class).addAnnotatedClass(Supplier.class);
            SessionFactory sf = con.buildSessionFactory();
            Session session = sf.openSession();
            Transaction tx = session.beginTransaction();

            FundRequest add = new FundRequest();
            add.setCost(supplierDevice.getCost() * quantit);
            add.setPurpose("New Purchase");
            add.setSup_dev_id(supplierDevice);
            session.persist(add);

            tx.commit();
            session.close();
            sf.close();


            HelloApplication.loadStage("create_purchase_request.fxml");
        }


    }

}