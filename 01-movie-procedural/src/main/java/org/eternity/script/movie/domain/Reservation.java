package org.eternity.script.movie.domain;

import lombok.*;
import org.eternity.script.shared.Money;

@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
@Getter @Setter
public class Reservation {
    private Long id;
    private Long customerId;
    private Long screeningId;
    private Integer audienceCount;
    private Money fee;

    public Reservation(Long customerId, Long screeningId, Integer audienceCount, Money fee) {
        this.customerId = customerId;
        this.screeningId = screeningId;
        this.audienceCount = audienceCount;
        this.fee = fee;
    }
}
