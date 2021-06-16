package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class gridview extends AppCompatActivity {

    //先声明一下需要使用的图片
    int [] imageId = {R.drawable.flower01,R.drawable.flower02,R.drawable.flower03
            ,R.drawable.flower04,R.drawable.flower05,R.drawable.flower06,R.drawable.flower07};
    //声明一个图片选择器对象
    ImageSwitcher imageSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview);
        //获取图像选择器
        imageSwitcher = findViewById(R.id.imageSwitcher2);
        //然后给他设置淡入淡出的切换的动画效果
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this
                , android.R.anim.fade_in));
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this
                , android.R.anim.fade_out));
        //给图像选择器设置ViewFactory 设置显示图片的参数信息
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            //设置图片视图组件到图片选择器的方法
            @Override
            public View makeView() {
                //实例化一个ImageView组件 显示图片的组件
                ImageView imageView = new ImageView(gridview.this);
                //设置图片组件的缩放显示参数信息
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                //设置图片组件布局显示参数 在图像选择器中的 显示图片的宽度和高度
                imageView.setLayoutParams(new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.WRAP_CONTENT
                        ,FrameLayout.LayoutParams.WRAP_CONTENT));
                return imageView;
            }
        });
        //最后 给图像切换器 设置默认显示图像
        imageSwitcher.setImageResource(imageId[0]);

        //获取网格视图组件
        GridView gridView = findViewById(R.id.gridView);
        //创建一个适配器 然后 重写里面的方法 设置显示图片的信息
        BaseAdapter adapter = new BaseAdapter() {

            //获取图片总数的方法
            @Override
            public int getCount() {
                return imageId.length;
            }

            //获取选中图片的方法
            @Override
            public Object getItem(int position) {
                return position;
            }

            //获取选中的图片的编号
            @Override
            public long getItemId(int position) {
                return position;
            }

            //获取图片视图的方法  需要设置网格视图的显示图片的大小 图片的信息
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView imageView;
                //如果 网格视图显示的图片为空 设置一下 网格视图的显示图片
                if (convertView==null){
                    //实例化 图像视图对象
                    imageView = new ImageView(gridview.this);
                    //设置一下图片的 宽度和高度 内边距
                    imageView.setAdjustViewBounds(true); //边框
                    imageView.setMaxWidth(150);
                    imageView.setMaxHeight(113);
                    imageView.setPadding(5,5,5,5);
                }else {
                    imageView = (ImageView)convertView;
                }
                //设置一下要显示的图片
                imageView.setImageResource(imageId[position]);
                return imageView;
            }
        };
        //将适配器关联到 网格式图上 显示图片到画廊中
        gridView.setAdapter(adapter);
        //设置点击图片事件的监听器 当用户点击完图片以后 就显示原来的图片到 右边 图片选择器里面
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //然 imageSwitcher图像选择器 显示被点击的图片
                imageSwitcher.setImageResource(imageId[position]);
            }
        });
    }
}