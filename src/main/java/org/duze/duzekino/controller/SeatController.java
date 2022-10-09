package org.duze.duzekino.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.duze.duzekino.model.Seat;
import org.duze.duzekino.model.Theater;
import org.duze.duzekino.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:66342")
@RequestMapping("api/v1/Seat")
@RequiredArgsConstructor
@Slf4j
public class SeatController {

    final SeatService seatService;

    // returns all seats in specific theater
    @GetMapping
    public List<Seat> getSeatsInTheater(@RequestParam Theater theater){
        log.info("Fetching seats in theater %s".formatted(theater));
        return seatService.findSeatsByTheater(theater);
    }

    // returns all seats in both theater, don't think we need it
    @GetMapping("/all-seats")
    public List<Seat> getAllSeats(){
        log.info("Fetching all seats");
        return seatService.getAllSeats();
    }


}
