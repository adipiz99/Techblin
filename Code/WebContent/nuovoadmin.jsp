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
		
			if(login == true){
				String type= (String) sessione.getAttribute("Type");
				if(!type.equalsIgnoreCase("amministratore")){
					response.sendRedirect("./nonautorizzato.jsp");
					return;
				}
			}	
		
			String message= ((String)request.getAttribute("message")); 
		
			if(!request.isSecure()) {response.sendRedirect("https://localhost:8443/Progetto/registrazione.jsp"); return;}	
		%>
		
		<title>Creazione admin</title>
	</head>
	<body>
	<div class="container">
	<!-- header.html -->
	
	<%@ include file="header.jsp" %>
	
	<!-- Form di registrazione -->
	<br><br>
	<div class="text-center" style="length:350px; width:350px; align-content: center; width: 50%; margin: 0 auto;">
	<img class="mb-4" src="Images/logo2.svg" alt="" width="300" height="72">
  	<h1 class="h3 mb-3 font-weight-normal">Inserisci i dati</h1>
  	</div>
	
	<div class="alert alert-danger" role="alert" id="errore"><%=message%></div>
	
	
	<form action="./AdminControl?action=insert" method="POST">
   
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
      <button type="submit" class="btn btn-primary">Crea amministratore</button>
    </div>
  </div>
</form>
</div>
	<!-- footer.html -->
	
	<%@ include file="footer.html" %>
	
	<!-- Script Radio buttons -->
	<script type="text/javascript">
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