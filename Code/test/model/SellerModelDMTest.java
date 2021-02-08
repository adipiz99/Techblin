package model;

import java.sql.SQLException;

import entity.SellerBean;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SellerModelDMTest extends TestCase {

	private SellerBean TC2_1;
	private SellerBean TC2_2;
	private SellerBean TC2_3;
	private SellerBean TC2_5;
	private SellerBean TC2_6;
	private SellerBean TC2_7;
	private SellerModelDM sellerDao;
	
	
	protected void setUp() throws Exception {
		sellerDao = new SellerModelDM();
	}
	
	
	public void testDoSaveExceptionId() throws SQLException {
		TC2_1 = new SellerBean("ven!", "newvenditore@esempio.it", "Marco", "Rossi", "Venditore2");
		
		try {
			sellerDao.doSave(TC2_1);
			fail("Check not passed");
		}catch (InvalidDataException ex) {
			
		}
	}
	
	public void testDoSaveExceptionEmail() throws SQLException {
		TC2_2 = new SellerBean("NewVenditore", "esempio@esempio", "Marco", "Rossi", "Venditore2");
		
		try {
			sellerDao.doSave(TC2_2);
			fail("Check not passed");
		}catch (InvalidDataException ex) {
			
		}
	}
	
	public void testDoSaveExceptionPassword() throws SQLException {
		TC2_3 = new SellerBean("NewVenditore", "newvenditore@esempio.it", "Marco", "Rossi", "cia");
		
		try {
			sellerDao.doSave(TC2_3);
			fail("Check not passed");
		}catch (InvalidDataException ex) {
			
		}
	}
	
	public void testDoSaveExceptionNome() throws SQLException {
		TC2_5 = new SellerBean("NewVenditore", "newvenditore@esempio.it", "Marco2", "Rossi", "Venditore2");
		
		try {
			sellerDao.doSave(TC2_5);
			fail("Check not passed");
		}catch (InvalidDataException ex) {
			
		}
	}
	
	public void testDoSaveExceptionCognome() throws SQLException {
		TC2_6 = new SellerBean("NewVenditore", "newvenditore@esempio.it", "Marco", "Ross1", "Venditore2");
		
		try {
			sellerDao.doSave(TC2_6);
			fail("Check not passed");
		}catch (InvalidDataException ex) {
			
		}
	}
	
	public void testDoSave() throws SQLException {
		TC2_7 = new SellerBean("NewVenditore", "newvenditore@esempio.it", "Marco", "Rossi", "Venditore2");
		String email = "newvenditore@esempio.it";
		
		sellerDao.doSave(TC2_7);
		SellerBean x= sellerDao.doRetrieveByKey(email);
		assertEquals(email, x.getEmail());
	}
	
	public static Test suite() {
		return new TestSuite(SellerModelDMTest.class);
	}
	
}
