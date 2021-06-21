package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class listview extends AppCompatActivity {

    //将 数据 展示到 ListView列表当中
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        //获取ListView组件
        ListView listView = findViewById(R.id.listView1);
        //设置列表顶部 的图片的样式 这里需要的是图片的视图对象
        listView.addHeaderView(line());
        //设置列表底部 的图片的样式
        listView.addFooterView(line());
        //创建一个适配器 为ListView 组件设置数据
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this
                , R.array.ctype                               //获取arrays.xml文件中的数据
                , android.R.layout.simple_list_item_checked);//设置列表的样式
        //将适配器跟 ListView关联
        listView.setAdapter(adapter);
        //如果想要列表视图 组件每个选项点击以后有反应的话 就可以设置点击事件 设置监听每一列的一个监听器
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            //parent 适配器中适配的数据  view 这个列表视图的组件 position被选中的位置
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //当某一列被点击以后就会触发这个监听器
                //获取那个被选中的列的数据
                String result = parent.getItemAtPosition(position).toString();
                //我们可以获取 被选中的选项的值 显示到提示框这里
                Toast.makeText(listview.this,result,Toast.LENGTH_SHORT).show();
                //这里也可以写点了哪个一个选项就跳转到哪个选项的页面去...
            }
        });
    }

    //定义一个方法 用来获取图片 然后放回这个图片的视图对象
    private View line(){
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.flower01);
        return imageView;
    }
}