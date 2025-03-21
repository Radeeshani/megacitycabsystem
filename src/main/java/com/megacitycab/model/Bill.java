package com.megacitycab.model;

public class Bill {
    private int billId;
    private int bookingId; // Foreign key to Booking
    private double totalAmount;
    private double tax;
    private double discount;
    private double kilometersTraveled; // New field

    // Getters and Setters
    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getKilometersTraveled() {
        return kilometersTraveled;
    }

    public void setKilometersTraveled(double kilometersTraveled) {
        this.kilometersTraveled = kilometersTraveled;
    }
}