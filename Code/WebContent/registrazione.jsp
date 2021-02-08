<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
		
		<%
			String message= ((String)request.getAttribute("message")); 
		
			if(!request.isSecure()) {response.sendRedirect("https://localhost:8443/Progetto/registrazione.jsp"); return;}	
		%>
		
		<title>Registrazione</title>
	</head>
	<body>
	<div class="container">
	<!-- header.html -->
	
	<%@ include file="header.jsp" %>
	
	<!-- Form di registrazione -->
	<br><br>
	<div class="text-center" style="length:350px; width:350px; align-content: center; width: 50%; margin: 0 auto;">
	<img class="mb-4" src="Images/logo2.svg" alt="" width="300" height="72">
  	<h1 class="h3 mb-3 font-weight-normal">Compila il modulo per registrarti</h1>
  	</div>
	
	<div class="alert alert-danger" role="alert" id="errore"><%=message%></div>
	
	
	<form action="./RegistrationControl" method="POST">
  <div class="form-group row">
  <legend class="col-form-label col-sm-2 pt-0">Tipologia</legend>
  <div class="col-sm-10" id="userType">
  <div class="form-check">
  	<input class="form-check-input" type="radio" name="type" id="acquirente" value="acquirente" onclick='fieldChange()' checked>
    <label class="form-check-label" for="gridRadios1">
      Acquirente
    </label>
  </div>
   <div class="form-check">
   <input class="form-check-input" type="radio" name="type" id="venditore" onclick='fieldChange()' value="venditore">
   <label class="form-check-label" for="gridRadios2">
      Venditore
   </label>
   </div>
   </div>
   </div>
   
   <div class="form-group row">
   <label for="id" id="idLabel" class="col-sm-2 col-form-label" style="display:none;">ID</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="id" id="id" style="display:none;">
    </div>
   </div>
   
   <div class="form-group row">
    <label for="inputEmail3" class="col-sm-2 col-form-label">Email</label>
    <div class="col-sm-10">
      <input type="email" class="form-control" name="email" id="inputEmail3" required>
    </div>
  </div>
  <div class="form-group row">
    <label for="inputPassword3" class="col-sm-2 col-form-label">Password</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" name="password" id="inputPassword3" required>
    </div>
  </div>
  <div class="form-group row">
    <label for="inputPassword3" class="col-sm-2 col-form-label">Conferma password</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" name="confermaPassword" id="inputPassword2" required>
    </div>
  </div>
  <div class="form-group row">
   <label for="id" class="col-sm-2 col-form-label">Nome</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="nome" id="nome" required>
    </div>
   </div>
   <div class="form-group row">
   <label for="id" class="col-sm-2 col-form-label">Cognome</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="cognome" id="cognome" required>
    </div>
   </div>
  <div class="form-group row">
    <div class="col-sm-10">
      <button type="submit" class="btn btn-primary">Registrati</button>
    </div>
  </div>
</form>
</div>
	<!-- footer.html -->
	
	<%@ include file="footer.html" %>
	
	<!-- Script Radio buttons -->
	<script type="text/javascript">
	
	function fieldChange(){
		if(document.getElementById("acquirente").checked){
			$("#id").removeAttr("Required");
			$("#id").hide();
			$("#idLabel").hide();
		}
		else{
			$("#id").attr("Required", true);
			$("#id").show();
			$("#idLabel").show();
		}
	}
	function checkError(){
		if($("#errore").text() != "null"){
			$("#errore").show();
		}
		else{
			$("#errore").hide();
		}
	}
	$(document).ready(checkError());
	</script>
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