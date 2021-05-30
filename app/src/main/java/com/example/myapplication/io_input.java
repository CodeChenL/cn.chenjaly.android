package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class io_input extends AppCompatActivity implements View.OnClickListener {
    //定义内存中写入数据的目标文件
    private static final String MEM_PATH = "file.txt";

    private static final String SDCard_PATH = android.os.Environment.getExternalStorageDirectory() + "/image.jpg";
    //声明两个需要使用的按钮
    private Button btnWrite1, btnRead1, btnWrite2, btnRead2;

    private ImageView iv_sd;
    int width, height;

    //定义初始化组件的方法
    private void initView() {
        btnWrite1 = findViewById(R.id.btn_write1);
        btnRead1 = findViewById(R.id.btn_read1);

        btnWrite2 = findViewById(R.id.btn_write2);
        btnRead2 = findViewById(R.id.btn_read2);
        iv_sd = findViewById(R.id.iv_sd);
    }

    //定义设置监听器的方法
    private void initListener() {
        btnWrite1.setOnClickListener(this);
        btnRead1.setOnClickListener(this);
        btnWrite2.setOnClickListener(this);
        btnRead2.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.io_input);
        //获取一下页面的宽度和高度 稍后用来
        width = getWindowManager().getDefaultDisplay().getWidth();
        height = getWindowManager().getDefaultDisplay().getHeight();
        initView();
        initListener();
    }

    //定义将数据写入到内存中的方法
    private void write2Memory(String path) {
        try {
            //使用文件输出流打开目标文件
            FileOutputStream os = openFileOutput(MEM_PATH, Activity.MODE_PRIVATE);
            //使用utf-8的编码格式将数据写入到目标文件中
            os.write(path.getBytes("utf-8"));
            //处理完毕后 关闭输出流节约内存空间
            os.close();
            Toast.makeText(getBaseContext(), "写入成功!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "写入失败!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    //定义一个从内存中读取数据的方法
    private void readFromMemory() {
        try {
            //使用io流的方式来读取目标文件
            FileInputStream is = openFileInput(MEM_PATH);
            //定义一个字节缓冲区用来暂时存放读取文件
            byte[] buffer = new byte[100];
            //将字节流从io中读取出来 通过缓冲区
            int byteCount = is.read(buffer);
            //然后将获取到的字节信息 转换成字符串
            String str = new String(buffer, 0, byteCount, "utf-8");
            Toast.makeText(getBaseContext(), str, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "读取失败!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    //定义将图片写入到存储空间中的方法
    private void write2SDCard() {
        try {
            //获取操作访问存储空间的权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            //创建文件输出流 通过这个输出流来写文件到 外部存储空间中
            FileOutputStream fos = new FileOutputStream(SDCard_PATH);
            //获取要被操作的文件 assets文件夹下面的image.jpg这个图片
            InputStream is = getResources().getAssets().open("image.jpg");
            //使用 io流来将assets文件夹中的图片写入到SDCard_PATH这个路径下面 就是文件的拷贝 复制
            //复制文件的话 首先先要读取源文件 然后再写入到一个新的位置
            //定义一个缓冲区 用来读取文件
            byte[] buffer = new byte[8192];
            //记录每次读取字节的长度
            int count = 0;
            //复制文件的话 首先先要读取源文件 然后再写入到一个新的位置
            while ((count = is.read(buffer)) > 0) {
                //只要我每次读的都有数据的话 我就将读取出来的数据 写入到目标文件里面去
                fos.write(buffer, 0, count);
            }
            Toast.makeText(getBaseContext(), "文件成功写入到" + SDCard_PATH, Toast.LENGTH_SHORT).show();
            //文件操作完毕关闭io流
            fos.close();
            is.close();
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "文件写入失败!!!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    //定义 读取存储空间中的图片的方法 然后将读取的图片显示到 手机应用上面
    private void readFromSDCard() {
        String name = SDCard_PATH;
        //判断图片是否存在
        if (!new File(name).exists()) {
            Toast.makeText(getBaseContext(), "图片不存在!!!", Toast.LENGTH_SHORT).show();
            return;
        }
        //有图片的话 我们就去读取图片
        try {
            //调用压缩图片的方法来获取压缩后的图片
            Bitmap bitmap2 = getBitmap2(name, width, height);
            iv_sd.setImageBitmap(bitmap2);
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "图片载入失败!!!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    //getBitmap2用于 通过计算原始图片的宽高 然后压缩图片 最后得到压缩后的图片Bitmap
    public static Bitmap getBitmap2(String imageFilePath, int displayWidth, int displayHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //但是允许我们查询图片的信息这其中就包括图片大小信息 但是又不返回实际的图片Bitmap
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(imageFilePath, options);
        //编码以后bitmap的宽高 bitmap除以屏幕的宽高度得到压缩比
        int widthRatio = (int) (options.outWidth / (float) displayWidth);
        int heightRatio = (int) (options.outHeight / (float) displayHeight);

        if (widthRatio > 1 && heightRatio > 1) {
            if (widthRatio > heightRatio) {
                //进行压缩 压缩到原来的（1/widthRatios）
                options.inSampleSize = widthRatio;
            } else {
                options.inSampleSize = heightRatio;
            }
        }
        options.inJustDecodeBounds = false;
        Bitmap bmp = BitmapFactory.decodeFile(imageFilePath, options);
        //图片压缩比例.
//        options.inSampleSize = ssize;
        return bmp;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
                                System.out.println("文本框的数据" + s);
                                //将文本框的输入的数据 调用写入内存的方法 写入内存的方法写入到内存中
                                write2Memory(s);
                            }
                        }).setNegativeButton("取消", null).show();
                break;
            case R.id.btn_read1:
                //定义读取数据的方法
                readFromMemory();
                break;
            case R.id.btn_write2:
                //调用一下写入图片的方法
                write2SDCard();
                break;
            case R.id.btn_read2:
                readFromSDCard();
                break;
        }
    }
}