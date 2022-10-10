package org.duze.duzekino.service;

import org.duze.duzekino.model.*;
import org.duze.duzekino.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.print.Book;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookingServiceTest {
    @Autowired BookingService bookingService;
    @Autowired BookingRepository bookingRepository;
    Booking booking;

    @Autowired ShowingService showingService;
    @Autowired ShowingRepository showingRepository;
    Showing showing;

    @Autowired MovieService movieService;
    @Autowired MovieRepository movieRepository;
    Movie movie;

    @Autowired TheaterRepository theaterRepository;
    @Autowired TheaterService theaterService;
    Theater theater;

    @Autowired SeatRepository seatRepository;
    @Autowired SeatService seatService;
    Seat seat;

    @Autowired ReservedSeatRepository reservedSeatRepository;
    @Autowired ReservedSeatService reservedSeatService;
    ReservedSeat reservedSeat;




    @BeforeEach
    void setUp() {
        // set up movies
        movie = new Movie("ExampleTitle", "desc", 2000, Duration.ofMinutes(60), PG.SEVENTEEN);
        TestUtils.setUpRepo(movie, movieRepository);
        // set up theater
        theater = TestUtils.setUpRepo(new Theater("ExampleTheater"), theaterRepository);
        // set up showing
        showing = TestUtils.setUpRepo(new Showing(LocalDateTime.now(), movie, theater), showingRepository);
        // set up booking
        booking = TestUtils.setUpRepo(new Booking("firstName", "lastName", "58484", "email", showing,110.00), bookingRepository);

    }


    // passed
    @Test
    void inDatabase() {
        assertTrue(bookingService.inDatabase(booking));
    }

    // passed
    @Test
    void getBookings() {
        assertEquals(List.of(booking), bookingService.getBookings());
    }

    // passed
    @Test
    void findBookingById() {
        assertEquals(booking, bookingService.findBookingById(booking.getBookingId()).get());
    }

    // ERROR:  object references an unsaved transient instance - save the transient instance before flushing
    @Test
    void addBooking() {
        Movie newMovie = new Movie("shrek", "desc", 2010, Duration.ofMinutes(6), PG.FIFTEEN);
        Theater newTheater = new Theater("name");
        Showing newShowing = new Showing(LocalDateTime.now(), newMovie, newTheater);
        Booking newBooking = new Booking("firstname", "lastName", "576884", "j@hf.dk", newShowing, 110.00);
        bookingService.addBooking(newBooking);
        assertEquals(List.of(booking, newBooking), bookingService.getBookings());
    }

    // ERROR: detached entity passed to persist
    @Test
    void updateBooking() {
        Movie updatedMovie = new Movie("shrek", "desc", 2010, Duration.ofMinutes(6), PG.FIFTEEN);
        Theater updatedTheater = new Theater("name");
        Showing updatedShowing = new Showing(LocalDateTime.now(), updatedMovie, updatedTheater);
        Booking updatedBooking = new Booking("firstname", "lastName", "576884", "j@hf.dk", updatedShowing, 110.00);

        bookingService.updateBooking(booking,updatedBooking);
        assertEquals(booking.getFirstName(), updatedBooking.getFirstName());
    }


    // passed
    @Test
    void deleteBooking() {
        bookingService.deleteBooking(booking);
        assertFalse(bookingService.inDatabase(booking));
    }

    // ERROR: detached entity passed to persist
    @Test
    void updateBookingById() {
        Movie m = new Movie("shrek", "desc", 2010, Duration.ofMinutes(6), PG.FIFTEEN);
        Theater t = new Theater("name");
        Showing s = new Showing(LocalDateTime.now(), m, t);
        Booking updatedBooking = new Booking("firstname", "lastName", "576884", "j@hf.dk", s, 110.00);
        bookingService.updateBookingById(booking.getBookingId(),updatedBooking);
        assertEquals(booking.getFirstName(), updatedBooking.getFirstName());
    }


}