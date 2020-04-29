package com.example.myapplication.ui.session;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Session {
    private String  date;
    private String iniTime;
    private String endTime;

    public Session(String date, String iniTime, String endTime)  {

        this.date = date;
        this.iniTime = iniTime;
        this.endTime = endTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIniTime() {
        return iniTime;
    }

    public void setIniTime(String iniTime) {
        this.iniTime = iniTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
