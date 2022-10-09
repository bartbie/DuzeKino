package org.duze.duzekino.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.duze.duzekino.exception.ShowingException;
import org.duze.duzekino.model.Booking;
import org.duze.duzekino.model.ReservedSeat;
import org.duze.duzekino.model.Seat;
import org.duze.duzekino.model.Showing;
import org.duze.duzekino.repository.ShowingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
@Slf4j
public final class ShowingService {
    final ShowingRepository showingRepo;
    final MovieService movieService;
    final SeatService seatService;

    public ShowingException newException(String msg) {
        log.error(msg);
        return new ShowingException(msg);
    }

    public boolean inDatabase(@NonNull Showing showing) {
        Predicate<Showing> movieEquals = s -> s.getMovie().equals(showing.getMovie());
        Predicate<Showing> theaterEquals = s -> s.getTheater().equals(showing.getTheater());
        return getShowings().stream().anyMatch(movieEquals.and(theaterEquals));
    }

    public List<Showing> getShowings() {
        return showingRepo.findAll();
    }

    public Optional<Showing> findShowingById(long id) {
        return showingRepo.findById(id);
    }

    public Optional<Showing> findShowingByMovieId(@NonNull Long movieId) {
        return showingRepo.findShowingByMovieId(movieId);
    }

    public Showing addShowing(@NonNull Showing showing) throws ShowingException {
        if (inDatabase(showing)) {
            throw newException("Showing already in Database");
        }
        log.info("Adding %s to Database".formatted(showing));
        createShowingFor90Days(showing);
        return showing;
    }

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

    public Showing updateShowing(@NonNull Showing oldShowing, @NonNull Showing newShowing) {
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


    public void createShowingFor90Days(Showing showing){
        for(int i = 0; i <= 90; i++){
            LocalDateTime startDate = showing.getTime();
            Showing newShowing = new Showing();
            newShowing.setTime(startDate.plusDays(i));
            showingRepo.save(newShowing);
        }
    }



}
