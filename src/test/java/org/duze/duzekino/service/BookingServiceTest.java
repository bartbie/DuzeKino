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
    Theater theater;

    @Autowired SeatRepository seatRepository;
    Seat seat;

    @Autowired ReservedSeatRepository reservedSeatRepository;
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
        // set up seat
        seat = TestUtils.setUpRepo(new Seat(2, 2, theater), seatRepository);
        // set up reserved seat
        reservedSeat = TestUtils.setUpRepo(new ReservedSeat(seat), reservedSeatRepository);
        HashSet<ReservedSeat> reservedSeats = new HashSet<>();
        reservedSeats.add(reservedSeat);
        // set up booking
        booking = TestUtils.setUpRepo(new Booking("firstName", "lastName", "58484", "email", showing, reservedSeats, 110.00), bookingRepository);

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

    @Test
    void addBooking() {
        Movie newMovie = new Movie("shrek", "desc", 2010, Duration.ofMinutes(6), PG.FIFTEEN);
        Theater newTheater = new Theater("name");
        Showing newShowing = new Showing(LocalDateTime.now(), newMovie, newTheater);
        Seat newSeat = new Seat(1, 1, theater);
        ReservedSeat newReservedSeat = new ReservedSeat(seat);
        HashSet<ReservedSeat> reservedSeats = new HashSet<>();
        reservedSeats.add(newReservedSeat);
        Booking newBooking = new Booking("firstname", "lastName", "576884", "j@hf.dk", newShowing, reservedSeats, 110.00);
        bookingService.addBooking(newBooking);
        assertEquals(List.of(booking, newBooking), bookingService.getBookings());
    }

/*    @Test
    void updateBooking() {
        Movie updatedMovie = new Movie("shrek", "desc", 2010, Duration.ofMinutes(6), PG.FIFTEEN);
        Theater updatedTheater = new Theater("name");
        Showing updatedShowing = new Showing(LocalDateTime.now(), updatedMovie, updatedTheater);
        Booking updatedBooking = new Booking("firstname", "lastName", "576884", "j@hf.dk", updatedShowing);

        bookingService.updateBooking(booking,updatedBooking);
        assertEquals(booking.getFirstName(), updatedBooking.getFirstName());
    }*/


    // passed
    @Test
    void deleteBooking() {
        bookingService.deleteBooking(booking);
        assertFalse(bookingService.inDatabase(booking));
    }


    @Test
    void updateBookingById() {
        Movie m = new Movie("shrek", "desc", 2010, Duration.ofMinutes(6), PG.FIFTEEN);
        Theater t = new Theater("name");
        Showing s = new Showing(LocalDateTime.now(), m, t);
        Seat newSeat = new Seat(1, 1, theater);
        ReservedSeat newReservedSeat = new ReservedSeat(seat);
        HashSet<ReservedSeat> reservedSeats = new HashSet<>();
        reservedSeats.add(newReservedSeat);
        Booking updatedBooking = new Booking("firstname", "lastName", "576884", "j@hf.dk", s, reservedSeats, 110.00);
        bookingService.updateBookingById(booking.getBookingId(),updatedBooking);
        assertEquals(booking.getFirstName(), updatedBooking.getFirstName());
    }


}