<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
	<form:form method="post" commandName="registrationForm"  action="/springTest/registerUser">
		<table>
				<tr>
					<td> <form:label path="userName"> User Name </form:label></td>
					<td> <form:input path="userName" type="text" /></td>
					<td> <form:errors path="userName" class="error" /></td>
				</tr>
				<tr>
					<td> <form:label path="userEmail"> Email </form:label></td>
					<td> <form:input path="userEmail" /></td>
					<td> <form:errors path="userEmail" class="error" /></td>
				</tr>
				<tr>
					<td> Submit <input value="submit" type="submit">  </input></td>
				</tr>
		</table>
	</form:form>
</body>
</html>