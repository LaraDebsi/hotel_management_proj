package com.example.demo;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RoomAmenitiesKey implements Serializable {

    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "amenity")
    private String amenity;

    // Default Constructor (Required by Hibernate)
    public RoomAmenitiesKey() {}

    public RoomAmenitiesKey(Long roomId, String amenity) {
        this.roomId = roomId;
        this.amenity = amenity;
    }

    // Getters and Setters
    public Long getRoomId() { return roomId; }
    public void setRoomId(Long roomId) { this.roomId = roomId; }

    public String getAmenity() { return amenity; }
    public void setAmenity(String amenity) { this.amenity = amenity; }

    // Override equals() and hashCode() for proper key comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomAmenitiesKey that = (RoomAmenitiesKey) o;
        return Objects.equals(roomId, that.roomId) &&
               Objects.equals(amenity, that.amenity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, amenity);
    }
}
