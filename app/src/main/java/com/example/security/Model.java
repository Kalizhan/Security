package com.example.security;

import android.widget.Spinner;

public class Model {
    String fname, day, time, reason, add, temp;

    Model() {

    }

    public Model(String fname, String day, String time, String reason, String add, String temp) {
        this.fname = fname;
        this.day = day;
        this.time = time;
        this.reason = reason;
        this.add = add;
        this.temp = temp;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}


