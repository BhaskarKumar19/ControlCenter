<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
<%@ page isELIgnored="false"%>
</head>
<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

	
<h2>Welcome !</h2>

<ul title="New USer">
<li><a href="userRegistration"> Registration Link</a></li>
</ul>

<ul title="Existing USer">
<li><a href="${contextPath}/login">Login</a></li>
</ul>
	
</body>
</html>