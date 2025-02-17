package com.megacitycab.controller;

import com.megacitycab.model.Car;
import com.megacitycab.util.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
@WebServlet("/CarServlet")
public class CarServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "add":
                addCar(request, response);
                break;
            case "update":
                updateCar(request, response);
                break;
            case "delete":
                deleteCar(request, response);
                break;
            case "view":
                viewCars(request, response);
                break;
            default:
                response.sendRedirect("manageCars.jsp");
        }
    }

    private void addCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String model = request.getParameter("model");
        String registrationNumber = request.getParameter("registrationNumber");
        int driverId = Integer.parseInt(request.getParameter("driverId"));

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "INSERT INTO cars (model, registration_number, driver_id) VALUES (?, ?, ?)")) {
            stmt.setString(1, model);
            stmt.setString(2, registrationNumber);
            stmt.setInt(3, driverId);
            stmt.executeUpdate();

            response.sendRedirect("manageCars.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error. Please try again.");
            request.getRequestDispatcher("manageCars.jsp").forward(request, response);
        }
    }

    private void updateCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int carId = Integer.parseInt(request.getParameter("carId"));
        String model = request.getParameter("model");
        String registrationNumber = request.getParameter("registrationNumber");
        int driverId = Integer.parseInt(request.getParameter("driverId"));

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "UPDATE cars SET model = ?, registration_number = ?, driver_id = ? WHERE car_id = ?")) {
            stmt.setString(1, model);
            stmt.setString(2, registrationNumber);
            stmt.setInt(3, driverId);
            stmt.setInt(4, carId);
            stmt.executeUpdate();

            response.sendRedirect("manageCars.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error. Please try again.");
            request.getRequestDispatcher("manageCars.jsp").forward(request, response);
        }
    }

    private void deleteCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int carId = Integer.parseInt(request.getParameter("carId"));

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "DELETE FROM cars WHERE car_id = ?")) {
            stmt.setInt(1, carId);
            stmt.executeUpdate();

            response.sendRedirect("manageCars.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error. Please try again.");
            request.getRequestDispatcher("manageCars.jsp").forward(request, response);
        }
    }

    private void viewCars(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Car> cars = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cars");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Car car = new Car();
                car.setCarId(rs.getInt("car_id"));
                car.setModel(rs.getString("model"));
                car.setRegistrationNumber(rs.getString("registration_number"));
                car.setDriverId(rs.getInt("driver_id"));
                cars.add(car);
            }

            request.setAttribute("cars", cars);
            request.getRequestDispatcher("viewCars.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error. Please try again.");
            request.getRequestDispatcher("manageCars.jsp").forward(request, response);
        }
    }
}