<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8" import="java.util.*,entity.ProductBean,entity.Cart,entity.BuyerBean,entity.SellerBean,entity.AdminBean"%>
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
				BuyerBean user= (BuyerBean) request.getSession().getAttribute("User");
				nome= user.getNome();
			}
		
		}
	}
	
	@SuppressWarnings("unchecked")
	Cart<ProductBean> cart = (Cart<ProductBean>)sessione.getAttribute("carrello");
	
	if(cart == null) {
		response.sendRedirect(response.encodeRedirectURL("./ProductControl?action=null"));
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
	<title>Homepage</title>
</head>
<body>
	<div class="container">
	
	<!-- header.html -->
	
	<%@ include file="header.jsp" %>
	
	<!-- Benvenuto utente -->
	<%if(login != null && login == true){ %>
		<h2>Benvenuto, <%=nome %></h2>
	<%} %>
	<!-- Carousel (Scorrimento orizzontale) -->
	
	<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel" style="z-index:1;">
 	 <ol class="carousel-indicators">
   		 <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
  	 	 <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
   		 <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
 	 </ol>
 	 <div class="carousel-inner">
  	  <div class="carousel-item active">
  	    <img class="d-block w-100" src="Images/MsiBig.jpg" alt="First slide">
  	  </div>
  	  <div class="carousel-item">
  	    <img class="d-block w-100" src="Images/NvidiaBig.jpg" alt="Second slide">
 	   </div>
	    <div class="carousel-item">
 	     <img class="d-block w-100" src="Images/MacbookBig.jpg" alt="Third slide">
 	   </div>
	  </div>
	  <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
 	   <span class="carousel-control-prev-icon" aria-hidden="true"></span>
  	  <span class="sr-only">Previous</span>
 	 </a>
 	 <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
    	<span class="carousel-control-next-icon" aria-hidden="true"></span>
    	<span class="sr-only">Next</span>
  	</a>
	</div>
	
		
	<!-- Resto del codice -->
	<br><br>
	<div class="container marketing">

    <!-- Three columns of text below the carousel -->
    <div class="row">
      <div class="col-lg-4">
		<img alt="" src="./Images/scope.svg" style="height:140px; width:140px;">
        <h2>Il nostro obiettivo</h2>
        <p>Che sia un nuovo PC o un upgrade per la tua cara vecchia build, il nostro scopo &eacute; poterti fornire tutto ciò che serve per dare vita a qualcosa di straordinario.</p>
      </div><!-- /.col-lg-4 -->
      <div class="col-lg-4">
		<img alt="" src="./Images/medal.svg" style="height:140px; width:140px;">
        <h2>Solo il meglio</h2>
        <p>Sappiamo quanto i nostri clienti tengano ai propri PC. Per questo abbiamo selezionato solo le migliori componenti, quelle che potranno soddisfare ogni esigenza della nostra clientela.</p>
      </div><!-- /.col-lg-4 -->
      <div class="col-lg-4">
		<img alt="" src="./Images/rocket.svg" style="height:140px; width:140px;">
        <h2>Spedizione rapida</h2>
        <p>I nostri prodotti vengono spediti entro un giorno lavorativo dall'ordinazione utilizzando i migliori corrieri. Tranquillo, non affideremmo mai il tuo ordine in cattive mani.</p>
      </div><!-- /.col-lg-4 -->
    </div><!-- /.row -->


    <!-- Featurette -->
    <hr class="featurette-divider">

    <div class="row featurette">
      <div class="col-md-7">
        <h2 class="featurette-heading">Hai bisogno di un PC da GAMING? <span class="text-muted"><br>Ci pensiamo noi!</span></h2>
        <p class="lead">Il nostro store &eacute; specializzato nella vendita di componenti per qualsiasi esigenza. Ce n'&eacute; per tutti i gusti!</p>
      </div>
      <div class="col-md-5">
    	<img class="img-fluid" alt="" src="./Images/gaming450.jpg">
      </div>
    </div>

    <hr class="featurette-divider">

    <div class="row featurette">
      <div class="col-md-7 order-md-2">
        <h2 class="featurette-heading">La tua vecchia workstation ti ha abbandonato?<span class="text-muted">Niente paura!</span></h2>
        <p class="lead">Abbiamo componenti di ogni tipo nel nostro store. Inizia a cercare ci&oacute; di cui hai bisogno oppure contattaci per ricevere una soluzione personalizzata.</p>
      </div>
      <div class="col-md-5 order-md-1">
        <img class="img-fluid" alt="" src="./Images/work450.jpg">
      </div>
    </div>

    <hr class="featurette-divider">

    <div class="row featurette">
      <div class="col-md-7">
        <h2 class="featurette-heading">Hai bisogno di Criptovalute?<span class="text-muted">Dacci dentro con il mining!</span></h2>
        <p class="lead">Nel catalogo troverai componenti efficienti in grado di mantenere al massimo le prestazioni per lungo tempo. Cosa aspetti? Inizia a creare la tua build!</p>
      </div>
      <div class="col-md-5">
        <img class="img-fluid" alt="" src="./Images/crypto450.jpg">
      </div>
    </div>

    <hr class="featurette-divider">

    <!-- /END THE FEATURETTES -->

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