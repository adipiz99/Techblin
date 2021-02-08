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

public class RegistrationControlTest {

	private RegistrationControl servlet = new RegistrationControl();
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
	public void testEmailAcquirente() throws Exception{
		when(request.getParameter("nome")).thenReturn("Mario");
		when(request.getParameter("cognome")).thenReturn("Verdi");
		when(request.getParameter("email")).thenReturn("esempio@esempio");
		when(request.getParameter("password")).thenReturn("Acquirente2");
		when(request.getParameter("confermaPassword")).thenReturn("Acquirente2");
		when(request.getParameter("type")).thenReturn("acquirente");
		when(request.getRequestDispatcher(("/registrazione.jsp"))).thenReturn(rd);
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(BADREQUEST_MESS);
	}
	
	@Test
	public void testEmailAlreadyPresentAcquirente() throws Exception{
		when(request.getParameter("nome")).thenReturn("Mario");
		when(request.getParameter("cognome")).thenReturn("Verdi");
		when(request.getParameter("email")).thenReturn("acquirente@example.it");
		when(request.getParameter("password")).thenReturn("Acquirente2");
		when(request.getParameter("confermaPassword")).thenReturn("Acquirente2");
		when(request.getParameter("type")).thenReturn("acquirente");
		when(request.getRequestDispatcher(("/registrazione.jsp"))).thenReturn(rd);
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(BADREQUEST_MESS);
	}
	
	@Test
	public void testPasswordAcquirente() throws Exception{
		when(request.getParameter("nome")).thenReturn("Mario");
		when(request.getParameter("cognome")).thenReturn("Verdi");
		when(request.getParameter("email")).thenReturn("newacquirente@example.it");
		when(request.getParameter("password")).thenReturn("ciao");
		when(request.getParameter("confermaPassword")).thenReturn("ciao");
		when(request.getParameter("type")).thenReturn("acquirente");
		when(request.getRequestDispatcher(("/registrazione.jsp"))).thenReturn(rd);
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(BADREQUEST_MESS);
	}
	
	@Test
	public void testPasswordNotEqualsAcquirente() throws Exception{
		when(request.getParameter("nome")).thenReturn("Mario");
		when(request.getParameter("cognome")).thenReturn("Verdi");
		when(request.getParameter("email")).thenReturn("newacquirente@example.it");
		when(request.getParameter("password")).thenReturn("Acquirente2");
		when(request.getParameter("confermaPassword")).thenReturn("Acquirente");
		when(request.getParameter("type")).thenReturn("acquirente");
		when(request.getRequestDispatcher(("/registrazione.jsp"))).thenReturn(rd);
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(BADREQUEST_MESS);
	}
	
	@Test
	public void testNomeAcquirente() throws Exception{
		when(request.getParameter("nome")).thenReturn("Mario2");
		when(request.getParameter("cognome")).thenReturn("Verdi");
		when(request.getParameter("email")).thenReturn("newacquirente@example.it");
		when(request.getParameter("password")).thenReturn("Acquirente2");
		when(request.getParameter("confermaPassword")).thenReturn("Acquirente2");
		when(request.getParameter("type")).thenReturn("acquirente");
		when(request.getRequestDispatcher(("/registrazione.jsp"))).thenReturn(rd);
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(BADREQUEST_MESS);
	}
	
	@Test
	public void testCognomeAcquirente() throws Exception{
		when(request.getParameter("nome")).thenReturn("Mario");
		when(request.getParameter("cognome")).thenReturn("Verdi9");
		when(request.getParameter("email")).thenReturn("newacquirente@example.it");
		when(request.getParameter("password")).thenReturn("Acquirente2");
		when(request.getParameter("confermaPassword")).thenReturn("Acquirente2");
		when(request.getParameter("type")).thenReturn("acquirente");
		when(request.getRequestDispatcher(("/registrazione.jsp"))).thenReturn(rd);
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(BADREQUEST_MESS);
	}
	
	@Test
	public void testRegistrazioneAcquirente() throws Exception{
		when(request.getParameter("nome")).thenReturn("Mario");
		when(request.getParameter("cognome")).thenReturn("Verdi");
		when(request.getParameter("email")).thenReturn("newacquirente@example.it");
		when(request.getParameter("password")).thenReturn("Acquirente2");
		when(request.getParameter("confermaPassword")).thenReturn("Acquirente2");
		when(request.getParameter("type")).thenReturn("acquirente");
		when(request.getRequestDispatcher(("/registrazione.jsp"))).thenReturn(rd);
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(SUCCESS_MESS);
	}
	
	@Test   
	public void testIdVenditore() throws Exception{
		when(request.getParameter("id")).thenReturn("ven!");
		when(request.getParameter("nome")).thenReturn("Marco");
		when(request.getParameter("cognome")).thenReturn("Rossi");
		when(request.getParameter("email")).thenReturn("newvenditore@example.it");
		when(request.getParameter("password")).thenReturn("Venditore2");
		when(request.getParameter("confermaPassword")).thenReturn("Venditore2");
		when(request.getParameter("type")).thenReturn("venditore");
		when(request.getRequestDispatcher(("/registrazione.jsp"))).thenReturn(rd);
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(BADREQUEST_MESS);
	}
	
