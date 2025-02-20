package com.megacitycab.controller;

import com.megacitycab.model.Car;
import com.megacitycab.service.CarService;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Car> cars = carService.getAllCars();
            request.setAttribute("cars", cars);
            request.getRequestDispatcher("/viewCars.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving cars");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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