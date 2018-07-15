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
	<h2>Welcome to User Admin Console</h2>
	<h3>Hi ${authenticatedUser.userEmail} !</h3>
	
	<ul title="Device Management">
		<li><a href="addDeviceForm"> Add a new device </a></li>
	</ul>	
	
	<ul title="My Devices">
		<li><a href="deviceList"> My devices </a></li>
	</ul>	
	
	<table>
	<caption>My Devices</caption>
		<tr>
			<th>Device Id</th>
			<th>Device name</th>
			<th>Description</th>
			<th>Access Key</th>
			<th>Device status</th>
		</tr>
		<c:forEach items="${user.devices}" var="element"> 
		  <tr>
		    <td>${element.deviceId}</td>
		    <td>${element.deviceName}</td>
		    <td>${element.description}</td>
		    <td>${element.accessKey}</td>
		    <td>${element.deviceStatus}</td>
		  </tr>
		</c:forEach>
	</table>
	
	
	<table>
	<caption>Topics</caption>
		<tr>
			<th>Device_ID</th>
			<th>SUB</th>
			<th>PUB</th>
		</tr>
		<c:forEach items="${user.devices}" var="device"> 
		<c:forEach items="${device.pubSubTopics}" var="pubSubTopics"> 
		  <tr>
		    <td>${pubSubTopics.id}</td>
		    <td>${pubSubTopics.publishTopic}</td>
		    <td>${pubSubTopics.subscribeTopic}</td>
		  </tr>
		 </c:forEach>
		</c:forEach>
	</table>
	

	<h3><a href="logout">Logout</a></h3>
</body>
</html>