package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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

public class AdminViewUsers implements Initializable {
    List<Obj_AdminViewUsers> createlist() {

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(UserHistory.class).addAnnotatedClass(Device.class).addAnnotatedClass(User.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(WorkCondn.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        String qry = "from User";
        List<User> temp = session.createQuery(qry).list();
        List<Obj_AdminViewUsers> result = new ArrayList<Obj_AdminViewUsers>();

        for(User u : temp) {
            Obj_AdminViewUsers random = new Obj_AdminViewUsers(u.getUser_id(),u.getFirst_name() + u.getLast_name(),u.getEmail_id(),u.getContact_no());
            result.add(random);
        }

        tx.commit();
        session.close();
        sf.close();

        return result;
    }

    @FXML
    private TableView<Obj_AdminViewUsers> Table;

    @FXML
    private Label Users;

    @FXML
    private Button add;

    @FXML
    private Button back;

    @FXML
    private Button clear;

    @FXML
    private TableColumn<Obj_AdminViewUsers, String> contactno;

    @FXML
    private TableColumn<Obj_AdminViewUsers, String> emailid;

    @FXML
    private TableColumn<Obj_AdminViewUsers, String> name;

    @FXML
    private TextField opensthetype;

    @FXML
    private Button remove;

    @FXML
    private Button search;

    @FXML
    private TableColumn<Obj_AdminViewUsers, Integer> uid;

    ObservableList<Obj_AdminViewUsers> List = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        List<Obj_AdminViewUsers> temp = createlist();
        for(Obj_AdminViewUsers o : temp) {
            List.add(o);
        }

        name.setCellValueFactory(new PropertyValueFactory<Obj_AdminViewUsers,String>("full_name"));
        contactno.setCellValueFactory(new PropertyValueFactory<Obj_AdminViewUsers,String>("contact"));
        uid.setCellValueFactory(new PropertyValueFactory<Obj_AdminViewUsers,Integer>("user_id"));
        emailid.setCellValueFactory(new PropertyValueFactory<Obj_AdminViewUsers,String>("email_id"));

        Table.setItems(List);
    }

    @FXML
    void adds(ActionEvent event) throws IOException{
        HelloApplication.loadStage("admin_add_users.fxml");

    }

    @FXML
    void backtoadminhomepage(ActionEvent event) throws IOException {
        HelloApplication.loadStage("admin_home_page.fxml");

    }

    @FXML
    void refreshthepage(ActionEvent event) throws IOException{
        HelloApplication.loadStage("admin_view_users.fxml");

    }

    @FXML
    void removes(ActionEvent event) throws IOException {
        Obj_AdminViewUsers temp  = Table.getSelectionModel().getSelectedItem();

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(UserHistory.class).addAnnotatedClass(Device.class).addAnnotatedClass(User.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(WorkCondn.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        User rem = session.get(User.class,temp.getUser_id());
        session.remove(rem);

        tx.commit();
        session.close();
        sf.close();

        HelloApplication.loadStage("admin_view_users.fxml");
    }

    @FXML
    void searchbytype(ActionEvent event) {
        List.clear();
        int id=Integer.parseInt(opensthetype.getText());

        List<Obj_AdminViewUsers> temp = createlist();
        for(Obj_AdminViewUsers o : temp) {
            if(o.getUser_id() == id) {
                List.add(o);
            }
        }

        Table.setItems(List);
    }

}