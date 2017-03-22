<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="http://jwd.bg/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<ct:HeaderMeta />
<body>
	<div class="container">
		<ct:Header />
		<ct:NavigationBar />	
		
		<div class="container">
			<form:form class="form-signin" method="get">
      			<h2 class="form-signin-heading" align="center">Welcome, ${user.username}!</h2>
		      	<br/>
		      	
		      	<button type="button" onclick="location='/library/bookRegister'" name="login" value="1" class="btn btn-lg btn-primary btn-block">Book register</button>        
				<button type="button" onclick="location='/library/clientRegister'" name="login" value="1" class="btn btn-lg btn-primary btn-block">Client register</button> 
				<button type="button" onclick="location='/library/lendRegister'" name="login" value="1" class="btn btn-lg btn-primary btn-block">Lend register</button> 		
			
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<button type="button" onclick="location='/library/admin'" name="login" value="1" class="btn btn-lg btn-primary btn-block">Administration page</button>
		      	</sec:authorize>
			</form:form>	    
		</div> <!-- /container -->	
		
		<ct:Footer />
	</div>
</body>
</html>