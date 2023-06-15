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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class AvailableDevicesInv implements Initializable {





    @FXML
    private TableColumn<AvailableDev, String> type;

    @FXML
    private Label ad;

    @FXML
    private Button allitemsavail;

    @FXML
    private Button available;

    @FXML
    private TextField availabledevices;

    @FXML
    private Button clear;

    @FXML
    private TableView<AvailableDev> Table;

    @FXML
    private Button backtohomeinv;

    @FXML
    private Button borrowed;

    @FXML
    private Button in_repaibutton;

    @FXML
    private TableColumn<AvailableDev, Integer> dev_id;

    @FXML
    private TableColumn<AvailableDev, String> model;

    @FXML
    private Label search;

    @FXML
    private Button serachavailabledevicesinv;

    @FXML
    void backtoallitems(ActionEvent event) throws IOException{
        HelloApplication.loadStage("all_devices_inv.fxml");

    }

    @FXML
    void backtoborrowed(ActionEvent event) throws IOException{
        HelloApplication.loadStage("borrowed_devices_inv.fxml");
    }

    @FXML
    void backtorepair(ActionEvent event) throws IOException{
        HelloApplication.loadStage("in_repair_devices_inv.fxml");
    }

    @FXML
    void openhomeinv(ActionEvent event) throws IOException{
        HelloApplication.loadStage("inv_manager_home_page.fxml");
    }

    @FXML
    void samepagebutrefresh(ActionEvent event) throws IOException{
        HelloApplication.loadStage("available_devices_inv.fxml");
    }

    @FXML
    void searchavailadev(ActionEvent event) {

    }





    List<AvailableDev> createlist() {

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Device.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(AvailableDev.class).addAnnotatedClass(WorkCondn.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        String qry = "from Device where status = 1 or status = 3";
        List<Device> temp = session.createQuery(qry).list();
        System.out.println(temp.toString());

        List<AvailableDev> result = new ArrayList<>();

        for(Device d: temp) {
            AvailableDev random = new AvailableDev(d.getDev_id(),d.getModel(),d.getType());
            result.add(random);
        }

        tx.commit();
        session.close();
        sf.close();

        return result;
    }


    ObservableList<AvailableDev> list=FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dev_id.setCellValueFactory(new PropertyValueFactory<AvailableDev,Integer>("dev_id"));
        model.setCellValueFactory(new PropertyValueFactory<AvailableDev,String>("model"));
        type.setCellValueFactory(new PropertyValueFactory<AvailableDev,String>("type"));
        ObservableList<AvailableDev> list=FXCollections.observableArrayList();

        List<AvailableDev> temp = createlist();

        for(AvailableDev ad : temp) {
            list.add(ad);
        }

        Table.setItems(list);
    }
    @FXML
    void searchopenall(ActionEvent event) {
        list.clear();
        int  id=Integer.parseInt(availabledevices.getText());
        List<AvailableDev> temp = createlist();

        for(AvailableDev ad : temp) {
            if(ad.getDev_id() == id) {
                list.add(ad);
            }
        }
        Table.setItems(list);



    }

    @FXML
    void refreshingthepage(ActionEvent event) throws IOException{
        HelloApplication.loadStage("available_devices_inv.fxml");
    }
}
