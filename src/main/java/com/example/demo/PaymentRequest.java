package com.example.demo;

public class PaymentRequest {
    private Long rentingId;
    private Double amount;

    // Getters and Setters
    public Long getRentingId() { return rentingId; }
    public void setRentingId(Long rentingId) { this.rentingId = rentingId; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
}
