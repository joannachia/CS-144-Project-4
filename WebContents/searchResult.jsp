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

<a href="">Back</a> || <a href="">Next</a>
<hr>


<form action="search" method="get">
	Keywords to search: <input type="text" name="q">
	<br>
	Number of Results to Skip: <input type="number" name="numResultsToSkip" onkeypress="return isNumberKey(event)">
	<br>
	Number of Results to Return: <input type="number" name="numResultsToReturn" onkeypress="return isNumberKey(event)">
	<br>
	<input type ="submit" value="Search!">
</form>

	
<script>
	function isNumberKey(evt){
		var charCode = (evt.which) ? evt.which : event.keyCode
		if(charCode > 31 
			&& (charCode < 48 || charCode > 57)){
			return false;
		}
		return true;
	}
</script>



<br>....................................<br>

<table>
	<tr>
		<th>Item Id</th>
		<th>Name</th>
	</tr>
	
<c:forEach var="i" items="${searchResults}">
	<tr>
		<td><a href="item?id=${i.getItemId()}"><c:out value="${i.getItemId()}"/></a></td>
		<td><c:out value="${i.getName()}"/></td>
	</tr>
</c:forEach>

</table>

</body>

</html>
