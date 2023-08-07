package org.example.chapter01.behaviorparameterization.strategy;

import org.example.chapter01.behaviorparameterization.Apple;

public interface ApplePredicate {
    boolean test(Apple apple);
}
