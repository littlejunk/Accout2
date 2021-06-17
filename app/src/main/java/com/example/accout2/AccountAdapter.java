package com.example.accout2;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class AccountAdapter extends ArrayAdapter<Account> {

    private int resourceId;

    public AccountAdapter(Context context , int textviewResourceId , List<Account> object){
        super(context,textviewResourceId,object);
        resourceId = textviewResourceId;
    }
    @Override
    public View getView(int postiion, View convertView , ViewGroup parent){
        Account account = getItem(postiion);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,
                false);
        TextView flag = (TextView)view.findViewById(R.id.flage_textview);
        TextView money = (TextView)view.findViewById(R.id.monry_textview);
        TextView moneyType = (TextView)view.findViewById(R.id.moneytype_textview);
        TextView remarks = (TextView)view.findViewById(R.id.remarks_textview);
        TextView time = (TextView)view.findViewById(R.id.time_textview);
        if(account.getFlag()){
            flag.setText("收入");
            flag.setTextColor(Color.parseColor("#00CC00"));
        }else{
            flag.setText("支出");
            flag.setTextColor(Color.parseColor("#FF3300"));
        }
        money.setText(new DecimalFormat("00.00").format(account.getMoney()));
        if(account.getFlag()){
            switch (account.getMonryType()){
                case 1 :
                    moneyType.setText("标签：其他");
                    break;
                case 2 :
                    moneyType.setText("标签：工资薪水");
                    break;
                case 3 :
                    moneyType.setText("标签：基金股票");
                    break;
                case 4 :
                    moneyType.setText("标签：被动收入");
                    break;
                case 5 :
                    moneyType.setText("标签：兼职打工");
                    break;
                default:
                    break;
            }
        }else {
            switch (account.getMonryType()) {
                case 1:
                    moneyType.setText("标签：其他");
                    break;
                case 2:
                    moneyType.setText("标签：零食饮料");
                    break;
                case 3:
                    moneyType.setText("标签：学校饮食");
                    break;
                case 4:
                    moneyType.setText("标签：外出娱乐");
                    break;
                case 5:
                    moneyType.setText("标签：学习用品");
                    break;
                default:
                    break;
            }
        }
        remarks.setText("备注："+account.getRemarks());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        time.setText(simpleDateFormat.format(account.getDate()));
        return view;

    }

}
