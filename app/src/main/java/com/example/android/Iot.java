package com.example.android;

public class Iot {
    public String id;
    public String hygro;
    public String temp;
    public String date;

    public Iot() {

    }

    public Iot(String id,String hygro,  String temp, String date) {
        this.id = id;
        this.hygro = hygro;
        this.temp = temp;
        this.date=date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHygro() {
        return hygro;
    }

    public void setHygro(String hygro) {
        this.hygro = hygro;
    }



    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}