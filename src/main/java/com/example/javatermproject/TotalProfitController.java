package com.example.javatermproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TotalProfitController implements Initializable {
    @FXML
    Label productionName,totalProfit;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productionName.setText(Main.proName);
        try {
            totalProfit.setText( "US$ " + Main.totalProfit());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
