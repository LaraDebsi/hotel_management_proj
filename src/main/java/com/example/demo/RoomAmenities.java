package com.example.demo;

import jakarta.persistence.*;

@Entity
@Table(name = "room_amenities", schema = "hotel")
public class RoomAmenities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_ID", nullable = false)
    private Room room;

    @Column(name = "amenity", nullable = false)
    private String amenity;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }

    public String getAmenity() { return amenity; }
    public void setAmenity(String amenity) { this.amenity = amenity; }
}
