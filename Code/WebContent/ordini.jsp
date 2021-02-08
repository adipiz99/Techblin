<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.ArrayList,entity.OrderBean"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
		<link rel="stylesheet" href="./style/ricerca.css">
		
		<%
			String email= (String) request.getAttribute("user");
			ArrayList<OrderBean> orders= (ArrayList<OrderBean>) request.getAttribute("ordini");
			
			boolean notEmpty= !orders.isEmpty();
		%>
		<title>I miei ordini</title>
	</head>
	<body>
	<div class="container">
	<!-- header.html -->
	
	<%@ include file="header.jsp" %>
	
	<h1>Ordini per l'account: </h1> <h4><%= email %></h4>
	
	<%
		if(notEmpty){
			for(OrderBean o : orders){
	%>
			<div class="well search-result">
				<div class="row">
					<div class="col-sm">
					<a href="./OrderControl?action=retrieve&order=<%=o.getId()%>">
						<h3>Ordine n°<%=o.getId()%></h3>
					</a>
					</div>
                </div>
			</div>
	<%		}
		} else {%>
			<p>Non hai ancora effettuato ordini.</p>
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