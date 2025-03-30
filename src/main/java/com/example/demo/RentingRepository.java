package com.example.demo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RentingRepository extends JpaRepository<Renting, Long> { 
    @Query("SELECT r FROM Renting r WHERE :today BETWEEN r.startDate AND r.endDate")
    List<Renting> findActiveRentings(@Param("today") LocalDate today);
}
