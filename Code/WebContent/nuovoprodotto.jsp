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
		
			if(!request.isSecure()) {response.sendRedirect("https://localhost:8443/Progetto/checkout.jsp"); return;}		
		
			HttpSession sessione= request.getSession(true);
			Boolean login= (Boolean) request.getSession().getAttribute("login");
			String tipo= (String) request.getSession().getAttribute("Type");
			
			String email= null;
			if(login != null && login){
				if(tipo.equalsIgnoreCase("amministratore")){
					AdminBean user= (AdminBean) request.getSession().getAttribute("User");
					email= user.getEmail();
				}
			
				if(tipo.equalsIgnoreCase("venditore")){
					SellerBean user= (SellerBean) request.getSession().getAttribute("User");
					email= user.getEmail();
				}

				if(tipo.equalsIgnoreCase("acquirente")){
					response.sendRedirect("./nonautorizzato.jsp");
					return;
				}
			}
		%>
		
		<title>Inserisci prodotto</title>
	</head>
	<body>
	<div class="container">
	<!-- header.html -->
	
	<%@ include file="header.jsp" %>
	
	<%if(login ==null || email == null || email.isBlank()){
		response.sendRedirect("./login.jsp");
	} else { %>
  <div class="py-5 text-center">
    <img class="d-block mx-auto mb-4" src="Images/logo2.svg" alt="" width="250" height="80">
    <h2>Inserisci prodotto</h2>
  </div>
  <%if(message != null){ %>
	<div class="alert alert-danger" role="alert" id="errore"><%=message%></div>
  <% } %>
  <div class="row">
    <div class="col-md-8 order-md-1">
    

      <form class="needs-validation" action="./ProductControl" method="post">
        <div class="row">
          <div class="col-md-6 mb-3">
            <label for="firstName">Nome</label>
            <input type="text" class="form-control" id="nome" name="name" placeholder="" value="" required>
          </div>
          <div class="col-md-6 mb-3">
            <label for="lastName">Descrizione</label>
            <input type="text" class="form-control" id="descrizione" name="description" placeholder="" value="" required>
          </div>
        </div>
        <div class="mb-3">
          <label for="prezzo">Prezzo</label>
          <input type="number" class="form-control" id="prezzo" name="price" placeholder="" value="" required>
        </div>

        <div class="mb-3">
          <label for="address">Quantit&agrave;</label>
          <input type="number" class="form-control" id="quantità" name="quantity" placeholder="" value="" required>
        </div>
          <div class="col-md-3 mb-3">
            <input type="hidden" class="form-control" id="seller" name="seller" value="<%=email %>" required>
            <input type="hidden" class="form-control" id="action" name="action" value="insert" required>
          </div>
        <hr class="mb-4">
        <button class="btn btn-primary" type="submit">Metti in vendita</button>
      </form>
    </div>
  </div>
	<br>
	<%} %>
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