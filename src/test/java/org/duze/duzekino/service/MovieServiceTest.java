package org.duze.duzekino.service;

import org.duze.duzekino.model.Movie;
import org.duze.duzekino.model.PG;
import org.duze.duzekino.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieServiceTest {

    @Autowired MovieService service;
    @Autowired MovieRepository repo;

    Movie movie;

    @BeforeEach
    void setUp() {
        movie = new Movie("ExampleTitle", "desc", 2000, Duration.ofMinutes(60), PG.SEVENTEEN);
        repo.deleteAll();
        repo.save(movie);
    }

    //    @AfterEach
    //    void tearDown() {
    //    }

    @Test
    void inDatabase() {
        assertTrue(service.inDatabase(movie));
    }

    @Test
    void getMovies() {
        assertEquals(List.of(movie), service.getMovies());
    }

    @Test
    void addMovie() {
        Movie newM = new Movie("Shrek", "the 2", 2001, Duration.ofMinutes(120), PG.ANY);
        service.addMovie(newM);
        assertEquals(List.of(movie, newM), (service.getMovies()));
    }

    @Test
    void findMovieById() {
        assertEquals(movie, service.findMovieById(movie.getId()).get());
    }

    @Test
    void updateMovie() {
        Movie updated = new Movie("Updated", "sced", 2, Duration.ofMinutes(6), PG.FIFTEEN);
        service.updateMovie(movie, updated);
        assertEquals(movie.getTitle(), updated.getTitle());
    }

    @Test
    void deleteMovie() {
        service.deleteMovie(movie);
        assertFalse(service.inDatabase(movie));
    }
}
