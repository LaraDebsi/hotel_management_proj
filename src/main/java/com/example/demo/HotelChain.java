package com.example.demo;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "hotel_chain", schema = "hotel")
public class HotelChain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotelchain_ID")
    private Long hotelchainId;

    private String name;

    @Column(name = "co_address")
    private String coAddress;

    @Column(name = "num_hotels")
    private Integer numHotels;

    @Column(name = "contact_email", unique = true)
    private String contactEmail;

    @Column(name = "contact_phone")
    private String contactPhone;

    @OneToMany(mappedBy = "hotelChain", cascade = CascadeType.ALL)
    private List<Hotel> hotels;

    // Getters and Setters
    public Long getHotelchainId() { return hotelchainId; }
    public void setHotelchainId(Long hotelchainId) { this.hotelchainId = hotelchainId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCoAddress() { return coAddress; }
    public void setCoAddress(String coAddress) { this.coAddress = coAddress; }

    public Integer getNumHotels() { return numHotels; }
    public void setNumHotels(Integer numHotels) { this.numHotels = numHotels; }

    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }

    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }

    public List<Hotel> getHotels() { return hotels; }
    public void setHotels(List<Hotel> hotels) { this.hotels = hotels; }
}