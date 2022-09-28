package org.duze.duzekino.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.duze.duzekino.model.Movie;
import org.duze.duzekino.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Consumer;

@RestController
@RequestMapping("api/v1/Movie")
@RequiredArgsConstructor
@Slf4j
public class MovieController {

    final MovieService movieService;

    @GetMapping
    public List<Movie> getMovies() {
        log.info("Fetching Movies");
        return movieService.getMovies();
    }

    @PostMapping
    public void registerMovie(@RequestBody Movie movie) throws IllegalStateException {
        log.info("Registering new Movie, %s".formatted(movie));
        movieService.addMovie(movie);
    }

    @DeleteMapping
    public void deleteMovie(@RequestParam long id) throws IllegalStateException {
        log.info("Deleting Movie with id %d".formatted(id));
        findByIdAndDo(movieService::deleteMovie, id);
    }

    @PutMapping
    public void updateMovie(@RequestParam long id, @RequestBody Movie movie) throws IllegalStateException {
        log.info("Updating Movie with id %d. New Data: %s".formatted(id, movie));
        findByIdAndDo(movie1 -> movieService.updateMovie(movie1, movie), id);
    }

    private void findByIdAndDo(Consumer<Movie> fn, long id) throws IllegalStateException {
        var mv = movieService.findMovieById(id);
        mv.ifPresentOrElse(
                fn,
                () -> {
                    log.error("Couldn't find movie with id %d".formatted(id));
                    throw new IllegalStateException("No movie with such ID");
                });
    }
}
