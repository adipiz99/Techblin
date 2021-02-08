package control;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.AdminBean;
import entity.BuyerBean;
import entity.ProductBean;
import entity.SellerBean;
import model.AdminModelDM;
import model.BuyerModelDM;
import model.ProductModelDM;
import model.SellerModelDM;
import model.StatsModelDM;


@WebServlet("/AdminControl")
public class AdminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS_MESS = "L'operazione e' avvenuta correttamente.";
	private static final String BADREQUEST_MESS = "L'operazione richiesta non e' valida.";
	
    private AdminModelDM modelA= new AdminModelDM();
    private BuyerModelDM modelB= new BuyerModelDM();
	private SellerModelDM modelS= new SellerModelDM();
    private ProductModelDM Pmodel= new ProductModelDM();
    private StatsModelDM STmodel= new StatsModelDM();

    public AdminControl() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action= (String) request.getParameter("action");
		
		if(action.equals("catalogo")) {
		ArrayList<ProductBean> products= new ArrayList<ProductBean>();
		Collection<ProductBean> prod;
		
		try {
			prod= Pmodel.doRetrieveAll(null);
		
			Iterator<?> it3  = prod.iterator();
			while(it3.hasNext()) {
				ProductBean bean = ((ProductBean)it3.next());
				products.add(bean);
			}
		
			System.out.println("Trovati " + products.size() + " elementi");
			
			request.setAttribute("result", products);
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/gestionecatalogo.jsp");
			dispatcher.forward(request, response);
		
		} catch (SQLException e) {e.printStackTrace();}
		}
		
		else if(action.equalsIgnoreCase("insert")) {
			AdminBean a= null;
			
			String nome= request.getParameter("nome");
			String cognome= request.getParameter("cognome");
			String email= request.getParameter("email");
			String password= request.getParameter("password");
			String confirmation= request.getParameter("confermaPassword");
			
			if(!isValidEmail(email) || !isValidPassword(password) || !isValidNameOrSurname(nome) || !isValidNameOrSurname(cognome)) {
				request.setAttribute("message", "Errore. Inserire i dati nel formato corretto.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/nuovoadmin.jsp");
				response.getWriter().write(BADREQUEST_MESS);
				dispatcher.forward(request, response);
				return;
			}
			
			
			String[] toControl= {nome, cognome, email, password, confirmation};
			
			for(String st : toControl) {
				if(st.isBlank() || st == null) {
					request.setAttribute("message", "Errore. Compilare tutti i campi per procedere alla registrazione.");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/nuovoadmin.jsp");
					response.getWriter().write(BADREQUEST_MESS);
					dispatcher.forward(request, response);
					return;
				}
			}
			
			if(!password.equals(confirmation)) {
				request.setAttribute("message", "Errore. Le password non corrispondono.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/nuovoadmin.jsp");
				response.getWriter().write(BADREQUEST_MESS);
				dispatcher.forward(request, response);
				return;
			}
			
			if(!isValidEmail(email)) {
				request.setAttribute("message", "Errore. L'indirizzo email inserito risulta errato.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/nuovoadmin.jsp");
				response.getWriter().write(BADREQUEST_MESS);
				dispatcher.forward(request, response);
				return;
			}
			
			boolean exists= checkExistence(email);
			
			if(exists) {
				request.setAttribute("message", "Errore. Sembra proprio che esista già un utente con questa email.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/nuovoadmin.jsp");
				response.getWriter().write(BADREQUEST_MESS);
				dispatcher.forward(request, response);
				return;
			}
			
			else {
				String date= (new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())).toString();
				a= new AdminBean(email, nome, cognome, password, date);
				try {
					modelA.doSave(a);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				response.sendRedirect("./successo.jsp");
				response.getWriter().write(SUCCESS_MESS);
				return;
			}
		}
		else if(action.equalsIgnoreCase("statistiche")) {
			String d1= request.getParameter("from");
			String d2= request.getParameter("to");
			
			if(d1 != null && !d1.isBlank() && d2 != null && !d2.isBlank()) {
				Date from= Date.valueOf(d1);
				Date to= Date.valueOf(d2);
				
				ArrayList<Integer> result= null;
				
				try {
					result= STmodel.calculate(from, to);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				request.setAttribute("result", result);
				RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/statistiche.jsp");
				dispatcher.forward(request, response);
				return;
			}
			else {
				request.setAttribute("message", "Errore. Inserisci le due date correttamente per continuare.");
				RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/statistiche.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}
	}
	
	
	protected boolean checkExistence(String username) {
		BuyerBean b= null;
		SellerBean s= null;
		AdminBean a= null;
		
		try {
			b= modelB.doRetrieveByKey(username);
			if(b.getEmail().isBlank()) b= null;
			else return true;
			
			s= modelS.doRetrieveByKey(username);
			if(s.getEmail().isBlank()) s= null;
			else return true;
			
			a= modelA.doRetrieveByKey(username);
			if(a.getEmail().isBlank()) a= null;
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

}
