package org.eternity.script.movie.service;

import org.eternity.script.movie.domain.*;
import org.eternity.script.movie.persistence.*;
import org.eternity.script.shared.Money;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservationService {
    private CustomerDAO customerDAO;
    private ScreeningDAO screeningDAO;
    private MovieDAO movieDAO;
    private DiscountPolicyDAO discountPolicyDAO;
    private DiscountConditionDAO discountConditionDAO;
    private ReservationDAO reservationDAO;

    public ReservationService(CustomerDAO customerDAO,
                              ScreeningDAO screeningDAO,
                              MovieDAO movieDAO,
                              DiscountPolicyDAO discountPolicyDAO,
                              DiscountConditionDAO discountConditionDAO,
                              ReservationDAO reservationDAO) {
        this.customerDAO = customerDAO;
        this.screeningDAO = screeningDAO;
        this.movieDAO = movieDAO;
        this.discountConditionDAO = discountConditionDAO;
        this.discountPolicyDAO = discountPolicyDAO;
        this.reservationDAO = reservationDAO;
    }

    @Transactional
    public Reservation reserveScreening(Long customerId, Long screeningId, Integer audienceCount) {
        Customer customer = customerDAO.selectCustomer(customerId);
        Screening screening = screeningDAO.selectScreening(screeningId);
        Movie movie = movieDAO.selectMovie(screening.getMovieId());
        DiscountPolicy policy = discountPolicyDAO.selectDiscountPolicy(movie.getId());
        List<DiscountCondition> conditions = discountConditionDAO.selectDiscountConditions(policy.getId());

        DiscountCondition condition = findDiscountCondition(screening, conditions);

        Money fee;
        if (condition != null) {
            fee = movie.getFee().minus(calculateDiscountAmount(policy, movie));
        } else {
            fee = movie.getFee();
        }

        Reservation reservation = makeReservation(customer, screening, audienceCount, fee);

        reservationDAO.insert(reservation);

        return reservation;
    }

    private DiscountCondition findDiscountCondition(Screening screening, List<DiscountCondition> conditions) {
        for(DiscountCondition condition : conditions) {
            if (condition.isPeriodCondition()) {
                if (screening.getScreeningTime().getDayOfWeek().equals(condition.getDayOfWeek()) &&
                    screening.getScreeningTime().toLocalTime().compareTo(condition.getStartTime()) >= 0 &&
                    screening.getScreeningTime().toLocalTime().compareTo(condition.getEndTime()) <= 0) {
                    return condition;
                }
            } else {
                if (condition.getSequence().equals(screening.getSequence())) {
                    return condition;
                }
            }
        }

        return null;
    }

    private Money calculateDiscountAmount(DiscountPolicy policy, Movie movie) {
        if (policy.isAmountPolicy()) {
            return policy.getAmount();
        } else if (policy.isPercentPolicy()) {
            return movie.getFee().times(policy.getPercent());
        }

        return Money.ZERO;
    }

    private Reservation makeReservation(Customer customer, Screening screening, Integer audienceCount, Money amount) {
        return new Reservation(customer.getId(), screening.getId(), audienceCount, amount.times(audienceCount));
    }
}
