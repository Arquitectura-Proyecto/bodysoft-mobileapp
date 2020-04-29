package com.example.myapplication.Model.Entities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SessionEntity {
    private int idCoach;
    private String daySession;
    private String iniTime;
    private String endTime;
    private int id_status;
    private int idUser;

    public SessionEntity(String date, String iniTime, String endTime)  {

        this.daySession = date;
        this.iniTime = iniTime;
        this.endTime = endTime;
    }

    public SessionEntity(int idCoach, String daySession, String iniTime, String endTime, int id_status, int idUser) {
        this.idCoach = idCoach;
        this.daySession = daySession;
        this.iniTime = iniTime;
        this.endTime = endTime;
        this.id_status = id_status;
        this.idUser = idUser;
    }

    public int getIdCoach() {
        return idCoach;
    }

    public void setIdCoach(int idCoach) {
        this.idCoach = idCoach;
    }

    public String getDaySession() {
        return daySession;
    }

    public void setDaySession(String daySession) {
        this.daySession = daySession;
    }

    public int getId_status() {
        return id_status;
    }

    public void setId_status(int id_status) {
        this.id_status = id_status;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getDate() {
        return daySession;
    }

    public void setDate(String date) {
        this.daySession = date;
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
