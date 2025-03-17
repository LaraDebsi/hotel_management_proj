package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> searchRooms(String area, Integer capacity, Double maxPrice, String hotelChain) {
        return roomRepository.findRooms(area, capacity, maxPrice, hotelChain);
    }
}
