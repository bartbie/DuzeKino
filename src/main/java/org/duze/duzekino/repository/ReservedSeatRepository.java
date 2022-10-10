package org.duze.duzekino.repository;

import org.duze.duzekino.model.Booking;
import org.duze.duzekino.model.ReservedSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservedSeatRepository extends JpaRepository<ReservedSeat, Long> {

    List<ReservedSeat> findAllByBooking(Booking booking);

    void deleteAllByBooking(Booking booking);


    void deleteByReservedId(long id);


}
