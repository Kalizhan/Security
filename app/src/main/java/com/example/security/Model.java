package com.example.security;

import android.widget.Spinner;

public class Model {
    String fname, date, reason, add;

    Model() {

    }

    public Model(String fname, String date, String reason, String add) {
        this.fname = fname;
        this.date = date;
        this.reason = reason;
        this.add = add;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}


