package org.example.chapter01.lamdbaexpression.consumer;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Functional {
    /**
     * Consumer: 객체를 받아와 void 반환
     * Method: accept
     */
    public static <T> void forEach(List<T> list, Consumer<T> c){
        for (T t: list){
            c.accept(t);
        }
    }

    public static void main(String[] args) {
        forEach(Arrays.asList(1,2,3,4,5), (Integer i)-> System.out.println(i));
    }
}

