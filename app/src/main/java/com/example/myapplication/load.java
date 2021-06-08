package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class load extends AppCompatActivity {

    //首先 定义4个成员变量
    private ProgressBar horizonP;       //水平方向进度条
    private ProgressBar circleP;        //圆形进度条
    private int mProgressStatus = 0;    //设置进度条的默认的状态
    private Handler mHandle;            //用来处理进度条完成状态的信息的处理器对象

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load);
        //分别获取水平方向进度条 跟 圆形进度条
        horizonP = findViewById(R.id.progressBar1);
        circleP = findViewById(R.id.progressBar2);
        //进度条 根据事件的完成状态来实时改变进度条的状态的处理代码 是Handler类对象来完成的
        //我们就实例化一个Handler对象
        mHandle = new Handler(){

            // 然后重写里面的handleMessage方法
            // 来实现 耗时操作 没有完成时的进度条的显示进度条
            @Override
            public void handleMessage(@NonNull Message msg) {
                //接收进度条的运行状态信息 然后处理进度条
                if (msg.what ==0x111){   //0x111十六位进制表示 的一个数字 表示进度条还没有走满的意思
                    //进度条没有满 那么我们就调用setProgress方法 去更新进度条的显示状态
                    horizonP.setProgress(mProgressStatus);
                }else{
                    Toast.makeText(load.this,"耗时操作完成",Toast.LENGTH_SHORT).show();
                    //进度条加载完成后在页面消失
                    horizonP.setVisibility(View.GONE);
                    circleP.setVisibility(View.GONE);
                }
            }
        };


        //在计算机当中 如果需要同时做多件事情的话 就需要使用线程来完成
        //我们可以创建一个线程对象Thread 来模拟耗时操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                //线程的工作任务就是写在这里的
                //这个线程的任务就是循环的去调用耗时操作的方法 直到mProgressStatus进度条的默认的状态到100
                while (true){
                    //获取耗时操作的完成的情况 进度情况
                    mProgressStatus = doWork();
                    //进度条的信息  里面存放的是发送给进度条运行状态的信息
                    Message m = new Message();
                    if (mProgressStatus<100){
                        //进度条还没有 走满 还在加载没有加载满整个进度条
                        m.what=0x111;  //表示进度条还没有走满 需要增加进度
                        //将消息发送给进度条处理器
                        mHandle.sendMessage(m);
                    }else {
                        m.what = 0x110;//进度条走满了
                        mHandle.sendMessage(m);
                        //进度条走满了 使用break终止循环 结束这个死循环
                        break;
                    }
                }
            }

            //模拟一个耗时操作
            private int doWork(){
                //random()方法是在0-1之间产生一个随机数
                //这里通过这个产生的随机数 来表示模拟一个每次进度条更新的进度
                mProgressStatus += Math.random()*10;
                //因为计算机执行这段代码 太快了 所以我们让他休眠200ms再来执行这段代码
                //然他有个 暂停一小会的时间 有个停顿 一下下的感觉
                try {
                    //让这个线程休眠 200毫秒
                    Thread.sleep(200);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return mProgressStatus;
            }


            //调用start方法启动线程
        }).start();
    }
}