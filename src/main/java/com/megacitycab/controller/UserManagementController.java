package com.megacitycab.controller;

import com.megacitycab.model.User;
import com.megacitycab.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/users")
public class UserManagementController extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        try {
            if (action != null && action.equals("edit")) {
                // Handle edit user request
                int userId = Integer.parseInt(request.getParameter("id"));
                User user = userService.getUserById(userId);
                if (user != null) {
                    request.setAttribute("user", user);
                    request.getRequestDispatcher("/editUser.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
                }
            } else if (action != null && action.equals("delete")) {
                // Handle delete user request
                int userId = Integer.parseInt(request.getParameter("id"));
                userService.deleteUser(userId);
                response.sendRedirect("users");
            } else {
                // Show user list
                List<User> users = userService.getAllUsers();
                request.setAttribute("users", users);
                request.getRequestDispatcher("/viewUsers.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        try {
            if (action != null && action.equals("add")) {
                // Handle add user request
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String role = request.getParameter("role");

                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setRole(role);

                userService.addUser(user);
                response.sendRedirect("users");
            } else if (action != null && action.equals("update")) {
                // Handle update user request
                int userId = Integer.parseInt(request.getParameter("userId"));
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String role = request.getParameter("role");

                User user = new User();
                user.setUserId(userId);
                user.setUsername(username);
                user.setPassword(password);
                user.setRole(role);

                userService.updateUser(user);
                response.sendRedirect("users");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred");
        }
    }
} 