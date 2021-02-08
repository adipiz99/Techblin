<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
		
		<%
			if(!request.isSecure()) {response.sendRedirect("https://localhost:8443/Progetto/checkout.jsp"); return;}		
		
			HttpSession sessione= request.getSession(true);
			ProductBean product= (ProductBean) request.getAttribute("product");
		%>
		
		<title>Gestione prodotto</title>
	</head>
	<body>
	<div class="container">
	<!-- header.html -->
	
	<%@ include file="header.jsp" %>
	
	<%
		if(product == null || product.isEmpty() || product.getVisible() == 0){
	%>
	
		<div class="jumbotron">
  			<h1 class="display-4">Il prodotto non esiste</h1>
 			<p class="lead">Il prodotto selezionato non è presente nei nostri sistemi.</p>
		</div>	
	<% } else { %>
	
  <div class="py-5 text-center">
    <img class="d-block mx-auto mb-4" src="Images/logo2.svg" alt="" width="250" height="80">
    <h2>Modifica prodotto</h2>
  </div>

  <div class="row">
    <div class="col-md-8 order-md-1">
      <form class="needs-validation" action="./ProductControl" method="post">
        <div class="row">
          <div class="col-md-6 mb-3">
            <label for="firstName">Nome</label>
            <input type="text" class="form-control" id="nome" name="name" placeholder="<%=product.getName() %>" value="<%=product.getName() %>" required>
          </div>
          <div class="col-md-6 mb-3">
            <label for="lastName">Descrizione</label>
            <input type="text" class="form-control" id="descrizione" name="description" placeholder="<%=product.getDescription() %>" value="<%=product.getDescription() %>" required>
          </div>
        </div>
        <div class="mb-3">
          <label for="prezzo">Prezzo</label>
          <input type="number" class="form-control" id="prezzo" name="price" placeholder="<%=product.getPrice() %>" value="<%=product.getPrice() %>" required>
        </div>

        <div class="mb-3">
          <label for="address">Quantit&agrave;</label>
          <input type="number" class="form-control" id="quantità" name="quantity" placeholder="<%=product.getQuantity() %>" value="<%=product.getQuantity() %>" required>
        </div>
          <div class="col-md-3 mb-3">
            <input type="hidden" class="form-control" id="code" name="id" value="<%=product.getCode() %>" required>
            <input type="hidden" class="form-control" id="action" name="action" value="update" required>
          </div>
        <hr class="mb-4">
        <button class="btn btn-primary" type="submit">Modifica</button>
      </form>
    </div>
  </div>
	<br>
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