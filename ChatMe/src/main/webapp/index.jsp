<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chat Login</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f2f2f2;
            text-align: center;
            padding-top: 100px;
        }

        .login-box {
            width: 350px;
            margin: auto;
            padding: 30px;
            background: white;
            border-radius: 10px;
            box-shadow: 0 0 10px #aaa;
        }

        input {
            width: 90%;
            padding: 12px;
            margin: 10px 0;
            box-sizing: border-box;
        }

        button {
            padding: 12px 30px;
            background: #333;
            color: white;
            border: none;
            cursor: pointer;
        }
    </style>
</head>

<body>

    <div class="login-box">

        <h2>SimpleChattingSystem</h2>

        <form action="login" method="post">

            <input type="text"
                   name="username"
                   placeholder="Enter Username"
                   required>

            <br>

            <button type="submit">Login</button>

        </form>

    </div>

</body>
</html>