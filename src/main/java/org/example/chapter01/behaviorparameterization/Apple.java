package org.example.chapter01.behaviorparameterization;

import org.example.chapter01.behaviorparameterization.strategy.ApplePredicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Predicate;

/**
 * Scenario: 사과 농장 주인의 요구 사항에 맞게 사과 판별기 만들어 주기
 */
public class Apple {

    Color color;
    int weight;

    boolean flag;
    public Color getColor() {
        return color;
    }

    public Apple(Color color,int weight){
        this.color = color;
        this.weight = weight;
    }

    /**
     * requirement: 초록 사과만을 골라내서 반환하라.
     * Version.01
     */
    public static List<Apple> filterGreenApples(List<Apple> inventory){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if(Color.GREEN.equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * requirement: filterGreenApples 코드를 반복 사용x, filterRedApples 구현
     * method: 색을 parameterization -> 파라미터에 색 추가
     * problem: 소공 측면에서 DRY(코드 중복) 원칙 위반
     * Version.02
     */
    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor().equals(color)){
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * requirement: 이번에는 색깔아 아닌 무게로 필터링 해줘!
     * problem: DRY 원칙 위반에도 불구, 그대로 파라미터화 해서 넘겨줌
     * version.03
     */
    public static List<Apple> filterApples(List<Apple> inventory, Color color, int weight, boolean flag){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if((flag && apple.getColor().equals(color)) ||
                    (!flag && apple.getWeight() > weight)){
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * requirement: strategy pattern 을 이용해서  runtime에 분류 알고리즘을 선택해서 유연성 높이자.
     * advantage: ocp 원칙 충족
     * version.04
     */
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p){
        List<Apple> result = new ArrayList<>();
        for(Apple apple: inventory){
            if (p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * requirement: 그래도 거추장스러운 느낌을 지울 수 없다. 개선해보자(복잡한 과정 간소화)
     * method: 익명 클래스를 사용하자.
     * problem: 가독성이 너무 떨어짐.
     * version.05
     * notice: 메인 함수 참고
     */

    /**
     * requirement: 코드 가독성을 높여라.
     * method: lambda expression
     * version.06
     * notice: 메인 함수 참고
     */

    /**
     * requirement: 리스트 형식으로 추상화 해라.
     * method: 형식 파라미터 사용
     * version.07
     */
    public static <T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> result = new ArrayList<>();
        for (T e : list){
            if (p.test(e)){
                result.add(e);
            }
        }
        return result;
    }



    public Integer getWeight() {
        return weight;
    }

    public enum Color{
        RED, GREEN;
    }


    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(Color.RED, 100), new Apple(Color.GREEN, 150),
                new Apple(Color.GREEN, 200));
        List<Apple> green = filterGreenApples(inventory);
        List<Apple> red = filterApplesByColor(inventory, Color.RED);

        //AppleClassification Version.05
        List<Apple> redApple = filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return Color.RED.equals(apple.getColor());
            }
        });

        //AppleClassification Version.06
        filterApples(inventory, (Apple apple) -> Color.RED.equals(apple.getColor()));

        //Classification Version.07
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
        List<Integer> filter = filter(numbers, (Integer i) -> i % 2 == 0);

        //application.01: Comparator sort
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });
        inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));

        //application.02: Runnable 코드 블럭 실행
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world");
            }
        });

        Thread t2 = new Thread(() -> System.out.println("Hello world"));

        //application.03: GUI event processor
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> threadName = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return Thread.currentThread().getName();
            }
        });
        Future<String> threadName2 = executorService.submit(() -> Thread.currentThread().getName());

    }
}
