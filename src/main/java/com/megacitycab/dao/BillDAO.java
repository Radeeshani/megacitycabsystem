package com.megacitycab.dao;

import com.megacitycab.model.Bill;
import com.megacitycab.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {

    // Add a new bill
    public void addBill(Bill bill) throws SQLException {
        String sql = "INSERT INTO bills (booking_id, total_amount, tax, discount) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bill.getBookingId());
            stmt.setDouble(2, bill.getTotalAmount());
            stmt.setDouble(3, bill.getTax());
            stmt.setDouble(4, bill.getDiscount());
            stmt.executeUpdate();
        }
    }

    // Get all bills
    public List<Bill> getAllBills() throws SQLException {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT * FROM bills";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setBillId(rs.getInt("bill_id"));
                bill.setBookingId(rs.getInt("booking_id"));
                bill.setTotalAmount(rs.getDouble("total_amount"));
                bill.setTax(rs.getDouble("tax"));
                bill.setDiscount(rs.getDouble("discount"));
                bills.add(bill);
            }
        }
        return bills;
    }

    // Get a bill by ID
    public Bill getBillById(int billId) throws SQLException {
        String sql = "SELECT * FROM bills WHERE bill_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, billId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Bill bill = new Bill();
                    bill.setBillId(rs.getInt("bill_id"));
                    bill.setBookingId(rs.getInt("booking_id"));
                    bill.setTotalAmount(rs.getDouble("total_amount"));
                    bill.setTax(rs.getDouble("tax"));
                    bill.setDiscount(rs.getDouble("discount"));
                    return bill;
                }
            }
        }
        return null;
    }

    // Update a bill
    public void updateBill(Bill bill) throws SQLException {
        String sql = "UPDATE bills SET booking_id = ?, total_amount = ?, tax = ?, discount = ? WHERE bill_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bill.getBookingId());
            stmt.setDouble(2, bill.getTotalAmount());
            stmt.setDouble(3, bill.getTax());
            stmt.setDouble(4, bill.getDiscount());
            stmt.setInt(5, bill.getBillId());
            stmt.executeUpdate();
        }
    }

    // Delete a bill
    public void deleteBill(int billId) throws SQLException {
        String sql = "DELETE FROM bills WHERE bill_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, billId);
            stmt.executeUpdate();
        }
    }
}