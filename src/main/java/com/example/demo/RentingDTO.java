package com.example.demo;
import java.time.LocalDate;

public class RentingDTO {
    public Long rentingId;
    public String customerName;
    public int roomNumber;
    public String hotelName;
    public LocalDate startDate;
    public LocalDate endDate;
    public Double payment;

    public RentingDTO(Renting renting) {
        this.rentingId = renting.getId();
        this.customerName = renting.getCustomer().getFull_name();
        this.roomNumber = renting.getRoom().getRoomNumber();
        this.hotelName = renting.getRoom().getHotel().getName();
        this.startDate = renting.getStartDate();
        this.endDate = renting.getEndDate();
        this.payment = renting.getPayment();
    }

    public RentingDTO(Archive archive) {
        this.rentingId = archive.getArchiveId(); 
        this.customerName = archive.getCustomer().getFull_name();
        this.roomNumber = archive.getRoom().getRoomNumber();
        this.hotelName = archive.getHotel().getName();
    
        Renting renting = archive.getRenting();
        if (renting != null) {
            this.startDate = renting.getStartDate();
            this.endDate = renting.getEndDate();
            this.payment = renting.getPayment();
        }
    }
}