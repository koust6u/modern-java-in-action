package org.example.chapter01.lamdbaexpression.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Functional {
    /**
     * Function: 형색 객체 T 를 R로 반환한다.
     * Method: apply
     **/
    public static <T, R> List<R> map(List<T> list, Function<T, R> f){
        List<R> result = new ArrayList<>();
        for (T t: list){
            result.add(f.apply(t));
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> map = map(Arrays.asList("lambdas", "in", "action"), (String s) -> s.length());
        System.out.println(map);
    }
}
