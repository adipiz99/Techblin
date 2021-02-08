<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
		
		<title>Errore</title>
	</head>
	<body>
	
	<!-- header.html -->
	
	<%@ include file="header.jsp" %>
	
  		<div class="jumbotron">
  			<h1 class="display-4">Ops... Il prodotto cercato non esiste!</h1>
 			<p class="lead">Sembra proprio che il prodotto che stai cercando di visualizzare non esista.</p>
  		<hr class="my-4">
  			<p>Niente paura! Puoi tornare alla Homepage da questo pulsante.</p>
  			<a class="btn btn-primary btn-lg" href="./index.jsp" role="button">Homepage</a>
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