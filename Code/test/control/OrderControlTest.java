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
public class OrderControlTest {

	private OrderControl servlet = new OrderControl();
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
	public void testNomeOrdine() throws Exception {
		session.setAttribute("carrello", cart);
		x.setCode(2);
		x.setPrice(550);
		cart.addItem(x);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("carrello")).thenReturn(cart);
		when(request.getParameter("action")).thenReturn("add");
		when(request.getParameter("nome")).thenReturn("Mari0");
		when(request.getParameter("cognome")).thenReturn("Rossi");
		when(request.getParameter("email")).thenReturn("acquirente@example.it");
		when(request.getParameter("indirizzo")).thenReturn("ViaRoma");
		when(request.getParameter("provincia")).thenReturn("Chieti");
		when(request.getParameter("citta")).thenReturn("Chieti");
		when(request.getParameter("cap")).thenReturn("66100");
		when(request.getParameter("paymentMethod")).thenReturn("carta");
		when(request.getParameter("titolare")).thenReturn("Mario Rossi");
		when(request.getParameter("carta")).thenReturn("4694412611557323");
		when(request.getParameter("scadenza")).thenReturn("12/2022");
		when(request.getParameter("cvv")).thenReturn("221");
		when(request.getParameter("conto")).thenReturn(null);
		when(request.getParameter("paypal")).thenReturn(null);
		when(request.getParameter("user")).thenReturn("acquirente@example.it");
		when(request.getRequestDispatcher(("/checkout.jsp"))).thenReturn(rd);
		when(request.getRequestDispatcher(("/confermaordine.jsp"))).thenReturn(rd);
		
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(BADREQUEST_MESS);
	}
	
	@Test
	public void testCognomeOrdine() throws Exception {
		session.setAttribute("carrello", cart);
		x.setCode(2);
		x.setPrice(550);
		cart.addItem(x);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("carrello")).thenReturn(cart);
		when(request.getParameter("action")).thenReturn("add");
		when(request.getParameter("nome")).thenReturn("Mario");
		when(request.getParameter("cognome")).thenReturn("Ros1");
		when(request.getParameter("email")).thenReturn("acquirente@example.it");
		when(request.getParameter("indirizzo")).thenReturn("ViaRoma");
		when(request.getParameter("provincia")).thenReturn("Chieti");
		when(request.getParameter("citta")).thenReturn("Chieti");
		when(request.getParameter("cap")).thenReturn("66100");
		when(request.getParameter("paymentMethod")).thenReturn("carta");
		when(request.getParameter("titolare")).thenReturn("Mario Rossi");
		when(request.getParameter("carta")).thenReturn("4694412611557323");
		when(request.getParameter("scadenza")).thenReturn("12/2022");
		when(request.getParameter("cvv")).thenReturn("221");
		when(request.getParameter("conto")).thenReturn(null);
		when(request.getParameter("paypal")).thenReturn(null);
		when(request.getParameter("user")).thenReturn("acquirente@example.it");
		when(request.getRequestDispatcher(("/checkout.jsp"))).thenReturn(rd);
		when(request.getRequestDispatcher(("/confermaordine.jsp"))).thenReturn(rd);
		
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(BADREQUEST_MESS);
	}
	
	@Test
	public void testEmailOrdine() throws Exception {
		session.setAttribute("carrello", cart);
		x.setCode(2);
		x.setPrice(550);
		cart.addItem(x);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("carrello")).thenReturn(cart);
		when(request.getParameter("action")).thenReturn("add");
		when(request.getParameter("nome")).thenReturn("Mario");
		when(request.getParameter("cognome")).thenReturn("Rossi");
		when(request.getParameter("email")).thenReturn("acquirente@");
		when(request.getParameter("indirizzo")).thenReturn("ViaRoma");
		when(request.getParameter("provincia")).thenReturn("Chieti");
		when(request.getParameter("citta")).thenReturn("Chieti");
		when(request.getParameter("cap")).thenReturn("66100");
		when(request.getParameter("paymentMethod")).thenReturn("carta");
		when(request.getParameter("titolare")).thenReturn("Mario Rossi");
		when(request.getParameter("carta")).thenReturn("4694412611557323");
		when(request.getParameter("scadenza")).thenReturn("12/2022");
		when(request.getParameter("cvv")).thenReturn("221");
		when(request.getParameter("conto")).thenReturn(null);
		when(request.getParameter("paypal")).thenReturn(null);
		when(request.getParameter("user")).thenReturn("acquirente@example.it");
		when(request.getRequestDispatcher(("/checkout.jsp"))).thenReturn(rd);
		when(request.getRequestDispatcher(("/confermaordine.jsp"))).thenReturn(rd);
		
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(BADREQUEST_MESS);
	}
	
	@Test
	public void testIndirizzoOrdine() throws Exception {
		session.setAttribute("carrello", cart);
		x.setCode(2);
		x.setPrice(550);
		cart.addItem(x);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("carrello")).thenReturn(cart);
		when(request.getParameter("action")).thenReturn("add");
		when(request.getParameter("nome")).thenReturn("Mario");
		when(request.getParameter("cognome")).thenReturn("Rossi");
		when(request.getParameter("email")).thenReturn("acquirente@example.it");
		when(request.getParameter("indirizzo")).thenReturn("Via Rom!");
		when(request.getParameter("provincia")).thenReturn("Chieti");
		when(request.getParameter("citta")).thenReturn("Chieti");
		when(request.getParameter("cap")).thenReturn("66100");
		when(request.getParameter("paymentMethod")).thenReturn("carta");
		when(request.getParameter("titolare")).thenReturn("Mario Rossi");
		when(request.getParameter("carta")).thenReturn("4694412611557323");
		when(request.getParameter("scadenza")).thenReturn("12/2022");
		when(request.getParameter("cvv")).thenReturn("221");
		when(request.getParameter("conto")).thenReturn(null);
		when(request.getParameter("paypal")).thenReturn(null);
		when(request.getParameter("user")).thenReturn("acquirente@example.it");
		when(request.getRequestDispatcher(("/checkout.jsp"))).thenReturn(rd);
		when(request.getRequestDispatcher(("/confermaordine.jsp"))).thenReturn(rd);
		
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(BADREQUEST_MESS);
	}
	
	@Test
	public void testProvinciaOrdine() throws Exception {
		session.setAttribute("carrello", cart);
		x.setCode(2);
		x.setPrice(550);
		cart.addItem(x);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("carrello")).thenReturn(cart);
		when(request.getParameter("action")).thenReturn("add");
		when(request.getParameter("nome")).thenReturn("Mario");
		when(request.getParameter("cognome")).thenReturn("Rossi");
		when(request.getParameter("email")).thenReturn("acquirente@example.it");
		when(request.getParameter("indirizzo")).thenReturn("Via Roma");
		when(request.getParameter("provincia")).thenReturn("Chiet!");
		when(request.getParameter("citta")).thenReturn("Chieti");
		when(request.getParameter("cap")).thenReturn("66100");
		when(request.getParameter("paymentMethod")).thenReturn("carta");
		when(request.getParameter("titolare")).thenReturn("Mario Rossi");
		when(request.getParameter("carta")).thenReturn("4694412611557323");
		when(request.getParameter("scadenza")).thenReturn("12/2022");
		when(request.getParameter("cvv")).thenReturn("221");
		when(request.getParameter("conto")).thenReturn(null);
		when(request.getParameter("paypal")).thenReturn(null);
		when(request.getParameter("user")).thenReturn("acquirente@example.it");
		when(request.getRequestDispatcher(("/checkout.jsp"))).thenReturn(rd);
		when(request.getRequestDispatcher(("/confermaordine.jsp"))).thenReturn(rd);
		
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(BADREQUEST_MESS);
	}
	
	@Test
	public void testCitt‡Ordine() throws Exception {
		session.setAttribute("carrello", cart);
		x.setCode(2);
		x.setPrice(550);
		cart.addItem(x);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("carrello")).thenReturn(cart);
		when(request.getParameter("action")).thenReturn("add");
		when(request.getParameter("nome")).thenReturn("Mario");
		when(request.getParameter("cognome")).thenReturn("Rossi");
		when(request.getParameter("email")).thenReturn("acquirente@example.it");
		when(request.getParameter("indirizzo")).thenReturn("Via Roma");
		when(request.getParameter("provincia")).thenReturn("Chieti");
		when(request.getParameter("citta")).thenReturn("Chiet2");
		when(request.getParameter("cap")).thenReturn("66100");
		when(request.getParameter("paymentMethod")).thenReturn("carta");
		when(request.getParameter("titolare")).thenReturn("Mario Rossi");
		when(request.getParameter("carta")).thenReturn("4694412611557323");
		when(request.getParameter("scadenza")).thenReturn("12/2022");
		when(request.getParameter("cvv")).thenReturn("221");
		when(request.getParameter("conto")).thenReturn(null);
		when(request.getParameter("paypal")).thenReturn(null);
		when(request.getParameter("user")).thenReturn("acquirente@example.it");
		when(request.getRequestDispatcher(("/checkout.jsp"))).thenReturn(rd);
		when(request.getRequestDispatcher(("/confermaordine.jsp"))).thenReturn(rd);
		
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(BADREQUEST_MESS);
	}
	
	@Test
	public void testCapOrdine() throws Exception {
		session.setAttribute("carrello", cart);
		x.setCode(2);
		x.setPrice(550);
		cart.addItem(x);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("carrello")).thenReturn(cart);
		when(request.getParameter("action")).thenReturn("add");
		when(request.getParameter("nome")).thenReturn("Mario");
		when(request.getParameter("cognome")).thenReturn("Rossi");
		when(request.getParameter("email")).thenReturn("acquirente@example.it");
		when(request.getParameter("indirizzo")).thenReturn("Via Roma");
		when(request.getParameter("provincia")).thenReturn("Chieti");
		when(request.getParameter("citta")).thenReturn("Chieti");
		when(request.getParameter("cap")).thenReturn("66y");
		when(request.getParameter("paymentMethod")).thenReturn("carta");
		when(request.getParameter("titolare")).thenReturn("Mario Rossi");
		when(request.getParameter("carta")).thenReturn("4694412611557323");
		when(request.getParameter("scadenza")).thenReturn("12/2022");
		when(request.getParameter("cvv")).thenReturn("221");
		when(request.getParameter("conto")).thenReturn(null);
		when(request.getParameter("paypal")).thenReturn(null);
		when(request.getParameter("user")).thenReturn("acquirente@example.it");
		when(request.getRequestDispatcher(("/checkout.jsp"))).thenReturn(rd);
		when(request.getRequestDispatcher(("/confermaordine.jsp"))).thenReturn(rd);
		
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(BADREQUEST_MESS);
	}
	
	@Test
	public void testTitolareOrdine() throws Exception {
		session.setAttribute("carrello", cart);
		x.setCode(2);
		x.setPrice(550);
		cart.addItem(x);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("carrello")).thenReturn(cart);
		when(request.getParameter("action")).thenReturn("add");
		when(request.getParameter("nome")).thenReturn("Mario");
		when(request.getParameter("cognome")).thenReturn("Rossi");
		when(request.getParameter("email")).thenReturn("acquirente@example.it");
		when(request.getParameter("indirizzo")).thenReturn("Via Roma");
		when(request.getParameter("provincia")).thenReturn("Chieti");
		when(request.getParameter("citta")).thenReturn("Chieti");
		when(request.getParameter("cap")).thenReturn("66100");
		when(request.getParameter("paymentMethod")).thenReturn("carta");
		when(request.getParameter("titolare")).thenReturn("");
		when(request.getParameter("carta")).thenReturn("4694412611557323");
		when(request.getParameter("scadenza")).thenReturn("12/2022");
		when(request.getParameter("cvv")).thenReturn("221");
		when(request.getParameter("conto")).thenReturn(null);
		when(request.getParameter("paypal")).thenReturn(null);
		when(request.getParameter("user")).thenReturn("acquirente@example.it");
		when(request.getRequestDispatcher(("/checkout.jsp"))).thenReturn(rd);
		when(request.getRequestDispatcher(("/confermaordine.jsp"))).thenReturn(rd);
		
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(BADREQUEST_MESS);
	}
	
	@Test
	public void testNumCartaOrdine() throws Exception {
		session.setAttribute("carrello", cart);
		x.setCode(2);
		x.setPrice(550);
		cart.addItem(x);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("carrello")).thenReturn(cart);
		when(request.getParameter("action")).thenReturn("add");
		when(request.getParameter("nome")).thenReturn("Mario");
		when(request.getParameter("cognome")).thenReturn("Rossi");
		when(request.getParameter("email")).thenReturn("acquirente@example.it");
		when(request.getParameter("indirizzo")).thenReturn("Via Roma");
		when(request.getParameter("provincia")).thenReturn("Chieti");
		when(request.getParameter("citta")).thenReturn("Chieti");
		when(request.getParameter("cap")).thenReturn("66100");
		when(request.getParameter("paymentMethod")).thenReturn("carta");
		when(request.getParameter("titolare")).thenReturn("Mario Rossi");
		when(request.getParameter("carta")).thenReturn("");
		when(request.getParameter("scadenza")).thenReturn("12/2022");
		when(request.getParameter("cvv")).thenReturn("221");
		when(request.getParameter("conto")).thenReturn(null);
		when(request.getParameter("paypal")).thenReturn(null);
		when(request.getParameter("user")).thenReturn("acquirente@example.it");
		when(request.getRequestDispatcher(("/checkout.jsp"))).thenReturn(rd);
		when(request.getRequestDispatcher(("/confermaordine.jsp"))).thenReturn(rd);
		
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(BADREQUEST_MESS);
	}
	
	@Test
	public void testScadenzaCartaOrdine() throws Exception {
		session.setAttribute("carrello", cart);
		x.setCode(2);
		x.setPrice(550);
		cart.addItem(x);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("carrello")).thenReturn(cart);
		when(request.getParameter("action")).thenReturn("add");
		when(request.getParameter("nome")).thenReturn("Mario");
		when(request.getParameter("cognome")).thenReturn("Rossi");
		when(request.getParameter("email")).thenReturn("acquirente@example.it");
		when(request.getParameter("indirizzo")).thenReturn("Via Roma");
		when(request.getParameter("provincia")).thenReturn("Chieti");
		when(request.getParameter("citta")).thenReturn("Chieti");
		when(request.getParameter("cap")).thenReturn("66100");
		when(request.getParameter("paymentMethod")).thenReturn("carta");
		when(request.getParameter("titolare")).thenReturn("Mario Rossi");
		when(request.getParameter("carta")).thenReturn("4694412611557323");
		when(request.getParameter("scadenza")).thenReturn(" ");
		when(request.getParameter("cvv")).thenReturn("221");
		when(request.getParameter("conto")).thenReturn(null);
		when(request.getParameter("paypal")).thenReturn(null);
		when(request.getParameter("user")).thenReturn("acquirente@example.it");
		when(request.getRequestDispatcher(("/checkout.jsp"))).thenReturn(rd);
		when(request.getRequestDispatcher(("/confermaordine.jsp"))).thenReturn(rd);
		
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(BADREQUEST_MESS);
	}
	
	@Test
	public void testCVVCartaOrdine() throws Exception {
		session.setAttribute("carrello", cart);
		x.setCode(2);
		x.setPrice(550);
		cart.addItem(x);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("carrello")).thenReturn(cart);
		when(request.getParameter("action")).thenReturn("add");
		when(request.getParameter("nome")).thenReturn("Mario");
		when(request.getParameter("cognome")).thenReturn("Rossi");
		when(request.getParameter("email")).thenReturn("acquirente@example.it");
		when(request.getParameter("indirizzo")).thenReturn("Via Roma");
		when(request.getParameter("provincia")).thenReturn("Chieti");
		when(request.getParameter("citta")).thenReturn("Chieti");
		when(request.getParameter("cap")).thenReturn("66100");
		when(request.getParameter("paymentMethod")).thenReturn("carta");
		when(request.getParameter("titolare")).thenReturn("Mario Rossi");
		when(request.getParameter("carta")).thenReturn("4694412611557323");
		when(request.getParameter("scadenza")).thenReturn("12/2022");
		when(request.getParameter("cvv")).thenReturn(" ");
		when(request.getParameter("conto")).thenReturn(null);
		when(request.getParameter("paypal")).thenReturn(null);
		when(request.getParameter("user")).thenReturn("acquirente@example.it");
		when(request.getRequestDispatcher(("/checkout.jsp"))).thenReturn(rd);
		when(request.getRequestDispatcher(("/confermaordine.jsp"))).thenReturn(rd);
		
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(BADREQUEST_MESS);
	}
	
	@Test
	public void testOrdine() throws Exception {
		session.setAttribute("carrello", cart);
		x.setCode(2);
		x.setPrice(550);
		cart.addItem(x);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("carrello")).thenReturn(cart);
		when(request.getParameter("action")).thenReturn("add");
		when(request.getParameter("nome")).thenReturn("Mario");
		when(request.getParameter("cognome")).thenReturn("Rossi");
		when(request.getParameter("email")).thenReturn("acquirente@example.it");
		when(request.getParameter("indirizzo")).thenReturn("ViaRoma");
		when(request.getParameter("provincia")).thenReturn("Chieti");
		when(request.getParameter("citta")).thenReturn("Chieti");
		when(request.getParameter("cap")).thenReturn("66100");
		when(request.getParameter("paymentMethod")).thenReturn("carta");
		when(request.getParameter("titolare")).thenReturn("Mario Rossi");
		when(request.getParameter("carta")).thenReturn("4694412611557323");
		when(request.getParameter("scadenza")).thenReturn("12/2022");
		when(request.getParameter("cvv")).thenReturn("221");
		when(request.getParameter("conto")).thenReturn(null);
		when(request.getParameter("paypal")).thenReturn(null);
		when(request.getParameter("user")).thenReturn("acquirente@example.it");
		when(request.getRequestDispatcher(("/checkout.jsp"))).thenReturn(rd);
		when(request.getRequestDispatcher(("/confermaordine.jsp"))).thenReturn(rd);
		
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(SUCCESS_MESS);
	} 
	
}
