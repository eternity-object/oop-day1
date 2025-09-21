package org.eternity.oop.movie.domain;

import org.eternity.oop.shared.Money;

import java.util.List;

public abstract class DiscountPolicy {
    private List<DiscountCondition> conditions;

    protected DiscountPolicy(List<DiscountCondition> conditions) {
        this.conditions = conditions;
    }

    public Money calculateDiscount(Screening screening) {
        for (DiscountCondition each : conditions) {
            if (each.isSatisfiedBy(screening)) {
                return getDiscountAmount(screening);
            }
        }

        return Money.ZERO;
    }

    abstract protected Money getDiscountAmount(Screening screening);
}
