package com.example.javatermproject.serverpackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.InterruptedException;
import java.io.BufferedWriter;


public class movieDatabase
{

    public ArrayList<movies> moviesList = new ArrayList<>();
    movieDatabase(String INPUT_FILE_NAME,String INPUT_MOVIE_TRAILER)throws  Exception, IOException, InterruptedException
    {
        //List<movies> moviesList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        BufferedReader br2 = new BufferedReader(new FileReader(INPUT_MOVIE_TRAILER));
        while (true) {
            String line = br.readLine();
            String line2 = br2.readLine();
            if (line == null) break;
            moviesList.add(new movies(line,line2));
        }
        br.close();
        br2.close();
    }
    public void addmovieDatabase(movies movie)
    {
        moviesList.add(movie);
    }
    public movieDatabase()
    {

    }
//    public void printallmovies()
//    {
//        for(int i = 0; i<moviesList.size() ; i++)
//        {
//            moviesList.get(i).printmovie();
//
//        }
//    }


    public void writefile(String OUTPUT_FILE_NAME,String OUTPUT_RESOURCE)throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        String text;
        for(int i=0;i<moviesList.size();i++)
        {
            text = moviesList.get(i).getTitle() + "," + String.valueOf(moviesList.get(i).getYear_of_release()) + "," + moviesList.get(i).getGenre1() + "," + moviesList.get(i).getGenre2() + "," + moviesList.get(i).getGenre3() + "," + moviesList.get(i).getRunning_time() + "," + moviesList.get(i).getProduction_company() + "," + moviesList.get(i).getBudget() + "," + moviesList.get(i).getRevenue();
            bw.write(text);
            bw.write(System.lineSeparator());
        }

        bw.close();
        BufferedWriter bt = new BufferedWriter(new FileWriter(OUTPUT_RESOURCE));
        text = "";
        for(int i=0;i<moviesList.size();i++)
        {
            text = moviesList.get(i).getTitle() + "," + moviesList.get(i).trailer;
            bt.write(text);
            bt.write(System.lineSeparator());
        }
        bt.close();


    }
}