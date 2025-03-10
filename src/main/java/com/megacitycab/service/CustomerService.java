package com.megacitycab.service;

import com.megacitycab.dao.CustomerDAO;
import com.megacitycab.model.Customer;
import java.sql.SQLException;
import java.util.List;

public class CustomerService {

    private CustomerDAO customerDAO = new CustomerDAO();

    // Add a new customer
    public void addCustomer(Customer customer) throws SQLException {
        customerDAO.addCustomer(customer);
    }

    // Get all customers
    public List<Customer> getAllCustomers() throws SQLException {
        return customerDAO.getAllCustomers();
    }

    // Get a customer by ID
    public Customer getCustomerById(int customerId) throws SQLException {
        return customerDAO.getCustomerById(customerId);
    }

    // Update a customer
    public void updateCustomer(Customer customer) throws SQLException {
        customerDAO.updateCustomer(customer);
    }

    // Delete a customer
    public void deleteCustomer(int customerId) throws SQLException {
        customerDAO.deleteCustomer(customerId);
    }
    
    public Customer getCustomerByUserId(int userId) throws SQLException {
        return customerDAO.getCustomerByUserId(userId);
    }
}