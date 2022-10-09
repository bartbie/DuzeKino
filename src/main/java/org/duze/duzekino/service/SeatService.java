package org.duze.duzekino.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.duze.duzekino.model.Booking;
import org.duze.duzekino.model.Seat;
import org.duze.duzekino.model.Showing;
import org.duze.duzekino.model.Theater;
import org.duze.duzekino.repository.SeatRepository;
import org.duze.duzekino.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
@Slf4j
public class SeatService {

    final SeatRepository seatRepository;
    final TheaterRepository theaterRepository;

    public boolean inDatabase(@NonNull Seat seat) {
        Predicate<Seat> row = s -> s.getRow() == (seat.getRow());
        Predicate<Seat> number = s -> s.getNumber() == (seat.getNumber());
        return getAllSeats().stream().anyMatch(row.and(number));
    }

    public List<Seat> getAllSeats(){
        return seatRepository.findAll();
    }

    // finds all seats in specific theater
    public List<Seat> findSeatsByTheater(Theater theater) {
        return seatRepository.findAllByTheater(theater);
    }

    public Optional<Seat> findSeatById(long id){
        return seatRepository.findById(id);
    }

    public Optional<Seat> findSeatByRowAndNumber(Theater theater, int row, int number) {
        return seatRepository.findSeatByTheaterAndRowAndNumber(theater, row, number);
    }





}
