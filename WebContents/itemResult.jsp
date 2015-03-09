<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
            var lat = parseFloat("${item.getLocation().getLatitude()}"),
                lng = parseFloat("${item.getLocation().getLongitude()}"),
                locationName = "${item.getLocation().getName()}",
                mapOptions = {
                    zoom: 14
                },
                map,
                mapCanvas = document.getElementById("map-canvas");
            if (!isNaN(lat) && !isNaN(lng)) {
                mapOptions.center = new google.maps.LatLng(lat, lng);
                map = new google.maps.Map(mapCanvas, mapOptions);
            } else if (locationName !== "") {
                codeAddress(locationName, function(location) {
                    console.log(location.lat(), location.lng());
                    mapOptions.center = location;
                    map = new google.maps.Map(mapCanvas, mapOptions);
                }, function() {
                    hideElement(mapCanvas);
                })
            } else {
                hideElement(mapCanvas);
            }
        }

        function hideElement(element) {
            element.style.display = "none";
        }

        function codeAddress(address, successCallback, failureCallback) {
            var geocoder = new google.maps.Geocoder();
            geocoder.geocode( { 'address': address}, function(results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                    successCallback(results[0].geometry.location);
                } else {
                    failureCallback();
                }
            });
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

<c:choose>
    <c:when test="${empty item}">
        <p>No item: ${id}</p>
    </c:when>
    <c:otherwise>


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

        <b>Currently:</b> <fmt:formatNumber value="${item.getCurrently()}" type="currency" /><br>
        <c:if test="${not empty item.getBuyPrice()}">
            <b>Buy Price:</b> <fmt:formatNumber value="${item.getBuyPrice()}" type="currency" /><br>
            <form action="creditCardInput">
                <input type="submit" value="Pay Now"><br><br>
            </form>
        </c:if>
        <b>First Bid:</b> <fmt:formatNumber value="${item.getFirstBid()}" type="currency" /><br>
        <b>Number of Bids:</b> <c:out value="${item.getNumberOfBids()}"/><br>
        <b>Location:</b> <c:out value="${item.getLocation().getName()}"/><br>
        <b>Latitude:</b> <c:out value="${item.getLocation().getLatitude()}"/><br>
        <b>Longitude:</b> <c:out value="${item.getLocation().getLongitude()}"/><br>
        <b>Country:</b> <c:out value="${item.getCountry()}"/><br>
        <b>Starts:</b> <c:out value="${item.getStarted()}"/><br>
        <b>Ends:</b> <c:out value="${item.getEnds()}"/><br>
        <b>Seller Id:</b> <c:out value="${item.getSeller().getId()}"/><br>
        <b>Seller Rating:</b> <fmt:formatNumber type="number" value="${item.getSeller().getRating()}" /><br>
        <b>Description:</b> <c:out value="${item.getDescription()}"/><br>


        <c:if test="${fn:length(item.getBids()) > 0}">
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
                    <td><fmt:formatNumber value="${i.getBidder().getRating()}" type="number"/></td>
                    <td><c:out value="${i.getBidder().getLocation().getName()}"/></td>
                    <td><c:out value="${i.getBidder().getLocation().getLatitude()}"/></td>
                    <td><c:out value="${i.getBidder().getLocation().getLongitude()}"/></td>
                    <td><c:out value="${i.getBidder().getCountry()}"/></td>
                    <td><c:out value="${i.getTime()}"/></td>
                    <td><fmt:formatNumber value="${i.getAmount()}" type="currency" /></td>
                </tr>
                </c:forEach>
            </table>
        </c:if>
    </c:otherwise>
</c:choose>






</body>

</html>



