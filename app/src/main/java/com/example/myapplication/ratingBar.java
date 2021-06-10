package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class ratingBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating_bar);
        //获取页面的组件
        Button btn  = findViewById(R.id.btn1);
        RatingBar bar = findViewById(R.id.ratingBar1);
        //设置按钮的点击事件 当按钮被点击以后就提示用户到底选择了多少个星星
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取用户选择的星星的个数
                float rating = bar.getRating();  //这个方法才是获取用户选中的星星的个数
                int numStars = bar.getNumStars();  //这个方法是获取星星的总数 这个方法是包括了 没有选中的星星的
                Toast.makeText(ratingBar.this,"你选中了"+rating+"个星星", Toast.LENGTH_SHORT).show();
            }
        });
    }
}