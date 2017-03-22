<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="http://jwd.bg/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:choose>
	<c:when test='${searchedClients != null}'>
		<c:set var="clients" value ="${searchedClients}"/>
	</c:when>
</c:choose>

<!DOCTYPE html>
<html>
<ct:HeaderMeta />
<body>
	<div class="container">
		<ct:Header />
		<ct:NavigationBar />		
		
		<h2 class="form-signin-heading" align="center">Client register</h2>
		
		<form class="form-search" align="center" action="${contextPath}${url_search}" method="post">
			<input type="text" name="searchedWord" class="input-medium search-query">
			<select name="searchParam">
				<option value="name">Name</option>
				<option value="pid">PID</option>
				<option value="birthdate">Date of birth</option>  
			 </select> 
			<button type="submit" class="btn">Search</button>
		</form>
		<br/>		
		<table class="table table-striped show-grid">	
			<tr>
				<th>Name</th>
				<th>PID</th>
				<th>Date of birth</th>
				<th>Created (modified) by</th>
				<th>Options</th>			
			</tr>			
			<c:forEach var="client" items="${clients}">
	  			<tr>
					<td>${client.name}</td>
					<td>${client.pid}</td>						
					<td>${client.birthdate}</td>
					<td>${client.createdBy.name}</td>						
					<td>
	                	<a href="${contextPath}${url_edit}?id=${client.id}">Edit</a>		                	    
	                	<a href="${contextPath}${url_delete}?id=${client.id}">Delete</a> 
	                </td>
				</tr>
			</c:forEach>
		</table>	
		
		<c:choose>
				<c:when test='${searchedClients.size() == 0}'>
				<div class="alert alert-info">					
	  				<strong>No values found</strong> 
				</div>			
				</c:when>				
			</c:choose>	
			
		<div class="container">						
			<form class="form-signin" method="get" >					  
		      	<a href="${contextPath}${url_add}" class="btn btn-lg btn-primary btn-block">Add client</a>		    	  
			</form>	    
		</div>
		
		<ct:Footer />
	</div>
</body>
</html>