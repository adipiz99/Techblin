package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.AdminBean;
import entity.BuyerBean;
import entity.SellerBean;
import model.AdminModelDM;
import model.BuyerModelDM;
import model.SellerModelDM;

@WebServlet("/UserControl")
public class UserControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BuyerModelDM modelB= new BuyerModelDM();
	private SellerModelDM modelS= new SellerModelDM();
	private AdminModelDM modelA= new AdminModelDM();
	private static final String SUCCESS_MESS = "L'operazione e' avvenuta correttamente.";
	private static final String BADREQUEST_MESS = "L'operazione richiesta non e' valida.";
       
	public UserControl() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione= request.getSession(true);
		@SuppressWarnings("unused")
		Boolean login= (Boolean) sessione.getAttribute("login");
		
		String action= request.getParameter("action");
		
		BuyerBean b= null;
		SellerBean s= null;
		AdminBean a= null;
		
		
		if(action.equalsIgnoreCase("password")) {
			String email= request.getParameter("email");
			String vecchia= request.getParameter("vecchia");
			String nuova= request.getParameter("nuova");
			String conferma= request.getParameter("conferma");
			
			if(email == null || vecchia == null || nuova == null || conferma == null) {
				
				request.setAttribute("message", "Compila tutti i campi.");
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/cambiopassword.jsp");
				dispatcher.forward(request, response);
				response.getWriter().write(BADREQUEST_MESS);
				return;
			}
			else if(!isValidPassword(nuova)) {
				request.setAttribute("message", "La nuova password non rispetta il formato richiesto.");
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/cambiopassword.jsp");
				response.getWriter().write(BADREQUEST_MESS);
				dispatcher.forward(request, response);
				return;
			}
			else if(!nuova.equals(conferma)) {
				request.setAttribute("message", "Le due password non coincidono.");
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/cambiopassword.jsp");
				response.getWriter().write(BADREQUEST_MESS);
				dispatcher.forward(request, response);
				return;
			}
			
			try {
				b= modelB.doRetrieveByKey(email);
				if(b.getEmail().isBlank()) b= null;
				
				s= modelS.doRetrieveByKey(email);
				if(s.getEmail().isBlank()) s= null;
				
				a= modelA.doRetrieveByKey(email);
				if(a.getEmail().isBlank()) a= null;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(b == null && s == null && a == null) {
				request.setAttribute("message", "L'utente non risulta registrato.");
				
				RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/cambiopassword.jsp");
				response.getWriter().write(BADREQUEST_MESS);
				dispatcher.forward(request, response);
				return;
			}
			
			if(b != null) {
				if(!b.getPassword().equals(vecchia)) {
					request.setAttribute("message", "La password inserita risulta errata.");
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("/cambiopassword.jsp");
					response.getWriter().write(BADREQUEST_MESS);
					dispatcher.forward(request, response);
					return;
				}
				b.setPassword(nuova);
				try {	
					modelB.doUpdate(b);	
					RequestDispatcher dispatcher = request.getRequestDispatcher("/successo.jsp");
					response.getWriter().write(SUCCESS_MESS);
					dispatcher.forward(request, response);
					return;
				}catch(Exception e) {System.out.println("Errore");}
			}
			
			else if(s != null) {
				if(!s.getPassword().equals(vecchia)) {
					request.setAttribute("message", "La password inserita risulta errata.");
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("/cambiopassword.jsp");
					response.getWriter().write(BADREQUEST_MESS);
					dispatcher.forward(request, response);
					return;
				}
				s.setPassword(nuova);
				try {
					modelS.doUpdate(s);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/successo.jsp");
					response.getWriter().write(SUCCESS_MESS);
					dispatcher.forward(request, response);
					return;
				}catch(Exception e) {System.out.println("Errore");}
			}
			
			else if(a != null) {
				if(!a.getPassword().equals(vecchia)) {
					request.setAttribute("message", "La password inserita risulta errata.");
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("/cambiopassword.jsp");
					response.getWriter().write(BADREQUEST_MESS);
					dispatcher.forward(request, response);
					return;
				}
				a.setPassword(nuova);
				try {
					modelA.doUpdate(a);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("/successo.jsp");
					response.getWriter().write(SUCCESS_MESS);
					dispatcher.forward(request, response);
					return;
				}catch(Exception e) {System.out.println("Errore");}
			}
			
		}
		else if(action.equalsIgnoreCase("email")) {
			String email= request.getParameter("email");
			String nuova= request.getParameter("nuova");
			
			if(email == null || nuova == null) {
				
				request.setAttribute("message", "Compila tutti i campi.");
				
				RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/cambioemail.jsp");
				dispatcher.forward(request, response);
				return;
			}
			else if(!isValidEmail(nuova)) {
				request.setAttribute("message", "La nuova email non rispetta il formato richiesto.");
				
				RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/cambioemail.jsp");
				dispatcher.forward(request, response);
				return;
			}
			try {
				b= modelB.doRetrieveByKey(nuova);
				if(b.getEmail().isBlank()) b= null;
				
				s= modelS.doRetrieveByKey(nuova);
				if(s.getEmail().isBlank()) s= null;
				
				a= modelA.doRetrieveByKey(nuova);
				if(a.getEmail().isBlank()) a= null;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(b == null && s == null && a == null) { // nuova email non associata a nessun utente
				try {
					b= modelB.doRetrieveByKey(email);
					if(b.getEmail().isBlank()) b= null;
				
					s= modelS.doRetrieveByKey(email);
					if(s.getEmail().isBlank()) s= null;
				
					a= modelA.doRetrieveByKey(email);
					if(a.getEmail().isBlank()) a= null;
				
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
				if(b != null) {
					b.setEmail(nuova);
					try {	
						modelB.doUpdate(b, email);
						request.getSession().invalidate();
						RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/successo.jsp");
						dispatcher.forward(request, response);
						return;
					}catch(Exception e) {System.out.println("Errore");}
				}
			
				else if(s != null) {
					s.setEmail(nuova);
					try {
						modelS.doUpdate(s, email);
						request.getSession().invalidate();
						RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/successo.jsp");
						dispatcher.forward(request, response);
						return;
					}catch(Exception e) {System.out.println("Errore");}
				}
			
				else if(a != null) {
					a.setEmail(nuova);
					try {
						modelA.doUpdate(a, email);
						request.getSession().invalidate();
						RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/successo.jsp");
						dispatcher.forward(request, response);
						return;
					}catch(Exception e) {System.out.println("Errore");}
				}
			}
		}
	}

	static boolean isValidPassword(String password) {
	      String regex = "^.{5,32}$";
	      return password.matches(regex);
	}
	
	static boolean isValidEmail(String email) {
		 String regex = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
	      return email.matches(regex);
	}
}
