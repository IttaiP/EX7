package com.example.orders;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;

public class DB {

    private final SharedPreferences sp;
    private String currentId = "";


    public DB(){
        OrderApp app = OrderApp.getInstance();
        // initialize sp
        this.sp = app.getSharedPreferences("orderID", Context.MODE_PRIVATE);
        if(this.sp.contains("id")){
            this.currentId = this.sp.getString("id", "");
        }


    }

    public void addNewOrder(Order order){}

    public void removeOrder(Order order){}

    public void updateSP(String id){}

    public void removeSP(String id){}

    public String getCurrentId(){
        return this.currentId;
    }
}
