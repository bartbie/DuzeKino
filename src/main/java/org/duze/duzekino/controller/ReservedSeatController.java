package org.duze.duzekino.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.duze.duzekino.exception.ReservedSeatException;
import org.duze.duzekino.model.Booking;
import org.duze.duzekino.model.ReservedSeat;
import org.duze.duzekino.service.ReservedSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reserved-seats")
@Slf4j
public class ReservedSeatController {

    final ReservedSeatService reservedSeatService;


    // gets all reserved seats for all showings for all movies (probably not useful)
    @GetMapping
    public List<ReservedSeat> getReservedSeats(){
        log.info("Fetching reserved seats");
        return reservedSeatService.getReservedSeats();
    }

    // gets the seats in booking
    @GetMapping("/booking")
    public List<ReservedSeat> getReservedSeatsInBooking(@RequestBody Booking booking){
        log.info("Fetching reserved seats in booking %s".formatted(booking));
        return reservedSeatService.getReservedSeatsByBooking(booking);
    }

    @PostMapping
    public void addReservedSeat(@RequestBody ReservedSeat reservedSeat) throws ReservedSeatException {
        log.info("Adding new reserved seat, %s".formatted(reservedSeat));
        reservedSeatService.addReservedSeat(reservedSeat);
    }

    @DeleteMapping
    public void deleteReservedSeat(@RequestParam long id) throws ReservedSeatException {
        log.info("Deteling reserved seat with id %d".formatted(id));
        reservedSeatService.deleteReservedSeatById(id);
    }

    @PutMapping
    public void updateReservedSeatById(@RequestParam long id, @RequestBody ReservedSeat newReservedSeat){
        log.info("Updating reserved seat with id %d. New Data: %s".formatted(id, newReservedSeat));
        reservedSeatService.updateReservedSeatById(id, newReservedSeat);
    }

}
