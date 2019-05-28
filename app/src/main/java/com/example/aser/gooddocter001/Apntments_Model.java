package com.example.aser.gooddocter001;

public class Apntments_Model {
    String dID;
    String uID;
    String status;
    String eTime;
    String sTime;
    String date;

    public String getdID() {
        return dID;
    }

    public void setdID(String dID) {
        this.dID = dID;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String geteTime() {
        return eTime;
    }

    public void seteTime(String eTime) {
        this.eTime = eTime;
    }

    public String getsTime() {
        return sTime;
    }

    public void setsTime(String sTime) {
        this.sTime = sTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getaID() {
        return aID;
    }

    public void setaID(String aID) {
        this.aID = aID;
    }

    String dName;
    String uName;
    String aID;


    public Apntments_Model(String dID, String uID, String status, String eTime, String sTime, String date,String dName,String uName,String aID) {
        this.dID = dID;
        this.uID = uID;
        this.status = status;
        this.eTime = eTime;
        this.sTime = sTime;
        this.date = date;
        this.dName=dName;
        this.uName=uName;
        this.aID=aID;
    }

    public Apntments_Model() {
    }
}
