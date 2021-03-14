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

<c:if test = "${laclass != null}">
	<form action="updatelaclass" method="post">
	<input type="hidden" name="id" value="<c:out value='${laclass.cId}' />" />
	LearnersAcademyClass Name: <input type=text name="name" value="<c:out value='${laclass.className}'/>"/><br/>
	<button type="submit">Update</button>
	</form>
</c:if>
<a href="listlaclass">Back</a>
</body>
</html>