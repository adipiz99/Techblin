package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ConcurrentModificationException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Cart;
import entity.ProductBean;
import model.ProductModelDM;


@WebServlet("/CartControl")
public class CartControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static ProductModelDM model = new ProductModelDM();
	private static final String BADREQUEST_MESS = "L'operazione richiesta non e' valida.";
	private static final String SUCCESS_MESS = "L'operazione e' avvenuta correttamente.";
    public CartControl() {
        super();
    }

    // action - Parametro utilizzato per definire il tipo di interazione con l'oggetto
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		Cart<ProductBean> cart = (Cart<ProductBean>)request.getSession().getAttribute("carrello");
		if(cart == null) {
			cart= new Cart<ProductBean>();
		}
		
		String action = (String) request.getParameter("action");
		System.out.println(action);
		
		try {
			if(action != null) {
				if(action.equals("addCart")) {
					String id = request.getParameter("id");
					ProductBean bean = model.doRetrieveByKey(id);
					if(bean != null && !bean.isEmpty()) {
						cart.addItem(bean);
						request.setAttribute("message", "Prodotto "+ bean.getName()+" aggiunto al carrello");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/ProductControl?action=details&id=" + bean.getCode());
						response.getWriter().write(SUCCESS_MESS);
						dispatcher.forward(request, response);
					} else {
						RequestDispatcher dispatcher = request.getRequestDispatcher("/ProductError.jsp");
						response.getWriter().write(BADREQUEST_MESS);
						dispatcher.forward(request, response);
					}
					
				} else if(action.equals("clearCart")) {
					cart.deleteItems();
					request.setAttribute("message", "Carrello svuotato");
					RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/index.jsp");
					dispatcher.forward(request, response);
					
				} else if(action.equals("deleteCart")) {
					String id = request.getParameter("id");
					ProductBean bean = model.doRetrieveByKey(id);
					if(bean != null && !bean.isEmpty()) {
						if(cart.getItems().contains(bean)) {
							cart.deleteItem(bean);
							request.setAttribute("message", "Prodotto "+ bean.getName()+" rimosso dal carrello");
							RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/cart.jsp");
							dispatcher.forward(request, response);
							return;
						}
						else {
							request.setAttribute("message", "Il prodotto non è presente nel carrello");
							RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/cart.jsp");
							dispatcher.forward(request, response);
							return;
						}
					}
				}
			}
		}
		catch (ConcurrentModificationException | SQLException e) {}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}	
}

