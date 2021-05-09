package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class onclick3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //这里这个句子的意思就是 绑定到 activity_main3.xml这个文件中
        setContentView(R.layout.onclick3);
    }

    //因为  xml页面设置了 onClick属性 就不用 去再编写监听器 直接在这里定义点击方法就得了
    public void onClick(View v){
        //View v这个形式参数就表示的是触发了这个点击事件的按钮
        switch (v.getId()){
            //如果说是button1被点击了
            case R.id.button1:
                Toast.makeText(onclick3.this,"按钮1被点击了",Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                Toast.makeText(onclick3.this,"按钮2被点击了",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}