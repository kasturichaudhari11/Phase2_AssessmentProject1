<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Student</title>
</head>
<body>
<form action="addstudent" method="post">
Student Name: <input type=text name="name"/><br/>
Email: <input type=email name="email"/><br/>
Address: <input type=text name="address"/><br/>
Date of Birth: <input type=text name="dob"/><br/>
<button type="submit">Add</button>
</form>
</body>
<a href="liststudent">Back</a>
</html>