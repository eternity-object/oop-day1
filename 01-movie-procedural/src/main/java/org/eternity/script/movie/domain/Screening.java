package org.eternity.script.movie.domain;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
@Getter @Setter
public class Screening {
    private Long id;
    private Long movieId;

    private Integer sequence;
    private LocalDateTime screeningTime;

    public Screening(Long movieId, Integer sequence, LocalDateTime screeningTime) {
        this.movieId = movieId;
        this.sequence = sequence;
        this.screeningTime = screeningTime;
    }
}
