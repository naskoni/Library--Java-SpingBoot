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
		<form:form class="form-signin" action="${contextPath}${url_post}" method="post" modelAttribute="user">
			<h2 class="form-signin-heading" align="center">User details</h2>
			
			
			<label>Username:</label>
	      	<input type="text" name="username" class="form-control" required autofocus>		

	      	<label>Name:</label>
	      	<input type="text" name="name" class="form-control" required>
	      		
      		<label>Password:</label>
      		<input type="pasword" name="password" class="form-control" required>
      		
      		<label>Choose role:</label>	
				<select class="form-control" name="role">
						<option value="ROLE_USER">User</option>	 
	  					<option value="ROLE_ADMIN">Admin</option>	  					  					
				 </select> 
			<br>	      	
      		
	      	<a href="${contextPath}${url_register}" class="btn btn-lg btn-primary btn-block">Cancel</a>
	      	<button type="submit" name="login" value="1" class="btn btn-lg btn-primary btn-block">Submit</button>
		</form:form>    
	</div>
		
	<ct:Footer />
</div>
</body>
</html>