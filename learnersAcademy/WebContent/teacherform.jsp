<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Teacher</title>
</head>
<body>
<form action="addteacher" method="post">
Teacher Name: <input type=text name="name"/><br/>
Email: <input type=email name="email"/><br/>
Address: <input type=text name="address"/><br/>
<button type="submit">Add</button>
</form>
</body>
<a href="listteacher">Back</a>
</html>