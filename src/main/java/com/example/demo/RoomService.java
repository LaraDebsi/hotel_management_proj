package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> searchRooms(String area, Room.RoomCapacity capacity, Double maxPrice, String hotelChain, LocalDate startDate, LocalDate endDate, Integer category) {
        System.out.println("🧪 Service got dates: " + startDate + " - " + endDate);
        return roomRepository.findRooms(area, capacity, maxPrice, hotelChain, startDate, endDate, category);
    }
    public List<String> getAllHotelChains() {
        return roomRepository.findAllHotelChainNames();
    }
}
