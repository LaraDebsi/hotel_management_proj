package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public boolean createBooking(BookingRequest bookingRequest) {
        Booking booking = new Booking();
        booking.setCustomerId(bookingRequest.getCustomerId());
        booking.setRoomId(bookingRequest.getRoomId());
        booking.setStartDate(bookingRequest.getStartDate());
        booking.setEndDate(bookingRequest.getEndDate());
        booking.setStatus("confirmed");
        bookingRepository.save(booking);
        return true;
    }
}
