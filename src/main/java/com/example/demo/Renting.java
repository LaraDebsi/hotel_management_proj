package com.example.demo;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Renting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;
    private Long roomId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double payment;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false) // Foreign key reference
    private Employee employee;


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

    public Employee getEmployee() {return employee; }
    public void setEmployee(Employee employee) { this.employee = employee;}

    public Double getPayment() { return payment; }
    public void setPayment(Double payment) {this.payment = payment; }
}
