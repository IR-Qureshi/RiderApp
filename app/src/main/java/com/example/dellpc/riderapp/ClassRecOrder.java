package com.example.dellpc.riderapp;

/**
 * Created by dell pc on 03-Jan-18.
 */

public class ClassRecOrder {
    String consigneeName;
    String datetime;

    public ClassRecOrder(){

    }
    public ClassRecOrder(String consigneeName, String datetime){
        this.consigneeName = consigneeName;
        this.datetime = datetime;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public String getDatetime() {
        return datetime;
    }
}
