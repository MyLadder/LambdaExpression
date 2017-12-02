package com.myladder.lambda.adapter;

/**
 * Created by Manu on 12/2/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myladder.lambda.ItemBean;
import com.myladder.lambda.R;

import java.util.ArrayList;

public class ListViewCustomAdapter extends BaseAdapter{

    ArrayList<ItemBean> itemList;

    public Activity context;
    public LayoutInflater inflater;

    public ListViewCustomAdapter(Activity context,ArrayList<ItemBean> itemList) {
        super();

        this.context = context;
        this.itemList = itemList;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public static class ViewHolder
    {
        ImageView imgViewLogo;
        TextView txtViewTitle;
        TextView txtViewDescription;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        ViewHolder holder;
        if(convertView==null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.items_layout, null);

            holder.imgViewLogo =  convertView.findViewById(R.id.imgViewLogo);
            holder.txtViewTitle =  convertView.findViewById(R.id.title);
            holder.txtViewDescription = convertView.findViewById(R.id.descrition);

            convertView.setTag(holder);
        }
        else
            holder=(ViewHolder)convertView.getTag();


        holder.imgViewLogo.setImageResource(itemList.get(position).getImage());
        holder.txtViewTitle.setText(itemList.get(position).getTitle());
        holder.txtViewDescription.setText(itemList.get(position).getDescription());

        return convertView;
    }

}