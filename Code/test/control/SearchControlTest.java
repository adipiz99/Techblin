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

public class SearchControlTest {

	private SearchControl servlet = new SearchControl();
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
	  public void testProductNotInDB() throws Exception {
		  when(request.getParameter("search")).thenReturn("Ciao");
		  when(request.getRequestDispatcher(("/ricerca.jsp"))).thenReturn(rd);
		  
		  PrintWriter MyWriter = mock(PrintWriter.class);
		  when(response.getWriter()).thenReturn(MyWriter);
		  servlet.doPost(request, response);	
		  verify(MyWriter).write(BADREQUEST_MESS);
	  }
	  
	  @Test
	  public void testProductInDB() throws Exception {
		  when(request.getParameter("search")).thenReturn("MSI");
		  when(request.getRequestDispatcher(("/ricerca.jsp"))).thenReturn(rd);
		  
		  PrintWriter MyWriter = mock(PrintWriter.class);
		  when(response.getWriter()).thenReturn(MyWriter);
		  servlet.doPost(request, response);	
		  verify(MyWriter).write(SUCCESS_MESS);
	  }
	  
	  
	  
}