package com.example.myapplication.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class BookDao extends SQLiteOpenHelper {

    //数据库名字
    public static String DB_NAME = "lib.db";
    //数据库表名
    public static String TAB_NAME = "lib_book_tab";
    //数据库版本号
    public static int DB_VERSION = 1;

    //构造器 调用父类构造器
    public BookDao(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    public BookDao(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
    }


    //第一次安装使用 创建数据库表的代码
    @Override
    public void onCreate(SQLiteDatabase db) {
        //这个方法就是app安装好以后 会执行SQLiteOpenHelper的子类 执行里面的onCreate
        //因此我们可以将创建数据库表的方法写到 onCreate方法中
        //因为SQLite使用的也是sql语法 所以创建数据库表的语法语句也是sql语句
        Log.i("tag","onCreate");
        String sql = "create table " + TAB_NAME
                + "("
                + BookBean.FILED_ID + " integer primary key autoincrement,"
                + BookBean.FILED_BOOKNAME + " varchar(20),"
                + BookBean.FILED_WRITER + " text,"
                + BookBean.FILED_PRESS + " varchar(20),"
                + BookBean.FILED_PRICE + " long)";
        //可以将sql语句打印到控制台
        System.out.println("sql--->"+sql);
        //我们需要通过SQLiteDatabase db 对象去执行sql语句  SQLiteDatabase db里面存放了许多操作sqlite数据库的方法
        //SQLiteDatabase db已经由SQLiteOpenHelper创建好了  所以我们可以直接使用它
        db.execSQL(sql);
    }

    //更新数据库表中的信息
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.i("tag","onUpgrade");
        //这个方法一般就是用来执行数据库表修改更新操作的方法
        String sql = "drop table  if exist" + TAB_NAME;   //这里的exist 会提示由错误 不用管它
        //执行sql语句
        db.execSQL(sql);
        //再次手动调用onCreate
        onCreate(db);
    }
}
