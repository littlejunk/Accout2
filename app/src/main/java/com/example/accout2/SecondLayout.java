package com.example.accout2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
//含有多个按钮的自定义控件，用于查看全部账单，收入添加，支出添加，删除数据
public class SecondLayout extends LinearLayout {
    private Button shujvkuButton;

    private Button shujvtuButton;

    private Button earnButton;

    private Button costButton;
    //为四个按钮配置相应的监听器
    public  SecondLayout(Context context, AttributeSet attrs){
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.second,this);
        shujvkuButton = (Button)findViewById(R.id.second_btn_1);
        shujvtuButton =(Button)findViewById(R.id.second_btn_2);
        earnButton = (Button) findViewById(R.id.second_btn_3);
        costButton =(Button)findViewById(R.id.second_btn_4);
        earnButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent((Activity)getContext(),EarnActivity.class);
                getContext().startActivity(intent);
            }
        });

        costButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent((Activity)getContext(),CostActivity.class);
                getContext().startActivity(intent);
            }
        });

        shujvkuButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent((Activity)getContext(),ListViewActivity.class);
                getContext().startActivity(intent);
            }
        });

        shujvtuButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent((Activity)getContext(),ChartActivity.class);
                getContext().startActivity(intent);
            }
        });

    }


}
