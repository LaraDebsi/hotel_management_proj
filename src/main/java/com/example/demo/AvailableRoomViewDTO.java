package com.example.demo;

public class AvailableRoomViewDTO {
    private String city;
    private int availableRooms;

    public AvailableRoomViewDTO(String city, int availableRooms) {
        this.city = city;
        this.availableRooms = availableRooms;
    }

    public String getCity() {
        return city;
    }

    public int getAvailableRooms() {
        return availableRooms;
    }
}