package org.example.chapter01.behaviorparameterization.strategy;

import org.example.chapter01.behaviorparameterization.Apple;

public class AppleHeavyWeightPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}
