package control;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

public class LoginTest {

	private Login servlet = new Login();
	private HttpServletRequest request;
	private HttpServletResponse response;
	private RequestDispatcher rd;
	private HttpSession session;
	private Cookie cookie;
	
	/** messaggio di errore inviato in caso di bad request. **/
	  private static final String BADREQUEST_MESS = "L'operazione richiesta non e' valida.";

	  /** messaggio restituito in caso di successo dell'operazione. **/
	  private static final String SUCCESS_MESS = "L'operazione e' avvenuta correttamente.";
	
		@Before
		public void initializationMock() {
			request = mock(HttpServletRequest.class);
			response = mock(HttpServletResponse.class);
			rd = mock(RequestDispatcher.class);
			cookie = mock(Cookie.class);
			session = mock(HttpSession.class);
		}
		
	
		@Test
		public void testEmailFormat() throws Exception {
			when(request.getParameter("email")).thenReturn("acquirente@example");
			when(request.getParameter("password")).thenReturn("Acquirente");
			when(request.getRequestDispatcher(("/login.jsp"))).thenReturn(rd);
			when(request.getRequestDispatcher(("/index.jsp"))).thenReturn(rd);
			when(request.getCookies()).thenReturn(new Cookie[]{cookie});
			when(cookie.getName()).thenReturn("");
			when(request.getSession(true)).thenReturn(session);
			when(request.getSession()).thenReturn(session);
			
			PrintWriter MyWriter = mock(PrintWriter.class);
			when(response.getWriter()).thenReturn(MyWriter);
			servlet.doPost(request, response);	
			verify(MyWriter).write(BADREQUEST_MESS);
		}
		
		@Test
		public void testEmailNotRegistered() throws Exception {
			when(request.getParameter("email")).thenReturn("newacquirente4@example.it");
			when(request.getParameter("password")).thenReturn("Acquirente");
			when(request.getRequestDispatcher(("/login.jsp"))).thenReturn(rd);
			when(request.getRequestDispatcher(("/index.jsp"))).thenReturn(rd);
			when(request.getCookies()).thenReturn(new Cookie[]{cookie});
			when(cookie.getName()).thenReturn("");
			when(request.getSession(true)).thenReturn(session);
			when(request.getSession()).thenReturn(session);
			
			PrintWriter MyWriter = mock(PrintWriter.class);
			when(response.getWriter()).thenReturn(MyWriter);
			servlet.doPost(request, response);	
			verify(MyWriter).write(BADREQUEST_MESS);
		}	
		
		@Test
		public void testEmailOrPasswordWrong() throws Exception{
			when(request.getParameter("email")).thenReturn("acquirente4@example.it");
			when(request.getParameter("password")).thenReturn("Acquirente1");
			when(request.getRequestDispatcher(("/login.jsp"))).thenReturn(rd);
			when(request.getRequestDispatcher(("/index.jsp"))).thenReturn(rd);
			when(request.getCookies()).thenReturn(new Cookie[]{cookie});
			when(cookie.getName()).thenReturn("");
			when(request.getSession(true)).thenReturn(session);
			when(request.getSession()).thenReturn(session);
			
			PrintWriter MyWriter = mock(PrintWriter.class);
			when(response.getWriter()).thenReturn(MyWriter);
			servlet.doPost(request, response);	
			verify(MyWriter).write(BADREQUEST_MESS);
		}
		
		@Test
		public void testLogin() throws Exception{
			when(request.getParameter("email")).thenReturn("acquirente@example.it");
			when(request.getParameter("password")).thenReturn("Acquirente");
			when(request.getRequestDispatcher(("/login.jsp"))).thenReturn(rd);
			when(request.getRequestDispatcher(("/index.jsp"))).thenReturn(rd);
			when(request.getCookies()).thenReturn(new Cookie[]{cookie});
			when(cookie.getName()).thenReturn("");
			when(request.getSession(true)).thenReturn(session);
			when(request.getSession()).thenReturn(session);
			
			PrintWriter MyWriter = mock(PrintWriter.class);
			when(response.getWriter()).thenReturn(MyWriter);
			servlet.doPost(request, response);	
			verify(MyWriter).write(SUCCESS_MESS);
		}
	
}
