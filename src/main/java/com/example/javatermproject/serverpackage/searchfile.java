package com.example.javatermproject.serverpackage;

import java.util.ArrayList;


public class searchfile
{
    public ArrayList<movies> moviesList = new ArrayList<>();


    public searchfile(movieDatabase moviesList)
    {
        this.moviesList = moviesList.moviesList;
    }


    public movieDatabase searchMovieByTitle(String Title)
    {
        movieDatabase shortmoviesList = new movieDatabase();
        int pos = -1;
        for(int i=0;i<moviesList.size();i++)
        {
            movies m = moviesList.get(i);
            if(Title.equalsIgnoreCase(m.getTitle()))
            {
                shortmoviesList.addmovieDatabase(m);
                pos = i;
                break;
            }
        }
        if (pos == -1)
            {System.out.println("No such movie with this name");}
        return shortmoviesList;

    }
    public movieDatabase searchProductionCompany(String productco)
    {
        movieDatabase shortmoviesList = new movieDatabase();
        int pos = -1;
        for(int i=0;i<moviesList.size();i++)
        {
            movies m = moviesList.get(i);
            if(productco.equalsIgnoreCase(m.getProduction_company()))
            {
                shortmoviesList.addmovieDatabase(m);
                pos = i;

            }
        }
        return shortmoviesList;
    }


    public movieDatabase searchByReleaseYear(int year)
    {
        movieDatabase shortmoviesList = new movieDatabase();
        int pos = -1;
        for(int i=0;i<moviesList.size();i++)
        {
            movies m = moviesList.get(i);
            if(year==m.getYear_of_release())
            {
                pos = i;
                shortmoviesList.addmovieDatabase(m);
            }
        }
        if(pos==-1)
            System.out.println("No such movie with this release year");
        return shortmoviesList;

    }

    public movieDatabase searchByGenre(String Genre)
    {
        movieDatabase shortmoviesList = new movieDatabase();
        int pos = -1;
        for(int i=0;i<moviesList.size();i++)
        {
            movies m = moviesList.get(i);
            if(Genre.equalsIgnoreCase(m.getGenre1()) || Genre.equalsIgnoreCase(m.getGenre2()) || Genre.equalsIgnoreCase(m.getGenre3()))
            {
                pos = i;
                shortmoviesList.addmovieDatabase(m);
                m.printmovie();
            }
        }
        if(pos==-1)
            System.out.println("No such movie with this genre");
        return shortmoviesList;

    }
    public movieDatabase searchByRunningTime(int lowest_time, int highest_time)
    {
        movieDatabase shortmoviesList = new movieDatabase();
        int pos = -1;
        for(int i=0;i<moviesList.size();i++)
        {
            movies m = moviesList.get(i);
            if(lowest_time <= m.getRunning_time() && highest_time >= m.getRunning_time())
            {
                pos = i;
                shortmoviesList.addmovieDatabase(m);
            }
        }
        if(pos==-1)
            System.out.println("No such movie with this running time range" );
        return shortmoviesList;
    }
    public movieDatabase searchTop10movies()
    {
        sort s = new sort(moviesList);

        return s.getTopmovies(10);
    }

    public movieDatabase getMostRecentMovie(String searchname)
    {
        movieDatabase ByProductionCompany = new movieDatabase();
        ByProductionCompany = searchProductionCompany(searchname);
        sort s = new sort(ByProductionCompany.moviesList);
        return s.getRecentMovie();

    }

    public movieDatabase getMaximumRevenue(String searchname)
    {
        movieDatabase ByProductionCompany = new movieDatabase();
        ByProductionCompany = searchProductionCompany(searchname);
        sort s = new sort(ByProductionCompany.moviesList);
        return s.getMaxRevenue();
    }

    public long getTotalProfit(String searchname)
    {
        movieDatabase m = searchProductionCompany(searchname);
        long totalprofit = 0;
        for(int i=0;i<m.moviesList.size();i++)
        {
            totalprofit = totalprofit + m.moviesList.get(i).getProfit();
        }
        return totalprofit;

    }
    public void DisplayAllProductionCompanyAndMovieNumber()
    {
        sort s = new sort(moviesList);
        movieDatabase m = new movieDatabase();
        m = s.sortedByCompany();
        String name = m.moviesList.get(0).getProduction_company();
        int count = 0;
        for(int i=0;i<m.moviesList.size();i++)
        {
            if(!(m.moviesList.get(i).getProduction_company().equalsIgnoreCase(name))) {
                System.out.println("Production Company: " + name + " ,  " + "Total Movies: " + count);
                count = 1;
                name = m.moviesList.get(i).getProduction_company();
            }
            else
            {
                count++;
            }
        }
        System.out.println("Production Company: " + name + " ,  " + "Total Movies: " + count);

    }


}