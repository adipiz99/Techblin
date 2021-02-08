package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Cart;
import entity.ProductBean;
import entity.SellerProductBean;
import model.ProductModelDM;
import model.SellerProductModelDM;


@WebServlet("/ProductControl")
public class ProductControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS_MESS = "L'operazione e' avvenuta correttamente.";
	private static final String BADREQUEST_MESS = "L'operazione richiesta non e' valida.";
       
	static ProductModelDM model = new ProductModelDM();
	static SellerProductModelDM SPmodel = new SellerProductModelDM();
	
	
    public ProductControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		@SuppressWarnings("unchecked")
		Cart<ProductBean> cart = (Cart<ProductBean>)request.getSession().getAttribute("carrello");
		if(cart == null) {
			cart = new Cart<ProductBean>();
			request.getSession().setAttribute("carrello", cart);
		}
		
		String action = request.getParameter("action");
		
		try {
			if(action != null) {
				if(action.equals("details")) {
					String id = request.getParameter("id");
					request.removeAttribute("product");
					ProductBean product= model.doRetrieveByKey(id);
					
					if(product.getVisible() == 0) {
						response.sendRedirect("./ProductError.jsp");
						return;
					}
					
					request.setAttribute("product", product);
					
				} 
				if(action.equals("retrieve")) {
					String id = request.getParameter("id");
					request.removeAttribute("product");
					ProductBean product= model.doRetrieveByKey(id);
					
					if(product.getVisible() == 0) {
						response.sendRedirect("./ProductError.jsp");
						return;
					}
					
					request.setAttribute("product", product);
					
					RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/gestioneprodotto.jsp");
					dispatcher.forward(request, response);
					return;
					
				}	else if(action.equals("insert")) {
					String name = request.getParameter("name");
					String description = request.getParameter("description");
					int price = Integer.parseInt(request.getParameter("price"));
					int quantity = Integer.parseInt(request.getParameter("quantity"));
					String seller= request.getParameter("seller");
					
					//preliminaryControl(request, response, name, description, "" + price, "" + quantity);
					if(!isValidName(name)) {
						request.setAttribute("message", "Errore. Il nome non rispetta il formato richiesto.");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/nuovoprodotto.jsp");
						response.getWriter().write(BADREQUEST_MESS);
						dispatcher.forward(request, response);
						return;
					}
					
					if(!isValidDescription(description)) {
						request.setAttribute("message", "Errore. La descrizione non rispetta il formato richiesto.");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/nuovoprodotto.jsp");
						response.getWriter().write(BADREQUEST_MESS);
						dispatcher.forward(request, response);
						return;
					}
					if(!isValidPrice(""+price)) {
						request.setAttribute("message", "Errore. Il prezzo non rispetta il formato richiesto.");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/nuovoprodotto.jsp");
						response.getWriter().write(BADREQUEST_MESS);
						dispatcher.forward(request, response);
						return;
					}
					if(!isValidQty(""+quantity)) {
						request.setAttribute("message", "Errore. La quantità non rispetta il formato richiesto.");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/nuovoprodotto.jsp");
						response.getWriter().write(BADREQUEST_MESS);
						dispatcher.forward(request, response);
						return;
					}
					
					ProductBean bean = new ProductBean();
					bean.setName(name);
					bean.setDescription(description);
					bean.setPrice(price);
					bean.setQuantity(quantity);
					bean.setVisible();
					
					model.doSave(bean);
					
					Collection<ProductBean> products= model.doRetrieveAll(null);
					
					int code= -1;
					
					Iterator<?> it  = products.iterator();
					while(it.hasNext()) {
						ProductBean p = ((ProductBean)it.next());
						if(p.getCode() > code)
							code= p.getCode();
					}
					
					SellerProductBean sp= new SellerProductBean();
					sp.setVenditore(seller);
					sp.setProdotto(code);
					
					SPmodel.doSave(sp);
					
					request.setAttribute("message", "Prodotto "+ bean.getName()+" aggiunto");
					
					response.sendRedirect("./successo.jsp");
					response.getWriter().write(SUCCESS_MESS);
					return;
				} else if(action.equalsIgnoreCase("delete")) {
					
					String id = request.getParameter("id");
					ProductBean bean = model.doRetrieveByKey(id);
					if(bean != null && !bean.isEmpty()) {
						model.doDelete(bean);
						try {
							for(ProductBean prod: cart.getItems()) {
								if(prod.getCode() == bean.getCode())
									cart.deleteItem(prod);
							}
						}catch (ConcurrentModificationException e) {}
							
						request.setAttribute("message", "Prodotto "+ bean.getName()+" eliminato");
						response.sendRedirect("./successo.jsp");
						return;
					}
					
				} else if(action.equals("update")) {
					String id = request.getParameter("id");
					String name = request.getParameter("name");
					String description = request.getParameter("description");
					String price = request.getParameter("price");
					String quantity = request.getParameter("quantity");	
					
					//preliminaryControlUpdate(request, response, name, description, price, quantity);
					
					if(!isValidName(name)) {
						request.setAttribute("message", "Errore. Il nome non rispetta il formato richiesto.");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/nuovoprodotto.jsp");
						response.getWriter().write(BADREQUEST_MESS);
						dispatcher.forward(request, response);
						return;
					}
					
					if(!isValidDescription(description)) {
						request.setAttribute("message", "Errore. La descrizione non rispetta il formato richiesto.");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/nuovoprodotto.jsp");
						response.getWriter().write(BADREQUEST_MESS);
						dispatcher.forward(request, response);
						return;
					}
					if(!isValidPrice(""+price)) {
						request.setAttribute("message", "Errore. Il prezzo non rispetta il formato richiesto.");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/nuovoprodotto.jsp");
						response.getWriter().write(BADREQUEST_MESS);
						dispatcher.forward(request, response);
						return;
					}
					if(!isValidQty(""+quantity)) {
						request.setAttribute("message", "Errore. La quantità non rispetta il formato richiesto.");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/nuovoprodotto.jsp");
						response.getWriter().write(BADREQUEST_MESS);
						dispatcher.forward(request, response);
						return;
					}
					
					int price_int = Integer.parseInt(price);
					int quantity_int = Integer.parseInt(quantity);
					
					ProductBean bean = new ProductBean();
					bean.setCode(Integer.parseInt(id));
					bean.setName(name);
					bean.setDescription(description);
					bean.setPrice(price_int);
					bean.setQuantity(quantity_int);
					bean.setVisible();
					
					model.doUpdate(bean);
					request.setAttribute("message", "Prodotto "+ bean.getName()+" aggiornato");
					
					response.sendRedirect("./successo.jsp");
					response.getWriter().write(SUCCESS_MESS);
					return;
				}
			}
		} catch(SQLException | NumberFormatException e) {
			System.out.println("Error: "+ e.getMessage());
			request.setAttribute("error", e.getMessage());			
		}
		
		if(action == null || action.equals("null") || action.isBlank()) {
			request.setAttribute("cart", cart);
			
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		request.setAttribute("cart", cart);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/prodotto.jsp");
		dispatcher.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	@SuppressWarnings("unused")
	private void preliminaryControl(HttpServletRequest request, HttpServletResponse response, String name, String description, String price, String qty) throws ServletException, IOException {
		if(!isValidName(name)) {
			request.setAttribute("message", "Errore. Il nome non rispetta il formato richiesto.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/nuovoprodotto.jsp");
			response.getWriter().write(BADREQUEST_MESS);
			dispatcher.forward(request, response);
			return;
		}
		if(!isValidDescription(description)) {
			request.setAttribute("message", "Errore. La descrizione non rispetta il formato richiesto.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/nuovoprodotto.jsp");
			response.getWriter().write(BADREQUEST_MESS);
			dispatcher.forward(request, response);
			return;
		}
		if(!isValidPrice(price)) {
			request.setAttribute("message", "Errore. Il prezzo non rispetta il formato richiesto.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/nuovoprodotto.jsp");
			response.getWriter().write(BADREQUEST_MESS);
			dispatcher.forward(request, response);
			return;
		}
		if(!isValidQty(qty)) {
			request.setAttribute("message", "Errore. La quantità non rispetta il formato richiesto.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/nuovoprodotto.jsp");
			response.getWriter().write(BADREQUEST_MESS);
			dispatcher.forward(request, response);
			return;
		}
	}
	
	@SuppressWarnings("unused")
	private void preliminaryControlUpdate(HttpServletRequest request, HttpServletResponse response, String name, String description, String price, String qty) throws ServletException, IOException {
		if(!isValidName(name)) {
			request.setAttribute("message", "Errore. Il nome non rispetta il formato richiesto.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/gestioneprodotto.jsp");
			response.getWriter().write(BADREQUEST_MESS);
			dispatcher.forward(request, response);
			return;
		}
		if(!isValidDescription(description)) {
			request.setAttribute("message", "Errore. La descrizione non rispetta il formato richiesto.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/gestioneprodotto.jsp");
			response.getWriter().write(BADREQUEST_MESS);
			dispatcher.forward(request, response);
			return;
		}
		if(!isValidPrice(price)) {
			request.setAttribute("message", "Errore. Il prezzo non rispetta il formato richiesto.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/gestioneprodotto.jsp");
			response.getWriter().write(BADREQUEST_MESS);
			dispatcher.forward(request, response);
			return;
		}
		if(!isValidQty(qty)) {
			request.setAttribute("message", "Errore. La quantità non rispetta il formato richiesto.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/gestioneprodotto.jsp");
			response.getWriter().write(BADREQUEST_MESS);
			dispatcher.forward(request, response);
			return;
		}
	}
	
	static boolean isValidName(String name) {
	      String regex = "^.{5,50}$";
	      return name.matches(regex);
	}
	
	static boolean isValidDescription(String description) {
	      String regex = "^.{10,150}$";
	      return description.matches(regex);
	}
	
	static boolean isValidPrice(String price) {
	      String regex = "^[0-9]{1,6}$";
	      return price.matches(regex);
	}
	
	static boolean isValidQty(String qty) {
	      String regex = "^[0-9]{1,5}$";
	      return qty.matches(regex);
	}
}
