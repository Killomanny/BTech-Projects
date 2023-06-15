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

import java.net.URL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminViewManagers implements Initializable {
    List<Obj_AdminViewMans> createlist() {

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(UserHistory.class).addAnnotatedClass(Device.class).addAnnotatedClass(User.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(WorkCondn.class).addAnnotatedClass(Manager.class).addAnnotatedClass(Branch.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        List<Manager> temp = session.createQuery("from Manager").list();
        List<Obj_AdminViewMans> result = new ArrayList<Obj_AdminViewMans>();

        for(Manager u : temp) {
            String qry = "from Branch where manager_id = " + u.getManager_id();
            List<Branch> b = session.createQuery(qry).list();

            Obj_AdminViewMans random = new Obj_AdminViewMans(u.getManager_id(),u.getFirst_name() +" "+ u.getLast_name(),u.getEmail_id(),u.getContact_no(),b.get(0).getBranch_name());
            result.add(random);
        }

        tx.commit();
        session.close();
        sf.close();

        return result;
    }
    @FXML
    private TableView<Obj_AdminViewMans> Table;

    @FXML
    private Button add;

    @FXML
    private Button back;

    @FXML
    private Button clear;

    @FXML
    private TableColumn<Obj_AdminViewMans, String> contactno;


    @FXML
    private TableColumn<Obj_AdminViewMans, String> emailid;

    @FXML
    private Label managers;

    @FXML
    private TableColumn<Obj_AdminViewMans,String> managertype;

    @FXML
    private TableColumn<Obj_AdminViewMans, Integer> mid;

    @FXML
    private TableColumn<Obj_AdminViewMans, String> name;

    @FXML
    private TextField opensthetype;

    @FXML
    private Button remove;

    @FXML
    private Button search;

    ObservableList<Obj_AdminViewMans> List = FXCollections.observableArrayList();
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle){
        List<Obj_AdminViewMans> temp = createlist();
        for(Obj_AdminViewMans o : temp) {
            List.add(o);
        }

        name.setCellValueFactory(new PropertyValueFactory<Obj_AdminViewMans,String>("full_name"));
        contactno.setCellValueFactory(new PropertyValueFactory<Obj_AdminViewMans,String>("contact"));
        emailid.setCellValueFactory(new PropertyValueFactory<Obj_AdminViewMans,String>("email"));
        mid.setCellValueFactory(new PropertyValueFactory<Obj_AdminViewMans,Integer>("manager_id"));
        managertype.setCellValueFactory(new PropertyValueFactory<Obj_AdminViewMans,String>("branch"));

        Table.setItems(List);
    }

    @FXML
    void adds(ActionEvent event) throws IOException {
        HelloApplication.loadStage("admin_add_manager.fxml");

    }

    @FXML
    void backtoadminhome(ActionEvent event) throws IOException {
        HelloApplication.loadStage("admin_home_page.fxml");

    }

    @FXML
    void refreshthepage(ActionEvent event) throws IOException{
        HelloApplication.loadStage("admin_view_managers.fxml");


    }

    @FXML
    void removes(ActionEvent event) throws IOException {
        Obj_AdminViewMans temp =Table.getSelectionModel().getSelectedItem();

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(UserHistory.class).addAnnotatedClass(Branch.class).addAnnotatedClass(User.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(WorkCondn.class).addAnnotatedClass(Manager.class).addAnnotatedClass(Device.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Manager rem = session.get(Manager.class,temp.getManager_id());
        List<Branch> tempb = session.createQuery("from Branch where manager_id = " + temp.getManager_id()).list();
        tempb.get(0).setManager_id(null);
        session.update(tempb.get(0));
        session.remove(rem);

        tx.commit();
        session.close();
        sf.close();

        HelloApplication.loadStage("admin_view_managers.fxml");

    }

    @FXML
    void searchbytype(ActionEvent event) {
        List.clear();

        int id =Integer.parseInt(opensthetype.getText());

        List<Obj_AdminViewMans> temp = createlist();
        for(Obj_AdminViewMans o : temp) {
            if(o.getManager_id() == id) {
                List.add(o);
            }
        }
        Table.setItems(List);

    }

}

