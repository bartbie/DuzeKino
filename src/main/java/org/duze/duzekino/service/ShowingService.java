package org.duze.duzekino.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.duze.duzekino.exception.MovieException;
import org.duze.duzekino.exception.ShowingException;
import org.duze.duzekino.model.Movie;
import org.duze.duzekino.model.Showing;
import org.duze.duzekino.repository.ShowingRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public final class ShowingService {
    final ShowingRepository showingRepo;
    final MovieService movieService;

    public ShowingException newException(String msg) {
        log.error(msg);
        return new ShowingException(msg);
    }

    public boolean inDatabase(@NonNull Showing showing) {
        Predicate<Showing> movieEquals = s -> s.getMovie().equals(showing.getMovie());
        Predicate<Showing> theaterEquals = s -> s.getTheater().equals(showing.getTheater());
        return getShowings().stream().anyMatch(movieEquals.and(theaterEquals));
    }

    // READ

    public List<Showing> getShowings() {
        return showingRepo.findAll();
    }

    public Optional<Showing> findShowingById(long id) {
        return showingRepo.findById(id);
    }

    public Optional<Showing> findShowingByMovieId(@NonNull Long movieId) {
        return showingRepo.findShowingByMovieId(movieId);
    }

    // CREATE

    // singular
    public Showing addShowing(@NonNull Showing showing) throws ShowingException, MovieException {
        if (inDatabase(showing)) {
            throw newException("Showing already in Database");
        }
        if (!movieService.inDatabase(showing.getMovie())) {
            throw movieService.newException("Movie not added to Database!");
        }

        log.info("Adding %s to Database".formatted(showing));
        showingRepo.save(showing);
        return showing;
    }

    public Showing addShowingAndMovie(@NonNull Showing showing) throws ShowingException {
        try {
           addShowing(showing);
        } catch (MovieException e) {
            movieService.addMovie(showing.getMovie());
            addShowing(showing);
        }
        return showing;
    }

    // DELETE

    // for internal use only
    private void deleteShowingUnchecked(@NonNull Showing showing) {
        log.info("Removing %s".formatted(showing));
        showingRepo.delete(showing);
    }

    public void deleteShowing(@NonNull Showing showing) throws ShowingException {
        if (!inDatabase(showing)) {
            throw newException("Showing not in Database!");
        }
        deleteShowingUnchecked(showing);
    }

    public void deleteShowingByMovieId(long movieId) throws ShowingException {
        findShowingByMovieId(movieId)
                .ifPresentOrElse(
                        this::deleteShowingUnchecked,
                        () -> {
                            throw newException("No such Movie with id %d".formatted(movieId));
                        });
    }

    // UPDATE

    public Showing updateShowing(@NonNull Showing oldShowing, @NonNull Showing newShowing) throws MovieException {
        if (!movieService.inDatabase(newShowing.getMovie())) {
            throw movieService.newException("newer Movie not added to Database!");
        }
        var oldInfo = oldShowing.toString();
        oldShowing.setTime(newShowing.getTime());
        oldShowing.setTheater(newShowing.getTheater());
        oldShowing.setMovie(newShowing.getMovie());
        showingRepo.save(oldShowing);
        log.info("Updated Showing from %s to %s".formatted(oldInfo, oldShowing));
        return oldShowing;
    }

    public Showing updateShowingById(long id, @NonNull Showing newShowing) throws ShowingException {
        var oldShowing = findShowingById(id);
        if (oldShowing.isEmpty()) {
            throw newException("No such Showing with id %d".formatted(id));
        }
        return updateShowing(oldShowing.get(), newShowing);
    }

    public Showing updateShowingAndCreateMovie(@NonNull Showing oldShowing, @NonNull Showing newShowing) {
        try {
            updateShowing(oldShowing, newShowing);
        } catch (MovieException e) {
            movieService.addMovie(newShowing.getMovie());
        }
        return updateShowing(oldShowing, newShowing);
    }

    // 90 days wrappers

    public Showing extendShowingFor90Days(@NonNull Showing showing) throws ShowingException {
        if (!inDatabase(showing)) {
            throw newException("Showing not in Database!");
        }
        for (int i = 1; i < 90; i++) {
            showingRepo.save(new Showing(
                    showing.getTime().plusDays(i),
                    showing.getMovie(),
                    showing.getTheater()));
        }
        return showing;
    }

    public Showing createShowingsFor90Days(@NonNull Showing showing) throws ShowingException, MovieException {
        if (inDatabase(showing)) {
            throw newException("Showing already in Database!");
        }
        if (!movieService.inDatabase(showing.getMovie())) {
            throw movieService.newException("Movie not added to Database!");
        }
        log.info("Adding 90 x %s to Database".formatted(showing));
        showingRepo.save(showing);
        return extendShowingFor90Days(showing);
    }

   public Showing createShowingsAndMovieFor90Days(@NonNull Showing showing) throws ShowingException {
       try {
           createShowingsFor90Days(showing);
       } catch (MovieException e) {
           movieService.addMovie(showing.getMovie());
           createShowingsFor90Days(showing);
       }
       return showing;
   }

    public List<Showing> findShowingsFor90Days(@NonNull Showing showing) {
        return showingRepo.findAll().stream()
                        .filter(
                                showing1 ->
                                        showing1.getMovie()
                                                .getMovieId()
                                                .equals(showing.getMovie().getMovieId()))
                        .collect(Collectors.toList());
    }

    public List<Showing> findShowingsFor90DaysByMovie(@NonNull Movie movie) {
        return showingRepo.findAll().stream()
                .filter(showing -> showing.getMovie().getMovieId().equals(movie.getMovieId()))
                .collect(Collectors.toList());
    }

    public void removeShowingsFor90Days(@NonNull Showing showing) throws ShowingException {
        if (!inDatabase(showing)) {
            throw newException("Showing not in Database!");
        }
        var showings = findShowingsFor90Days(showing);
        log.info("removing %d x %s".formatted(showings.size(), showing));
        showingRepo.deleteAll(showings);
    }

    public void removeShowingsFor90DaysByMovie(@NonNull Movie movie) throws MovieException, ShowingException {
        if (!movieService.inDatabase(movie)) {
            throw newException("Movie not in Database!");
        }
        showingRepo.findShowingByMovieId(movie.getMovieId()).ifPresent(this::removeShowingsFor90Days);
    }

    public void updateShowingsFor90Days(@NonNull Showing oldShowing, @NonNull Showing newShowing) throws ShowingException, MovieException {
        if (!inDatabase(oldShowing)) {
            throw newException("Showing not in Database!");
        }
        if (!movieService.inDatabase(newShowing.getMovie())) {
            throw movieService.newException("newer Movie not added to Database!");
        }
        var oldInfo = oldShowing.toString();
        log.info("updating from %s to %s".formatted(oldInfo, newShowing));
        findShowingsFor90Days(oldShowing).forEach(showing1 -> {
            showing1.setTheater(newShowing.getTheater());
            showing1.setMovie(newShowing.getMovie());
            showingRepo.save(showing1);
        });
    }

    public void updateShowingsFor90DaysByMovie(@NonNull Movie movie, @NonNull Showing newShowing) throws ShowingException, MovieException {
        if (!movieService.inDatabase(movie)) {
            throw newException("Showing not in Database!");
        }
        if (!movieService.inDatabase(newShowing.getMovie())) {
            throw movieService.newException("newer Movie not added to Database!");
        }
        log.info("updating for movie %s to %s".formatted(movie, newShowing));
        findShowingsFor90DaysByMovie(movie).forEach(showing1 -> {
            showing1.setTheater(newShowing.getTheater());
            showing1.setMovie(newShowing.getMovie());
            showingRepo.save(showing1);
        });
    }

    public void updateShowingsAncCreateMovieFor90Days(@NonNull Showing oldShowing, @NonNull Showing newShowing) throws ShowingException {
        try {
           updateShowingsFor90Days(oldShowing, newShowing);
        } catch (MovieException e) {
            movieService.addMovie(newShowing.getMovie());
            updateShowingsFor90Days(oldShowing, newShowing);
        }
    }

    public void updateShowingsAncCreateMovieFor90DaysByMovie(@NonNull Movie movie, @NonNull Showing newShowing) throws ShowingException {
        try {
            updateShowingsFor90DaysByMovie(movie, newShowing);
        } catch (MovieException e) {
            movieService.addMovie(newShowing.getMovie());
            updateShowingsFor90DaysByMovie(movie, newShowing);
        }
    }
}
