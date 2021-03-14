<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Subject</title>
</head>
<body>

<c:if test = "${subject != null}">
	<form action="updatesubject" method="post">
	<input type="hidden" name="id" value="<c:out value='${subject.subId}' />" />
	Subject Name: <input type=text name="subjectname" value="<c:out value='${subject.subName}'/>"/><br/>
	<button type="submit">Update</button>
	</form>
</c:if>
<a href="listsubject">Back</a>
</body>
</html>