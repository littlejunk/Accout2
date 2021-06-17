package com.example.accout2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DetailAccount extends AppCompatActivity {

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
        dataList =myData.getDataList();

        setContentView(R.layout.activity_detail_account);

        TitleLayout titleLayout =(TitleLayout)findViewById(R.id.title_detailaccount);
        titleLayout.getTextView().setText("账单");
        //得到此详细账单的位置
        Intent intent =getIntent();
        final int position = intent.getIntExtra("position",0);
        //展示对应账单详细信息
        final Account account = dataList.get(position);
        TextView textView1 = (TextView)findViewById(R.id.account6);
        TextView textView2 = (TextView)findViewById(R.id.account7);
        TextView textView3 = (TextView)findViewById(R.id.account8);
        TextView textView4 = (TextView)findViewById(R.id.account10);
        final EditText editText = (EditText)findViewById(R.id.account5);
        if(account.getFlag()){
            textView1.setText("收入");
        }else{
            textView1.setText("支出");
        }
        textView2.setText(new DecimalFormat("00.00").format(account.getMoney()));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        textView3.setText(simpleDateFormat.format(account.getDate()));
        editText.setHint(account.getRemarks());
        if(account.getFlag()){
            switch (account.getMonryType()){
                case 1 :
                    textView4.setText("其他");
                    break;
                case 2 :
                    textView4.setText("工资薪水");
                    break;
                case 3 :
                    textView4.setText("基金股票");
                    break;
                case 4 :
                    textView4.setText("被动收入");
                    break;
                case 5 :
                    textView4.setText("兼职打工");
                    break;
                default:
                    break;
            }
        }else {
            switch (account.getMonryType()) {
                case 1:
                    textView4.setText("其他");
                    break;
                case 2:
                    textView4.setText("零食饮料");
                    break;
                case 3:
                    textView4.setText("学校饮食");
                    break;
                case 4:
                    textView4.setText("外出娱乐");
                    break;
                case 5:
                    textView4.setText("学习用品");
                    break;
                default:
                    break;
            }
        }
        //为下方按钮设置监听器
        Button throwBtn = (Button)findViewById(R.id.throw_btn);
        Button saveBtn =(Button)findViewById(R.id.save_btn);
        throwBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(DetailAccount.this);
                dialog.setTitle("警告！");
                dialog.setMessage("账单删除后将无法恢复");
                dialog.setCancelable(true);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dataList.remove(position);
                        DetailAccount.this.finish();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(editText.getText())) {
                    account.setRemarks(editText.getText().toString());
                }
                DetailAccount.this.finish();
                }
        });
    }
}
