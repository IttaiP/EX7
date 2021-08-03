package com.example.orders;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Making extends AppCompatActivity {

    DB db;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.making);


        db = OrderApp.getInstance().getDB();

        // add status change listener
        db.listenForStatusChange(this);

    }

    @Override
    protected void onDestroy() {
        db.destroyListener();
        super.onDestroy();
    }
}
