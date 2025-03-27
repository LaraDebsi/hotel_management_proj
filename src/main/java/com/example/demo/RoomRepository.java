package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

   /*  @Query("SELECT r FROM Room r WHERE " +
           "(:area IS NULL OR r.hotel.city LIKE %:area%) " +
           "AND (:capacity IS NULL OR r.capacity = :capacity) " +
           "AND (:maxPrice IS NULL OR r.price <= :maxPrice) " +
           "AND (:hotelChain IS NULL OR r.hotel.hotelChain.name LIKE %:hotelChain%)")
    List<Room> findRooms(@Param("area") String area,
                         @Param("capacity") Integer capacity,
                         @Param("maxPrice") Double maxPrice,
                         @Param("hotelChain") String hotelChain);*/
        @Query("SELECT r FROM Room r " +
              "JOIN FETCH r.hotel h " + // <--- this ensures hotel is loaded
              "WHERE (:area IS NULL OR h.city LIKE %:area%) " +
              "AND (:capacity IS NULL OR r.capacity = :capacity) " +
              "AND (:maxPrice IS NULL OR r.price <= :maxPrice) " +
              "AND (:hotelChain IS NULL OR h.hotelChain.name LIKE %:hotelChain%)")
       List<Room> findRooms(@Param("area") String area,
                            @Param("capacity") Integer capacity,
                            @Param("maxPrice") Double maxPrice,
                            @Param("hotelChain") String hotelChain);
                  
}
