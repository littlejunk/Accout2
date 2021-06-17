package com.example.accout2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Account> dataList=new ArrayList<Account>();

    private MyData myData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //读取存储的数据
        myData = (MyData)getApplication();
        dataList = myData.getDataList();
        //注册home键与菜单键监听器，用于在用户不用back退出应用时保存数据
        registerReceiver(homeKeyEventReceiver, new IntentFilter(
                Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
        //隐藏标题栏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(R.layout.activity_main);
        //设置标题栏文本
        TitleLayout titleLayout=(TitleLayout)findViewById(R.id.title_1);
        titleLayout.getTextView().setText("记账本");
        //计算总体数据并展示
        inFirstView();
        //初始化底部Listview
        inListView();

    }

    //重写onStart方法，再次加载该界面时更新相关数据
    @Override
    public void onStart() {
        super.onStart();
        inFirstView();
        AccountAdapter accountAdapter = new AccountAdapter(MainActivity.this, R.layout.account_item, dataList) {
            @Override
            public int getCount() {
                return Math.min(5, dataList.size());
            }
        };
        ListView listView = (ListView) findViewById(R.id.main_listview);
        listView.setAdapter(accountAdapter);
    }
    //初始化总体数据
    private void inFirstView(){
        FirstLayout firstLayout = (FirstLayout) findViewById(R.id.first_main);
        firstLayout.getCostText().setText(new DecimalFormat("00.00").format(getCost()));
        firstLayout.getEarnText().setText(new DecimalFormat("00.00").format(getEarn()));
        if (getEarn() < getCost()) {
            firstLayout.getShengyu().setText(new DecimalFormat("00.00").format(1000.00f + getEarn() - getCost()));
        }else {
            firstLayout.getShengyu().setText(new DecimalFormat("00.00").format(1000.00f));
        }
    }
    //初始化底部listview
    private void inListView(){
        AccountAdapter accountAdapter = new AccountAdapter(MainActivity.this, R.layout.account_item,dataList) {
            @Override
            public int getCount() {
                return Math.min(5, dataList.size());
            }
        };
        ListView listView = (ListView) findViewById(R.id.main_listview);
        listView.setAdapter(accountAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailAccount.class);
                //将当前item展示的账单在arraylist的位置存入intent，传递给下一个activity
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }
    //计算本月总收入
    private float getEarn() {
        int num = dataList.size() ;
        float money = 0;
        for (int i = 0; i < num; i++) {
            if (dataList.get(i).getFlag()&&(dataList.get(i).getDate().getYear()==new Date(System.currentTimeMillis()).getYear())&&
                    (dataList.get(i).getDate().getMonth()==new Date(System.currentTimeMillis()).getMonth())) {
                money += dataList.get(i).getMoney();
            }
        }
        return money;
    }
    //计算本月总支出
    private float getCost() {
        int num = dataList.size();
        float money = 0;
        for (int i = 0; i < num; i++) {
            if (!dataList.get(i).getFlag()&&(dataList.get(i).getDate().getYear()==new Date(System.currentTimeMillis()).getYear())&&
                    (dataList.get(i).getDate().getMonth()==new Date(System.currentTimeMillis()).getMonth())) {
                money += dataList.get(i).getMoney();
            }
        }
        return money;
    }
    //重写Destory方法，活动销毁时保存数据
    @Override
    protected void onDestroy() {
        myData.save();
        unregisterReceiver(homeKeyEventReceiver);
        super.onDestroy();
    }
    // 监听home键和菜单键
    private BroadcastReceiver homeKeyEventReceiver;
    {
        homeKeyEventReceiver = new BroadcastReceiver() {
            String REASON = "reason";
            String HOMEKEY = "homekey";
            String RECENTAPPS = "recentapps";

            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                    String reason = intent.getStringExtra(REASON);
                    if (TextUtils.equals(reason, HOMEKEY)) {
                        // 点击 Home键
                       myData.save();
                    } else if (TextUtils.equals(reason, RECENTAPPS)) {
                        // 点击 菜单键
                        myData.save();
                    }
                }
            }
        };
    }


}