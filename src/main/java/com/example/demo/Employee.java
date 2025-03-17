package com.example.demo;

import jakarta.persistence.*;

@Entity
@Table(name = "employee", schema = "hotel")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_ID")
    private Long employeeId;

    private String full_name;
    private String address;

    @Column(name = "SSN_SIN", unique = true)
    private String ssnSin;

    @ManyToOne
    @JoinColumn(name = "hotel_ID", nullable = false)
    private Hotel hotel;

    // Getters and Setters
    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public String getFull_name() { return full_name; }
    public void setFull_name(String full_name) { this.full_name = full_name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getSsnSin() { return ssnSin; }
    public void setSsnSin(String ssnSin) { this.ssnSin = ssnSin; }

    public Hotel getHotel() { return hotel; }
    public void setHotel(Hotel hotel) { this.hotel = hotel; }
}
