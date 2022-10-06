package org.duze.duzekino.repository;

import org.duze.duzekino.model.Showing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowingRepository extends JpaRepository<Showing, Long> {

    @Query("SELECT s FROM Showing s WHERE s.movie.movieId = ?1")
    Optional<Showing> findShowingByMovieId(Long movieId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("DELETE FROM Showing s WHERE s.movie.movieId = ?1")
    void deleteShowingByMovieId(Long movieId);

}
