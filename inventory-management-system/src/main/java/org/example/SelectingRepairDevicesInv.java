package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class SelectingRepairDevicesInv implements Initializable {

    List<RepairRequestDev> createlist() {

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Device.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(RepairDev.class).addAnnotatedClass(WorkCondn.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        String qry = "from Device";
        List<Device> temp = session.createQuery(qry).list();

        if(temp.isEmpty()) System.out.println("List is empty");

        List<RepairRequestDev> result = new ArrayList<>();

        for(Device d: temp) {
            RepairRequestDev random = new RepairRequestDev(d.getDev_id(),d.getModel(),d.getType(),d.getBrand());
            result.add(random);
        }

        tx.commit();
        session.close();
        sf.close();

        return result;
    }

    @FXML
    private TableColumn<RepairRequestDev, String> brand;

    @FXML
    private TableColumn<RepairRequestDev, String> model;

    @FXML
    private Button add;

    @FXML
    private Button backtohomerep;

    @FXML
    private TableColumn<RepairRequestDev, Integer> dev_id;

    @FXML
    private TableColumn<RepairRequestDev, String> type;

    @FXML
    private TableView<RepairRequestDev> Table;

    @FXML
    private TextField search;

    @FXML
    private Label searchhis;

    @FXML
    private Button searchrepdev;

    @FXML
    private Label taghis;

    @FXML
    private Button clear;

    ObservableList<RepairRequestDev> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        dev_id.setCellValueFactory(new PropertyValueFactory<RepairRequestDev,Integer>("dev_id"));
        model.setCellValueFactory(new PropertyValueFactory<RepairRequestDev,String>("model"));
        brand.setCellValueFactory(new PropertyValueFactory<RepairRequestDev,String>("brand"));
        type.setCellValueFactory(new PropertyValueFactory<RepairRequestDev,String>("type"));


        List<RepairRequestDev> temp = createlist();
        for(RepairRequestDev rrd : temp) {
            list.add(rrd);
        }

        Table.setItems(list);
    }

    @FXML
    void addtorepair(ActionEvent event) throws IOException{
        RepairRequestDev temp = Table.getSelectionModel().getSelectedItem();

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Device.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(RepairDev.class).addAnnotatedClass(WorkCondn.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        if(session.get(Device.class,temp.getDev_id()).getWork().getCon_id() == 1) {
                        taghis.setText("The Device doesn't need repair");
        } else {

            Parent pane = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("after_add_to_repair.fxml")));

            FXMLLoader loader = new FXMLLoader((getClass().getResource("after_add_to_repair.fxml")));
            pane = loader.load();

            AfterAddToRepair afterAddToRepair = loader.getController();
            HelloApplication.primaryStage.getScene().setRoot(pane);
            afterAddToRepair.pass(temp);
        }

        tx.commit();
        session.close();
        sf.close();
    }

    @FXML
    void openhomeinvrep(ActionEvent event) throws IOException {
        HelloApplication.loadStage("inv_manager_home_page.fxml");

    }

    @FXML
    void openserachrepdev(ActionEvent event) {

        int tempa = Integer.parseInt(search.getText());
        list.clear();

        List<RepairRequestDev> temp = createlist();

        for(RepairRequestDev rrd : temp) {
            if(rrd.getDev_id() == tempa) {
                list.add(rrd);
            }
        }
        Table.setItems(list);
    }

    @FXML
    void refreshsamepage(ActionEvent event) throws IOException {
       HelloApplication.loadStage("Selecting_repair_inv.fxml");
    }

}
