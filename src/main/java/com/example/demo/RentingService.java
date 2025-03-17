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

    public boolean convertBookingToRenting(Long bookingId, Long employeeId) {
        Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);
        Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);

        if (bookingOpt.isPresent() && employeeOpt.isPresent()) {
            Booking booking = bookingOpt.get();
            Renting renting = new Renting();
            renting.setCustomerId(booking.getCustomerId());
            renting.setRoomId(booking.getRoomId());
            renting.setStartDate(booking.getStartDate());
            renting.setEndDate(booking.getEndDate());
            renting.setEmployee(employeeOpt.get());
            Optional<Room> roomOpt = roomRepository.findById(booking.getRoomId());
            if (roomOpt.isPresent()) {
                renting.setPayment(roomOpt.get().getPrice()); 
            } else {
                return false; 
            }
            rentingRepository.save(renting);
            bookingRepository.delete(booking);
            return true;
        }
        return false;
    }
}
