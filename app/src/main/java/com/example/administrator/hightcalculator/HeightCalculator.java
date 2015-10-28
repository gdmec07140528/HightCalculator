package com.example.administrator.hightcalculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class HeightCalculator extends Activity {
    private Button b1;
    private EditText et1;
    private CheckBox cb1;
    private CheckBox cb2;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height_calculator);
        b1= (Button) findViewById(R.id.button);
        et1= (EditText) findViewById(R.id.editText);
        cb1= (CheckBox) findViewById(R.id.checkBox);
        cb2= (CheckBox) findViewById(R.id.checkBox2);
        tv1= (TextView) findViewById(R.id.textView4);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerEvent();
    }

    private void registerEvent() {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!et1.getText().toString().trim().equals("")) {
                    if (cb1.isChecked() || cb2.isChecked()) {
                        Double weight = Double.parseDouble(et1.getText().toString());
                        StringBuffer sb = new StringBuffer();
                        sb.append("--------评估结果---------\n");
                        if (cb1.isChecked()) {
                            sb.append("男性标准身高：");
                            double result = evaluateHeight(weight, "男");
                            sb.append(result + "(厘米)");
                        }
                        if (cb2.isChecked()) {
                            sb.append("女性标准身高：");
                            double result = evaluateHeight(weight, "女");
                            sb.append(result + "(厘米)");
                        }
                        tv1.setText(sb.toString());
                    } else {
                        showMessage("请选择性别！");
                    }

                } else {
                    showMessage("请输入体重！");
                }
            }
        });
    }
    private double evaluateHeight(Double weight, String sex) {
        double height;
        if (sex=="男") {
            height=170-(62-weight)/0.6;
        }else{
            height=158-(52-weight)/0.5;
        }
        return height;
    }

    private void showMessage(String message) {
        AlertDialog alert=new AlertDialog.Builder(this).create();
        alert.setTitle("系统信息");
        alert.setMessage(message);
        alert.setButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
           alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.add(Menu.NONE,1,Menu.NONE,"退出");
        getMenuInflater().inflate(R.menu.menu_height_calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(item.getItemId()){
            case 1:
                finish();
                break;

        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
