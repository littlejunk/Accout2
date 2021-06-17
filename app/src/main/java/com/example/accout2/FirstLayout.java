package com.example.accout2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;
//用于展示本月总收入与支出的控件，可返回对应的textview方法用于实时更新数据
public class FirstLayout extends RelativeLayout {

    private TextView earnText;

    private  TextView costText;

    private  TextView shengyu;

    public FirstLayout(Context context, AttributeSet attrs){
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.first,this);
        earnText = (TextView)findViewById(R.id.first_textview_2);
        costText =(TextView)findViewById(R.id.first_textview_3);
        shengyu = (TextView)findViewById(R.id.first_textview_5);
    }

    public TextView getEarnText(){
        return earnText;
    }

    public TextView getShengyu(){
        return shengyu;
    }

    public TextView getCostText(){
        return costText;
    }
}
