package org.example;

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
import org.hibernate.jdbc.Work;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class InRepairDevicesInv implements Initializable {
    List<RepairDev> createlist() {

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Device.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(RepairDev.class).addAnnotatedClass(WorkCondn.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        String qry = "from Device where workcon = 2";
        List<Device> temp = session.createQuery(qry).list();

        if(temp.isEmpty()) System.out.println("List is empty");

        List<RepairDev> result = new ArrayList<>();

        for(Device d: temp) {
            RepairDev random = new RepairDev(d.getDev_id(),d.getModel(),d.getType());
            result.add(random);
        }

        tx.commit();
        session.close();
        sf.close();

        return result;
    }
    @FXML
    private Button clear;

    @FXML
    private Button allitemsbutton;

    @FXML
    private Button availablebutton;

    @FXML
    private Button backbutton;

    @FXML
    private Button borrowedbutton;

    @FXML
    private Label inrepair;

    @FXML
    private Button inrepairbutton;

    @FXML
    private TableView<RepairDev> Table;

    @FXML
    private TableColumn<RepairDev, Integer> dev_id;

    @FXML
    private TableColumn<RepairDev, String> model;

    @FXML
    private Button sbuttonbyrep;

    @FXML
    private TextField sidbyrepair;

    @FXML
    private TableColumn<RepairDev,String> type;

    ObservableList<RepairDev> list = FXCollections.observableArrayList();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dev_id.setCellValueFactory(new PropertyValueFactory<RepairDev,Integer>("dev_id"));
        model.setCellValueFactory(new PropertyValueFactory<RepairDev,String>("model"));
        type.setCellValueFactory(new PropertyValueFactory<RepairDev,String>("type"));

        list.clear();
        List<RepairDev> temp = createlist();

        for(RepairDev ad : temp) {
            list.add(ad);
        }

        Table.setItems(list);

    }

    @FXML
    void backtoall(ActionEvent event) throws IOException {
        HelloApplication.loadStage("all_devices_inv.fxml");
    }

    @FXML
    void backtoavailable(ActionEvent event) throws IOException {
        HelloApplication.loadStage("available_devices_inv.fxml");

    }

    @FXML
    void backtoborrowed(ActionEvent event) throws IOException {
        HelloApplication.loadStage("borrowed_devices_inv.fxml");

    }

    @FXML
    void gotohomeinv(ActionEvent event) throws IOException {
        HelloApplication.loadStage("inv_manager_home_page.fxml");

    }

    @FXML
    void refreshsame(ActionEvent event) throws IOException {
        HelloApplication.loadStage("in_repair_devices_inv.fxml");

    }


    @FXML
    void searchbyrepair(ActionEvent event) {
        list.clear();
        int  id=Integer.parseInt(sidbyrepair.getText());
        List<RepairDev> temp = createlist();

        for(RepairDev ad : temp) {
            if(ad.getDev_id() == id) {
                list.add(ad);
            }
        }
        Table.setItems(list);



    }

    @FXML
    void refreshingthepage(ActionEvent event) throws IOException {
        HelloApplication.loadStage("in_repair_devices_inv.fxml");
    }
}
