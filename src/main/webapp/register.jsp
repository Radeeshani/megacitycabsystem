<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Register - Mega City Cab</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <style>
        body {
            background-color: #f8f9fa;
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0;
        }
        .container-custom {
            width: 80%;
            height: 80%;
            display: flex;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            overflow: hidden;
        }
        .form-section {
            width: 50%;
            background-color: #fff;
            padding: 2rem;
            display: flex;
            flex-direction: column;
            justify-content: center;
        }
        .form-section h2 {
            margin-bottom: 1.5rem;
            color: #333;
            text-align: center;
        }
        .form-control {
            margin-bottom: 1rem;
        }
        .btn-register {
            width: 100%;
            padding: 0.75rem;
            background-color: #ff8800;
            border: none;
            color: #fff;
            font-size: 1rem;
            border-radius: 5px;
        }
        .btn-register:hover {
            background-color: #ff8800;
        }
        .error-message {
            color: #dc3545;
            text-align: center;
            margin-top: 1rem;
        }
        .login-link {
            text-align: center;
            margin-top: 1rem;
        }
        .login-link a {
            color: #ff8800;
            text-decoration: none;
        }
        .login-link a:hover {
            text-decoration: underline;
        }
        .image-section {
            width: 50%;
            background: url('https://media.istockphoto.com/id/869683836/vector/family-in-the-taxi-cab.jpg?s=612x612&w=0&k=20&c=PkgoKuNlukbxQA6d87Z_Rz7tFpugWCp2F9DRnX9YRiU=') center center/cover no-repeat;
        }
    </style>
</head>
<body>

<div class="container-custom">
    <!-- Image Section -->
    <div class="image-section"></div>

    <!-- Registration Form Section -->
    <div class="form-section">
        <h2>Register</h2>
        <% if (request.getParameter("error") != null) { %>
            <div class="error-message">
                Registration failed. Please try again.
            </div>
        <% } %>
        <form action="<%= request.getContextPath() %>/register" method="post">
            <!-- User Details -->
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <!-- Customer Details -->
            <div class="mb-3">
                <label for="name" class="form-label">Full Name</label>
                <input type="text" class="form-control" id="name" name="name" required>
            </div>
            <div class="mb-3">
                <label for="address" class="form-label">Address</label>
                <input type="text" class="form-control" id="address" name="address" required>
            </div>
            <div class="mb-3">
                <label for="nic" class="form-label">NIC</label>
                <input type="text" class="form-control" id="nic" name="nic" required>
            </div>
            <div class="mb-3">
                <label for="phone" class="form-label">Phone</label>
                <input type="text" class="form-control" id="phone" name="phone" required>
            </div>
            <button type="submit" class="btn btn-register">Register</button>
        </form>
        <div class="login-link">
            Already have an account? <a href="login.jsp">Login</a>
        </div>
    </div>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>
