package org.example;



        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.fxml.Initializable;
        import javafx.scene.Parent;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.control.PasswordField;
        import javafx.scene.control.TextField;
        import java.io.IOException;
        import java.util.List;
        import java.util.Objects;
        import javafx.application.Platform;

        import org.hibernate.Session;
        import org.hibernate.SessionFactory;
        import org.hibernate.Transaction;
        import org.hibernate.cfg.Configuration;
        import javafx.stage.Stage;
        import java.net.URL;
        import java.util.ResourceBundle;

public class ManagersLoginPage  {


    @FXML // fx:id="exittoscreen"
    private Button exittoscreen; // Value injected by FXMLLoader

    @FXML // fx:id="login"
    private Button loginmanagers; // Value injected by FXMLLoader

    @FXML  //fx:id="password"
    private PasswordField password;

    @FXML  //fx:id="uname"
    private TextField uname;

    @FXML //fx:id="errorsho"
    private Label errorsho;;



    @FXML
    void closewindowloginpage(ActionEvent event) throws IOException {
        HelloApplication.loadStage("log_in_as.fxml");
        }


        @FXML
        void login () throws IOException {

            int userid = Integer.parseInt(uname.getText());
            String Password = password.getText();
            HelloApplication.login_id = userid;

            Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Person.class).addAnnotatedClass(Manager.class).addAnnotatedClass(Branch.class);
            SessionFactory sf = con.buildSessionFactory();
            Session session = sf.openSession();
            Transaction tx = session.beginTransaction();

            Manager login_instance = new Manager();
            login_instance = session.get(Manager.class, userid);
            String qry = "from Branch where manager_id = " + login_instance.getManager_id();
            List<Branch> temp = session.createQuery(qry).list();

            if(login_instance != null) {
                if (Objects.equals(login_instance.getDigest(), Password)) {
                    if (temp.get(0).getBranch_id() == 1) {
                        HelloApplication.loadStage("inv_manager_home_page.fxml");
                    } else if (temp.get(0).getBranch_id() == 2) {
                        HelloApplication.loadStage("accounts_after_login.fxml");
                        //call branch 2 page
                    } else if (temp.get(0).getBranch_id() == 3) {
                        //call branch 3 page
                    }
                } else {
                    //wrong password
                    errorsho.setText("Password is wrong");
//                System.out.println("Password is wrong");
                }
            } else {
                errorsho.setText("UserID not found");
            }

            tx.commit();
            session.close();
            sf.close();

        }

    }



