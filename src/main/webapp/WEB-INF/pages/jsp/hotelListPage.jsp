<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<h1 align="center">List Of Hotels</h1>
</head>
<body>
	<table align="center">
		<c:forEach items="${hotelList }" var="data">
			<tr>
				<td colspan="2" rowspan="4"><img
					src="${data.url}"
					width="100" height="100" /></td>
				<td>Name : ${data.name }</td>
			</tr>
			<tr>
				<td>City : ${data.city }</td>
			</tr>
			<tr>
				<td>Price : ${data.price }</td>
			</tr>
			<tr>
				<td><a href="/hotel/getHotel?hotelId=${data.id}">Book</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>