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

import java.net.URL;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ObjectHistoryInv implements Initializable {

    List<DeviceHistory> createlist() {

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(UserHistory.class).addAnnotatedClass(Device.class).addAnnotatedClass(User.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(WorkCondn.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        String qry = "from UserHistory";
        List<UserHistory> temp = session.createQuery(qry).list();
        List<DeviceHistory> result = new ArrayList<DeviceHistory>();

        for(UserHistory d: temp) {
            DeviceHistory random = new DeviceHistory(d.getUser_id().getUser_id(),d.getDevice_id().getDev_id(),d.getStart_date(),d.getDue_date(),d.getReturned_at());
            result.add(random);
        }

        tx.commit();
        session.close();
        sf.close();

        return result;
    }

    @FXML
    private Button backtohome;

    @FXML
    private TableColumn<DeviceHistory, String> start_date;

    @FXML
    private Button clearhis;

    @FXML
    private TableColumn<DeviceHistory, String> due_date;

    @FXML
    private TextField enterinprodectidhis;

    @FXML
    private TableView<DeviceHistory> Table;

    @FXML
    private Label labelhistory;

    @FXML
    private TableColumn<DeviceHistory, Integer> dev_id;

    @FXML
    private TableColumn<DeviceHistory, String> returned_at;

    @FXML
    private Button searchhis;

    @FXML
    private TableColumn<DeviceHistory, Integer> user_id;

    ObservableList<DeviceHistory> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        dev_id.setCellValueFactory(new PropertyValueFactory<DeviceHistory,Integer>("dev_id"));
        user_id.setCellValueFactory(new PropertyValueFactory<DeviceHistory,Integer>("user_id"));
        start_date.setCellValueFactory(new PropertyValueFactory<DeviceHistory,String>("start_date"));
        due_date.setCellValueFactory(new PropertyValueFactory<DeviceHistory,String>("due_date"));
        returned_at.setCellValueFactory(new PropertyValueFactory<DeviceHistory,String>("returned_at"));

        List<DeviceHistory> temp = createlist();

        for(DeviceHistory dh : temp) {
            list.add(dh);
        }

        Table.setItems(list);
    }

    @FXML
    void gotohomehis(ActionEvent event) throws IOException {
        HelloApplication.loadStage("object_history_inv.fxml");
    }

    @FXML
    void openhomeinv(ActionEvent event) throws IOException {
        HelloApplication.loadStage("inv_manager_home_page.fxml");
    }

    @FXML
    void opensearchhis(ActionEvent event) {
        list.clear();

        int id = Integer.parseInt(enterinprodectidhis.getText());

        List<DeviceHistory> temp = createlist();

        for(DeviceHistory dh : temp) {
            if(dh.getDev_id() == id) {
                list.add(dh);
            }
        }

        Table.setItems(list);
    }
}
