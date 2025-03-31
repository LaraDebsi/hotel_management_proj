package com.example.demo;

public class HotelCapacityViewDTO {
    private String hotelName;
    private int totalCapacity;

    public HotelCapacityViewDTO(String hotelName, int totalCapacity) {
        this.hotelName = hotelName;
        this.totalCapacity = totalCapacity;
    }

    public String getHotelName() {
        return hotelName;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }
}