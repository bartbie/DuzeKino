package org.duze.duzekino.controller;

import org.duze.duzekino.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
public class SeatController {

    @Autowired
    SeatService seatService;

    //mappings to be written
}
