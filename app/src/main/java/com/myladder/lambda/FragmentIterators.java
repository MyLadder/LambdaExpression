package com.myladder.lambda;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Created by Manu on 12/2/2017.
 */

public class FragmentIterators extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);


        return rootView;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void arraySimple(){
        /* prints out in the console all elements of the list using the for each functionality */
        Arrays.asList( "element", "other", "another one" ).forEach( e -> System.out.println( e ) );
    }




    @RequiresApi(api = Build.VERSION_CODES.N)
    private void comparisonString(){
        /* sorts all elements in a collection using a lambda expression as comparator */
        List names = Arrays.asList( "prado", "gugenheim", "reina sofia", "louvre" );
        Collections.sort( names, (String a, String b ) -> b.compareTo( a ) );
        names.forEach( e -> System.out.println( e ) );
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void simpleList() {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        //Old way:
        for (Integer n : list) {
            System.out.println(n);
        }
        //New way:
        list.forEach(n -> System.out.println(n));
        //or we can use :: double colon operator in Java 8
        list.forEach(System.out::println);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void simpeListIteration() {
        List<String> list = new ArrayList<>();
        list.add("Rick");
        list.add("Negan");
        list.add("Daryl");
        list.add("Glenn");
        list.add("Carl");

        /* Iterate without using Lambda
		 Iterator iterator = list.iterator();
		 while (iterator.hasNext()) {
			System.out.println(iterator.next());
		 }
		*/
        list.forEach(
                // lambda expression
                (names) -> System.out.println(names)
        );
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void simpleMapIteration(){
        Map<String, Integer> prices = new HashMap<>();
        prices.put("Apple", 50);
        prices.put("Orange", 20);
        prices.put("Banana", 10);
        prices.put("Grapes", 40);
        prices.put("Papaya", 50);

		/* Iterate without using Lambda
		   for (Map.Entry<String, Integer> entry : prices.entrySet()) {
		   System.out.println("Fruit: " + entry.getKey() + ", Price: " + entry.getValue());
		   }
		*/

        prices.forEach((k,v)->System.out.println("Fruit: " + k + ", Price: " + v));
    }


    private void threadinitMethod() {
        //Old way:
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from thread");
            }
        }).start();

//New way:
        new Thread(
                () -> System.out.println("Hello from thread")
        ).start();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void predicateLogics() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        System.out.println("Print all numbers:");
        evaluate(list, (n) -> true);

        System.out.println("Print no numbers:");
        evaluate(list, (n) -> false);

        System.out.println("Print even numbers:");
        evaluate(list, (n) -> n % 2 == 0);

        System.out.println("Print odd numbers:");
        evaluate(list, (n) -> n % 2 == 1);

        System.out.println("Print numbers greater than 5:");
        evaluate(list, (n) -> n > 5);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void evaluate(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer n : list) {
            if (predicate.test(n)) {
                System.out.println(n + " ");
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void streamSquareMethods() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        //Old way:
        for (Integer n : list) {
            int x = n * n;
            System.out.println(x);
        }

        //New way:
        list.stream().map((x) -> x * x).forEach(System.out::println);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void streamSumOfSquareMethods() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        //Old way:
        int sum = 0;
        for (Integer n : list) {
            int x = n * n;
            sum = sum + x;
        }
        System.out.println(sum);

       //New way:
        int sum1 = list.stream().map(x -> x * x).reduce((x, y) -> x + y).get();
        System.out.println(sum1);
    }


}

