package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;

import java.net.URL;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class UserBorrowNewDevice implements Initializable {

    List<DeviceRequest> createlist(int a) {

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Device.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(WorkCondn.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        String qry;
        if(a == 0) {
            qry = "from Device";
        } else {
            qry = "from Device where status = 1 or status = 3";
        }

        List<Device> temp = session.createQuery(qry).list();

        List<DeviceRequest> result = new ArrayList<>();

        for(Device d: temp) {
            DeviceRequest random = new DeviceRequest(d.getDev_id(),d.getType(),d.getModel(),d.getStatus().getStatus());
            result.add(random);
        }

        tx.commit();
        session.close();
        sf.close();

        return result;
    }


    @FXML
    private Button available;

    @FXML
    private Button backtohomeusers;

    @FXML
    private Button clear;

    @FXML
    private Label errorshowlabel;

    @FXML
    private Label labelforsearch;

    @FXML
    private TableColumn<DeviceRequest, Integer> dev_id;

    @FXML
    private TableColumn<DeviceRequest, String> model;

    @FXML
    private TableColumn<DeviceRequest, String> type;

    @FXML
    private Button request;

    @FXML
    private Button searchbyid;

    @FXML
    private TableColumn<DeviceRequest, String> status;

    @FXML
    private TableView<DeviceRequest> Table;

    @FXML
    private Label toppagelabel;

    @FXML
    private TextField searchid;

    ObservableList<DeviceRequest> list =FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dev_id.setCellValueFactory(new PropertyValueFactory<DeviceRequest,Integer>("dev_id"));
        model.setCellValueFactory(new PropertyValueFactory<DeviceRequest,String>("model"));
        type.setCellValueFactory(new PropertyValueFactory<DeviceRequest,String>("type"));
        status.setCellValueFactory(new PropertyValueFactory<DeviceRequest,String>("status"));

        List<DeviceRequest> temp = createlist(0);

        for(DeviceRequest dr : temp) {
            list.add(dr);
        }

        Table.setItems(list);

    }

    @FXML
    void addtorequestsuser(ActionEvent event) {
        DeviceRequest deviceRequest = Table.getSelectionModel().getSelectedItem();

        if(Objects.equals(deviceRequest.getStatus(), "unavailable for borrowing")){
            errorshowlabel.setText("Device is not available at the moment");
        } else {
            Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(UserHistory.class).addAnnotatedClass(Device.class).addAnnotatedClass(User.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(BorrowRequest.class).addAnnotatedClass(WorkCondn.class);
            SessionFactory sf = con.buildSessionFactory();
            Session session = sf.openSession();
            Transaction tx = session.beginTransaction();

            User user = session.get(User.class,HelloApplication.login_id);
            Device dev = session.get(Device.class,deviceRequest.getDev_id());

            LocalDateTime currentLocalDateTime = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String formattedDateTime = currentLocalDateTime.format(dateTimeFormatter);

            BorrowRequest temp = new BorrowRequest();
            temp.setUser_id(user);
            temp.setDevice_id(dev);
            temp.setApprove_status("pending");
            temp.setRequest_date(formattedDateTime);

            session.persist(temp);

            tx.commit();
            session.close();
            sf.close();
        }

//        DeviceRequest temp = Table.getSelectionModel().getSelectedItem();


    }

    @FXML
    void filtersavailabledevices(ActionEvent event) {
        list.clear();
        List<DeviceRequest> temp = createlist(1);

        for(DeviceRequest dr : temp) {
            list.add(dr);
        }

        Table.setItems(list);

    }

    @FXML
    void gotohome(ActionEvent event) throws IOException {
        HelloApplication.loadStage("user_home_page.fxml");

    }

    @FXML
    void openid(ActionEvent event) {
        list.clear();
        int id = Integer.parseInt(searchid.getText());

        List<DeviceRequest> temp = createlist(0);
        for(DeviceRequest dr : temp) {
            if(dr.getDev_id() == id) {
                list.add(dr);
            }
        }

        Table.setItems(list);
    }

    @FXML
    void refreshsamepage(ActionEvent event) throws IOException {
        HelloApplication.loadStage("user_borrow_new_device.fxml");
    }
}
