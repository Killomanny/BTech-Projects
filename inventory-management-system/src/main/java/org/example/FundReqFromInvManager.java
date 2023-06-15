package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class FundReqFromInvManager implements Initializable {

    List<Obj_FundRequest> createlist() {

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Device.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(FundRequest.class).addAnnotatedClass(WorkCondn.class).addAnnotatedClass(Manager.class).addAnnotatedClass(SupType.class).addAnnotatedClass(SupplierDevice.class).addAnnotatedClass(Supplier.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        String qry = "from FundRequest where approve_status = \"pending\"";
        List<FundRequest> temp = session.createQuery(qry).list();

        List<Obj_FundRequest> result = new ArrayList<>();

        for(FundRequest f : temp) {
            Obj_FundRequest random;
            if(f.getDevice_id() == null) {
                random = new Obj_FundRequest(f.getSup_dev_id().getType(),f.getReq_id(),f.getSup_dev_id().getModel(),f.getPurpose(),f.getCost(),0);
            } else {
                random  = new Obj_FundRequest(f.getDevice_id().getType(),f.getReq_id(),f.getDevice_id().getModel(),f.getPurpose(),f.getCost(),f.getDevice_id().getDev_id());
            }
            result.add(random);
        }

        tx.commit();
        session.close();
        sf.close();

        return result;
    }
    @FXML
    private TableColumn<Obj_FundRequest, String> PName;

    @FXML
    private TableColumn<Obj_FundRequest, String> Ptype;

    @FXML
    private Button approve;

    @FXML
    private Button back;

    @FXML
    private TableColumn<Obj_FundRequest, Integer> cost;

    @FXML
    private Button diapprove;

    @FXML
    private TableColumn<Obj_FundRequest, String> fundtype;

    @FXML
    private TableView<Obj_FundRequest> Table;

    ObservableList<Obj_FundRequest> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        Ptype.setCellValueFactory(new PropertyValueFactory<Obj_FundRequest,String>("type"));
        PName.setCellValueFactory(new PropertyValueFactory<Obj_FundRequest,String>("model"));
        fundtype.setCellValueFactory(new PropertyValueFactory<Obj_FundRequest,String>("purpose"));
        cost.setCellValueFactory(new PropertyValueFactory<Obj_FundRequest,Integer>("cost"));


        List<Obj_FundRequest> temp = createlist();
        for(Obj_FundRequest ofr : temp) {
            list.add(ofr);
        }

        Table.setItems(list);
    }

    @FXML
    void addtoreq(ActionEvent event) {
        Obj_FundRequest sel = Table.getSelectionModel().getSelectedItem();

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Device.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(FundRequest.class).addAnnotatedClass(WorkCondn.class).addAnnotatedClass(Manager.class).addAnnotatedClass(PTransactions.class).addAnnotatedClass(SupType.class).addAnnotatedClass(SupplierDevice.class).addAnnotatedClass(Supplier.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        FundRequest temp = session.get(FundRequest.class,sel.getRequest_id());
        temp.setApprove_status("Approved");
        session.update(temp);

        if(temp.getDevice_id() != null) {
            Device tempd = session.get(Device.class, temp.getDevice_id().getDev_id());
            tempd.setWork(session.get(WorkCondn.class, 1));
            session.update(tempd);
        }

        PTransactions tempp = new PTransactions();
        tempp.setAmount(temp.getSup_dev_id().getCost());
        tempp.setSup_type(session.get(SupType.class,2));
        tempp.setDev_id(session.get(Device.class,sel.getDev_id()));

        if(temp.getSup_dev_id() != null) {
            int quantity = sel.getCost()/temp.getSup_dev_id().getCost();
            int room = 1;
            for(int i = 0; i < quantity; i++) {
                Device add = new Device();
                add.setStatus(session.get(DevStatus.class, 1));
                add.setWork(session.get(WorkCondn.class, 1));
                add.setType(temp.getSup_dev_id().getType());
                add.setBrand(temp.getSup_dev_id().getBrand());
                add.setModel(temp.getSup_dev_id().getModel());
                add.setFloor(1);
                room +=1;
                add.setRoom(room);

                session.persist(add);
                tx.commit();

                List<Device> new_dev = session.createQuery("from Device").list();

                tempp.setSent_to(session.get(Supplier.class, temp.getSup_dev_id().getSold_by().getSup_id()));
                tempp.setDev_id(new_dev.get(new_dev.size() - 1));
            }
        } else {
            tempp.setSent_to(null);
        }

        LocalDateTime currentLocalDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateTime = currentLocalDateTime.format(dateTimeFormatter);

        tempp.setDate_and_time(formattedDateTime);

        session.persist(tempp);

        tx.commit();
        session.close();
        sf.close();

        Table.getItems().remove(sel);
    }

    @FXML
    void diapprovereq(ActionEvent event) {
        Obj_FundRequest sel = Table.getSelectionModel().getSelectedItem();

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Device.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(FundRequest.class).addAnnotatedClass(WorkCondn.class).addAnnotatedClass(Supplier.class).addAnnotatedClass(SupplierDevice.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        FundRequest temp = session.get(FundRequest.class,sel.getRequest_id());
        temp.setApprove_status("Disapproved");
        session.update(temp);

        tx.commit();
        session.close();
        sf.close();

        Table.getItems().remove(sel);
    }

    @FXML
    void gotohomeaccounts(ActionEvent event) throws IOException {
      HelloApplication.loadStage("accounts_after_login.fxml");
    }
}
