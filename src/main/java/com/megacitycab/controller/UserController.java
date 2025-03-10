package com.megacitycab.controller;

import com.megacitycab.model.Customer;
import com.megacitycab.model.User;
import com.megacitycab.service.CustomerService;
import com.megacitycab.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class UserController extends HttpServlet {
    private UserService userService = new UserService();
    private CustomerService customerService = new CustomerService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            User user = userService.authenticate(username, password);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                // Fetch customerId if the user is a customer
                if (user.getRole().equals("customer")) {
                    Customer customer = customerService.getCustomerByUserId(user.getUserId());
                    if (customer != null) {
                        session.setAttribute("customerId", customer.getCustomerId());
                        // Redirect to CustomerHomeController to fetch bookings
                        response.sendRedirect("customerHome");
                        return;
                    } else {
                        response.sendRedirect("login.jsp?error=1");
                        return;
                    }
                }

                // Redirect based on role
                if (user.getRole().equals("admin") || user.getRole().equals("staff")) {
                    response.sendRedirect("dashboard.jsp");
                } else {
                    response.sendRedirect("login.jsp?error=1");
                }
            } else {
                response.sendRedirect("login.jsp?error=1");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error during login");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("login.jsp");
    }
}