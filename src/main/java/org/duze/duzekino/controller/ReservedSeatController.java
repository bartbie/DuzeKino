package org.duze.duzekino.controller;

import lombok.RequiredArgsConstructor;
import org.duze.duzekino.service.ReservedSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequiredArgsConstructor
public class ReservedSeatController {

    final ReservedSeatService reservedSeatService;
}
