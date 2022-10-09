package org.duze.duzekino.repository;

import org.duze.duzekino.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findBookingByFirstNameAndLastName(String firstname, String lastname);
}
