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
			Boolean login= (Boolean) sessione.getAttribute("login");
			if(login == null) login= false;
			
			String email= null;
			BuyerBean utente= null;
			
			if(login == true){
				String type= (String) sessione.getAttribute("Type");
			
				if(type.equalsIgnoreCase("acquirente")){
					utente= (BuyerBean) sessione.getAttribute("User");
					email= utente.getEmail();
				}
				else{
					response.sendRedirect("./nonautorizzato.jsp");
				}
			}
			email= email + "";
			
			@SuppressWarnings("unchecked")
			Cart<ProductBean> cart = (Cart<ProductBean>)sessione.getAttribute("carrello");
			
			if(cart == null) {
				response.sendRedirect(response.encodeRedirectURL("./ProductControl"));
				cart = (Cart<ProductBean>)sessione.getAttribute("carrello");
			}
			
			String message= (String) request.getAttribute("message");
			
			request.setAttribute("carrello", cart);
		%>
		
		<title>Checkout</title>
	</head>
	<body>
	<div class="container">
	<!-- header.html -->
	
	<%@ include file="header.jsp" %>
	
	<%
		if(cart == null || cart.getItems().isEmpty()){
	%>
	
		<div class="jumbotron">
  			<h1 class="display-4">Il carrello &eacute; vuoto...</h1>
 			<p class="lead">Per effettuare un acquisto, aggiungi qualche prodotto dal nostro catalogo!</p>
		</div>	
	<% } else { %>
	
  <div class="py-5 text-center">
    <img class="d-block mx-auto mb-4" src="Images/logo2.svg" alt="" width="250" height="80">
    <h2>Checkout</h2>
  </div>

  <div class="row">
    <div class="col-md-8 order-md-1">
      <div class="alert alert-danger" role="alert" id="message"><%=message%></div>
      <h4 class="mb-3">Indirizzo di spedizione</h4>
      <form class="needs-validation" action="./OrderControl?action=add" method="post">
        <div class="row">
          <div class="col-md-6 mb-3">
            <label for="firstName">Nome</label>
            <input type="text" class="form-control" id="firstName" name="nome" placeholder="" value="<%=utente.getNome() %>" required>
          </div>
          <div class="col-md-6 mb-3">
            <label for="lastName">Cognome</label>
            <input type="text" class="form-control" id="lastName" name="cognome" placeholder="" value="<%=utente.getCognome() %>" required>
          </div>
        </div>
        <div class="mb-3">
          <label for="email">Email</label>
          <input type="email" class="form-control" id="email" name="email" placeholder="indirizzo@example.it" value="<%=email %>" required>
        </div>

        <div class="mb-3">
          <label for="address">Indirizzo</label>
          <input type="text" class="form-control" id="address" name="indirizzo" placeholder="Via Roma 35" required>
        </div>
        <div class="row">
          <div class="col-md-5 mb-3">
            <label for="country">Regione</label>
            <select class="custom-select d-block w-100" id="regione" name="regione" onchange="ajaxProvince()" required>
              <option value="">Scegli...</option>
            </select>
          </div>
          <div class="col-md-4 mb-3">
            <label for="state">Provincia</label>
            <select class="custom-select d-block w-100" id="provincia" name="provincia" required>
              <option value="">Scegli...</option>
            </select>
          </div>
          <div class="col-md-3 mb-3">
            <label for="citta">Citt&agrave;</label>
            <input type="text" class="form-control" id="citta" name="citta" required>
          </div>
          <div class="col-md-3 mb-3">
            <label for="zip">Cap</label>
            <input type="text" class="form-control" id="cap" name="cap" required>
          </div>
        </div>
        <hr class="mb-4">

        <h4 class="mb-3">Pagamento</h4>

        <div class="d-block my-3">
          <div class="custom-control custom-radio">
            <input id="credit" name="paymentMethod" type="radio" value="carta" class="custom-control-input" onchange="radio()" checked required>
            <label class="custom-control-label" for="credit">Carta di credito</label>
          </div>
          <div class="custom-control custom-radio">
            <input id="debit" name="paymentMethod" type="radio" value="conto" class="custom-control-input" onchange="radio()" required>
            <label class="custom-control-label" for="debit">Conto corrente</label>
          </div>
          <div class="custom-control custom-radio">
            <input id="paypalRadio" name="paymentMethod" type="radio" value="paypal" class="custom-control-input" onchange="radio()" required>
            <label class="custom-control-label" for="paypalRadio">PayPal</label>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6 mb-3">
            <label for="cc-name" id="titolareLabel">Titolare</label>
            <input type="text" class="form-control" id="titolare" name="titolare" placeholder="" required>
          </div>
          <div class="col-md-6 mb-3">
            <label for="cc-number" id="cartaLabel">Numero carta</label>
            <input type="number" class="form-control" id="carta" name="carta" placeholder="" required>
          </div>
          <div class="col-md-3 mb-3">
            <label for="cc-expiration" id="scadenzaLabel">Scadenza</label>
            <input type="text" class="form-control" id="scadenza" name="scadenza" placeholder="" required>
          </div>
          <div class="col-md-3 mb-3">
            <label for="cc-cvv" id="cvvLabel">CVV</label>
            <input type="number" class="form-control" id="cvv" name="cvv" placeholder="" required>
          </div>
        </div>
        <div class="row">
         <div class="col-md-6 mb-3">
            <label for="cc-number" id="contoLabel">IBAN</label>
            <input type="text" class="form-control" id="conto" name="conto" placeholder="" required>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6 mb-3">
            <label for="cc-number" id="paypalLabel">Account PayPal</label>
            <input type="email" class="form-control" id="paypal" name="paypal" placeholder="" required>
            <input type="hidden" class="form-control" id="user" name="user" value="<%=email %>" required>
          </div>
        </div>
        <hr class="mb-4">
        <button class="btn btn-primary" type="submit">Conferma ordine</button>
      </form>
    </div>
  </div>
	<br>
	<% } %>
	</div>
	<!-- footer.html -->
	
	<%@ include file="footer.html" %>
	
	 <!-- Chiamate AJAX -->
	<script type="text/javascript">
		function ajaxProvince(){
			var params= "action=provincia&regione=" + $("#regione").children("option:selected").val();
			$.ajax({
				"type": "GET",
				"url": "./AjaxServlet",
				"data": params,
				"success": function(response) {
					var response = response;
                    var json = JSON.parse(response);
                    $("#provincia").children("option").remove();
					for(var i=0; i<json.length;i++){
                        $('#provincia').append('<option value="' + json[i] + '">' + json[i] + '</option>');
					}
				}
			});
		}
		
		function ajaxRegioni(){
			$.ajax({
				"type": "GET",
				"url": "./AjaxServlet",
				"data": "action=regione",
				"success": function(response) {
					var response = response;
                    var json = JSON.parse(response);
                    $("#regione").children("option").remove();
					for(var i=0; i<json.length;i++){
                        $('#regione').append('<option value="' + json[i] + '">' + json[i] + '</option>');
					}
					ajaxProvince();
				}
			});
		}
		
		$(document).ready(ajaxRegioni());
	</script>
	
	 <!-- Radio Button script -->
	
	<script type="text/javascript">
		function radio(){
			var sel= $(".custom-control-input:checked").val();
			if(sel === "carta"){
				
				$("#titolareLabel").show();
				$("#cartaLabel").show();
				$("#scadenzaLabel").show();
				$("#cvvLabel").show();
				$("#titolare").show();
				$("#carta").show();
				$("#scadenza").show();
				$("#cvv").show();
				
				$("#titolare").attr("Required", true);
				$("#carta").attr("Required", true);
				$("#scadenza").attr("Required", true);
				$("#cvv").attr("Required", true);
				
				$("#conto").removeAttr("Required");
				$("#conto").hide();
				$("#contoLabel").hide();
				$("#paypal").removeAttr("Required");
				$("#paypal").hide();
				$("#paypalLabel").hide();
			}
			else if(sel === "conto"){
				$("#titolareLabel").show();
				$("#titolare").show();
				$("#contoLabel").show();
				$("#conto").show();
				
				$("#titolare").attr("Required", true);
				$("#conto").attr("Required", true);
				
				$("#cartaLabel").hide();
				$("#scadenzaLabel").hide();
				$("#cvvLabel").hide();
				$("#paypalLabel").hide();
				$("#carta").hide();
				$("#scadenza").hide();
				$("#cvv").hide();
				$("#paypal").hide();
				$("#carta").removeAttr("Required");
				$("#scadenza").removeAttr("Required");
				$("#cvv").removeAttr("Required");
				$("#paypal").removeAttr("Required");
				
				
			}
			else if(sel === "paypal"){
				
				$("#paypalLabel").show();
				$("#paypal").show();
				
				$("#paypal").attr("Required", true);
				
				$("#cartaLabel").hide();
				$("#scadenzaLabel").hide();
				$("#cvvLabel").hide();
				$("#contoLabel").hide();
				$("#titolareLabel").hide();
				$("#carta").hide();
				$("#scadenza").hide();
				$("#cvv").hide();
				$("#conto").hide();
				$("#titolare").hide();
				$("#titolare").removeAttr("Required");
				$("#carta").removeAttr("Required");
				$("#scadenza").removeAttr("Required");
				$("#cvv").removeAttr("Required");
				$("#conto").removeAttr("Required");
				
			}
		}
		
		$(document).ready(radio());
	</script>
	
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