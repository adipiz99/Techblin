package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Cart;
import entity.OrderBean;
import entity.OrderProductBean;
import entity.ProductBean;
import model.OrderModelDM;
import model.OrderProductModelDM;
import model.ProductModelDM;

@WebServlet("/OrderControl")
public class OrderControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	OrderModelDM model= new OrderModelDM();
	OrderProductModelDM OPmodel= new OrderProductModelDM();
	ProductModelDM Pmodel= new ProductModelDM();
	private static final String BADREQUEST_MESS = "L'operazione richiesta non e' valida.";
	private static final String SUCCESS_MESS = "L'operazione e' avvenuta correttamente.";
       
    public OrderControl() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action= request.getParameter("action");
		
		if(action != null && !action.isBlank()) {
			if(action.equalsIgnoreCase("add")) {
				String nome= request.getParameter("nome");
				String cognome= request.getParameter("cognome");
				String email= request.getParameter("email");
				String indirizzo= request.getParameter("indirizzo");
				String provincia= request.getParameter("provincia");
				String città= request.getParameter("citta");
 				String cap= request.getParameter("cap");
				String paymentMethod= request.getParameter("paymentMethod");
				String titolare= request.getParameter("titolare");
				String carta= request.getParameter("carta");
				String scadenza= request.getParameter("scadenza");
				String cvv= request.getParameter("cvv");
				String conto= request.getParameter("conto");
				String paypal= request.getParameter("paypal");
				String user= request.getParameter("user");
				@SuppressWarnings("unchecked")
				Cart<ProductBean> cart= ((Cart<ProductBean>) request.getSession().getAttribute("carrello"));
				
				if(!isValidName(nome)) {
					request.setAttribute("message", "Errore. Il nome non rispetta il formato richiesto.");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/checkout.jsp");
					response.getWriter().write(BADREQUEST_MESS);
					dispatcher.forward(request, response);
					return;
				}
				
				if(!isValidSurname(cognome)) {
					request.setAttribute("message", "Errore. Il cognome non rispetta il formato richiesto.");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/checkout.jsp");
					response.getWriter().write(BADREQUEST_MESS);
					dispatcher.forward(request, response);
					return;
				}
				
				if(!isValidEmail(email)) {
					request.setAttribute("message", "Errore. L'email non rispetta il formato richiesto.");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/checkout.jsp");
					response.getWriter().write(BADREQUEST_MESS);
					dispatcher.forward(request, response);
					return;
				}
				
				if(!isValidAddress(indirizzo)) {
					request.setAttribute("message", "Errore. L'email non rispetta il formato richiesto.");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/checkout.jsp");
					response.getWriter().write(BADREQUEST_MESS);
					dispatcher.forward(request, response);
					return;
				}
				
				if(!isValidCap(cap)) {
					request.setAttribute("message", "Errore. Il cap non rispetta il formato richiesto.");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/checkout.jsp");
					response.getWriter().write(BADREQUEST_MESS);
					dispatcher.forward(request, response);
					return;
				}
				
				if(!isValidProvincia(provincia)) {
					request.setAttribute("message", "Errore. La provincia non rispetta il formato richiesto.");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/checkout.jsp");
					response.getWriter().write(BADREQUEST_MESS);
					dispatcher.forward(request, response);
					return;
				}
				
				if(!isValidCity(città)){
					request.setAttribute("message", "Errore. La provincia non rispetta il formato richiesto.");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/checkout.jsp");
					response.getWriter().write(BADREQUEST_MESS);
					dispatcher.forward(request, response);
					return;
				}
	
				String details= null;
				
				if(nome.isBlank()||cognome.isBlank()||email.isBlank()||indirizzo.isBlank()||provincia.isBlank()||città.isBlank()||cap.isBlank()||paymentMethod.isBlank()||user.isBlank()||cart == null || cart.getItems().isEmpty()) {
					request.setAttribute("message", "Alcuni campi non risultano compilati. Si prega di riempire il modulo per continuare.");
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("/checkout.jsp");
					response.getWriter().write(BADREQUEST_MESS);
					dispatcher.forward(request, response);
					return;
				}
				
				if(paymentMethod.equalsIgnoreCase("carta")) {
					if(titolare.isBlank()||carta.isBlank()||scadenza.isBlank()||cvv.isBlank()) {
						request.setAttribute("message", "Alcuni campi non risultano compilati. Si prega di riempire il modulo per continuare.");
						
						RequestDispatcher dispatcher = request.getRequestDispatcher("/checkout.jsp");
						response.getWriter().write(BADREQUEST_MESS);
						dispatcher.forward(request, response);
						return;
					}
					
					if(!preliminaryControlCard(request, response, titolare, carta, scadenza, cvv)){
						request.setAttribute("message", "Errore. I dati di pagamento inseriti non rispettano il formato richiesto.");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/checkout.jsp");
						response.getWriter().write(BADREQUEST_MESS);
						dispatcher.forward(request, response);
						return;
					}
					
					details= "Carta che finisce con: " + carta.substring(carta.length() -4, carta.length()) + " Scad. " + scadenza + ", di: " + titolare;
				}
				else if(paymentMethod.equalsIgnoreCase("conto")) {
					if(titolare.isBlank()||conto.isBlank()) {
						request.setAttribute("message", "Alcuni campi non risultano compilati. Si prega di riempire il modulo per continuare.");
						
						RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/checkout.jsp");
						dispatcher.forward(request, response);
						return;
					}
					
					details= "Conto Corrente con IBAN: " + conto + ", intestato a: " + titolare;
				}
				else if(paymentMethod.equalsIgnoreCase("paypal")) {
					if(paypal.isBlank()) {
						request.setAttribute("message", "Alcuni campi non risultano compilati. Si prega di riempire il modulo per continuare.");
						
						RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/checkout.jsp");
						dispatcher.forward(request, response);
						return;
					}
					
					details= "Account PayPal di: " + paypal;
				}
				
				int totale= 0;
				
				for(ProductBean p : cart.getItems()) {
					totale += p.getPrice();
				}
				
				long millis=System.currentTimeMillis();  
		        java.sql.Date date=new java.sql.Date(millis);
				
				OrderBean bean= new OrderBean();
				
				bean.setTotale(totale);
				bean.setStato("Ricevuto");
				bean.setDataOrdine(date);
				bean.setAcquirente(user);
				bean.setIndirizzo(indirizzo);
				bean.setCap(cap);
				bean.setCittà(città);
				bean.setProvincia(provincia);
				bean.setTipologiaPagamento(paymentMethod);
				bean.setDatiPagamento(details);
				bean.setNome(nome);
				bean.setCognome(cognome);
				
				int id= 0;
				
				try {
					model.doSave(bean);
					Collection<OrderBean> orders= model.doRetrieveAll(null);
					
					Iterator<?> it  = orders.iterator();
					while(it.hasNext()) {
						OrderBean order = ((OrderBean)it.next());
						if(order.getId() > id) id = order.getId();
					}
					
					for(ProductBean p : cart.getItems()) {
						OrderProductBean OPbean= new OrderProductBean();
						OPbean.setOrdine(id);
						OPbean.setProdotto(p.getCode());
						
						ProductBean prod= Pmodel.doRetrieveByKey("" + p.getCode());
						prod.setQuantity(prod.getQuantity() - 1);
						Pmodel.doUpdate(prod);
						
						OPmodel.doSave(OPbean);
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				ArrayList<ProductBean> ordinati= new ArrayList<ProductBean>();
				
				for(ProductBean p : cart.getItems())
					ordinati.add(p);
				
				cart.deleteItems();
				
				bean.setId(id);
				request.setAttribute("ordine", bean);
				request.setAttribute("prodotti", ordinati);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/confermaordine.jsp");
				response.getWriter().write(SUCCESS_MESS);
				dispatcher.forward(request, response);
				return;
			}
			else if(action.equalsIgnoreCase("retrieveall")) {
				String user= request.getParameter("user");
				
				try {
					Collection<OrderBean> orders= model.doRetrieveAll(null);
					
					Iterator<?> it  = orders.iterator();
					while(it.hasNext()) {
						OrderBean order = ((OrderBean)it.next());
						if(!order.getAcquirente().equalsIgnoreCase(user)) it.remove();
					}
					
					ArrayList<OrderBean> orderAL= new ArrayList<OrderBean>();
					
					Iterator<?> it2  = orders.iterator();
					while(it2.hasNext()) {
						OrderBean order = ((OrderBean)it2.next());
						orderAL.add(order);
					}
					
					request.setAttribute("ordini", orderAL);
					request.setAttribute("user", user);
					
					RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/ordini.jsp");
					dispatcher.forward(request, response);
					return;
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			else if(action.equalsIgnoreCase("retrieve")) {
				String order= request.getParameter("order");
				int id= Integer.parseInt(order);
				
				try {
					OrderBean ordine= model.doRetrieveByKey(id);
					
					Collection<OrderProductBean> collection= OPmodel.doRetrieveAll(null);
					
					Iterator<?> it  = collection.iterator();
					while(it.hasNext()) {
						OrderProductBean op = ((OrderProductBean)it.next());
						if(op.getOrdine() != id) it.remove();
					}
					
					ArrayList<ProductBean> ordinati= new ArrayList<ProductBean>();
					
					Iterator<?> it2  = collection.iterator();
					while(it2.hasNext()) {
						OrderProductBean op = ((OrderProductBean)it2.next());
						ProductBean p= Pmodel.doRetrieveByKey("" + op.getProdotto());
						ordinati.add(p);
					}
					
					request.setAttribute("ordine", ordine);
					request.setAttribute("prodotti", ordinati);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/riepilogoOrdine.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}
	}
	
	private boolean preliminaryControlCard(HttpServletRequest request, HttpServletResponse response, String holder, String card, String exp, String cvv) throws ServletException, IOException {
		if(!isValidHolder(holder) || !isValidCard(card) || !isValidExp(exp) || !isValidCvv(cvv))
			return false;
		return true;
	}
	
	static boolean isValidName(String name) {
		String regex = "^[A-Za-z]{1,20}$";
		return name.matches(regex);
	}
	
	static boolean isValidSurname(String surname) {
		String regex = "^[A-Za-z]{1,20}$";
		return surname.matches(regex);
	}
	
	static boolean isValidEmail(String email) {
	      String regex = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
	      return email.matches(regex);
	}
	
	static boolean isValidAddress(String address) {
	      String regex = "^[a-zA-Z0-9,. ]*$";
	      return address.matches(regex);
	}
	
	static boolean isValidCity(String city) {
	      String regex = "^[a-zA-Z\\s]*$";
	      return city.matches(regex);
	}
	
	static boolean isValidCap(String cap) {
	      String regex = "^([0-9]{5})$";
	      return cap.matches(regex);
	}
	
	static boolean isValidProvincia(String prov) {
	      String regex = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$";
	      return prov.matches(regex);
	}
	
	static boolean isValidHolder(String holder) {
	      String regex = "^^[a-zA-Z\\s]{5,40}$";
	      return holder.matches(regex);
	}
	
	static boolean isValidCard(String card) {
	      String regex = "^[0-9]{8,19}$";
	      return card.matches(regex);
	}
	
	static boolean isValidExp(String exp) {
	      String regex = "^(0[1-9]|1[0-2])\\/?([0-9]{4}|[0-9]{2})$";
	      return exp.matches(regex);
	}
	
	static boolean isValidCvv(String cvv) {
	      String regex = "^[0-9]{3,4}$";
	      return cvv.matches(regex);
	}

}