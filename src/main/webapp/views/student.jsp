<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register page</title>
</head>
<body>
<h1 align="center">Welcome to the register page. Input your name and get your ID.</h1>
<p align="center">
	<form action="/student" method="post">
	First Name : <input type="text" name="first_name"> <br>
	Last Name : <input type="text" name="last_name"> <br>
	<input type="submit" value="Confirm"> <br>
	</form>
	<br>
	Your ID is: ${id} <br>
<p>
	<p align="left"><a href="/">Back</a><p>
</body>
</html>
