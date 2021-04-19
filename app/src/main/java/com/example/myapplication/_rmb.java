package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        Button button_qk=(Button)findViewById(R.id.btn_rmb_qk);
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        System.out.println(bundle.getString("demo"));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rmb rmb=new Rmb();
                rmb.setRmb(editText.getText().toString());
                textView.setText(rmb.getRmb());
            }
        });
        button_qk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(null);
            }
        });
    }
}