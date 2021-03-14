<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<c:if test = "${student != null}">
	<form action="updatestudent" method="post">
	<input type="hidden" name="id" value="<c:out value='${student.pId}' />" />
	Student Name: <input type=text name="name" value="<c:out value='${student.personName}' />"/><br/>
	Email: <input type=email name="email" value="<c:out value='${student.email}' />"/><br/>
	Address: <input type=text name="address" value="<c:out value='${student.address}' />"/><br/>
	Date of Birth: <input type=text name="dob" value="<c:out value='${student.dateOfBirth}' />"/><br/>
	<button type="submit">Update</button>
	</form>
</c:if>
<a href="liststudent">Back</a>
</body>
</html>