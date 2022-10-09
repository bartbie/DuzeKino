package org.duze.duzekino.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.duze.duzekino.model.Theater;
import org.duze.duzekino.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping("api/v1/Theater")
@RequiredArgsConstructor
@Slf4j
public class TheaterController {

    final TheaterService theaterService;

    @GetMapping
    public List<Theater> getTheaters(){
        log.info("Fetching theaters");
        return theaterService.getTheaters();
    }



}
