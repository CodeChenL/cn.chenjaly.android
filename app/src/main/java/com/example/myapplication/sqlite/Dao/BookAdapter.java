package com.example.myapplication.sqlite.Dao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.myapplication.R;
import org.w3c.dom.Text;

import java.util.List;
import java.util.Map;
//这个适配器类的作用 就是讲图书数据展示到页面listview工具
//适配器 就是起一个适配 转换的效果
public class BookAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;//视图容器
    private List<Map<String,Object>> listItem;
    private String index[];

    public final class ViewHolder{
        public TextView tvId,tvBookName,tvWriter,tvPress,tvPrice;
    }

    //使用构造器创建视图设置上下文
    public BookAdapter(Context context
            ,List<Map<String,Object>>list
            ,String [] index){
        this.index = index;
        this.context = context;
        //一个用于加载布局的系统服务，就是实例化与Layout XML文件对应的View对象
        //这里我们使用这个对象来完全地使用Java代码来编写Android页面布局
        inflater = LayoutInflater.from(context);
        this.listItem = list;
    }

    @Override
    public int getCount() {
        return listItem.size();
    }

    @Override
    public Object getItem(int position) {
        return listItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null){
            //ViewHolder通常出现在适配器里，为的是listview滚动的时候快速设置值，而不必每次都重新创建很多对象，从而提升性能
            holder = new ViewHolder();
            String strId = listItem.get(position).get(index[0])+"";
            String strBookName = listItem.get(position).get(index[1])+"";
            String strWriter = listItem.get(position).get(index[2])+"";
            String strPress = listItem.get(position).get(index[3])+"";
            String strPrice = listItem.get(position).get(index[4])+"";

            //获取文件视图
            convertView = inflater.inflate(R.layout.book_item,null);
            holder.tvId = convertView.findViewById(R.id.book_item_id);
            holder.tvBookName = convertView.findViewById(R.id.book_item_bookname);
            holder.tvWriter = convertView.findViewById(R.id.book_item_writer);
            holder.tvPrice = convertView.findViewById(R.id.book_item_price);
            holder.tvPress = convertView.findViewById(R.id.book_item_press);

            //设置字符串内容
            holder.tvId.setText(strId);
            holder.tvBookName.setText(strBookName);
            holder.tvWriter.setText(strWriter);
            holder.tvPress.setText(strPress);
            holder.tvPrice.setText(strPrice);

            //将控件设置到 converview中
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        return convertView;
    }
}

