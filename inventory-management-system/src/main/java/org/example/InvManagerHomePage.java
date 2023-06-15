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

import java.net.URL;

import java.io.IOException;
import java.util.Objects;
import java.util.ResourceBundle;

public class InvManagerHomePage implements Initializable {
    int id;
    public int pass(int id) {
        this.id = id;

        return id;
    }
    @FXML
    private Button devicesrelated;

    @FXML
    private Label naming;

    @FXML
    private Button historyinv;

    @FXML
    private Button infoof;

    @FXML
    private Button logoutinv;

    @FXML
    private Button purchaseinv;

    @FXML
    private Button repdevicesinv;

    @FXML
    private Button reqinv;

    @FXML
    void exitinventorytologinpage(ActionEvent event) throws IOException {
        HelloApplication.loadStage("manager_login_page.fxml");
    }

    @FXML
    void opendevicesinventory(ActionEvent event) throws IOException {
        HelloApplication.loadStage("all_devices_inv.fxml");
    }

    @FXML
    void openhistoryinv(ActionEvent event) throws IOException {
        HelloApplication.loadStage("object_history_inv.fxml");
    }

    @FXML
    void openinfopartinv(ActionEvent event) throws IOException {
        HelloApplication.loadStage("manager_info.fxml");
    }

    @FXML
    void openpurchaseinv(ActionEvent event) throws IOException {

        HelloApplication.loadStage("create_purchase_request.fxml");

    }

    @FXML
    void openrepdevinv(ActionEvent event) throws IOException {
        HelloApplication.loadStage("selecting_repair_inv.fxml");

    }

    @FXML
    void openrequestsinv(ActionEvent event) throws IOException {
        HelloApplication.loadStage("users_requests_inv.fxml");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Device.class).addAnnotatedClass(BorrowDev.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(WorkCondn.class).addAnnotatedClass(UserHistory.class).addAnnotatedClass(User.class).addAnnotatedClass(Manager.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Manager m1 = session.get(Manager.class,HelloApplication.login_id);

        naming.setText(m1.getFirst_name());

    }
}
