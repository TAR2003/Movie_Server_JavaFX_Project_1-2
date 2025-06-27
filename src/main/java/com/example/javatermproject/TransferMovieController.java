package com.example.javatermproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class TransferMovieController implements Initializable {

    public ArrayList<String> p = new ArrayList<>();

    @FXML
    Stage stage;
    @FXML
    Stage current;
    @FXML
    private AnchorPane scenePane;
    @FXML
    ChoiceBox<String> choiceBox;
    @FXML
    Label movieName;
    public String name,product;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void show(String Title1, String production) throws IOException {

        p.addAll(Main.productions);
        p.remove(production);
        name = Title1;
        product = production;

        choiceBox.getItems().addAll(p);
        movieName.setText(Title1);
        //current = (Stage) FXMLLoader.load(getClass().getResource("transferMovie.fxml"));




    }
    public void clickBack1(ActionEvent actionEvent) throws IOException {
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Transfer");
//        alert.setHeaderText("You are about to transfer the movie" + name);
//        alert.setContentText("Do you want to transfer???");
//        if(alert.showAndWait().get() == ButtonType.OK) {
            //stage = (Stage) actionEvent.getScene().getWindow();
//            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
//            System.out.println("You successfully transferred!");
//            stage.close();
//            closeIt();
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        //Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("production-company.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        //}


}
    public void click(ActionEvent actionEvent) throws IOException {
        clickBack1(actionEvent);
    }
    public void transfer(ActionEvent actionEvent) throws IOException {
        String p = choiceBox.getValue();
        Main.transferMovies(name,p);
        clickBack1(actionEvent);
    }

    private void closeIt() throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("currentWindow.fxml"));
        //Stage stage = (Stage) getScene().getWindow().close();
        // do what you have to do
       // stage.close();
       // Window window = (Node)getScene().getWindow();

        //current.close();
    }

}


