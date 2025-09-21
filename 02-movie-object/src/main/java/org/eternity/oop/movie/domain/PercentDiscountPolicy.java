package org.eternity.oop.movie.domain;

import org.eternity.oop.shared.Money;

import java.util.List;

public class PercentDiscountPolicy extends DiscountPolicy {
    private double percent;

    protected PercentDiscountPolicy(double percent, List<DiscountCondition> conditions) {
        super(conditions);
        this.percent = percent;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return screening.getFixedFee().times(percent);
    }
}
