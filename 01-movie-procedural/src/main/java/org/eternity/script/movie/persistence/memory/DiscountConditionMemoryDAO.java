package org.eternity.script.movie.persistence.memory;

import org.eternity.script.movie.domain.DiscountCondition;
import org.eternity.script.movie.persistence.DiscountConditionDAO;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

import static java.time.DayOfWeek.WEDNESDAY;
import static org.eternity.script.movie.domain.DiscountCondition.ConditionType.PERIOD_CONDITION;
import static org.eternity.script.movie.domain.DiscountCondition.ConditionType.SEQUENCE_CONDITION;

@Repository
public class DiscountConditionMemoryDAO extends InMemoryDAO<DiscountCondition> implements DiscountConditionDAO {
    public DiscountConditionMemoryDAO() {
        add(new DiscountCondition(1L,
                SEQUENCE_CONDITION, null, null, null, 1));
        add(new DiscountCondition(1L,
                PERIOD_CONDITION, WEDNESDAY, LocalTime.of(9, 0), LocalTime.of(11, 30), null));
    }

    @Override
    public List<DiscountCondition> selectDiscountConditions(Long policyId) {
        return getMany(condition -> condition.getPolicyId().equals(policyId));
    }
}
