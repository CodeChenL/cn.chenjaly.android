package com.example.myapplication.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.sqlite.Dao.BookBean;
import com.example.myapplication.sqlite.Dao.BookDao;

public class update extends AppCompatActivity implements View.OnClickListener {

    private EditText etBookName,etWriter,etPress,etPrice;

    private Button btnOk,btnCancel;

    private String strId;//需要查询的id

    private void initListener(){
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    private void initView(){
        etBookName = findViewById(R.id.upd_ite_et_bookname);
        etWriter =  findViewById(R.id.upd_iem_et_writer);
        etPress = findViewById(R.id.upd_iem_et_press);
        etPrice = findViewById(R.id.upd_iem_et_price);

        btnOk = findViewById(R.id.upd_btn_ok);
        btnCancel =findViewById(R.id.upd_btn_cancel);

        //获取图书id 书名 作者出版社
        Intent intent = getIntent();
        //因为放进去是通过名称来存放的所以拿出来的时候 也是要用对应的名称来获取
        Bundle bundle = intent.getBundleExtra("bundle");
        strId = bundle.getString(BookBean.FILED_ID);
        //默认显示原来的数据
        etBookName.setText(bundle.getString(BookBean.FILED_BOOKNAME));
        etWriter.setText(bundle.getString(BookBean.FILED_WRITER));
        etPress.setText(bundle.getString(BookBean.FILED_PRESS));
        etPrice.setText(bundle.getString(BookBean.FILED_PRICE));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);
        initView();
        initListener();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.upd_btn_ok:
                //获取文字
                String strBookName = etBookName.getText().toString();
                String strWriter = etWriter.getText().toString();
                String strPress = etPress.getText().toString();
                String strPrice = etPrice.getText().toString();

                try {
                    BookDao dao = new BookDao(update.this);
                    SQLiteDatabase db = dao.getWritableDatabase();

                    //ContentValues用来存放需要修改的参数
                    ContentValues values = new ContentValues();
                    values.put(BookBean.FILED_BOOKNAME,strBookName);
                    values.put(BookBean.FILED_WRITER,strWriter);
                    values.put(BookBean.FILED_PRESS,strPress);
                    values.put(BookBean.FILED_PRICE,strPrice);

                    db.update(BookDao.TAB_NAME,values,"_id=?",new String[]{strId});
                    Toast.makeText(update.this,"修改成功",Toast.LENGTH_SHORT).show();
                    //发送广播刷新通知
                    Intent intent = new Intent();
                    intent.setAction("update");
                    update.this.sendBroadcast(intent);
                    System.out.println("发送广播");
                    finish();
                }catch (Exception e){
                    Toast.makeText(update.this,"修改失败",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                break;
            case R.id.upd_btn_cancel:
                finish();
                break;
            default:
                break;


        }
    }
}