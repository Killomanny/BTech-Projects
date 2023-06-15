package org.example;

import jakarta.persistence.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SupplierAllDevices implements Initializable {

    List<SupplierDevice> createlist() {

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Device.class).addAnnotatedClass(All_Devices.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(WorkCondn.class).addAnnotatedClass(SupplierDevice.class).addAnnotatedClass(Supplier.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        String qry = "from SupplierDevice";
        List<SupplierDevice> result = session.createQuery(qry).list();

        tx.commit();
        session.close();
        sf.close();

        return result;
    }

    @FXML
    private Button adddevice;

    @FXML
    private Label allDevices;

    @FXML
    private Button back;

    @FXML
    private Button clear;

    @FXML
    private TableColumn<SupplierDevice, Integer> cost;

    @FXML
    private TextField opensthetype;

    @FXML
    private TableColumn<SupplierDevice, String> pbrand;

    @FXML
    private TableColumn<SupplierDevice, String> pmodel;

    @FXML
    private TableColumn<SupplierDevice, Integer> quantity;

    @FXML
    private TableColumn<SupplierDevice, String> ptype;

    @FXML
    private Button remove;

    @FXML
    private Button search;

    @FXML
    private TableView<SupplierDevice> Table;

    ObservableList<SupplierDevice> list = FXCollections.observableArrayList();

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {
        List<SupplierDevice> temp = createlist();
        for(SupplierDevice sd : temp) {
                list.add(sd);
        }

        ptype.setCellValueFactory(new PropertyValueFactory<SupplierDevice, String>("type"));
        pbrand.setCellValueFactory(new PropertyValueFactory<SupplierDevice, String>("brand"));
        pmodel.setCellValueFactory(new PropertyValueFactory<SupplierDevice, String>("model"));
        cost.setCellValueFactory(new PropertyValueFactory<SupplierDevice, Integer>("cost"));
        quantity.setCellValueFactory(new PropertyValueFactory<SupplierDevice, Integer>("quantity"));

        Table.setItems(list);
    }

    @FXML
    void searchbytype(ActionEvent event) {
        list.clear();

        List<SupplierDevice> temp = createlist();
        for(SupplierDevice sd : temp) {
            list.add(sd);
        }

        String search = opensthetype.getText();
        for(SupplierDevice sd : list) {
            if(!sd.getType().contains(search)) {
                list.remove(sd);
            }
        }

        Table.setItems(list);
    }

    @FXML
    void refreshthepage(ActionEvent event) throws IOException {

        HelloApplication.loadStage("supplier_all_devices.fxml");

    }

    @FXML
    void backtosupplierhome(ActionEvent event) throws IOException {

        HelloApplication.loadStage("supplier_after_login.fxml");

    }

    @FXML
    void opensaddevicepage(ActionEvent event) throws IOException {

        HelloApplication.loadStage("supplier_add_new_device.fxml");

    }

    @FXML
    void removesthedevicefromall(ActionEvent event)  throws IOException {
        SupplierDevice rem = Table.getSelectionModel().getSelectedItem();
        int id = Table.getSelectionModel().getSelectedIndex();

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Device.class).addAnnotatedClass(All_Devices.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(WorkCondn.class).addAnnotatedClass(SupplierDevice.class).addAnnotatedClass(Supplier.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

//        session.createQuery("delete from SupplierDevice where dev_id = " + rem.getDev_id());
        session.remove(rem);

        tx.commit();
        session.close();
        sf.close();

        Table.getItems().remove(id);
    }

}