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
<b>Buy Price:</b> <c:out value="${buyPrice}"/><br>
<b>First Bid:</b> <c:out value="${firstBid}"/><br>
<b>Number of Bids:</b> <c:out value="${numb_bids}"/><br>
<b>Location:</b> <c:out value="${location.getName()}"/><br>
<b>Latitude:</b> <c:out value="${location.getLatitude()}"/><br>
<b>Longitude:</b> <c:out value="${location.getLongitude()}"/><br>
<b>Country:</b> <c:out value="${country}"/><br>
<b>Starts:</b> <c:out value="${starts}"/><br>
<b>Ends:</b> <c:out value="${ends}"/><br>
<b>Seller Id:</b> <c:out value="${seller.getId()}"/><br>
<b>Seller Rating:</b> <c:out value="${seller.getRating()}"/><br>
<b>Description:</b> <c:out value="${description}"/><br>


<table>
	<tr>
		<th>User Id</th>
		<th>Rating</th>
		<th>Location</th>
		<th>Latitude</th>
		<th>Longitude</th>
		<th>Country</th>
		<th>Time</th>
		<th>Amount</th>

	</tr>
	<c:forEach var="i" items="{$bids}">
	<tr>
		<td><c:out value="${i.getBidder().getUserId()}"/></td>
		<td><c:out value="${i.getBidder().getRating()}"/></td>
		<td><c:out value="${i.getBidder().getLocation().getName()}"/></td>
		<td><c:out value="${i.getBidder().getLocation().getLatitude()}"/></td>
		<td><c:out value="${i.getBidder().getLocation().getLongitude()}"/></td>
		<td><c:out value="${i.getBidder().getCountry()}"/></td>
		<td><c:out value="${i.getTime()}"/></td>
		<td><c:out value="${i.getAmount()}"/></td>
	</tr>
	</c:forEach>
</table>






</body>

</html>



