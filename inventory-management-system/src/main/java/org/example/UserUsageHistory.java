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

public class UserUsageHistory implements Initializable {

    List<Obj_UserUsageHistory> createlist() {

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(UserHistory.class).addAnnotatedClass(Device.class).addAnnotatedClass(User.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(WorkCondn.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        String qry = "from UserHistory where user_id = " + HelloApplication.login_id;
        List<UserHistory> temp = session.createQuery(qry).list();
        List<Obj_UserUsageHistory> result = new ArrayList<Obj_UserUsageHistory>();

        for(UserHistory d: temp) {
            Obj_UserUsageHistory random = new Obj_UserUsageHistory(d.getDevice_id().getDev_id(),d.getDevice_id().getType(),d.getDevice_id().getModel(),d.getStart_date(),d.getDue_date(),d.getReturned_at());
            result.add(random);
        }

        tx.commit();
        session.close();
        sf.close();

        return result;
    }
    @FXML
    private Button back;

    @FXML
    private Label bd;

    @FXML
    private TableColumn<Obj_UserUsageHistory, String> start_date;

    @FXML
    private Button clear;

    @FXML
    private TableColumn<Obj_UserUsageHistory, String> due_date;

    @FXML
    private Button noreturndate;

    @FXML
    private TableColumn<Obj_UserUsageHistory, Integer> dev_id;

    @FXML
    private TableColumn<Obj_UserUsageHistory, String> model;

    @FXML
    private TableColumn<Obj_UserUsageHistory, String> type;

    @FXML
    private TableColumn<Obj_UserUsageHistory, String> returned_at;

    @FXML
    private Label sbid;

    @FXML
    private Button search;

    @FXML
    private TextField searchbyid;

    @FXML
    private TableView<Obj_UserUsageHistory> Table;

    ObservableList<Obj_UserUsageHistory> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        dev_id.setCellValueFactory(new PropertyValueFactory<Obj_UserUsageHistory,Integer>("dev_id"));
        model.setCellValueFactory(new PropertyValueFactory<Obj_UserUsageHistory,String>("model"));
        start_date.setCellValueFactory(new PropertyValueFactory<Obj_UserUsageHistory,String>("start_date"));
        due_date.setCellValueFactory(new PropertyValueFactory<Obj_UserUsageHistory,String>("due_date"));
        returned_at.setCellValueFactory(new PropertyValueFactory<Obj_UserUsageHistory,String>("returned_at"));
        type.setCellValueFactory(new PropertyValueFactory<Obj_UserUsageHistory,String>("type"));
        List<Obj_UserUsageHistory> temp = createlist();

        for(Obj_UserUsageHistory dh : temp) {
            list.add(dh);
        }

        Table.setItems(list);
    }

    @FXML
    void backtoihomeinv(ActionEvent event) throws IOException {
        HelloApplication.loadStage("user_home_page.fxml");
    }

    @FXML
    void opennotreturned(ActionEvent event) {
        list.clear();

        List<Obj_UserUsageHistory> temp = createlist();
        for(Obj_UserUsageHistory uuh : temp) {
            if(uuh.getReturned_at() == null) {
                list.add(uuh);
            }
        }

        Table.setItems(list);
    }

    @FXML
    void refreshthepage(ActionEvent event) throws IOException {
       HelloApplication.loadStage("user_usage_history.fxml");
    }

    @FXML
    void searchinborrowedinv(ActionEvent event) {
        list.clear();
        int id=Integer.parseInt(searchbyid.getText());

        List<Obj_UserUsageHistory> temp = createlist();

        for(Obj_UserUsageHistory uuh : temp) {
            if(uuh.getDev_id() == id) {
                list.add(uuh);
            }
        }

   Table.setItems(list);
    }

}
