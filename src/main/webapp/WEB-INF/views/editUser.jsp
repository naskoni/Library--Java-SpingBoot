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
			
			<c:choose>
				<c:when test='${url_post eq "/editUserPost"}'>
					<input type="hidden" name="id" value="${user.id}">
					<input type="hidden" name="status" value="${user.status}">
					<input type="hidden" name="role" value="${user.role}">
				</c:when>				
			</c:choose>				
			
			<label>Username:</label>
	      	<input type="text" name="username" class="form-control" value="${user.username}" required autofocus>		

	      	<label>Name:</label>
	      	<input type="text" name="name" class="form-control" value="${user.name}" required>
	      		
      		<label>New password:</label>
      		<input type="password" name="password" class="form-control" required> 
      		
	      	<a href="${contextPath}/home" class="btn btn-lg btn-primary btn-block">Cancel</a>
	      	<button type="submit" name="login" value="1" class="btn btn-lg btn-primary btn-block">Submit</button>
		</form:form>    
	</div>
		
	<ct:Footer />
</div>
</body>
</html>