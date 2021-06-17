package com.example.accout2;

import android.app.Application;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MyData extends Application {
       //程序运行时用于共享的数据
       private ArrayList<Account> dataList= new ArrayList<Account>();

       public ArrayList<Account> getDataList(){
           return this.dataList;
       }

       public void setDataList(ArrayList<Account> dataList){
           this.dataList=dataList;
       }
       //将数据从sharedPreferences中取出
       public void read(){
           SharedPreferences sharedPreferences = getSharedPreferences("ListSave2", MODE_PRIVATE);
           String json = sharedPreferences.getString("listsave2", null);
           if (json != null) {
               Gson gson = new Gson();
               Type type = new TypeToken<ArrayList<Account>>() {
               }.getType();
               ArrayList<Account> list1 = new ArrayList<Account>();
               list1 = gson.fromJson(json, type);
               dataList = list1;
           }
       }
       //将数据存入
       public void save(){
           SharedPreferences.Editor editor = getSharedPreferences("ListSave2", MODE_PRIVATE).edit();
           Gson gson = new Gson();
           String json = gson.toJson(dataList);
           editor.putString("listsave2", json);
           editor.commit();
       }

       @Override
       public void onCreate(){
           super.onCreate();
           read();
       }

}
