<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring 4 MVC</title>
<%@ page isELIgnored="false"%>
</head>
<body>
	<h2>Welcome to login page</h2>
	
	
	<form:form method="post" commandName="login"  action="/springTest/login">
		<table>
				<tr>
					<td> <form:label path="userEmail"> <spring:message code="label.email"/> </form:label></td>
					<td> <form:input path="userEmail" /></td>
					<td> <form:errors path="userEmail" class="error" /></td>
				</tr>
				<tr>
					<td> <form:label path="password"> <spring:message code="label.password"/> </form:label></td>
					<td> <form:input path="password" /></td>
					<td> <form:errors path="password" class="error" /></td>
				</tr>
	
				<tr>
					<td> Submit <input value="submit" type="submit">  </input></td>
				</tr>
		</table>
	</form:form>
	
	
	
</body>
</html>