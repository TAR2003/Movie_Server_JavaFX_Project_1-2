package com.example.javatermproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class showMovieController implements Initializable {

    public String Title,Genre1,Genre2,Genre3,Production_company;
    public int year_of_release, Running_time;
    public long Budget, Revenue, profit;
    public String poster,trailer,backDrop;
    @FXML
    ImageView MoviePoster;
    @FXML
    Label pro1,revL,budG,yearR,gr1,gr2,gr3,movieTitle,time;


    public
    @FXML
    void showTrailer (ActionEvent actionEvent) {
        Stage stage = new Stage();
        WebView w = new WebView();
        w.getEngine().load(trailer);

        w.setPrefSize(800,600);
        stage.setScene(new Scene(w));
        stage.show();
    }


    public void show(String t, String g1,String g2, String g3, String pro, String poster1, String trailer1, String backDrop1, int y, int r, long bud, long rev, long profit1) {
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
        //System.out.println("here also");
        if(poster.equalsIgnoreCase("")){
            System.out.println("ewrror there os mo ");
        }
        else{
            Image myImage1 = new Image(poster);
            MoviePoster.setImage(myImage1);}
        movieTitle.setText(Title);

        gr1.setText("Genre: " + Genre1);
        gr2.setText("            " + Genre2);
        gr3.setText("            " + Genre3);

        time.setText("Running Time: " + Running_time + " minutes");
        yearR.setText("Year of Release: " + year_of_release);
        budG.setText("Budget: " + Budget);
        revL.setText("Revenue: " + Revenue);
        pro1.setText("Production Company: " + Production_company);


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        budG.setText("budget is : " + Budget);
    }

    public void displayName(String poster) {
        //System.out.println(poster + "here in teh aonthre pabne");
    }
}



