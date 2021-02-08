<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import= "entity.BuyerBean,entity.SellerBean,entity.AdminBean,javax.servlet.http.Cookie"%>
    
<%
	if(!request.isSecure()) {response.sendRedirect("https://localhost:8443/Progetto/login.jsp"); return;}
	Cookie[] cookies= request.getCookies();
	
	if(cookies != null){
		for(Cookie c : cookies) {
			if(c.getName().equalsIgnoreCase("User")){
				String username= c.getValue();
				response.sendRedirect("./Login");
			}
		}
	}

	String status= (String) request.getAttribute("status");
	
	if(status != null){

		if(status.equals("success")){
			response.sendRedirect(response.encodeURL("./index.jsp"));
			return;
		}
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
		
		<title>Login</title>
	</head>
	<body>
	<div class="container">
	<!-- header.html -->
	
	<%@ include file="header.jsp" %>
	
	<!-- Login form -->
	
<div class="text-center" style="length:350px; width:350px; align-content: center; width: 50%; margin: 0 auto;">
	
	<form class="form-signin" action="<%=response.encodeURL("/Progetto/Login")%>" method="POST">
  		<br><br><img class="mb-4" src="Images/logo2.svg" alt="" width="300" height="72">
  	
  	<h1 class="h3 mb-3 font-weight-normal">Inserisci le tue credenziali</h1>
  
  	<label for="inputEmail" class="sr-only">Indirizzo Email</label>
  	<input type="email" name="email" id="email" class="form-control" placeholder="Indirizzo Email" required autofocus>
  
  	<label for="inputPassword" class="sr-only">Password</label>
  	<input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
  
  <div class="checkbox mb-3">
    <label>
      <input type="checkbox" value="on" class="form-check-input" id="checkbox" name="checkbox"> Resta connesso
    </label>
  </div>
  
  <button class="btn btn-lg btn-primary btn-block" type="submit">Accedi</button>
	</form>
	<br><br>
	<%if(status != null && status.equals("failure")){ %>
		<br><div class="alert alert-danger" role="alert"><%=request.getAttribute("Message") %></div>
	<%} %>
	</div>
	</div>

	<!-- footer.html -->
	
	<%@ include file="footer.html" %>
	
	<!-- Script to make .dropdown working fine -->
	
	<script type="text/javascript">
		$(document).ready(function() {
	    	$(".dropdown-toggle").dropdown();});
	</script>

	<!--  Cdn script -->
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
	</body>
</html>