package com.example.javatermproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddMoviesController implements Initializable {

    public boolean b = true;
    @FXML
    Button check,OK,back;
    @FXML
    TextField newtitle, newg1,newg2,newg3, newruntime,newrelyear, newbudget,newrev,newtrailer;
    @FXML
    Label announce;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    Main.mode = "addmovie";
    }
    public void checkit(ActionEvent actionEvent) throws IOException {
        checkt();
        checkt();
    }
    public void checkt() throws IOException {
        boolean b = Main.checkM(newtitle.getText());
        b = Main.bolle;
        if(b){
            announce.setText("It's OK");
            b = true;

        }
        else {
            announce.setText("there is another movie with the same name");
            b= false;
        }
    }


    public void clickBack1(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        //Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("production-company.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        //}
    }
    public void confirmed(ActionEvent actionEvent) throws IOException {
        String Title,Genre1,Genre2,Genre3,Production_company,poster,trailer;
        int year_of_release,Running_time;
        long Budget,Revenue;
        int i1=0,i2=0,i3=0,i4=0,i5=0,i6=0;
        Title  = newtitle.getText();
        if(!Title.equalsIgnoreCase(""))
            i1 = 1;
        Genre1 = newg1.getText();
        if(!Genre1.equalsIgnoreCase(""))
            i2 = 1;
        Genre2 = newg2.getText();
        Genre3 = newg3.getText();
       year_of_release = Integer.parseInt(newrelyear.getText());
       if(year_of_release!=0)
           i3=1;
       Running_time = Integer.parseInt(newruntime.getText());
        if(Running_time!=0)
            i4=1;
        Budget = Long.parseLong(newbudget.getText());
        if(Budget!=0)
            i5=1;
        Revenue = Long.parseLong(newrev.getText());
        if(Revenue!=0)
            i6=1;
        poster = "";
        trailer = newtrailer.getText();
       int sum = i1+i2+i3+i4+i5+i6;
       if(sum==6 && b==true){
        Main.addMovie(Title, year_of_release, Genre1, Genre2,  Genre3, Running_time,  Budget, Revenue, poster,  trailer);
        clickBack1(actionEvent);
       }





    }

}