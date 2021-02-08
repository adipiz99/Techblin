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


public class ProductControlTest<T> {

	private ProductControl servlet = new ProductControl();
	private HttpServletRequest request;
	private HttpServletResponse response;
	private RequestDispatcher rd;
	private HttpSession session;
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
	public void testNomePr() throws Exception {
		when(request.getSession()).thenReturn(session);
 		when(request.getParameter("action")).thenReturn("insert");
		when(request.getParameter("name")).thenReturn("Sch");
		when(request.getParameter("description")).thenReturn("RTX 3080 GPU Nvidia");
		when(request.getParameter("price")).thenReturn("998");
		when(request.getParameter("quantity")).thenReturn("12");
		when(request.getParameter("seller")).thenReturn("venditore@example.it");
		when(request.getRequestDispatcher(("/nuovoprodotto.jsp"))).thenReturn(rd);
		when(request.getRequestDispatcher(("/successo.jsp"))).thenReturn(rd);
		when(request.getRequestDispatcher(("/prodotto.jsp"))).thenReturn(rd);
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(BADREQUEST_MESS);
	  }
	
	@Test
	public void testDecrizionePr() throws Exception {
		when(request.getSession()).thenReturn(session);
 		when(request.getParameter("action")).thenReturn("insert");
		when(request.getParameter("name")).thenReturn("Scheda video Nvidia");
		when(request.getParameter("description")).thenReturn("RTX");
		when(request.getParameter("price")).thenReturn("998");
		when(request.getParameter("quantity")).thenReturn("12");
		when(request.getParameter("seller")).thenReturn("venditore@example.it");
		when(request.getRequestDispatcher(("/nuovoprodotto.jsp"))).thenReturn(rd);
		when(request.getRequestDispatcher(("/successo.jsp"))).thenReturn(rd);
		when(request.getRequestDispatcher(("/prodotto.jsp"))).thenReturn(rd);
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(BADREQUEST_MESS);
	  }
	
	@Test
	public void testPrezzoPr() throws Exception {
		when(request.getSession()).thenReturn(session);
 		when(request.getParameter("action")).thenReturn("insert");
		when(request.getParameter("name")).thenReturn("Scheda video Nvidia");
		when(request.getParameter("description")).thenReturn("RTX 3080 GPU Nvidia");
		when(request.getParameter("price")).thenReturn("9980000");
		when(request.getParameter("quantity")).thenReturn("12");
		when(request.getParameter("seller")).thenReturn("venditore@example.it");
		when(request.getRequestDispatcher(("/nuovoprodotto.jsp"))).thenReturn(rd);
		when(request.getRequestDispatcher(("/successo.jsp"))).thenReturn(rd);
		when(request.getRequestDispatcher(("/prodotto.jsp"))).thenReturn(rd);
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(BADREQUEST_MESS);
	  }
	
	@Test
	public void testQuantit‡Pr() throws Exception {
		when(request.getSession()).thenReturn(session);
 		when(request.getParameter("action")).thenReturn("insert");
		when(request.getParameter("name")).thenReturn("Scheda video Nvidia");
		when(request.getParameter("description")).thenReturn("RTX 3080 GPU Nvidia");
		when(request.getParameter("price")).thenReturn("998");
		when(request.getParameter("quantity")).thenReturn("120000");
		when(request.getParameter("seller")).thenReturn("venditore@example.it");
		when(request.getRequestDispatcher(("/nuovoprodotto.jsp"))).thenReturn(rd);
		when(request.getRequestDispatcher(("/successo.jsp"))).thenReturn(rd);
		when(request.getRequestDispatcher(("/prodotto.jsp"))).thenReturn(rd);
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(BADREQUEST_MESS);
	  }
	
	@Test
	public void testInserimentoPr() throws Exception {
		when(request.getSession()).thenReturn(session);
 		when(request.getParameter("action")).thenReturn("insert");
		when(request.getParameter("name")).thenReturn("Scheda video Nvidia");
		when(request.getParameter("description")).thenReturn("RTX 3080 GPU Nvidia");
		when(request.getParameter("price")).thenReturn("998");
		when(request.getParameter("quantity")).thenReturn("12");
		when(request.getParameter("seller")).thenReturn("venditore@example.it");
		when(request.getRequestDispatcher(("/nuovoprodotto.jsp"))).thenReturn(rd);
		when(request.getRequestDispatcher(("/successo.jsp"))).thenReturn(rd);
		when(request.getRequestDispatcher(("/prodotto.jsp"))).thenReturn(rd);
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(SUCCESS_MESS);
	  }
	
