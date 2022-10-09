package org.duze.duzekino.repository;

import org.duze.duzekino.model.Seat;
import org.duze.duzekino.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findAllByTheater(Theater theater);

    Optional<Seat> findSeatByTheaterAndRowAndNumber(Theater theater, int row, int number);
}
