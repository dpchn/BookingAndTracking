<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table align="center">
		<tr>
			<td><table>
					<tr>
						<td colspan="2"><img src="${data.url}" width="100" height="100" /></td>
					</tr>
					<tr>
						<td colspan="2">${data.name }</td>
					</tr>
					<tr>
						<td colspan="2">City : ${data.city }</td>
					</tr>
				</table></td>
			<td><table>
					<form action="book" method="post" name="HotelBookForm">
						<tr>
							<td>From : <input type="date" name="startDate"></td>
							<td>To : <input type="date" name="endDate"></td>
						</tr>
						<tr>
							<td colspan="2">No. Of People : <input type="text"
								name="noOfPeople" ></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input type="submit"
								value="Book"></td>
						</tr>
					</form>
				</table></td>
		</tr>
	</table>
</body>
</html>