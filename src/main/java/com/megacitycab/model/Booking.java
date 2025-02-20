package com.megacitycab.model;

import java.sql.Timestamp;

public class Booking {
    private int bookingId;
    private int customerId; // Foreign key to Customer
    private String pickupLocation;
    private String destination;
    private String contactNumber;
    private Timestamp bookingDate;
    private int assignedDriverId; // Foreign key to Driver
    private String status; // Pending, Completed, Cancelled

    // Getters and Setters
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Timestamp getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Timestamp bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getAssignedDriverId() {
        return assignedDriverId;
    }

    public void setAssignedDriverId(int assignedDriverId) {
        this.assignedDriverId = assignedDriverId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}