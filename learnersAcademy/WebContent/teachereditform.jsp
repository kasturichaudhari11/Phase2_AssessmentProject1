<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Teacher</title>
</head>
<body>

<c:if test = "${teacher != null}">
	<form action="updateteacher" method="post">
	<input type="hidden" name="id" value="<c:out value='${teacher.pId}' />" />
	Teacher Name: <input type=text name="name" value="<c:out value='${teacher.personName}' />"/><br/>
	Email: <input type=email name="email" value="<c:out value='${teacher.email}' />"/><br/>
	Address: <input type=text name="address" value="<c:out value='${teacher.address}' />"/><br/>
	<button type="submit">Update</button>
	</form>
</c:if>
<a href="listteacher">Back</a>
</body>
</html>