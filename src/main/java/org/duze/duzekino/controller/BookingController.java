package org.duze.duzekino.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.duze.duzekino.exception.BookingException;
import org.duze.duzekino.exception.MovieNotFoundException;
import org.duze.duzekino.model.Booking;
import org.duze.duzekino.model.Movie;
import org.duze.duzekino.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Consumer;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("api/v1/Booking")
@RequiredArgsConstructor
@Slf4j
public class BookingController {
    final BookingService bookingService;


    @GetMapping
    public List<Booking> getBookings() {
        log.info("Fetching Bookings");
        return bookingService.getBookings();
    }

    @PostMapping
    public void addBooking(@RequestBody Booking booking) throws IllegalStateException {
        log.info("Registering new Movie, %s".formatted(booking));
        bookingService.addBooking(booking);
    }

    @DeleteMapping
    public void deleteBooking(@RequestParam long id) throws MovieNotFoundException {
        log.info("Deleting Booking with id %d".formatted(id));
        findByIdAndDo(bookingService::deleteBooking, id);
    }

    @PutMapping
    public void updateBooking(@RequestParam long id, @RequestBody Booking booking) throws BookingException {
        log.info("Updating Booking id %d. New Data: %s".formatted(id, booking));
        findByIdAndDo(movie1 -> bookingService.updateBooking(movie1, booking), id);
    }

    private void findByIdAndDo(Consumer<Booking> fn, long id) throws MovieNotFoundException {
        var mv = bookingService.findBookingById(id);
        mv.ifPresentOrElse(
                fn,
                () -> {
                    log.error("Couldn't find booking with id %d".formatted(id));
                    throw new MovieNotFoundException("No booking with such ID");
                });
    }
}