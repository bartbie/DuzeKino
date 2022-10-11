package org.duze.duzekino.service;

import lombok.NonNull;
import org.duze.duzekino.exception.MovieException;
import org.duze.duzekino.exception.ShowingException;
import org.duze.duzekino.model.Movie;
import org.duze.duzekino.model.PG;
import org.duze.duzekino.model.Showing;
import org.duze.duzekino.model.Theater;
import org.duze.duzekino.repository.MovieRepository;
import org.duze.duzekino.repository.ShowingRepository;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@FixMethodOrder()
class ShowingServiceTest {

    @Autowired MovieService movieService;
    @Autowired MovieRepository movieRepository;
    Movie movie;

    @Autowired ShowingService showingService;
    @Autowired ShowingRepository showingRepository;
    Showing showing;

    Theater theater;

    @BeforeEach
    void setUp() {
//         set up movies
        movie = new Movie("ExampleTitle", "desc", 2000, Duration.ofMinutes(60), PG.SEVENTEEN);
        movieService.addMovie(movie);
//         set up theater
        theater = Theater.THEATER2;
//         set up showings
        showing = new Showing(LocalDateTime.now(), movie, theater);
        showingRepository.save(showing);
    }

    @AfterEach
    void tearDown() {
        movieRepository.deleteAll();
        showingRepository.deleteAll();
    }

    @Test
    void inDatabase() {
        assertTrue(showingService.inDatabase(showing));
    }

    @Test
    void getShowings() {
        assertEquals(List.of(showing), showingService.getShowings());
    }

    @Test
    void findShowingById() {
        assertEquals(showing, showingService.findShowingById(showing.getId()).get());
    }

    @Test
    void addShowing() {
        Movie newM = new Movie("Shrek", "the 2", 2001, Duration.ofMinutes(120), PG.ANY);
        movieService.addMovie(newM);
        Showing newS = new Showing(LocalDateTime.now(), newM, Theater.THEATER1);
        showingService.addShowing(newS);
        assertEquals(List.of(showing, newS), showingService.getShowings());
    }

    @Test
    void addShowingAndMovie() {
        Movie newM = new Movie("Shrek", "the 2", 2001, Duration.ofMinutes(120), PG.ANY);
        Showing newS = new Showing(LocalDateTime.now(), newM, Theater.THEATER1);
        showingService.addShowingAndMovie(newS);
        assertEquals(List.of(showing, newS), showingService.getShowings());
    }

    @Test
    void deleteShowing() {
        showingService.deleteShowing(showing);
        assertFalse(showingService.inDatabase(showing));
    }

    @Test
    void deleteShowingByMovieId() {
        showingService.deleteShowingByMovieId(movie.getMovieId());
        assertFalse(showingService.inDatabase(showing));
    }

    @Test
    void updateShowing() {
        Movie updatedMovie = new Movie("Updated", "sced", 2, Duration.ofMinutes(6), PG.FIFTEEN);
        movieService.addMovie(updatedMovie);
        Showing updated = new Showing(LocalDateTime.now(), updatedMovie, Theater.THEATER1);
        showingService.updateShowing(showing, updated);
        assertEquals(showing.getMovie().getTitle(), updated.getMovie().getTitle());
    }

    @Test
    void updateShowingById() {
        System.out.println(showing);
        Movie updatedMovie = new Movie("Updated", "sced", 2, Duration.ofMinutes(6), PG.FIFTEEN);
        movieService.addMovie(updatedMovie);
        System.out.println(updatedMovie);
        Showing updated = new Showing(LocalDateTime.now(), updatedMovie, Theater.THEATER1);
        System.out.println(updated);
        showing = showingService.updateShowingById(showing.getId(), updated);
        assertEquals(showing.getMovie().getTitle(), updated.getMovie().getTitle());
    }

    @Test
    void updateShowingAndCreateMovie() {
        Movie updatedMovie = new Movie("Updated", "sced", 2, Duration.ofMinutes(6), PG.FIFTEEN);
        Showing updated = new Showing(LocalDateTime.now(), updatedMovie, Theater.THEATER1);
        showingService.updateShowingAndCreateMovie(showing, updated);
        assertEquals(showing.getMovie().getTitle(), updated.getMovie().getTitle());
    }

