package com.example.demo;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "room_amenities", schema = "hotel")
public class RoomAmenities {

    @EmbeddedId
    private RoomAmenitiesKey id;

    @ManyToOne
    @JoinColumn(name = "room_ID", insertable = false, updatable = false, nullable = false)
    @JsonBackReference
    private Room room;

    public RoomAmenities() {}

    public RoomAmenities(RoomAmenitiesKey id, Room room) {
        this.id = id;
        this.room = room;
    }
    
    @Column(name = "amenity", insertable = false, updatable = false, nullable = false)
    private String amenity;

    // Getters and Setters
    public RoomAmenitiesKey getId() { return id; }
    public void setId(RoomAmenitiesKey id) { this.id = id; }

    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }

    public String getAmenity() { return amenity; }
    public void setAmenity(String amenity) { this.amenity = amenity; }
}
