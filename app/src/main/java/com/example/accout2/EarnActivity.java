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

public class EarnActivity extends AppCompatActivity {

//    final static int MENU_ADD = Menu.FIRST;
//    final static int MENU_SAVE = Menu.FIRST + 1;
//    final static int MENU_DELETE = Menu.FIRST + 2;
//    final static int MENU_ADDUSER = Menu.FIRST + 3;
//    final static int MENU_ADDDOCUMENT = Menu.FIRST + 4;

    private MyData myData ;

    private ArrayList<Account> dataList;

    private int moneytype=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //隐藏标题栏
        ActionBar actionBar =getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        myData = (MyData)getApplication();
        dataList = myData.getDataList();
        setContentView(R.layout.activity_earn);
        TitleLayout titleLayout =(TitleLayout)findViewById(R.id.title_earn);
        titleLayout.getTextView().setText("收入");
        Button earnSaveButton = (Button)findViewById(R.id.earn_save);
        final EditText earnMoney = (EditText)findViewById(R.id.earnmoneyeditview);
        final EditText earnRemarks = (EditText)findViewById(R.id.earnremarkseditview);
        final Button earntype = (Button)findViewById(R.id.earnmenubutton);
        earntype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(EarnActivity.this,v);
                getMenuInflater().inflate(R.menu.earnmenu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.earnmenu1 :
                                moneytype = 1;
                                earntype.setText("其他");
                                return true;
                            case R.id.earnmenu2 :
                                moneytype = 2;
                                earntype.setText("工资薪水");
                                return true;
                            case R.id.earnmenu3:
                                moneytype = 3;
                                earntype.setText("基金股票");
                                return true;
                            case R.id.earnmenu4:
                                moneytype = 4;
                                earntype.setText("被动收入");
                                return true;
                            case  R.id.earnmenu5:
                                moneytype =5;
                                earntype.setText("兼职打工");
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });
        earnSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(earnMoney.getText())) {
                    Toast.makeText(EarnActivity.this, "请输入金额", Toast.LENGTH_SHORT).show();
                }else if(!isNumber(earnMoney.getText().toString())){
                    Toast.makeText(EarnActivity.this, "请输入正确格式，如2或2.52", Toast.LENGTH_SHORT).show();
                } else {
                    if(isInteger(earnMoney.getText().toString())){
                        Account account = new Account(true,(float)Integer.valueOf(earnMoney.getText().toString()),
                                earnRemarks.getText().toString(),moneytype);
                        dataList.add(0,account);
                        EarnActivity.this.finish();
                    }else {
                        Account account = new Account(true,Float.parseFloat(earnMoney.getText().toString()),
                                earnRemarks.getText().toString(),moneytype);
                        dataList.add(0,account);
                        EarnActivity.this.finish();
                    }

                }

            }
        });
//        TextView textView = (TextView)findViewById(R.id.earnmenutextview);
//        registerForContextMenu(textView);
    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//
//        //实例化菜单代码
//        MenuInflater inflater=getMenuInflater();
//        inflater.inflate(R.menu.earnmenu ,menu);
//    }
    //@Override
//    public boolean onContextItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case MENU_ADD:
//                Toast.makeText(EarnActivity.this, "添加", Toast.LENGTH_SHORT).show();
//                isSaved=false;
//                break;
//            case MENU_SAVE:
//                Toast.makeText(EarnActivity.this, "保存", Toast.LENGTH_SHORT).show();
//                isSaved=true;
//                break;
//            case MENU_DELETE:
//                Toast.makeText(EarnActivity.this, "删除", Toast.LENGTH_SHORT).show();
//                break;
//            case MENU_ADDUSER:
//                Toast.makeText(EarnActivity.this, "添加用户", Toast.LENGTH_SHORT).show();
//                break;
//            case MENU_ADDDOCUMENT:
//                Toast.makeText(EarnActivity.this, "添加文档", Toast.LENGTH_SHORT).show();
//                break;
//            default:
//                throw new IllegalStateException("Unexpected value: " + item.getItemId());
//        }
//
//        return super.onContextItemSelected(item);
//    }
     private static boolean isNumber(String s){
        String reg = "^[0-9]+(.[0-9]+)?$";
        return s.matches(reg);
     }

    private boolean isInteger(String s){
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(s).matches();
    }
}

