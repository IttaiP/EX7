package com.example.orders;

import android.app.Application;

import com.google.firebase.FirebaseApp;


public class OrderApp extends Application {
    private static OrderApp instance;
    private static DB db;


    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        instance = this;
        db = new DB();

    }


    public static OrderApp getInstance(){
        return instance;
    }


    public DB getDB(){
        return db;
    }


}
