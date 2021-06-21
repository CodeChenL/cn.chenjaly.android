package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class listview2 extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在这个ListActivity中  是直接通过java代码来实现listView列表视图的是不需要xml页面的
        //所以我们这里的java代码 不用绑定xml页面 直接将下面这个代码删掉或者注释掉就好了
//        setContentView(R.layout.activity_main7);
        //在这个类中我们只需要设置数组数据 跟 适配器即可完成 视图列表
        String [] songs = new String[]{"哭砂","时间煮雨","有没有人告诉你","栀子花开","等待"};
        //创建一个适配器 设置列表的显示内容
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this
                , android.R.layout.simple_list_item_single_choice   //设置列表的样式
                ,songs   //存放到适配器的数据
        );
        //将适配器关联到当前列表视图
        this.setListAdapter(adapter);
    }

    //如果想要点击每个列选项有反应的话 就要设置 一下选项点击事件的监听器
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String result = l.getItemAtPosition(position).toString();
        Toast.makeText(listview2.this,result,Toast.LENGTH_SHORT).show();
    }
}