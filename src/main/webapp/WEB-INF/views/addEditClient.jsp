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
		<form:form class="form-signin" action="${contextPath}${url_post}" method="post" modelAttribute="client">
			<h2 class="form-signin-heading" align="center">Client details</h2>
			
			<c:choose>
				<c:when test='${url_post eq "/editClientPost"}'>
					<input type="hidden" name="id" value="${client.id}">				
				</c:when>				
			</c:choose>		

	      	<label>Name:</label>
	      	<input type="text" name="name" class="form-control" value="${client.name}" required autofocus>
	      		
      		<label>PID:</label>
      		<input type="number" name="pid" class="form-control" value="${client.pid}" required>	 
      		    	
	      	<label>Date of birth (yyyy-mm-dd):</label>	      	
	      	<input type="text" name="birthdate" class="form-control" value="${client.birthdate}" >
	     
			<br>
	      	<a href="${contextPath}${url_register}" class="btn btn-lg btn-primary btn-block">Cancel</a>
	      	<button type="submit" name="login" value="1" class="btn btn-lg btn-primary btn-block">Submit</button>
		</form:form>    
	</div>
		
	<ct:Footer />
</div>
</body>
</html>