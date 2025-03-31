package com.example.demo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> { 
    List<Booking> findByStartDateGreaterThanEqual(LocalDate date);
    List<Booking> findByCustomer_CustomerId(Long customerId);
}
