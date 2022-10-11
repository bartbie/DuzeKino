package org.duze.duzekino.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.duze.duzekino.exception.ShowingException;
import org.duze.duzekino.model.Showing;
import org.duze.duzekino.service.ShowingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Consumer;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/api/v1/Showings")
@RequiredArgsConstructor
@Slf4j
public class ShowingController {

    final ShowingService showingService;

    @GetMapping
    public List<Showing> getShowings(){
        log.info("Fetching Showings");
        return showingService.getShowings();
    }

    @PostMapping
    public void addShowing(@RequestBody Showing showing) throws ShowingException {
        log.info("Registering new showing, %s".formatted(showing));
        showingService.addShowing(showing);
    }

    @DeleteMapping
    public void deleteShowing(@RequestParam long id) throws ShowingException{
        log.info("Deleting showing with id %d".formatted(id));
        findByIdAndDo(showingService::deleteShowing, id);
    }

    @PutMapping
    public void updateShowingById(@RequestParam long id, @RequestBody Showing showing) {
        log.info("Updating showing with id %d. New Data: %s".formatted(id, showing));
        findByIdAndDo(showing1 -> showingService.updateShowing(showing1, showing), id);

    }

    private void findByIdAndDo(Consumer<Showing> fn, long id) throws ShowingException {
        var mv = showingService.findShowingById(id);
        mv.ifPresentOrElse(
                fn,
                () -> {
                    log.error("Couldn't find showing with id %d".formatted(id));
                    throw new ShowingException("No showing with such ID");
                });
    }




}
