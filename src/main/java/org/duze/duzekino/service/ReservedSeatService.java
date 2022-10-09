package org.duze.duzekino.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.duze.duzekino.exception.BookingException;
import org.duze.duzekino.exception.ReservedSeatException;
import org.duze.duzekino.model.Booking;
import org.duze.duzekino.model.ReservedSeat;
import org.duze.duzekino.model.Showing;
import org.duze.duzekino.repository.BookingRepository;
import org.duze.duzekino.repository.ReservedSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
@Slf4j
public final class ReservedSeatService {

    final ReservedSeatRepository reservedSeatRepository;
    final BookingService bookingService;


    public ReservedSeatException newException(String message){
        log.error(message);
        return new ReservedSeatException(message);
    }

    public BookingException bookingException(String message){
        log.error(message);
        return new BookingException(message);
    }


    public boolean inDatabase(@NonNull ReservedSeat reservedSeat){
        Predicate<ReservedSeat> seatEquals = rs -> rs.getSeat().equals(reservedSeat.getSeat());
        Predicate<ReservedSeat> bookingEquals = rs -> rs.getBooking().equals(reservedSeat.getBooking());
        return getReservedSeats().stream().anyMatch(seatEquals.and(bookingEquals));
    }

    // All reserved seats for all showings for all movies (not usefull I guess)
    public List<ReservedSeat> getReservedSeats(){
        return reservedSeatRepository.findAll();
    }

    // gets reserved seat in booking
    public List<ReservedSeat> getReservedSeatsByBooking(@NonNull Booking booking){
        return reservedSeatRepository.findAllByBooking(booking);
    }

    public Optional<ReservedSeat> getReservedSeatById(long id){
        return reservedSeatRepository.findById(id);
    }

    public ReservedSeat addReservedSeat(@NonNull ReservedSeat reservedSeat){
        if(inDatabase(reservedSeat)){
            throw newException("Seat is already reserved");
        }
        log.info("Adding %s to Database".formatted(reservedSeat));
        reservedSeatRepository.save(reservedSeat);
        return reservedSeat;
    }

    // for internal use only
    private void deleteReservedSeatUnchecked(@NonNull ReservedSeat reservedSeat) {
        log.info("Removing %s".formatted(reservedSeat));
        reservedSeatRepository.delete(reservedSeat);
    }

    public void deleteReservedSeat(@NonNull ReservedSeat reservedSeat) throws ReservedSeatException {
        if(!inDatabase(reservedSeat)) {
            throw newException("Reserved seat not in database!");
        }
        deleteReservedSeatUnchecked(reservedSeat);
    }

    // deletes reserved seats by booking (could also use cascade)
    public void deleteReservedSeatsByBooking(@NonNull Booking booking) throws BookingException {
        if(!bookingService.inDatabase(booking)){
            throw bookingException("Booking not in database!");
        }
        reservedSeatRepository.deleteAllByBooking(booking);
    }

    // deletes reserved seat by id
    public void deleteReservedSeatById(long id) throws ReservedSeatException {
        if(getReservedSeatById(id).isEmpty()){
            throw newException("No such reserved seat");
        }
        reservedSeatRepository.deleteByReservedId(id);
    }

    public ReservedSeat updateReservedSeat(@NonNull ReservedSeat oldSeat, @NonNull ReservedSeat newSeat){
        var oldInfo = oldSeat.toString();
        oldSeat.setSeat(newSeat.getSeat());
        oldSeat.setBooking(newSeat.getBooking());
        reservedSeatRepository.save(oldSeat);
        log.info("Updated reserved seat from %s to %s".formatted(oldInfo, oldSeat));
        return oldSeat;
    }

    public ReservedSeat updateReservedSeatById(long id, @NonNull ReservedSeat newReservedSeat){
        var oldReservedSeat = getReservedSeatById(id);
        if(oldReservedSeat.isEmpty()){
            throw newException("No such reserved seat with id %d".formatted(id));
        }
        return updateReservedSeat(oldReservedSeat.get(), newReservedSeat);
    }










}
