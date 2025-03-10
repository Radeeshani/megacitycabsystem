//package com.megacitycab.service;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;
//
//import java.sql.SQLException;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import com.megacitycab.dao.UserDAO;
//import com.megacitycab.model.User;
//
//public class UserServiceTest {
//
//    @Mock
//    private UserDAO userDAO;
//
//    @InjectMocks
//    private UserService userService;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testAdminLoginSuccess() throws SQLException {
//        // Arrange
//        String username = "admin";
//        String password = "admin123";
//        
//        User expectedUser = new User();
//        expectedUser.setUserId(1);
//        expectedUser.setUsername(username);
//        expectedUser.setPassword(password);
//        expectedUser.setRole("admin");
//
//        when(userDAO.authenticate(username, password)).thenReturn(expectedUser);
//
//        // Act
//        User actualUser = userService.authenticate(username, password);
//
//        // Assert
//        assertNotNull("User should not be null", actualUser);
//        assertEquals("User ID should match", 1, actualUser.getUserId());
//        assertEquals("Username should match", username, actualUser.getUsername());
//        assertEquals("Role should be admin", "admin", actualUser.getRole());
//        
//        verify(userDAO).authenticate(username, password);
//    }
//
//    @Test
//    public void testAdminLoginFailure() throws SQLException {
//        // Arrange
//        String username = "admin";
//        String password = "wrongpassword";
//
//        when(userDAO.authenticate(username, password)).thenReturn(null);
//
//        // Act
//        User actualUser = userService.authenticate(username, password);
//
//        // Assert
//        assertNull("User should be null for invalid credentials", actualUser);
//        
//        verify(userDAO).authenticate(username, password);
//    }
//
//    @Test(expected = SQLException.class)
//    public void testAdminLoginDatabaseError() throws SQLException {
//        // Arrange
//        String username = "admin";
//        String password = "admin123";
//
//        when(userDAO.authenticate(username, password)).thenThrow(new SQLException("Database connection failed"));
//
//        // Act
//        userService.authenticate(username, password);
//
//        // Assert - expected SQLException
//    }
//} 