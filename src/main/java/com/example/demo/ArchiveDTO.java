package com.example.demo;
public class ArchiveDTO {
    public Long archiveId;
    public Long rentingId;
    public Long bookingId;
    public String customerName;
    public String hotelName;
    public int roomNumber;
    public String startDate;
    public String endDate;
    public Double payment;

    public ArchiveDTO(Archive archive) {
        this.archiveId = archive.getArchiveId();
        //this.rentingId = archive.getRenting() != null ? archive.getRenting().getId() : null;
        this.bookingId = archive.getBooking() != null ? archive.getBooking().getBookingId() : null;
        this.customerName = archive.getCustomer().getFull_name();
        this.hotelName = archive.getHotel().getName();
        this.roomNumber = archive.getRoom().getRoomNumber();
        this.startDate = archive.getStartDate() != null ? archive.getStartDate().toString() : null;
        this.endDate = archive.getEndDate() != null ? archive.getEndDate().toString() : null;
        this.payment = archive.getPayment();
    }
}
