package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        EditText editText1=(EditText)findViewById(R.id.editText1);
        EditText editText2=(EditText)findViewById(R.id.editText2);
        Button button1=(Button)findViewById(R.id.button1);
        Button button2=(Button)findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText1.getText().toString().equals("admin888")&&editText2.getText().toString().equals("admin888")){
                    Intent intent=new Intent(login.this, login1.class);
                    Bundle bundle= new Bundle();
                    bundle.putString("登录信息","张三登录成功");
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else {
                    Toast.makeText(login.this,"按钮1被点击了",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}