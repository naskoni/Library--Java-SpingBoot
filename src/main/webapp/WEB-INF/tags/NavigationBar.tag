<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ attribute name="title"%>

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">${title}</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-left">				
				<li><a href="home"><strong>Home</strong></a></li>
				<li><a href="bookRegister"><strong>Books</strong></a></li>
				<li><a href="clientRegister"><strong>Clients</strong></a></li>
				<li><a href="lendRegister"><strong>Lends</strong></a></li>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li><a href="admin"><strong>Administration</strong></a></li>					
		      	</sec:authorize>				
			</ul>
		
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a><strong>${user.username}</strong></a></li>
				<li><a href="editUser"><strong>Profile</strong></a></li>
				<li><a href="logout"><strong>Logout</strong></a></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
	<!--/.container-fluid -->
</nav>