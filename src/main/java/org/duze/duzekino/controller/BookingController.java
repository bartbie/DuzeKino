package org.duze.duzekino.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.duze.duzekino.model.Booking;
import org.duze.duzekino.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("api/v1/Booking")
@RequiredArgsConstructor
@Slf4j
public class BookingController {

    final BookingService bookingService;

    @GetMapping
    public List<Booking> getAllBookings(){
        return bookingService.getALlBookings();
    }

    @PostMapping
    public ResponseEntity<Booking> addBooking(@RequestBody Booking booking){
        bookingService.save(booking);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> updatePhoto(@PathVariable long id, @RequestBody Booking booking) {
        Optional<Booking> retrievedBooking = bookingService.findByID(id);
        if (retrievedBooking.isPresent()) {
            retrievedBooking.get().setCustomer_id(booking.getCustomer_id());
            retrievedBooking.get().setNrOfSeats(booking.getNrOfSeats());
            bookingService.save(retrievedBooking.get());

            return new ResponseEntity<>(retrievedBooking.get(), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(new Booking(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Booking> deleteBooking(@PathVariable long id){
        Optional<Booking> retrievedBooking = bookingService.findByID(id);
        if (retrievedBooking.isPresent()){
            bookingService.deleteBooking(retrievedBooking.get());
            return new ResponseEntity<>(retrievedBooking.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new Booking(), HttpStatus.NOT_FOUND);
        }
    }
}