    @Test
    void createShowingsFor90Days() {
        // one showing is already added
//        System.out.println(showingService.getShowings());
        Movie newM = new Movie("Shrek", "the 2", 2001, Duration.ofMinutes(120), PG.ANY);
        movieService.addMovie(newM);
        Showing newS = new Showing(LocalDateTime.now(), newM, Theater.THEATER1);
        showingService.createShowingsFor90Days(newS);
        assertEquals(90+1, showingService.getShowings().size());
    }

    @Test
    void extendShowingFor90Days() {
        showingService.extendShowingFor90Days(showing);
        assertEquals(90, showingService.getShowings().size());
    }

    @Test
    void createShowingsAndMovieFor90Days() {
        Movie newM = new Movie("Shrek", "the 2", 2001, Duration.ofMinutes(120), PG.ANY);
        Showing newS = new Showing(LocalDateTime.now(), newM, Theater.THEATER1);
        showingService.createShowingsAndMovieFor90Days(newS);
        assertEquals(90+1, showingService.getShowings().size());
    }

    @Test
    void findShowingsFor90Days() {
        showingService.extendShowingFor90Days(showing);
        assertEquals(90, showingService.findShowingsFor90Days(showing).size());
    }

    @Test
    void findShowingsFor90DaysByMovie() {
        showingService.extendShowingFor90Days(showing);
        assertEquals(90, showingService.findShowingsFor90DaysByMovie(showing.getMovie()).size());
    }

    @Test
    void removeShowingsFor90Days() {
        showingService.extendShowingFor90Days(showing);
        Movie newM = new Movie("Shrek", "the 2", 2001, Duration.ofMinutes(120), PG.ANY);
        Showing newS = new Showing(LocalDateTime.now(), newM, Theater.THEATER1);
        showingService.addShowingAndMovie(newS);
        showingService.removeShowingsFor90Days(showing);
        assertEquals(1, showingService.getShowings().size());
    }

    @Test
    void removeShowingsFor90DaysByMovie() {
        showingService.extendShowingFor90Days(showing);
        Movie newM = new Movie("Shrek", "the 2", 2001, Duration.ofMinutes(120), PG.ANY);
        Showing newS = new Showing(LocalDateTime.now(), newM, Theater.THEATER1);
        showingService.addShowingAndMovie(newS);
        showingService.removeShowingsFor90DaysByMovie(newM);
        assertEquals(90, showingService.getShowings().size());
    }

    @Test
    void updateShowingsFor90Days() {
        showingService.extendShowingFor90Days(showing);
        Movie newM = new Movie("Shrek", "the 2", 2001, Duration.ofMinutes(120), PG.ANY);
        movieService.addMovie(newM);
        Showing newS = new Showing(LocalDateTime.now(), newM, Theater.THEATER1);
        showingService.updateShowingsFor90Days(showing, newS);
        assertEquals(90, showingService.findShowingsFor90Days(newS).size());
    }

    @Test
    void updateShowingsFor90DaysByMovie() {
        showingService.extendShowingFor90Days(showing);
        Movie newM = new Movie("Shrek", "the 2", 2001, Duration.ofMinutes(120), PG.ANY);
        movieService.addMovie(newM);
        Showing newS = new Showing(LocalDateTime.now(), newM, Theater.THEATER1);
        showingService.updateShowingsFor90DaysByMovie(movie, newS);
        assertEquals(90, showingService.findShowingsFor90Days(newS).size());
    }

    @Test
    void updateShowingsAncCreateMovieFor90Days() {
        showingService.extendShowingFor90Days(showing);
        Movie newM = new Movie("Shrek", "the 2", 2001, Duration.ofMinutes(120), PG.ANY);
        Showing newS = new Showing(LocalDateTime.now(), newM, Theater.THEATER1);
        showingService.updateShowingsAncCreateMovieFor90Days(showing, newS);
        assertEquals(90, showingService.findShowingsFor90Days(newS).size());
    }
    @Test
    public void updateShowingsAncCreateMovieFor90DaysByMovie() {
        showingService.extendShowingFor90Days(showing);
        Movie newM = new Movie("Shrek", "the 2", 2001, Duration.ofMinutes(120), PG.ANY);
        Showing newS = new Showing(LocalDateTime.now(), newM, Theater.THEATER1);
        showingService.updateShowingsAncCreateMovieFor90DaysByMovie(movie, newS);
        assertEquals(90, showingService.findShowingsFor90Days(newS).size());

    }
}
