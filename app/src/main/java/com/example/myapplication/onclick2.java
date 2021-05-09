package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//使用自身类 来作为事件的监听器  课本84页
public class onclick2 extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onclick2);
        //首先要获取这个被点击的按钮
        Button btn2 = (Button)findViewById(R.id.btn2);
        //这里这个this指的是 当前类的对象 MainActivity2 因为它继承了View.OnClickListener接口
        btn2.setOnClickListener(this);
    }

    //重写onClick 方法处理点击事件
    @Override
    public void onClick(View v) {
        Toast.makeText(onclick2.this,"按钮2被点击了",Toast.LENGTH_SHORT).show();
    }
}