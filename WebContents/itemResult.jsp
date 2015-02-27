<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>Ebay Search</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }
        html, body {
            width: 100%;
            margin: 0;
            padding: 0;
        }
        #map-canvas {
            width: 400px;
            height: 400px;
            margin: 0;
            padding: 0;
            float: right;
        }
    </style>
    <script type="text/javascript" src="http://maps.google.com/maps/api/js?key=AIzaSyAdZLtQ08dr2cH8qD3-dPlaMLtu3hKhMTo">
    </script>
    <script type="text/javascript">
        function initialize() {
            var lat = ${item.getLocation().getLatitude()};
            var lng = ${item.getLocation().getLongitude()};
            var latlng = new google.maps.LatLng(lat, lng);
            var mapOptions = {
                zoom: 14, // default is 8
                center: latlng
            };
            var map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
        }
        google.maps.event.addDomListener(window, 'load', initialize);
    </script>
</head>

<body>

<form action="item" method="get">
    Item Id: <input type="number" name="id">
    <input type="submit" value="Search">
</form>

<hr>

<div id="map-canvas"></div>

<b>Item Id:</b> <c:out value="${item.getId()}"/><br>
<b>Item Name:</b> <c:out value="${item.getName()}"/><br>
<br><br>

<table>
	<tr>
		<th> Categories</th>
	</tr>

	<c:forEach var="i" items="${item.getCategories()}">
	<tr>
		<td><c:out value="${i}"/></td>
	</tr>
	</c:forEach>
</table>

<br><br>

<b>Currently:</b> <c:out value="${item.getCurrently()}"/><br>
<b>Buy Price:</b> <c:out value="${item.getBuyPrice()}"/><br>
<b>First Bid:</b> <c:out value="${item.getFirstBid()}"/><br>
<b>Number of Bids:</b> <c:out value="${item.getNumberOfBids()}"/><br>
<b>Location:</b> <c:out value="${item.getLocation().getName()}"/><br>
<b>Latitude:</b> <c:out value="${item.getLocation().getLatitude()}"/><br>
<b>Longitude:</b> <c:out value="${item.getLocation().getLongitude()}"/><br>
<b>Country:</b> <c:out value="${item.getCountry()}"/><br>
<b>Starts:</b> <c:out value="${item.getStarted()}"/><br>
<b>Ends:</b> <c:out value="${item.getEnds()}"/><br>
<b>Seller Id:</b> <c:out value="${item.getSeller().getId()}"/><br>
<b>Seller Rating:</b> <c:out value="${item.getSeller().getRating()}"/><br>
<b>Description:</b> <c:out value="${item.getDescription()}"/><br>



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
	<c:forEach var="i" items="${item.getBids()}">
	<tr>
		<td><c:out value="${i.getBidder().getId()}"/></td>
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



