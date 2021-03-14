<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Students</title>
</head>
<body>
<br/>      
<a href="newstudent">Add New Student</a>&nbsp;&nbsp;&nbsp;<a href="adminOperation.jsp">Main Menu</a>
<br/>      
<br/>      
	<table>
	<caption><h3>List of Students</h3></caption>
	<tr/>
	<tr>
		<th>Name</th>
		<th>Email</th>
		<th>Address</th>
		<th>Date of Birth</th>
		</tr>
    <c:forEach var="student" items="${listStudents}">
		<tr>	
		<td><c:out value="${student.personName}" /></td>
		<td><c:out value="${student.email}" /></td>
		<td><c:out value="${student.address}" /></td>
		<td><c:out value="${student.dateOfBirth}" /></td>
		<td><a href="editstudent?id=<c:out value='${student.pId}' />">Edit</a><td>
		<td><a href="deletestudent?id=<c:out value='${student.pId}' />">Delete</a><td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>
