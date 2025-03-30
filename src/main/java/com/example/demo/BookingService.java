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

        System.out.println("📅 Booking dates received - Start: " + bookingRequest.getStartDate() + ", End: " + bookingRequest.getEndDate());

        // Create new Booking instance
        Booking booking = new Booking();
        booking.setCustomer(customerOpt.get());
        booking.setRoom(roomOpt.get());
        booking.setStartDate(bookingRequest.getStartDate());
        booking.setEndDate(bookingRequest.getEndDate());
        booking.setStatus(Booking.BookingStatus.confirmed);

        bookingRepository.save(booking);
        return true;
    }
}