<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>Ebay Search</title>

<style>
	table, th, td{
	border: 1px solid black;

	}
</style>
</head>



<body>
<table>
	<tr>
		<th>Item Id</th>
		<th>Name</th>
	</tr>
	
<c:forEach var="i" items="${searchResults}">
	<tr>
		<td><c:out value="${i.getItemId()}"/></td>
		<td><c:out value="${i.getName()}"/></td>
	</tr>
</c:forEach>

</table>

</body>

</html>
