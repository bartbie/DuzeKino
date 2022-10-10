package org.duze.duzekino.service;

import org.duze.duzekino.model.Theater;
import org.duze.duzekino.repository.TheaterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TheaterServiceTest {

    @Autowired TheaterService theaterService;
    @Autowired TheaterRepository theaterRepository;
    Theater theater;

    @BeforeEach
    void setUp() {
        theater = TestUtils.setUpRepo(new Theater("ExampleTheater"), theaterRepository);
    }

    // passed
    @Test
    void getTheaters() {
        assertEquals(List.of(theater), theaterService.getTheaters());
    }

    // passed
    @Test
    void findTheaterById() {
        assertEquals(theater, theaterService.findTheaterById(theater.getTheaterId()).get());
    }

    // passed
    @Test
    void findTheaterByName() {
        assertEquals(theater, theaterService.findTheaterByName(theater.getName()).get());
    }

    // passed
    @Test
    void addTheater() {
        Theater newTheater = new Theater("newName");
        theaterService.addTheater(newTheater);
        assertEquals(List.of(theater, newTheater), theaterService.getTheaters());
    }
}