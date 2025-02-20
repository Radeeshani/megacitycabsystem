package com.megacitycab.service;

import com.megacitycab.dao.DriverDAO;
import com.megacitycab.model.Driver;
import java.sql.SQLException;
import java.util.List;

public class DriverService {

    private DriverDAO driverDAO = new DriverDAO();

    // Add a new driver
    public void addDriver(Driver driver) throws SQLException {
        driverDAO.addDriver(driver);
    }

    // Get all drivers
    public List<Driver> getAllDrivers() throws SQLException {
        return driverDAO.getAllDrivers();
    }

    // Get a driver by ID
    public Driver getDriverById(int driverId) throws SQLException {
        return driverDAO.getDriverById(driverId);
    }

    // Update a driver
    public void updateDriver(Driver driver) throws SQLException {
        driverDAO.updateDriver(driver);
    }

    // Delete a driver
    public void deleteDriver(int driverId) throws SQLException {
        driverDAO.deleteDriver(driverId);
    }
}