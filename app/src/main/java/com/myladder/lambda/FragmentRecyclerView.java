package com.myladder.lambda;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myladder.lambda.adapter.RecyclerViewCustomAdapter;

import java.util.ArrayList;

/**
 * Created by Manu on 12/2/2017.
 */

public class FragmentRecyclerView extends Fragment implements RecyclerViewCustomAdapter.OnitemClickSelectListener {
    private View rootView;
    private RecyclerView recyclerView;
    private ArrayList<ItemBean> itemList;
    RecyclerViewCustomAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        prepareArrayLits();
        recyclerView = rootView.findViewById(R.id.recyclerView);
        mAdapter = new RecyclerViewCustomAdapter(getActivity(),itemList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        return rootView;
    }
    public void prepareArrayLits() {
        itemList = new ArrayList<>();
        AddObjectToList(R.drawable.ic_launcher_background, "One", "one desc");
        AddObjectToList(R.drawable.ic_launcher_background, "Two", "Delete desc");
        AddObjectToList(R.drawable.ic_launcher_background, "Three", "Down desc");
        AddObjectToList(R.drawable.ic_launcher_background, "Four", "Information desc");
        AddObjectToList(R.drawable.ic_launcher_background, "Five", "Help desc");
        AddObjectToList(R.drawable.ic_launcher_background, "Six", "Download desc");
        AddObjectToList(R.drawable.ic_launcher_background, "Seven", "Mail desc");
        AddObjectToList(R.drawable.ic_launcher_background, "Eight", "Search desc");
        AddObjectToList(R.drawable.ic_launcher_background, "Nine", "Settings desc");
        AddObjectToList(R.drawable.ic_launcher_background, "Ten", "Settings desc");
    }

    // Add one item into the Array List
    public void AddObjectToList(int image, String title, String desc) {
        ItemBean bean = new ItemBean();
        bean.setDescription(desc);
        bean.setImage(image);
        bean.setTitle(title);
        itemList.add(bean);
    }

    @Override
    public void onItemClick(int position) {

    }
}
