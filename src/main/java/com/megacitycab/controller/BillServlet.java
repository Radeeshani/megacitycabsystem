package com.megacitycab.controller;

import com.megacitycab.model.Bill;
import com.megacitycab.util.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("serial")
@WebServlet("/BillServlet")
public class BillServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookingId = Integer.parseInt(request.getParameter("bookingId"));

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "SELECT * FROM bookings WHERE booking_id = ?")) {
            stmt.setInt(1, bookingId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Bill bill = new Bill();
                bill.setBookingId(bookingId);
                bill.setTotalAmount(rs.getDouble("total_amount"));
                bill.setTax(rs.getDouble("tax"));
                bill.setDiscount(rs.getDouble("discount"));

                // Set bill in request
                request.setAttribute("bill", bill);
                request.getRequestDispatcher("calculateBill.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Booking not found.");
                request.getRequestDispatcher("calculateBill.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error. Please try again.");
            request.getRequestDispatcher("calculateBill.jsp").forward(request, response);
        }
    }
}