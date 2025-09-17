package org.eternity.oop.movie.domain;

public interface DiscountCondition {
    boolean isSatisfiedBy(Screening screening);
}
