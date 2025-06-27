package com.example.javatermproject.serverpackage;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class server {

    public static ArrayList<movies> moviesList = new ArrayList<>();
    public static ArrayList<productions> productions = new ArrayList<>();
    //    public ArrayList<production_company> production_companies = new ArrayList<production_company>();
    //public ArrayList<poster> movieposters = new ArrayList<>();


    static final String INPUT_MOVIE_FILE_NAME = "movies.txt";
    static final String INPUT_MOVIE_TRAILER = "movieResources.txt";
    public static String thenameof;

    public void readFIle() throws IOException {

    }

    public static void main(String[] args) throws Exception {
        movieDatabase movieDatabase = new movieDatabase(INPUT_MOVIE_FILE_NAME,INPUT_MOVIE_TRAILER);


        for(int i=0;i<movieDatabase.moviesList.size();i++) {
           moviesList.add(movieDatabase.moviesList.get(i));
       }
       // System.out.println("hello after first step  " + moviesList.size() );
        //System.out.println(moviesList.get(34).poster);
        productionListGenerator();

        ServerSocket server = new ServerSocket(3333);
        System.out.println("Server started");
        String string;
        HashMap<String, SocketWrapper> clientMap = new HashMap<>();

        new Thread(() -> {
            while(true) {
                //System.out.println("in the new ");
                Scanner scanner = new Scanner(System.in);
                String str = scanner.nextLine();
                if (str.equals("off")) {
                    movieDatabase m = new movieDatabase();
                    m.moviesList.addAll(moviesList);
                    try {
                        m.writefile(INPUT_MOVIE_FILE_NAME,INPUT_MOVIE_TRAILER);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Server is exiting now");
                    System.exit(0);
                }
            }
        }).start();

        while (true) {
            Socket clientSocket = server.accept();
            SocketWrapper client = new SocketWrapper(clientSocket);
            System.out.println("A client just connected");



            new Thread(() -> {
                try {
                    ArrayList<productions> nst = new ArrayList<>();
                    nst.addAll(getPros());
                    client.write(new DataWrapper("getPro", nst));

                    Object data1 = client.read();

                    DataWrapper dwr = (DataWrapper) data1;
                    String name = (String) dwr.data;
                    thenameof = name;

                    clientMap.put(name,client);

                    while(true) {

                        Object data = client.read();

                        DataWrapper dw = (DataWrapper) data;

                        //System.out.println(dw.command + " in the server");
                        if(dw.command.equals("getAllMovies")) {
                            String PROname = (String) dw.data;
                            movieDatabase md = new movieDatabase();
                            md.moviesList.addAll(moviesList);
                            searchfile src = new searchfile(md);
                            movieDatabase m2 = src.searchProductionCompany(PROname);
                            ArrayList<movies> a = new ArrayList<movies>();
                            a.addAll(m2.moviesList);
                            client.write(new DataWrapper("getAllMovies",a));

                        }
                        else if(dw.command.equals("theName")) {
                            String name1 = (String) dw.data;
                            clientMap.put(name1,client);
                            thenameof = name1;

                        }
                        else if(dw.command.equals("add")) {
                            movies movienew = (movies) dw.data;
                            moviesList.add(movienew);
                            movieDatabase md = new movieDatabase();
                            md.moviesList.addAll(moviesList);

                        }
                        else if(dw.command.equals("transfer")) {
                            moviechange mcg = (moviechange) dw.data;
                            String nm = mcg.movieName;
                            String produc = mcg.production;
                            moviechanger(nm,produc);
                            SocketWrapper recc = clientMap.get(produc);
                            if(recc != null) {
                                recc.write(new DataWrapper("transfer",nm));
                            }


                        }
                        else if(dw.command.equals("namechk")) {
                            String namer = (String) dw.data;
                            boolean bol = chk(namer);
                            client.write(new DataWrapper("namechk",bol));
                        }

                    }
                } catch (IOException e) {
                    System.out.println("Client disconnected");
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        client.closeConnection();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();

        }



    }

    public static<moviesList> ArrayList<movies> ss() {
        return moviesList;
    }

    public static void main1() throws Exception {
        movieDatabase movieDatabase = new movieDatabase(INPUT_MOVIE_FILE_NAME,INPUT_MOVIE_TRAILER);
        //System.out.println("hello after first step");
        //moviesList = movieDatabase.moviesList;
        for(int i=0;i<movieDatabase.moviesList.size();i++) {
            moviesList.add(movieDatabase.moviesList.get(i));
        }
        productionListGenerator();
        //System.out.println(moviesList.get(34).poster);


    }

    public static void productionListGenerator() {
        String t;
        int k;
        int i;
        for(i=0;i<moviesList.size();i++) {

            t = moviesList.get(i).getProduction_company();
            for(k=0;k<productions.size();k++) {

                if(productions.get(k).name.equalsIgnoreCase(t)) {

                    k=productions.size()+5;
                }

            }
            if(k==productions.size()) {
                productions p = new productions(t,"123");
                productions.add(p);
                //System.out.println(p.name + "   " + p.password);
                // System.out.println(t + productions.size());
            }

        }
    }


    public static ArrayList<productions> getPros() {

        return productions;
    }

    public static boolean passwordCheck (String name,String pa) {
        String s="";
        for(int i=0;i<productions.size();i++) {
            if(productions.get(i).name.equalsIgnoreCase(name)){
               s =  productions.get(i).password;
            break;
            }
        }
        if(s.equals(pa))
            return true;
        else return false;
    }
    public static void moviechanger(String nm, String produc) {
        int j=0;
        for(int i=0;i<moviesList.size();i++) {
            if(moviesList.get(i).getTitle().equals(nm))
            {
                moviesList.get(i).setProduction_company(produc);
                j = i;
                break;
            }

        }
        //System.out.println(moviesList.get(j).getProduction_company());
    }
    public static boolean chk(String namer) {
        boolean bol = true;
        for(int i=0;i<moviesList.size();i++) {
            if (moviesList.get(i).getTitle().equalsIgnoreCase(namer))
            {
                bol = false;
                break;
            }
        }
        return bol;
    }
}