package com.example.accout2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Alldata extends RelativeLayout {
    private TextView monthcost;

    private TextView monthearn;

    private TextView yearcost;

    private TextView yearearn;

    public Alldata(Context context, AttributeSet attrs){
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.all,this);
        monthcost = (TextView)findViewById(R.id.monthcostmoney);
        monthearn = (TextView)findViewById(R.id.monthearnmoney);
        yearcost = (TextView)findViewById(R.id.yearcostmoney);
        yearearn = (TextView)findViewById(R.id.yearearnmoney);
    }

    public TextView getMonthcost(){
        return this.monthcost;
    }

    public TextView getMonthearn(){
        return this.monthearn;
    }

    public TextView getYearcost(){
        return this.yearcost;
    }

    public  TextView getYearearn(){
        return this.yearearn;
    }
}
