<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
		
		<%
			if(!request.isSecure()) {response.sendRedirect("https://localhost:8443/Progetto/cambiopassword.jsp"); return;}
		
			HttpSession sessione= request.getSession(true);
			Boolean login= (Boolean) sessione.getAttribute("login");
			if(login == null) login= false;
			
			String message= (String) request.getAttribute("message");
			
			String email= null;
			
			if(login == true){
				String type= (String) sessione.getAttribute("Type");
			
				if(type.equalsIgnoreCase("amministratore")){
					AdminBean user= (AdminBean) sessione.getAttribute("User");
					email= user.getEmail();
				}
			
				if(type.equalsIgnoreCase("venditore")){
					SellerBean user= (SellerBean) request.getSession().getAttribute("User");
					email= user.getEmail();
				}
			
				if(type.equalsIgnoreCase("acquirente")){
					BuyerBean user= (BuyerBean) request.getSession().getAttribute("User");
					email= user.getEmail();
				}
			
			}
			email= email + "";
		%>
		
		<title>Cambio email</title>
	</head>
	<body>
		<div class="container">
	<!-- header.html -->
	
	<%@ include file="header.jsp" %>
	
	<% if(login == true){ %>
	
		<h1>Cambio email</h1>
		
		<%if(message != null){ %>
		<div class="alert alert-danger" role="alert"><%=message %></div>
		<%} %>
		
			<form class="needs-validation" action="./UserControl" method="post">
        <div class="row">
          <div class="col-md-6 mb-3">
          
            <label for="old">Nuova email</label>
            <input type="email" class="form-control" id="new" name="nuova" placeholder="" value="" required>
            
            <input type="hidden" class="form-control" id="email" name="email" placeholder="" value="<%=email %>" required>
            <input type="hidden" class="form-control" id="action" name="action" placeholder="" value="email" required>
            
          </div>
        </div>
        <hr class="mb-4">
        <button class="btn btn-primary" type="submit">Modifica</button>
      </form>
		<br>
	<% } else if(login == false){ %>
		<div class="jumbotron">
  			<h1 class="display-4">Sembra che tu non abbia effettuato l'accesso...</h1>
 			<p class="lead">Effettua il login per continuare</p>
  		<hr class="my-4">
  			<a class="btn btn-primary btn-lg" href="./login.jsp" role="button">Login</a>
		</div><br>
	<% } %>
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