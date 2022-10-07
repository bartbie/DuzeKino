package org.duze.duzekino.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.duze.duzekino.model.Booking;
import org.duze.duzekino.model.Movie;
import org.duze.duzekino.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public final class BookingService {
    final BookingRepository bookingRepo;

    public List<Booking> getBookings() {return bookingRepo.findAll();}

    public Optional<Booking> findBookingById(long id) {
        return bookingRepo.findById(id);
    }

    public Booking updateBooking(@NonNull Booking oldB, @NonNull Booking newB) {
        var oldInfo = oldB.toString();
        oldB.setFirstName(newB.getFirstName());
        oldB.setLastName(newB.getLastName());
        oldB.setPhoneNumber(newB.getPhoneNumber());
        oldB.setEmail(newB.getEmail());
        oldB.setShowing(newB.getShowing());
        bookingRepo.save(oldB);
        log.info("Updated booking from %s to %s".formatted(oldInfo, oldB));
        return oldB;
    }
    public void deleteBooking(@NonNull Booking booking) {
        log.info("removing %s".formatted(booking));
        bookingRepo.delete(booking);
    }
    public Booking addBooking(@NonNull Booking booking) {
        log.info("Adding %s to Database".formatted(booking));
        bookingRepo.save(booking);
        return booking;
    }
}

