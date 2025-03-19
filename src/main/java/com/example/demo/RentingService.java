package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class RentingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RentingRepository rentingRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public boolean convertBookingToRenting(Long bookingId, Long employeeId) {
        Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);
        Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);

        if (bookingOpt.isPresent() && employeeOpt.isPresent()) {
            Booking booking = bookingOpt.get();

            // Check if Customer exists
            Optional<Customer> customerOpt = customerRepository.findById(booking.getCustomer().getCustomerId());
            if (customerOpt.isEmpty()) return false;

            // Check if Room exists
            Optional<Room> roomOpt = roomRepository.findById(booking.getRoom().getRoomId());
            if (roomOpt.isEmpty()) return false;

            Renting renting = new Renting();
            renting.setCustomer(customerOpt.get());
            renting.setRoom(roomOpt.get());
            renting.setStartDate(booking.getStartDate());
            renting.setEndDate(booking.getEndDate());
            renting.setEmployee(employeeOpt.get());
            renting.setPayment(roomOpt.get().getPrice());
            
            rentingRepository.save(renting);
            bookingRepository.delete(booking);
            return true;
        }
        return false;
    }
}