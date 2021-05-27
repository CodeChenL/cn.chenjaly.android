package com.example.myapplication.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;

public class sqliteMain extends AppCompatActivity implements View.OnClickListener {

    private EditText etBookName,etWriter,etPress,etPrice;
    private Button btnQuery,btnAdd,btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite_main);
        //初始化控件
        initView();
        //设置监听器
        initListener();
    }

    private void initView(){
        etBookName = findViewById(R.id.main_bookname);
        etWriter = findViewById(R.id.main_writer);
        etPress = findViewById(R.id.main_press);
        etPrice = findViewById(R.id.main_price);

        btnAdd = findViewById(R.id.main_btn_add);
        btnQuery = findViewById(R.id.main_btn_query);
        btnExit = findViewById(R.id.main_btn_exit);
    }

    private void initListener(){
        btnAdd.setOnClickListener(this);
        btnQuery.setOnClickListener(this);
        btnExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //replace将 输入文本框中的空格 " " 替换成 ""去掉空格
        String strBookName = etBookName.getText().toString().replace(" ","");
        String strWriter = etWriter.getText().toString().replace(" ","");
        String strPress = etPress.getText().toString().replace(" ","");
        String strPrice = etPrice.getText().toString().replace(" ","");

        switch (view.getId()){
            case R.id.main_btn_query:
                //查询弹出结果界面显示 传递参数
                queryData(strBookName,strWriter,strPress,strPrice);
                break;
            case R.id.main_btn_exit:
                finish();
                break;
            case  R.id.main_btn_add:
                //添加记录
                addData(strBookName,strWriter,strPress,strPrice);
                break;
            default:
                break;
        }


    }

    //添加数据
    private void addData(String strBookName,String strWriter,String strPress,String strPrice){
        //添加数据的步骤
        //1获取 页面窗口中要添加的数据 判断至少要有一项输入数据(因为这个数据 已经在上面获取到了 所以直接判断是否为空就好了)
        if (strBookName.equals("")||strWriter.equals("")||strPress.equals("")||strPrice.equals("")){
            Toast.makeText(this,"至少要输入一项数据!!!",Toast.LENGTH_SHORT).show();
        }else {
            //如果数据不为空 就可以开始添加数据
            //2获取执行sql语句的对象
            BookDao bookDao = new BookDao(this);
            //因为SQLiteDatabase是由 BookDao的父类SQLiteOpenHelper创建的 所以我们可以从BookDao中获取
            SQLiteDatabase db = bookDao.getWritableDatabase();
            //3编写添加数据sql语句
            try {
                String  sql = "insert into " + BookDao.TAB_NAME +"(bookname,writer,press,price) values (?,?,?,?)";
                //4执行调用sql语句
                db.execSQL(sql,new Object[]{strBookName,strWriter,strPress,Double.parseDouble(strPrice)});
                Toast.makeText(this,"数据添加成功！！！",Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                Toast.makeText(this,"数据添加失败！！！",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }

    }


    //查询数据的方法
    private void queryData(String strBookName,String strWriter,String strPress,String strPrice){
        //补全代码======================================================
    }
}