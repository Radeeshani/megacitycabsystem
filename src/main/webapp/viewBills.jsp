<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.megacitycab.model.Bill" %>
<%@ page import="com.megacitycab.model.Booking" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>View Bills - Mega City Cab</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <style>
        .container {
            margin-top: 2rem;
        }
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
        .table-container {
            background: #fff;
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        .table-container h2 {
            margin-bottom: 1.5rem;
            color: #333;
            text-align: center;
        }
        .btn-action {
            margin: 0 0.25rem;
        }
        @media print {
            body * {
                visibility: hidden;
            }
            .print-section, .print-section * {
                visibility: visible;
            }
            .print-section {
                position: absolute;
                left: 0;
                top: 0;
                width: 100%;
            }
        }
    </style>
</head>
<body>
    <!-- Navbar -->
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
            <div class="col-md-10">
                <div class="table-container">
                    <h2>Bill List</h2>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Booking ID</th>
                                <th>Total Amount</th>
                                <th>Tax</th>
                                <th>Discount</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% List<Bill> bills = (List<Bill>) request.getAttribute("bills");
                               for (Bill bill : bills) { %>
                                <tr>
                                    <td><%= bill.getBookingId() %></td>
                                    <td><%= bill.getTotalAmount() %></td>
                                    <td><%= bill.getTax() %></td>
                                    <td><%= bill.getDiscount() %></td>
                                    <td>
                                        <button onclick="printBill('<%= bill.getBillId() %>', '<%= bill.getBookingId() %>', '<%= bill.getTotalAmount() %>', '<%= bill.getTax() %>', '<%= bill.getDiscount() %>')" class="btn btn-success btn-action">Print</button>
                                    </td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- Completed Bookings Section -->
        <div class="row justify-content-center mt-4">
            <div class="col-md-10">
                <div class="table-container">
                    <h2>Completed Bookings</h2>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Booking ID</th>
                                <th>Pickup Location</th>
                                <th>Destination</th>
                                <th>Contact Number</th>
                                <th>Booking Date</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
                               if (bookings != null) {
                                   for (Booking booking : bookings) {
                                       if ("Completed".equals(booking.getStatus())) { %>
                                        <tr>
                                            <td><%= booking.getBookingId() %></td>
                                            <td><%= booking.getPickupLocation() %></td>
                                            <td><%= booking.getDestination() %></td>
                                            <td><%= booking.getContactNumber() %></td>
                                            <td><%= booking.getBookingDate() %></td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/addBill.jsp?bookingId=<%= booking.getBookingId() %>" class="btn btn-primary btn-action">Add Bill</a>
                                            </td>
                                        </tr>
                                    <% }
                                   }
                               } else { %>
                                <tr>
                                    <td colspan="6" class="text-center">No completed bookings found.</td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <!-- Print Section (Hidden by default) -->
    <div id="printSection" class="print-section" style="display: none;">
        <h2>Bill Details</h2>
        <p><strong>Bill ID:</strong> <span id="printBillId"></span></p>
        <p><strong>Booking ID:</strong> <span id="printBookingId"></span></p>
        <p><strong>Tax:</strong> <span id="printTax"></span>%</p>
        <p><strong>Discount:</strong> <span id="printDiscount"></span>%</p>
        <p><strong>Total Amount: Rs.</strong> <span id="printTotalAmount"></span></p>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
    <!-- Print Functionality -->
    <script>
        function printBill(billId, bookingId, totalAmount, tax, discount) {
            document.getElementById('printBillId').innerText = billId;
            document.getElementById('printBookingId').innerText = bookingId;
            document.getElementById('printTotalAmount').innerText = totalAmount;
            document.getElementById('printTax').innerText = tax;
            document.getElementById('printDiscount').innerText = discount;

            document.getElementById('printSection').style.display = 'block';
            window.print();
            document.getElementById('printSection').style.display = 'none';
        }
    </script>
</body>
</html>