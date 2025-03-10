<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login - Mega City Cab</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <style>
        body {
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: #f8f9fa;
        }
        .container {
            max-width: 900px;
            width: 100%;
            background: #fff;
            border-radius: 15px;
            box-shadow: 0 8px 15px rgba(0, 0, 0, 0.3);
            overflow: hidden;
        }
        .row {
            height: 100%;
        }
        .left-side {
            padding: 2.5rem;
            display: flex;
            flex-direction: column;
            justify-content: center;
        }
        .login-container h2 {
            margin-bottom: 1.5rem;
            color: #333;
            text-align: center;
            font-weight: bold;
        }
        .form-control {
            margin-bottom: 1.2rem;
            border-radius: 10px;
        }
        .btn-login {
            width: 100%;
            padding: 0.75rem;
            background-color: #ff8800;
            border: none;
            color: #fff;
            font-size: 1rem;
            border-radius: 10px;
            transition: background-color 0.3s;
        }
        .btn-login:hover {
            background-color: #cc6e00;
        }
        .error-message {
            color: #dc3545;
            text-align: center;
            margin-top: 1rem;
            font-weight: bold;
        }
        .register-link {
            text-align: center;
            margin-top: 1.2rem;
        }
        .register-link a {
            color: #ff8800;
            text-decoration: none;
        }
        .register-link a:hover {
            text-decoration: underline;
        }
        .form-label {
            color: #555;
        }
        .right-side {
            background: url('https://www.pngall.com/wp-content/uploads/10/Taxi-Driver-PNG-Image.png') no-repeat center center;
            background-size: cover;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="row">
            <!-- Left Side: Login Form -->
            <div class="col-md-6 left-side">
                <h2>Login</h2>
                <!-- Display error message if login fails -->
                <% if (request.getParameter("error") != null) { %>
                    <div class="error-message">
                        Invalid username or password. Please try again.
                    </div>
                <% } %>
                <!-- Login Form -->
                <form action="login" method="post">
                    <div class="mb-3">
                        <label for="username" class="form-label">Username</label>
                        <input type="text" class="form-control" id="username" name="username" required>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input type="password" class="form-control" id="password" name="password" required>
                    </div>
                    <button type="submit" class="btn btn-login">Login</button>
                </form>
                <!-- Register Link -->
                <div class="register-link">
                    Don't have an account? <a href="register.jsp">Create one</a>
                </div>
            </div>
            <!-- Right Side: Image -->
            <div class="col-md-6 right-side"></div>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>
