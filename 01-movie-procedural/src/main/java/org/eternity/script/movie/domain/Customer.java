package org.eternity.script.movie.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Customer {
    private Long id;
    private String name;

    public Customer(String name) {
        this(null, name);
    }
}
