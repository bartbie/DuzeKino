package org.duze.duzekino.service;

import org.duze.duzekino.model.Seat;
import org.duze.duzekino.model.Theater;
import org.duze.duzekino.repository.SeatRepository;
import org.duze.duzekino.repository.TheaterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SeatServiceTest {

    @Autowired SeatService seatService;
    @Autowired SeatRepository seatRepository;
    Seat seat;

    @Autowired TheaterRepository theaterRepository;
    @Autowired TheaterService theaterService;
    Theater theater;

    @BeforeEach
    void setUp() {
        // set up Theater
        theater = TestUtils.setUpRepo(new Theater("ExampleTheater"), theaterRepository);
        // set up Seat
        seat = TestUtils.setUpRepo(new Seat(1, 2, theater), seatRepository);
    }

    // passed
    @Test
    void inDatabase() {
        assertTrue(seatService.inDatabase(seat));
    }

    // passed
    @Test
    void getAllSeats() {
        assertEquals(List.of(seat), seatService.getAllSeats());
    }


    // passed
    @Test
    void findSeatsByTheater() {
        assertEquals(List.of(seat), seatService.findSeatsByTheater(seat.getTheater()));

    }

    // passed
    @Test
    void findSeatById() {
        assertEquals(seat, seatService.findSeatById(seat.getSeatId()).get());
    }


    // passed
    @Test
    void findSeatByRowAndNumber() {
        assertEquals(seat, seatService.findSeatByRowAndNumber(seat.getTheater(), seat.getRow(), seat.getNumber()).get());
    }
}