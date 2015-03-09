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
		    <form action="${confirmationUrl}" method="post">
		    	<b>Credit Card:</b> <input type="text" name="creditCardNumber">
		        <input type="submit" value="Submit"><br><br>
		    </form>
		</c:when>
		<c:otherwise>
			Not available for purchase
		</c:otherwise>
	</c:choose>


</body>
</html>
