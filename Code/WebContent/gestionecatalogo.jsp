<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.ArrayList,entity.ProductBean"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
		<link rel="stylesheet" href="./style/ricerca.css">
		
		<%
		String nome= null;
		HttpSession sessione= request.getSession(true);
		
		Boolean login= (Boolean) sessione.getAttribute("login");
		
		if(login != null){
		
			if(login == true){
				String type= (String) sessione.getAttribute("Type");
			
				if(type.equalsIgnoreCase("amministratore")){
					AdminBean user= (AdminBean) sessione.getAttribute("User");
					nome= user.getNome();
				}
			
				if(type.equalsIgnoreCase("venditore")){
					SellerBean user= (SellerBean) request.getSession().getAttribute("User");
					nome= user.getNome();
				}
			
				if(type.equalsIgnoreCase("acquirente")){
					response.sendRedirect("./nonautorizzato.jsp");
					return;
				}
			}
		}
		ArrayList<ProductBean> products= (ArrayList<ProductBean>) request.getAttribute("result");
		
		boolean notEmpty= !products.isEmpty();
		%>
		<title>Gestione catalogo</title>
	</head>
	<body>
	<div class="container">
	<!-- header.html -->
	
	<%@ include file="header.jsp" %>
	
	<h1>Elenco dei prodotti in vendita nello store:</h1>
	
	<br>
	
	<%
		if(notEmpty){
			for(ProductBean p : products){
	%>
			<div class="well search-result">
				<div class="row">
					<div class="col-sm">
						<h3><%=p.getName() %></h3>
						<p><%=p.getDescription()%></p>
					</div>
					<div class="col-sm">
						<a href="./ProductControl?action=retrieve&id=<%=p.getCode()%>"><button type="button" class="btn btn-secondary">Modifica</button></a>
						&nbsp;&nbsp;&nbsp;
						<a href="./ProductControl?action=delete&id=<%=p.getCode()%>"><button type="button" class="btn btn-danger">Elimina</button></a>
					</div>
                </div>
			</div>
	<%		}
		} else {%>
			<p>Sembra che non ci siano prodotti in vendita...</p>
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