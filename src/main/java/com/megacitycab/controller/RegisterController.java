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
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    private UserService userService = new UserService();
    private CustomerService customerService = new CustomerService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String nic = request.getParameter("nic");
        String phone = request.getParameter("phone");

        // Create User object
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole("customer"); // Ensure this matches the ENUM values in the database

        // Create Customer object
        Customer customer = new Customer();
        customer.setName(name);
        customer.setAddress(address);
        customer.setNIC(nic);
        customer.setPhone(phone);

        try {
            // Save user to the database
            int userId = userService.addUser(user);

            // Save customer to the database
            customer.setUserId(userId); // Link customer to user
            customerService.addCustomer(customer);

            // Redirect to login page after successful registration
            response.sendRedirect("login.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("register.jsp?error=1");
        }
    }
}