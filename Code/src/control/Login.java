package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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

@WebServlet(description = "Login servlet", urlPatterns = { "/Login" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BuyerModelDM modelB= new BuyerModelDM();
	private SellerModelDM modelS= new SellerModelDM();
	private AdminModelDM modelA= new AdminModelDM();
	private static final String SUCCESS_MESS = "L'operazione e' avvenuta correttamente.";
	private static final String BADREQUEST_MESS = "L'operazione richiesta non e' valida.";
	
    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cookie[] cookies= request.getCookies();
		@SuppressWarnings("unused")
		HttpSession session= request.getSession(true);
		
		String username= (String) request.getParameter("email");
		String password= (String) request.getParameter("password");
		String checkbox= (String) request.getParameter("checkbox");
		System.out.println(request.getParameter("checkbox"));
		
		BuyerBean b= null;
		SellerBean s= null;
		AdminBean a= null;
		
		request.setAttribute("Message", "");
		request.setAttribute("status", "success");
		

		 
		 if(!isValidEmail(username)) {
			 	request.getSession().setAttribute("User", null);
				request.setAttribute("message", "Errore. L'indirizzo email inserito risulta errato.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
				response.getWriter().write(BADREQUEST_MESS);
				dispatcher.forward(request, response);
				return;
			}
		
		if(checkbox == null) {
			checkbox= "off";
		}
		
		for(Cookie c : cookies) {
			// Ricerca Cookie User
			if(c.getName().equalsIgnoreCase("User")) {
				username= c.getValue();
				try {
					//Ricerca utente specificato dal cookie
					b= modelB.doRetrieveByKey(username);
					if(b.getEmail().isBlank()) b= null;
					
					s= modelS.doRetrieveByKey(username);
					if(s.getEmail().isBlank()) s= null;
					
					a= modelA.doRetrieveByKey(username);
					if(a.getEmail().isBlank()) a= null;
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
			if(b != null) {
					request.getSession().setAttribute("User", b);
					request.getSession().setAttribute("Type", "acquirente");
					request.getSession().setAttribute("login", true);
					
					RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/index.jsp");
					dispatcher.forward(request, response);
					return;
			}
			
			else if(s != null) {
					request.getSession().setAttribute("User", s);
					request.getSession().setAttribute("Type", "venditore");
					request.getSession().setAttribute("login", true);
					
					RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/index.jsp");
					dispatcher.forward(request, response);
					return;
			}
			
			else if(a != null) {
					request.getSession().setAttribute("User", a);
					request.getSession().setAttribute("Type", "amministratore");
					request.getSession().setAttribute("login", true);
					
					response.sendRedirect("./index.jsp");
					return;
			}
		
		
		if(username == null || password == null) {
			request.getSession().setAttribute("login", false);
		}
		
		try {
			 preliminaryControl(request, response, username);//Format check on email field
			//Cerca tra lo username gli utenti
			b= modelB.doRetrieveByKey(username);
			if(b.getEmail().isBlank()) b= null;
			
			s= modelS.doRetrieveByKey(username);
			if(s.getEmail().isBlank()) s= null;
			
			a= modelA.doRetrieveByKey(username);
			if(a.getEmail().isBlank()) a= null;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(b != null) {
			if(b.getPassword().equals(password)) {
				request.getSession(true).setAttribute("User", b);
				request.getSession().setAttribute("Type", "acquirente");
				request.getSession().setAttribute("login", true);
				
				if(checkbox.equalsIgnoreCase("on")) {
					Cookie d= new Cookie("User", username);
					d.setMaxAge(60*60*24*365);
					response.addCookie(d);
				}
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
				response.getWriter().write(SUCCESS_MESS);
				dispatcher.forward(request, response);
				return;
			}
			//Username trovato e password non valida o null
			request.setAttribute("status", "failure");
			request.setAttribute("Message", "Password errata.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			response.getWriter().write(BADREQUEST_MESS);
			dispatcher.forward(request, response);
			return;
		}
		
		else if(s != null) {
			if(s.getPassword().equals(password)) {
				request.getSession(true).setAttribute("User", s);
				request.getSession().setAttribute("Type", "venditore");
				request.getSession().setAttribute("login", true);
				
				if(checkbox.equalsIgnoreCase("on")) {
					Cookie d= new Cookie("User", username);
					d.setMaxAge(60*60*24*365);
					response.addCookie(d);
				}
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
				response.getWriter().write(SUCCESS_MESS);
				dispatcher.forward(request, response);
				return;
			}
			//Username trovato e password non valida o null
			request.setAttribute("status", "failure");
			request.setAttribute("Message", "Password errata.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			response.getWriter().write(BADREQUEST_MESS);
			dispatcher.forward(request, response);
			return;
		}
		
		else if(a != null) {
			if(a.getPassword().equals(password)) {
				request.getSession(true).setAttribute("User", a);
				request.getSession().setAttribute("Type", "amministratore");
				request.getSession().setAttribute("login", true);
				
				if(checkbox.equalsIgnoreCase("on")) {
					Cookie d= new Cookie("User", username);
					d.setMaxAge(60*60*24*365);
					response.addCookie(d);
				}
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
				response.getWriter().write(SUCCESS_MESS);
				dispatcher.forward(request, response);
				return;
			}
			//Username trovato e password non valida o null
			request.setAttribute("status", "failure");
			request.setAttribute("Message", "Password errata.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			response.getWriter().write(BADREQUEST_MESS);
			dispatcher.forward(request, response);
			return;
		}
		
		else {
			//Username non trovato
			request.setAttribute("status", "failure");
			request.setAttribute("Message", "Email o Password errati.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			response.getWriter().write(BADREQUEST_MESS);
			dispatcher.forward(request, response);
			return;
		}
	}
	
	private void preliminaryControl(HttpServletRequest request, HttpServletResponse response, String email) throws ServletException, IOException {
		if(!isValidEmail(email)) {
			request.setAttribute("status", "failure");
			request.setAttribute("Message", "Formato email non corretto.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			response.getWriter().write(BADREQUEST_MESS);
			dispatcher.forward(request, response);
			return;
		}
	}
	
	static boolean isValidEmail(String email) {
	      String regex ="^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
	      return email.matches(regex);
	}

}
