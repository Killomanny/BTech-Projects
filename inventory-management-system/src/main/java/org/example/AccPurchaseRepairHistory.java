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

public class AccPurchaseRepairHistory implements Initializable {

    List<Obj_PTransactions> createlist(int flag, int ser) {
        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Device.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(FundRequest.class).addAnnotatedClass(PTransactions.class).addAnnotatedClass(WorkCondn.class).addAnnotatedClass(Manager.class).addAnnotatedClass(SupType.class).addAnnotatedClass(SupplierDevice.class).addAnnotatedClass(Supplier.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        String qry;
        if(flag == 0) {
            qry = "from PTransactions";
        } else {
            qry = "from PTransactions where dev_id = " + ser;
        }
        List<PTransactions> temp = session.createQuery(qry).list();
        List<Obj_PTransactions> result = new ArrayList<Obj_PTransactions>();

        for(PTransactions pt : temp) {
            Obj_PTransactions random;
            if(pt.getDev_id() != null) {
                random = new Obj_PTransactions(pt.getTrans_id(),pt.getDev_id().getDev_id(),pt.getDate_and_time(),pt.getSup_type().getSup_name(),pt.getAmount());
            } else {
                random = new Obj_PTransactions(pt.getTrans_id(),0,pt.getDate_and_time(),pt.getSup_type().getSup_name(),pt.getAmount());
            }
            result.add(random);
        }

        tx.commit();
        session.close();
        sf.close();

        return result;
    }

    @FXML
    private TableColumn<Obj_PTransactions, Integer> Ammount;

    @FXML
    private Button Back;

    @FXML
    private Button clear;

    @FXML
    private TableColumn<Obj_PTransactions, String> date;

    @FXML
    private TableColumn<Obj_PTransactions, Integer> pid;

    @FXML
    private TableView<Obj_PTransactions> Table;

    @FXML
    private Button search;

    @FXML
    private TextField searchtextfield;

    @FXML
    private TableColumn<Obj_PTransactions, Integer> tid;

    @FXML
    private Label toplabel;

    @FXML
    private TableColumn<Obj_PTransactions, String> type;

    ObservableList<Obj_PTransactions> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Obj_PTransactions> temp = createlist(0,0);
        for(Obj_PTransactions ofr : temp) {
            list.add(ofr);
        }

        tid.setCellValueFactory(new PropertyValueFactory<Obj_PTransactions,Integer>("trans_id"));
        pid.setCellValueFactory(new PropertyValueFactory<Obj_PTransactions,Integer>("dev_id"));
        date.setCellValueFactory(new PropertyValueFactory<Obj_PTransactions,String>("date"));
        type.setCellValueFactory(new PropertyValueFactory<Obj_PTransactions,String>("type"));
        Ammount.setCellValueFactory(new PropertyValueFactory<Obj_PTransactions,Integer>("amount"));

        Table.setItems(list);
    }

    @FXML
    void opensacchome(ActionEvent event) throws IOException {

        HelloApplication.loadStage("accounts_after_login.fxml");
    }

    @FXML
    void refreshthesamepage(ActionEvent event) throws IOException {

        HelloApplication.loadStage("acc_purchase_repair_history.fxml");

    }

    @FXML
    void searchimnprhistory(ActionEvent event) {
        list.clear();
        Integer ser = Integer.parseInt(searchtextfield.getText());

        List<Obj_PTransactions> temp = createlist(1,ser);
        for(Obj_PTransactions pt : temp) {
            list.add(pt);
        }

        Table.setItems(list);
    }
}
