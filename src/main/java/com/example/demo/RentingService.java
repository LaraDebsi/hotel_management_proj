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
    System.out.println("🔁 Starting booking-to-renting conversion...");
    System.out.println("📌 Booking ID: " + bookingId + ", Employee ID: " + employeeId);

    Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);
    if (bookingOpt.isEmpty()) {
        System.out.println("❌ Booking not found.");
        return false;
    }

    Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
    if (employeeOpt.isEmpty()) {
        System.out.println("❌ Employee not found.");
        return false;
    }

    Booking booking = bookingOpt.get();

    Optional<Customer> customerOpt = customerRepository.findById(booking.getCustomer().getCustomerId());
    if (customerOpt.isEmpty()) {
        System.out.println("❌ Customer not found.");
        return false;
    }

    Optional<Room> roomOpt = roomRepository.findById(booking.getRoom().getRoomId());
    if (roomOpt.isEmpty()) {
        System.out.println("❌ Room not found.");
        return false;
    }

    System.out.println("✅ All references loaded, proceeding to convert...");

    Renting renting = new Renting();
    renting.setCustomer(customerOpt.get());
    renting.setRoom(roomOpt.get());
    renting.setStartDate(booking.getStartDate());
    renting.setEndDate(booking.getEndDate());
    renting.setEmployee(employeeOpt.get());
    renting.setPayment(roomOpt.get().getPrice());

    rentingRepository.save(renting);
    System.out.println("💾 Renting saved.");

    // Update room status
    roomOpt.get().setAvailabilityStatus(Room.AvailabilityStatus.rented);
    roomRepository.save(roomOpt.get());
    System.out.println("🏨 Room marked as rented.");

    bookingRepository.delete(booking);
    System.out.println("🗑️ Booking deleted.");

    return true;
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

        roomOpt.get().setAvailabilityStatus(Room.AvailabilityStatus.rented);
        roomRepository.save(roomOpt.get());

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

            archive.setRenting(renting);
    
            Archive savedArchive = archiveRepository.save(archive);
            System.out.println("🗂️ Archive saved: " + savedArchive.getArchiveId());
            System.out.println("📆 Renting start: " + renting.getStartDate());
                        
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