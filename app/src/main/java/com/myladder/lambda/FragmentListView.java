package com.myladder.lambda;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.myladder.lambda.adapter.ListViewCustomAdapter;

import java.util.ArrayList;

/**
 * Created by Manu on 12/2/2017.
 */

public class FragmentListView extends Fragment {
    private View rootView;
    private ListView listView;
    ListViewCustomAdapter adapter;
    private ArrayList<ItemBean> itemList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_listview, container, false);
        prepareArrayLits();
        listView = rootView.findViewById(R.id.listview);
        adapter = new ListViewCustomAdapter(getActivity(), itemList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent,  view,  position,  id) -> {
            Toast.makeText(getActivity(),itemList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
        });
        return rootView;
    }

    /* Method used to prepare the ArrayList,
    * Same way, you can also do looping and adding object into the ArrayList.
    */
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

}
