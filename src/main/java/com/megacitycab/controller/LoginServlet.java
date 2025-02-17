package com.megacitycab.controller;

import com.megacitycab.model.User;
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

@SuppressWarnings("serial")
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "SELECT * FROM users WHERE username = ? AND password = ?")) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setRole(rs.getString("role"));
                user.setName(rs.getString("name"));
                user.setAddress(rs.getString("address"));
                user.setNic(rs.getString("nic"));
                user.setPhone(rs.getString("phone"));

                // Set user in session
                request.getSession().setAttribute("user", user);

                // Redirect based on role
                if ("admin".equals(user.getRole())) {
                    response.sendRedirect("adminDashboard.jsp");
                } else if ("staff".equals(user.getRole())) {
                    response.sendRedirect("staffDashboard.jsp");
                } else if ("customer".equals(user.getRole())) {
                    response.sendRedirect("customerDashboard.jsp");
                }
            } else {
                request.setAttribute("error", "Invalid username or password");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error. Please try again.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}