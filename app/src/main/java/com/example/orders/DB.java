package com.example.orders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

public class DB {

    private final SharedPreferences sp;
    private String currentId = "";
    private FirebaseFirestore firestore;
    OrderApp app;


    public DB(){
        app = OrderApp.getInstance();
        // initialize sp
        this.sp = app.getSharedPreferences("orderID", Context.MODE_PRIVATE);
        if(this.sp.contains("id")){
            this.currentId = this.sp.getString("id", "");
        }
        //------ run line to reset shared prefferences -----
        this.sp.edit().clear().commit();
        this.firestore = FirebaseFirestore.getInstance();

        // -------- create listeners---------

    }

    public void addNewOrder(Order order){
        this.firestore.collection("Orders").document(order.id)
                .set(order).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                updateSP(order.id);
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("FAIL!!!","set failed", e);
            }
        });
    }

    public void removeOrder(Order order){
        this.firestore.collection("Orders").document(order.id)
                .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                removeSP(order.id);
                Intent newOrderIntent = new Intent(app, NewOrder.class);
            }
        });
    }

    public void updateSP(String id){
        SharedPreferences.Editor editor = this.sp.edit();
        editor.putString("id", id);
        editor.apply();
    }

    public void removeSP(String id){
        SharedPreferences.Editor editor = this.sp.edit();
        editor.remove(id);
        editor.apply();
    }

    public String getCurrentId(){
        return this.currentId;
    }
}
