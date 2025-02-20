package com.megacitycab.service;

import com.megacitycab.dao.UserDAO;
import com.megacitycab.model.User;
import java.sql.SQLException;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    // Authenticate user (login)
    public User authenticate(String username, String password) throws SQLException {
        return userDAO.authenticate(username, password);
    }
}