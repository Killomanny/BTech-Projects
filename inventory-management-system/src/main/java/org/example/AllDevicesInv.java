package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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

public class AllDevicesInv implements Initializable {

    List<All_Devices> createlist() {

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Device.class).addAnnotatedClass(All_Devices.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(WorkCondn.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        String qry = "from Device";
        List<Device> temp = session.createQuery(qry).list();

        List<All_Devices> result = new ArrayList<>();

        for(Device d: temp) {
            All_Devices random = new All_Devices(d.getDev_id(),d.getModel(),d.getStatus().getStatus(),d.getWork().getCon());
            result.add(random);
        }

        tx.commit();
        session.close();
        sf.close();

        return result;
    }

    @FXML
    private Button allitems1;

    @FXML
    private Button availableinv;

    @FXML
    private Button backtohomeinv;

    @FXML
    private Button borrowedinv;

    @FXML
    private Button clear;

    @FXML
    private Button in_repairinv;

    @FXML
    private Button fulldetailsall;

    @FXML
    private TableView<All_Devices> Table;

    @FXML
    private TableColumn<All_Devices, String> work_condn;

    @FXML
    private Button changecondition;

    @FXML
    private TableColumn<All_Devices, Integer> dev_id;

    @FXML
    private TableColumn<All_Devices, String> model;

    @FXML
    private TableColumn<All_Devices, String> status;

    @FXML
    private TextField searchall;

    @FXML
    private Button searchinvall;

    ObservableList<All_Devices> list = FXCollections.observableArrayList(
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<All_Devices> temp = createlist();

        for(All_Devices ad : temp) {
            list.add(ad);
        }

        dev_id.setCellValueFactory(new PropertyValueFactory<All_Devices,Integer>("dev_id"));
        model.setCellValueFactory(new PropertyValueFactory<All_Devices,String>("model"));
        status.setCellValueFactory(new PropertyValueFactory<All_Devices,String>("status"));
        work_condn.setCellValueFactory(new PropertyValueFactory<All_Devices,String>("work_condn"));
        Table.setItems(list);
    }

    @FXML
    void openallitems(ActionEvent event) throws IOException {
        HelloApplication.loadStage("all_devices_inv.fxml");
    }

    @FXML
    void openavailable(ActionEvent event) throws IOException {
        HelloApplication.loadStage("available_devices_inv.fxml");
    }

    @FXML
    void openborrowed() throws IOException {
        HelloApplication.loadStage("borrowed_devices_inv.fxml");

    }

    @FXML
    void openhomeinv() throws IOException {
        HelloApplication.loadStage("inv_manager_home_page.fxml");

    }

    @FXML
    void openinrepairinv(ActionEvent event) throws IOException {
        HelloApplication.loadStage("in_repair_devices_inv.fxml");

    }

    @FXML
    void searchopenall(ActionEvent event) {
        list.clear();
        int  id=Integer.parseInt(searchall.getText());
        List<All_Devices> temp = createlist();

        for(All_Devices ad : temp) {
            if(ad.dev_id == id) {
                list.add(ad);
            }
        }
        Table.setItems(list);



    }


    @FXML
    void opendetailsofobject(ActionEvent e) throws  IOException{

        All_Devices temp =Table.getSelectionModel().getSelectedItem();


        Parent pane = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("inv_obj_full_details.fxml")));


        FXMLLoader loader =new FXMLLoader((getClass().getResource("inv_obj_full_details.fxml")));
        pane = loader.load();

        InvObjFullDetails invobjfulldetails = loader.getController();
        HelloApplication.primaryStage.getScene().setRoot(pane);
        invobjfulldetails.pass(temp);

    }

    @FXML
    void refreshingthepage(ActionEvent event) throws  IOException{
        HelloApplication.loadStage("all_devices_inv.fxml");
    }

    @FXML
    void changesthecondition(ActionEvent event) throws IOException {
        All_Devices temp = Table.getSelectionModel().getSelectedItem();

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Device.class).addAnnotatedClass(All_Devices.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(WorkCondn.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Device tempd = session.get(Device.class,temp.getDev_id());
        if(tempd.getWork().getCon_id() == 1) {
            tempd.setWork(session.get(WorkCondn.class, 2));
        } else {
            tempd.setWork(session.get(WorkCondn.class, 1));
        }

        session.update(tempd);

        tx.commit();
        session.close();
        sf.close();

        HelloApplication.loadStage("all_devices_inv.fxml");
    }



}
