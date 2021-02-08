package control;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

public class UserControlTest {
	
	private UserControl servlet = new UserControl();
	private HttpServletRequest request;
	private HttpServletResponse response;
	private RequestDispatcher rd;
	private HttpSession session;

	/** messaggio di errore inviato in caso di bad request. **/
	  private static final String BADREQUEST_MESS = "L'operazione richiesta non e' valida.";

	  /** messaggio restituito in caso di successo dell'operazione. **/
	  private static final String SUCCESS_MESS = "L'operazione e' avvenuta correttamente.";


	  @Before
		public void initializationMock() {
			request = mock(HttpServletRequest.class);
			response = mock(HttpServletResponse.class);
			rd = mock(RequestDispatcher.class);
			session = mock(HttpSession.class);
		}

	  @Test
	  public void testOldPassword() throws Exception {
		  when(request.getParameter("action")).thenReturn("password");
		  when(request.getParameter("email")).thenReturn("acquirente@example.it");
		  when(request.getParameter("vecchia")).thenReturn("acquirente");
		  when(request.getParameter("nuova")).thenReturn("Acquirente1");
		  when(request.getParameter("conferma")).thenReturn("Acquirente1");
		  when(request.getRequestDispatcher(("/cambiopassword.jsp"))).thenReturn(rd);
		  when(request.getRequestDispatcher(("/successo.jsp"))).thenReturn(rd);
		  when(request.getSession(true)).thenReturn(session);
		  
		  PrintWriter MyWriter = mock(PrintWriter.class);
		  when(response.getWriter()).thenReturn(MyWriter);
		  servlet.doPost(request, response);	
		  verify(MyWriter).write(BADREQUEST_MESS);
	  }
	  
	  @Test
	  public void testNewPasswordFormat() throws Exception {
		  when(request.getParameter("action")).thenReturn("password");
		  when(request.getParameter("email")).thenReturn("acquirente@example.it");
		  when(request.getParameter("vecchia")).thenReturn("Acquirente");
		  when(request.getParameter("nuova")).thenReturn("Acq");
		  when(request.getParameter("conferma")).thenReturn("Acq");
		  when(request.getRequestDispatcher(("/cambiopassword.jsp"))).thenReturn(rd);
		  when(request.getRequestDispatcher(("/successo.jsp"))).thenReturn(rd);
		  when(request.getSession(true)).thenReturn(session);
		  
		  PrintWriter MyWriter = mock(PrintWriter.class);
		  when(response.getWriter()).thenReturn(MyWriter);
		  servlet.doPost(request, response);	
		  verify(MyWriter).write(BADREQUEST_MESS);
	  }
	  
	  @Test
	  public void testTwoPasswordNotEquals() throws Exception {
		  when(request.getParameter("action")).thenReturn("password");
		  when(request.getParameter("email")).thenReturn("acquirente@example.it");
		  when(request.getParameter("vecchia")).thenReturn("Acquirente");
		  when(request.getParameter("nuova")).thenReturn("Acquirente1");
		  when(request.getParameter("conferma")).thenReturn("Acquirente");
		  when(request.getRequestDispatcher(("/cambiopassword.jsp"))).thenReturn(rd);
		  when(request.getRequestDispatcher(("/successo.jsp"))).thenReturn(rd);
		  when(request.getSession(true)).thenReturn(session);
		  
		  PrintWriter MyWriter = mock(PrintWriter.class);
		  when(response.getWriter()).thenReturn(MyWriter);
		  servlet.doPost(request, response);	
		  verify(MyWriter).write(BADREQUEST_MESS);
	  }

	  @Test
	  public void testEditPassword() throws Exception {
		  when(request.getParameter("action")).thenReturn("password");
		  when(request.getParameter("email")).thenReturn("acquirente@example.it");
		  when(request.getParameter("vecchia")).thenReturn("Acquirente");
		  when(request.getParameter("nuova")).thenReturn("Acquirente1");
		  when(request.getParameter("conferma")).thenReturn("Acquirente1");
		  when(request.getRequestDispatcher(("/cambiopassword.jsp"))).thenReturn(rd);
		  when(request.getRequestDispatcher(("/successo.jsp"))).thenReturn(rd);
		  when(request.getSession(true)).thenReturn(session);
		  
		  PrintWriter MyWriter = mock(PrintWriter.class);
		  when(response.getWriter()).thenReturn(MyWriter);
		  servlet.doPost(request, response);	
		  verify(MyWriter).write(SUCCESS_MESS);
	  }




}
