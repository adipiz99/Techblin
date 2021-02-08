package control;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.BuyerBean;
import entity.SellerBean;
import model.BuyerModelDM;
import model.SellerModelDM;


@WebServlet("/RegistrationControl")
public class RegistrationControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS_MESS = "L'operazione e' avvenuta correttamente.";
	private static final String BADREQUEST_MESS = "L'operazione richiesta non e' valida.";
	private BuyerModelDM modelB= new BuyerModelDM();
	private SellerModelDM modelS= new SellerModelDM();     

    public RegistrationControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BuyerBean b= null;
		SellerBean s= null;
		
		String nome= request.getParameter("nome");
		String cognome= request.getParameter("cognome");
		String email= request.getParameter("email");
		String password= request.getParameter("password");
		String confirmation= request.getParameter("confermaPassword");
		String id= request.getParameter("id");
		String userType= request.getParameter("type");
		
		if(userType != null && !userType.isBlank()) {
			if(userType.equalsIgnoreCase("venditore")) {
				String[] toControl= {nome, cognome, email, password, confirmation, id};
				
				for(String st : toControl) {
					if(st.isBlank() || st == null) {
						request.setAttribute("message", "Errore. Compilare tutti i campi per procedere alla registrazione.");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/registrazione.jsp");
						response.getWriter().write(BADREQUEST_MESS);
						dispatcher.forward(request, response);
						return;
					}
				}
				
					if(!password.equals(confirmation)) {
						request.setAttribute("message", "Errore. Le password non corrispondono.");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/registrazione.jsp");
						response.getWriter().write(BADREQUEST_MESS);
						dispatcher.forward(request, response);
						return;
					}
					
					if(!isValidNameOrSurname(nome) || !isValidNameOrSurname(cognome)) {
						request.setAttribute("message", "Errore. Il nome o il cognome inseriti non rispettano il formato richiesto.");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/registrazione.jsp");
						response.getWriter().write(BADREQUEST_MESS);
						dispatcher.forward(request, response);
						return;
					}
					
					if(!isValidId(id)) {
						request.setAttribute("message", "Errore. L'id risulta errato  risulta errato.");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/registrazione.jsp");
						response.getWriter().write(BADREQUEST_MESS);
						dispatcher.forward(request, response);
						return;
					}
					
					if(!isValidPassword(password)) {
						request.setAttribute("message", "Errore. La password inserita non rispetta il formato richiesto.");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/registrazione.jsp");
						response.getWriter().write(BADREQUEST_MESS);
						dispatcher.forward(request, response);
						return;
					}
					
					if(!isValidEmail(email)) {
						request.setAttribute("message", "Errore. L'indirizzo email inserito risulta errato.");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/registrazione.jsp");
						response.getWriter().write(BADREQUEST_MESS);
						dispatcher.forward(request, response);
						return;
					}
					
					boolean exists= checkExistence(email);
					
					if(exists) {
						request.setAttribute("message", "Errore. Sembra proprio che esista già un utente con questa email.");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/registrazione.jsp");
						response.getWriter().write(BADREQUEST_MESS);
						dispatcher.forward(request, response);
						return;
					}
					
					else {
						String date= (new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())).toString();
						s= new SellerBean(id, email, nome, cognome, password, date);
						try {
							modelS.doSave(s);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
						response.sendRedirect("./registrazioneConclusa.jsp");
						response.getWriter().write(SUCCESS_MESS);
						return;
					}
					
		}
			else if(userType.equalsIgnoreCase("acquirente")) {
				String[] toControl= {nome, cognome, email, password, confirmation};
				
				for(String st : toControl) {
					if(st.isBlank() || st == null) {
						request.setAttribute("message", "Errore. Compilare tutti i campi per procedere alla registrazione.");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/registrazione.jsp");
						response.getWriter().write(BADREQUEST_MESS);
						dispatcher.forward(request, response);
						return;
					}
				}
				
					if(!isValidNameOrSurname(nome) || !isValidNameOrSurname(cognome)) {
						request.setAttribute("message", "Errore. Il nome o il cognome inseriti non rispettano il formato richiesto.");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/registrazione.jsp");
						response.getWriter().write(BADREQUEST_MESS);
						dispatcher.forward(request, response);
						return;
					}
				
					if(!isValidPassword(password)) {
						request.setAttribute("message", "Errore. La password inserita non rispetta il formato richiesto.");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/registrazione.jsp");
						response.getWriter().write(BADREQUEST_MESS);
						dispatcher.forward(request, response);
						return;
					}
					
					if(!password.equals(confirmation)) {
						request.setAttribute("message", "Errore. Le password non corrispondono.");
						response.getWriter().write(BADREQUEST_MESS);
						RequestDispatcher dispatcher = request.getRequestDispatcher("/registrazione.jsp");
						dispatcher.forward(request, response);
						return;
					}
					
					if(!isValidEmail(email)) {
						request.setAttribute("message", "Errore. L'indirizzo email inserito risulta errato.");
						response.getWriter().write(BADREQUEST_MESS);
						RequestDispatcher dispatcher = request.getRequestDispatcher("/registrazione.jsp");
						dispatcher.forward(request, response);
						return;
					}
					
					boolean exists= checkExistence(email);
					
					if(exists) {
						request.setAttribute("message", "Errore. Sembra proprio che esista già un utente con questa email.");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/registrazione.jsp");
						response.getWriter().write(BADREQUEST_MESS);
						dispatcher.forward(request, response);
						return;
					}
					
					else {
						String date= (new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())).toString();
						b= new BuyerBean(email, nome, cognome, password, date);
						try {
							modelB.doSave(b);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
						response.sendRedirect("./registrazioneConclusa.jsp");
						response.getWriter().write(SUCCESS_MESS);
						return;
					}
			}
		}
		
		
		
	}
	
	protected boolean checkExistence(String username) {
		BuyerBean b= null;
		SellerBean s= null;
		
		try {
			b= modelB.doRetrieveByKey(username);
			if(b.getEmail().isBlank()) b= null;
			else return true;
			
			s= modelS.doRetrieveByKey(username);
			if(s.getEmail().isBlank()) s= null;
			else return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	static boolean isValidEmail(String email) {
	      String regex = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
	      return email.matches(regex);
	}
	
	static boolean isValidPassword(String password) {
	      String regex = "^.{5,32}$";
	      return password.matches(regex);
	}
	
	static boolean isValidNameOrSurname(String s) {
	      String regex = "^[A-Za-z]{1,20}$";
	      return s.matches(regex);
	}
	
	static boolean isValidId(String s) {
	      String regex = "^[a-zA-Z0-9]{4,20}$";
	      return s.matches(regex);
	}
}
