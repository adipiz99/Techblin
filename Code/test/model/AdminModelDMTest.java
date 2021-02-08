package model;

import java.sql.SQLException;

import entity.AdminBean;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AdminModelDMTest extends TestCase {
	
	
	AdminModelDM adminDao;
	AdminBean TC9_1,TC9_2,TC9_3,TC9_4,TC9_5,TC9_6,TC9_7;
	
	
	public void setUp() {
		adminDao = new AdminModelDM();
	}
	
	
	//TC9_1 = new AdminBean("Vincenzo","Bianchi","admin2@example.it","admin2");
	public void testDoSaveEmailException() throws SQLException {
		TC9_1 = new AdminBean("admin2@example","Vincenzo","Bianchi","admin2");
		
		try {
			adminDao.doSave(TC9_1);
			fail("Check not passed");
		}catch (InvalidDataException ex) {
			
		}
	}
	
	public void testDoSaveExceptionDBEmailAlreadyPresent(){
		TC9_2 = new AdminBean("admin@example.it","Vincenzo","Bianchi","Admin2");
		
		try {
			adminDao.doSave(TC9_2);
			fail("Check not passed");
		}catch (SQLException e) {
			
		}
	}
	
	public void testDoSavePasswordException() throws SQLException{
		TC9_3 = new AdminBean("admin2@example.it","Vincenzo","Bianchi","adm");
		
		try {
			adminDao.doSave(TC9_3);
			fail("Check not passed");
		}catch (InvalidDataException e) {
			
		}
	}
	
	public void testDoSaveNameException() throws SQLException{
		TC9_5 = new AdminBean("admin2@example.it","Vincenzo1","Bianchi","Admin2");
		
		try {
			adminDao.doSave(TC9_5);
			fail("Check not passed");
		}catch (InvalidDataException e) {
			
		}
	}
	
	public void testDoSaveCognomeException() throws SQLException{
		TC9_6 = new AdminBean("admin2@example.it","Vincenzo","B!anchi","Admin2");
		
		try {
			adminDao.doSave(TC9_6);
			fail("Check not passed");
		}catch (InvalidDataException e) {
			
		}
	}
	
	public void testDoSave() throws SQLException{
		TC9_7 = new AdminBean("admin2@example.it","Vincenzo","Bianchi","Admin2");
		adminDao.doSave(TC9_7);
		AdminBean x = adminDao.doRetrieveByKey(TC9_7.getEmail());
		assertEquals(TC9_7.getEmail(),x.getEmail());
	}
	
	public static Test suite() {
		return new TestSuite(AdminModelDMTest.class);
	}

}
