package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class bmi1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi1);
        TextView textViewSex=(TextView)findViewById(R.id.sex);
        TextView textViewSg=(TextView)findViewById(R.id.h);
        TextView textViewTz=(TextView)findViewById(R.id.w);
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        textViewSex.setText(bundle.getString("sex"));
        textViewSg.setText(bundle.getString("sg"));
        textViewTz.setText(bundle.getString("tz"));
        Button button=(Button)findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(bmi1.this, bmi.class);
                startActivity(intent1);
            }
        });

    }
}