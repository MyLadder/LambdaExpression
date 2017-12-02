package com.myladder.lambda;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myladder.lambda.model.Country;
import com.myladder.lambda.model.Hobbie;
import com.myladder.lambda.model.Language;
import com.myladder.lambda.model.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by Manu on 12/2/2017.
 */

public class FragmentPredicators extends Fragment {

    private View rootView;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
       //old way
        List<Person> persons = populatePersons();
        for( Person p : persons )
        {
            showSpanish( p );
            showEnglishSpeakers( p );
            showPeopleThatLikeDancing( p );
            showAmericansThatLikeSports( p );
        }

        // new way using Lambda

        // print all persons using lambdas
        persons.forEach( ( s ) -> {
            System.out.println( s );
        } );

        // using an instantiated predicate
        Predicate<Person> spanish = p -> {
            return Country.SPAIN.equals( p.getCountry() );
        };
        persons.stream().filter( spanish ).forEach( ( person ) -> {
            System.out.println( person );
        } );


        // using Lambdas directly for filtering the spanish ones
        persons.stream().filter( person -> {
            return Country.SPAIN.equals( person.getCountry() );
        } ).forEach( ( eachPerson ) -> {
            System.out.println( eachPerson );
        } );

        // using Lambdas directly for filtering the chinesse ones that love to eat
        persons.stream().filter( person -> {
            return Country.CHINA.equals( person.getCountry() ) && Hobbie.EAT.equals( person.getFavouriteAction() );
        } ).forEach( ( eachPerson ) -> {
            System.out.println( eachPerson );
        } );

        Predicate<Person> germans = person -> {
            return Country.GERMANY.equals( person.getCountry() );
        };

        Predicate<Person> dancers = person -> {
            return Hobbie.DANCE.equals( person.getFavouriteAction() );
        };

        // combining different predicates is also possible
        persons.stream().filter( germans.and( dancers ) ).forEach( ( eachPerson ) -> {
            System.out.println( eachPerson );
        } );

        // using streams and filters is possible to get statistics directly like number of Persons
        // based of different criteria
        persons.stream().filter( germans.and( dancers ) ).count();

        // oldest person
        System.out.println( "oldest one: "
                + persons.stream().filter( germans.and( dancers ) ).max( ( p1, p2 ) -> p1.getAge() - p2.getAge() ) );

        return rootView;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void easyImplementation(){

        /* functions with two input parametes and one output can be implemented easily using lambdas */
        BiFunction<Integer, Integer, Integer> addition = (x, y ) -> x + y;
        System.out.println( "calling addition of 2 and 3 resulting: " + addition.apply( 2, 3 ) );

        /*
         * Runnable is a functional interface, we have to implement the method run or provide a
         * function for it in Lambda style
         */
        Runnable r = ( ) -> System.out.println( "calling Runnable: run run run" );
        Thread t = new Thread( r );
        t.start();

        /* prints out in the console all elements of the list using the for each functionality */
        Arrays.asList( "element", "other", "another one" ).forEach( e -> System.out.println( e ) );

        /*
         * similar behaviour by passing a consumer instead of passing directly the lambda
         */
        Consumer<String> printString = (String s ) -> {
            System.out.println( s );
        };
        Arrays.asList( "1", "2", "3" ).forEach( printString );

        /* sorts all elements in a collection using a lambda expression as comparator */
        List<String> names = Arrays.asList( "Prado", "Gugenheim", "Reina Sofia", "Louvre" );
        Collections.sort( names, (String a, String b ) -> b.compareTo( a ) );
        names.forEach( e -> System.out.println( e ) );

        /*
         * we create a predicate that is going to be used to filter the elements of the list that
         * are null, for this we use the stream() method of the new Streams API and the :: operator
         * to access methods of functional interfaces
         */
        Predicate<String> isNotNull = ( String s ) -> {
            return s != null;
        };
        Arrays.asList( "1", "2", "3", null ).stream().filter( isNotNull ).forEach( System.out::println );

    }


    private static void showAmericansThatLikeSports( Person p )
    {
        if( p != null && Country.USA.equals( p.getCountry() ) && Hobbie.SPORTS.equals( p.getFavouriteAction() ) )
        {
            System.out.println( p );
        }
    }

    private static void showPeopleThatLikeDancing( Person p )
    {
        if( p != null && Hobbie.DANCE.equals( p.getFavouriteAction() ) )
        {
            System.out.println( p );
        }

    }

    private static void showEnglishSpeakers( Person p )
    {
        if( p != null && Language.ENGLISH.equals( p.getLanguage() ) )
        {
            System.out.println( p );
        }

    }

