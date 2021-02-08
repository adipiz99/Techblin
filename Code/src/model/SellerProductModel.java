package model;

import java.sql.SQLException;
import java.util.Collection;

public interface SellerProductModel<T> {

public T doRetrieveByKey(String code, int code2) throws SQLException;
	
	public Collection<T> doRetrieveAll(String order) throws SQLException;
	
	public void doSave(T product) throws SQLException;
	
	public void doUpdate(T product) throws SQLException;
	
	public void doDelete(T product) throws SQLException;
}
