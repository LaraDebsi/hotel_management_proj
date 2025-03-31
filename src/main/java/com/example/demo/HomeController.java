package com.example.demo;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.PostConstruct;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;



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

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RentingRepository rentingRepository;

    @Autowired
    private ArchiveRepository archiveRepository;



    @PostConstruct
    public void init() {
    System.out.println("HomeController Loaded. RoomService, BookingService, RentingService Injected!");
}

    // 1. Welcome message
    @GetMapping("/")
    public String home() {
        return "Welcome to the Hotel Management System!";
    }

    // 2. Search Available Rooms
    @GetMapping("/rooms/search")
    public List<Room> searchRooms(@RequestParam(required = false) String area,
                                  @RequestParam(required = false) Room.RoomCapacity capacity,
                                  @RequestParam(required = false) Double maxPrice,
                                  @RequestParam(required = false) String hotelChain,
                                  @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                  @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                  @RequestParam(required = false) Integer category) {
        System.out.println("🟣 Controller received capacity: " + capacity);
        return roomService.searchRooms(area, capacity, maxPrice, hotelChain, startDate, endDate, category);
    }

    @GetMapping("/hotel-chains")
    public List<String> getHotelChains() {
        return roomService.getAllHotelChains();
    }

    // 3. Create a Booking
    @PostMapping("/booking/create")
    public ResponseEntity<String> createBooking(@RequestBody BookingRequest bookingRequest) {
        try {
            boolean success = bookingService.createBooking(bookingRequest);
            return success ? ResponseEntity.ok("Booking created!") : ResponseEntity.badRequest().body("Booking failed.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    // 4. Convert Booking to Renting
    @PostMapping("/renting/convert")
    public ResponseEntity<String> convertToRenting(@RequestParam Long bookingId, @RequestParam Long employeeId) {
        boolean success = rentingService.convertBookingToRenting(bookingId, employeeId);
        return success ? ResponseEntity.ok("Booking converted to renting.") : ResponseEntity.badRequest().body("Conversion failed.");
    }

    @GetMapping("/bookings/upcoming")
    public List<BookingDTO> getUpcomingBookings() {
        LocalDate today = LocalDate.now();
        List<Booking> bookings = bookingRepository.findByStartDateGreaterThanEqual(today);
        return bookings.stream()
                       .map(BookingDTO::new)
                       .toList(); // or use Collectors.toList()
    }

    @PostMapping("/renting/direct")
    public ResponseEntity<String> directRent(@RequestBody DirectRentRequest request) {
        try {
            String result = rentingService.handleDirectRentWithReason(request);
            return result.equals("success")
                ? ResponseEntity.ok("Rental created successfully!")
                : ResponseEntity.badRequest().body("❌ " + result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Unexpected error: " + e.getMessage());
        }
    }

    @GetMapping("/rentings/active")
    public ResponseEntity<?> getRentings(@RequestParam(required = false) Boolean todayOnly) {
        try {
            List<Renting> rentings;
    
            if (Boolean.TRUE.equals(todayOnly)) {
                LocalDate today = LocalDate.now();
                rentings = rentingRepository.findActiveRentings(today);
            } else {
                rentings = rentingRepository.findAll();
            }
    
            System.out.println("✅ Returning rentings: " + rentings.size());
            return ResponseEntity.ok(
                rentings.stream().map(RentingDTO::new).toList()
            );
        } catch (Exception e) {
            System.err.println("❌ Rentings error: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body("Server error: " + e.getMessage());
        }
    }

    @DeleteMapping("/rentings/end/{id}")
    public ResponseEntity<String> endRental(@PathVariable Long id) {
        boolean success = rentingService.endRental(id);
        return success
            ? ResponseEntity.ok("Rental ended and room marked available.")
            : ResponseEntity.badRequest().body("Rental not found.");
    }

    @GetMapping("/archive/rentings")
    public List<ArchiveDTO> getArchivedRentals() {
        List<Archive> archived = archiveRepository.findAll();
        return archived.stream().map(ArchiveDTO::new).toList();
    }
    
    @GetMapping("/views/available-rooms")
    public List<AvailableRoomViewDTO> getAvailableRoomsPerCity() {
        return roomService.getAvailableRoomsView();
    }

    @GetMapping("/views/hotel-capacities")
    public List<HotelCapacityViewDTO> getHotelCapacitiesView() {
        return roomService.getHotelCapacityView();
    }

    @PostMapping("/rentings/payment")
    public ResponseEntity<String> addPaymentToRental(@RequestParam Long rentingId, @RequestParam Double amount) {
        Optional<Renting> rentingOpt = rentingRepository.findById(rentingId);
        if (rentingOpt.isEmpty()) return ResponseEntity.badRequest().body("Rental not found.");

        Renting renting = rentingOpt.get();
        renting.setPayment(amount);
        rentingRepository.save(renting);

        return ResponseEntity.ok("💸 Payment of $" + amount + " recorded for rental ID: " + rentingId);
    }


    

}