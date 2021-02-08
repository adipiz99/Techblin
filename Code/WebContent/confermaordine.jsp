<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="entity.OrderBean, javax.servlet.RequestDispatcher, java.util.ArrayList,entity.ProductBean,java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
		<link rel="stylesheet" href="./style/riepilogo.css">
		
		<%	
			HttpSession sessione= request.getSession(true);
			Boolean login= (Boolean) sessione.getAttribute("login");
			
			if(login == null) login= false;
			
			if(login == true){
				String type= (String) sessione.getAttribute("Type");
			
			}
			
			OrderBean order= (OrderBean) request.getAttribute("ordine");
			@SuppressWarnings("unchecked")
			ArrayList<ProductBean> ordinati= ((ArrayList<ProductBean>) request.getAttribute("prodotti"));
			
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			String date= format.format(order.getDataOrdine());
			
		%>
		
		<title>Conferma ordine</title>
	</head>
<body>
	<div class="container">
	<%@ include file="header.jsp" %>
	</div>
	<br><br>
	<div class="py-5 text-center">
    <img class="d-block mx-auto mb-4" src="Images/logo2.svg" alt="" width="250" height="80">
    <h2>Dettagli ordine</h2>
  </div>
	<div class="container">
    &nbsp;&nbsp;&nbsp;&nbsp; 
    <div class="row">
        <div class="col-md-12">
            <div class="text-xs-center">
                <h2>Ordine numero <%= order.getId() %></h2>
            </div>
            <hr>
            <div class="row">
                <div class="col-xs-12 col-md-3 col-lg-3 float-xs-left">
                    <div class="card  height">
                        <div class="card-header">Indirizzo di spedizione</div>
                        <div class="card-block" style="text-align:center;">
                            <strong>Indirizzo: </strong><%= order.getIndirizzo() %><br>
                             <strong>CAP: </strong><%=order.getCap()%><br>
                             <strong>Citt&aacute;: </strong><%=order.getCittà()%><br>
                             <strong>Provincia: </strong><%=order.getProvincia() %><br>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-3 col-lg-3 float-xs-left">
                    <div class="card  height">
                        <div class="card-header">Informazioni ordine</div>
                        <div class="card-block" style="text-align:center;">
                            <strong>Data ordine:</strong> <%=date %><br>
                            <strong>Stato:</strong> <%=order.getStato() %><br>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-3 col-lg-3">
                    <div class="card  height">
                        <div class="card-header">Pagamento</div>
                        <div class="card-block" style="text-align:center;">
                            <strong>Dati: </strong> <%= order.getDatiPagamento() %><br>
                        </div>
                    </div>
                </div>
            </div>
        </div>
   </div><br>
    <div class="row">
        <div class="col-md-12">
            <div class="card ">
                <div class="card-header">
                    <h3 class="text-xs-center"><strong>Riepilogo ordine</strong></h3>
                </div>
                <div class="card-block">
                    <div class="table-responsive">
                        <table class="table table-sm">
                            <thead>
                                <tr>
                                    <td><strong>Nome prodotto</strong></td>
                                    <td class="text-xs-center"><strong>Prezzo</strong></td>
                                </tr>
                            </thead>
                            <tbody>
                            <%for(ProductBean p : ordinati){ %>
                                <tr>
                                    <td><%=p.getName() %></td>
                                    <td class="text-xs-center">&euro;<%=p.getPrice()%></td>
                                </tr>
                           <%} %>
                                <tr>
                                    <td class="text-xs-center"><strong>Totale:</strong></td>
                                    <td class="text-xs-right">&euro;<%=order.getTotale() %></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
	<br><br><br>
</body>
	
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