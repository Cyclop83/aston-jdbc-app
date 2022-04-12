<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Deletion Page</title>
</head>
<h1 align="center">Welcome! Input student ID to delete his record.</h1>
<body>
    <form action="/student_delete" method="post">
    	Enter Your ID : <input type="text" name="student_id"> <br>
    	<input type="submit" value="Confirm"> <br>
    </form>
    <br>
    ${message}
    <br>
    <br>
    <p align="left"><a href="/">Back</a><p>
</body>
</html>