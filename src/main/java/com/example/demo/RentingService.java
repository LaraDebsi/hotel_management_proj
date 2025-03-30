package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    @Autowired  
    private ArchiveRepository archiveRepository;


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

    public boolean handleDirectRent(DirectRentRequest request) {
        Optional<Room> roomOpt = roomRepository.findById(request.getRoomId());
        Optional<Employee> empOpt = employeeRepository.findById(request.getEmployeeId());
    
        if (roomOpt.isEmpty() || empOpt.isEmpty()) return false;
    
        Room room = roomOpt.get();
        Employee emp = empOpt.get();
    
        // Check if room is available
        if (room.getAvailabilityStatus() == Room.AvailabilityStatus.rented) return false;
    
        // Check if customer exists by SSN
        Customer customer = customerRepository.findBySsnSin(request.getSsn())
            .orElseGet(() -> {
                Customer c = new Customer();
                c.setFull_name(request.getCustomerName());
                c.setAddress(request.getAddress());
                c.setSsnSin(request.getSsn());
                c.setRegistrationDate(java.time.LocalDate.now());
                return customerRepository.save(c);
            });
    
        // Create renting
        Renting renting = new Renting();
        renting.setRoom(room);
        renting.setCustomer(customer);
        renting.setEmployee(emp);
        renting.setStartDate(request.getStartDate());
        renting.setEndDate(request.getEndDate());
        renting.setPayment(room.getPrice());
    
        rentingRepository.save(renting);
    
        // Mark room as occupied
        room.setAvailabilityStatus(Room.AvailabilityStatus.rented);
        roomRepository.save(room);
    
        return true;
    }

    public String handleDirectRentWithReason(DirectRentRequest request) {
        System.out.println("🔎 Direct Rent Request:");
        System.out.println("Room ID: " + request.getRoomId());
        System.out.println("Employee ID: " + request.getEmployeeId());
        Optional<Room> roomOpt = roomRepository.findById(request.getRoomId());
        Optional<Employee> empOpt = employeeRepository.findById(request.getEmployeeId());

        if (roomOpt.isEmpty()) return "Room ID not found.";
        if (empOpt.isEmpty()) return "Employee ID not found.";

        Room room = roomOpt.get();
        if (room.getAvailabilityStatus() == Room.AvailabilityStatus.rented) {
            return "Room is already rented.";
        }

        // Check or create customer
        Customer customer = customerRepository.findBySsnSin(request.getSsn())
            .orElseGet(() -> {
                Customer c = new Customer();
                c.setFull_name(request.getCustomerName());
                c.setAddress(request.getAddress());
                c.setSsnSin(request.getSsn());
                c.setRegistrationDate(LocalDate.now());
                return customerRepository.save(c);
            });

        Renting renting = new Renting();
        renting.setRoom(room);
        renting.setCustomer(customer);
        renting.setEmployee(empOpt.get());
        renting.setStartDate(request.getStartDate());
        renting.setEndDate(request.getEndDate());
        renting.setPayment(room.getPrice());

        rentingRepository.save(renting);

        room.setAvailabilityStatus(Room.AvailabilityStatus.rented);
        roomRepository.save(room);

        return "success";
    }

    public boolean endRental(Long rentingId) {
        Optional<Renting> rentingOpt = rentingRepository.findById(rentingId);
    
        if (rentingOpt.isPresent()) {
            Renting renting = rentingOpt.get();
            Room room = renting.getRoom();
    
            // 1. Archive the rental
            Archive archive = new Archive();
            archive.setBooking(renting.getBooking()); // nullable
            archive.setRoom(room);
            archive.setHotel(room.getHotel());
            archive.setCustomer(renting.getCustomer());
    
            Archive savedArchive = archiveRepository.save(archive);
                        
            // 3. Mark room available again
            room.setAvailabilityStatus(Room.AvailabilityStatus.available);
            roomRepository.save(room);
    
            // 4. Delete the renting (if desired)
            rentingRepository.delete(renting);


    
            return true;
        }
    
        return false;
    }
    
    
    
}