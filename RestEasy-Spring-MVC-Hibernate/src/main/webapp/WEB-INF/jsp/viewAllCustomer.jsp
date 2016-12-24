<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RestEasy-Spring-MVC-Hibernate</title>
</head>
<body style="text-align: center">
	<h4>View All Customers</h4>
	<table align="center" border="1" cellpadding="10">
		<tr>
			<th>Customer ID</th>
			<th>Name</th>
			<th>Age</th>
		</tr>
		<c:forEach items="${customerListType.customerType}" var="customerType"
			varStatus="status">
			<tr>
				<td>${customerType.customerId}</td>
				<td>${customerType.name}</td>
				<td>${customerType.age}</td>
			</tr>
		</c:forEach>
	</table>
	<br />
</body>
</html>