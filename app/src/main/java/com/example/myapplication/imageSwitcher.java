package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class imageSwitcher extends AppCompatActivity {

    //图片放到drawable文件夹里面以后 会产生一个id编号用来记录每个图片
    int [] imageId = new int []{R.drawable.flower01,R.drawable.flower02,R.drawable.flower03
            ,R.drawable.flower04,R.drawable.flower05,R.drawable.flower06,R.drawable.flower07};
    //设置多一个参数 用来记录显示的图片的位置
    private int index = 0;
    //声明一下图片切换器的对象
    private ImageSwitcher imageSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_switcher);
        /**
         * 操作思路：
         *  1、获取图片选择器 给他设置图片的淡入淡出效果
         *  2、为其设置ViewSwitcher.ViewFactory 实现 makeView方法
         *  3、为图片切换器设置默认的显示图片
         */
        imageSwitcher = findViewById(R.id.imageSwitcher1);
        //为图片选择器切换图片的设置一下动画 淡入淡出的效果
        //设置图片淡入的效果
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        //设置图片淡出的效果
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
        //为其设置  ViewSwitcher.ViewFactory 并设置一下 makeView
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                //实例化一个imageView 才是图片
                ImageView imageView = new ImageView(imageSwitcher.this);
                //设置一下 图片保持横纵比的居中缩放
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                //设置一下 图片的布局参数  宽度和高度的属性  设置宽高自适应
//                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT
//                        ,FrameLayout.LayoutParams.WRAP_CONTENT));
                //也可以设置图片大小 用具体数值来设置 这里就是android:layout_width  android:layout_height
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(520
                        ,520));
                //返回 处理后的 imageView对象
                return imageView;
            }
        });
        //设置一下默认显示图片
        imageSwitcher.setImageResource(imageId[0]);
        //如果我们要实现图片的切换的话 实际上就是改变索引index的数值 +1的话就是下一张图片 -1的话就是上一张图片
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        //设置上一张的按钮的功能
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进行图片切换上一张的功能
                if (index>0){
                    //将索引的值-1   这样的话 图片就显示到前一张去了
                    index--;
                }else {
                    //当图片是第一张的时候 我们点击上一张图片的话 就是去到最后一张图片
                    index = imageId.length-1;//最后一张图片的索引位置
                }
                //设置一下显示的图片
                imageSwitcher.setImageResource(imageId[index]);
            }
        });

        //设置图片下一张的按钮的功能
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进行图片下一张的按钮的功能
                if (index<imageId.length-1){  //imageId.length-1表示的是最后一张图片的下标位置
                    //当图片不是最后一张的时候  图片下标自增就可以了
                    index++;
                }else {
                    //当图片是最后一张的时候 那么 切换成第一张图片
                    index = 0;
                }
                //设置一下 显示图片
                imageSwitcher.setImageResource(imageId[index]);
            }
        });
    }
}