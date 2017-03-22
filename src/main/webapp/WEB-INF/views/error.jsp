<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<body>	
	<h2>Ooops!</h2>
	
	<c:if test="${not empty errorMessage}">
		<h2>${errorMessage}</h2>
	</c:if>
	
	<p>Press back to return to previous page.</p>
</body>
</html>