package org.duze.duzekino.controller;

import org.duze.duzekino.service.ShowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
public class ShowingsController {

    @Autowired
    ShowingService showingService;

    //mappings to be written
}
