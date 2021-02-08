<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
		
		<%
			HttpSession sessione= request.getSession(true);
			Boolean login= (Boolean) sessione.getAttribute("login");
			if(login == null) login= false;
		%>
		
		<title>Impostazioni</title>
	</head>
	<body>
	
	<!-- header.html -->
	
	<%@ include file="header.jsp" %>
	
	<%
		if(login == true){
			if(type.equals("amministratore")){
	%>
		<div class="container">
			<h2>Impostazioni</h2>
  			<div class="row">
    			<div class="col-sm">
    			<h4>Modifiche all'account:</h4>
    			</div>
    			<div class="col-sm">
    				<a href="./cambiopassword.jsp"><button type="button" class="btn btn-secondary">Modifica password</button></a>
    			</div>
    		</div>
    		<br>
    		<div class="row">
    			<div class="col-sm">
    			
    			</div>
    			<div class="col-sm">
    				<a href="./cambioemail.jsp"><button type="button" class="btn btn-secondary">Modifica email</button></a>
    			</div>
    		</div>
    		<br>
    		<hr>
    		<div class="row">
    			<div class="col-sm">
    				<h4>Amministrazione sito:</h4>
    			</div>
    			<div class="col-sm">
    				<a href="./statistiche.jsp"><button type="button" class="btn btn-danger">Area statistiche</button></a>
    			</div>
    		</div>
    		<br>
    		<div class="row">
    			<div class="col-sm">
    			
    			</div>
    			<div class="col-sm">
    				<a href="./nuovoadmin.jsp"><button type="button" class="btn btn-danger">Creazione nuovo amministratore</button></a>
    			</div>
    		</div>
    		<br>
		</div>
	<%} else if(type.equals("acquirente") || type.equals("venditore")){ %>
	<div class="container">
			<h2>Impostazioni</h2>
			<div class="row">
    			<div class="col-sm">
    				<h4>Modifiche all'account:</h4>
    			</div>
    			<div class="col-sm">
    				<a href="./cambiopassword.jsp"><button type="button" class="btn btn-secondary">Modifica password</button></a>
    			</div>
    		</div>
    		<br>
    		<div class="row">
    			<div class="col-sm">
    			
    			</div>
    			<div class="col-sm">
    				<a href="./cambioemail.jsp"><button type="button" class="btn btn-secondary">Modifica email</button></a>
    			</div>
    			<br>
    		</div>
    		<br>
    </div>
    		
	<%}}else if(login == false){%>
		<div class="jumbotron">
  			<h1 class="display-4">Sembra che tu non abbia effettuato l'accesso...</h1>
 			<p class="lead">Effettua il login per continuare</p>
  		<hr class="my-4">
  			<a class="btn btn-primary btn-lg" href="./login.jsp" role="button">Login</a>
		</div><br>
	<%}%>
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