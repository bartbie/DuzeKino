package org.duze.duzekino.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.duze.duzekino.model.Showing;
import org.duze.duzekino.repository.ShowingRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public final class ShowingService {
    final ShowingRepository showingRepository;

    //create
    //read
    //update
    //delete


    public List<Showing> getShowings() {
        return showingRepository.findAll();
    }

    public Showing addShowing(@NonNull Showing showing){
        showingRepository.save(showing);
        return showing;
    }

    // Exception
    public Showing findShowingByMovieId(@NonNull Long movieId){
        return showingRepository.findShowingByMovieId(movieId);
    }

    public void deleteShowingByMovieId(@NonNull Long movieId) {
        showingRepository.deleteShowingByMovieId(movieId);
    }

    public Showing updateShowing(@NonNull Showing showing){
        Showing oldShowing = findShowingByMovieId(showing.getMovie().getMovieId());
        oldShowing.setTime(showing.getTime());
        oldShowing.setTheater(showing.getTheater());
        oldShowing.setMovie(showing.getMovie());
        showingRepository.save(oldShowing);
        return oldShowing;
    }


}
