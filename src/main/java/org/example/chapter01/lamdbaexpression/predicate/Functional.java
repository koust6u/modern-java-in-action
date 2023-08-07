package org.example.chapter01.lamdbaexpression.predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Functional {
    /**
     *  Predicate: 인자로 객체를 받아 boolean 반환
     *  Method: test
     */
    public static  <T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> results = new ArrayList<>();

        for (T t : list){
            if (p.test(t)){
                results.add(t);
            }
        }
        return results;
    }

    public static void main(String[] args) {
        Predicate<String> nonEmptyStringPredicate = (String s)  -> !s.isEmpty();
        List<String> listOfString = Arrays.asList(" ", "", "string", "korea");
        List<String> nonEmpty = filter(listOfString, nonEmptyStringPredicate);
    }
}
