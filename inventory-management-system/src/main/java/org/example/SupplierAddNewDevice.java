package org.example;

import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.IOException;

public class SupplierAddNewDevice {

    @FXML
    private Button add;

    @FXML
    private Button back;

    @FXML
    private TextField brand;

    @FXML
    private TextField cost;

    @FXML
    private TextField model;

    @FXML
    private TextField quantity;

    @FXML
    private TextField producttype;

    @FXML
    private Label toplabel;

    @FXML
    void addtothedevicestabel(ActionEvent event) throws IOException {

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Device.class).addAnnotatedClass(All_Devices.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(WorkCondn.class).addAnnotatedClass(SupplierDevice.class).addAnnotatedClass(Supplier.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        String bran = brand.getText();
        int  cos = Integer.parseInt(cost.getText());
        String mode = model.getText();
        int quantit = Integer.parseInt(quantity.getText());
        String type = producttype.getText();

        SupplierDevice add = new SupplierDevice();
        add.setBrand(bran);
        add.setCost(cos);
        add.setModel(mode);
        add.setType(type);
        add.setQuantity(quantit);
        add.setSold_by(session.get(Supplier.class,HelloApplication.login_id));

        session.persist(add);

        tx.commit();
        session.close();
        sf.close();

        HelloApplication.loadStage("supplier_all_devices.fxml");
    }

    @FXML
    void backtosupplierhomepage(ActionEvent event) throws IOException {

        HelloApplication.loadStage("supplier_all_devices.fxml");

    }

}
