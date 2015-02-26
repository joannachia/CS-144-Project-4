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


<b>Item Id:</b> <c:out value="${itemId}"/><br>
<b>Item Name:</b> <c:out value="${itemName}"/><br>
<br><br>

<table>
	<tr>
		<th> Categories</th>
	</tr>

	<c:forEach var="i" items="{$categories}">
	<tr>
		<td><c:out value="${i}"/></td>
	</tr>
	</c:forEach>
</table>

<br><br>

<b>Currently:</b> <c:out value="${currently}"/><br>
<b>






<c:forEach var="i" items="${searchResults}">
	<tr>
		<td><c:out value="${i.getItemId()}"/></td>
		<td><c:out value="${i.getName()}"/></td>
	</tr>
</c:forEach>

</table>

</body>

</html>



