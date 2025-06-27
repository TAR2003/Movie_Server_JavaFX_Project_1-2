package com.example.javatermproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.CharArrayReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoginScene implements Initializable {
    @FXML
    PasswordField password;

    @FXML
    ChoiceBox<String> productionCompany;
    @FXML
    Label msgBox;
    @FXML
    static ArrayList<String> s = new ArrayList<>();
    @FXML
    static String choice;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // ArrayList<String> s = new ArrayList<>();
        initiate();


    }
    public void initiate() {
//        if(Main.moviesList.size()==0) {
//            try {
//                Main.in();
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//
//
//            for (int i = 0; i < Main.productions.size(); i++) {
//                s.add(Main.productions.get(i).name);
//            }
//        }
        // productionCompany.setOnAction(this::);


        s = Main.productions;
        //System.out.println("IN initializer of login scene");
        productionCompany.getItems().addAll(s);
    }
    public static void secondInitiate() {

    }


    public void loginClick(ActionEvent actionEvent) throws IOException {

        //if(Main.moviesList.size()==0)

        String entered = password.getText();
        String name = productionCompany.getValue();
        //System.out.println(entered + "  " + name);
        if(Main.passwordCheck(name,entered)) {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        //Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("production-company.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        Main.act = actionEvent;
        stage.show();
        }
        else{
            msgBox.setText("Your entered password is wrong try again");
        }


    }




    public void getCompany(ActionEvent actionEvent) {
       //choice =
    }
    public void closeW(ActionEvent actionEvent) {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
