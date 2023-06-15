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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CreatePurchaseRequest implements Initializable {

    List<SupplierDevice> createlist() {

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Device.class).addAnnotatedClass(SupplierDevice.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(WorkCondn.class).addAnnotatedClass(FundRequest.class).addAnnotatedClass(Supplier.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        List<SupplierDevice> result = session.createQuery("from SupplierDevice").list();
        for(SupplierDevice sd : result) {
            sd.setSid(sd.getSold_by().getSup_id());
        }

        tx.commit();
        session.close();
        sf.close();

        return result;
    }
    @FXML
    private TableView<SupplierDevice> Table;

    @FXML
    private Button back;

    @FXML
    private Button clear;

    @FXML
    private TableColumn<SupplierDevice, Integer> cost;

    @FXML
    private TableColumn<SupplierDevice, String> brand;

    @FXML
    private TableColumn<SupplierDevice, String > model;

    @FXML
    private TableColumn<SupplierDevice, String> type;

    @FXML
    private Button purchase;

    @FXML
    private Button search;

    @FXML
    private TableColumn<SupplierDevice, Integer> sid;

    @FXML
    private TextField textfields;

    ObservableList<SupplierDevice> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        List<SupplierDevice> temp = createlist();
        for(SupplierDevice sd : temp) {
            list.add(sd);
        }

        cost.setCellValueFactory(new PropertyValueFactory<SupplierDevice,Integer>("cost"));
        brand.setCellValueFactory(new PropertyValueFactory<SupplierDevice,String>("brand"));
        model.setCellValueFactory(new PropertyValueFactory<SupplierDevice,String>("model"));
        type.setCellValueFactory(new PropertyValueFactory<SupplierDevice,String>("type"));
        sid.setCellValueFactory(new PropertyValueFactory<SupplierDevice,Integer>("sid"));

        Table.setItems(list);

    }

    @FXML
    void backtohome(ActionEvent event) throws IOException {
      HelloApplication.loadStage("inv_manager_home_page.fxml");
    }

    @FXML
    void purchases(ActionEvent event) throws IOException {
        SupplierDevice tempa = Table.getSelectionModel().getSelectedItem();
        System.out.println(tempa);

        Parent pane = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("set_quantity.fxml")));

        FXMLLoader loader =new FXMLLoader((getClass().getResource("set_quantity.fxml")));
        pane = loader.load();

        SetQuantity setquantity = loader.getController();
        setquantity.pass(tempa);
        HelloApplication.primaryStage.getScene().setRoot(pane);


    }

    @FXML
    void refershthesamepage(ActionEvent event) throws IOException {
        HelloApplication.loadStage("create_purchase_request.fxml");
    }

    @FXML
    void searchesthepages(ActionEvent event) {
        list.clear();

        List<SupplierDevice> temp = createlist();
        for(SupplierDevice sd : temp) {
            list.add(sd);
        }

        String search = textfields.getText();
        for(SupplierDevice sd : list) {
            if(!sd.getType().contains(search)) {
                list.remove(sd);
            }
        }

        Table.setItems(list);
    }

}