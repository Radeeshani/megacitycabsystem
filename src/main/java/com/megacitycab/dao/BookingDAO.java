package com.megacitycab.dao;

import com.megacitycab.model.Booking;
import com.megacitycab.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    // Add a new booking
	public void addBooking(Booking booking) throws SQLException {
	    String sql = "INSERT INTO bookings (customer_id, pickup_location, destination, contact_number, status) VALUES (?, ?, ?, ?, ?)";
	    try (Connection conn = DBUtil.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, booking.getCustomerId());
	        stmt.setString(2, booking.getPickupLocation());
	        stmt.setString(3, booking.getDestination());
	        stmt.setString(4, booking.getContactNumber());
	        stmt.setString(5, booking.getStatus());
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e; // Re-throw the exception for handling in the controller
	    }
	}

    // Get all bookings
    public List<Booking> getAllBookings() throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT b.*, u.username as customer_username " +
                    "FROM bookings b " +
                    "JOIN customers c ON b.customer_id = c.customer_id " +
                    "JOIN users u ON c.user_id = u.user_id";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("booking_id"));
                booking.setCustomerId(rs.getInt("customer_id"));
                booking.setPickupLocation(rs.getString("pickup_location"));
                booking.setDestination(rs.getString("destination"));
                booking.setContactNumber(rs.getString("contact_number"));
                booking.setBookingDate(rs.getTimestamp("booking_date"));
                booking.setAssignedDriverId(rs.getInt("assigned_driver_id"));
                booking.setStatus(rs.getString("status"));
                booking.setCustomerUsername(rs.getString("customer_username"));
                bookings.add(booking);
            }
        }
        return bookings;
    }

    // Get a booking by ID
    public Booking getBookingById(int bookingId) throws SQLException {
        String sql = "SELECT * FROM bookings WHERE booking_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookingId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Booking booking = new Booking();
                    booking.setBookingId(rs.getInt("booking_id"));
                    booking.setCustomerId(rs.getInt("customer_id"));
                    booking.setPickupLocation(rs.getString("pickup_location"));
                    booking.setDestination(rs.getString("destination"));
                    booking.setContactNumber(rs.getString("contact_number"));
                    booking.setBookingDate(rs.getTimestamp("booking_date"));
                    booking.setAssignedDriverId(rs.getInt("assigned_driver_id"));
                    booking.setStatus(rs.getString("status"));
                    return booking;
                }
            }
        }
        return null;
    }

    // Update a booking
    public void updateBooking(Booking booking) throws SQLException {
        String sql = "UPDATE bookings SET customer_id = ?, pickup_location = ?, destination = ?, contact_number = ?, assigned_driver_id = ?, status = ? WHERE booking_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, booking.getCustomerId());
            stmt.setString(2, booking.getPickupLocation());
            stmt.setString(3, booking.getDestination());
            stmt.setString(4, booking.getContactNumber());
            stmt.setInt(5, booking.getAssignedDriverId());
            stmt.setString(6, booking.getStatus());
            stmt.setInt(7, booking.getBookingId());
            stmt.executeUpdate();
        }
    }

    // Delete a booking
    public void deleteBooking(int bookingId) throws SQLException {
        String sql = "DELETE FROM bookings WHERE booking_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookingId);
            stmt.executeUpdate();
        }
    }
    
    public List<Booking> getBookingsByCustomerId(int customerId) throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE customer_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Booking booking = new Booking();
                    booking.setBookingId(rs.getInt("booking_id"));
                    booking.setCustomerId(rs.getInt("customer_id"));
                    booking.setPickupLocation(rs.getString("pickup_location"));
                    booking.setDestination(rs.getString("destination"));
                    booking.setContactNumber(rs.getString("contact_number"));
                    booking.setBookingDate(rs.getTimestamp("booking_date"));
                    booking.setStatus(rs.getString("status"));
                    bookings.add(booking);
                }
            }
        }
        return bookings;
    }
    
    public void updateBookingStatus(int bookingId, String status) throws SQLException {
        String sql = "UPDATE bookings SET status = ? WHERE booking_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, bookingId);
            stmt.executeUpdate();
        }
    }
}