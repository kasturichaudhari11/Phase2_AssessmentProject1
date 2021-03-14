<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Teachers</title>
</head>
<body>
<br/>      
<a href="newteacher">Add New Teacher</a>&nbsp;&nbsp;&nbsp;<a href="adminOperation.jsp">Main Menu</a>
<br/>      
<br/>      
	<table>
	<caption><h3>List of Teachers</h3></caption>
	<tr/>
	<tr>
		<th>Name</th>
		<th>Email</th>
		<th>Address</th>
		</tr>
    <c:forEach var="teacher" items="${listTeachers}">
		<tr>	
		<td><c:out value="${teacher.personName}" /></td>
		<td><c:out value="${teacher.email}" /></td>
		<td><c:out value="${teacher.address}" /></td>
		<td><a href="editteacher?id=<c:out value='${teacher.pId}' />">Edit</a><td>
		<td><a href="deleteteacher?id=<c:out value='${teacher.pId}' />">Delete</a><td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>
