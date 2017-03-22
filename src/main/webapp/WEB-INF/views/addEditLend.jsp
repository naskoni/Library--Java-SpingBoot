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
		<form:form class="form-signin" action="${contextPath}${url_post}" method="post" modelAttribute="lend">
			<h2 class="form-signin-heading" align="center">Lend details</h2>
			
			<c:choose>
				<c:when test='${url_post eq "/editLendPost"}'>
					<input type="hidden" name="id" value="${lend.id}">				
				</c:when>				
			</c:choose>		

	      	<label>Choose book:</label>
	      	<select name="bookId" class="form-control">
				<c:forEach var="book" items="${books}" >
					<option ${book.id == lend.book.id ? 'selected="selected"' : ''} 
						value="${book.id}">${book.name}</option>
				</c:forEach>
			</select> 
	      	
	      	<label>Choose client:</label>
	      	<select name=clientId class="form-control">
				<c:forEach var="client" items="${clients}" >
					<option ${client.id == lend.client.id ? 'selected="selected"' : ''} 
						value="${client.id}">${client.name}</option>
				</c:forEach>
			</select> 
	      		
      		<label>Date of lending (yyyy-mm-dd):</label>	      	
	      	<input type="text" name="lendingDate" class="form-control" value="${lend.lendingDate}" required>	 
      		    	
	      	<label>Date of return (yyyy-mm-dd):</label>	      	
	      	<input type="text" name="returnDate" class="form-control" value="${lend.returnDate}" required>
	     
			<br>
	      	<a href="${contextPath}${url_register}" class="btn btn-lg btn-primary btn-block">Cancel</a>
	      	<button type="submit" name="login" value="1" class="btn btn-lg btn-primary btn-block">Submit</button>
		</form:form>    
	</div>
		
	<ct:Footer />
</div>
</body>
</html>