package org.eternity.script.movie.persistence.memory;

import org.eternity.script.movie.domain.Screening;
import org.eternity.script.movie.persistence.ScreeningDAO;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class ScreeningMemoryDAO extends InMemoryDAO<Screening> implements ScreeningDAO {
    public ScreeningMemoryDAO() {
        add(new Screening(1L, 1, LocalDateTime.of(2024, 12, 11, 18, 0)));
    }

    @Override
    public Screening selectScreening(Long id) {
        return getOne(screening -> screening.getId().equals(id));
    }
}
