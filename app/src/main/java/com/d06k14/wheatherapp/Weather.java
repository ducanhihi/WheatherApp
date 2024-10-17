package com.d06k14.wheatherapp;

public class Weather {
    String temp2;
    String dt;
    String description;


    public Weather(String dt, String description, String temp2) {
        this.dt = dt;
        this.description = description;
        this.temp2 = temp2;
    }


    public String getTemp2() {return temp2;}

    public void setTemp2(String temp2) {this.temp2 = temp2;}
    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
