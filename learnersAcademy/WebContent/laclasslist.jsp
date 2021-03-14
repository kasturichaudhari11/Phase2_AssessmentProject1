<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LearnersAcademyClasss</title>
</head>
<body>
<br/>      
<a href="newlaclass">Add New LearnersAcademyClass</a>&nbsp;&nbsp;&nbsp;<a href="adminOperation.jsp">Main Menu</a>
<br/>      
<br/>      
<table>
<caption><h3>List of LearnersAcademyClasss</h3></caption>
   <c:forEach var="laclass" items="${listLearnersAcademyClass}">
	<tr>	
	<td><c:out value="${laclass.className}" /></td>
	<td>
		<table>
			<c:forEach var="subject" items="${laclass.subjectList}">
			<tr>	
			<td><c:out value="${subject.subName}" /></td>
			</tr>
			</c:forEach>
		</table>
	</td>
	<td><a href="editlaclass?id=<c:out value='${laclass.cId}' />">Edit</a><td>
	<td><a href="deletelaclass?id=<c:out value='${laclass.cId}' />">Delete</a><td>
	</tr>
</c:forEach>
</table>
</body>
</html>
