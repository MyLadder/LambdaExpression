package com.myladder.lambda.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.myladder.lambda.ItemBean;
import com.myladder.lambda.R;

import java.util.List;

/**
 * Created by Manu on 12/2/2017.
 */

public class RecyclerViewCustomAdapter extends RecyclerView.Adapter<RecyclerViewCustomAdapter.CustomViewHolder> {

    private List<ItemBean> feedItemList;
    private Context mContext;
    OnitemClickSelectListener onitemClickSelectListener;

    public RecyclerViewCustomAdapter(Context context, List<ItemBean> feedItemList, OnitemClickSelectListener onitemClickSelectListener) {
        this.feedItemList = feedItemList;
        this.mContext = context;
        this.onitemClickSelectListener = onitemClickSelectListener;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.reccycler_row, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int position) {
        ItemBean feedItem = feedItemList.get(position);
        //Setting text view title
        customViewHolder.textView.setText(Html.fromHtml(feedItem.getTitle()));
        customViewHolder.cardView.setOnClickListener(v ->{
            onitemClickSelectListener.onItemClick(position);
        });

    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;
        protected TextView textView;
        protected CardView cardView;

        public CustomViewHolder(View view) {
            super(view);
            this.imageView =  view.findViewById(R.id.icon);
            this.textView =  view.findViewById(R.id.title);
            this.cardView = view.findViewById(R.id.cardview);
        }
    }

    public interface OnitemClickSelectListener {
        void onItemClick(int position);
    }
}
