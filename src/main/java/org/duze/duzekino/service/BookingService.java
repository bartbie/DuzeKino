package org.duze.duzekino.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.duze.duzekino.model.Booking;
import org.duze.duzekino.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingService {

    final BookingRepository bookingRepository;

    public List<Booking> getALlBookings() {
        return bookingRepository.findAll();
    }

    public void save(Booking booking){
        bookingRepository.save(booking);
    }


    public void deleteBooking(Booking booking){
        bookingRepository.delete(booking);
    }

    public Optional<Booking> findByID(long id){
       return bookingRepository.findById(id);
    }


}
