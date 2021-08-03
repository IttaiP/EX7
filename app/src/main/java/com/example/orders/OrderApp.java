package com.example.orders;

import android.app.Application;


public class OrderApp extends Application {
    private static OrderApp instance;
    private static DB db;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        // create database communicator
        db = new DB();

    }


    public static OrderApp getInstance(){
        return instance;
    }


    public DB getDB(){
        return db;
    }


}
