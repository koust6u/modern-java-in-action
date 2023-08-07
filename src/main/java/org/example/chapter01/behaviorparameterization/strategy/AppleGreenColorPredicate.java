package org.example.chapter01.behaviorparameterization.strategy;

import org.example.chapter01.behaviorparameterization.Apple;

public class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return Apple.Color.GREEN.equals(apple.getColor());
    }
}
