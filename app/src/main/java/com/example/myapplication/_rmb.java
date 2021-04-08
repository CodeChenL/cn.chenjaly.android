package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Rmb;

public class _rmb extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__rmb);
        Button button=(Button)findViewById(R.id.btn_rmb);
        EditText editText=(EditText)findViewById(R.id.edit_rmb);
        TextView textView=(TextView)findViewById(R.id.text_rmb);
        System.out.println("HHHHHHHHHHHHHHHHHH"+button.getText());
    }
}