<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="http://jwd.bg/tags" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:choose>
	<c:when test='${searchedBooks != null}'>
		<c:set var="books" value ="${searchedBooks}"/>
	</c:when>
</c:choose>

<!DOCTYPE html>
<html>
<ct:HeaderMeta />
<body>
	<div class="container">
		<ct:Header />
		<ct:NavigationBar />		
		
		<h2 class="form-signin-heading" align="center">Book register</h2>
		
		<form class="form-search" align="center" action="${contextPath}${url_search}" method="post">
			<input type="text" name="searchedWord" class="input-medium search-query">
			<select name="searchParam">
				<option value="name">Name</option>
				<option value="author">Author</option>
				<option value="year">Year</option>  
			 </select> 
			<button type="submit" class="btn">Search</button>
		</form>
		<br/>
				
		<table class="table table-striped show-grid">	
			<tr>
				<th>Name</th>
				<th>Author</th>
				<th>Year of publishing</th>
				<th>ISBN</th>
				<th>Options</th>			
			</tr>			
			<c:forEach var="book" items="${books}">
	  			<tr>
					<td>${book.name}</td>
					<td>${book.author}</td>						
					<td>${book.year}</td>
					<td>${book.isbn}</td>						
					<td>
	                	<a href="${contextPath}${url_edit}?id=${book.id}">Edit</a>		                	    
	                	<a href="${contextPath}${url_delete}?id=${book.id}">Delete</a> 
	                </td>
				</tr>
			</c:forEach>
		</table>	
		
		<c:choose>
				<c:when test='${searchedBooks.size() == 0}'>
				<div class="alert alert-info">					
	  				<strong>No values found</strong> 
				</div>			
				</c:when>				
			</c:choose>	
			
		<div class="container">						
			<form class="form-signin" method="get" >					  
		      	<a href="${contextPath}${url_add}" class="btn btn-lg btn-primary btn-block">Add book</a>		    	  
			</form>	    
		</div>
		
		<ct:Footer />
	</div>
</body>
</html>