package com.example.javatermproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private String s = "hello there";

    @FXML
    private static int i = 0;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void initialize () throws InterruptedException {
                welcomeText.setText(s);

    }

    public void onClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        //Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        //stage.setX(700);
        //stage.setY(200);
        stage.show();
        String s;
        for(int i =0 ; i<100000 ; i++)
        {
            s = "here we are the" + i;

        }
        s = "no there";

    }
    public void exitB ( ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("log out????");
        alert.setHeaderText("You are about to log out");
        alert.setContentText("do you");
        if(alert.showAndWait().get() == ButtonType.OK) {
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.close();
        }




    }
    public void no(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        //Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("production-company.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setX(700);
        stage.setY(200);
        stage.show();
    }
}