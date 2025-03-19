package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HomeController {

    @Autowired
    private RoomService roomService;
    
    @Autowired
    private BookingService bookingService;

    @Autowired
    private RentingService rentingService;

    // ✅ 1. Welcome message
    @GetMapping("/")
    public String home() {
        return "Welcome to the Hotel Management System!";
    }

    // ✅ 2. Search Available Rooms
    @GetMapping("/rooms/search")
    public List<Room> searchRooms(@RequestParam(required = false) String area,
                                  @RequestParam(required = false) Integer capacity,
                                  @RequestParam(required = false) Double maxPrice,
                                  @RequestParam(required = false) String hotelChain) {
        return roomService.searchRooms(area, capacity, maxPrice, hotelChain);
    }

    // ✅ 3. Create a Booking
    @PostMapping("/booking/create")
    public ResponseEntity<String> createBooking(@RequestBody BookingRequest bookingRequest) {
        boolean success = bookingService.createBooking(bookingRequest);
        return success ? ResponseEntity.ok("Booking created!") : ResponseEntity.badRequest().body("Booking failed.");
    }

    // ✅ 4. Convert Booking to Renting
    @PostMapping("/renting/convert")
    public ResponseEntity<String> convertToRenting(@RequestParam Long bookingId, @RequestParam Long employeeId) {
        boolean success = rentingService.convertBookingToRenting(bookingId, employeeId);
        return success ? ResponseEntity.ok("Booking converted to renting.") : ResponseEntity.badRequest().body("Conversion failed.");
    }
}