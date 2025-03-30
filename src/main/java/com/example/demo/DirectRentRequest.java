package com.example.demo;

import java.time.LocalDate;

public class DirectRentRequest {
    public Long roomId;
    public String customerName;
    public String address;
    public String ssn;
    public LocalDate startDate;
    public LocalDate endDate;
    public Long employeeId;

    public Long getRoomId() {return roomId;}
    public void setRoomId(Long roomId) {this.roomId = roomId;}
    
    public String getCustomerName() {return customerName;}
    public void setCustomerName(String customerName) {this.customerName = customerName;}
    
    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}
    
    public String getSsn() {return ssn;}
    public void setSsn(String ssn) {this.ssn = ssn;}
    
    public LocalDate getStartDate() {return startDate;}
    public void setStartDate(LocalDate startDate) {this.startDate = startDate;}
    
    public LocalDate getEndDate() {return endDate;}
    public void setEndDate(LocalDate endDate) {this.endDate = endDate;}
    
    public Long getEmployeeId() {return employeeId;}
    public void setEmployeeId(Long employeeId) {this.employeeId = employeeId;}
    
}
