package org.duze.duzekino.service;

import org.duze.duzekino.model.Movie;
import org.duze.duzekino.model.PG;
import org.duze.duzekino.model.Showing;
import org.duze.duzekino.model.Theater;
import org.duze.duzekino.repository.MovieRepository;
import org.duze.duzekino.repository.ShowingRepository;
import org.duze.duzekino.repository.TheaterRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShowingServiceTest {

    @Autowired MovieService movieService;
    @Autowired MovieRepository movieRepository;
    Movie movie;

    @Autowired ShowingService showingService;
    @Autowired ShowingRepository showingRepository;
    Showing showing;

    @Autowired TheaterRepository theaterRepository;
    Theater theater;

    @BeforeEach
    void setUp() {
        // set up movies
        movie = new Movie("ExampleTitle", "desc", 2000, Duration.ofMinutes(60), PG.SEVENTEEN);
        TestUtils.setUpRepo(movie, movieRepository);
        // set up theater
        theater = TestUtils.setUpRepo(new Theater("ExampleTheater"), theaterRepository);
        // set up showings
        showing = TestUtils.setUpRepo(new Showing(LocalDateTime.now(), movie, theater), showingRepository);

    }

//    @AfterEach
//    void tearDown() {}

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

    // failed. addShowing creates 90 showings and showing is only one, method actually returns the right thing
    @Test
    void addShowing() {
        Movie newM = new Movie("Shrek", "the 2", 2001, Duration.ofMinutes(120), PG.ANY);
        Showing newS = new Showing(LocalDateTime.now(), newM, new Theater("TestTheater"));
        showingService.addShowing(newS);
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
        Showing updated = new Showing(LocalDateTime.now(), updatedMovie, new Theater("TestTheater"));
        showingService.updateShowing(showing, updated);
        assertEquals(showing.getMovie().getTitle(), updated.getMovie().getTitle());
    }

    @Test
    void updateShowingById() {
        Movie updatedMovie = new Movie("Updated", "sced", 2, Duration.ofMinutes(6), PG.FIFTEEN);
        Showing updated = new Showing(LocalDateTime.now(), updatedMovie, new Theater("TestTheater"));
        showingService.updateShowingById(showing.getId(), updated);
        assertEquals(showing.getMovie().getTitle(), updated.getMovie().getTitle());
    }
}
