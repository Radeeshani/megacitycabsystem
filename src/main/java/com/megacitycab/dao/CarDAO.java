package com.megacitycab.dao;

import com.megacitycab.model.Car;
import com.megacitycab.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {

    // Add a new car
    public void addCar(Car car) throws SQLException {
        String sql = "INSERT INTO cars (model, registration_number, driver_id) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, car.getModel());
            stmt.setString(2, car.getRegistrationNumber());
            stmt.setInt(3, car.getDriverId());
            stmt.executeUpdate();
        }
    }

    // Get all cars
    public List<Car> getAllCars() throws SQLException {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Car car = new Car();
                car.setCarId(rs.getInt("car_id"));
                car.setModel(rs.getString("model"));
                car.setRegistrationNumber(rs.getString("registration_number"));
                car.setDriverId(rs.getInt("driver_id"));
                cars.add(car);
            }
        }
        return cars;
    }

    // Get a car by ID
    public Car getCarById(int carId) throws SQLException {
        String sql = "SELECT * FROM cars WHERE car_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, carId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Car car = new Car();
                    car.setCarId(rs.getInt("car_id"));
                    car.setModel(rs.getString("model"));
                    car.setRegistrationNumber(rs.getString("registration_number"));
                    car.setDriverId(rs.getInt("driver_id"));
                    return car;
                }
            }
        }
        return null;
    }

    // Update a car
    public void updateCar(Car car) throws SQLException {
        String sql = "UPDATE cars SET model = ?, registration_number = ?, driver_id = ? WHERE car_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, car.getModel());
            stmt.setString(2, car.getRegistrationNumber());
            stmt.setInt(3, car.getDriverId());
            stmt.setInt(4, car.getCarId());
            stmt.executeUpdate();
        }
    }

    // Delete a car
    public void deleteCar(int carId) throws SQLException {
        String sql = "DELETE FROM cars WHERE car_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, carId);
            stmt.executeUpdate();
        }
    }
}