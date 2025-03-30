package com.example.demo;

public class BookingDTO {
    public Long bookingId;
    public String customerName;
    public Long roomId;
    public String startDate;
    public String endDate;

    public BookingDTO(Booking booking) {
        this.bookingId = booking.getBookingId();
        this.customerName = booking.getCustomer().getFull_name();
        this.roomId = booking.getRoom().getRoomId();
        this.startDate = booking.getStartDate().toString();
        this.endDate = booking.getEndDate().toString();
    }
}
