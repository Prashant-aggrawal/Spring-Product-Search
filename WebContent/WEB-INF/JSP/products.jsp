<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<b>The product table</b>
	<table>
		<tr>
			<td>createdDate</td>
			<td></td>
			<td>imageUrl</td>
		</tr>

		<c:forEach var="product" items="${products}">
			<tr>
				<td><c:out value="${product.createdDate}"></c:out></td>
				<td></td>
				<td><c:out value="${product.imageUrl}"></c:out></td>
			</tr>

		</c:forEach>

	</table>
</body>
</html>