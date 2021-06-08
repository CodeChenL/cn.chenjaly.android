package com.example.myapplication.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.sqlite.Dao.BookAdapter;
import com.example.myapplication.sqlite.Dao.BookBean;
import com.example.myapplication.sqlite.Dao.BookDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class result extends AppCompatActivity implements View.OnClickListener {

    //控件
    Button btnBack;
    ListView listView;

    TextView tmpBookName;

    //数据库变量
    BookDao dao;
    SQLiteDatabase db;
    ArrayList<BookBean> resultList;//显示item的list集合
    List<Map<String,Object>> listAdapter; //数据映射list集合
    String [] index = {"id","bookname","writer","press","price"};
    BookAdapter adapter;

    //常量
    public final static int MSG_DEL_SUCCESS = 1;
    public final static int MSG_DEL = 2;

    //变量
    String idData; //选择的id
    int positionData;
    String str;//修改的书名
    Map<String,Object> map;//选中的item映射

    //广播变量
    IntentFilter intentFilter;
    RefreshReceiver receiver;

    @Override
    protected void onDestroy() {
        System.out.println("destroy注销");
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    private void initView(){
        //初始化组件
        btnBack = findViewById(R.id.res_ly_btn_back);
        listView = findViewById(R.id.listview);
        //设置监听
        btnBack.setOnClickListener(this);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        //注册广播
        intentFilter = new IntentFilter();
        intentFilter.addAction("update");
        //创建广播接收器对象
        receiver = new RefreshReceiver();
        this.registerReceiver(receiver,intentFilter);
        super.onStart();
        //初始化页面组件
        initView();
        //初始化数据集
        initData();
        //获取list集合
        resultList = getItemList();
        if (resultList.size()!=0){
            //获取数据映射
            listAdapter = getDataMap(resultList);
            //加载适配器 列表数据 是通过适配器去赋值显示的
            adapter = new BookAdapter(result.this,listAdapter,index);
            listView.setAdapter(adapter);
        }else {
            Toast.makeText(result.this,"无此图书信息!",Toast.LENGTH_SHORT).show();
        }
        //点击item列表 显示删除或者修改
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //获取适配器中的映射数据
                map = (Map<String, Object>)adapter.getItem(position);
                idData = map.get("id").toString();
                System.out.println("选择的id"+idData);
                positionData = position;
                //弹框提示
                new AlertDialog.Builder(result.this)
                        .setTitle("对本条记录如何操作")
                        .setPositiveButton("删除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deleteData();
                            }
                        })
                        .setNegativeButton("修改", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //弹出activity修改 传递id编号
                                Intent intent = new Intent(result.this, update.class);
                                Bundle bundle = new Bundle();
                                bundle.putString(BookBean.FILED_ID,idData);
                                bundle.putString(BookBean.FILED_BOOKNAME,map.get(BookBean.FILED_BOOKNAME).toString());
                                bundle.putString(BookBean.FILED_WRITER,map.get(BookBean.FILED_WRITER).toString());
                                bundle.putString(BookBean.FILED_PRESS,map.get(BookBean.FILED_PRESS).toString());
                                bundle.putString(BookBean.FILED_PRICE,map.get(BookBean.FILED_PRICE).toString());
                                intent.putExtra("bundle",bundle);
                                startActivity(intent);
                            }
                        }).show();
                //修改以后更新 listView
                freshData();
            }
        });
    }

    //定义获取adpter的数据映射的方法
    private List<Map<String,Object>> getDataMap(ArrayList<BookBean> resultList){
        ArrayList<Map<String, Object>> dataMap = new ArrayList<>();
        Map<String,Object> map;
        for (BookBean book : resultList) {
            //通过键值对的形式来存放 图书数据
            map = new HashMap<String, Object>();
            map.put("id",book.getId()+"");
            map.put("bookname",book.getBookname());
            map.put("writer",book.getWriter());
            map.put("press",book.getPress());
            map.put("price",book.getPrice()+"");
            dataMap.add(map);
        }
        return dataMap;
    }

    //获取显示item数据
    private ArrayList<BookBean> getItemList(){
        ArrayList<BookBean> list = new ArrayList<>();
        //首先先获取操作数据库表的 对象
        BookDao bookDao = new BookDao(this);
        db = bookDao.getWritableDatabase();
        try {
            //补全代码
            //我们要获取 刚刚MainActivity 页面跳转过来的数据
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            String strBookName = bundle.getString(BookBean.FILED_BOOKNAME);
            String strWriter = bundle.getString(BookBean.FILED_WRITER);
            String strPrice = bundle.getString(BookBean.FILED_PRICE);
            String strPress = bundle.getString(BookBean.FILED_PRESS);
            //将获取过来的数据 作为sql 查询语句的查询条件 去进行sql
            //编写sql查询语句  因为图书有很多 信息不是很确定 所以我们要使用模糊查询
            String sql = "select * from " +BookDao.TAB_NAME + " where ";
            //select * from 表名 where bookName like '%java%' and writer like '%T%' and price = 200
            //因为传过来的参数是不确定的  有4种情况  因此这里我需要拼接一下查询条件
            //创建一个容器 用来存放查询条件的
            ArrayList<String> list1 = new ArrayList<>();
            //将查询条件语句放到 集合
            if (!strBookName.equals("")){
                list1.add(BookBean.FILED_BOOKNAME+ " like "+"'%"+strBookName+"%'");
            }
            if (!strWriter.equals("")){
                list1.add(BookBean.FILED_WRITER+ " like "+"'%"+strWriter+"%'");
            }
            if (!strPress.equals("")){
                list1.add(BookBean.FILED_PRESS+ " like "+"'%"+strPress+"%'");
            }
            if (!strPrice.equals("")){
                list1.add(BookBean.FILED_PRICE+ "="+strPrice);
            }
            //进行sql语句的拼接
            String str1 ="";

            if (list1.size()>1){
                //list1.size()>1存放查询条件的容器 里面有多个查询条件 拼接查询语句
                for (int i = 0;i<list1.size()-1;i++){
                    //最后一个条件的话是不需要拼接上and关键字的
                    str1 += " " + list1.get(i)+" and ";
                }
                //最后一个条件是没有and的
                str1 += list1.get(list1.size()-1);
            }else{
                //只有一个查询条件的情况下
                str1 = list1.get(0);
            }
            //将查询条件拼接到 sql语句上面
            sql +=str1;
            //看看sql语句拼接的有没有问题
            System.out.println(sql);
            //获取SQLiteDatabase对象 然后执行sql语句
            //查询完毕返回cursor 游标对象类似jdbc里面的resultSet查询结果集 用来存放查询结果的
            Cursor cursor = db.rawQuery(sql, null);

            //移动游标  获取游标中的数据
            while (cursor.moveToNext()){
                //游标指向哪一行就会记录哪一行的全部数据 将这些全部数据放到 游标对象里面
                //我们要将这个游标里面的数据取出来
                BookBean book =  new BookBean(cursor.getInt(0),cursor.getString(1),
                        cursor.getString(2),cursor.getString(3),
                        cursor.getDouble(4));
                //将查询出来的数据放到list集合中
                list.add(book);
            }
            Log.i("tag","图书查询成功!!!");
        }catch (Exception e){
            Log.i("tag","图书查询失败!!!");
            e.printStackTrace();
        }
        return list;
    }

    //初始化集合数据容器
    private void initData(){
        resultList = new ArrayList<BookBean>();
    }

    //刷新数据的方法
    public void freshData(){
        resultList = getItemList();

        if (resultList.size()!=0){
            //获取数据映射
            listAdapter = getDataMap(resultList);
            //加载适配器
            adapter = new BookAdapter(result.this,listAdapter,index);
            listView.setAdapter(adapter);
            System.out.println("刷新成功");
        }else {
            System.out.println("刷新失败");
        }
    }

    //设置按钮点击事件
    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.res_ly_btn_back){
            finish();
        }
    }

    //设置删除数据方法
    private void deleteData(){
        try {
            BookDao bookDao = new BookDao(this);
            SQLiteDatabase db = bookDao.getWritableDatabase();
            db.delete(BookDao.TAB_NAME,BookBean.FILED_ID +"=?",new String[]{idData});
            Toast.makeText(this,"删除成功!!!",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this,"删除失败!!!",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        listAdapter.remove(positionData);
        adapter.notifyDataSetChanged();
    }

    /**
     * 自定义广播刷新 listView 自定义广播接收器
     */
    class RefreshReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //接收道广播的刷新动作 就刷新列表信息
            if (intent.getAction().equals("update")){
                System.out.println("接收到信息开始刷新");
                freshData();
            }
        }
    }
}