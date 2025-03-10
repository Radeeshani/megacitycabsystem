package com.megacitycab.controller;

import com.megacitycab.model.Booking;
import com.megacitycab.service.BookingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/bookings")
public class BookingController extends HttpServlet {
    private BookingService bookingService = new BookingService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null && action.equals("myBookings")) {
            // Handle fetching bookings for the logged-in customer
            int customerId = (int) request.getSession().getAttribute("customerId");
            try {
                List<Booking> bookings = bookingService.getBookingsByCustomerId(customerId);
                request.setAttribute("bookings", bookings);
                request.getRequestDispatcher("/customerHome.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving bookings");
            }
        } else {
            // Handle other GET requests (e.g., view all bookings for admin)
            try {
                List<Booking> bookings = bookingService.getAllBookings();
                request.setAttribute("bookings", bookings);
                request.getRequestDispatcher("/viewBookings.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving bookings");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            int bookingId = Integer.parseInt(request.getParameter("bookingId"));

            try {
                if (action.equals("complete")) {
                    bookingService.updateBookingStatus(bookingId, "Completed");
                    response.sendRedirect("bookings"); // Admin view
                } else if (action.equals("cancel")) {
                    bookingService.updateBookingStatus(bookingId, "Cancelled");
                    // Check if request is from customer or admin
                    if (request.getSession().getAttribute("customerId") != null) {
                        response.sendRedirect("customerHome"); // Customer view
                    } else {
                        response.sendRedirect("bookings"); // Admin view
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error updating booking status");
            }
        } else {
            // Handle add booking request
            HttpSession session = request.getSession();
            Integer customerId = (Integer) session.getAttribute("customerId");

            if (customerId == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Customer ID not found in session");
                return;
            }

            String pickupLocation = request.getParameter("pickupLocation");
            String destination = request.getParameter("destination");
            String contactNumber = request.getParameter("contactNumber");

            Booking booking = new Booking();
            booking.setCustomerId(customerId);
            booking.setPickupLocation(pickupLocation);
            booking.setDestination(destination);
            booking.setContactNumber(contactNumber);
            booking.setStatus("Pending"); // Default status

            try {
                bookingService.addBooking(booking);
                response.sendRedirect("customerHome"); // Redirect to customer home
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error adding booking");
            }
        }
    }
}