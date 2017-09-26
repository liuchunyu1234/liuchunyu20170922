package com.bawei.lcy.liuchunyu20170922;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;



/**
 * Created by LCY on 2017/9/22.
 */

public class CelaAdapter extends BaseAdapter{
    private List<CelaBean> beanList;
    private Context context;
    private LayoutInflater inflater;

    public CelaAdapter(List<CelaBean> beanList, Context context) {
        this.beanList = beanList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return beanList.size();
    }

    @Override
    public Object getItem(int position) {
        return beanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder hoder = new ViewHoder();
        if(convertView==null){
            convertView = inflater.inflate(R.layout.cela,null);
            hoder.image1 = (ImageView) convertView.findViewById(R.id.image1);
            hoder.tv = (TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(hoder);
        }else{
            hoder = (ViewHoder) convertView.getTag();
        }
        hoder.image1.setImageResource(beanList.get(position).getImage());
        hoder.tv.setText(beanList.get(position).getName());
        return convertView;
    }
    class ViewHoder{
        ImageView image1;
        TextView tv;
    }
}
