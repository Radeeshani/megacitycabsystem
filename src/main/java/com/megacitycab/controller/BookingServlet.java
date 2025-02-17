package com.megacitycab.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.megacitycab.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        String pickupLocation = request.getParameter("pickupLocation");
        String destination = request.getParameter("destination");
        String contactNumber = request.getParameter("contactNumber");

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "INSERT INTO bookings (customer_id, pickup_location, destination, contact_number) VALUES (?, ?, ?, ?)")) {
            stmt.setInt(1, customerId);
            stmt.setString(2, pickupLocation);
            stmt.setString(3, destination);
            stmt.setString(4, contactNumber);
            stmt.executeUpdate();

            // Redirect to view bookings page
            response.sendRedirect("viewBookings.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error. Please try again.");
            request.getRequestDispatcher("bookCab.jsp").forward(request, response);
        }
    }
}
