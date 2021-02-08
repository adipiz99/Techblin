<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8" import="java.util.*,entity.ProductBean,entity.Cart,entity.BuyerBean,entity.SellerBean,entity.AdminBean"%>
<%
	Boolean login_header= (Boolean) request.getSession().getAttribute("login");
	String type= (String) request.getSession().getAttribute("Type");
	
	String email_header= null;
	if(login_header != null && login_header){
		if(type.equalsIgnoreCase("amministratore")){
			AdminBean user= (AdminBean) request.getSession().getAttribute("User");
			email_header= user.getEmail();
		}
	
		if(type.equalsIgnoreCase("venditore")){
			SellerBean user= (SellerBean) request.getSession().getAttribute("User");
			email_header= user.getEmail();
		}

		if(type.equalsIgnoreCase("acquirente")){
			BuyerBean user= (BuyerBean) request.getSession().getAttribute("User");
			email_header= user.getEmail();
		}
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
		<link rel="stylesheet" href="style/overrideHome.css">
		
		<title>Insert title here</title>
	</head>
	<body>
	
	
	<!-- Codice barra di navigazione -->	
	
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" style="z-index:2;">
	<div class="container">
	<a class="navbar-brand" href="index.jsp"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 128.256 29.001" width="128.256" height="29.001"><g fill="#c10000" color="#c10000" transform="translate(0, 0) scale(0.5753969)"><svg width="50.4" height="50.4" x="0.0" y="0.0" viewBox="0 0 50.4 50.4"><g><rect x="0.166" y="31.438" width="3.515" height="3.518"/><rect x="5.93" y="31.438" width="3.515" height="3.518"/><rect x="11.834" y="31.438" width="3.516" height="3.518"/><rect x="17.599" y="31.438" width="3.514" height="3.518"/><rect x="23.502" y="31.438" width="3.515" height="3.518"/><rect x="29.267" y="31.438" width="3.515" height="3.518"/><rect x="35.17" y="31.438" width="3.517" height="3.518"/><rect x="40.937" y="31.438" width="3.515" height="3.518"/><rect x="0.166" y="37.269" width="3.515" height="3.514"/><rect x="5.93" y="37.269" width="3.515" height="3.514"/><rect x="11.834" y="37.269" width="3.516" height="3.514"/><rect x="17.599" y="37.269" width="3.514" height="3.514"/><rect x="23.502" y="37.269" width="3.515" height="3.514"/><rect x="29.267" y="37.269" width="3.515" height="3.514"/><rect x="35.17" y="37.269" width="3.517" height="3.514"/><rect x="40.937" y="37.269" width="3.515" height="3.514"/><rect x="0.166" y="43.154" width="3.515" height="3.514"/><rect x="5.93" y="43.154" width="3.515" height="3.514"/><rect x="11.834" y="43.154" width="26.853" height="3.514"/><rect x="40.937" y="43.154" width="3.515" height="3.514"/><rect x="46.887" y="31.438" width="3.514" height="3.518"/><rect x="46.887" y="37.269" width="3.514" height="3.514"/><rect x="46.887" y="43.154" width="3.514" height="3.514"/><rect x="6" y="4.032" width="38.521" height="24.06"/></g></svg></g><line x1="41" y1="1" x2="41" y2="28.001" stroke="#c10000" stroke-linecap="round"/><path fill="#c10000" fill-rule="nonzero" d="M6.05 16.97L3.34 16.97Q3.24 16.97 3.24 16.85L3.24 16.85L3.24 3.17L0.12 3.17Q0 3.17 0 3.05L0 3.05L0.02 0.36Q0.02 0.26 0.12 0.26L0.12 0.26L9.24 0.26Q9.36 0.26 9.36 0.36L9.36 0.36L9.36 3.05Q9.36 3.17 9.26 3.17L9.26 3.17L6.12 3.17L6.14 16.85Q6.14 16.97 6.05 16.97L6.05 16.97ZM18.38 16.97L10.63 16.97Q10.54 16.97 10.54 16.85L10.54 16.85L10.56 0.36Q10.56 0.26 10.66 0.26L10.66 0.26L18.36 0.26Q18.46 0.26 18.46 0.38L18.46 0.38L18.46 3.07Q18.46 3.17 18.36 3.17L18.36 3.17L13.44 3.17L13.44 6.91L18.36 6.91Q18.46 6.91 18.46 7.01L18.46 7.01L18.48 9.72Q18.48 9.82 18.38 9.82L18.38 9.82L13.44 9.82L13.44 14.02L18.38 14.02Q18.48 14.02 18.48 14.14L18.48 14.14L18.48 16.87Q18.48 16.97 18.38 16.97L18.38 16.97ZM24.65 17.21L24.65 17.21Q23.33 17.21 22.26 16.55Q21.19 15.89 20.57 14.78Q19.94 13.68 19.94 12.31L19.94 12.31L19.97 4.82Q19.97 3.50 20.58 2.41Q21.19 1.32 22.26 0.66Q23.33 0 24.65 0L24.65 0Q25.97 0 27.02 0.65Q28.08 1.30 28.70 2.39Q29.33 3.48 29.33 4.82L29.33 4.82L29.33 5.93Q29.33 6.02 29.23 6.02L29.23 6.02L26.54 6.02Q26.45 6.02 26.45 5.93L26.45 5.93L26.45 4.82Q26.45 4.03 25.93 3.46Q25.42 2.88 24.65 2.88L24.65 2.88Q23.78 2.88 23.33 3.47Q22.87 4.06 22.87 4.82L22.87 4.82L22.87 12.31Q22.87 13.20 23.39 13.75Q23.90 14.30 24.65 14.30L24.65 14.30Q25.42 14.30 25.93 13.69Q26.45 13.08 26.45 12.31L26.45 12.31L26.45 11.21Q26.45 11.11 26.54 11.11L26.54 11.11L29.26 11.11Q29.35 11.11 29.35 11.21L29.35 11.21L29.35 12.31Q29.35 13.68 28.72 14.78Q28.08 15.89 27.02 16.55Q25.97 17.21 24.65 17.21ZM33.91 16.97L31.20 16.97Q31.10 16.97 31.10 16.85L31.10 16.85L31.13 0.36Q31.13 0.26 31.25 0.26L31.25 0.26L33.91 0.26Q34.03 0.26 34.03 0.36L34.03 0.36L34.01 6.89L37.61 6.89L37.61 0.36Q37.61 0.26 37.70 0.26L37.70 0.26L40.37 0.26Q40.49 0.26 40.49 0.36L40.49 0.36L40.54 16.85Q40.54 16.97 40.42 16.97L40.42 16.97L37.73 16.97Q37.61 16.97 37.61 16.85L37.61 16.85L37.61 9.79L34.01 9.79L34.01 16.85Q34.01 16.97 33.91 16.97L33.91 16.97ZM46.90 16.97L42.60 16.97Q42.50 16.97 42.50 16.85L42.50 16.85L42.55 0.36Q42.55 0.26 42.65 0.26L42.65 0.26L47.26 0.26Q48.62 0.26 49.70 0.92Q50.78 1.58 51.41 2.66Q52.03 3.74 52.03 5.02L52.03 5.02Q52.03 6.05 51.56 6.94Q51.10 7.82 50.45 8.38L50.45 8.38Q51.14 9.07 51.53 9.98Q51.91 10.90 51.91 11.90L51.91 11.90Q51.91 13.30 51.24 14.45Q50.57 15.60 49.43 16.28Q48.29 16.97 46.90 16.97L46.90 16.97ZM45.43 3.12L45.43 6.89L47.26 6.89Q48.10 6.89 48.61 6.30Q49.13 5.71 49.13 5.02L49.13 5.02Q49.13 4.25 48.58 3.68Q48.02 3.12 47.26 3.12L47.26 3.12L45.43 3.12ZM45.43 9.79L45.41 14.04L46.90 14.04Q47.76 14.04 48.38 13.40Q49.01 12.77 49.01 11.90L49.01 11.90Q49.01 11.04 48.38 10.42Q47.76 9.79 46.90 9.79L46.90 9.79L45.43 9.79ZM61.49 16.97L53.74 16.97Q53.64 16.97 53.64 16.85L53.64 16.85L53.66 0.38Q53.66 0.26 53.78 0.26L53.78 0.26L56.45 0.26Q56.57 0.26 56.57 0.38L56.57 0.38L56.54 14.02L61.49 14.02Q61.61 14.02 61.61 14.14L61.61 14.14L61.61 16.85Q61.61 16.97 61.49 16.97L61.49 16.97ZM66 16.97L63.29 16.97Q63.17 16.97 63.17 16.85L63.17 16.85L63.19 0.36Q63.19 0.26 63.29 0.26L63.29 0.26L65.98 0.26Q66.07 0.26 66.07 0.36L66.07 0.36L66.10 16.85Q66.10 16.97 66 16.97L66 16.97ZM70.82 16.97L68.42 16.97Q68.23 16.97 68.23 16.80L68.23 16.80L68.21 0.46Q68.21 0.26 68.40 0.26L68.40 0.26L70.56 0.26L74.62 9.72L74.50 0.46Q74.50 0.26 74.71 0.26L74.71 0.26L77.09 0.26Q77.23 0.26 77.23 0.46L77.23 0.46L77.26 16.82Q77.26 16.97 77.14 16.97L77.14 16.97L75.02 16.97L70.87 8.14L71.04 16.78Q71.04 16.97 70.82 16.97L70.82 16.97Z" transform="translate(51, 6.017)"/></svg></a>
 	 <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
  	  <span class="navbar-toggler-icon"></span>
 	 </button>

	  <div class="collapse navbar-collapse" id="navbarSupportedContent">
 	   <ul class="navbar-nav mr-auto">
   	   <li class="nav-item active">
    	    <a class="nav-link" href="./index.jsp">Home <span class="sr-only">(current)</span></a>
      	</li>
      	<% if(login_header == null || login_header == false){ %>
      	 <li class="nav-item">
     	   <a class="nav-link" href="./registrazione.jsp">Registrati</a>
     	 </li>
     	 <li class="nav-item">
     	   <a class="nav-link" href="./login.jsp">Login</a>
     	 </li>
     	 <% } %>
     	 <!-- User menu -->
     	<% if(login_header != null && login_header == true){ 
     		if(type.equalsIgnoreCase("acquirente")){%>
   	  	   <li class="nav-item dropdown">
     	   <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
     	     User menu
     	   </a>
     	   <div class="dropdown-menu" aria-labelledby="navbarDropdown">
    	      <a class="dropdown-item" href="./OrderControl?action=retrieveAll&user=<%= email_header%>">I miei ordini</a>
       	   <a class="dropdown-item" href="./impostazioni.jsp">Impostazioni</a>
       	   <div class="dropdown-divider"></div>
       	   <a class="dropdown-item" href="./Logout">Logout</a>
       	 </div>
     	 </li>
     	 <% } else if(type.equalsIgnoreCase("venditore")){%>
     	 	<li class="nav-item dropdown">
     	   <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
     	     User menu
     	   </a>
     	   <div class="dropdown-menu" aria-labelledby="navbarDropdown">
    	      <a class="dropdown-item" href="./SellerControl?action=vetrina&seller=<%= email_header%>">La mia vetrina</a>
       	   <a class="dropdown-item" href="./impostazioni.jsp">Impostazioni</a>
       	   <div class="dropdown-divider"></div>
       	   <a class="dropdown-item" href="./Logout">Logout</a>
       	 </div>
     	 </li>
     	<% } else if(type.equalsIgnoreCase("amministratore")){ %>
     		<li class="nav-item dropdown">
     	   <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
     	     User menu
     	   </a>
     	   <div class="dropdown-menu" aria-labelledby="navbarDropdown">
    	      <a class="dropdown-item" href="./AdminControl?action=catalogo">Gestione prodotti</a>
       	   <a class="dropdown-item" href="./impostazioni.jsp">Impostazioni</a>
       	   <div class="dropdown-divider"></div>
       	   <a class="dropdown-item" href="./Logout">Logout</a>
       	 </div>
     	 </li>
     	<% } }%>
     <!-- Cart menu -->
  	 	<li class="nav-item dropdown">
     	   <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
     	     <svg width="16" height="16" viewBox="0 0 16 16" class="bi bi-cart2" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  				<path fill-rule="evenodd" d="M0 2.5A.5.5 0 0 1 .5 2H2a.5.5 0 0 1 .485.379L2.89 4H14.5a.5.5 0 0 1 .485.621l-1.5 6A.5.5 0 0 1 13 11H4a.5.5 0 0 1-.485-.379L1.61 3H.5a.5.5 0 0 1-.5-.5zM3.14 5l1.25 5h8.22l1.25-5H3.14zM5 13a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0zm9-1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0z"/>
			</svg>
     	   </a>
     	   <div class="dropdown-menu" aria-labelledby="navbarDropdown">
    	      <a class="dropdown-item" href="./cart.jsp">Visualizza</a>
       	   <a class="dropdown-item" href="./checkout.jsp">Checkout</a>
       	   <div class="dropdown-divider"></div>
       	   <a class="dropdown-item" href="<%=response.encodeURL("./CartControl?action=clearCart")%>">Svuota il carrello</a>
       	 </div>
     	 </li>
   	 </ul>
   	 <form class="form-inline my-2 my-lg-0" action="<%=response.encodeURL("./SearchControl?search=")%>" method="GET">
   	   <input class="form-control mr-sm-2" type="search" name="search" id="search" placeholder="Cerca..." aria-label="Search" onChange="toSearch()">
   	   <button type="submit" class="btn btn-outline-danger">Cerca</button>
   	 </form>
  	</div>
  	</div>
	</nav>
	
	<!--  Cdn script -->
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
	</body>
</html>