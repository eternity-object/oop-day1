package org.eternity.script.movie.persistence.memory;

import org.eternity.script.movie.domain.Movie;
import org.eternity.script.movie.persistence.MovieDAO;
import org.eternity.script.shared.Money;
import org.springframework.stereotype.Repository;

@Repository
public class MovieMemoryDAO extends InMemoryDAO<Movie> implements MovieDAO {
    public MovieMemoryDAO() {
        add(new Movie(1L, "한산", 120, Money.won(10000L)));
    }

    @Override
    public Movie selectMovie(Long movieId) {
        return getOne(movie -> movie.getId().equals(movieId));
    }
}
