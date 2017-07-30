package com.jsobiecki.tand;

import java.io.Serializable;

/**
 * Created by Jacek on 28.04.2017.
 */
public class Date implements Serializable{
    private int year;
    private int month;
    private int day;
    private double distance;
    private int time;

    public Date(int year, int month, int day, double distance, int time) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.distance = distance;
        this.time = time;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public double getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }

    public void printDate(){
        System.out.println(year+" "+month+" "+day+" "+distance+" "+time);
    }
    public void printTime(){
        System.out.println(distance);
    }
}
