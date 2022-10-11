package org.duze.duzekino.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.PropertySource;
import org.duze.duzekino.exception.MovieException;
import org.duze.duzekino.model.Movie;
import org.duze.duzekino.model.PG;
import org.duze.duzekino.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
@Slf4j
public final class MovieService {
    final MovieRepository movieRepo;

    public boolean inDatabase(@NonNull Movie movie) {
        Predicate<Movie> yearEquals = m -> m.getYear().equals(movie.getYear());
        Predicate<Movie> titleEquals = m -> m.getTitle().equalsIgnoreCase(movie.getTitle());
        return getMovies().stream().anyMatch(yearEquals.and(titleEquals));
    }

    public MovieException newException(String msg) {
        log.error(msg);
        return new MovieException(msg);
    }

    public List<Movie> getMovies() {
        return movieRepo.findAll();
    }

    public Movie addMovie(@NonNull Movie movie) throws MovieException {
        if (inDatabase(movie)) {
            throw newException("Movie Already in Database");
        }
        log.info("Adding %s to Database".formatted(movie));
        movieRepo.save(movie);
        return movie;
    }

    public Optional<Movie> findMovieById(long id) {
        return movieRepo.findById(id);
    }

    public Movie updateMovie(@NonNull Movie oldM, @NonNull Movie newM) {
        var oldInfo = oldM.toString();
        oldM.setDescription(newM.getDescription());
        oldM.setTitle(newM.getTitle());
        oldM.setLength(newM.getLength());
        oldM.setYear(newM.getYear());
        oldM.setRating(newM.getRating());
        movieRepo.save(oldM);
        log.info("Updated Movie from %s to %s".formatted(oldInfo, oldM));
        return oldM;
    }

    public void deleteMovie(@NonNull Movie movie) {
        log.info("removing %s".formatted(movie));
        movieRepo.delete(movie);
    }
}
