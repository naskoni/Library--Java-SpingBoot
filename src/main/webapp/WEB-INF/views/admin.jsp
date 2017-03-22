<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="http://jwd.bg/tags" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:choose>
	<c:when test='${searchedUsers != null}'>
		<c:set var="users" value ="${searchedUsers}"/>
	</c:when>
</c:choose>

<!DOCTYPE html>
<html>
<ct:HeaderMeta />
<body>
	<div class="container">
		<ct:Header />
		<ct:NavigationBar />
		
		<h2 class="form-signin-heading" align="center">Administration page</h2>
		
		<form class="form-search" align="center" action="${contextPath}${url_search}" method="post">
			<input type="text" name="searchedWord" class="input-medium search-query">
			<select name="searchParam">
				<option value="username">Username</option>
				<option value="name">Name</option>
				<option value="role">Role</option>
				<option value="status">Status</option>  
			 </select> 
			<button type="submit" class="btn">Search</button>
		</form>
		<br/>
		
		<table class="table table-striped show-grid">	
				<tr>
					<th>Username</th>
					<th>Name</th>
					<th>Role</th>
					<th>Status</th>
					<th>Options</th>				
				</tr>
				<c:forEach var="user" items="${users}">
		  			<tr>
						<td>${user.username}</td>
						<td>${user.name}</td>						
						<td>${user.role.substring(5).toLowerCase()}</td>
						<td>${user.status}</td>
						<td>
							<c:choose>
								<c:when test='${user.status eq "active"}'>
									<a href="${contextPath}${url_deactivate}?id=${user.id}">Deactivate</a>
								</c:when>
							</c:choose>	                		                	    
	                	 
	                	</td>
					</tr>
				</c:forEach>
		</table>
		
		<div class="container">						
			<form class="form-signin" method="get" >					  
		      	<a href="${contextPath}${url_add}" class="btn btn-lg btn-primary btn-block">Add user</a>		    	  
			</form>	    
		</div>
		
		<ct:Footer />
	</div>
</body>
</html>