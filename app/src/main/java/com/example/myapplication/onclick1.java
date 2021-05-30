package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class onclick1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onclick1);
        //首先 获取页面的按钮
        Button btn = (Button) findViewById(R.id.btn);
        //设置按钮的监听器
        btn.setOnClickListener(new View.OnClickListener() {
            //OnClickListener这个接口中的 OnClick方法才是对按钮被点击后的处理
            @Override
            public void onClick(View v) {
                //context 跟text只是这个makeText这个方法上面的参数名称而已 idea 特有的提示
                Toast.makeText(onclick1.this, "按钮被点击了", Toast.LENGTH_SHORT).show();
            }
        });
    }
}