package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class bmi extends AppCompatActivity {
    private int sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rgSex);
        Button button = (Button) findViewById(R.id.js);
        Intent intent = new Intent(bmi.this, bmi1.class);
        Bundle bundle = new Bundle();
        EditText editText = (EditText) findViewById(R.id.sg);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("sg", "您的身高为" + editText.getText().toString());
                if (radioGroup.getCheckedRadioButtonId() == R.id.n) {
                    bundle.putString("sex", "您是一位男士");
                    bundle.putString("tz", "您的标准体重为" + String.valueOf((Integer.parseInt(editText.getText().toString()) - 80) * 0.7) + "公斤");
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.v) {
                    bundle.putString("sex", "您是一位女士");
                    bundle.putString("tz", "您的标准体重为" + String.valueOf((Integer.parseInt(editText.getText().toString()) - 70) * 0.6) + "公斤");
                }
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}