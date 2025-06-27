package com.example.javatermproject.serverpackage;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class movies implements Serializable
{
    static int i=0;
    private String Title,Genre1,Genre2,Genre3,Production_company;
    private int year_of_release, Running_time;
    private long Budget, Revenue, profit;

    public String poster,trailer,backDrop;

    public movies(String Title, int year_of_release, String Genre1, String Genre2, String Genre3, int Running_time, String Production_company, long Budget, long Revenue, String poster, String trailer)
    {
        this.Title = Title;
        this.year_of_release = year_of_release;
        this.Genre1 = Genre1;
        this.Genre2 = Genre2;
        this.Genre3 = Genre3;
        this.Running_time = Running_time;
        this.Production_company = Production_company;
        this.Budget = Budget;
        this.Revenue = Revenue;
        this.profit = Revenue - Budget;
        this.trailer = trailer;
        this.poster = poster;
    }
    public movies(String Full,String trailercontain)
    {
        String[] all = Full.split("[,]");
        this.Title = all[0];
        this.year_of_release = Integer.parseInt(all[1],10);
        this.Genre1 = all[2];
        this.Genre2 = all[3];
        this.Genre3 = all[4];
        this.Production_company = all[6];
        this.Running_time = Integer.parseInt(all[5],10);
        this.Budget = Integer.parseInt(all[7],10);
        this.Revenue = Integer.parseInt(all[8],10);
        this.profit = Revenue - Budget;
        String[] s = trailercontain.split("[,]");
        trailer = s[1];
        String[] another = this.Title.split("[ ]");
        String p = "";
        poster = "";

        for(int i=0;i< another.length;i++)
        {
            try {
                String[] str = another[i].split("[:][,]['][-][!]");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
//            for(int j=0;j< str.length;j++){
//                poster = poster + str[j];
//            }
            p = p + another[i];

        }
        poster = p.replaceAll("[^\\w\\s\\ ]", "");
        String b = poster;
        poster = "/Assets/Thumbnails/" + poster + "poster.jpg";
        backDrop = "/Assets/Thumbnails/" + b + "backdrop.jpg";
        //System.out.println(Title + " " + i + "  number " + poster);
        i++;

    }

    public String getTitle()
    {
        return Title;
    }

    public String getPoster() {
        return poster;
    }

    public int getYear_of_release()
    {
        return year_of_release;
    }

    public long getBudget() {
        return Budget;
    }

    public long getRevenue() {
        return Revenue;
    }

    public int getRunning_time() {
        return Running_time;
    }

    public String getGenre1() {
        return Genre1;
    }

    public String getGenre2() {
        return Genre2;
    }

    public String getGenre3() {
        return Genre3;
    }

    public String getProduction_company() {
        return Production_company;
    }

    public long getProfit() {
        return profit;
    }

    public void printmovie()
    {
        System.out.println("Title: " + Title);
        System.out.println("Year of Release: " + year_of_release);
        System.out.println("Genre: " + Genre1);
        if(Genre2!="") System.out.println("       " + Genre2);
        if(Genre3!="") System.out.println("       " + Genre3);
        System.out.println("Running Time(in minutes): " + Running_time);
        System.out.println("Production Company: " + Production_company);
        System.out.println("Budget:  " + Budget);
        System.out.println("Revenue: " + Revenue);
        System.out.println("Profit:  " + profit);

        System.out.println("");


    }
    public void setProduction_company(String p) {
        Production_company = p;
       // System.out.println("Setting name new");
    }

}
