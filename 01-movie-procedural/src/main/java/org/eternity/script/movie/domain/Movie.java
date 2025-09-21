package org.eternity.script.movie.domain;

import lombok.*;
import org.eternity.script.shared.Money;

@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
@Getter @Setter
public class Movie {
	private Long id;
	private Long policyId;
	private String title;
	private Integer runningTime;
	private Money fee;

	public Movie(Long policyId, String title, Integer runningTime, Money fee) {
		this.policyId = policyId;
		this.title = title;
		this.runningTime = runningTime;
		this.fee = fee;
	}
}
