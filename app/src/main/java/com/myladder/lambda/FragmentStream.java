package com.myladder.lambda;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Manu on 12/2/2017.
 */

public class FragmentStream extends Fragment {

    private View rootView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_home, container, false);


        return rootView;
    }

    //A Simple Example of Java Stream Filter()
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void simpleFilter() {
        List<String> names = Arrays.asList("Melisandre", "Sansa", "Jon", "Daenerys", "Joffery");
        //Creating the stream of all names
        Stream<String> allNames = names.stream();
        //Creating another stream by filtering long names using filter()
        Stream<String> longNames = allNames.filter(str -> str.length() > 6);
        //displaying the long names
        longNames.forEach(str -> System.out.print(str + " "));
    }

    //output
    //Melisandre Daenerys Joffery

    //Stream filter() and collect()

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void filterandCollect() {
        List<String> names = Arrays.asList("Melisandre", "Sansa", "Jon", "Daenerys", "Joffery");

        List<String> longnames = names.stream()    // converting the list to stream
                .filter(str -> str.length() > 6)   // filter the stream to create a new stream
                .collect(Collectors.toList());  // collect the final stream and convert it to a List

        longnames.forEach(System.out::println);
    }

    //outout
//    Melisandre
//    Daenerys
//    Joffery

    //Stream filter() with multiple conditions

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void filterandMultiple() {
        List<String> names = Arrays.asList("Melisandre", "Sansa", "Jon", "Daenerys", "Joffery");
        List<String> longnames = names.stream()
                .filter(str -> str.length() > 6 && str.length() < 8) //Multiple conditions
                .collect(Collectors.toList());
        longnames.forEach(System.out::println);

    }
    //output Joffery

    //Stream filter() and map() method in Java
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void filterandMap() {
        List<Integer> num = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> squares = num.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println(squares);
    }

    // output : [1, 4, 9, 16, 25, 36]

    //Map Filter by key
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void mapFilterbyKey() {
        Map<Integer, String> hmap = new HashMap<Integer, String>();
        hmap.put(11, "Apple");
        hmap.put(22, "Orange");
        hmap.put(33, "Kiwi");
        hmap.put(44, "Banana");

        Map<Integer, String> result = hmap.entrySet()
                .stream()
                .filter(map -> map.getKey().intValue() <= 22)
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));

        System.out.println("Result: " + result);
    }
    //Result: {22=Orange, 11=Apple}

    //Filter Map by Values
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void mapFilterbyValue() {
        Map<Integer, String> hmap = new HashMap<Integer, String>();
        hmap.put(11, "Apple");
        hmap.put(22, "Orange");
        hmap.put(33, "Kiwi");
        hmap.put(44, "Banana");

        Map<Integer, String> result = hmap.entrySet()
                .stream()
                .filter(map -> "Orange".equals(map.getValue()))
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));

        System.out.println("Result: " + result);
    }
    //Result: {22=Orange}

    // Filter Map by both Keys and Values
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void filterMapbyKeyandValue(){
        Map<Integer, String> hmap = new HashMap<Integer, String>();
        hmap.put(1, "ABC");
        hmap.put(2, "XCB");
        hmap.put(3, "ABB");
        hmap.put(4, "ZIO");

        Map<Integer, String> result = hmap.entrySet()
                .stream()
                .filter(p -> p.getKey().intValue() <= 2) //filter by key
                .filter(map -> map.getValue().startsWith("A")) //filter by value
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));

        System.out.println("Result: " + result);
    }

    //Learn more from this link
   // https://beginnersbook.com/2017/10/java-8-filter-null-values-from-a-stream/

}


