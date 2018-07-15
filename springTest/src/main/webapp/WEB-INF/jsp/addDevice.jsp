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
	<h2>Add Your new Device</h2>
	
	<form:form method="post" commandName="addDevice"  action="/springTest/addDevice">
		<table>
				<tr>
					<td> <form:label path="deviceName"> <spring:message code="label.device.deviceName"/> </form:label></td>
					<td> <form:input path="deviceName" /></td>
					<td> <form:errors path="deviceName" class="error" /></td>
				</tr>
				<tr>
					<td> <form:label path="description"> <spring:message code="label.device.description"/> </form:label></td>
					<td> <form:input path="description" /></td>
					<td> <form:errors path="description" class="error" /></td>
				</tr>
	
				<tr>
					<td> Submit <input value="submit" type="submit">  </input></td>
				</tr>
		</table>
	</form:form>
	
	
	
</body>
</html>