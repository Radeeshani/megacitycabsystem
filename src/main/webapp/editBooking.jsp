<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.megacitycab.model.Booking" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Edit Booking - Mega City Cab</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            margin-top: 2rem;
        }
        .form-container {
            background: #fff;
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        .form-container h2 {
            margin-bottom: 1.5rem;
            color: #333;
            text-align: center;
        }
        .btn-submit {
            width: 100%;
            padding: 0.75rem;
            background-color: #007bff;
            border: none;
            color: #fff;
            font-size: 1rem;
            border-radius: 5px;
        }
        .btn-submit:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/dashboard.jsp">Mega City Cab</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <!-- Manage Customers Dropdown -->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Manage Customers
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/addCustomer.jsp">Add Customer</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/customers">View Customers</a></li>
                        </ul>
                    </li>
                    <!-- Manage Drivers Dropdown -->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Manage Drivers
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/addDriver.jsp">Add Driver</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/drivers">View Drivers</a></li>
                        </ul>
                    </li>
                    <!-- Manage Cars Dropdown -->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Manage Cars
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/addCar.jsp">Add Car</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/cars">View Cars</a></li>
                        </ul>
                    </li>
                    <!-- Manage Bookings Dropdown -->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Manage Bookings
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/addBooking.jsp">Add Booking</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/bookings">View Bookings</a></li>
                        </ul>
                    </li>
                    <!-- Manage Bills Dropdown -->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Manage Bills
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/addBill.jsp">Add Bill</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/bills">View Bills</a></li>
                        </ul>
                    </li>
                </ul>
                <form action="${pageContext.request.contextPath}/login" method="get" class="d-flex">
                    <button type="submit" class="btn btn-outline-light">Logout</button>
                </form>
            </div>
        </div>
    </nav>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="form-container">
                    <h2>Edit Booking</h2>
                    <%
                        Booking booking = (Booking) request.getAttribute("booking");
                        if (booking == null) {
                            out.println("<div class='alert alert-danger'>Booking not found.</div>");
                        } else {
                    %>
                    <form action="${pageContext.request.contextPath}/bookings" method="post">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="bookingId" value="<%= booking.getBookingId() %>">
                        <div class="mb-3">
                            <label for="customerId" class="form-label">Customer ID</label>
                            <input type="number" class="form-control" id="customerId" name="customerId" value="<%= booking.getCustomerId() %>" required>
                        </div>
                        <div class="mb-3">
                            <label for="pickupLocation" class="form-label">Pickup Location</label>
                            <input type="text" class="form-control" id="pickupLocation" name="pickupLocation" value="<%= booking.getPickupLocation() %>" required>
                        </div>
                        <div class="mb-3">
                            <label for="destination" class="form-label">Destination</label>
                            <input type="text" class="form-control" id="destination" name="destination" value="<%= booking.getDestination() %>" required>
                        </div>
                        <div class="mb-3">
                            <label for="contactNumber" class="form-label">Contact Number</label>
                            <input type="text" class="form-control" id="contactNumber" name="contactNumber" value="<%= booking.getContactNumber() %>" required>
                        </div>
                        <div class="mb-3">
                            <label for="assignedDriverId" class="form-label">Assigned Driver ID</label>
                            <input type="number" class="form-control" id="assignedDriverId" name="assignedDriverId" value="<%= booking.getAssignedDriverId() %>" required>
                        </div>
                        <div class="mb-3">
                            <label for="status" class="form-label">Status</label>
                            <select class="form-control" id="status" name="status" required>
                                <option value="Pending" <%= booking.getStatus().equals("Pending") ? "selected" : "" %>>Pending</option>
                                <option value="Completed" <%= booking.getStatus().equals("Completed") ? "selected" : "" %>>Completed</option>
                                <option value="Cancelled" <%= booking.getStatus().equals("Cancelled") ? "selected" : "" %>>Cancelled</option>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-submit">Update Booking</button>
                    </form>
                    <% } %>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>