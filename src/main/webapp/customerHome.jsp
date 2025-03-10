<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.megacitycab.model.Booking" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Customer Home - Mega City Cab</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <style>
        body {
            background: url('https://www.shutterstock.com/image-vector/taxi-service-abstract-banner-design-600nw-2506919915.jpg') no-repeat center center fixed;
            background-size: cover;
            position: relative;
        }
        body::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5); 
            z-index: -1;
        }
        .container {
            margin-top: 2rem;
        }
        .form-container, .table-container {
            background: rgba(255, 255, 255, 0.9);
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            margin-bottom: 2rem;
        }
        .form-container h2, .table-container h2 {
            margin-bottom: 1.5rem;
            color: #ff8800;
            text-align: center;
        }
        .btn-submit {
            width: 100%;
            padding: 0.75rem;
            background-color: #ff8800;
            border: none;
            color: #fff;
            font-size: 1rem;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        .btn-submit:hover {
            background-color: #e76f00;
        }
        .navbar {
            background-color: #ff8800;
        }
        .navbar .nav-link {
            color: #fff;
            transition: color 0.3s;
        }
        .navbar .nav-link:hover {
            color: #f5f5f5;
        }
        .navbar .btn-outline-light {
            color: #fff;
            border-color: #fff;
        }
        .navbar .btn-outline-light:hover {
            background-color: #fff;
            color: #ff8800;
        }
        .table {
            margin-top: 1rem;
        }
        .table th {
            background-color: #ff8800;
            color: #fff;
        }
        .table tbody tr:hover {
            background-color: #ffe0b2;
        }
        .form-label {
            color: #333;
        }
        .btn-danger {
		    background-color: #dc3545;
		    border: none;
		    padding: 0.25rem 0.5rem;
		    font-size: 0.875rem;
		    border-radius: 4px;
		}
		
		.btn-danger:hover {
		    background-color: #c82333;
		}
		
		.btn-secondary {
		    background-color: #6c757d;
		    border: none;
		    padding: 0.25rem 0.5rem;
		    font-size: 0.875rem;
		    border-radius: 4px;
		}
    </style>
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/customerHome">Mega City Cab</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/customerHome">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/help.jsp">Help</a>
                    </li>
                </ul>
                <form action="${pageContext.request.contextPath}/login" method="get" class="d-flex">
                    <button type="submit" class="btn btn-outline-light">Logout</button>
                </form>
            </div>
        </div>
    </nav>

    <div class="container">
        <!-- Book a Cab Form -->
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="form-container">
                    <h2>Book a Cab</h2>
                    <form action="${pageContext.request.contextPath}/bookings" method="post">
                        <div class="mb-3">
                            <label for="pickupLocation" class="form-label">Pickup Location</label>
                            <input type="text" class="form-control" id="pickupLocation" name="pickupLocation" required>
                        </div>
                        <div class="mb-3">
                            <label for="destination" class="form-label">Destination</label>
                            <input type="text" class="form-control" id="destination" name="destination" required>
                        </div>
                        <div class="mb-3">
                            <label for="contactNumber" class="form-label">Contact Number</label>
                            <input type="text" class="form-control" id="contactNumber" name="contactNumber" required>
                        </div>
                        <button type="submit" class="btn btn-submit">Book Now</button>
                    </form>
                </div>
            </div>
        </div>

		<!-- My Bookings Table -->
		<div class="row justify-content-center">
		    <div class="col-md-10">
		        <div class="table-container">
		            <h2>My Bookings</h2>
		            <table class="table table-striped">
		                <thead>
		                    <tr>
		                        <th>Booking ID</th>
		                        <th>Pickup Location</th>
		                        <th>Destination</th>
		                        <th>Contact Number</th>
		                        <th>Booking Date</th>
		                        <th>Status</th>
		                        <th>Action</th> <!-- New column for Cancel Button -->
		                    </tr>
		                </thead>
		                <tbody>
		                    <% List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
		                       if (bookings != null) {
		                           for (Booking booking : bookings) { %>
		                            <tr>
		                                <td><%= booking.getBookingId() %></td>
		                                <td><%= booking.getPickupLocation() %></td>
		                                <td><%= booking.getDestination() %></td>
		                                <td><%= booking.getContactNumber() %></td>
		                                <td><%= booking.getBookingDate() %></td>
		                                <td><%= booking.getStatus() %></td>
		                                <td>
		                                    <% if (booking.getStatus().equals("Pending")) { %>
		                                        <form action="${pageContext.request.contextPath}/bookings" method="post" style="display:inline;">
		                                            <input type="hidden" name="action" value="cancel">
		                                            <input type="hidden" name="bookingId" value="<%= booking.getBookingId() %>">
		                                            <button type="submit" class="btn btn-danger btn-sm">Cancel</button>
		                                        </form>
		                                    <% } else { %>
		                                        <button class="btn btn-secondary btn-sm" disabled>Cancel</button>
		                                    <% } %>
		                                </td>
		                            </tr>
		                        <% }
		                       } else { %>
		                        <tr>
		                            <td colspan="7" class="text-center">No bookings found.</td>
		                        </tr>
		                    <% } %>
		                </tbody>
		            </table>
		        </div>
		    </div>
		</div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>
