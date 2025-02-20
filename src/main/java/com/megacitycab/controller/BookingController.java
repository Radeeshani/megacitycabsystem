package com.megacitycab.controller;

import com.megacitycab.model.Booking;
import com.megacitycab.service.BookingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/bookings")
public class BookingController extends HttpServlet {
    private BookingService bookingService = new BookingService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Booking> bookings = bookingService.getAllBookings();
            request.setAttribute("bookings", bookings);
            request.getRequestDispatcher("/viewBookings.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving bookings");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        String pickupLocation = request.getParameter("pickupLocation");
        String destination = request.getParameter("destination");
        String contactNumber = request.getParameter("contactNumber");
        int assignedDriverId = Integer.parseInt(request.getParameter("assignedDriverId"));

        Booking booking = new Booking();
        booking.setCustomerId(customerId);
        booking.setPickupLocation(pickupLocation);
        booking.setDestination(destination);
        booking.setContactNumber(contactNumber);
        booking.setAssignedDriverId(assignedDriverId);

        try {
            bookingService.addBooking(booking);
            response.sendRedirect("bookings");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error adding booking");
        }
    }
}