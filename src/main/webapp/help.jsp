<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Help - Mega City Cab</title>
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
        .help-container {
            background: rgba(255, 255, 255, 0.9);
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            margin-bottom: 2rem;
        }
        .help-container h2 {
            margin-bottom: 1.5rem;
            color: #ff8800;
            text-align: center;
        }
        .help-container h3 {
            margin-top: 1.5rem;
            color: #007bff;
        }
        .help-container p {
            font-size: 1rem;
            line-height: 1.6;
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

    <!-- Help Content -->
    <div class="container">
        <div class="help-container">
            <h2>System Usage Guidelines</h2>
            <p>Welcome to Mega City Cab! Here are some guidelines to help you use the system effectively:</p>

            <h3>1. Booking a Cab</h3>
            <p>
                To book a cab, go to the <strong>Home</strong> page and fill out the "Book a Cab" form. Provide your pickup location, destination, and contact number. Click <strong>Book Now</strong> to confirm your booking.
            </p>

            <h3>2. Viewing Your Bookings</h3>
            <p>
                After booking a cab, you can view all your bookings in the <strong>My Bookings</strong> section on the Home page. This section displays details such as booking ID, pickup location, destination, contact number, booking date, and status.
            </p>

            <h3>3. Checking Booking Status</h3>
            <p>
                The status of your booking (e.g., Pending, Completed, Cancelled) is displayed in the <strong>My Bookings</strong> table. If you have any questions about your booking status, please contact customer support.
            </p>

            <h3>4. Logging Out</h3>
            <p>
                To log out of the system, click the <strong>Logout</strong> button in the top-right corner of the page. This will securely end your session.
            </p>

            <h3>5. Need Further Assistance?</h3>
            <p>
                If you encounter any issues or have additional questions, please contact our support team at <strong>support@megacitycab.com</strong> or call <strong>+1-800-123-4567</strong>.
            </p>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>