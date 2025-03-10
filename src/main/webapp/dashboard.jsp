<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Dashboard - Mega City Cab</title>
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
        .dashboard-content {
            flex: 1;
            padding: 2rem;
        }
        .footer {
            background-color: rgba(255, 255, 255, 0.9); 
            padding: 1rem;
            text-align: center;
            border-top: 1px solid #e9ecef;
        }
        /* Custom styles for dropdown */
        .dropdown:hover .dropdown-menu {
            display: block;
        }
        /* Custom styles for cards */
        .card {
            transition: transform 0.2s, box-shadow 0.2s;
            background: rgba(255, 255, 255, 0.9); 
            border: none;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        .card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
        }
        .card-title {
            font-size: 1.25rem;
            font-weight: bold;
            color: #333;
        }
        .card-text {
            font-size: 0.9rem;
            color: #666;
        }
        .btn-primary {
            background-color: #ff8800; 
            border: none;
            transition: background-color 0.3s ease;
        }
        .btn-primary:hover {
            background-color: #e76f00; 
        }
        h1, .lead {
            color: #fff; 
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

    <!-- Dashboard Content -->
    <div class="dashboard-content">
        <div class="container">
            <h1>Welcome to Mega City Cab</h1>
            <p class="lead">Use the navigation bar or the cards below to manage users, customers, drivers, cars, bookings, and bills.</p>

            <!-- Quick Navigation Cards -->
            <div class="row mt-4">
                <!-- Manage Users Card -->
                <div class="col-md-4 mb-4">
                    <div class="card h-100 text-center">
                        <div class="card-body">
                            <h5 class="card-title">Manage Users</h5>
                            <p class="card-text">Add, view, and manage admin/staff users.</p>
                            <a href="${pageContext.request.contextPath}/users" class="btn btn-primary">Go to Users</a>
                        </div>
                    </div>
                </div>

                <!-- Manage Customers Card -->
                <div class="col-md-4 mb-4">
                    <div class="card h-100 text-center">
                        <div class="card-body">
                            <h5 class="card-title">Manage Customers</h5>
                            <p class="card-text">Add, view, and manage customer details.</p>
                            <a href="${pageContext.request.contextPath}/customers" class="btn btn-primary">Go to Customers</a>
                        </div>
                    </div>
                </div>

                <!-- Manage Drivers Card -->
                <div class="col-md-4 mb-4">
                    <div class="card h-100 text-center">
                        <div class="card-body">
                            <h5 class="card-title">Manage Drivers</h5>
                            <p class="card-text">Add, view, and manage driver details.</p>
                            <a href="${pageContext.request.contextPath}/drivers" class="btn btn-primary">Go to Drivers</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row mt-4">
                <!-- Manage Cars Card -->
                <div class="col-md-4 mb-4">
                    <div class="card h-100 text-center">
                        <div class="card-body">
                            <h5 class="card-title">Manage Cars</h5>
                            <p class="card-text">Add, view, and manage car details.</p>
                            <a href="${pageContext.request.contextPath}/cars" class="btn btn-primary">Go to Cars</a>
                        </div>
                    </div>
                </div>

                <!-- Manage Bookings Card -->
                <div class="col-md-4 mb-4">
                    <div class="card h-100 text-center">
                        <div class="card-body">
                            <h5 class="card-title">Manage Bookings</h5>
                            <p class="card-text">View and manage all bookings.</p>
                            <a href="${pageContext.request.contextPath}/bookings" class="btn btn-primary">Go to Bookings</a>
                        </div>
                    </div>
                </div>

                <!-- Manage Bills Card -->
                <div class="col-md-4 mb-4">
                    <div class="card h-100 text-center">
                        <div class="card-body">
                            <h5 class="card-title">Manage Bills</h5>
                            <p class="card-text">Add, view, and manage billing details.</p>
                            <a href="${pageContext.request.contextPath}/bills" class="btn btn-primary">Go to Bills</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>