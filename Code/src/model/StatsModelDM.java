package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StatsModelDM {
	
	public ArrayList<Integer> calculate(Date from, Date to) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;
		PreparedStatement preparedStatement3 = null;
		PreparedStatement preparedStatement4 = null;
		PreparedStatement preparedStatement5 = null;
		PreparedStatement preparedStatement6 = null;
		
		if(!from.before(to)) throw new InvalidDataException(); //Lancio eccezione
		
		ArrayList<Integer> result= new ArrayList<Integer>();
		
		String selectSQL;
		
		try {
			// Calcolo numero di registrazioni da DATA 1  a DATA 2
			
			int count= 1;
			int registrazioni=0;
			
			connection = DriverManagerConnectionPool.getConnection();
			selectSQL= "SELECT COUNT(*) as res FROM acquirente WHERE registrazione >= ? AND registrazione <= ?";
			
			preparedStatement2 = connection.prepareStatement(selectSQL);
			
			preparedStatement2.setDate(1, from);
			preparedStatement2.setDate(2, to);
			
			System.out.println("Stat n° " + count++ + ": " + preparedStatement2.toString());
			ResultSet rs= preparedStatement2.executeQuery();
			
			while(rs.next())
			registrazioni += rs.getInt("res");
			
			selectSQL= "SELECT COUNT(*) as res FROM venditore WHERE registrazione >= ? AND registrazione <= ?";
			
			preparedStatement3 = connection.prepareStatement(selectSQL);
			
			preparedStatement3.setDate(1, from);
			preparedStatement3.setDate(2, to);
			
			System.out.println("Stat n° " + count++ + ": " + preparedStatement3.toString());
			rs= preparedStatement3.executeQuery();
			
			while(rs.next())
			registrazioni += rs.getInt("res");
			
			selectSQL= "SELECT COUNT(*) as res FROM amministratore WHERE registrazione >= ? AND registrazione <= ?";
			
			preparedStatement4 = connection.prepareStatement(selectSQL);
			
			preparedStatement4.setDate(1, from);
			preparedStatement4.setDate(2, to);
			
			System.out.println("Stat n° " + count++ + ": " + preparedStatement4.toString());
			rs= preparedStatement4.executeQuery();
			
			while(rs.next())
			registrazioni += rs.getInt("res");
			result.add(registrazioni);
			
			//	Calcolo numero di prodotti in vendita e Calcolo numero di prodotti in vendita e attualmente disponibili
			
			selectSQL= "SELECT * FROM product";
			
			preparedStatement5 = connection.prepareStatement(selectSQL);
			
			System.out.println("Stat n° " + count++ + ": " + preparedStatement5.toString());
			rs= preparedStatement5.executeQuery();
			
			int numProdotti=0;
			int disponibili=0;
			
			while(rs.next()) {
				numProdotti++;
				if(rs.getInt("quantity") > 0)
					disponibili++;
			}
			result.add(numProdotti);
			result.add(disponibili);
			
			// Calcolo numero di ordini e Calcolo totale transazioni ordini
			
			selectSQL= "SELECT * FROM ordine";
			
			preparedStatement6 = connection.prepareStatement(selectSQL);
			
			System.out.println("Stat n° " + count++ + ": " + preparedStatement6.toString());
			rs= preparedStatement6.executeQuery();
			
			int ordini=0;
			int totale=0;
			
			while(rs.next()) {
				ordini++;
				totale += rs.getInt("totale");
			}
			result.add(ordini);
			result.add(totale);
			
			// Calcolo numero ordini da DATA 1 a DATA 2
			
			selectSQL = "SELECT COUNT(*) as res FROM ordine WHERE dataOrdine >= ? AND dataOrdine <= ?";
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setDate(1, from);
			preparedStatement.setDate(2, to);
					
			System.out.println("Stat n° " + count++ + ": " + preparedStatement.toString());
			rs= preparedStatement.executeQuery();
			while(rs.next()) {
				result.add(rs.getInt("res"));
			}
		} 
		finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
				if(preparedStatement2 != null) 
					preparedStatement2.close();
				if(preparedStatement3 != null) 
					preparedStatement3.close();
				if(preparedStatement4 != null) 
					preparedStatement4.close();
				if(preparedStatement5 != null) 
					preparedStatement5.close();
				if(preparedStatement6 != null) 
					preparedStatement6.close();
			} 
			finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return result;
	}
}
