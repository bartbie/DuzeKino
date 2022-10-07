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
    @Autowired BookingRepository repo;
    Booking booking;

    @Autowired ShowingService showingService;
    @Autowired ShowingRepository showingRepository;
    Showing showing;

    @Autowired ReservedSeatService reservedSeatService;
    @Autowired ReservedSeatRepository reservedSeatRepository;
    ReservedSeat reservedSeat;
    List<ReservedSeat> reservedSeats;

    @Autowired SeatService seatService;
    @Autowired SeatRepository seatRepository;
    Seat seat;

    @Autowired MovieService movieService;
    @Autowired
    MovieRepository movieRepository;
    Movie movie;

    @Autowired
    TheaterRepository theaterRepository;
    Theater theater;

    @BeforeEach
    void setUp() {
        // set up movies
        movie = new Movie("ExampleTitle", "desc", 2000, Duration.ofMinutes(60), PG.SEVENTEEN);
        TestUtils.setUpRepo(movie, movieRepository);
        // set up theater
        theater = TestUtils.setUpRepo(new Theater("ExampleTheater"), theaterRepository);
        // set up for seat
        seat = TestUtils.setUpRepo(new Seat(1, 2, theater), seatRepository);
        // set up reserved seats
        reservedSeat = TestUtils.setUpRepo(new ReservedSeat(seat), reservedSeatRepository);
        reservedSeats = new ArrayList<>();
        // set up showings
        showing = TestUtils.setUpRepo(new Showing(LocalDateTime.now(), movie, theater), showingRepository);

    }



    @Test
    void getBookings() {
        assertEquals(List.of(booking), bookingService.getBookings());
    }

    @Test
    void findBookingById() {
        assertEquals(booking, bookingService.findBookingById(booking.getBookingId()).get());
    }

    @Test
    void updateBooking() {
        Movie m = new Movie("shrek", "desc", 2010, Duration.ofMinutes(6), PG.FIFTEEN);
        Theater t = new Theater("name");
        Showing s = new Showing(LocalDateTime.now(), m, t);
        Seat seat1 = new Seat(1, 2,t);
        ReservedSeat reservedSeat = new ReservedSeat(seat1);
        List<ReservedSeat> seats = new ArrayList<>();
        seats.add(reservedSeat);

        Booking updatedBooking = new Booking("firstname", "lastName", "576884", "j@hf.dk", s, seats);
        bookingService.updateBookingById(booking.getBookingId(),updatedBooking);
        assertEquals(booking.getFirstName(), updatedBooking.getFirstName());
    }

    @Test
    void deleteBooking() {
        bookingService.deleteBooking(booking);
        assertFalse(bookingService.inDatabase(booking));
    }

    @Test
    void addBooking() {
        Movie m = new Movie("shrek", "desc", 2010, Duration.ofMinutes(6), PG.FIFTEEN);
        Theater t = new Theater("name");
        Showing s = new Showing(LocalDateTime.now(), m, t);
        Seat seat1 = new Seat(1, 2,t);
        ReservedSeat reservedSeat = new ReservedSeat(seat1);
        List<ReservedSeat> seats = new ArrayList<>();
        seats.add(reservedSeat);
        Booking newB = new Booking("firstname", "lastName", "576884", "j@hf.dk", s, seats);
        bookingService.addBooking(newB);
        assertEquals(List.of(booking, newB), bookingService.getBookings());
    }

    @Test
    void updateBookingById() {
        Movie m = new Movie("shrek", "desc", 2010, Duration.ofMinutes(6), PG.FIFTEEN);
        Theater t = new Theater("name");
        Showing s = new Showing(LocalDateTime.now(), m, t);
        Seat seat1 = new Seat(1, 2,t);
        ReservedSeat reservedSeat = new ReservedSeat(seat1);
        List<ReservedSeat> seats = new ArrayList<>();
        seats.add(reservedSeat);

        Booking updatedBooking = new Booking("firstname", "lastName", "576884", "j@hf.dk", s, seats);
        bookingService.updateBookingById(booking.getBookingId(),updatedBooking);
        assertEquals(booking.getFirstName(), updatedBooking.getFirstName());
    }


    @Test
    void inDatabase() {
        assertTrue(bookingService.inDatabase(booking));
    }
}