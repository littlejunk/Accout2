package com.example.accout2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class CostActivity extends AppCompatActivity {

    private int costtype =1;

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
        dataList=myData.getDataList();
        setContentView(R.layout.activity_cost);
        TitleLayout titleLayout = (TitleLayout)findViewById(R.id.title_cost);
        titleLayout.getTextView().setText("支出");
        final Button costtypebutton = (Button)findViewById(R.id.costmenubutton);
        costtypebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(CostActivity.this,v);
                getMenuInflater().inflate(R.menu.costmenu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.costmenu1 :
                                costtype = 1;
                                costtypebutton.setText("其他");
                                return true;
                            case R.id.costmenu2 :
                                costtype = 2;
                                costtypebutton.setText("零食饮料");
                                return true;
                            case R.id.costmenu3:
                                costtype = 3;
                                costtypebutton.setText("学校饮食");
                                return true;
                            case R.id.costmenu4:
                                costtype = 4;
                                costtypebutton.setText("外出娱乐");
                                return true;
                            case  R.id.costmenu5:
                                costtype =5;
                                costtypebutton.setText("学习用品");
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });
        Button earnSaveButton = (Button)findViewById(R.id.cost_save);
        final EditText costMoney = (EditText)findViewById(R.id.costmoneyeditview);
        final EditText costRemarks = (EditText)findViewById(R.id.costremarkseditview);
        earnSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(costMoney.getText())) {
                    Toast.makeText(CostActivity.this, "请输入金额", Toast.LENGTH_SHORT).show();
                }else if(!isNumber(costMoney.getText().toString())){
                    Toast.makeText(CostActivity.this, "请输入正确格式，如2或2.52", Toast.LENGTH_SHORT).show();
                }else {
                    if(isInteger(costMoney.getText().toString())) {
                        Account account = new Account(false, (float)Integer.valueOf(costMoney.getText().toString()),
                                costRemarks.getText().toString(), costtype);
                        dataList.add(0, account);
                        CostActivity.this.finish();
                    }else{
                        Account account = new Account(false, Float.parseFloat(costMoney.getText().toString()),
                                costRemarks.getText().toString(), costtype);
                        dataList.add(0, account);
                        CostActivity.this.finish();
                    }
                }

            }
        });
    }
    private static boolean isNumber(String s){
        String reg = "^[0-9]+(.[0-9]+)?$";
        return s.matches(reg);
    }

    private boolean isInteger(String s){
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(s).matches();
    }
}
