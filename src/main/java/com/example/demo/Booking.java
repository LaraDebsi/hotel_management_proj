package com.example.demo;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "booking", schema = "hotel")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_ID")
    private Long id;
    private String status;

    @ManyToOne
    @JoinColumn(name = "customer_ID", nullable = false)
    private Long customerId;

    @ManyToOne
    @JoinColumn(name = "room_ID", nullable = false)
    private Long roomId;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public Long getRoomId() { return roomId; }
    public void setRoomId(Long roomId) { this.roomId = roomId; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status; }

    
}
