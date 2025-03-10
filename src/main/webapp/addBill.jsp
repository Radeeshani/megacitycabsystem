<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add Bill - Mega City Cab</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <style>
        body {
            background: url('https://www.shutterstock.com/image-vector/taxi-service-abstract-banner-design-600nw-2506919915.jpg') no-repeat center center fixed;
            background-size: cover;
            position: relative;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
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
        .navbar {
            background-color: #ff8800; 
        }
        .navbar-brand, .nav-link {
            color: #fff !important;
        }
        .nav-link:hover {
            color: #f8f9fa !important;
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
            background-color: #ff8800;
            border: none;
            color: #fff;
            font-size: 1rem;
            border-radius: 5px;
        }
        .btn-submit:hover {
            background-color: #ff8800;
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
                    <!-- Manage Users Dropdown -->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownUsers" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Manage Users
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownUsers">
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/addUser.jsp">Add User</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/users">View Users</a></li>
                        </ul>
                    </li>
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
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/cars?action=add">Add Car</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/cars">View Cars</a></li>
                        </ul>
                    </li>
                    <!-- Manage Bookings Dropdown -->
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/bookings">Manage Bookings</a>
                    </li>
                    <!-- Manage Bills  -->
                    <li class="nav-item">
						    <a class="nav-link" href="${pageContext.request.contextPath}/bills">Manage Bills</a>
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
                <h2>Add Bill</h2>
                <form action="${pageContext.request.contextPath}/bills" method="post">
                    <div class="mb-3">
                        <label for="bookingId" class="form-label">Booking ID</label>
                        <input type="number" class="form-control" id="bookingId" name="bookingId" value="<%= request.getParameter("bookingId") != null ? request.getParameter("bookingId") : "" %>" readonly required>
                    </div>
                    <div class="mb-3">
                        <label for="kilometersTraveled" class="form-label">Kilometers Traveled</label>
                        <input type="number" step="0.01" class="form-control" id="kilometersTraveled" name="kilometersTraveled" required>
                    </div>
                    <div class="mb-3">
                        <label for="tax" class="form-label">Tax (%)</label>
                        <input type="number" step="0.01" class="form-control" id="tax" name="tax" required>
                    </div>
                    <div class="mb-3">
                        <label for="discount" class="form-label">Discount (%)</label>
                        <input type="number" step="0.01" class="form-control" id="discount" name="discount" required>
                    </div>
                    <div class="mb-3">
					    <label for="totalAmount" class="form-label">Total Amount</label>
					    <input type="number" step="0.01" class="form-control" id="totalAmount" name="totalAmount" readonly>
					</div>
                    <button type="submit" class="btn btn-submit">Add Bill</button>
                </form>
            </div>
        </div>
    </div>
</div>

	<!-- JavaScript to calculate total amount -->
	<script>
	document.getElementById('kilometersTraveled').addEventListener('input', calculateTotalAmount);
	document.getElementById('tax').addEventListener('input', calculateTotalAmount);
	document.getElementById('discount').addEventListener('input', calculateTotalAmount);

	function calculateTotalAmount() {
	    const kilometersTraveled = parseFloat(document.getElementById('kilometersTraveled').value) || 0;
	    const tax = parseFloat(document.getElementById('tax').value) || 0;
	    const discount = parseFloat(document.getElementById('discount').value) || 0;

	    // Base rate per kilometer (you can adjust this value)
	    const ratePerKilometer = 10; 

	    // Calculate total amount
	    let totalAmount = kilometersTraveled * ratePerKilometer;
	    totalAmount += (totalAmount * tax) / 100; 
	    totalAmount -= (totalAmount * discount) / 100; 

	    // Update the total amount field
	    document.getElementById('totalAmount').value = totalAmount.toFixed(2);
	}
	</script>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>