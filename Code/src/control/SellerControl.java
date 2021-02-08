package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.ProductBean;
import entity.SellerProductBean;
import model.ProductModelDM;
import model.SellerProductModelDM;


@WebServlet("/SellerControl")
public class SellerControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductModelDM model= new ProductModelDM();
	private SellerProductModelDM modelVP= new SellerProductModelDM();
   
    public SellerControl() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sellerEmail= (String) request.getParameter("seller");
		String action= (String) request.getParameter("action");
		
		if(action.equals("vetrina")) {
		ArrayList<ProductBean> products= new ArrayList<ProductBean>();
		Collection<SellerProductBean> seller;
		
		try {
			seller= modelVP.doRetrieveAll(null);
		
			Iterator<?> it3  = seller.iterator();
			while(it3.hasNext()) {
				SellerProductBean bean = ((SellerProductBean)it3.next());
				String email= bean.getVenditore();
				if(!email.equals(sellerEmail)) {
					it3.remove();
					break;
				}
			}
		
			Iterator<?> it2  = seller.iterator();
			while(it2.hasNext()) {
				SellerProductBean sp = ((SellerProductBean)it2.next());
				String code= "" + sp.getProdotto();
				ProductBean bean = (ProductBean) model.doRetrieveByKey(code);
				if(bean.getCode() != -1 && bean.getVisible() == 1)
				products.add(bean);
			}
		
			System.out.println("Trovati " + products.size() + " elementi");
			
			request.setAttribute("result", products);
			request.setAttribute("email", sellerEmail);
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/vetrina.jsp");
			dispatcher.forward(request, response);
		
		} catch (SQLException e) {e.printStackTrace();}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
