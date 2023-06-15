package org.example;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HelloApplication extends Application {
    public static int login_id;
    public static String due_date;
    public static List<Integer> sup_dev_id = new ArrayList<Integer>();
    public static Stage primaryStage;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("log_in_as.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        primaryStage = stage ;
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("manager_login_page.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
//        primaryStage = stage ;
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
//    }


    public static void loadStage(String fxml) throws IOException{
        Parent pane = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource(fxml)));
        primaryStage.getScene().setRoot(pane);
    }

//    public static void loadStage(String fxml) throws IOException{
//        System.out.println("1");
//        Parent pane = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("../resources/org/example/"+fxml)));
//        primaryStage.getScene().setRoot(pane);
//    }





    public static void main(String[] args) {

        launch();
    }
}