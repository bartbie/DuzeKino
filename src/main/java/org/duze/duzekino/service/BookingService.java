package org.duze.duzekino.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.duze.duzekino.exception.BookingException;
import org.duze.duzekino.model.Booking;
import org.duze.duzekino.model.Movie;
import org.duze.duzekino.model.ReservedSeat;
import org.duze.duzekino.model.Showing;
import org.duze.duzekino.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;


@Service
@RequiredArgsConstructor
@Slf4j
public final class BookingService {
    final BookingRepository bookingRepo;
//    final ReservedSeatService reservedSeatService;

    public BookingException newException(String msg){
        log.error(msg);
        return new BookingException(msg);
    }

    public boolean inDatabase(@NonNull Booking booking) {
        Predicate<Booking> phoneNumberEquals = s -> s.getPhoneNumber().equals(booking.getPhoneNumber());
        Predicate<Booking> showingEquals = s -> s.getShowing().equals(booking.getShowing());
        return getBookings().stream().anyMatch(phoneNumberEquals.and(showingEquals));
    }
    public List<Booking> getBookings() {return bookingRepo.findAll();}

    public Optional<Booking> findBookingById(long id) {
        return bookingRepo.findById(id);
    }

    public Optional<Booking> findBookingByName(String firstName, String lastName){
        return bookingRepo.findBookingByFirstNameAndLastName(firstName, lastName);
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

    // internal use only
    private void deleteBookingUnchecked(@NonNull Booking booking) {
        log.info("removing %s".formatted(booking));
        bookingRepo.delete(booking);
    }

    public void deleteBooking(@NonNull Booking booking) throws BookingException {
        if (!inDatabase(booking)) {
            throw newException("Booking not in Database!");
        }
        deleteBookingUnchecked(booking);
    }


    public Booking addBooking(@NonNull Booking booking) throws BookingException {
        if(inDatabase(booking)){
            throw newException("Booking already in database");
        }
        log.info("Adding %s to Database".formatted(booking));
        bookingRepo.save(booking);
        return booking;
    }


    public Booking updateBookingById(long id, @NonNull Booking newBooking) throws BookingException {
        var oldBooking = findBookingById(id);
        if (oldBooking.isEmpty()){
            throw newException("No such booking with id %d".formatted(id));
        }
        return updateBooking(oldBooking.get(), newBooking);
    }

}

