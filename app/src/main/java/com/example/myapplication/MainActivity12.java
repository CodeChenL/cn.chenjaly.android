package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity12 extends AppCompatActivity {
    private int sex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12);
        RadioGroup radioGroup=(RadioGroup)findViewById(R.id.rgSex);
        RadioButton radioButtonN=(RadioButton)findViewById(R.id.n);
        RadioButton radioButtonV=(RadioButton)findViewById(R.id.v);
        Button button=(Button)findViewById(R.id.js);
        Intent intent=new Intent(MainActivity12.this,MainActivity13.class);
        Bundle bundle= new Bundle();
        EditText editText=(EditText)findViewById(R.id.sg);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                sex=checkedId;
                System.out.println(sex);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("sg","您的身高为"+editText.getText().toString());
                if (sex==R.id.n){
                    bundle.putString("sex","您是一位男士");
                    bundle.putString("tz","您的标准体重为"+String.valueOf((Integer.parseInt(editText.getText().toString())-80)*0.7)+"公斤");
                }else if (sex==R.id.v){
                    bundle.putString("sex","您是一位女士");
                    bundle.putString("tz","您的标准体重为"+String.valueOf((Integer.parseInt(editText.getText().toString())-70)*0.6)+"公斤");
                }
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}