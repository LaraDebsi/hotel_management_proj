package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoomRepository roomRepository;

    public boolean createBooking(BookingRequest bookingRequest) {
        // Validate if Customer and Room exist
        Optional<Customer> customerOpt = customerRepository.findById(bookingRequest.getCustomerId());
        Optional<Room> roomOpt = roomRepository.findById(bookingRequest.getRoomId());

        if (customerOpt.isEmpty() || roomOpt.isEmpty()) {
            throw new IllegalArgumentException("Customer or Room not found");
        }

        // Validate start date is before end date
        if (bookingRequest.getStartDate().isAfter(bookingRequest.getEndDate())) {
            throw new IllegalArgumentException("Start date must be before end date");
        }

        // Create new Booking instance
        Booking booking = new Booking();
        booking.setCustomer(customerOpt.get());
        booking.setRoom(roomOpt.get());
        booking.setStartDate(bookingRequest.getStartDate());
        booking.setEndDate(bookingRequest.getEndDate());
        booking.setStatus(Booking.BookingStatus.CONFIRMED);

        bookingRepository.save(booking);
        return true;
    }
}