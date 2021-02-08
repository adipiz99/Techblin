package model;
import java.sql.SQLException;

import entity.BuyerBean;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class BuyerModelDMTest extends TestCase {

	private BuyerBean TC1_1;
	private BuyerBean TC1_2;
	private BuyerBean TC1_3;
	private BuyerBean TC1_5;
	private BuyerBean TC1_6;
	private BuyerBean TC1_7;
	private BuyerModelDM buyerDao;

	
	protected void setUp() throws Exception{
		buyerDao = new BuyerModelDM();
	}
	
	
	
	public void testDoSaveExceptionMail() throws SQLException {
		TC1_1 = new BuyerBean("esempio@esempio", "Marco", "Rossi", "MarcoRossi1");
	
		try {
			buyerDao.doSave(TC1_1);
			fail("Check not passed");
		}catch (InvalidDataException ex) {
			
		}
	}
	
	public void testDoSaveExceptionPassword() throws SQLException {
		TC1_3 = new BuyerBean("newacquirente@example.it", "Mario", "Verdi", "ciao");
		try {
			buyerDao.doSave(TC1_3);
			fail("Check not passed");
		}catch (InvalidDataException ex) {
			
		}
	}
	
	public void testDoSaveExceptionNome() throws SQLException {
		TC1_5 = new BuyerBean("newacquirente@example.it", "Mario2", "Verdi", "Acquirente2");
		try {
			buyerDao.doSave(TC1_5);
			fail("Check not passed");
		}catch (InvalidDataException ex) {
			
		}
	}
	
	public void testDoSaveExceptionCognome() throws SQLException {
		TC1_6 = new BuyerBean("newacquirente@example.it", "Mario", "Verdi9", "Acquirente2");
		try {
			buyerDao.doSave(TC1_6);
			fail("Check not passed");
		}catch (InvalidDataException ex) {
			
		}
	}
	
	
	public void testDoSaveExcecptionDBEmailAlreadyPresent() {
		TC1_2 = new BuyerBean("acquirente@example.it", "Mario", "Verdi", "Acquirente2");
		try {
			buyerDao.doSave(TC1_2);
			fail("Check not passed");
		} 
		
		catch (SQLException e) {
		
		}
		}
	

	public void testDoSave() throws SQLException {
		TC1_7 = new BuyerBean("newacquirente@example.it", "Mario", "Verdi", "Acquirente2");
		buyerDao.doSave(TC1_7);
		BuyerBean x = buyerDao.doRetrieveByKey(TC1_7.getEmail());
		assertEquals("newacquirente@example.it",x.getEmail());
	}

																	//************************TC3_1*********************************//
	public void testDoRetrieveByKeyExceptionEmail() throws SQLException {
		String TC3_1 = "newacquirente@esempio.";
		try {
			buyerDao.doRetrieveByKey(TC3_1);
			fail("Check not passed");
		} catch (InvalidDataException e) {
			
		}
	}
	
	public void testDoRetrieveByKeyNotRegistered() throws SQLException {
		String TC3_2 = "newacquirente4@esempio.it";
		BuyerBean nonRegistrato = buyerDao.doRetrieveByKey(TC3_2);
		BuyerBean testX = new BuyerBean(); //Utente non inizializzato quindi vuoto
		assertEquals(testX,nonRegistrato); //se uguali vuol dire che ci restituisce un oggetto vuoto la ricerca nel DB
		
	}
	
	public void testDoRetrieveByKeyPasswordNotEquals() throws SQLException {
		String TC3_3 = "acquirente@example.it";
		String TC3_3pw = "Acquirente1";
		BuyerBean acquirente = buyerDao.doRetrieveByKey(TC3_3);
		assertFalse(TC3_3pw.equals(acquirente.getPassword()));	
	}
	
	public void testDoRetrieveByKey() throws SQLException {
		String TC3_3 = "acquirente@example.it";
		String TC3_3pw = "Acquirente";
		BuyerBean acquirente = buyerDao.doRetrieveByKey(TC3_3);
		assertTrue(TC3_3pw.equals(acquirente.getPassword()));	
	}
	
	
	public void testDoUpdateIncorrectOldPassword() throws SQLException {
		String TC4_1 = "acquirente";
		BuyerBean acquirente = buyerDao.doRetrieveByKey("acquirente@example.it");
		assertFalse(TC4_1.equals(acquirente.getPassword()));	
	}
	
	public void testDoUpdatePasswordException() throws SQLException {
		String TC4_2 = "acq";
		BuyerBean acquirente = buyerDao.doRetrieveByKey("acquirente@example.it");
		acquirente.setPassword(TC4_2);
		try {
			buyerDao.doUpdate(acquirente);
			fail("Check not passed");
		} catch (InvalidDataException e) {
			
		}
	}
	
	public void testDoUpdate() throws SQLException {
		String TC4_2 = "Acquirente1";
		BuyerBean acquirente = buyerDao.doRetrieveByKey("acquirente@example.it");
		acquirente.setPassword(TC4_2);
		buyerDao.doUpdate(acquirente);
		assertEquals(TC4_2,acquirente.getPassword());
	}
	
	
	
	public static Test suite() {
		return new TestSuite(BuyerModelDMTest.class);
	}
	
	
}

