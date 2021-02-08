package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import entity.OrderProductBean;

public class OrderProductModelDM implements OrderProductModel<OrderProductBean> {

	@Override
	public OrderProductBean doRetrieveByKey(int prodotto, int ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		OrderProductBean bean = new OrderProductBean();
		
		String selectSQL = "SELECT * FROM prodottiOrdine WHERE prodotto = ? AND ordine = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, prodotto);
			preparedStatement.setInt(2, ordine);
			
			System.out.println("doRetrieveByKey: "+ preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean.setProdotto(rs.getInt("prodotto"));
				bean.setOrdine(rs.getInt("ordine"));				
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
	public Collection<OrderProductBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<OrderProductBean> orderProducts = new LinkedList<OrderProductBean>();
		
		String selectSQL = "SELECT * FROM prodottiOrdine";
		
		if(order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			System.out.println("doRetrieveAll:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				OrderProductBean bean = new OrderProductBean();
				
				bean.setProdotto(rs.getInt("prodotto"));
				bean.setOrdine(rs.getInt("ordine"));
				
				orderProducts.add(bean);
			}
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return orderProducts;
	}

	@Override
	public void doSave(OrderProductBean po) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO prodottiOrdine" +
				" (prodotto, ordine) VALUES (?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setInt(1, po.getProdotto());
			preparedStatement.setInt(2, po.getOrdine());
			
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
	public void doUpdate(OrderProductBean po) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE prodottiOrdine SET " +
				" WHERE prodotto = ? AND ordine = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);	
			
			preparedStatement.setInt(1, po.getProdotto());
			preparedStatement.setInt(2, po.getOrdine());
			
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
	public void doDelete(OrderProductBean po) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String deleteSQL = "DELETE FROM prodottiOrdine WHERE prodotto = ? AND ordine = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, po.getProdotto());
			preparedStatement.setInt(2, po.getOrdine());
			
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

}
