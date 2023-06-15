package  org.example;

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

import java.net.URL;
import java.io.IOException;
import java.util.ResourceBundle;

public class SupplierSaleHistory implements Initializable {

    @FXML
    private Button Back;

    @FXML
    private TableColumn<PTransactions, Integer> amount;

    @FXML
    private Button clear;

    @FXML
    private TableColumn<PTransactions,String> date;



    @FXML
    private TableView<PTransactions> Table;

    @FXML
    private Button search;

    @FXML
    private TextField searchtextfield;

    @FXML
    private TableColumn<PTransactions, Integer> tid;

    @FXML
    private TableColumn<PTransactions, String> model;

    @FXML
    private Label toplabel;

    @FXML
    private TableColumn<PTransactions, String> type;

    ObservableList<PTransactions> List = FXCollections.observableArrayList();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        tid.setCellValueFactory(new PropertyValueFactory<PTransactions,Integer>("trans_id"));
        model.setCellValueFactory(new PropertyValueFactory<PTransactions,String>("model"));
        date.setCellValueFactory(new PropertyValueFactory<PTransactions,String>("date_and_time"));
        type.setCellValueFactory(new PropertyValueFactory<PTransactions,String>("type"));
        amount.setCellValueFactory(new PropertyValueFactory<PTransactions,Integer>("amount"));
        Table.setItems(List);

    }

    @FXML
    void backtosupphome(ActionEvent event) throws IOException {

        HelloApplication.loadStage("supplier_after_login.fxml");

    }

    @FXML
    void refreshthesamepage(ActionEvent event) throws IOException {

        HelloApplication.loadStage("supplier_sale_history.fxml");

    }

    @FXML
    void searchimnprhistory(ActionEvent event) {

    }

}
