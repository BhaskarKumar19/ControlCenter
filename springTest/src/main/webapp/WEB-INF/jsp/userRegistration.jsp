<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring 4 MVC</title>
<%@ page isELIgnored="false"%>

<style>
    .error {
        color: red; font-weight: bold;
    }
</style>

</head>
<body>
	<h1>Please enter the below details to register v1 </h1>
	
	<c:if test="${logout ne null}">
   		<div class="error">You have been successfully logged out.</div>
    </c:if>
	language::${language}
	
	<form:form method="post" commandName="registrationForm"  action="/springTest/registerUser">
		<table>
				<tr>
					<td> <form:label path="userName"> <spring:message code="label.userName"/> </form:label></td>
					<td> <form:input path="userName" type="text" /></td>
					<td> <form:errors path="userName" class="error" /></td>
				</tr>
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
					<td> <form:label path="confirmPassword"> <spring:message code="label.confirmPassword"/> </form:label></td>
					<td> <form:input path="confirmPassword" /></td>
					<td> <form:errors path="confirmPassword" class="error" /></td>
				</tr>
				<tr>
					<td> Submit <input value="submit" type="submit">  </input></td>
				</tr>
		</table>
	</form:form>
</body>
</html>