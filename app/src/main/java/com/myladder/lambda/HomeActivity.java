package com.myladder.lambda;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class HomeActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        navigation(new FragmentHome());
     //   TextView tView = findViewById(R.id.tv);
      //  tView.setOnClickListener(v -> System.out.println("Testing Long Click"));
      //  tView.setOnClickListener(this::logError);
//        listView = new ListView(this);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
//        listView.setOnItemClickListener( (parent,view,position, id) ->  {
//
//        });
    }

    public void navigation(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.commit();
    }

    public void logError(View v) {
        Log.d("debug", "Button clicked");
    }
}
