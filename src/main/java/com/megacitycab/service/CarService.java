package com.megacitycab.service;

import com.megacitycab.dao.CarDAO;
import com.megacitycab.model.Car;
import java.sql.SQLException;
import java.util.List;

public class CarService {

    private CarDAO carDAO = new CarDAO();

    // Add a new car
    public void addCar(Car car) throws SQLException {
        carDAO.addCar(car);
    }

    // Get all cars
    public List<Car> getAllCars() throws SQLException {
        return carDAO.getAllCars();
    }

    // Get a car by ID
    public Car getCarById(int carId) throws SQLException {
        return carDAO.getCarById(carId);
    }

    // Update a car
    public void updateCar(Car car) throws SQLException {
        carDAO.updateCar(car);
    }

    // Delete a car
    public void deleteCar(int carId) throws SQLException {
        carDAO.deleteCar(carId);
    }
}