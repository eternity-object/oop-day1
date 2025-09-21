package org.eternity.oop;

import org.eternity.oop.movie.domain.*;
import org.eternity.oop.shared.Money;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static java.time.DayOfWeek.WEDNESDAY;

public class MovieApplication {

	public static void main(String[] args) {
		Screening screening = new Screening(
			new Movie(Money.won(10000L),
					new AmountDiscountPolicy(
							Money.won(1000L),
							List.of(
								new SequenceCondition(1),
								new PeriodCondition(WEDNESDAY, LocalTime.of(9, 0), LocalTime.of(11, 30))))),
			1,
			LocalDateTime.of(2024, 12, 11, 18, 0));
		Reservation reservation = screening.reserve(new Customer("사용자"), 2);

		System.out.println("요금: " + reservation.getFee());
	}
}
