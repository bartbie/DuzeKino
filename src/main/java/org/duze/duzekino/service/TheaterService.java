package org.duze.duzekino.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.duze.duzekino.exception.BookingException;
import org.duze.duzekino.model.Booking;
import org.duze.duzekino.model.Theater;
import org.duze.duzekino.repository.TheaterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public  final class TheaterService {
    final TheaterRepository theaterRepository;

    public List<Theater> getTheaters() {return theaterRepository.findAll();}

    public Optional<Theater> findTheaterById(long id) {
        return theaterRepository.findById(id);
    }

    public Optional<Theater> findTheaterByName(String name) {
        return theaterRepository.findTheaterByName(name);
    }

    public Theater addTheater(@NonNull Theater theater) throws IllegalStateException {
        theaterRepository.save(theater);
        return theater;
    }
}
