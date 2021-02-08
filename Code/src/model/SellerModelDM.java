package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import entity.SellerBean;

public class SellerModelDM implements SellerModel<SellerBean> {

	@Override
	public SellerBean doRetrieveByKey(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		if(!isValidEmail(email)) throw new InvalidDataException(); //Lancio eccezione per errore dati

		SellerBean bean = new SellerBean();
		
		String selectSQL = "SELECT * FROM venditore WHERE email = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);
			
			System.out.println("doRetrieveByKey: "+ preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean.setID(rs.getString("ID"));
				bean.setEmail(rs.getString("email"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setPassword(rs.getString("password"));
				bean.setRegistrazione(rs.getDate("registrazione").toString());
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
	public Collection<SellerBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<SellerBean> products = new LinkedList<SellerBean>();
		
		String selectSQL = "SELECT * FROM venditore";
		
		if(order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			System.out.println("doRetrieveAll:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				SellerBean bean = new SellerBean();
				
				bean.setID(rs.getString("ID"));
				bean.setEmail(rs.getString("email"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setPassword(rs.getString("password"));	
				bean.setRegistrazione(rs.getDate("registrazione").toString());
				
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
	public void doSave(SellerBean seller) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		boolean validity= checkData(seller.getID(),seller.getEmail(), seller.getPassword(), seller.getNome(), seller.getCognome());
		
		if(!validity) throw new InvalidDataException();  //Lancio eccezione per errore dati
		
		String insertSQL = "INSERT INTO venditore" +
				" (ID, email, nome, cognome, password, registrazione) VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, seller.getID());
			preparedStatement.setString(2, seller.getEmail());
			preparedStatement.setString(3, seller.getNome());
			preparedStatement.setString(4, seller.getCognome());
			preparedStatement.setString(5, seller.getPassword());
			preparedStatement.setDate(6, java.sql.Date.valueOf(seller.getRegistrazione()));
			
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
	public void doUpdate(SellerBean seller) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		boolean validity= checkData(seller.getID(), seller.getEmail(), seller.getPassword(), seller.getNome(), seller.getCognome());
		
		if(!validity) throw new InvalidDataException();  //Lancio eccezione per errore dati
		
		String updateSQL = "UPDATE venditore SET " +
				" ID = ?, nome = ?, cognome = ?, password = ?, registrazione = ?, email = ? WHERE email = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);	
			
			preparedStatement.setString(1, seller.getID());
			preparedStatement.setString(2, seller.getNome());
			preparedStatement.setString(3, seller.getCognome());
			preparedStatement.setString(4, seller.getPassword());
			preparedStatement.setDate(5, java.sql.Date.valueOf(seller.getRegistrazione()));
			
			preparedStatement.setString(6, seller.getEmail());
			preparedStatement.setString(7, seller.getEmail());
			
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
	
	public void doUpdate(SellerBean seller, String oldemail) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE venditore SET " +
				" ID = ?, nome = ?, cognome = ?, password = ?, registrazione = ?, email = ? WHERE email = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);	
			
			preparedStatement.setString(1, seller.getID());
			preparedStatement.setString(2, seller.getNome());
			preparedStatement.setString(3, seller.getCognome());
			preparedStatement.setString(4, seller.getPassword());
			preparedStatement.setDate(5, java.sql.Date.valueOf(seller.getRegistrazione()));
			
			preparedStatement.setString(6, seller.getEmail());
			preparedStatement.setString(7, oldemail);
			
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
	public void doDelete(SellerBean seller) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String deleteSQL = "DELETE FROM venditore WHERE email = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, seller.getEmail());
			
			System.out.println("doDelete: "+ preparedStatement.toString());
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
	
	private boolean checkData(String id, String email, String password, String nome, String cognome) {
		if(!isValidId(id) || !isValidEmail(email) || !isValidPassword(password) || !isValidNameOrSurname(nome) || !isValidNameOrSurname(cognome))
			return false;
		return true;
	}
	
	static boolean isValidEmail(String email) {
	      String regex = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
	      return email.matches(regex);
	}
	
	static boolean isValidPassword(String password) {
	      String regex = "^.{5,32}$";
	      return password.matches(regex);
	}
	
	static boolean isValidId(String id) {
	      String regex = "^[a-zA-Z0-9]{4,20}$";
	      return id.matches(regex);
	}
	
	static boolean isValidNameOrSurname(String s) {
	      String regex = "^[A-Za-z]{1,20}$";
	      return s.matches(regex);
	}

}
