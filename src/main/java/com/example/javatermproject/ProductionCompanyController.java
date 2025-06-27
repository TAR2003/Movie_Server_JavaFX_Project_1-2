package com.example.javatermproject;


import com.example.javatermproject.serverpackage.movies;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductionCompanyController implements Initializable {

@FXML
public VBox vitems = null;
@FXML
    Label productionName;

    public ArrayList<movies> moviesList = new ArrayList<>();

@FXML
private ImageView poster;
public static ActionEvent actionEvent1;
public static ProductionCompanyController plk;


static int modeIndicator=1;

@FXML
Image myImage = new Image(getClass().getResourceAsStream("/Assets/Thumbnails/DieHardposter.jpg"));

WebView w;

    @Override
    public void initialize (URL location, ResourceBundle resources) {

        if(modeIndicator==0 || modeIndicator==1) {
            try {
                initiate();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else if(modeIndicator==2) {
            try {
                sR();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else if(modeIndicator==3) {
            try {
                sM();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
    public void initiate() throws IOException {
        //Main.objt = this;
        vitems.getChildren().clear();
        Main.proList();
        moviesList = Main.moviesList;
        //System.out.println("the size of the list in proiduction" + moviesList.size());
        Node[] node = new Node[moviesList.size()];
        for(int i=0;i< moviesList.size() ;i++) {


            try {
                movies movie = moviesList.get(i);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("moviepanes.fxml"));
                node[i] = loader.load();
                moviePanesController moviePanesController = loader.getController();
                moviePanesController.movie(movie.getTitle(),movie.getGenre1(), movie.getGenre2(),movie.getGenre3(),movie.getProduction_company(),movie.poster,movie.trailer,movie.backDrop,movie.getYear_of_release(),movie.getRunning_time(),movie.getBudget(),movie.getRevenue(),movie.getProfit());
            } catch (IOException e) {
                e.printStackTrace();
            }
            vitems.getChildren().add(node[i]);
        }
        //Image img = new Image(getClass().getResourceAsStream(Main.showMovie(34)));
        //Main.showMovie(34);


        //Image myImage1 = new Image(getClass().getResourceAsStream("/Assets/Thumbnails/ToyStoryposter.jpg"));
        //poster.setImage(myImage1);
        modeIndicator = 1;
        productionName.setText(Main.proName);
        Main.mode = "production";
        plk = this;
    }

    @FXML
    void searchMovieClick(ActionEvent actionEvent) {
        vitems.getChildren().clear();
        Node[] node = new Node[25];
        for(int i=0;i<moviesList.size() ;i++) {
            try {
                node[i] = FXMLLoader.load(getClass().getResource("moviepanes.fxml"));

            } catch (IOException e) {
                e.printStackTrace();
            }
            vitems.getChildren().add(node[i]);
        }
    }

    public void allMovies(ActionEvent actionEvent) throws IOException {
        initiate();
        initiate();
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        modeIndicator = 1;
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-scene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
//        LoginScene l = new LoginScene();
//        l.initiate();
    }

    public void searchRecent(ActionEvent actionEvent) throws IOException {
       sR();
        //Image img = new Image(getClass().getResourceAsStream(Main.showMovie(34)));
        //Main.showMovie(34);


    }
    public void sR() throws IOException {
        initiate();
        vitems.getChildren().clear();

        moviesList = Main.recentList();
        Node[] node = new Node[moviesList.size()];
        for(int i=0;i< moviesList.size() ;i++) {

            try {
                movies movie = moviesList.get(i);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("moviepanes.fxml"));
                node[i] = loader.load();
                moviePanesController moviePanesController = loader.getController();
                moviePanesController.movie(movie.getTitle(),movie.getGenre1(), movie.getGenre2(),movie.getGenre3(),movie.getProduction_company(),movie.poster,movie.trailer,movie.backDrop,movie.getYear_of_release(),movie.getRunning_time(),movie.getBudget(),movie.getRevenue(),movie.getProfit());
            } catch (IOException e) {
                e.printStackTrace();
            }
            vitems.getChildren().add(node[i]);
        }
        modeIndicator = 2;
    }


    public void searchMax(ActionEvent actionEvent) throws IOException {

         sM();
    }
    public void sM() throws IOException {
        initiate();
        vitems.getChildren().clear();

        moviesList = Main.maxList();
        Node[] node = new Node[moviesList.size()];
        for(int i=0;i< moviesList.size() ;i++) {


            try {
                movies movie = moviesList.get(i);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("moviepanes.fxml"));
                node[i] = loader.load();
                moviePanesController moviePanesController = loader.getController();
                moviePanesController.movie(movie.getTitle(),movie.getGenre1(), movie.getGenre2(),movie.getGenre3(),movie.getProduction_company(),movie.poster,movie.trailer,movie.backDrop,movie.getYear_of_release(),movie.getRunning_time(),movie.getBudget(),movie.getRevenue(),movie.getProfit());
            } catch (IOException e) {
                e.printStackTrace();
            }
            vitems.getChildren().add(node[i]);
        }
        modeIndicator = 3;
        //Image img = new Image(getClass().getResourceAsStream(Main.showMovie(34)));
        //Main.showMovie(34);
    }


    public void totalProfit(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("totalProfit.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 300);
        stage.setTitle("Profit");
        stage.setScene(scene);
        stage.show();
    }


    public void addMovie(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("addMovies.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Add Movie");
        stage.setScene(scene);
        stage.show();
    }

    public void reload(ActionEvent actionEvent) throws IOException {
        initiate();
        initiate();
    }

    public static ProductionCompanyController pback() {
        return plk;
    }

    public void closeW(ActionEvent actionEvent) {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
