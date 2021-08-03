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



    public String name;
    public String comments;
    public String id;
    public String pickels;
    public boolean humus;
    public boolean tahini;
    public Status status;

    public Order(){
        String name = "";
        String comments = "";
        String id = UUID.randomUUID().toString();
        String pickels = "0";
        boolean humus = false;
        boolean tahini = false;
        Status status = Status.waiting;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setComments(String comment){
        this.comments = comment;
    }

    public String getComments(){
        return comments;
    }


    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void setPickels(String pickelsNum){
        if (pickelsNum.equals("")) {
            this.pickels = "0";
        }
        else {this.pickels = pickelsNum;}
        ;
        if(Integer.parseInt(pickels)<0){
            this.pickels = "0";
        }
        if(Integer.parseInt(pickels)>10){
            this.pickels = "10";
        }
    }

    public String getPickels(){
        return pickels;
    }

    public void setHumus(boolean val){
        this.humus = val;
    }

    public boolean getHumus(){
        return humus;
    }

    public void setTahini(boolean tahini) {
        this.tahini = tahini;
    }

    public boolean getTahini(){
        return tahini;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus(){
        return status;
    }

}
