<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form method = "post" action="LoginServlet">
Username: <input type = "text" name = "uname"/><br/>
Password: <input type = "password" name = "password"/><br/>
<button type = "submit">Login</button>${message} 
</form>
</body>
</html>