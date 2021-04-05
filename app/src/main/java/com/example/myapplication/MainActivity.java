package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button btn=(Button)findViewById(R.id.btn1);
        System.out.println(btn.getText());
        btn.setOnClickListener(new MyListener());
        Button btn1=(Button)findViewById(R.id.btn2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"按钮2被点击了",Toast.LENGTH_LONG).show();
            }
        });
    }
    class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this,"按钮1被点击了",Toast.LENGTH_LONG).show();
        }
    }
}