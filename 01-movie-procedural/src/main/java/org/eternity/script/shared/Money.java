package org.eternity.script.shared;

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@NoArgsConstructor
@ToString
public class Money {
    public static final Money ZERO = Money.won(0);

    public static Money won(long amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    private BigDecimal amount;

    private Money(BigDecimal amount) {
        this.amount = amount;
    }

    public Long longValue() {
        return this.amount.longValue();
    }

    public Money plus(Money other) {
        return new Money(amount.add(other.amount));
    }

    public Money minus(Money other) {
        return new Money(amount.subtract(other.amount));
    }

    public Money divide(long divisor) {
        return new Money(amount.divide(BigDecimal.valueOf(divisor), 0, RoundingMode.HALF_EVEN));
    }

    public Money divide(long divisor, RoundingMode rounding) {
        return new Money(amount.divide(BigDecimal.valueOf(divisor), 0, rounding));
    }

    public Money times(double times) {
        return new Money(amount.multiply(BigDecimal.valueOf(times)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount.compareTo(money.amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }
}
