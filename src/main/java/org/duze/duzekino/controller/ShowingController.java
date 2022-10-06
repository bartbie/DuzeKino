package org.duze.duzekino.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.duze.duzekino.model.Showing;
import org.duze.duzekino.service.ShowingService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
