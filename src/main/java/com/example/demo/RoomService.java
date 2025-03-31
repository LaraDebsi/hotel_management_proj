package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<AvailableRoomViewDTO> getAvailableRoomsView() {
        String sql = "SELECT hotel_city, available_rooms FROM hotel.available_rooms_per_city";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
            new AvailableRoomViewDTO(rs.getString("hotel_city"), rs.getInt("available_rooms")));
    }

    public List<HotelCapacityViewDTO> getHotelCapacityView() {
        String sql = "SELECT name, total_capacity FROM hotel.total_room_capacity_per_hotel";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
            new HotelCapacityViewDTO(rs.getString("name"), rs.getInt("total_capacity")));
    }
}
