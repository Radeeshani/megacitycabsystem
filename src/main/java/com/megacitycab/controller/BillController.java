package com.megacitycab.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.megacitycab.model.Bill;
import com.megacitycab.model.Booking;
import com.megacitycab.service.BillService;
import com.megacitycab.service.BookingService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/bills")
public class BillController extends HttpServlet {
    private BillService billService = new BillService();
    private BookingService bookingService = new BookingService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get all bills
            List<Bill> bills = billService.getAllBills();
            request.setAttribute("bills", bills);

            // Get all bookings and filter for completed ones
            List<Booking> allBookings = bookingService.getAllBookings();
            List<Booking> completedBookings = allBookings.stream()
                .filter(booking -> "Completed".equals(booking.getStatus()))
                .collect(Collectors.toList());
            request.setAttribute("bookings", completedBookings);

            request.getRequestDispatcher("/viewBills.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving bills and bookings");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookingId = Integer.parseInt(request.getParameter("bookingId"));
        double kilometersTraveled = Double.parseDouble(request.getParameter("kilometersTraveled"));
        double tax = Double.parseDouble(request.getParameter("tax"));
        double discount = Double.parseDouble(request.getParameter("discount"));

        // Calculate total amount
        double ratePerKilometer = 10; // Base rate per kilometer
        double totalAmount = kilometersTraveled * ratePerKilometer;
        totalAmount += (totalAmount * tax) / 100; // Add tax
        totalAmount -= (totalAmount * discount) / 100; // Subtract discount

        Bill bill = new Bill();
        bill.setBookingId(bookingId);
        bill.setKilometersTraveled(kilometersTraveled);
        bill.setTax(tax);
        bill.setDiscount(discount);
        bill.setTotalAmount(totalAmount);

        try {
            billService.addBill(bill);
            response.sendRedirect("bills");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error adding bill");
        }
    }
}