    private static void showSpanish( Person p )
    {
        if( p != null && Country.SPAIN.equals( p.getCountry() ) )
        {
            System.out.println( p );
        }

    }

    private static List<Person> populatePersons()
    {
        List<Person> persons = new ArrayList<Person>();
        persons.add( new Person( "Dani", 22, Country.SPAIN, Language.SPANISH, Hobbie.DRINK ) );
        persons.add( new Person( "Joe", 24, Country.USA, Language.ENGLISH, Hobbie.DANCE ) );
        persons.add( new Person( "Matt", 26, Country.USA, Language.ENGLISH, Hobbie.TALK ) );
        persons.add( new Person( "Jolie", 21, Country.AUSTRALIA, Language.ENGLISH, Hobbie.TRAVEL ) );
        persons.add( new Person( "Juan", 25, Country.SPAIN, Language.SPANISH, Hobbie.SPORTS ) );
        persons.add( new Person( "Francisca", 35, Country.AUSTRALIA, Language.ENGLISH, Hobbie.TALK ) );
        persons.add( new Person( "Davide", 18, Country.GERMANY, Language.GERMAN, Hobbie.DANCE ) );
        persons.add( new Person( "David", 50, Country.AUSTRALIA, Language.ENGLISH, Hobbie.SPORTS ) );
        persons.add( new Person( "Tercia", 65, Country.PORTUGAL, Language.PORTUGUESE, Hobbie.DANCE ) );
        persons.add( new Person( "Steven", 15, Country.AUSTRALIA, Language.ENGLISH, Hobbie.SPORTS ) );
        persons.add( new Person( "Jorge", 22, Country.AUSTRALIA, Language.ENGLISH, Hobbie.PLAY ) );
        persons.add( new Person( "Adam", 22, Country.USA, Language.ENGLISH, Hobbie.DANCE ) );
        persons.add( new Person( "Alexei", 22, Country.AUSTRALIA, Language.ENGLISH, Hobbie.TALK ) );
        persons.add( new Person( "Igor", 22, Country.AUSTRALIA, Language.ENGLISH, Hobbie.DRINK ) );
        persons.add( new Person( "Pan", 41, Country.CHINA, Language.CHINESSE, Hobbie.DANCE ) );
        persons.add( new Person( "Jorge", 22, Country.SPAIN, Language.SPANISH, Hobbie.PLAY ) );
        persons.add( new Person( "Siu", 22, Country.CHINA, Language.CHINESSE, Hobbie.DRINK ) );
        persons.add( new Person( "Yan", 29, Country.CHINA, Language.CHINESSE, Hobbie.DANCE ) );
        persons.add( new Person( "Rolf", 22, Country.GERMANY, Language.GERMAN, Hobbie.DRINK ) );
        persons.add( new Person( "Helmut", 22, Country.GERMANY, Language.GERMAN, Hobbie.DRINK ) );
        persons.add( new Person( "Maria", 16, Country.AUSTRALIA, Language.ENGLISH, Hobbie.DANCE ) );
        persons.add( new Person( "Jorge", 32, Country.SPAIN, Language.SPANISH, Hobbie.PLAY ) );
        persons.add( new Person( "Inga", 22, Country.GERMANY, Language.GERMAN, Hobbie.DRINK ) );
        persons.add( new Person( "Oscar", 80, Country.SPAIN, Language.SPANISH, Hobbie.DANCE ) );
        persons.add( new Person( "Jorge", 78, Country.SPAIN, Language.SPANISH, Hobbie.PLAY ) );
        persons.add( new Person( "Dimitris", 22, Country.GREECE, Language.GREEK, Hobbie.TRAVEL ) );
        persons.add( new Person( "Socatres", 30, Country.GREECE, Language.GREEK, Hobbie.TALK ) );
        return persons;
    }
}


















//https://examples.javacodegeeks.com/java-basics/lambdas/java-8-lambda-expressions-tutorial/
//https://howtodoinjava.com/java-8/complete-lambda-expressions-tutorial-in-java/
//https://beginnersbook.com/2017/10/method-references-in-java-8/
//https://github.com/mikaelhg/lambda-demo/blob/master/src/main/java/lambda/LambdaDemo.java
//https://github.com/neomatrix369/LambdaExamples/tree/master/src/main/java
//https://github.com/java8-course/lambda/blob/master/src/test/java/lambda/part1/example/Lambdas01.java
//https://github.com/shekhargulati/java8-the-missing-tutorial/blob/master/code/src/main/java/com/shekhargulati/java8_tutorial/ch02/Example1_Lambda.java
