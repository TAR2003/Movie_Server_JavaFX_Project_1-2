package com.example.javatermproject.serverpackage;

import java.io.Serializable;

public class DataWrapper implements Serializable {
    public String command;
    public Object data;


    public DataWrapper(String command,Object data) {
        this.command = command;
        this.data = data;
    }

}
