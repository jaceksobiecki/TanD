package com.jsobiecki.tand;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

import static com.jsobiecki.tand.Controller.*;

/**
 * Created by Jacek on 06.07.2017.
 */
public class CalWindow implements Initializable{
    private double distance = 0;
    private int time = 0;
    private int index=-1;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField distText;
    @FXML
    private TextField timeText;
    @FXML
    public void save(){
        anchorPane.getScene().getWindow().hide();
        if(index!=-1) {
            getDateArray().get(index).setDistance(Double.parseDouble(distText.getText()));
            getDateArray().get(index).setTime(Integer.parseInt(timeText.getText()));
        }
        else if(Double.parseDouble(distText.getText())!=0 || Integer.parseInt(timeText.getText())!=0){
                getDateArray().add(new Date(getCurrentYear(), getCurrentMonth(), getDay(), Double.parseDouble(distText.getText()), Integer.parseInt(timeText.getText())));
        }
        Controller.opened=false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(int i = 0; i< getDateArray().size(); i++){
            if(getDateArray().get(i).getDay()== getDay() && getDateArray().get(i).getMonth()== getCurrentMonth() && getDateArray().get(i).getYear()== getCurrentYear()){
                distance+= getDateArray().get(i).getDistance();
                time+= getDateArray().get(i).getTime();
                index=i;
            }
        }

        distText.setText(String.valueOf(distance));
        timeText.setText((String.valueOf(time)));
    }
}
