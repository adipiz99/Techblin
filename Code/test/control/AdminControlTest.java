package control;

import static org.mockito.Mockito.mock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.TestCase;
import model.AdminModelDMTest;

public class AdminControlTest{

	private AdminControl servlet = new AdminControl();
	private HttpServletRequest request;
	private HttpServletResponse response;
	private RequestDispatcher rd;
	
	/** messaggio di errore inviato in caso di bad request. **/
	  private static final String BADREQUEST_MESS = "L'operazione richiesta non e' valida.";

	  /** messaggio restituito in caso di successo dell'operazione. **/
	  private static final String SUCCESS_MESS = "L'operazione e' avvenuta correttamente.";
	
	  @Before
		public void initializationMock() {
			request = mock(HttpServletRequest.class);
			response = mock(HttpServletResponse.class);
			rd = mock(RequestDispatcher.class);
		}
	
	  @Test
	  public void testEmail() throws Exception {
		 when(request.getParameter("action")).thenReturn("insert");
		 when(request.getParameter("nome")).thenReturn("Vincenzo");
		 when(request.getParameter("cognome")).thenReturn("Bianchi");
		 when(request.getParameter("email")).thenReturn("admin2@example");
		 when(request.getParameter("password")).thenReturn("Admin2");
		 when(request.getParameter("confermapassword")).thenReturn("Admin2");
		 when(request.getRequestDispatcher(("/nuovoadmin.jsp"))).thenReturn(rd);
		 when(request.getRequestDispatcher(("/successo.jsp"))).thenReturn(rd);
		 
		 PrintWriter MyWriter = mock(PrintWriter.class);
		 when(response.getWriter()).thenReturn(MyWriter);
		 servlet.doPost(request, response);	
		 verify(MyWriter).write(BADREQUEST_MESS);
	}
	  
	  @Test
	  public void testEmailAlreadyPresent() throws Exception {
		 when(request.getParameter("action")).thenReturn("insert");
		 when(request.getParameter("nome")).thenReturn("Vincenzo");
		 when(request.getParameter("cognome")).thenReturn("Bianchi");
		 when(request.getParameter("email")).thenReturn("admin@example.it");
		 when(request.getParameter("password")).thenReturn("Admin2");
		 when(request.getParameter("confermaPassword")).thenReturn("Admin2");
		 when(request.getRequestDispatcher(("/nuovoadmin.jsp"))).thenReturn(rd);
		 when(request.getRequestDispatcher(("/successo.jsp"))).thenReturn(rd);
		 
		 PrintWriter MyWriter = mock(PrintWriter.class);
		 when(response.getWriter()).thenReturn(MyWriter);
		 servlet.doPost(request, response);	
		 verify(MyWriter).write(BADREQUEST_MESS);
	}
	  
	  @Test
	  public void testPassword() throws Exception {
		 when(request.getParameter("action")).thenReturn("insert");
		 when(request.getParameter("nome")).thenReturn("Vincenzo");
		 when(request.getParameter("cognome")).thenReturn("Bianchi");
		 when(request.getParameter("email")).thenReturn("admin2@example.it");
		 when(request.getParameter("password")).thenReturn("adm");
		 when(request.getParameter("confermaPassword")).thenReturn("adm");
		 when(request.getRequestDispatcher(("/nuovoadmin.jsp"))).thenReturn(rd);
		 when(request.getRequestDispatcher(("/successo.jsp"))).thenReturn(rd);
		 
		 PrintWriter MyWriter = mock(PrintWriter.class);
		 when(response.getWriter()).thenReturn(MyWriter);
		 servlet.doPost(request, response);	
		 verify(MyWriter).write(BADREQUEST_MESS);
	}
	
	  @Test
	  public void testTwoPasswordNotEquals() throws Exception {
		 when(request.getParameter("action")).thenReturn("insert");
		 when(request.getParameter("nome")).thenReturn("Vincenzo");
		 when(request.getParameter("cognome")).thenReturn("Bianchi");
		 when(request.getParameter("email")).thenReturn("admin2@example.it");
		 when(request.getParameter("password")).thenReturn("Admin2");
		 when(request.getParameter("confermaPassword")).thenReturn("adminw");
		 when(request.getRequestDispatcher(("/nuovoadmin.jsp"))).thenReturn(rd);
		 when(request.getRequestDispatcher(("/successo.jsp"))).thenReturn(rd);
		 
		 PrintWriter MyWriter = mock(PrintWriter.class);
		 when(response.getWriter()).thenReturn(MyWriter);
		 servlet.doPost(request, response);	
		 verify(MyWriter).write(BADREQUEST_MESS);
	}
	  
	  @Test
	  public void testNome() throws Exception {
		 when(request.getParameter("action")).thenReturn("insert");
		 when(request.getParameter("nome")).thenReturn("Vincenzo1");
		 when(request.getParameter("cognome")).thenReturn("Bianchi");
		 when(request.getParameter("email")).thenReturn("admin2@example.it");
		 when(request.getParameter("password")).thenReturn("Admin2");
		 when(request.getParameter("confermaPassword")).thenReturn("Admin2");
		 when(request.getRequestDispatcher(("/nuovoadmin.jsp"))).thenReturn(rd);
		 when(request.getRequestDispatcher(("/successo.jsp"))).thenReturn(rd);
		 
		 PrintWriter MyWriter = mock(PrintWriter.class);
		 when(response.getWriter()).thenReturn(MyWriter);
		 servlet.doPost(request, response);	
		 verify(MyWriter).write(BADREQUEST_MESS);
	}
	  
	  @Test
	  public void testCognome() throws Exception {
		 when(request.getParameter("action")).thenReturn("insert");
		 when(request.getParameter("nome")).thenReturn("Vincenzo");
		 when(request.getParameter("cognome")).thenReturn("B!anchi");
		 when(request.getParameter("email")).thenReturn("admin2@example.it");
		 when(request.getParameter("password")).thenReturn("Admin2");
		 when(request.getParameter("confermaPassword")).thenReturn("Admin2");
		 when(request.getRequestDispatcher(("/nuovoadmin.jsp"))).thenReturn(rd);
		 when(request.getRequestDispatcher(("/successo.jsp"))).thenReturn(rd);
		 
		 PrintWriter MyWriter = mock(PrintWriter.class);
		 when(response.getWriter()).thenReturn(MyWriter);
		 servlet.doPost(request, response);	
		 verify(MyWriter).write(BADREQUEST_MESS);
	}
	  
	  @Test
	  public void testNewAdmin() throws Exception {
		 when(request.getParameter("action")).thenReturn("insert");
		 when(request.getParameter("nome")).thenReturn("Vincenzo");
		 when(request.getParameter("cognome")).thenReturn("Bianchi");
		 when(request.getParameter("email")).thenReturn("admin2@example.it");
		 when(request.getParameter("password")).thenReturn("Admin2");
		 when(request.getParameter("confermaPassword")).thenReturn("Admin2");
		 when(request.getRequestDispatcher(("/nuovoadmin.jsp"))).thenReturn(rd);
		 when(request.getRequestDispatcher(("/successo.jsp"))).thenReturn(rd);
		 
		 PrintWriter MyWriter = mock(PrintWriter.class);
		 when(response.getWriter()).thenReturn(MyWriter);
		 servlet.doPost(request, response);	
		 verify(MyWriter).write(SUCCESS_MESS);
	}
	  

	
}
