package org.eternity.script.movie.persistence.memory;

import org.eternity.script.movie.domain.DiscountPolicy;
import org.eternity.script.movie.persistence.DiscountPolicyDAO;
import org.eternity.script.shared.Money;
import org.springframework.stereotype.Repository;

@Repository
public class DiscountPolicyMemoryDAO extends InMemoryDAO<DiscountPolicy> implements DiscountPolicyDAO {

    public DiscountPolicyMemoryDAO() {
        add(new DiscountPolicy(1L, DiscountPolicy.PolicyType.AMOUNT_POLICY, Money.won(1000L), null));
    }

    @Override
    public DiscountPolicy selectDiscountPolicy(Long movieId) {
        return getOne(policy -> policy.getMovieId().equals(movieId));
    }
}
