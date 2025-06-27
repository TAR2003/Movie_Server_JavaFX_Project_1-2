package com.example.javatermproject.serverpackage;

import java.io.Serializable;

public class moviechange implements Serializable {
    public String movieName;
    public String production;
    public moviechange(String movieName, String production) {
        this.production = production;
        this.movieName = movieName;
    }

}
