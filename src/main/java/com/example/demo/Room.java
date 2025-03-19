package com.example.demo;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "room", schema = "hotel")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_ID")
    private Long roomId;

    @Column(name = "price", nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "capacity", nullable = false)
    private RoomCapacity capacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "view_type", nullable = false)
    private ViewType viewType;

    @Enumerated(EnumType.STRING)
    @Column(name = "availability_status", nullable = false)
    private AvailabilityStatus availabilityStatus;

    @ManyToOne
    @JoinColumn(name = "hotel_ID", nullable = false)
    private Hotel hotel;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomAmenities> amenities;

    // Enum for view type
    public enum ViewType { SEA, MOUNTAIN, NONE }

    // Enum for availability status
    public enum AvailabilityStatus { available, booked, rented, under_maintenance }

    public enum RoomCapacity {
        SINGLE,  
        DOUBLE, 
        SUITE;
    }

    // Getters and Setters
    public Long getRoomId() { return roomId; }
    public void setRoomId(Long roomId) { this.roomId = roomId; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public RoomCapacity getCapacity() { return capacity; }
    public void setCapacity(RoomCapacity capacity) { this.capacity = capacity; }

    public ViewType getViewType() { return viewType; }
    public void setViewType(ViewType viewType) { this.viewType = viewType; }

    public AvailabilityStatus getAvailabilityStatus() { return availabilityStatus; }
    public void setAvailabilityStatus(AvailabilityStatus availabilityStatus) { this.availabilityStatus = availabilityStatus; }

    public Hotel getHotel() { return hotel; }
    public void setHotel(Hotel hotel) { this.hotel = hotel; }

    public List<RoomAmenities> getAmenities() { return amenities; }
    public void setAmenities(List<RoomAmenities> amenities) { this.amenities = amenities; }
}
