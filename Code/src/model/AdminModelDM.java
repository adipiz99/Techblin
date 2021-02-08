package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import entity.AdminBean;

public class AdminModelDM implements AdminModel<AdminBean> {

	@Override
	public AdminBean doRetrieveByKey(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		if(!isValidEmail(email)) throw new InvalidDataException(); //Lancio eccezione per errore dati

		AdminBean bean = new AdminBean();
		
		String selectSQL = "SELECT * FROM amministratore WHERE email = ?";
		
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
	public Collection<AdminBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<AdminBean> products = new LinkedList<AdminBean>();
		
		String selectSQL = "SELECT * FROM amministratore";
		
		if(order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			System.out.println("doRetrieveAll:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				AdminBean bean = new AdminBean();
				
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
	public void doSave(AdminBean admin) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO amministratore" +
				" (email, nome, cognome, password, registrazione) VALUES (?, ?, ?, ?, ?)";
		
		boolean validity= checkData(admin.getEmail(), admin.getPassword(), admin.getNome(), admin.getCognome());
		
		if(!validity) throw new InvalidDataException();  //Lancio eccezione per errore dati
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, admin.getEmail());
			preparedStatement.setString(2, admin.getNome());
			preparedStatement.setString(3, admin.getCognome());
			preparedStatement.setString(4, admin.getPassword());
			preparedStatement.setDate(5, java.sql.Date.valueOf(admin.getRegistrazione()));
			
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
	public void doUpdate(AdminBean admin) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		boolean validity= checkData(admin.getEmail(), admin.getPassword(), admin.getNome(), admin.getCognome());
		
		if(!validity) throw new InvalidDataException();  //Lancio eccezione per errore dati
		
		String updateSQL = "UPDATE amministratore SET " +
				"nome = ?, cognome = ?, password = ?, registrazione = ? email=? WHERE email = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);	
			
			preparedStatement.setString(1, admin.getNome());
			preparedStatement.setString(2, admin.getCognome());
			preparedStatement.setString(3, admin.getPassword());
			preparedStatement.setString(5, admin.getEmail());
			preparedStatement.setString(6, admin.getEmail());
			preparedStatement.setDate(4, java.sql.Date.valueOf(admin.getRegistrazione()));
			
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
	
	public void doUpdate(AdminBean admin, String oldemail) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE amministratore SET " +
				"nome = ?, cognome = ?, password = ?, registrazione = ? email=? WHERE email = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);	
			
			preparedStatement.setString(1, admin.getNome());
			preparedStatement.setString(2, admin.getCognome());
			preparedStatement.setString(3, admin.getPassword());
			preparedStatement.setString(5, admin.getEmail());
			preparedStatement.setString(6, oldemail);
			preparedStatement.setDate(4, java.sql.Date.valueOf(admin.getRegistrazione()));
			
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
	public void doDelete(AdminBean admin) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String deleteSQL = "DELETE FROM amministratore WHERE email = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, admin.getEmail());
			
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
