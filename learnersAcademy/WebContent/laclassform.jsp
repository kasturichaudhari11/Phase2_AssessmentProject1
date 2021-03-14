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
<form action="addlaclass" method="post">
LearnersAcademyClass Name: <input type=text name="name"/><br/>
<select name="selectSubjects" multiple>
	<c:forEach var="subject" items="${listSubjects	}">
	<option value="<c:out value='${subject.subId}' />">${subject.subName}</option>
	</c:forEach>
</select>
<br/>
<button type="submit">Add</button>
</form>
<a href="listlaclass">Back</a>
</body>
</html>