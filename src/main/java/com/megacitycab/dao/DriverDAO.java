package com.megacitycab.dao;

import com.megacitycab.model.Driver;
import com.megacitycab.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverDAO {

    // Add a new driver
    public void addDriver(Driver driver) throws SQLException {
        String sql = "INSERT INTO drivers (name, license_number, contact) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, driver.getName());
            stmt.setString(2, driver.getLicenseNumber());
            stmt.setString(3, driver.getContact());
            stmt.executeUpdate();
        }
    }

    // Get all drivers
    public List<Driver> getAllDrivers() throws SQLException {
        List<Driver> drivers = new ArrayList<>();
        String sql = "SELECT * FROM drivers";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Driver driver = new Driver();
                driver.setDriverId(rs.getInt("driver_id"));
                driver.setName(rs.getString("name"));
                driver.setLicenseNumber(rs.getString("license_number"));
                driver.setContact(rs.getString("contact"));
                drivers.add(driver);
            }
        }
        return drivers;
    }

    // Get a driver by ID
    public Driver getDriverById(int driverId) throws SQLException {
        String sql = "SELECT * FROM drivers WHERE driver_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, driverId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Driver driver = new Driver();
                    driver.setDriverId(rs.getInt("driver_id"));
                    driver.setName(rs.getString("name"));
                    driver.setLicenseNumber(rs.getString("license_number"));
                    driver.setContact(rs.getString("contact"));
                    return driver;
                }
            }
        }
        return null;
    }

    // Update a driver
    public void updateDriver(Driver driver) throws SQLException {
        String sql = "UPDATE drivers SET name = ?, license_number = ?, contact = ? WHERE driver_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, driver.getName());
            stmt.setString(2, driver.getLicenseNumber());
            stmt.setString(3, driver.getContact());
            stmt.setInt(4, driver.getDriverId());
            stmt.executeUpdate();
        }
    }

    // Delete a driver
    public void deleteDriver(int driverId) throws SQLException {
        String sql = "DELETE FROM drivers WHERE driver_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, driverId);
            stmt.executeUpdate();
        }
    }
}