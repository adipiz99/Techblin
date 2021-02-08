package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import entity.OrderBean;

public class OrderModelDM implements OrderModel<OrderBean> {

	@Override
	public OrderBean doRetrieveByKey(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		OrderBean bean = new OrderBean();
		
		String selectSQL = "SELECT * FROM ordine WHERE id = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);
			
			System.out.println("doRetrieveByKey: "+ preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean.setId(rs.getInt("id"));
				bean.setTotale(rs.getInt("totale"));
				bean.setDataOrdine(rs.getDate("dataOrdine"));	
				bean.setStato(rs.getString("stato"));
				bean.setAcquirente(rs.getString("acquirente"));
				bean.setIndirizzo(rs.getString("indirizzo"));
				bean.setCittà(rs.getString("città"));
				bean.setAcquirente(rs.getString("provincia"));
				bean.setCap(rs.getString("cap"));
				bean.setTipologiaPagamento(rs.getString("tipologiaPagamento"));
				bean.setDatiPagamento(rs.getString("datiPagamento"));
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
	public Collection<OrderBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<OrderBean> orders = new LinkedList<OrderBean>();
		
		String selectSQL = "SELECT * FROM ordine";
		
		if(order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			System.out.println("doRetrieveAll:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				OrderBean bean = new OrderBean();
				
				bean.setId(rs.getInt("id"));
				bean.setTotale(rs.getInt("totale"));
				bean.setDataOrdine(rs.getDate("dataOrdine"));	
				bean.setStato(rs.getString("stato"));
				bean.setAcquirente(rs.getString("acquirente"));
				bean.setIndirizzo(rs.getString("indirizzo"));
				bean.setCittà(rs.getString("città"));
				bean.setProvincia(rs.getString("provincia"));
				bean.setCap(rs.getString("cap"));
				bean.setTipologiaPagamento(rs.getString("tipologiaPagamento"));
				bean.setDatiPagamento(rs.getString("datiPagamento"));
				
				orders.add(bean);
			}
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return orders;
	}

	@Override
	public void doSave(OrderBean order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		if(!preliminaryControlUser(order.getAcquirente(), order.getIndirizzo(), order.getProvincia(), order.getCittà(), order.getCap(),order.getNome(),order.getCognome(),order.getDatiPagamento())) throw new InvalidDataException();
		
		String insertSQL = "INSERT INTO ordine" +
				" (totale, dataOrdine, stato, acquirente, indirizzo, cap, città, provincia, tipologiaPagamento, datiPagamento, nome, cognome) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setInt(1, order.getTotale());
			preparedStatement.setDate(2, order.getDataOrdine());
			preparedStatement.setString(3, order.getStato());
			preparedStatement.setString(4, order.getAcquirente());
			preparedStatement.setString(5, order.getIndirizzo());
			preparedStatement.setString(6, order.getCap());
			preparedStatement.setString(7, order.getCittà());
			preparedStatement.setString(8, order.getProvincia());
			preparedStatement.setString(9, order.getTipologiaPagamento());
			preparedStatement.setString(10, order.getDatiPagamento());
			preparedStatement.setString(11, order.getNome());
			preparedStatement.setString(12, order.getCognome());
			
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
	public void doUpdate(OrderBean order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		if(!preliminaryControlUser(order.getAcquirente(), order.getIndirizzo(), order.getProvincia(),order.getCittà(), order.getCap(), order.getNome(),order.getCognome(),order.getDatiPagamento())) throw new InvalidDataException();
		
		String updateSQL = "UPDATE ordine SET " +
				"totale = ?, dataOrdine = ?, stato = ?, acquirente = ?, indirizzo = ?, cap = ?, città = ?, provincia = ?, tipologiaPagamento = ?, datiPagamento = ? WHERE id = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);	
			
			preparedStatement.setInt(11, order.getId());
			preparedStatement.setInt(1, order.getTotale());
			preparedStatement.setDate(2, order.getDataOrdine());
			preparedStatement.setString(3, order.getStato());
			preparedStatement.setString(4, order.getAcquirente());
			preparedStatement.setString(5, order.getIndirizzo());
			preparedStatement.setString(6, order.getCap());
			preparedStatement.setString(7, order.getCittà());
			preparedStatement.setString(8, order.getProvincia());
			preparedStatement.setString(9, order.getTipologiaPagamento());
			preparedStatement.setString(10, order.getDatiPagamento());
			
			preparedStatement.setInt(8, order.getId());
			
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
	public void doDelete(OrderBean order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String deleteSQL = "DELETE FROM ordine WHERE id = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, order.getId());
			
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
	
	private boolean preliminaryControlUser(String email, String address, String provincia, String city, String cap, String nome, String cognome, String datiPagamento) {
		if(!isValidEmail(email) || !isValidAddress(address) || !isValidProvincia(provincia) || !isValidCity(city) || !isValidCap(cap) || !isValidName(nome) || !isValidSurname(cognome) || datiPagamento.isBlank())
			return false;
		return true;
	}
	
	static boolean isValidName(String name) {
		String regex = "^[A-Za-z]{1,20}$";
		return name.matches(regex);
	}
	
	static boolean isValidSurname(String surname) {
		String regex = "^[A-Za-z]{1,20}$";
		return surname.matches(regex);
	}
	
	static boolean isValidEmail(String email) {
	      String regex = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
	      return email.matches(regex);
	}
	
	static boolean isValidAddress(String address) {
	      String regex = "^[a-zA-Z0-9,. ]*$";
	      return address.matches(regex);
	}
	
	static boolean isValidCity(String city) {
	      String regex = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$";
	      return city.matches(regex);
	}
	
	static boolean isValidReg(String regione) {
	      String regex = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$";
	      return regione.matches(regex);
	}
	
	static boolean isValidProvincia(String prov) {
	      String regex = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$";
	      return prov.matches(regex);
	}
	
	static boolean isValidCap(String cap) {
	      String regex = "^([0-9]{5})$";
	      return cap.matches(regex);
	}

}
