package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import entity.ProductBean;

public class ProductModelDM implements ProductModel<ProductBean> {

	@Override
	public ProductBean doRetrieveByKey(String code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProductBean bean = new ProductBean();
		
		String selectSQL = "SELECT * FROM product WHERE code = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, Integer.parseInt(code));
			
			System.out.println("doRetrieveByKey: "+ preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean.setCode(rs.getInt("code"));
				bean.setName(rs.getString("name"));
				bean.setDescription(rs.getString("description"));
				bean.setPrice(rs.getInt("price"));
				bean.setQuantity(rs.getInt("quantity"));	
				int visibility= (rs.getInt("visible"));
				if (visibility == 1)
					bean.setVisible();
				else bean.setInvisible();
			}			
			
			System.out.println(bean);
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return bean;
	}

	@Override
	public Collection<ProductBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<ProductBean> products = new LinkedList<ProductBean>();
		
		String selectSQL = "SELECT * FROM product";
		
		if(order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			System.out.println("doRetrieveAll:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				ProductBean bean = new ProductBean();
				
				bean.setCode(rs.getInt("code"));
				bean.setName(rs.getString("name"));
				bean.setDescription(rs.getString("description"));
				bean.setPrice(rs.getInt("price"));
				bean.setQuantity(rs.getInt("quantity"));
				int visibility= (rs.getInt("visible"));
				if (visibility == 1)
					bean.setVisible();
				else bean.setInvisible();

				
				products.add(bean);
			}
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return products;
	}

	@Override
	public void doSave(ProductBean product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		boolean validity= preliminaryControl(product.getName(), product.getDescription(), "" + product.getPrice(), "" + product.getQuantity());
		
		if(!validity) throw new InvalidDataException();  //Lancio eccezione per errore dati
		
		String insertSQL = "INSERT INTO product" +
				" (name, description, price, quantity, visible) VALUES (?, ?, ?, ?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, product.getName());
			preparedStatement.setString(2, product.getDescription());
			preparedStatement.setInt(3, product.getPrice());
			preparedStatement.setInt(4, product.getQuantity());
			preparedStatement.setInt(5, product.getVisible());
			
			System.out.println("doSave: "+ preparedStatement.toString());
			preparedStatement.executeUpdate();
		
			connection.commit();

		} finally {
				try {
					if(preparedStatement != null) 
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}		
	}

	@Override
	public void doUpdate(ProductBean product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		boolean validity= preliminaryControl(product.getName(), product.getDescription(), "" + product.getPrice(), "" + product.getQuantity());
		
		if(!validity) throw new InvalidDataException();  //Lancio eccezione per errore dati
		
		String updateSQL = "UPDATE product SET " +
				" name = ?, description = ?, price = ?, quantity = ?, visible = ? WHERE code = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);	
			
			preparedStatement.setString(1, product.getName());
			preparedStatement.setString(2, product.getDescription());
			preparedStatement.setInt(3, product.getPrice());
			preparedStatement.setInt(4, product.getQuantity());
			preparedStatement.setInt(5, product.getVisible());
			
			preparedStatement.setInt(6, product.getCode());
			
			System.out.println("doUpdate: "+ preparedStatement.toString());
			preparedStatement.executeUpdate();
			
			connection.commit();
			
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}	
	}

	@Override
	public void doDelete(ProductBean product) throws SQLException {
		product.setInvisible();
		doUpdate(product);
	}
	
	private boolean preliminaryControl(String name, String description, String price, String qty) {
		if(!isValidName(name) || !isValidQty(qty) || !isValidPrice(price) || !isValidDescription(description))
			return false;
		return true;
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
