package org.duze.duzekino.controller;

import org.duze.duzekino.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:66342")
public class TheaterController {

    @Autowired
    TheaterService theaterService;

    //mappings to be written
}
