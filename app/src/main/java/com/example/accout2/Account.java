package com.example.accout2;

import java.io.Serializable;
import java.util.Date;

public class Account implements Serializable {
    private boolean flag;

    private float money;

    private String remarks;

    private int monryType;

    private Date date;

    public Account(boolean flag , float money , String remarks, int monryType){
        this.flag = flag;
        this.money = money;
        this.remarks = remarks;
        this.monryType = monryType;
        this.date = new Date(System.currentTimeMillis());
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public float getMoney(){

        return  this.money;
    }

    public boolean getFlag(){

        return this.flag;
    }

    public String getRemarks(){

        return  this.remarks;
    }

    public Date getDate(){

        return  this.date;
    }

    public int getMonryType(){

        return this.monryType;
    }

}
