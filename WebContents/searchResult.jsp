<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
	<title>Ebay Search</title>
    <link rel="stylesheet" type="text/css" href="autoSuggest.css">
    <script type="text/javascript" src="autoSuggest.js"></script>
    <script type="text/javascript" src="suggestions.js"></script>
    <script type="text/javascript">
        window.onload = function () {
            var oTextbox = new AutoSuggestControl(document.getElementById("query"), new Suggestions());
        }
    </script>
</head>



<body>



<form action="search" method="get" autocomplete="off">
	Keywords to search: <input type="text" name="q" id="query">
	<br>
	<input type="hidden" name="numResultsToSkip" value="0" />
	<input type="hidden" name="numResultsToReturn" value="20" />
	<input type ="submit" value="Search!">
</form>

	


<hr>



<p id="previous">Previous</p>
<p id="next">Next</p>



<script>
var q = '${q}';
var numResultsToSkip = ${numResultsToSkip};
var numResultsToReturn = '20';
var n_numResultsToSkip = numResultsToSkip + 20;
var numResults = parseInt("${fn:length(searchResults)}");
	
if (numResults == 20) {
    document.getElementById("next").innerHTML = '<a href="search?q=' + q + '&numResultsToSkip=' + n_numResultsToSkip.toString() + '&numResultsToReturn=' + numResultsToReturn + '">Next</a>';
}

if (numResultsToSkip >= 20){
	var b_numResultsToSkip = numResultsToSkip - 20;
	document.getElementById("previous").innerHTML = '<a href="search?q=' + q + '&numResultsToSkip=' + b_numResultsToSkip.toString() + '&numResultsToReturn=' + numResultsToReturn + '">Previous</a>';
}


</script>

<c:choose>
    <c:when test="${empty searchResults}">
        <p>No results matching query: "${q}"</p>
    </c:when>
    <c:otherwise>
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
    </c:otherwise>
</c:choose>


</body>

</html>
