package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import entity.BuyerBean;

public class BuyerModelDM implements BuyerModel<BuyerBean> {

	@Override
	public BuyerBean doRetrieveByKey(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		if(!isValidEmail(email)) throw new InvalidDataException(); //Lancio eccezione per errore dati

		BuyerBean bean = new BuyerBean();
		
		String selectSQL = "SELECT * FROM acquirente WHERE email = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);
			
			System.out.println("doRetrieveByKey: "+ preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
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
	public Collection<BuyerBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<BuyerBean> products = new LinkedList<BuyerBean>();
		
		String selectSQL = "SELECT * FROM acquirente";
		
		if(order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			System.out.println("doRetrieveAll:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				BuyerBean bean = new BuyerBean();

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
	public void doSave(BuyerBean buyer) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		boolean validity= checkData(buyer.getEmail(), buyer.getPassword(), buyer.getNome(), buyer.getCognome());
		
		if(!validity) throw new InvalidDataException();  //Lancio eccezione per errore dati
		
		String insertSQL = "INSERT INTO acquirente" +
				" (email, nome, cognome, password, registrazione) VALUES (?, ?, ?, ?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, buyer.getEmail());
			preparedStatement.setString(2, buyer.getNome());
			preparedStatement.setString(3, buyer.getCognome());
			preparedStatement.setString(4, buyer.getPassword());
			preparedStatement.setDate(5, java.sql.Date.valueOf(buyer.getRegistrazione()));
			
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
	public void doUpdate(BuyerBean buyer) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		boolean validity= checkData(buyer.getEmail(), buyer.getPassword(), buyer.getNome(), buyer.getCognome());
		
		if(!validity) throw new InvalidDataException();  //Lancio eccezione per errore dati
		
		String updateSQL = "UPDATE acquirente SET " +
				"nome = ?, cognome = ?, password = ?, registrazione = ?, email = ?  WHERE email = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);	
			
			preparedStatement.setString(1, buyer.getNome());
			preparedStatement.setString(2, buyer.getCognome());
			preparedStatement.setString(3, buyer.getPassword());
			preparedStatement.setDate(4, java.sql.Date.valueOf(buyer.getRegistrazione()));
			
			preparedStatement.setString(5, buyer.getEmail());
			preparedStatement.setString(6, buyer.getEmail());
			
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
	
	public void doUpdate(BuyerBean buyer, String oldemail) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE acquirente SET " +
				"nome = ?, cognome = ?, password = ?, registrazione = ?, email = ?  WHERE email = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);	
			
			preparedStatement.setString(1, buyer.getNome());
			preparedStatement.setString(2, buyer.getCognome());
			preparedStatement.setString(3, buyer.getPassword());
			preparedStatement.setDate(4, java.sql.Date.valueOf(buyer.getRegistrazione()));
			
			preparedStatement.setString(5, buyer.getEmail());
			preparedStatement.setString(6, oldemail);
			
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
	public void doDelete(BuyerBean buyer) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String deleteSQL = "DELETE FROM acquirente WHERE email = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, buyer.getEmail());
			
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
	
	private boolean checkData(String email, String password, String nome, String cognome) {
		if(!isValidEmail(email) || !isValidPassword(password) || !isValidNameOrSurname(nome) || !isValidNameOrSurname(cognome))
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
	
	static boolean isValidNameOrSurname(String s) {
	      String regex = "^[A-Za-z]{1,20}$";
	      return s.matches(regex);
	}

}
