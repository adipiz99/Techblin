<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
		
		<title>Contatti</title>
	</head>
	<body>
	<div class="container">
	
	<!-- header.html -->
	
	<%@ include file="header.jsp" %>
	
	<h2>Contatti</h2>
	
	<div class="col-md-6 mt-3 contact-widget-section2 wow animated fadeInLeft" data-wow-delay=".2s">
                <p>La nostra sede si trova presso l'Università degli studi di Salerno, il posto in cui è nato questo piccolo store. Per qualsiasi problema, non esitate a contattarci:</p>

                <div class="find-widget">
                 Azienda:  Techblin Srl
                </div>
                <div class="find-widget">
                 Indirizzo: Università degli Studi di Salerno, Via Giovanni Paolo II, Fisciano
                </div>
                <div class="find-widget">
                  Telefono:  +39 089-890-977
                </div>
              </div>
              <br>
	
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