	@Test
	public void testUpdateNome() throws Exception {
		when(request.getSession()).thenReturn(session);
 		when(request.getParameter("action")).thenReturn("update");
 		when(request.getParameter("id")).thenReturn("13");
		when(request.getParameter("name")).thenReturn("Proc");
		when(request.getParameter("description")).thenReturn("Intel i7 10700K");
		when(request.getParameter("price")).thenReturn("850");
		when(request.getParameter("quantity")).thenReturn("1");
		when(request.getRequestDispatcher(("/nuovoprodotto.jsp"))).thenReturn(rd);
		when(request.getRequestDispatcher(("/successo.jsp"))).thenReturn(rd);
		when(request.getRequestDispatcher(("/prodotto.jsp"))).thenReturn(rd);
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(BADREQUEST_MESS);
	  }
	
	@Test
	public void testUpdateDescrizionePr() throws Exception {
		when(request.getSession()).thenReturn(session);
 		when(request.getParameter("action")).thenReturn("update");
 		when(request.getParameter("id")).thenReturn("13");
		when(request.getParameter("name")).thenReturn("Processore Intel i7");
		when(request.getParameter("description")).thenReturn("Inteli7");
		when(request.getParameter("price")).thenReturn("850");
		when(request.getParameter("quantity")).thenReturn("1");
		when(request.getRequestDispatcher(("/nuovoprodotto.jsp"))).thenReturn(rd);
		when(request.getRequestDispatcher(("/successo.jsp"))).thenReturn(rd);
		when(request.getRequestDispatcher(("/prodotto.jsp"))).thenReturn(rd);
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(BADREQUEST_MESS);
	  }
	
	@Test
	public void testUpdatePrezzo() throws Exception {
		when(request.getSession()).thenReturn(session);
 		when(request.getParameter("action")).thenReturn("update");
 		when(request.getParameter("id")).thenReturn("13");
		when(request.getParameter("name")).thenReturn("Processore Intel i7");
		when(request.getParameter("description")).thenReturn("Intel i7 10900K");
		when(request.getParameter("price")).thenReturn("850000!");
		when(request.getParameter("quantity")).thenReturn("1");
		when(request.getRequestDispatcher(("/nuovoprodotto.jsp"))).thenReturn(rd);
		when(request.getRequestDispatcher(("/successo.jsp"))).thenReturn(rd);
		when(request.getRequestDispatcher(("/prodotto.jsp"))).thenReturn(rd);
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(BADREQUEST_MESS);
	  }
	
	@Test
	public void testUpdateQuantityPr() throws Exception {
		when(request.getSession()).thenReturn(session);
 		when(request.getParameter("action")).thenReturn("update");
 		when(request.getParameter("id")).thenReturn("13");
		when(request.getParameter("name")).thenReturn("Processore Intel i7");
		when(request.getParameter("description")).thenReturn("Intel i7 10900K");
		when(request.getParameter("price")).thenReturn("850");
		when(request.getParameter("quantity")).thenReturn("100000");
		when(request.getRequestDispatcher(("/nuovoprodotto.jsp"))).thenReturn(rd);
		when(request.getRequestDispatcher(("/successo.jsp"))).thenReturn(rd);
		when(request.getRequestDispatcher(("/prodotto.jsp"))).thenReturn(rd);
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(BADREQUEST_MESS);
	  }
	
	@Test
	public void testUpdatePr() throws Exception {
		when(request.getSession()).thenReturn(session);
 		when(request.getParameter("action")).thenReturn("update");
 		when(request.getParameter("id")).thenReturn("13");
		when(request.getParameter("name")).thenReturn("Processore Intel i7");
		when(request.getParameter("description")).thenReturn("Intel i7 10900K");
		when(request.getParameter("price")).thenReturn("850");
		when(request.getParameter("quantity")).thenReturn("1");
		when(request.getRequestDispatcher(("/nuovoprodotto.jsp"))).thenReturn(rd);
		when(request.getRequestDispatcher(("/successo.jsp"))).thenReturn(rd);
		when(request.getRequestDispatcher(("/prodotto.jsp"))).thenReturn(rd);
		
		PrintWriter MyWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(MyWriter);
		servlet.doPost(request, response);	
		verify(MyWriter).write(SUCCESS_MESS);
	  }
	
}
