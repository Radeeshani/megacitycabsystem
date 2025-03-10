package com.megacitycab.controller;

import com.megacitycab.model.Car;
import com.megacitycab.model.Driver;
import com.megacitycab.service.CarService;
import com.megacitycab.service.DriverService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/cars")
public class CarController extends HttpServlet {
    private CarService carService = new CarService();
    private DriverService driverService = new DriverService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "edit":
                    // Handle edit car request
                    int editCarId = Integer.parseInt(request.getParameter("id"));
                    try {
                        Car car = carService.getCarById(editCarId);
                        if (car != null) {
                            List<Driver> drivers = driverService.getAllDrivers(); 
                            request.setAttribute("car", car); 
                            request.setAttribute("drivers", drivers); 
                            request.getRequestDispatcher("/editCar.jsp").forward(request, response);
                        } else {
                            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Car not found");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving car details");
                    }
                    break;

                case "delete":
                    // Handle delete car request
                    int deleteCarId = Integer.parseInt(request.getParameter("id"));
                    try {
                        carService.deleteCar(deleteCarId);
                        response.sendRedirect("cars"); 
                    } catch (SQLException e) {
                        e.printStackTrace();
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting car");
                    }
                    break;

                case "add":
                    // Handle add car request
                    try {
                        List<Driver> drivers = driverService.getAllDrivers(); 
                        request.setAttribute("drivers", drivers); 
                        request.getRequestDispatcher("/addCar.jsp").forward(request, response);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving drivers");
                    }
                    break;

                default:
                    try {
                        List<Car> cars = carService.getAllCars();
                        request.setAttribute("cars", cars);
                        request.getRequestDispatcher("/viewCars.jsp").forward(request, response);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving cars");
                    }
                    break;
            }
        } else {
            try {
                List<Car> cars = carService.getAllCars();
                request.setAttribute("cars", cars);
                request.getRequestDispatcher("/viewCars.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving cars");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null && action.equals("update")) {
            int carId = Integer.parseInt(request.getParameter("carId"));
            String model = request.getParameter("model");
            String registrationNumber = request.getParameter("registrationNumber");
            int driverId = Integer.parseInt(request.getParameter("driverId"));

            Car car = new Car();
            car.setCarId(carId);
            car.setModel(model);
            car.setRegistrationNumber(registrationNumber);
            car.setDriverId(driverId);

            try {
                carService.updateCar(car);
                response.sendRedirect("cars");
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error updating car");
            }
        } else {
            String model = request.getParameter("model");
            String registrationNumber = request.getParameter("registrationNumber");
            int driverId = Integer.parseInt(request.getParameter("driverId"));

            Car car = new Car();
            car.setModel(model);
            car.setRegistrationNumber(registrationNumber);
            car.setDriverId(driverId);

            try {
                carService.addCar(car);
                response.sendRedirect("cars");
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error adding car");
            }
        }
    }
}