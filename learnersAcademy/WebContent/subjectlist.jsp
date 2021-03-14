<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Subjects</title>
</head>
<body>
<br/>      
<a href="newsubject">Add New Subject</a>&nbsp;&nbsp;&nbsp;<a href="adminOperation.jsp">Main Menu</a>
<br/>      
<br/>      
<table>
<caption><h3>List of Subjects</h3></caption>
   <c:forEach var="subject" items="${listSubjects}">
	<tr>	
	<td><c:out value="${subject.subName}" /></td>
	<td><a href="editsubject?id=<c:out value='${subject.subId}' />">Edit</a><td>
	<td><a href="deletesubject?id=<c:out value='${subject.subId}' />">Delete</a><td>
	</tr>
</c:forEach>
</table>
</body>
</html>
