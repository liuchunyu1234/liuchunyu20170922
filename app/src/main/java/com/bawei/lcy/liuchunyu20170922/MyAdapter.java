package com.bawei.lcy.liuchunyu20170922;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by LCY on 2017/9/22.
 * 适配器：用来获得数据，对PullToReFreshListView进行优化
 */

public class MyAdapter extends BaseAdapter{
    private List<DataBean.ResultsBean> list;
    private Context context;
    private LayoutInflater inflater;

    public MyAdapter(List<DataBean.ResultsBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        int rs = 0;
        if(list.get(position).getImages()!=null){
            rs = 0;
        }else if(list.get(position).getImages()==null){
            rs = 1;
        }
        return rs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int rs = getItemViewType(position);
        ViewHoder1 hoder1;
        ViewHoder2 hoder2;
        if(rs==0){
            if(convertView==null){
                hoder1 = new ViewHoder1();
                convertView = inflater.inflate(R.layout.item1, null);
                hoder1.image = (ImageView) convertView.findViewById(R.id.image);
                hoder1.tv1 = (TextView) convertView.findViewById(R.id.tv1);
                convertView.setTag(hoder1);
            }else{
                hoder1 = (ViewHoder1) convertView.getTag();
            }
            ImageLoader.getInstance().displayImage(list.get(position).getImages().get(0),hoder1.image);
          //得到数据
            hoder1.tv1.setText(list.get(position).get_id()+"\n"+list.get(position).getCreatedAt()+"\n"+
                    list.get(position).getDesc()+"\n"+list.get(position).getPublishedAt()+"\n"+
                    list.get(position).getSource()+"\n"+list.get(position).getType()+"\n"+
                    list.get(position).getUrl()+"\n"+list.get(position).getWho());

        }else if(rs==1){
            if(convertView==null){
                hoder2 = new ViewHoder2();
                convertView = inflater.inflate(R.layout.item2, null);
                hoder2.tv2 = (TextView) convertView.findViewById(R.id.tv2);
                convertView.setTag(hoder2);
            }else{
                hoder2 = (ViewHoder2) convertView.getTag();
            }
            hoder2.tv2.setText(list.get(position).get_id()+"\n"+list.get(position).getCreatedAt()+"\n"+
                    list.get(position).getDesc()+"\n"+list.get(position).getPublishedAt()+"\n"+
                    list.get(position).getSource()+"\n"+list.get(position).getType()+"\n"+
                    list.get(position).getUrl()+"\n"+list.get(position).getWho());

        }
        return convertView;
    }

    class ViewHoder1{
        ImageView image;
        TextView tv1;
    }
    class ViewHoder2{
        TextView tv2;
    }
}