	@Test   
	public void testEmailVenditore() throws Exception{
		when(request.getParameter("id")).thenReturn("NewVenditore");
		when(request.getParameter("nome")).thenReturn("Marco");
		when(request.getParameter("cognome")).thenReturn("Rossi");
		when(request.getParameter("email")).thenReturn("esempio@esempio");
		when(request.getParameter("password")).thenReturn("Venditore2");
		when(request.getParameter("confermaPassword")).thenReturn("Venditore2");
		when(request.getParameter("type")).thenReturn("venditore");
		when(request.getRequestDispatcher(("/registrazione.jsp"))).thenReturn(rd);
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(BADREQUEST_MESS);
	}
	
	@Test   
	public void testEmailAlreadyPresentVenditore() throws Exception{
		when(request.getParameter("id")).thenReturn("NewVenditore");
		when(request.getParameter("nome")).thenReturn("Marco");
		when(request.getParameter("cognome")).thenReturn("Rossi");
		when(request.getParameter("email")).thenReturn("venditore@example.it");
		when(request.getParameter("password")).thenReturn("Venditore2");
		when(request.getParameter("confermaPassword")).thenReturn("Venditore2");
		when(request.getParameter("type")).thenReturn("venditore");
		when(request.getRequestDispatcher(("/registrazione.jsp"))).thenReturn(rd);
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(BADREQUEST_MESS);
	}
	
	@Test   
	public void testPasswordVenditore() throws Exception{
		when(request.getParameter("id")).thenReturn("NewVenditore");
		when(request.getParameter("nome")).thenReturn("Marco");
		when(request.getParameter("cognome")).thenReturn("Rossi");
		when(request.getParameter("email")).thenReturn("newvenditore@example.it");
		when(request.getParameter("password")).thenReturn("ciao");
		when(request.getParameter("confermaPassword")).thenReturn("ciao");
		when(request.getParameter("type")).thenReturn("venditore");
		when(request.getRequestDispatcher(("/registrazione.jsp"))).thenReturn(rd);
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(BADREQUEST_MESS);
	}
	
	@Test   
	public void testPasswordNotEqualsVenditore() throws Exception{
		when(request.getParameter("id")).thenReturn("NewVenditore");
		when(request.getParameter("nome")).thenReturn("Marco");
		when(request.getParameter("cognome")).thenReturn("Rossi");
		when(request.getParameter("email")).thenReturn("newvenditore@example.it");
		when(request.getParameter("password")).thenReturn("Venditore2");
		when(request.getParameter("confermaPassword")).thenReturn("Venditore");
		when(request.getParameter("type")).thenReturn("venditore");
		when(request.getRequestDispatcher(("/registrazione.jsp"))).thenReturn(rd);
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(BADREQUEST_MESS);
	}
	
	@Test   
	public void testNomeVenditore() throws Exception{
		when(request.getParameter("id")).thenReturn("NewVenditore");
		when(request.getParameter("nome")).thenReturn("Marco2");
		when(request.getParameter("cognome")).thenReturn("Rossi");
		when(request.getParameter("email")).thenReturn("newvenditore@example.it");
		when(request.getParameter("password")).thenReturn("Venditore2");
		when(request.getParameter("confermaPassword")).thenReturn("Venditore2");
		when(request.getParameter("type")).thenReturn("venditore");
		when(request.getRequestDispatcher(("/registrazione.jsp"))).thenReturn(rd);
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(BADREQUEST_MESS);
	}
	
	@Test   
	public void testCognomeVenditore() throws Exception{
		when(request.getParameter("id")).thenReturn("NewVenditore");
		when(request.getParameter("nome")).thenReturn("Marco");
		when(request.getParameter("cognome")).thenReturn("Ross1");
		when(request.getParameter("email")).thenReturn("newvenditore@example.it");
		when(request.getParameter("password")).thenReturn("Venditore2");
		when(request.getParameter("confermaPassword")).thenReturn("Venditore2");
		when(request.getParameter("type")).thenReturn("venditore");
		when(request.getRequestDispatcher(("/registrazione.jsp"))).thenReturn(rd);
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(BADREQUEST_MESS);
	}
	
	@Test   
	public void testRegistrazioneVenditore() throws Exception{
		when(request.getParameter("id")).thenReturn("NewVenditore");
		when(request.getParameter("nome")).thenReturn("Marco");
		when(request.getParameter("cognome")).thenReturn("Rossi");
		when(request.getParameter("email")).thenReturn("newvenditore@example.it");
		when(request.getParameter("password")).thenReturn("Venditore2");
		when(request.getParameter("confermaPassword")).thenReturn("Venditore2");
		when(request.getParameter("type")).thenReturn("venditore");
		when(request.getRequestDispatcher(("/registrazione.jsp"))).thenReturn(rd);
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(SUCCESS_MESS);
	}

	
	
	
}
