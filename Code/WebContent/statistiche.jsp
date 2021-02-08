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
			
			@SuppressWarnings("unchecked")
			ArrayList<Integer> result= ((ArrayList<Integer>) request.getAttribute("result"));
			String message= (String) request.getAttribute("message");
		%>
		
		<title>Area statistiche</title>
	</head>
<body>
	<div class="container">
	<%@ include file="header.jsp" %>
	</div>
	<br><br>
	<div class="py-5 text-center">
    <img class="d-block mx-auto mb-4" src="Images/logo2.svg" alt="" width="250" height="80">
    <h2>Area statistiche</h2>
    <h4>Inserisci due date per visualizzare le statistiche</h4>
  </div>
	<div class="container">
	
	<%if(message != null && !message.isBlank()){ %>
		<div class="alert alert-danger" role="alert"><%=message %></div>
	<%} %>
	
	<form action="./AdminControl?action=statistiche" method="POST">
		<div class="row">
      	    <div class="col-md-6 mb-3">
        	    <label for="from">Da:</label>
        	    <input type="date" class="form-control" id="from" name="from" required>
        	  </div>
	          <div class="col-md-6 mb-3">
	            <label for="to">A:</label>
            	<input type="date" class="form-control" id="to" name="to" required>
          	</div>
        </div>
        <div class="row">
        	<div class="col-md-6">
            	<input type="submit" class="btn btn-primary">
          	</div>
		</div>
	</form>
	<br><br>
	
	
	<%if(result != null && !result.isEmpty()){ %>
    <div class="row">
        <div class="col-md-12">
            <div class="card ">
                <div class="card-header">
                    <h3 class="text-xs-center"><strong>Dati calcolati</strong></h3>
                </div>
                <div class="card-block">
                    <div class="table-responsive">
                        <table class="table table-sm">
                            <thead>
                                <tr>
                                    <td><strong>Statistica</strong></td>
                                    <td class="text-xs-center"><strong>Valore</strong></td>
                                </tr>
                            </thead>
                            <tbody>
 
                                <tr>
                                    <td class="text-xs-center">Numero di registrazioni da DATA1 a DATA2</td>
                                    <td class="text-xs-center"><%=result.get(0) %></td>
                                </tr>
    							<tr>
                                    <td class="text-xs-center">Numero di prodotti in vendita</td>
                                    <td class="text-xs-center"><%=result.get(1) %></td>
                                </tr>
                                <tr>
                                    <td class="text-xs-center">  - di cui disponibili</td>
                                    <td class="text-xs-center"><%=result.get(2) %></td>
                                </tr>
                                <tr>
                                    <td class="text-xs-center">Numero di ordini effettuati</td>
                                    <td class="text-xs-center"><%=result.get(3) %></td>
                                </tr>
                                <tr>
                                    <td class="text-xs-center">  - da cui totale fatturato</td>
                                    <td class="text-xs-center">&euro; <%=result.get(4) %></td>
                                </tr>
                                <tr>
                                    <td class="text-xs-center">Numero di ordini effettuati da DATA1 a DATA2</td>
                                    <td class="text-xs-center"><%=result.get(5) %></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%} %>
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