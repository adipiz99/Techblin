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
			String ricerca= (String) request.getAttribute("search");
			ArrayList<ProductBean> products= (ArrayList<ProductBean>) request.getAttribute("result");
			
			boolean notEmpty= !products.isEmpty();
		%>
		<title>Ricerca</title>
	</head>
	<body>
	<div class="container">
	<!-- header.html -->
	
	<%@ include file="header.jsp" %>
	
	<h1>Risultati di ricerca per: </h1> <h2><%= ricerca %></h2>
	
	<%
		if(notEmpty){
			for(ProductBean p : products){
	%>
			<div class="well search-result">
				<div class="row">
					<div class="col-sm">
					<a href="./ProductControl?action=details&id=<%=p.getCode()%>">
						<h3><%=p.getName() %></h3>
					</a>
						<p><%=p.getDescription()%></p>
					</div>
                </div>
			</div>
	<%		}
		} else {%>
			<p>Sembra che non ci siano corrispondenze nel catalogo...</p>
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