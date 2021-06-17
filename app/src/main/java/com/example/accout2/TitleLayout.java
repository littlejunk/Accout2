package com.example.accout2;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TitleLayout extends LinearLayout {
    private TextView textView ;
    public TitleLayout(Context context, AttributeSet attrs){
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.title,this);
        textView=(TextView)findViewById(R.id.title_text);
        Button backButton = (Button)findViewById(R.id.title_back);
        backButton.setOnClickListener(new OnClickListener(){
              @Override
            public void onClick(View v){
                  ((Activity) getContext()).finish();
              }
        });
    }

    public TextView getTextView(){
        return textView;
    }

}
