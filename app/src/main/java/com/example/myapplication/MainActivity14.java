package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;

import java.io.InputStream;
import java.util.List;

public class MainActivity14 extends AppCompatActivity implements View.OnClickListener{

    Button btnShowXML;

    private void initView() {
        btnShowXML = findViewById(R.id.btnShowXML);
    }

    private void initListener() {
        btnShowXML.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main14);
        initView();
        initListener();
    }
    @Override
    public void onClick(View v){
        if (v.getId() == R.id.btnShowXML) {
            Log.v("msg", "按钮被点击了");
            InputStream is = getResources().openRawResource(R.raw.products);
            XML2Porduct xml2Porduct = new XML2Porduct();
            try {
                Xml.parse(is, Xml.Encoding.UTF_8, xml2Porduct);

            } catch (Exception e) {
                e.printStackTrace();
            }
            List<Product> products = xml2Porduct.getProducts();
            StringBuilder msg = new StringBuilder("共有" + products.size() + "\t个产品:\n");
            for (Product product : products) {
                msg.append("id: ").append(product.getId()).append("\t产品名称: ").append(product.getName()).append("\t价格： ").append(product.getPrice()).append("\n");
            }
            new AlertDialog.Builder(this)
                    .setTitle("产品信息")  //设置弹框标题
                    .setMessage(msg.toString())  //设置显示信息
                    .setPositiveButton("关闭", null).show();//设置确定按钮的功能
        }
    }
}
