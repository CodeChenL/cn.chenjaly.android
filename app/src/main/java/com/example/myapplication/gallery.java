package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

public class gallery extends AppCompatActivity {

    //定义一下 画廊需要使用到的图片
    int [] imageId = {R.drawable.flower01,R.drawable.flower02,R.drawable.flower03,R.drawable.flower04,
            R.drawable.flower05,R.drawable.flower06,R.drawable.flower07};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);
        //获取画廊视图
        Gallery gallery = findViewById(R.id.gallery1);
        //创建适配器 来显示列表中显示对的内容
        BaseAdapter adapter = new BaseAdapter() {

            //在适配器中设置 图片的显示的数量
            @Override
            public int getCount() {
                return imageId.length;
            }

            //获取画廊中当前图片的选项 位置编号
            @Override
            public Object getItem(int position) {
                return position;
            }

            //获取当前选项的ID
            @Override
            public long getItemId(int position) {
                return position;
            }

            //获取视图组件
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView imageView;
                //如果画廊中没有显示的图片对象 就先设置一下显示图片的参数信息
                if (convertView==null){
                    imageView = new ImageView(gallery.this);
                    //设置图片的尺寸类型 按照什么方式进行放大
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    //设置一下  画廊的样式文件
//                    TypedArray typedArray = obtainStyledAttributes(R.styleable.Gallery);
                    //设置背景样式 到画廊中
//                    imageView.setBackgroundResource(typedArray.getResourceId(R.styleable.Gallery_android_galleryItemBackground,0));
                    //设置一下图片的显示的宽度和高度
                    imageView.setLayoutParams(new Gallery.LayoutParams(560,360));
                    //设置一下图片的内边距
                    imageView.setPadding(5,0,5,0);
                }else {
                    imageView = (ImageView)convertView;
                }
                //给视图组件设置一下 选中的图片
                imageView.setBackgroundResource(imageId[position]);
                return imageView;
            }
        };
        //将适配器放到 画廊视图中
        gallery.setAdapter(adapter);
        //设置一张默认的选中的图片  我们设置中间那个图片被选中
        gallery.setSelection(imageId.length/2);
        //为画廊设置监听事件  获取被选中的图片的位置
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(gallery.this,"您选中了第"+String.valueOf(position+1)+"张图片",Toast.LENGTH_SHORT).show();
            }
        });
    }
}