package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.ProductBean;
import model.ProductModelDM;


@WebServlet("/SearchControl")
public class SearchControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductModelDM model= new ProductModelDM();
    private static final String SUCCESS_MESS = "L'operazione e' avvenuta correttamente.";
	private static final String BADREQUEST_MESS = "L'operazione richiesta non e' valida.";
	
    public SearchControl() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String toSearch= (String) request.getParameter("search");
		
		
		
		String[] strings= toSearch.split(" ");
		ArrayList<String> words= new ArrayList<String>();
		
		Collections.addAll(words, strings);
		
		Collection<ProductBean> collection;
		ArrayList<ProductBean> products= new ArrayList<ProductBean>();
		
		try {
			collection = model.doRetrieveAll(null);
		
			Iterator<?> it  = collection.iterator();
			while(it.hasNext()) {
				ProductBean bean = ((ProductBean)it.next());
				for(String word : words) {
					if(!bean.getName().contains(word) || bean.getVisible() == 0) {
						it.remove();
						break;
					}
				}
			}
		
			Iterator<?> it2  = collection.iterator();
			while(it2.hasNext()) {
				ProductBean bean = (ProductBean)it2.next();
				products.add(bean);
			}
		
			System.out.println("Trovati " + products.size() + " elementi");
			if(products.size()==0) {
				response.getWriter().write(BADREQUEST_MESS);
				request.setAttribute("result", products);
				request.setAttribute("search", toSearch);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ricerca.jsp");
				dispatcher.forward(request, response);
			}
			request.setAttribute("result", products);
			request.setAttribute("search", toSearch);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ricerca.jsp");
			response.getWriter().write(SUCCESS_MESS);
			dispatcher.forward(request, response);
		
		} catch (SQLException e) {System.out.println("Errore durante la ricerca");}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}
