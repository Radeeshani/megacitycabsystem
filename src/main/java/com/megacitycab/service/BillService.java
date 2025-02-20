package com.megacitycab.service;

import com.megacitycab.dao.BillDAO;
import com.megacitycab.model.Bill;
import java.sql.SQLException;
import java.util.List;

public class BillService {

    private BillDAO billDAO = new BillDAO();

    // Add a new bill
    public void addBill(Bill bill) throws SQLException {
        billDAO.addBill(bill);
    }

    // Get all bills
    public List<Bill> getAllBills() throws SQLException {
        return billDAO.getAllBills();
    }

    // Get a bill by ID
    public Bill getBillById(int billId) throws SQLException {
        return billDAO.getBillById(billId);
    }

    // Update a bill
    public void updateBill(Bill bill) throws SQLException {
        billDAO.updateBill(bill);
    }

    // Delete a bill
    public void deleteBill(int billId) throws SQLException {
        billDAO.deleteBill(billId);
    }
}