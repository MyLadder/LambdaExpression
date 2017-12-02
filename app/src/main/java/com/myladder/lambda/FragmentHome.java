package com.myladder.lambda;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Manu on 12/2/2017.
 */

public class FragmentHome extends Fragment {

    private View rootView;
    private Button button1, button2, button3;
    private HomeActivity homeActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        homeActivity = (HomeActivity) getActivity();
        button1 = rootView.findViewById(R.id.button1);
        button2 = rootView.findViewById(R.id.button2);
        button3 = rootView.findViewById(R.id.button3);

        button1.setOnClickListener(v -> {
            homeActivity.navigation(new FragmentListView());
        });
        button2.setOnClickListener(v -> {
            homeActivity.navigation(new FragmentRecyclerView());
        });
        button3.setOnClickListener(v -> {
            homeActivity.navigation(new FragmentIterators());
        });


        return rootView;
    }
}
