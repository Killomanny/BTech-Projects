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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BorrowedDevicesInv implements Initializable{
    List<BorrowDev> createlist() {

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(UserHistory.class).addAnnotatedClass(Device.class).addAnnotatedClass(User.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(WorkCondn.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        String qry = "from UserHistory where returned_at = null";
        List<UserHistory> temp = session.createQuery(qry).list();

        List<BorrowDev> result = new ArrayList<>();

        for(UserHistory d: temp) {
            BorrowDev random = new BorrowDev(d.getDevice_id().getDev_id(),d.getUser_id().getUser_id(),d.getDevice_id().getModel(),d.getStart_date(),d.getDue_date());
            result.add(random);
        }

        tx.commit();
        session.close();
        sf.close();

        return result;
    }
    @FXML
    private Button allitems;

    @FXML
    private Button available;

    @FXML
    private Button back;

    @FXML
    private Label bd;

    @FXML
    private TableColumn<BorrowDev, String> start_date;

    @FXML
    private Button borrowed;

    @FXML
    private TableColumn<BorrowDev, String> due_date;

    @FXML
    private Button inrepair;

    @FXML
    private TableColumn<BorrowDev, Integer> dev_id;

    @FXML
    private TableColumn<BorrowDev, String> model;

    @FXML
    private Label sbid;

    @FXML
    private Button search;

    @FXML
    private TextField searchinborrowed;

    @FXML
    private Button clear;

    @FXML
    private TableView<BorrowDev> Table;

    @FXML
    private TableColumn<BorrowDev, Integer> user_id;

    ObservableList<BorrowDev> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        list.clear();

        user_id.setCellValueFactory(new PropertyValueFactory<BorrowDev,Integer>("user_id"));
        dev_id.setCellValueFactory(new PropertyValueFactory<BorrowDev,Integer>("dev_id"));
        model.setCellValueFactory(new PropertyValueFactory<BorrowDev,String>("model"));
        start_date.setCellValueFactory(new PropertyValueFactory<BorrowDev,String>("start_date"));
        due_date.setCellValueFactory(new PropertyValueFactory<BorrowDev,String>("due_date"));



        List<BorrowDev> temp = createlist();

        for(BorrowDev ad : temp) {
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
    void backtoihomeinv(ActionEvent event) throws IOException {
        HelloApplication.loadStage("inv_manager_home_page.fxml");

    }

    @FXML
    void backtorepair(ActionEvent event) throws IOException {
        HelloApplication.loadStage("in_repair_devices_inv.fxml");

    }

    @FXML
    void changestatusavailable(ActionEvent event) throws IOException {
        BorrowDev temp = Table.getSelectionModel().getSelectedItem();
        int id = temp.getDev_id();

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Device.class).addAnnotatedClass(BorrowDev.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(WorkCondn.class).addAnnotatedClass(UserHistory.class).addAnnotatedClass(User.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Device temp_dev = session.get(Device.class,id);
        DevStatus temp_ds = session.get(DevStatus.class,1);
        temp_dev.setStatus(temp_ds);
        session.update(temp_dev);

        List<UserHistory> temp_uh = session.createQuery("from UserHistory where returned_at = null and device_id = " + id).list();
        UserHistory uh1 = temp_uh.get(0);

        System.out.println("Printing before returning " + uh1);

        LocalDateTime currentLocalDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateTime = currentLocalDateTime.format(dateTimeFormatter);

        uh1.setReturned_at(formattedDateTime);
        System.out.println("Printing after returning" + uh1);
        session.update(uh1);

        tx.commit();
        session.close();
        sf.close();

        Table.getItems().remove(temp);
        HelloApplication.loadStage("borrowed_devices_inv.fxml");
    }



    @FXML
    void  searchinborrowedinv(ActionEvent event) {
        list.clear();
        int  id=Integer.parseInt(searchinborrowed.getText());
        List<BorrowDev> temp = createlist();

        for(BorrowDev ad : temp) {
            if(ad.getDev_id() == id) {
                list.add(ad);
            }
        }
        Table.setItems(list);



    }

    @FXML
    void refreshingthepage(ActionEvent event) throws IOException{
        HelloApplication.loadStage("borrowed_devices_inv.fxml");
    }
}
