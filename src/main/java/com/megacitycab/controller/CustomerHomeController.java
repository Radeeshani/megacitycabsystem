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

@WebServlet("/customerHome")
public class CustomerHomeController extends HttpServlet {
    private BookingService bookingService = new BookingService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer customerId = (Integer) session.getAttribute("customerId");

        if (customerId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            List<Booking> bookings = bookingService.getBookingsByCustomerId(customerId);
            request.setAttribute("bookings", bookings);
            request.getRequestDispatcher("/customerHome.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving bookings");
        }
    }
} 