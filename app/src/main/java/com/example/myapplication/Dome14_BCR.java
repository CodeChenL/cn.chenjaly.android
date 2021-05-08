package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Dome14_BCR extends AppCompatActivity {

    private TextView tvDisPlay;
    InnerReceiver innerReceiver;
    IntentFilter intentFilter;
    private boolean registerornot = false;
    private class InnerReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            switch (action) {
                case "toRed":
                    tvDisPlay.setBackgroundColor(Color.RED);
                    break;
                case "toGreen":
                    tvDisPlay.setBackgroundColor(Color.GREEN);
                    break;
                case "toBlue":
                    tvDisPlay.setBackgroundColor(Color.BLUE);
                    break;
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dome14__b_c_r);
        tvDisPlay=findViewById(R.id.tvDisplay);
        innerReceiver =new InnerReceiver();
        intentFilter=new IntentFilter();
        intentFilter.addAction("toRed");
        intentFilter.addAction("toBlue");
        intentFilter.addAction("toGreen");
    }
    @SuppressLint("NonConstantResourceId")
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btRegister:
                if (registerornot){
                    Toast.makeText(this,"已经注册无需重复注册",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    registerReceiver(innerReceiver,intentFilter );
                    registerornot=true;
                    Toast.makeText(this,"广播已注册",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btCancel:
                if (registerornot){
                    unregisterReceiver(innerReceiver);
                    registerornot=false;
                    Toast.makeText(this,"广播已注销",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,"广播尚未注册",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btToRed:
                Intent toRed=new Intent("toRed");
                sendBroadcast(toRed);
                break;
            case R.id.btToBlue:
                Intent toBlue=new Intent("toBlue");
                sendBroadcast(toBlue);
                break;
            case R.id.btToGreen:
                Intent toGreen=new Intent("toGreen");
                sendBroadcast(toGreen);
                break;
        }
    }
}