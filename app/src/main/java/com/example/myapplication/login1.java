package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class login1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login1);
        TextView textView=(TextView) findViewById(R.id.test);
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        textView.setText(bundle.getString("登录信息"));
    }
}