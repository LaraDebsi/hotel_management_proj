package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDate;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

        @Query("SELECT r FROM Room r " +
              "JOIN FETCH r.hotel h " + // <--- this ensures hotel is loaded
              "WHERE (:area IS NULL OR h.city LIKE %:area%) " +
              "AND (:capacity IS NULL OR r.capacity = :capacity) " +
              "AND (:maxPrice IS NULL OR r.price <= :maxPrice) " +
              "AND (:hotelChain IS NULL OR h.hotelChain.name LIKE %:hotelChain%)" +
              "AND (:category IS NULL OR h.category = :category)")
       List<Room> findRooms(@Param("area") String area,
                            @Param("capacity") Room.RoomCapacity capacity,
                            @Param("maxPrice") Double maxPrice,
                            @Param("hotelChain") String hotelChain,
                            @Param("startDate") LocalDate startDate,
                            @Param("endDate") LocalDate endDate,
                            @Param("category") Integer category);
       @Query("SELECT DISTINCT h.hotelChain.name FROM Room r JOIN r.hotel h WHERE h.hotelChain.name IS NOT NULL")
       List<String> findAllHotelChainNames();          
}
