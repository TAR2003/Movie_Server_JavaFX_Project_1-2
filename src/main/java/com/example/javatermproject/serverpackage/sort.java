package com.example.javatermproject.serverpackage;

import java.util.ArrayList;
import java.util.*;

public class sort
{
    ArrayList<movies> moviesList = new ArrayList<movies>();
    public sort( ArrayList<movies> moviesArrayList)
    {
        //moviesList = moviesArrayList;
        for(int i=0;i<moviesArrayList.size();i++)
        {
            moviesList.add(moviesArrayList.get(i));
        }
    }
    public movieDatabase getTopmovies(int num)
    {
        movieDatabase m = new movieDatabase();
        sortBeasedonProfit();
        for(int i=0;i<num;i++)
        {
            m.addmovieDatabase(moviesList.get(i));
        }
        return m;
    }
    public void sortBeasedonProfit()
    {
        Collections.sort(moviesList, new Profitcompare());
    }
    public void sortBasedonYear()
    {
        Collections.sort(moviesList, new YearCompare());
    }
    public void sortBasedonRevenue()
    {
        Collections.sort(moviesList, new RevenueCompare());
    }
    public void sortBasedonCompany()
    {
        Collections.sort(moviesList, new StringCompare());
    }
    public movieDatabase getRecentMovie()
    {
        sortBasedonYear();
        int mostrecentyear = 0;
        if(moviesList.size()>0)
            mostrecentyear = moviesList.get(0).getYear_of_release();
        movieDatabase m = new movieDatabase();
        int i=0;
        for(i=0;i<moviesList.size();i++){

            if(moviesList.get(i).getYear_of_release() == mostrecentyear) {
                m.addmovieDatabase(moviesList.get(i));

            }
            else
                i = moviesList.size();
        }
        return m;
    }

    public movieDatabase getMaxRevenue()
    {
        sortBasedonRevenue();
        movieDatabase m = new movieDatabase();
        long max=0;
        if(moviesList.size()>0)
            max = moviesList.get(0).getRevenue();
        //movieDatabase m1 = new movieDatabase();
        for(int i=0;i<moviesList.size();i++)
        {
            if(moviesList.get(i).getRevenue() < max)
                break;
            else
                m.addmovieDatabase(moviesList.get(i));

        }
        return m;
    }

    public movieDatabase sortedByCompany()
    {
        movieDatabase m = new movieDatabase();
        sortBasedonCompany();
        for(int i=0;i<moviesList.size();i++)
        {
            m.moviesList.add(moviesList.get(i));
        }
        return m;

    }

}

class Profitcompare implements Comparator<movies>
{
    public int compare(movies m1, movies m2)
    {
        long dif = m2.getProfit() - m1.getProfit();
        if(dif>0) return 1;
        else  return -1;
    }
}
class YearCompare implements Comparator<movies>
{
    public int compare(movies m1,movies m2)
    {
        return m2.getYear_of_release() - m1.getYear_of_release();
    }
}

class RevenueCompare implements Comparator<movies>
{
    public int compare(movies m1,movies m2)
    {
        long dif = m2.getRevenue() - m1.getRevenue();
        if(dif>0) return 1;
        else  return -1;
    }
}
class StringCompare implements Comparator<movies>
{
    public int compare(movies m1,movies m2)
    {
        int dif = m1.getProduction_company().compareTo(m2.getProduction_company());
        if(dif>0) return 1;
        else  return -1;
    }
}