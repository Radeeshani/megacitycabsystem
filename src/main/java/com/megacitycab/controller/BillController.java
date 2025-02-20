package com.megacitycab.controller;

import com.megacitycab.model.Bill;
import com.megacitycab.service.BillService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/bills")
public class BillController extends HttpServlet {
    private BillService billService = new BillService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Bill> bills = billService.getAllBills();
            request.setAttribute("bills", bills);
            request.getRequestDispatcher("/viewBills.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving bills");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookingId = Integer.parseInt(request.getParameter("bookingId"));
        double totalAmount = Double.parseDouble(request.getParameter("totalAmount"));
        double tax = Double.parseDouble(request.getParameter("tax"));
        double discount = Double.parseDouble(request.getParameter("discount"));

        Bill bill = new Bill();
        bill.setBookingId(bookingId);
        bill.setTotalAmount(totalAmount);
        bill.setTax(tax);
        bill.setDiscount(discount);

        try {
            billService.addBill(bill);
            response.sendRedirect("bills");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error adding bill");
        }
    }
}