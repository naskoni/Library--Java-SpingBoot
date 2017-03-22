<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="http://jwd.bg/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:choose>
	<c:when test='${searchedLends != null}'>
		<c:set var="lends" value ="${searchedLends}"/>
	</c:when>
</c:choose>

<!DOCTYPE html>
<html>
<ct:HeaderMeta />
<body>
	<div class="container">
		<ct:Header />
		<ct:NavigationBar />		
		
		<h2 class="form-signin-heading" align="center">Lend register</h2>
		
		<form class="form-search" align="center" action="${contextPath}${url_search}" method="post">
			<input type="text" name="searchedWord" class="input-medium search-query">
			<select name="searchParam">
				<option value="book">Book name</option>
				<option value="client">Client name</option>
				<option value="lendingDate">Date of lending</option>
			 	<option value="returnDate">Date of return</option>
			 </select> 
			<button type="submit" class="btn">Search</button>
		</form>
		<br/>
				
		<table class="table table-striped show-grid">	
			<tr>
				<th>Book name</th>
				<th>Client name</th>
				<th>Date of lending</th>
				<th>Date of return</th>
				<th>Options</th>			
			</tr>			
			<c:forEach var="lend" items="${lends}">
	  			<tr>
					<td>${lend.book.name}</td>
					<td>${lend.client.name}</td>						
					<td>${lend.lendingDate}</td>
					<td>${lend.returnDate}</td>						
					<td>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<a href="${contextPath}${url_edit}?id=${lend.id}">Edit</a>
		      			</sec:authorize>	                	
	                </td>
				</tr>
			</c:forEach>
		</table>	
		
		<c:choose>
				<c:when test='${searchedLends.size() == 0}'>
				<div class="alert alert-info">					
	  				<strong>No values found</strong> 
				</div>			
				</c:when>				
			</c:choose>	
			
		<div class="container">						
			<form class="form-signin" method="get" >					  
		      	<a href="${contextPath}${url_add}" class="btn btn-lg btn-primary btn-block">Add lend</a>		    	  
			</form>	    
		</div>
		
		<ct:Footer />
	</div>
</body>
</html>