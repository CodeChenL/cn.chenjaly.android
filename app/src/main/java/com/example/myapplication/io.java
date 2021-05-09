package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class io extends AppCompatActivity implements View.OnClickListener{
    //定义内存中写入数据的目标文件
    private static final String MEM_PATH ="file.txt";

    //声明两个需要使用的按钮
    private Button btnWrite1,btnRead1;

    //定义初始化组件的方法
    private void initView(){
        btnWrite1 = findViewById(R.id.btn_write1);
        btnRead1 = findViewById(R.id.btn_read1);
    }

    //定义设置监听器的方法
    private void initListener(){
        btnWrite1.setOnClickListener(this);
        btnRead1.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.io);
        initView();
        initListener();
    }

    //定义将数据写入到内存中的方法
    private void write2Memory(String path){
        try {
            //使用文件输出流打开目标文件
            FileOutputStream os = openFileOutput(MEM_PATH, Activity.MODE_PRIVATE);
            //使用utf-8的编码格式将数据写入到目标文件中
            os.write(path.getBytes("utf-8"));
            //处理完毕后 关闭输出流节约内存空间
            os.close();
            Toast.makeText(getBaseContext(),"写入成功!",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(getBaseContext(),"写入失败!",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    //定义一个从内存中读取数据的方法
    private void readFromMemory(){
        try {
            //使用io流的方式来读取目标文件
            FileInputStream is = openFileInput(MEM_PATH);
            //定义一个字节缓冲区用来暂时存放读取文件
            byte[] buffer = new byte[100];
            //将字节流从io中读取出来 通过缓冲区
            int byteCount = is.read(buffer);
            //然后将获取到的字节信息 转换成字符串
            String str = new String(buffer, 0, byteCount,"utf-8");
            Toast.makeText(getBaseContext(),str,Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(getBaseContext(),"读取失败!",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_write1:
                //当将数据写入内存的按钮被点击的话
                //需要弹出一个提示框 让用户输入数据
                EditText etInput = new EditText(getBaseContext());
                new AlertDialog.Builder(this)
                        .setTitle("请输入字符串")
                        .setView(etInput)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //获取一下 文本框的输入数据
                                String s = etInput.getText().toString();
                                System.out.println("文本框的数据"+s);
                                //将文本框的输入的数据 调用写入内存的方法 写入内存的方法写入到内存中
                                write2Memory(s);
                            }
                        }).setNegativeButton("取消",null).show();
                break;
            case R.id.btn_read1:
                //定义读取数据的方法
                readFromMemory();
                break;
        }
    }
}