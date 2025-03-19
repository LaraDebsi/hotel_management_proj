package com.example.demo;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking", schema = "hotel")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_ID")
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "customer_ID", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "room_ID", nullable = false)
    private Room room;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @Column(name = "extendable", nullable = false)
    private Boolean extendable;

    @Column(name = "payment", nullable = false)
    private Double payment;

    @Column(name = "booking_timestamp", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime bookingTimestamp;

    // Enum for Booking Status
    public enum BookingStatus { CONFIRMED, CANCELLED, CHECKED_IN }

    // Getters and Setters
    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public BookingStatus getStatus() { return status; }
    public void setStatus(BookingStatus status) { this.status = status; }

    public Double getPayment() { return payment; }
    public void setPayment(Double payment) { this.payment = payment; }

    public Boolean getExtendable() {return extendable;}
    public void setExtendable(Boolean extendable) {this.extendable = extendable; }

    public LocalDateTime getBookingTimestamp() { return bookingTimestamp; }
}