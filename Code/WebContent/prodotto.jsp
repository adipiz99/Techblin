<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="entity.ProductBean,javax.servlet.RequestDispatcher"%>
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
			
			ProductBean product = (ProductBean) request.getAttribute("product");
		
			if(product == null || product.getVisible() == 0){
				RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/ProductError.jsp");
				dispatcher.forward(request, response);
			}
			
			if(login == true){
				String type= (String) sessione.getAttribute("Type");
			
			}
			
			@SuppressWarnings("unchecked")
			Cart<ProductBean> cart = (Cart<ProductBean>)sessione.getAttribute("carrello");
			
			if(cart == null) {
				response.sendRedirect(response.encodeRedirectURL("./ProductControl?action=null"));
			}
			
			int count= 0;
			for(ProductBean bean : cart.getItems()){
				if(bean.getCode() == product.getCode())
					count++;
			}
			
			boolean checkQTY= true;
			if(product != null)
				if(count >= product.getQuantity()) checkQTY= false;
			
			String nomeProdotto= product.getName(); 
		%>
		
		<title><%= nomeProdotto %></title>
	</head>
	<body>
	
	<!-- header.html -->
	
	<%@ include file="header.jsp" %>
	<div class="container">
		<h2>Dettagli prodotto</h2>
	<div class="row wow fadeIn" style="visibility: visible; animation-name: fadeIn;">
        <!--Grid column-->
        <div class="col-md-6 mb-4">
			
          <!--Content-->
          
          <div class="p-4">
          <p class="lead font-weight-bold"><%=product.getName() %> </p>
            <p class="lead">
              <span class="mr-1">
              </span>
              <span>&euro;<%=product.getPrice() %></span>
            </p>
			
			<p class="lead">Disponibilità: </p>
				<%if(product.getQuantity() > 0){ %>
				<p style="color: green">Disponibile</p>
				<form class="d-flex justify-content-left" action="./CartControl" method="GET">
            
              <!-- Default input -->
              <input type="text" value="<%= product.getCode() %>" name="id" aria-label="Search" class="form-control" style="display: none">
              <input type="text" value="addCart" name="action" aria-label="Search" class="form-control" style="display: none">
              <%if(login == true && type.equals("acquirente") && checkQTY == true){ %>
              <button class="btn btn-primary btn-md my-0 p waves-effect waves-light" type="submit" id="add">
                <i class="fas fa-shopping-cart ml-1">Aggiungi al carrello</i>
              </button>
              <%} %>

            </form>		
			<br>
			
			<% if(request.getAttribute("message") != null){ %>
            	
            	<div class="alert alert-success" role="alert">
  					<%=(String) request.getAttribute("message") %>
				</div>
            <%}%>
				<%} else { %>
				<p style="color: red">Non Disponibile</p>
				<%} %>
            <p class="lead font-weight-bold">Descrizione</p>

            <p><%=product.getDescription() %></p>

          </div>
          <!--Content-->

        </div>
        <!--Grid column-->

      </div>
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