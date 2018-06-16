<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<h1 align="center">Welcome To Home</h1>
</head>
<body>
	<div align="center">
		<table>
			<tr>
				<td>
					<div>
						<h3>Profile Details</h3>

						<table align="left">
							<tr>
								<th>Name :</th>
								<td>${data.name }</td>
							</tr>
							<tr>
								<th>Name :</th>
								<td>${data.email }</td>
							</tr>

							<tr>
								<th>Last login :</th>
								<td>${data.lastLogin}</td>
							</tr>
							<tr>
								<td colspan="2"><a href="/hotel/getHotelList">Search
										Hotel</a></td>
							</tr>
							<tr>
								<td colspan="2"><a href="/hotel/draftedHotel">Drafted Hotel</a></td>
							</tr>
						</table>
					</div>
				</td>
				<td colspan="4"></td>
				<td>
					<div>
						<h3>Booking Hisotry</h3>
					</div>
					<div>
						<table align="right">
							<c:if test="${fn:length(history) gt 0}">
								<c:forEach var="data" items="${history }">
									<tr>
										<td>
											<div>Hotel : ${data.name }</div>
											<div>Start Date : ${data.startDate }</div>
											<div>End Date : ${data.endDate }</div> -----------------
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</table>
					</div>
				</td>
			</tr>
			<c:if test="${fn:length(searchedHotel) gt 0}">
				<tr>
					<td colspan="4">
						<div>
							<h3>Recomendations</h3>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<table>
							<c:forEach var="data" items="${searchedHotel }">
								<tr>
									<div>
										<img src="${data.url}" width="100" height="100" />
									</div>
									<div>Hotel Name : ${ data.name}</div>
									<div>City : ${data.city}</div>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
			</c:if>
		</table>
	</div>
</body>

</html>