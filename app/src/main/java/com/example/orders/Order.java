package com.example.orders;


import java.io.Serializable;
import java.util.*;


public class Order implements Serializable {

    public enum Status {
        waiting,
        progress,
        ready,
        done
    }



    String name;
    String id;
    int pickels;
    boolean humus;
    boolean tahini;
    Status status;

    public Order(){
        String name = "";
        String id = UUID.randomUUID().toString();
        int pickels = 0;
        boolean humus = false;
        boolean tahini = false;
        Status status = Status.waiting;
    }

    void setName(String name){
        this.name = name;
    }

    void setId(String id){
        this.id = id;
    }

    void setPickels(int pickelsNum){
        this.pickels = pickelsNum;
        if(pickels<0){
            this.pickels = 0;
        }
        if(pickels>10){
            this.pickels = 10;
        }
    }

    void setHumus(boolean val){
        this.humus = val;
    }

    public void setTahini(boolean tahini) {
        this.tahini = tahini;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
