package com.megacitycab.controller;

import com.megacitycab.model.Driver;
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
@WebServlet("/DriverServlet")
public class DriverServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "add":
                addDriver(request, response);
                break;
            case "update":
                updateDriver(request, response);
                break;
            case "delete":
                deleteDriver(request, response);
                break;
            case "view":
                viewDrivers(request, response);
                break;
            default:
                response.sendRedirect("manageDrivers.jsp");
        }
    }

    private void addDriver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String licenseNumber = request.getParameter("licenseNumber");
        String contact = request.getParameter("contact");

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "INSERT INTO drivers (name, license_number, contact) VALUES (?, ?, ?)")) {
            stmt.setString(1, name);
            stmt.setString(2, licenseNumber);
            stmt.setString(3, contact);
            stmt.executeUpdate();

            response.sendRedirect("manageDrivers.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error. Please try again.");
            request.getRequestDispatcher("manageDrivers.jsp").forward(request, response);
        }
    }

    private void updateDriver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int driverId = Integer.parseInt(request.getParameter("driverId"));
        String name = request.getParameter("name");
        String licenseNumber = request.getParameter("licenseNumber");
        String contact = request.getParameter("contact");

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "UPDATE drivers SET name = ?, license_number = ?, contact = ? WHERE driver_id = ?")) {
            stmt.setString(1, name);
            stmt.setString(2, licenseNumber);
            stmt.setString(3, contact);
            stmt.setInt(4, driverId);
            stmt.executeUpdate();

            response.sendRedirect("manageDrivers.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error. Please try again.");
            request.getRequestDispatcher("manageDrivers.jsp").forward(request, response);
        }
    }

    private void deleteDriver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int driverId = Integer.parseInt(request.getParameter("driverId"));

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "DELETE FROM drivers WHERE driver_id = ?")) {
            stmt.setInt(1, driverId);
            stmt.executeUpdate();

            response.sendRedirect("manageDrivers.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error. Please try again.");
            request.getRequestDispatcher("manageDrivers.jsp").forward(request, response);
        }
    }

    private void viewDrivers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Driver> drivers = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM drivers");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Driver driver = new Driver();
                driver.setDriverId(rs.getInt("driver_id"));
                driver.setName(rs.getString("name"));
                driver.setLicenseNumber(rs.getString("license_number"));
                driver.setContact(rs.getString("contact"));
                drivers.add(driver);
            }

            request.setAttribute("drivers", drivers);
            request.getRequestDispatcher("viewDrivers.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error. Please try again.");
            request.getRequestDispatcher("manageDrivers.jsp").forward(request, response);
        }
    }
}