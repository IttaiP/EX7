package com.example.orders;

import android.app.Application;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

public class OrderApp extends Application {
    private static OrderApp instance;
    private static DB db;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        final FirebaseFirestore firestore = FirebaseFirestore.getInstance();
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
