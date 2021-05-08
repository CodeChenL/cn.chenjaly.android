package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button btn1=(Button)findViewById(R.id.btn1);
        System.out.println(btn1.getText());
        btn1.setOnClickListener(new MyListener());
        Button btn2=(Button)findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MainActivity11.class);
                Bundle bundle= new Bundle();
                bundle.putString("demo","数据");
                intent.putExtras(bundle);
                startActivity(intent);
                Toast.makeText(MainActivity.this,"按钮2被点击了",Toast.LENGTH_LONG).show();
            }
        });
        Button btn3=(Button)findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MainActivity10.class);
                startActivity(intent);
            }
        });
        Button btn4=(Button)findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MainActivity12.class);
                startActivity(intent);
            }
        });
        Button btn5=(Button)findViewById(R.id.btn5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Dome14_BCR.class);
                startActivity(intent);
            }
        });
        Button btn6=(Button)findViewById(R.id.btn5);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MainActivity14.class);
                startActivity(intent);
            }
        });
    }
    class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(MainActivity.this, rmb.class);
            startActivity(intent);
        }
    }
}