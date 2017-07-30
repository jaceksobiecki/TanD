package com.jsobiecki.tand;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    int flag=0;
    static boolean opened=false;
    //for dates
    private static ArrayList<Date> dateArray = new ArrayList<>();
    final ToggleGroup group = new ToggleGroup();
    @FXML
    GridPane gridPane;
    LocalDate localDate = LocalDate.now();
    Button[] btnTab = new Button[31];

    private double distance = 0;
    private int time = 0;
    private static int currentMonth;
    private static int currentYear;
    private static int day;
    @FXML
    private AnchorPane pane;
    @FXML
    private TextField timeField;
    @FXML
    private TextField distanceField;
    @FXML
    private ToggleButton pickDateBtn;
    @FXML
    private Label monthDist;
    @FXML
    private Label week1;
    @FXML
    private Label week2;
    @FXML
    private Label week3;
    @FXML
    private Label week4;
    @FXML
    private Label week5;
    @FXML
    private Label week6;
    @FXML
    private Label dateLbl;
    @FXML
    private Label lblMonth;
    @FXML
    private Label calYear;
    @FXML
    private AreaChart<?, ?> chart;
    private XYChart.Series series;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    private int dayOfWeekColumn;


    public static int getCurrentYear() {
        return currentYear;
    }


    public static int getCurrentMonth() {
        return currentMonth;
    }

    public static int getDay() {
        return day;
    }

    public static ArrayList<Date> getDateArray() {
        return dateArray;
    }

    public void left(){
        if(currentMonth>1)
            currentMonth--;
        else {
            currentMonth=12;
            currentYear--;
            calYear.setText(String.valueOf(currentYear));
        }
        switch(currentMonth){
            case 1:
                lblMonth.setText("January");
                break;
            case 2:
                lblMonth.setText("February");
                break;
            case 3:
                lblMonth.setText("March");
                break;
            case 4:
                lblMonth.setText("April");
                break;
            case 5:
                lblMonth.setText("May");
                break;
            case 6:
                lblMonth.setText("June");
                break;
            case 7:
                lblMonth.setText("July");
                break;
            case 8:
                lblMonth.setText("August");
                break;
            case 9:
                lblMonth.setText("September");
                break;
            case 10:
                lblMonth.setText("October");
                break;
            case 11:
                lblMonth.setText("November");
                break;
            case 12:
                lblMonth.setText("December");
                break;
        }
        gridPane.getChildren().clear();
        setCalendar();
        setResults();
        lblMonth.setAlignment(Pos.CENTER);
    }
    public void right(){
        if(currentMonth<12)
            currentMonth++;
        else {
            currentMonth=1;
            currentYear++;
            calYear.setText(String.valueOf(currentYear));
        }
        switch(currentMonth){
            case 1:
                lblMonth.setText("January");
                break;
            case 2:
                lblMonth.setText("February");
                break;
            case 3:
                lblMonth.setText("March");
                break;
            case 4:
                lblMonth.setText("April");
                break;
            case 5:
                lblMonth.setText("May");
                break;
            case 6:
                lblMonth.setText("June");
                break;
            case 7:
                lblMonth.setText("July");
                break;
            case 8:
                lblMonth.setText("August");
                break;
            case 9:
                lblMonth.setText("September");
                break;
            case 10:
                lblMonth.setText("October");
                break;
            case 11:
                lblMonth.setText("November");
                break;
            case 12:
                lblMonth.setText("December");
                break;
        }
        gridPane.getChildren().clear();
        setCalendar();
        setResults();
        lblMonth.setAlignment(Pos.CENTER);
    }

    private void getDate() {
        currentMonth = localDate.getMonthValue();
        currentYear = localDate.getYear();
        day = localDate.getDayOfMonth();
    }

    private void setCalendar() {
        for (int i = 0; i < 31; i++) {
            btnTab[i] = new Button(i + 1 + "");
            btnTab[i].setAlignment(Pos.CENTER);
            btnTab[i].getStyleClass().add("button-calendar");
        }

        LocalDate firstDayofMonth = LocalDate.of(currentYear, currentMonth, 1);
        switch (firstDayofMonth.getDayOfWeek()) {
            case MONDAY:
                dayOfWeekColumn = 0;
                break;
            case TUESDAY:
                dayOfWeekColumn = 1;
                break;
            case WEDNESDAY:
                dayOfWeekColumn = 2;
                break;
            case THURSDAY:
                dayOfWeekColumn = 3;
                break;
            case FRIDAY:
                dayOfWeekColumn = 4;
                break;
            case SATURDAY:
                dayOfWeekColumn = 5;
                break;
            case SUNDAY:
                dayOfWeekColumn = 6;
                break;
        }
        int x = 0;
        //sets up days in month
        int y;
        if (currentMonth == 2) {
            if (currentYear % 4 == 0 && currentYear % 100 != 0 || currentYear % 400 == 0)
                y = 29;
            else
                y = 28;
        } else if (currentMonth == 4 || currentMonth == 6 || currentMonth == 9 || currentMonth == 11)
            y = 30;
        else
            y = 31;
        //adds buttons
        for (int i = dayOfWeekColumn; i < 7; i++) {
            gridPane.add(btnTab[x], i, 0);
            x++;
        }
        for (int i = 1; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                gridPane.add(btnTab[x], j, i);
                x++;
                if (x == y)
                    break;
            }
            if (x == y)
                break;
        }
        for (int i = 0; i < 31; i++) {
            gridPane.setHalignment(btnTab[i], HPos.CENTER);
        }


    }


    @FXML
    private void addBtn() {
        String t = timeField.getText();
        String dist = distanceField.getText();
        String y = String.valueOf(currentYear);
        String m = String.valueOf(currentMonth);
        String d = String.valueOf(day);
        dateArray.add(new Date(Integer.parseInt(y), Integer.parseInt(m), Integer.parseInt(d), Double.parseDouble(dist), Integer.parseInt(t)));
        setResults();
    }

    private void setResults() {
        //ustawienie dystansu w miesiącu
        distance = 0;
        time = 0;
        double distanceWeek1 = 0;
        int timeWeek1 = 0;
        double distanceWeek2 = 0;
        int timeWeek2 = 0;
        double distanceWeek3 = 0;
        int timeWeek3 = 0;
        double distanceWeek4 = 0;
        int timeWeek4 = 0;
        double distanceWeek5 = 0;
        int timeWeek5 = 0;
        double distanceWeek6 = 0;
        int timeWeek6 = 0;
        for (int i = 0; i < dateArray.size(); i++) {
            if (dateArray.get(i).getMonth() == currentMonth && dateArray.get(i).getYear() == currentYear) {
                distance += dateArray.get(i).getDistance();
                time += dateArray.get(i).getTime();
                btnTab[dateArray.get(i).getDay() - 1].setStyle("-fx-background-color: aquamarine");
                if (gridPane.getRowIndex(btnTab[dateArray.get(i).getDay() - 1]) == 0) {
                    distanceWeek1 += dateArray.get(i).getDistance();
                    timeWeek1 += dateArray.get(i).getTime();
                } else if (gridPane.getRowIndex(btnTab[dateArray.get(i).getDay() - 1]) == 1) {
                    distanceWeek2 += dateArray.get(i).getDistance();
                    timeWeek2 += dateArray.get(i).getTime();
                } else if (gridPane.getRowIndex(btnTab[dateArray.get(i).getDay() - 1]) == 2) {
                    distanceWeek3 += dateArray.get(i).getDistance();
                    timeWeek3 += dateArray.get(i).getTime();
                } else if (gridPane.getRowIndex(btnTab[dateArray.get(i).getDay() - 1]) == 3) {
                    distanceWeek4 += dateArray.get(i).getDistance();
                    timeWeek4 += dateArray.get(i).getTime();
                } else if (gridPane.getRowIndex(btnTab[dateArray.get(i).getDay() - 1]) == 4) {
                    distanceWeek5 += dateArray.get(i).getDistance();
                    timeWeek5 += dateArray.get(i).getTime();
                } else if (gridPane.getRowIndex(btnTab[dateArray.get(i).getDay() - 1]) == 5) {
                    distanceWeek6 += dateArray.get(i).getDistance();
                    timeWeek6 += dateArray.get(i).getTime();
                }
            }
        }
        int hours = time/60;
        int min = time-((time/60)*60);
        int h1 = timeWeek1/60;
        int min1 = timeWeek1-((timeWeek1/60)*60);
        int h2 = timeWeek2/60;
        int min2 = timeWeek2-((timeWeek2/60)*60);
        int h3 = timeWeek3/60;
        int min3 = timeWeek3-((timeWeek3/60)*60);
        int h4 = timeWeek4/60;
        int min4 = timeWeek4-((timeWeek4/60)*60);
        int h5 = timeWeek5/60;
        int min5 = timeWeek5-((timeWeek5/60)*60);
        int h6 = timeWeek6/60;
        int min6 = timeWeek6-((timeWeek6/60)*60);

        if(min<10)
            monthDist.setText(distance + " km" + " | " + hours+":0" + min);
        else
            monthDist.setText(distance + " km" + " | " + hours+":" + min);
        if (min1 < 10)
            week1.setText(distanceWeek1 + " km" + " | " + h1 + ":0" +min1);
        else
            week1.setText(distanceWeek1 + " km" + " | " + h1 + ":" +min1);
        if(min2<10)
            week2.setText(distanceWeek2 + " km" + " | " + h2 + ":0" +min2);
        else
            week2.setText(distanceWeek2 + " km" + " | " + h2 + ":" +min2);
        if(min3<10)
            week3.setText(distanceWeek3 + " km" + " | " + h3 + ":0" +min3);
        else
            week3.setText(distanceWeek3 + " km" + " | " + h3 + ":" +min3);
        if(min4<10)
            week4.setText(distanceWeek4 + " km" + " | " + h4 + ":0" +min4);
        else
            week4.setText(distanceWeek4 + " km" + " | " + h4 + ":" +min4);
        if(min5<10)
            week5.setText(distanceWeek5 + " km" + " | " + h5 + ":0" +min5);
        else
            week5.setText(distanceWeek5 + " km" + " | " + h5 + ":" +min5);
        if(min6<10)
            week6.setText(distanceWeek6 + " km" + " | " + h6 + ":0" +min6);
        else
            week6.setText(distanceWeek6 + " km" + " | " + h6 + ":" +min6);

        if (distanceWeek1 == 0)
            week1.setVisible(false);
        else
            week1.setVisible(true);
        if (distanceWeek2 == 0)
            week2.setVisible(false);
        else
            week2.setVisible(true);
        if (distanceWeek3 == 0)
            week3.setVisible(false);
        else
            week3.setVisible(true);
        if (distanceWeek4 == 0)
            week4.setVisible(false);
        else
            week4.setVisible(true);
        if (distanceWeek5 == 0)
            week5.setVisible(false);
        else
            week5.setVisible(true);
        if (distanceWeek6 == 0)
            week6.setVisible(false);
        else
            week6.setVisible(true);
        setChart();
    }

    private void chooseDate() {
        if (String.valueOf(currentMonth).length() == 1)
            dateLbl.setText(String.valueOf(day) + " 0" + String.valueOf(currentMonth) + " " + String.valueOf(currentYear));
        else
            dateLbl.setText(String.valueOf(day) + " " + String.valueOf(currentMonth) + " " + String.valueOf(currentYear));
    }

    private void setChart() {
        try {
            series.getData().clear();
        } catch (Exception e) {
        }
        if(flag==0) {
            series = new XYChart.Series();
            chart.getData().addAll(series);
        }
        flag++;
        int distTab[] = new int[12];
        for(int dist : distTab){
            dist=0;
        }
        for (int i = 0; i < dateArray.size(); i++) {
            if (dateArray.get(i).getYear() == currentYear) {
                switch (dateArray.get(i).getMonth()) {
                    case 1:
                        distTab[0] += dateArray.get(i).getDistance();
                        break;
                    case 2:
                        distTab[1] += dateArray.get(i).getDistance();
                        break;
                    case 3:
                        distTab[2] += dateArray.get(i).getDistance();
                        break;
                    case 4:
                        distTab[3] += dateArray.get(i).getDistance();
                        break;
                    case 5:
                        distTab[4] += dateArray.get(i).getDistance();
                        break;
                    case 6:
                        distTab[5] += dateArray.get(i).getDistance();
                        break;
                    case 7:
                        distTab[6] += dateArray.get(i).getDistance();
                        break;
                    case 8:
                        distTab[7] += dateArray.get(i).getDistance();
                        break;
                    case 9:
                        distTab[8] += dateArray.get(i).getDistance();
                        break;
                    case 10:
                        distTab[9] += dateArray.get(i).getDistance();
                        break;
                    case 11:
                        distTab[10] += dateArray.get(i).getDistance();
                        break;
                    case 12:
                        distTab[11] += dateArray.get(i).getDistance();
                        break;
                }
            }
        }
        int i=1;
        for(int dist: distTab){
            series.getData().add(new XYChart.Data(String.valueOf(i), dist));
            i++;
        }
    }
    private void openCalWindow(){
        //okno zmian
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("CalWindow.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setAlwaysOnTop(true);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.show();
        pane.setDisable(true);
        opened=true;
        Thread thread = new Thread(new Wait());
        thread.start();

    }
    public class Wait implements Runnable{

        @Override
        public void run() {
            while(opened){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            pane.setDisable(false);
            Platform.runLater(() -> {
            setResults();
            });

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getDate();
        chooseDate();

        //Wczytanie ArrayList
        try {
            FileInputStream fis = new FileInputStream("dateArray");
            ObjectInputStream ois = new ObjectInputStream(fis);
            dateArray = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            System.out.println("No previous data");
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }
        setCalendar();
        //ustawienie dystansu
        setResults();
        switch(currentMonth){
            case 1:
                lblMonth.setText("January");
                break;
            case 2:
                lblMonth.setText("February");
                break;
            case 3:
                lblMonth.setText("March");
                break;
            case 4:
                lblMonth.setText("April");
                break;
            case 5:
                lblMonth.setText("May");
                break;
            case 6:
                lblMonth.setText("June");
                break;
            case 7:
                lblMonth.setText("July");
                break;
            case 8:
                lblMonth.setText("August");
                break;
            case 9:
                lblMonth.setText("September");
                break;
            case 10:
                lblMonth.setText("October");
                break;
            case 11:
                lblMonth.setText("November");
                break;
            case 12:
                lblMonth.setText("December");
                break;
        }
        lblMonth.setAlignment(Pos.CENTER);

        for (int i = 0; i < 31; i++) {
            int a=i;
            btnTab[i].setOnAction((event) -> {
                day = a+1;
                openCalWindow();
            });
        }
        pickDateBtn.setToggleGroup(group);
        pickDateBtn.setSelected(false);

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle toggle, Toggle new_toggle) {
                if (new_toggle != null) {

                    for (int i = 0; i < 31; i++) {
                        int a = i;
                        btnTab[i].setOnAction((event) -> {
                            day = a + 1;
                            chooseDate();
                        });
                    }
                } else {
                    for (int i = 0; i < 31; i++) {
                        int a=i;
                        btnTab[i].setOnAction((event) -> {
                            day = a + 1;
                           openCalWindow();
                        });
                    }
                }
            }
        });
    }
}
