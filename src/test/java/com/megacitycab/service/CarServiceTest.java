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
//import com.megacitycab.dao.CarDAO;
//import com.megacitycab.model.Car;
//
//public class CarServiceTest {
//
//    @Mock
//    private CarDAO carDAO;
//
//    @InjectMocks
//    private CarService carService;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testAddCarSuccess() throws SQLException {
//        // Arrange
//        Car car = new Car();
//        car.setModel("Toyota Camry");
//        car.setPlateNumber("ABC123");
//        car.setStatus("Available");
//
//        doNothing().when(carDAO).addCar(car);
//
//        // Act
//        carService.addCar(car);
//
//        // Assert
//        verify(carDAO, times(1)).addCar(car);
//    }
//
//    @Test(expected = SQLException.class)
//    public void testAddCarDatabaseError() throws SQLException {
//        // Arrange
//        Car car = new Car();
//        car.setModel("Toyota Camry");
//        car.setPlateNumber("ABC123");
//        car.setStatus("Available");
//
//        doThrow(new SQLException("Database error")).when(carDAO).addCar(car);
//
//        // Act
//        carService.addCar(car);
//
//        // Assert - expected SQLException
//    }
//
//    @Test
//    public void testAddCarWithValidation() throws SQLException {
//        // Arrange
//        Car car = new Car();
//        car.setModel("Toyota Camry");
//        car.setPlateNumber("ABC123");
//        car.setStatus("Available");
//
//        // Mock validation check (assuming we add validation in CarService)
//        when(carDAO.getCarByPlateNumber("ABC123")).thenReturn(null);
//        doNothing().when(carDAO).addCar(car);
//
//        // Act
//        carService.addCar(car);
//
//        // Assert
//        verify(carDAO, times(1)).addCar(car);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void testAddCarWithInvalidData() throws SQLException {
//        // Arrange
//        Car car = new Car();
//        // Missing required fields
//
//        // Act
//        carService.addCar(car);
//
//        // Assert - expected IllegalArgumentException
//    }
//} 