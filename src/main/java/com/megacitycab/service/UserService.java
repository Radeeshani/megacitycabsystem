package com.megacitycab.service;

import com.megacitycab.dao.UserDAO;
import com.megacitycab.model.User;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    // Authenticate user (login)
    public User authenticate(String username, String password) throws SQLException {
        return userDAO.authenticate(username, password);
    }
    
    public int addUser(User user) throws SQLException {
        return userDAO.addUser(user);
    }
    
    public User getUserById(int userId) throws SQLException {
        return userDAO.getUserById(userId);
    }
    
    public void updateUser(User user) throws SQLException {
        userDAO.updateUser(user);
    }

    public List<User> getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }

    public void deleteUser(int userId) throws SQLException {
        userDAO.deleteUser(userId);
    }
}