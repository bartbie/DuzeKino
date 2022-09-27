package org.duze.duzekino.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.duze.duzekino.model.Movie;
import org.duze.duzekino.model.PG;
import org.duze.duzekino.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public final class MovieService {
    final MovieRepository movieRepo;

    // create movie
    // get movies
    // update movie
    // remove movie

    public List<Movie> getMovies(){
        return movieRepo.findAll();
    }

    public Movie addMovie(@NonNull Movie movie) throws IllegalStateException {
        if (inDatabase(movie)) {
            throw new IllegalStateException("Already in Database");
        }

        movieRepo.save(movie);
        return movie;
    }

    public Optional<Movie> findMovieById(long id) {
        return movieRepo.findMovieById(id);
    }

    public Movie updateMovie(@NonNull Movie oldM, @NonNull Movie newM) {
        oldM.setDescription(newM.getDescription());
        oldM.setTitle(newM.getTitle());
        oldM.setLength(newM.getLength());
        oldM.setYear(newM.getYear());
        oldM.setRating(newM.getRating());
        return newM;
    }

    public boolean inDatabase(@NonNull Movie movie) {
        return false;
    }





/*    public Movie createMovie(String title, String description, Integer year, Duration length, PG rating){
        Movie newMovie = Movie.builder()
                .title(title)
                .description(description)
                .year(year)
                .length(length)
                .rating(rating)
                .build();
        movieRepo.save(newMovie);
        return newMovie;
    }*/


}
