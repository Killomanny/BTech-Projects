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

import java.net.URL;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class
UserRequestsInv implements Initializable {

    List<Obj_UserRequestInv> createlist() {

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Device.class).addAnnotatedClass(User.class).addAnnotatedClass(BorrowRequest.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(WorkCondn.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        List<BorrowRequest> temp = session.createQuery("from BorrowRequest where approve_status = \"pending\"").list();
        List<Obj_UserRequestInv> result = new ArrayList<Obj_UserRequestInv>();

        for(BorrowRequest br : temp) {
            Obj_UserRequestInv uri = new Obj_UserRequestInv(br.getUser_id().getUser_id(),br.getDevice_id().getDev_id(),br.getRequest_date());
            result.add(uri);
        }

        tx.commit();
        session.close();
        sf.close();

        return result;
    }

    @FXML
    private Button approve;

    @FXML
    private Button backtohomeinv;

    @FXML
    private Label bandrinv;

    @FXML
    private Button disaaprove;

    @FXML
    private TableColumn<Obj_UserRequestInv, Integer> dev_id;

    @FXML
    private TableColumn<Obj_UserRequestInv, String> request_date;

    @FXML
    private TableView<Obj_UserRequestInv> Table;

    @FXML
    private TableColumn<Obj_UserRequestInv, Integer> user_id;

    ObservableList<Obj_UserRequestInv> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

      dev_id.setCellValueFactory(new PropertyValueFactory<Obj_UserRequestInv,Integer>("dev_id"));
      user_id.setCellValueFactory(new PropertyValueFactory<Obj_UserRequestInv,Integer>("user_id"));
      request_date.setCellValueFactory(new PropertyValueFactory<Obj_UserRequestInv,String>("request_date"));

      List<Obj_UserRequestInv> temp = createlist();
      for(Obj_UserRequestInv br : temp) {
          list.add(br);
      }

      Table.setItems(list);
    }

    @FXML
    void deletedfromrequests(ActionEvent event) {
        Obj_UserRequestInv tempa=Table.getSelectionModel().getSelectedItem();
        int tempar = Table.getSelectionModel().getSelectedIndex();

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Device.class).addAnnotatedClass(User.class).addAnnotatedClass(BorrowRequest.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(WorkCondn.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        List<BorrowRequest> temp = session.createQuery("from BorrowRequest where user_id = "+tempa.getUser_id()+" and request_date = \""+tempa.getRequest_date() + "\"" + " and device_id = " + tempa.getDev_id()).list();
        BorrowRequest instance = temp.get(temp.size() - 1);
        instance.setApprove_status("Disapproved");

        session.update(instance);

        tx.commit();
        session.close();
        sf.close();
        Table.getItems().remove(tempar);

    }

    @FXML
    void itwillgotoborrowed(ActionEvent event) throws ParseException, IOException {
        Obj_UserRequestInv tempa=Table.getSelectionModel().getSelectedItem();
        int tempar = Table.getSelectionModel().getSelectedIndex();


        Table.getItems().remove(tempar);

        Parent pane = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("due_date_selector.fxml")));

        FXMLLoader loader =new FXMLLoader((getClass().getResource("due_date_selector.fxml")));
        pane = loader.load();

        DueDateSelector dueDateSelector = loader.getController();
        HelloApplication.primaryStage.getScene().setRoot(pane);
        dueDateSelector.pass(tempa);
    }

    @FXML
    void openhomeinvreq(ActionEvent event) throws IOException {
        HelloApplication.loadStage("inv_manager_home_page.fxml");

    }


}
