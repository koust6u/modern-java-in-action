package org.example.chapter01.lamdbaexpression.intpredicate;

import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class Functional {
    /**
     * IntPredicate: 메모리 소모를 유발하는 Wrapper type의 오토박싱 피할수 있게 도와줌
     * 유사 기능: DoublePredicate, LongPredicate, IntConsumer, LongBinaryOperator, IntFunction
     */
    public static void main(String[] args) {
        IntPredicate evenNumbers = (int i) -> i %2  == 0;
        evenNumbers.test(1000); //박싱 없음

        Predicate<Integer> oddNumber = (Integer i) -> i%2 != 0;
        oddNumber.test(1000);

    }
}
