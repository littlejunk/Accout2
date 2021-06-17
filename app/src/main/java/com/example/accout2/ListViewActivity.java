package com.example.accout2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

//展示全部账单
public class ListViewActivity extends AppCompatActivity {

    private MyData myData;

    private ArrayList<Account> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        ActionBar actionBar =getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }

        myData = (MyData)getApplication();
        dataList = myData.getDataList();

        setContentView(R.layout.activity_list_view);

        TitleLayout titleLayout = (TitleLayout)findViewById(R.id.title_listview);
        titleLayout.getTextView().setText("数据列表");

        inListView();
    }
    //实时更新修改后的数据
    @Override
    public void onStart(){
        super.onStart();
        AccountAdapter accountAdapter = new AccountAdapter(ListViewActivity.this,R.layout.account_item,dataList);
        ListView listView =(ListView)findViewById(R.id.listview2);
        listView.setAdapter(accountAdapter);
    }

    private void inListView(){
        TitleLayout titleLayout = (TitleLayout)findViewById(R.id.title_listview);
        titleLayout.getTextView().setText("数据列表");

        AccountAdapter accountAdapter = new AccountAdapter(ListViewActivity.this,R.layout.account_item,dataList);
        ListView listView =(ListView)findViewById(R.id.listview2);
        listView.setAdapter(accountAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListViewActivity.this,DetailAccount.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
    }
}
