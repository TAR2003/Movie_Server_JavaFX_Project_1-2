package com.example.javatermproject;


import com.example.javatermproject.serverpackage.movies;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class moviePanesController implements Initializable {
    private String Title,Genre1,Genre2,Genre3,Production_company;
    private int year_of_release, Running_time;
    private long Budget, Revenue, profit;
    public String poster,trailer,backDrop;
    @FXML
    private Parent root;
    @FXML
    ImageView thePoster;
    @FXML
    Label theTitle;

    @FXML
    Label theGenre1;
    @FXML
    Label runTime;

    @FXML
    Label yearRel;
    @FXML
    Label budgetL;
    @FXML
    Label revL;

    Stage stage;
    Scene scene;
    public void movie(String t, String g1,String g2, String g3, String pro, String poster1, String trailer1, String backDrop1, int y, int r, long bud, long rev, long profit1) {
        Title = t;
        Genre1 = g1;
        Genre2 = g2;
        Genre3 = g3;
        Production_company = pro;
        year_of_release = y;
        Running_time = r;
        Budget = bud;
        Revenue = rev;
        profit = profit1;
        poster = poster1;
        trailer = trailer1;
        backDrop = backDrop1;
        if(poster.equalsIgnoreCase("")){
            System.out.println("error there is no picture");
        }
        else{
            Image myImage1 = new Image(poster);
            thePoster.setImage(myImage1);}
        theTitle.setText(Title);
        theGenre1.setText("Genre: " + Genre1 + "        " + Genre2 + "       " + Genre3);
        runTime.setText("Running Time: " + Running_time + " minutes");
        yearRel.setText("Year of Release: " + year_of_release);
        budgetL.setText("Budget: " + Budget);
        revL.setText("Revenue: " + Revenue);


    }


    public static void ss(String s) {
        //poster = s;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void mouseClick(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("showMovie.fxml"));
        root = fxmlLoader.load();
        showMovieController s = fxmlLoader.getController();
        s.show(Title, Genre1,Genre2, Genre3, Production_company, poster, trailer, backDrop, year_of_release, Running_time, Budget, Revenue, profit);
        Stage stage = new Stage();
        Scene scene = new Scene(root, 800, 600);
        stage.setResizable(true);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }



    public void click() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("showMovie.fxml"));
        Parent root = loader.load();
//        showMovieController scene2Controller = loader.getController();
//        scene2Controller.displayName(poster);
         showMovieController s = loader.getController();
         s.show(Title, Genre1,Genre2, Genre3, Production_company, poster, trailer, backDrop, year_of_release, Running_time, Budget, Revenue, profit);
        //root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        Stage stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void transferMovie(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("transferMovie.fxml"));
        Parent root = loader.load();
//        showMovieController scene2Controller = loader.getController();
//        scene2Controller.displayName(poster);
        TransferMovieController s = loader.getController();
        s.show(Title, Production_company);
        //root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
       // Stage stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
