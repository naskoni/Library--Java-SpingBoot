<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="http://jwd.bg/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<ct:HeaderMeta />
<body>
<div class="container">
	<ct:Header />
	<ct:NavigationBar />
	
	<div class="container">
		<form:form class="form-signin" action="${contextPath}${url_post}" method="post" modelAttribute="book">
			<h2 class="form-signin-heading" align="center">Book details</h2>
			
			<c:choose>
				<c:when test='${url_post eq "/editBookPost"}'>
				<input type="hidden" name="id" value="${book.id}">
				</c:when>				
			</c:choose>		
<%-- 			<input type="hidden" name="id" value="${book.id}"> --%>

	      	<label>Name:</label>
	      	<input type="text" name="name" class="form-control" value="${book.name}" required autofocus>
	      		
      		<label>Author:</label>
      		<input type="text" name="author" class="form-control" value="${book.author}" required>	 
      		    	
	      	<label>Year of publishing:</label>	      	
	      	<input type="number" name="year" class="form-control" value="${book.year}" required >
	      	
      		<label>ISBN:</label>      		
      		<input type="text" name="isbn" class="form-control" value="${book.isbn}" >
			<br>
	      	<a href="${contextPath}${url_register}" class="btn btn-lg btn-primary btn-block">Cancel</a>
	      	<button type="submit" name="login" value="1" class="btn btn-lg btn-primary btn-block">Submit</button>
		</form:form>    
	</div>
		
	<ct:Footer />
</div>
</body>
</html>