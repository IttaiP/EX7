package com.example.orders;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.ktx.Firebase;

public class DB {

    private final SharedPreferences sp;
    private String currentId = "";
    private FirebaseFirestore firestore;
    private ListenerRegistration listener;
    OrderApp app;


    public DB(){
        app = OrderApp.getInstance();
        // initialize sp
        this.sp = app.getSharedPreferences("orderID", Context.MODE_PRIVATE);
        if(this.sp.contains("id")){
            this.currentId = this.sp.getString("id", "");
        }
        //------ run line to reset shared prefferences -----
//        this.sp.edit().clear().commit();
        this.firestore = FirebaseFirestore.getInstance();

        // -------- create listeners---------

    }

    public void addNewOrder(Order order){
        this.firestore.collection("orders").document(order.id)
                .set(order).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
//                updateSP(order.id);
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("FAIL!!!","set failed", e);
            }
        });
    }

    public void removeOrder(Order order){
        this.firestore.collection("orders").document(order.id)
                .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                removeSP(order.id);
                Intent newOrderIntent = new Intent(app, NewOrder.class);
                newOrderIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                app.startActivity(newOrderIntent);
            }
        });
    }

    public void OpenIntentByStatus(String id, Context context){
        this.firestore.collection("orders").document(id).get().
                addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot == null || documentSnapshot.toObject(Order.class) == null) {
                        Intent openNewOrderActivity = new Intent(context, NewOrder.class);
//                        openNewOrderActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(openNewOrderActivity);
                        return;
                    }
                    Order order = documentSnapshot.toObject(Order.class);
                    switch (order.getStatus()) {
                        case waiting:
                            Intent openEditOrderActivity = new Intent(context, EditActivity.class);
                            openEditOrderActivity.putExtra("order", order);
//                            openEditOrderActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(openEditOrderActivity);
                            break;
                        case in_progress:
                            Intent openOrderInProgActivity = new Intent(context, Making.class);
                            openOrderInProgActivity.putExtra("order", order);
//                            openOrderInProgActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(openOrderInProgActivity);
                            break;
                        case ready:
                            Intent openOrderReadyActivity = new Intent(context, Ready.class);
                            openOrderReadyActivity.putExtra("order", order);
//                            openOrderReadyActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(openOrderReadyActivity);
                            break;
                        default:
                            Log.d("ERROR", "STATUS is:" + order.getStatus());
                    }
                });
    }

    public void listenForStatusChange(Context context){
        String TAG = "STATUS CHANGE";
        listener = this.firestore.collection("orders")
                .document(currentId)
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }
                Order order = snapshot.toObject(Order.class);
                if (snapshot != null && snapshot.exists()) {
                    Log.d(TAG, "Current data: " + snapshot.getData());
                    Order.Status status = snapshot.get("status", Order.Status.class);
                    if (status != null) {
                        switch (status) {
                            case in_progress:
                                if(context.getClass() == Making.class){
                                    return;
                                }
                                Intent progressIntent = new Intent(context, Making.class);
                                progressIntent.putExtra("order", order);
//                                progressIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(progressIntent);
                                ((Activity) context).finish();
                                return;
                            case ready:
                                if(context.getClass() == Ready.class){
                                    return;
                                }
                                Intent readyIntent = new Intent(context, Ready.class);
                                readyIntent.putExtra("order", order);
//                                readyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(readyIntent);
                                ((Activity) context).finish();
                                return;
                            case done:
                                Intent doneIntent = new Intent(context, NewOrder.class);
//                                doneIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(doneIntent);
                                ((Activity) context).finish();
                                return;
                            case waiting:
                                return;
                        }
                    } else {
                        Log.d(TAG, "Current data: null");
                        Intent doneIntent = new Intent(context, NewOrder.class);
                        context.startActivity(doneIntent);
                        ((Activity) context).finish();
                        return;

                    }
                }
            }
        });
    }

    public void destroyListener(){
        listener.remove();
    }

    public void updateSP(String id){
        SharedPreferences.Editor editor = this.sp.edit();
        editor.putString("id", id);
        editor.apply();
    }

    public void removeSP(String id){
        SharedPreferences.Editor editor = this.sp.edit();
        editor.remove("id");
        editor.apply();
    }

    public String getCurrentId(){
        return this.currentId;
    }

    public void setCurrentId(String id){
        this.currentId = id;
    }
}
