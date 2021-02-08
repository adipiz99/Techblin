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

import entity.Cart;
import entity.ProductBean;

public class CartControlTest {

	private CartControl servlet = new CartControl();
	private HttpServletRequest request;
	private HttpServletResponse response;
	private RequestDispatcher rd;
	private HttpSession session;
	Cart<ProductBean>cart = new Cart<ProductBean>();
	ProductBean x = new ProductBean();
	private static final String BADREQUEST_MESS = "L'operazione richiesta non e' valida.";
	private static final String SUCCESS_MESS = "L'operazione e' avvenuta correttamente.";
	
	
	@Before
	public void initializationMock() {
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		rd = mock(RequestDispatcher.class);
		session = mock(HttpSession.class);
	}
	
	@Test
	public void testProdottoNotFoundCarrello() throws Exception {
		when(request.getSession()).thenReturn(session);
		when(request.getParameter("action")).thenReturn("addCart");
		when(request.getParameter("id")).thenReturn("500");
		when(request.getRequestDispatcher(("/ProductControl?action=details&id=500"))).thenReturn(rd);
		when(request.getRequestDispatcher(("/ProductError.jsp"))).thenReturn(rd);
		
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doGet(request, response);	
		verify(MyWriter).write(BADREQUEST_MESS);
	}
	
	@Test
	public void testProdottoCarrello() throws Exception {
		when(request.getSession()).thenReturn(session);
		when(request.getParameter("action")).thenReturn("addCart");
		when(request.getParameter("id")).thenReturn("5");
		when(request.getRequestDispatcher(("/ProductControl?action=details&id=5"))).thenReturn(rd);
		when(request.getRequestDispatcher(("/ProductError.jsp"))).thenReturn(rd);
		
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doGet(request, response);	
		verify(MyWriter).write(SUCCESS_MESS);
	}
	
}
