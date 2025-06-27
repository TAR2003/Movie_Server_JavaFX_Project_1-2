package com.example.javatermproject;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.javatermproject.serverpackage.DataWrapper;
import com.example.javatermproject.serverpackage.moviechange;
import com.example.javatermproject.serverpackage.productions;
import com.example.javatermproject.serverpackage.movieDatabase;
import com.example.javatermproject.serverpackage.sort;
import com.example.javatermproject.serverpackage.searchfile;

import com.example.javatermproject.serverpackage.movies;


import com.example.javatermproject.serverpackage.passcheck;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Application {
    public static ArrayList<movies> moviesList = new ArrayList<>();
    public static ArrayList<productions> productionsW = new ArrayList<>();
    public static ArrayList<String> productions = new ArrayList<>();
    public static String proName;
    public static String mode="menu";
    public static long totalProfitoftheCompany;
    public static boolean bolle = true;

    public static SocketWrapper server;
    public static ActionEvent act;
    private static ModuleLayer.Controller controller;
    public static Main fxm;

    static {
        try {
            server = new SocketWrapper("127.0.0.1",3333);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-scene.fxml"));
        //controller = fxmlLoader.getController();
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setResizable(true);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {


        Object datum1 = server.read();
        DataWrapper dw1 = (DataWrapper) datum1;
        productionsW.addAll((ArrayList<productions>) dw1.data);
        productionList();
        getfrom();
        launch();
        System.out.println("logged + out");
    }
    public static void setTo() throws IOException {
        server.write(new DataWrapper("theName", proName));
        getListfromserver();


    }

    public static void getfrom() {
        new Thread(() -> {
            try {
                while(true) {
                    Object data = server.read();
                    DataWrapper dw = (DataWrapper) data;

                    if(dw.command.equals("getAllMovies")) {


                        ArrayList<movies> array = new ArrayList<>();
                        array =  (ArrayList<movies>)dw.data;
                        moviesList = array;
                        //System.out.println(dw.command + "  here in the special input          "  + moviesList.size());
                        movieDatabase md1 = new movieDatabase();
                        md1.moviesList.addAll(moviesList);
                       // md1.printallmovies();
                    }
                    else if(dw.command.equals("namechk")) {
                        boolean bol = (boolean) dw.data;
                        bolle = bol;
                    }
                    else if(dw.command.equals("transfer")) {
                        String jkl = (String) dw.data;
                        //System.out.println("here in the trnsfer");
                        //objt.initiate();

                        //fxm.getController().;
                        //controller.refresh();



                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    server.closeConnection();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }).start();
    }
    public static void productionList() {
        for(int i=0;i<productionsW.size();i++) {
            productions.add(productionsW.get(i).name);
        }

    }

//    public static void in() throws Exception {
//        ss(2);
//    }
    public static void Transfer(String Mname, String production) {

    }

//    public static  void ss(int j) throws Exception {
//        server.main1();
//        for(int i=0;i<server.moviesList.size();i++)
//        {
//            moviesList.add(server.moviesList.get(i));
//        }
//
//        for(int i=0;i<server.productions.size();i++)
//        {
//           productions.add(server.productions.get(i));
//        }
//
//       // System.out.println(productions);
//
//
//
//
//    }

    public static boolean passwordCheck(String name, String password) throws IOException {
            String s = getPassword(name);
           // getListfromserver();
            proName = name;
         //System.out.println("the password of " + name + " is + " + s);
            if(password.equals(s)) {

                setTo();
                //System.out.println(true + " yes");
                return true;
            }
            else {
                //System.out.println(false);
                return false;
            }

       //return true;
    }
    public static String getPassword(String name) throws IOException {
        String s= "";
        //getListfromserver();
        for(int i=0;i<productions.size();i++) {

            if(productionsW.get(i).name.equalsIgnoreCase(name)){
               s =  productionsW.get(i).password;
            break;
            }
        }
        return s;
    }
    public static void proList() throws IOException {

        getListfromserver();
    }

    public static long totalProfit() throws IOException {
        getListfromserver();
        movieDatabase m = new movieDatabase();
        m.moviesList = moviesList;
        searchfile s = new searchfile(m);
        return s.getTotalProfit(proName);
    }
    public static ArrayList<movies> recentList() throws IOException {
        getListfromserver();

        movieDatabase m = new movieDatabase();
        m.moviesList = moviesList;
        searchfile s = new searchfile(m);
        return s.getMostRecentMovie(proName).moviesList;
    }
    public static ArrayList<movies> maxList() throws IOException {


        getListfromserver();
        movieDatabase m = new movieDatabase();
        m.moviesList = moviesList;
        searchfile s = new searchfile(m);
        return s.getMaximumRevenue(proName).moviesList;
    }
    public static void transferMovies(String moviename, String productionCompany1) throws IOException {
        moviechange mv = new moviechange(moviename,productionCompany1);
        server.write(new DataWrapper("transfer",mv));
        for(int i=0;i<moviesList.size();i++) {
            if(moviesList.get(i).getTitle().equals(moviename)) {
                moviesList.remove(moviesList.get(i));
                break;
            }
        }


    }

    public static boolean checkM(String name) throws IOException {
        server.write(new DataWrapper("namechk",name));
        return bolle;
    }
    public static void addMovie(String Title, int year_of_release, String Genre1, String Genre2, String Genre3, int Running_time, long Budget, long Revenue, String poster, String trailer) throws IOException {

        //getListfromserver();
        movies mit = new movies(Title, year_of_release, Genre1, Genre2,  Genre3, Running_time, proName,  Budget, Revenue, poster,  trailer);
        moviesList.add(mit);
        server.write(new DataWrapper("add",mit));
        //System.out.println("after adding");

    }

    public static void getListfromserver() throws IOException {
        server.write(new DataWrapper("getAllMovies", proName));
    }

    public static void reloadBack() throws IOException {
        Stage stage = (Stage)((Node)act.getSource()).getScene().getWindow();
        //Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("production-company.fxml"));
        ProductionCompanyController pk = ProductionCompanyController.pback();
        pk = fxmlLoader.getController();
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("production");
        stage.setScene(scene);
        stage.show();
    }
}