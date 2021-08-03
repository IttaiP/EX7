package com.example.orders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private DB db = null;
    private String id = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // need to get order ID
        db = OrderApp.getInstance().getDB();
        id = db.getCurrentId();


        if(id.equals("")){
            Intent newOrderIntent = new Intent(this, NewOrder.class);
            this.startActivity(newOrderIntent);
        } else{
            // get status and open page accordingly
        }
    }
}
