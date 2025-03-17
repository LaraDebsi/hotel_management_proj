package com.example.demo;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "hotel", schema = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_ID")
    private Long hotelId;

    private String name;
    private Integer category;

    @Column(name = "num_rooms")
    private Integer numRooms;

    private String city;

    @Column(name = "contact_email", unique = true)
    private String contactEmail;

    @Column(name = "contact_phone")
    private String contactPhone;

    private String address;

    @ManyToOne
    @JoinColumn(name = "hotelchain_ID", nullable = false)
    private HotelChain hotelChain;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Room> rooms;

    // Getters and Setters
    public Long getHotelId() { return hotelId; }
    public void setHotelId(Long hotelId) { this.hotelId = hotelId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getCategory() { return category; }
    public void setCategory(Integer category) { this.category = category; }

    public Integer getNumRooms() { return numRooms; }
    public void setNumRooms(Integer numRooms) { this.numRooms = numRooms; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }

    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public HotelChain getHotelChain() { return hotelChain; }
    public void setHotelChain(HotelChain hotelChain) { this.hotelChain = hotelChain; }

    public List<Room> getRooms() { return rooms; }
    public void setRooms(List<Room> rooms) { this.rooms = rooms; }
}
