<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring 4 MVC</title>
<%@ page isELIgnored="false"%>
</head>
<body>
	<h2>Registration successful</h2>
	<ul>
		<li>userName:${name}</li>
		<li>Email:${email}</li>
		<li>Password:${user.password}</li>
		<li>ConfirmPassword::${user.confirmPassword}</li>
	</ul>
	
	<table>
	<caption>Registered Users Details</caption>
		<tr>
			<th>User Name</th>
			<th>Email</th>
			<th>Access Key</th>
		</tr>
		<c:forEach items="${userList}" var="element"> 
		  <tr>
		    <td>${element.userName}</td>
		    <td>${element.userEmail}</td>
		    <td>${element.accessToken}</td>
		  </tr>
		</c:forEach>
	</table>
	
	
	<table>
	<caption>Device Details</caption>
		<tr>
			<th>Device name</th>
			<th>device</th>
			<th>Access Key</th>
		</tr>
		<c:forEach items="${deviceList}" var="element"> 
		  <tr>
		    <td>${element.deviceId}</td>
		    <td>${element.deviceName}</td>
		    <td>${element.deviceStatus}</td>
		  </tr>
		</c:forEach>
	</table>
		
	
	<h3><a href="logout">Logout</a></h3>
</body>
</html>