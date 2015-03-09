<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
    <title>Ebay Search</title>
</head>
<body>
     
	<c:if test="${not empty item.getId()}">
		<b>Item Id:</b> <c:out value="${item.getId()}"/><br>
	    <b>Item Name:</b> <c:out value="${item.getName()}"/><br>
	</c:if>


    <c:choose>
	    <c:when test="${not empty item.getBuyPrice()}">
	    	<b>Buy Price:</b> <fmt:formatNumber value="${item.getBuyPrice()}" type="currency" /><br>

			<c:choose>
				<c:when test="">
					<b>Credit Card:</b> <c:out value="${creditCardNumber}"><br>
					<b>Time:</b> <c:out value="${time}"><br>
				</c:when>
				<c:otherwise>
					This product was not purchased<br>
				</c:otherwise>
			</c:choose>
	    	
		</c:when>
		<c:otherwise>
			<b>Buy Price:</b> Not available for purchase<br>
		</c:otherwise>
	</c:choose>




</body>
</html>
