package com.megacitycab.service;

import com.megacitycab.dao.BookingDAO;
import com.megacitycab.model.Booking;
import java.sql.SQLException;
import java.util.List;

public class BookingService {

    private BookingDAO bookingDAO = new BookingDAO();

    // Add a new booking
    public void addBooking(Booking booking) throws SQLException {
        bookingDAO.addBooking(booking);
    }

    // Get all bookings
    public List<Booking> getAllBookings() throws SQLException {
        return bookingDAO.getAllBookings();
    }

    // Get a booking by ID
    public Booking getBookingById(int bookingId) throws SQLException {
        return bookingDAO.getBookingById(bookingId);
    }

    // Update a booking
    public void updateBooking(Booking booking) throws SQLException {
        bookingDAO.updateBooking(booking);
    }

    // Delete a booking
    public void deleteBooking(int bookingId) throws SQLException {
        bookingDAO.deleteBooking(bookingId);
    }
}