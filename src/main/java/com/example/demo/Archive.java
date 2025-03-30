package com.example.demo;

import jakarta.persistence.*;

@Entity
@Table(name = "archive", schema = "hotel")
public class Archive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "archive_ID")
    private Long archiveId;

    @ManyToOne
    @JoinColumn(name = "booking_ID")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "renting_ID")
    private Renting renting;

    @ManyToOne
    @JoinColumn(name = "room_ID", nullable = false)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "hotel_ID", nullable = false)
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "customer_ID", nullable = false)
    private Customer customer;

    // Getters and Setters
    public Long getArchiveId() { return archiveId; }
    public void setArchiveId(Long archiveId) { this.archiveId = archiveId; }

    public Booking getBooking() { return booking; }
    public void setBooking(Booking booking) { this.booking = booking; }

    public Renting getRenting() { return renting; }
    public void setRenting(Renting renting) { this.renting = renting; }

    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }

    public Hotel getHotel() { return hotel; }
    public void setHotel(Hotel hotel) { this.hotel = hotel; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
}
