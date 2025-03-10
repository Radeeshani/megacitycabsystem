package com.megacitycab.controller;
import com.megacitycab.service.UserService;
import com.megacitycab.model.User; 

import com.megacitycab.model.Customer;
import com.megacitycab.service.CustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/customers")
public class CustomerController extends HttpServlet {
	private UserService userService = new UserService();
    private CustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "edit":
                    // Handle edit customer request
                    int editCustomerId = Integer.parseInt(request.getParameter("id"));
                    try {
                        Customer customer = customerService.getCustomerById(editCustomerId);
                        if (customer != null) {
                            // Fetch the associated user details
                            User user = userService.getUserById(customer.getUserId());
                            request.setAttribute("customer", customer); // Set the customer object
                            request.setAttribute("user", user); // Set the user object
                            request.getRequestDispatcher("/editCustomer.jsp").forward(request, response);
                        } else {
                            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Customer not found");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving customer details");
                    }
                    break;

                case "delete":
                    // Handle delete customer request
                    int deleteCustomerId = Integer.parseInt(request.getParameter("id"));
                    try {
                        customerService.deleteCustomer(deleteCustomerId);
                        response.sendRedirect("customers"); // Redirect to the customer list page after deletion
                    } catch (SQLException e) {
                        e.printStackTrace();
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting customer");
                    }
                    break;

                default:
                    // Handle other GET requests (e.g., view customers)
                    try {
                        request.setAttribute("customers", customerService.getAllCustomers());
                        request.getRequestDispatcher("/viewCustomers.jsp").forward(request, response);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving customers");
                    }
                    break;
            }
        } else {
            // Handle other GET requests (e.g., view customers)
            try {
                request.setAttribute("customers", customerService.getAllCustomers());
                request.getRequestDispatcher("/viewCustomers.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving customers");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null && action.equals("update")) {
            // Handle update customer request
            int customerId = Integer.parseInt(request.getParameter("customerId"));
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String NIC = request.getParameter("nic");
            String phone = request.getParameter("phone");

            Customer customer = new Customer();
            customer.setCustomerId(customerId);
            customer.setName(name);
            customer.setAddress(address);
            customer.setNIC(NIC);
            customer.setPhone(phone);

            try {
                customerService.updateCustomer(customer);
                response.sendRedirect("customers");
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error updating customer");
            }
        } else {
            // Handle add customer request
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String NIC = request.getParameter("nic");
            String phone = request.getParameter("phone");

            // Create User object
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setRole("customer"); // Default role for customers

            // Create Customer object
            Customer customer = new Customer(); 
            customer.setName(name); 
            customer.setAddress(address); 
            customer.setNIC(NIC); 
            customer.setPhone(phone); 

            try {
                // Save user to the database
                int userId = userService.addUser(user);

                // Save customer to the database
                customer.setUserId(userId); // Link customer to user
                customerService.addCustomer(customer);

                // Redirect to the customers list page
                response.sendRedirect("customers");
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("addCustomer.jsp?error=1");
            }
        }
    }
}