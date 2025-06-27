package com.example.javatermproject.serverpackage;

import java.io.Serializable;

public class productions implements Serializable {
    public String  name;
    public String password;
    public productions (String n,String p) {
        name = n;
        password = p;
    }

    public void addName(String n, String p) {
        name = n;
        password = p;
    }

}