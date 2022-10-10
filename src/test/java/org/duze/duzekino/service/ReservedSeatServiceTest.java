package org.duze.duzekino.service;

import org.duze.duzekino.model.*;
import org.duze.duzekino.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservedSeatServiceTest {

    @Autowired SeatRepository seatRepository;
    @Autowired SeatService seatService;
    Seat seat;

    @Autowired TheaterService theaterService;
    @Autowired TheaterRepository theaterRepository;
    Theater theater;

    @Autowired ReservedSeatService reservedSeatService;
    @Autowired ReservedSeatRepository reservedSeatRepository;
    ReservedSeat reservedSeat;

    @Autowired BookingService bookingService;
    @Autowired BookingRepository bookingRepository;
    Booking booking;

    @Autowired MovieService movieService;
    @Autowired MovieRepository movieRepository;
    Movie movie;

    @Autowired ShowingService showingService;
    @Autowired ShowingRepository showingRepository;
    Showing showing;

    @BeforeEach
    void setUp() {
        // set up for Theater
        theater = TestUtils.setUpRepo(new Theater("ExampleTheater"), theaterRepository);
        // set up for seat
        seat = TestUtils.setUpRepo(new Seat(2, 2, theater), seatRepository);
        // set up movies
        movie = new Movie("ExampleTitle", "desc", 2000, Duration.ofMinutes(60), PG.SEVENTEEN);
        TestUtils.setUpRepo(movie, movieRepository);
        // set up showing
        showing = TestUtils.setUpRepo(new Showing(LocalDateTime.now(), movie, theater), showingRepository);
        // set up booking
        booking = TestUtils.setUpRepo(new Booking("firstName", "lastName", "58484", "email", showing,110.00), bookingRepository);
        // set up for Reserved Seat
        reservedSeat = TestUtils.setUpRepo(new ReservedSeat(seat, booking), reservedSeatRepository);

    }


    // passed
    @Test
    void inDatabase() {
        assertTrue(reservedSeatService.inDatabase(reservedSeat));
    }

    // passed
    @Test
    void getReservedSeats() {
        assertEquals(List.of(reservedSeat), reservedSeatService.getReservedSeats());
    }


    // passed
    @Test
    void getReservedSeatsByBooking() {
        assertEquals(List.of(reservedSeat), reservedSeatService.getReservedSeatsByBooking(reservedSeat.getBooking()));
    }

    // passed
    @Test
    void getReservedSeatById() {
        assertEquals(reservedSeat, reservedSeatService.getReservedSeatById(reservedSeat.getReservedId()).get());
    }


    // object references an unsaved transient instance - save the transient instance before flushing
    @Test
    void addReservedSeat() {
        Movie newM = new Movie("Shrek", "the 2", 2001, Duration.ofMinutes(120), PG.ANY);
        Theater newTheater = new Theater("TestTheater");
        Showing newShowing = new Showing(LocalDateTime.now(), newM, newTheater);
        Booking newBooking = new Booking("firstname", "lastName", "576884", "j@hf.dk", newShowing, 110.00);
        Seat newSeat = new Seat(1, 2, newTheater);
        ReservedSeat newReservedSeat = new ReservedSeat(newSeat, newBooking);
        reservedSeatService.addReservedSeat(newReservedSeat);
        assertEquals(List.of(reservedSeat, newReservedSeat), reservedSeatService.getReservedSeats());

    }

    // passed
    @Test
    void deleteReservedSeat() {
        reservedSeatService.deleteReservedSeat(reservedSeat);
        assertFalse(reservedSeatService.inDatabase(reservedSeat));
    }

    // passed
    @Test
    @Transactional
    void deleteReservedSeatsByBooking(){
        reservedSeatService.deleteReservedSeatsByBooking(reservedSeat.getBooking());
        assertFalse(reservedSeatService.inDatabase(reservedSeat));
    }

    // passed
    @Test
    @Transactional
    void deleteReservedSeatById() {
        reservedSeatService.deleteReservedSeatById(reservedSeat.getReservedId());
        assertFalse(reservedSeatService.inDatabase(reservedSeat));
    }

    // ERROR: object references an unsaved transient instance - save the transient instance before flushing
    @Test
    void updateReservedSeat() {
        Movie updatedM = new Movie("Shr", "the 2", 2001, Duration.ofMinutes(120), PG.ANY);
        Theater updatedT = new Theater("TestTheater");
        Showing updatedS = new Showing(LocalDateTime.now(), updatedM, updatedT);
        Booking newBooking = new Booking("firstname", "lastName", "576884", "j@hf.dk", updatedS, 110.00);
        Seat updatedSeat = new Seat(1, 3, updatedT);
        ReservedSeat updatedReservedSeat = new ReservedSeat(updatedSeat, newBooking);
        reservedSeatService.updateReservedSeat(reservedSeat, updatedReservedSeat);
        assertEquals(reservedSeat.getSeat(), updatedReservedSeat.getSeat());
    }

    // doesn't work
    @Test
    void updateReservedSeatById() {
        Movie updatedM = new Movie("Shr", "the 2", 2001, Duration.ofMinutes(120), PG.ANY);
        Theater updatedT = new Theater("TestTheater");
        Showing updatedS = new Showing(LocalDateTime.now(), updatedM, updatedT);
        Booking newBooking = new Booking("firstname", "lastName", "576884", "j@hf.dk", updatedS, 110.00);
        Seat updatedSeat = new Seat(1, 2, updatedT);
        ReservedSeat updatedReservedSeat = new ReservedSeat(updatedSeat, newBooking);
        assertEquals(reservedSeat.getSeat(), updatedReservedSeat.getSeat());
    }
}