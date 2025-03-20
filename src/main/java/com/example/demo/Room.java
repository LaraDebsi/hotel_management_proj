package com.example.demo;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
@Table(name = "room", schema = "hotel")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_ID")
    private Long roomId;

    @ManyToOne
    @JoinColumn(name = "hotel_ID", nullable = false)
    @JsonIgnore
    private Hotel hotel;

    @Column(name = "room_number", nullable = false, columnDefinition = "INT DEFAULT 1")
    private Integer roomNumber;

    @Column(name = "price", nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "capacity", nullable = false)
    private RoomCapacity capacity;

    @Column(name = "extendable", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean extendable;

    @Column(name = "damage", columnDefinition = "TEXT DEFAULT NULL")
    private String damage;

    @Enumerated(EnumType.STRING)
    @Column(name = "view_type", nullable = false)
    private ViewType viewType;

    @Enumerated(EnumType.STRING)
    @Column(name = "availability_status", nullable = false, columnDefinition = "VARCHAR(20) DEFAULT 'available'")
    private AvailabilityStatus availabilityStatus;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    @JsonManagedReference
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

    public Hotel getHotel() { return hotel; }
    public void setHotel(Hotel hotel) { this.hotel = hotel; }

    public Integer getRoomNumber() { return roomNumber; }
    public void setRoomNumber(Integer roomNumber) { this.roomNumber = roomNumber; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public RoomCapacity getCapacity() { return capacity; }
    public void setCapacity(RoomCapacity capacity) { this.capacity = capacity; }

    public Boolean getExtendable() { return extendable; }
    public void setExtendable(Boolean extendable) { this.extendable = extendable; }

    public String getDamage() { return damage; }
    public void setDamage(String damage) { this.damage = damage; }

    public ViewType getViewType() { return viewType; }
    public void setViewType(ViewType viewType) { this.viewType = viewType; }

    public AvailabilityStatus getAvailabilityStatus() { return availabilityStatus; }
    public void setAvailabilityStatus(AvailabilityStatus availabilityStatus) { this.availabilityStatus = availabilityStatus; }

    public List<RoomAmenities> getAmenities() { return amenities; }
    public void setAmenities(List<RoomAmenities> amenities) { this.amenities = amenities; }
}
