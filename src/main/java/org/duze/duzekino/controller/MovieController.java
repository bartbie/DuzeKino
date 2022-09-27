package org.duze.duzekino.controller;

import lombok.RequiredArgsConstructor;
import org.duze.duzekino.model.Movie;
import org.duze.duzekino.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Consumer;

@RestController
@RequestMapping("api/v1/Movie")
@RequiredArgsConstructor
public class MovieController {

    final MovieService movieService;

    @GetMapping
    public List<Movie> getMovies() {
        return movieService.getMovies();
    }

    @PostMapping
    public void registerMovie(@RequestBody Movie movie) throws IllegalStateException {
        movieService.addMovie(movie);
    }

    @DeleteMapping
    public void deleteMovie(@RequestParam long id) throws IllegalStateException {
        findByIdAndDo(movieService::deleteMovie, id);
    }

    @PutMapping
    public void updateMovie(@RequestParam long id, @RequestBody Movie movie) throws IllegalStateException{
        findByIdAndDo(movie1 -> movieService.updateMovie(movie1, movie), id);
    }

    private void findByIdAndDo(Consumer<Movie> fn, long id) throws  IllegalStateException{
        var mv = movieService.findMovieById(id);
        mv.ifPresentOrElse(
                fn,
                () -> {
                    throw new IllegalStateException("No movie with such ID");
                });
    }
}
