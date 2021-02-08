<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,entity.ProductBean,entity.Cart"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
		<link rel="stylesheet" href="./style/ricerca.css">
		
		<%
			HttpSession sessione= request.getSession(true);
			@SuppressWarnings("unchecked")
			Cart<ProductBean> cart = (Cart<ProductBean>)sessione.getAttribute("carrello");
			
			if(cart == null) {
				response.sendRedirect(response.encodeRedirectURL("./ProductControl"));
				cart = (Cart<ProductBean>)sessione.getAttribute("carrello");
			}
			
			String message= (String) request.getAttribute("message");
		%>
		
		<title>Carrello</title>
	</head>
	<body>
	
	<!-- header.html -->
	
	<%@ include file="header.jsp" %>
	
	<div class="container">
	<h1>Carrello</h1>
	
	<div class="alert alert-danger" role="alert" id="message"><%=message%></div>
	
	<%
		int tot= 0;
		if(cart != null && !cart.getItems().isEmpty()){
			for(ProductBean p : cart.getItems()){
				tot+=p.getPrice();
	%>
			<div class="well search-result">
				<div class="row">
					<div class="col-sm">
					<a href="./ProductControl?action=details&id=<%=p.getCode()%>">
						<h3><%=p.getName() %></h3>
					</a>
					<h4>Prezzo: &euro;<%=p.getPrice() %></h4>
					<br><br>
						<a href="./CartControl?action=deleteCart&id=<%=p.getCode()%>"><button type="button" class="btn btn-danger">Rimuovi</button></a>
					</div>
                </div>
			</div>
	<%		}%>
		<br>
		<h4>Totale: &euro;<%=tot %></h4>
		<% } else { %>
			<h3>Sembra che il tuo carrello sia vuoto...</h3>
	<% } %>
	</div>
	<!-- footer.html -->
	<%@ include file="footer.html" %>
	
	<script type="text/javascript">
	function checkMessage(){
		if($("#message").text() != "null"){
			$("#message").show();
		}
		else{
			$("#message").hide();
		}
	}
	$(document).ready(checkMessage());
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