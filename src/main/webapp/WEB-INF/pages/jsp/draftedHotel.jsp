<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<h1 align="center">Drafted Hotel Not Booked</h1>
<body>
	<c:if test="${fn:length(draftedHotel) == 0}">
		<h4>Not drafted Hotel</h4>
	</c:if>
	<c:if test="${fn:length(draftedHotel) gt 0}">
		<table align="center">
			<c:forEach items="${draftedHotel }" var="data">
				<tr>
					<td colspan="2" rowspan="4"><img src="${data.url}" width="100"
						height="100" /></td>
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
	</c:if>
</body>
</html>