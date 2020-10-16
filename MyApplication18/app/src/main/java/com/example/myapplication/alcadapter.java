package com.example.myapplication;

public class alcadapter {
    String userid,date;
    int perday,cost,year,startday,stopday;
    public  alcadapter(){}

    public int getStartday() {
        return startday;
    }

    public void setStartday(int startday) {
        this.startday = startday;
    }

    public int getStopday() {
        return stopday;
    }

    public void setStopday(int stopday) {
        this.stopday = stopday;
    }

    public alcadapter(String userid, String date, int perday, int cost, int year, int startday, int stopday) {
        this.userid = userid;
        this.date = date;
        this.perday = perday;
        this.cost = cost;
        this.year = year;
        this.startday=startday;
        this.stopday=stopday;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPerday() {
        return perday;
    }

    public void setPerday(int perday) {
        this.perday = perday;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


}
