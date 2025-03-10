package com.megacitycab.controller;

import com.megacitycab.model.Driver;
import com.megacitycab.service.DriverService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/drivers")
public class DriverController extends HttpServlet {
    private DriverService driverService = new DriverService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "edit":
                    // Handle edit driver request
                    int editDriverId = Integer.parseInt(request.getParameter("id"));
                    try {
                        Driver driver = driverService.getDriverById(editDriverId);
                        if (driver != null) {
                            request.setAttribute("driver", driver); // Set the driver object
                            request.getRequestDispatcher("/editDriver.jsp").forward(request, response);
                        } else {
                            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Driver not found");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving driver details");
                    }
                    break;

                case "delete":
                    // Handle delete driver request
                    int deleteDriverId = Integer.parseInt(request.getParameter("id"));
                    try {
                        driverService.deleteDriver(deleteDriverId);
                        response.sendRedirect("drivers"); // Redirect to the driver list page after deletion
                    } catch (SQLException e) {
                        e.printStackTrace();
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting driver");
                    }
                    break;

                default:
                    // Handle other GET requests (e.g., view drivers)
                    try {
                        List<Driver> drivers = driverService.getAllDrivers();
                        request.setAttribute("drivers", drivers);
                        request.getRequestDispatcher("/viewDrivers.jsp").forward(request, response);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving drivers");
                    }
                    break;
            }
        } else {
            // Handle other GET requests (e.g., view drivers)
            try {
                List<Driver> drivers = driverService.getAllDrivers();
                request.setAttribute("drivers", drivers);
                request.getRequestDispatcher("/viewDrivers.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving drivers");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null && action.equals("update")) {
            // Handle update driver request
            int driverId = Integer.parseInt(request.getParameter("driverId"));
            String name = request.getParameter("name");
            String licenseNumber = request.getParameter("licenseNumber");
            String contact = request.getParameter("contact");

            Driver driver = new Driver();
            driver.setDriverId(driverId);
            driver.setName(name);
            driver.setLicenseNumber(licenseNumber);
            driver.setContact(contact);

            try {
                driverService.updateDriver(driver);
                response.sendRedirect("drivers");
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error updating driver");
            }
        } else {
            // Handle add driver request
            String name = request.getParameter("name");
            String licenseNumber = request.getParameter("licenseNumber");
            String contact = request.getParameter("contact");

            Driver driver = new Driver();
            driver.setName(name);
            driver.setLicenseNumber(licenseNumber);
            driver.setContact(contact);

            try {
                driverService.addDriver(driver);
                response.sendRedirect("drivers");
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error adding driver");
            }
        }
    }
}